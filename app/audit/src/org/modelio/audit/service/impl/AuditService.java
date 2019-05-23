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

package org.modelio.audit.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.audit.engine.AuditEngine;
import org.modelio.audit.engine.CheckElementRunner;
import org.modelio.audit.engine.core.IAuditExecutionPlan;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.AuditModelController;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.service.IAuditService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Audit service implementation.
 */
@objid ("7ddbb117-45fb-11e2-9b4d-bc305ba4815c")
@Creatable
public class AuditService implements IAuditService {
    @objid ("7ddbb11a-45fb-11e2-9b4d-bc305ba4815c")
    private AuditEngine auditEngine;

    @objid ("7ddbb11b-45fb-11e2-9b4d-bc305ba4815c")
    private GProject openedProject;

    @objid ("4fd2d0ed-357f-45c7-b763-e7d91f4f80e5")
    private AuditModelController modelController;

    @objid ("599c07f0-15cf-421f-9d46-13e12822a4b3")
    private Geometry geometry;

    @objid ("0fc6094e-ecc2-47bd-9612-45a9520f1e0b")
    @Inject
    private StatusReporter errReporter;

    @objid ("b6a751a4-21ce-40e3-9008-7f0cc6c56b36")
    private Map<String, Thread> threadMap;

    @objid ("7ddbb11c-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public void setConfigurationFile(File confFile) {
        this.geometry.setConfigurationFile(confFile);
        
        restart();
    }

