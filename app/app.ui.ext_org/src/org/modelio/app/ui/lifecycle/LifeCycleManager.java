/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.app.ui.lifecycle;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.ModelioEnv;
import org.modelio.app.core.activation.ActivationService;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.app.core.events.ModelioEventService;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.core.navigate.ModelioNavigationService;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.core.picking.ModelioPickingService;
import org.modelio.app.project.core.services.CommandLineData;
import org.modelio.app.ui.ApplicationTitleUpdater;
import org.modelio.app.ui.SwapLogMonitor;
import org.modelio.app.ui.login.Splash;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.app.ui.progress.ModelioProgressService;
import org.modelio.app.ui.welcome.WelcomeView;
import org.modelio.app.update.plugin.AppUpdateExt;
import org.modelio.core.rcp.inputpart.IInputPartService;
import org.modelio.core.rcp.inputpart.InputPartService;
import org.modelio.core.rcp.system.ModelioHelpSystem;
import org.modelio.core.rcp.uiservice.IModelioUiService;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.module.catalog.FileModuleStore;
import org.modelio.mda.infra.service.plugins.AggregatedModuleStore;
import org.modelio.mda.infra.service.plugins.PluginModulesCache;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.utils.log.writers.PluginLogger;
import org.modelio.vbasic.net.SslManager;
import org.modelio.vcore.session.impl.cache.MemoryManager;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;
import org.osgi.service.prefs.BackingStoreException;

/**
 * E4 life cycle manager.
 * <p>
 * Drives Modelio start sequence.
 */
@objid ("474f0435-3e51-43c4-b021-4a3fae2bfab1")
public class LifeCycleManager {
    @objid ("c767d44e-2514-4993-947d-a221ee6abb67")
    private static final String WELCOME_HREF = "org.modelio.documentation.welcome/html/Index.html";

    @objid ("e19a9138-eec0-43c8-8e4e-b5773ffb280f")
    private ApplicationTitleUpdater titleUpdater;

    @objid ("6a09416d-f314-490d-9f10-fa8564b3e3f6")
    private Splash splash;

    @objid ("58c41593-e154-46bc-9dd4-e02ded767fef")
    private CommandLineData cmdLineData;