    @objid ("7ddbb120-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public void restart() {
        try {
            loadModel();
        
            // Generate the plan
            IAuditExecutionPlan auditPlan = getProjectConfigurator().createPlan();
        
            // Change the plan
            this.auditEngine.setPlan(auditPlan);
        } catch (FileSystemException e) {
            this.errReporter.show(StatusReporter.ERROR, FileUtils.getLocalizedMessage(e), e);
        } catch (IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
    }

    @objid ("7ddbb123-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public void checkElement(MObject element, String jobId) {
        this.auditEngine.checkElement(element, jobId);
    }

    /**
     * Return File descriptor of the local configuration file for the current project.
     * <p>
     * An opened modeling session is assumed to be available.
     * @return File : Configuration file
     */
    @objid ("7ddbb127-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public File getConfigurationFile() {
        return this.geometry.getConfigurationFile();
    }

    /**
     * This method is called after a project opening. Keep a reference to the modeling session and model services.
     */
    @objid ("7ddbb12d-45fb-11e2-9b4d-bc305ba4815c")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENING) final GProject project) {
        if (project != null) {
            this.openedProject = project;
            this.geometry.initForProject(project);
        
            try {
                // Ensure the configuration file exists.
                // Do migration of needed
                ensureConfigurationFile();
        
                // Load the model
                loadModel();
        
                // Generate the plan
                IAuditExecutionPlan auditPlan = getProjectConfigurator().createPlan();
        
                // Init the audit engine, set the plan and start
                this.auditEngine.setPlan(auditPlan);
                this.auditEngine.start(this.openedProject.getSession());
        
            } catch (FileSystemException e) {
                this.errReporter.show(StatusReporter.ERROR, FileUtils.getLocalizedMessage(e), e);
            } catch (IOException e) {
                Audit.LOG.error(e);
                this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
            }
        }
    }

    /**
     * Called when a project is closed. On session close un-reference the modeling session and model services.
     */
    @objid ("7dde127a-45fb-11e2-9b4d-bc305ba4815c")
    @Optional
    @Inject
    void onProjectClosing(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSING) final GProject closedProject) {
        if (closedProject != null) {
            // Standard audit
            this.auditEngine.stop(closedProject.getSession());
            this.openedProject = null;
        }
    }

    @objid ("7dde1283-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public AuditEngine getAuditEngine() {
        return this.auditEngine;
    }

    /**
     * constructor to be called by E4 engine.
     */
    @objid ("b4c3aaa7-f8a1-41cc-9c0b-d8cf049f14c5")
    public AuditService() {
        this.auditEngine = new AuditEngine();
        this.modelController = new AuditModelController();
        this.geometry = new Geometry();
        this.threadMap = new HashMap<>();
    }

    @objid ("36b7f133-5e97-483e-aeb0-9965120a1037")
    @Override
    public AuditConfigurationModel getConfigurationModel() {
        return new AuditConfigurationModel(getProjectConfigurator().getModel());
    }

    @objid ("4b43952c-f80d-4afe-90d0-a91d9ab991b7")
    @Override
    public void apply(AuditConfigurationModel auditConfiguration) {
        // Create an audit configurator
        this.modelController.applyAuditConfiguration(auditConfiguration);
        
        try {
            saveConfiguration(getConfigurationFile());
            restart();
        } catch (IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
    }

    @objid ("53eb65a1-1cbe-4b86-8f51-368644e4783a")
    private AuditModelController getProjectConfigurator() {
        return this.modelController;
    }

    @objid ("c8161b2b-1836-4e48-a89b-e2a942be8a69")
    @Override
    public AuditConfigurationModel getFactorySettings() {
        AuditModelController ret = new AuditModelController();
        try {
            ret.applyAuditConfiguration(this.geometry.getDefaultProjectConfigurationFile());
        } catch (IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
        return ret.getModel();
    }

    @objid ("cb7aaa3a-8f81-4781-a48c-55f70ef1e696")
    private void saveConfiguration(File file) throws IOException {
        this.modelController.writeConfiguration(file);
    }

    /**
     * Creates a new model controller matching the audit service current configuration.
     * @return a new model controller.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("76e8a01d-9912-44d3-b1d6-5376f260cc8b")
    private AuditModelController createModelController() throws IOException {
        AuditModelController ret = new AuditModelController();
        File defaultConf = this.geometry.getDefaultProjectConfigurationFile();
        if (defaultConf != null) {
            ret.addDefaultConf(defaultConf);
        }
        
        final File confFile = getConfigurationFile();
        if (confFile.isFile()) {
            ret.applyAuditConfiguration(confFile);
        }
        return ret;
    }

    @objid ("aaa47858-96c4-4adc-a211-69cf9b3b6ffa")
    private void loadModel() throws IOException {
        this.modelController = createModelController();
    }

    /**
     * Get the edited project.
     * @return the edited project.
     */
    @objid ("1a82221b-5501-4a65-89df-42671396dbbe")
    GProject getProject() {
        assert (this.openedProject != null);
        return this.openedProject;
    }

    @objid ("a146c2ab-3d69-4d46-9638-821cac96ecf4")
    @Override
    public void checkElementTree(List<MObject> selection, String jobId) {
        CheckElementRunner checker = new CheckElementRunner(this, selection, jobId);
        Thread checkerThread = new Thread(checker);
        checkerThread.setPriority(Thread.MIN_PRIORITY);
        checkerThread.setName("CHECKER");
        checkerThread.start();
        
        this.threadMap.put(jobId, checkerThread);
    }

    @objid ("4685e5e3-6ef7-477f-bb73-16e3caccf244")
    @Override
    public void addAuditMonitor(IAuditMonitor monitor) {
        this.auditEngine.addAuditMonitor(monitor);
    }

    @objid ("40c2eea9-b031-4219-96f9-ffb3567c938f")
    @Override
    public void removeAuditMonitor(IAuditMonitor monitor) {
        this.auditEngine.addAuditMonitor(monitor);
    }

    @objid ("4b695881-32ea-4ce1-b7ef-fd4ebe5b0272")
    @Override
    public void interuptCheck(String jobId) {
        Thread checkerThread = this.threadMap.get(jobId);
        if (checkerThread != null) {
            checkerThread.interrupt();
        }
    }

    /**
     * Ensure the configuration file exists.
     * @throws java.io.IOException in case of I/O error making the project configuration file.
     */
    @objid ("703edf07-33d2-40cf-ae9a-9a2d5f447f3d")
    private void ensureConfigurationFile() throws IOException {
        File curDefault = this.geometry.getDefaultProjectConfigurationFile();
        if (curDefault == null || !curDefault.isFile()) {
            // If no conf exists, write one from the current plan
            AuditModelController newconf = new AuditModelController();
            newconf.applyAuditConfiguration(getConfigurationModel());
            newconf.writeConfiguration(this.geometry.defaultProjectConfigurationFile);
        }
    }

    /**
     * Current configuration files geometry.
     * <p>
     * Gives the configuration files path.
     */
    @objid ("47824921-74a7-4ae6-b1af-7b20c48425eb")
    private static class Geometry {
        /**
         * The currently used configuration file. Might be <code>null</code>.
         */
        @objid ("0e84e471-42a6-4de7-b355-7528929922bf")
        private File configurationFile;

        /**
         * The default configuration file stored in the opened project.
         */
        @objid ("73c99eed-cda8-483b-9c06-1da9b359b534")
        private File defaultProjectConfigurationFile;

        @objid ("0702a158-b73f-4865-a585-d47019cbaff3")
        public Geometry() {
            // nothing
        }

        /**
         * Change the currently used audit configuration file.
         * @param confFile an audit configuration file. Set to <code>null</code> to make the default configuration file used.
         */
        @objid ("ccb78196-44cb-4932-b7b4-fab715a79a85")
        public void setConfigurationFile(File confFile) {
            this.configurationFile = confFile;
        }

        /**
         * Find the first configuration file available in the following ordered list:
         * <ul>
         * <li>The set configuration file
         * <li>The default project configuration file
         * <li>The set factory settings file
         * <li>The default factory settings file.
         * @return the configuration file to read.
         */
        @objid ("20c85e9a-c398-427c-aead-6d63572434f1")
        public File findFirstConfigurationFile() {
            final File[] ff = new File[] { getConfigurationFile(), getDefaultProjectConfigurationFile() };
            for (File f : ff) {
                if (f != null && f.isFile()) {
                    return f;
                }
            }
            return null;
        }

        /**
         * Return File descriptor of the currently used audit configuration file.
         * <p>
         * When no specific configuration file is defined, defaults to {@link #defaultProjectConfigurationFile}.
         * </p>
         * @return an audit configuration file.
         */
        @objid ("9a5449ce-008e-4055-aafa-bd2db6416caf")
        public File getConfigurationFile() {
            if (this.configurationFile != null) {
                return this.configurationFile;
            }
            return this.defaultProjectConfigurationFile;
        }

        /**
         * Return File descriptor of the currently used default configuration file.
         * <p>
         * When no specific default configuration file is defined, returns <code>null</code>.
         * </p>
         * @return an audit configuration file. Might be <code>null</code>.
         */
        @objid ("1a4dd68f-a0e7-4c81-914e-bf3bdcf12be0")
        public File getDefaultProjectConfigurationFile() {
            if (this.defaultProjectConfigurationFile != null && this.defaultProjectConfigurationFile.isFile()) {
                return this.defaultProjectConfigurationFile;
            }
            return null;
        }

        @objid ("683e932c-a337-404e-8170-f82f2b0a955d")
        public File initForProject(GProject project) {
            return this.defaultProjectConfigurationFile = project.getProjectDataPath().resolve(GProject.DATA_SUBDIR).resolve(".config/auditconfiguration.properties").toFile();
        }

    }

}