    @objid ("b46266aa-9b70-4eca-bc39-03ed33589e0f")
    @PostContextCreate
    void postContextCreate(final IEclipseContext context) {
        AppUi.LOG.info("Modelio by Modeliosoft");
        // Get the command line arguments
        final IApplicationContext appContext = context.get(IApplicationContext.class);
        final String args[] = (String[]) appContext.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        final StringBuilder cmdline = new StringBuilder();
        for (final String arg : args) {
            cmdline.append(arg);
            cmdline.append(' ');
        }
        AppUi.LOG.info("Command line arguments = '%s'", cmdline);
        this.cmdLineData = new CommandLineData(args);
        
        // Depending on command line options, pop up a splash
        if (!this.cmdLineData.isBatch()) {
            // No batch mode, show the splash
            this.splash = new Splash();
            this.splash.open();
        }
        
        // -mdebug on command line forces log level to help development and
        // debugging
        if (this.cmdLineData.isDebug()) {
            PluginLogger.logLevel = LogService.LOG_DEBUG;
        }
        
        // Set up the ModelioEnv instance, this also create the modelio runtime
        // directories if needed
        final ModelioEnv modelioEnv = ContextInjectionFactory.make(ModelioEnv.class, context);
        context.set(ModelioEnv.class, modelioEnv);
        AppUi.LOG.info("Modelio version            : '%s'", modelioEnv.getVersion());
        AppUi.LOG.info("Modelio runtime data path  : '%s'", modelioEnv.getRuntimeDataPath());
        AppUi.LOG.info("Modelio module catalog path: '%s'", modelioEnv.getModuleCatalogPath());
        AppUi.LOG.info("Modelio macro  catalog path: '%s'", modelioEnv.getMacroCatalogPath());
        AppUi.LOG.info("Modelio update site        : '%s'", AppUpdateExt.getUpdateSite());
        
        // initialize SSL certificates manager
        final Path serverCertsDb = modelioEnv.getRuntimeDataPath().resolve("servercerts.db");
        try {
            SslManager.getInstance().setTrustStoreFile(modelioEnv.getRuntimeDataPath().resolve("servercerts.db"));
        } catch (final IOException e1) {
            AppUi.LOG.warning(e1);
            final String message = "Cannot read trusted server certificates from '" + serverCertsDb + "':\n"
                    + e1.getLocalizedMessage();
            context.get(StatusReporter.class).show(StatusReporter.ERROR, message, e1);
        }
        
        // EXPERIMENTAL STUFF ABOUT UPDATE
        // Check for updates
        // If an update is performed, restart. Otherwise log the status.
        // Get the update site location from the preferences
        // if (this.splash != null)
        // this.splash.showMessage(AppUi.I18N.getString("Splash.updates"));
        
        // final IEclipsePreferences prefs =
        // InstanceScope.INSTANCE.getNode(AppUpdate.PLUGIN_ID);
        // final IWorkbench workbench = context.get(IWorkbench.class);
        // final ModelioOnStartUpdater updater = new ModelioOnStartUpdater();
        // updater.execute(workbench, prefs, modelioEnv.getVersion());
        
        // END EXPERIMENTAL STUFF
        
        // Initialize catalog from module store if the local catalog directory does not contain any entry
        context.set(IModuleStore.class, initModulesCatalog(modelioEnv));
        
        if (this.splash != null) {
            this.splash.showMessage(AppUi.I18N.getString("Splash.services"));
        }
        
        // Create and Register the ModelioEventService instance
        final ModelioEventService modelioEventService = new ModelioEventService(context);
        context.set(IModelioEventService.class, modelioEventService);
        
        // Create and Register the ModelioProgressService instance
        // The service is registered both as a standard IProgressService and as
        // an extended IModelioProgressService
        final ModelioProgressService modelioProgressService = new ModelioProgressService();
        context.set(IModelioProgressService.class, modelioProgressService);
        context.set(IProgressService.class, modelioProgressService);
    }

    @objid ("ab606429-1892-408d-9739-071039297c1c")
    @ProcessAdditions
    void onProcessAdditions(final MApplication application) {
        final IEclipseContext context = application.getContext();
        
        this.titleUpdater = ContextInjectionFactory.make(ApplicationTitleUpdater.class, context);
        context.set(ApplicationTitleUpdater.class, this.titleUpdater);
        
        final PerspectiveManager pm = ContextInjectionFactory.make(PerspectiveManager.class, context);
        context.set(IModelioUiService.class, pm);
        
        // Perspectives initialization, force the Welcome part if first launch
        WelcomeView.setWelcomeHref(LifeCycleManager.WELCOME_HREF);
        final IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(AppUi.PLUGIN_ID);
        final boolean firstLaunch = prefs.getBoolean("FirstLaunch", true);
        if (firstLaunch) {
            pm.showWelcome(true);
            prefs.putBoolean("FirstLaunch", false);
            try {
                prefs.sync();
            } catch (final BackingStoreException e) {
                AppUi.LOG.warning(e);
            }
        } else {
            pm.switchToPerspective(null); // 'null' sets a default perspective
        }
        
        // Create and Register the picking service instance
        final IModelioPickingService pickingService = ContextInjectionFactory.make(ModelioPickingService.class, context);
        context.set(IModelioPickingService.class, pickingService);
        
        // Create and Register the navigate service instance
        final IModelioNavigationService navigateService = ContextInjectionFactory.make(ModelioNavigationService.class, context);
        context.set(IModelioNavigationService.class, navigateService);
        
        // Create and Register the activation service instance
        final IActivationService activationService = ContextInjectionFactory.make(ActivationService.class, context);
        context.set(IActivationService.class, activationService);
        
        // Create and Register the InputPart Service instance
        final IInputPartService inputPartService = ContextInjectionFactory.make(InputPartService.class, context);
        context.set(IInputPartService.class, inputPartService);
        
        // Swap monitoring:
        // - the title updater for a simplified swap activity report to the end
        // user
        // - a LOG report in debug mode
        AppUi.LOG.info("SWAP is %s", MemoryManager.get().isSwapEnabled() ? "ENABLED" : "DISABLED");
        if (MemoryManager.get().isSwapEnabled()) {
            MemoryManager.get().addMemoryListener(this.titleUpdater);
            if (AppUi.LOG.isDebugEnabled()) {
                MemoryManager.get().addMemoryListener(new SwapLogMonitor());
            }
        }
        
        // Configuring HELP
        /*
         * if (AppUi.LOG.isDebugEnabled()) { IToc[] tocs = org.eclipse.help.HelpSystem.getTocs(); for (IToc toc : tocs) {
         * AppUi.LOG.debug("TOC %s", toc.getLabel()); for (ITopic topic : toc.getTopics()) { AppUi.LOG.debug("  - %s (href=%s)",
         * topic.getLabel(), topic.getHref()); } } }
         */
        context.set(IWorkbenchHelpSystem.class, ModelioHelpSystem.getInstance());
        
        // Switch off the splash screen if needed.
        if (this.splash != null) {
            this.splash.close();
            this.splash = null;
        }
        
        // Batch mode
        // MANTAYORY: keep this code sequence the last one of the method
        final IEventBroker eventBroker = context.get(IEventBroker.class);
        if (eventBroker != null) {
            final EventHandler eventHandler = new EventHandler() {
        
                @SuppressWarnings ("synthetic-access")
                @Override
                public void handleEvent(Event arg0) {
                    // Unlock the workspace
                    final Location instanceLocation = (Location) context.get(E4Workbench.INSTANCE_LOCATION);
                    if (instanceLocation != null) {
                        instanceLocation.release();
                    }
        
                    // Run Batch
                    // MANTAYORY: keep this code sequence the last one of the method
                    eventBroker.post("BATCH", LifeCycleManager.this.cmdLineData);
                }
            };
        
            eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, eventHandler);
        }
    }

    @objid ("68684452-09b9-43cc-8187-12587debedd4")
    @ProcessRemovals
    void onProcessRemovals() {
        // Called after @ProcessAdditions but for removals.
        // final MApplication application
    }

    @objid ("4021a0d4-a0af-4d87-b82d-eac07a7f0ef6")
    @PreSave
    void onPreSave() {
        // Is called before the application model is saved. You can modify the
        // model before it is persisted.
    }

    @objid ("61c57b6e-4aac-477f-b313-244e7115779a")
    private void deliverMdaStoreModules(IModuleStore catalog) {
        final Location location = Platform.getInstallLocation();
        final Path moduleStore = new File(location.getURL().getFile()).toPath().resolve("modules");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(moduleStore, "*.jmdac")) {
            for (final Path jmdacPath : ds) {
                // Iterate over the paths in the directory and print
                // filenames
                AppUi.LOG.debug("installing module %s in catalog", jmdacPath.getFileName());
                catalog.installModuleArchive(jmdacPath, null);
            }
        } catch (final IOException e) {
            AppUi.LOG.debug(e);
        }
    }

    /**
     * Initialize catalog from module store if the local catalog directory
     * does not contain any entry
     * @param modelioEnv
     */
    @objid ("c0a5f790-80f7-43f3-b7cf-3954aa99e9ff")
    private IModuleStore initModulesCatalog(final ModelioEnv modelioEnv) {
        final IModuleStore catalog = new FileModuleStore(modelioEnv.getMetamodelExtensions(), modelioEnv.getModuleCatalogPath());
        
        boolean emptyCatalog;
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(modelioEnv.getModuleCatalogPath(),
                entry -> !entry.endsWith("version.dat"))) {
            emptyCatalog = !ds.iterator().hasNext();
        } catch (final IOException e) {
            AppUi.LOG.warning(e);
            emptyCatalog = false;
        }
        
        if (emptyCatalog) {
            if (this.splash != null) {
                this.splash.showMessage(AppUi.I18N.getString("Splash.modules"));
            }
            deliverMdaStoreModules(catalog);
        }
        
        PluginModulesCache pluginsStore = new PluginModulesCache(modelioEnv.getMetamodelExtensions());
        AggregatedModuleStore aggregatedStore = new AggregatedModuleStore(pluginsStore, catalog);
        return aggregatedStore;
    }

}
