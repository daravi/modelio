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

package org.modelio.diagram.editor.plugin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.tools.NodeCreationTool;
import org.modelio.diagram.editor.tools.PanSelectionTool;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeCreationTool;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidCreationTool;
import org.modelio.diagram.elements.core.commands.DrawingObjectFactory;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceLinkCreationFactory;
import org.modelio.diagram.elements.core.tools.BendedConnectionAndNodeCreationTool;
import org.modelio.diagram.elements.core.tools.BendedConnectionCreationTool;
import org.modelio.diagram.elements.core.tools.multipoint.MultiPointCreationTool;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.ellipse.GmEllipseDrawing;
import org.modelio.diagram.elements.drawings.line.GmLineDrawing;
import org.modelio.diagram.elements.drawings.rectangle.GmRectangleDrawing;
import org.modelio.diagram.elements.drawings.text.GmTextDrawing;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.gproject.gproject.GProject;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Parse and provide the palette toolbar of the diagram.
 * <p>
 * An instance is present in the E4 context and neesd E4 context injection.
 */
@objid ("667336ae-33f7-11e2-95fe-001ec947c8cc")
public class ToolRegistry {
    @objid ("c2f944bc-3896-11e2-95fe-001ec947c8cc")
    private static final String FACTORYEXTENSION_ID = "org.modelio.diagram.editor.toolregistry";

    /**
     * Tool that creates drawing ellipses .
     */
    @objid ("0061b99f-18c7-4c1b-9540-d1284e1edf4f")
    public static final String TOOL_CREATE_DRAWING_ELLIPSE = "CREATE_DRAWING_ELLIPSE";

    /**
     * Tool that creates drawing lines .
     */
    @objid ("1969d6e6-6962-4b26-9d16-3288712c2ff9")
    public static final String TOOL_CREATE_DRAWING_LINE = "CREATE_DRAWING_LINE";

    /**
     * Tool that creates drawing rectangles .
     */
    @objid ("f6c3b389-3dcc-49f9-9cd1-443a67d6736b")
    public static final String TOOL_CREATE_DRAWING_RECTANGLE = "CREATE_DRAWING_RECTANGLE";

    /**
     * Tool that creates drawing texts .
     */
    @objid ("9d70d667-9304-4c0e-9453-b4a0bc737bbc")
    public static final String TOOL_CREATE_DRAWING_TEXT = "CREATE_DRAWING_TEXT";

    /**
     * ID of the link creation tool that shows popup menu to choose the kind of link.
     */
    @objid ("f4460109-1040-4dd2-8a83-5b1bb057a0ec")
    public static final String TOOL_POPUPMENU_CREATELINK = "POPUPMENU_CREATELINK_TOOL";

    @objid ("b2a7baca-58ab-11e2-9574-002564c97630")
    @Inject
    @Optional
    private IMModelServices mModelServices;

    @objid ("a9bc92a8-0549-4fa7-9ec5-bf88abf88528")
    private Map<String, ToolEntry> toolRegistry;

    /**
     * Initialize the tool registry.
     */
    @objid ("6677fb5e-33f7-11e2-95fe-001ec947c8cc")
    public ToolRegistry() {
    }

    /**
     * Get the palette tool from the given id.
     * @param toolId An id.
     * @return the found palette tool or <i>null</i> if none found.
     */
    @objid ("6677fb58-33f7-11e2-95fe-001ec947c8cc")
    public ToolEntry getTool(String toolId) {
        // Load all the tools
        if (this.toolRegistry == null) {
            initRegistery();
        }
        
        final ToolEntry toolEntry = this.toolRegistry.get(toolId);
        
        // a tool should never be null...
        return toolEntry != null ? toolEntry : createDummyTool(toolId);
    }

    /**
     * Return a non modifiable view of all registered tools.
     * @return all registered tools.
     */
    @objid ("51c1b91b-f952-46ec-bbe7-579df85e1d67")
    public Collection<ToolEntry> getTools() {
        return Collections.unmodifiableCollection(this.toolRegistry.values());
    }

    /**
     * Register a new palette Tool.
     * @param toolId The new tool id.
     * @param tool The new tool.
     */
    @objid ("6677fb4f-33f7-11e2-95fe-001ec947c8cc")
    public void registerTool(String toolId, ToolEntry tool) {
        // Load all the tools
        if (this.toolRegistry == null) {
            initRegistery();
        }
        
        if (this.toolRegistry.containsKey(toolId)) {
            DiagramEditor.LOG.error("WARNING: redefining tool entry '%s'" + toolId);
        }
        this.toolRegistry.put(toolId, tool);
    }

    /**
     * Remove a tool from the palette.
     * @param toolId The id of the tool to remove.
     */
    @objid ("6677fb54-33f7-11e2-95fe-001ec947c8cc")
    public void unregisterTool(String toolId) {
        if (this.toolRegistry != null) {
            this.toolRegistry.remove(toolId);
        }
    }

    @objid ("5cc45a36-a84c-4168-85f1-27a60ebd0554")
    @Inject
    @Optional
    void onProjectClose(@SuppressWarnings ("unused") @EventTopic (ModelioEventTopics.PROJECT_CLOSING) final GProject project) {
        // Empty the tool registry
        this.toolRegistry = null;
    }

    @objid ("667f2268-33f7-11e2-95fe-001ec947c8cc")
    @Inject
    @Optional
    void onProjectOpen(IMModelServices modelServices) {
        this.mModelServices = modelServices;
    }

    @objid ("6677fb64-33f7-11e2-95fe-001ec947c8cc")
    protected void dump(IPath iPath) {
        PrintStream fout;
        try {
            fout = new PrintStream(new FileOutputStream(iPath.toFile()));
        
            fout.println("\nTool registry contents:");
            for (String k : this.toolRegistry.keySet()) {
                fout.println(" - " + k + " " + this.toolRegistry.get(k));
            }
            fout.println();
            fout.close();
        
        } catch (FileNotFoundException e) {
            DiagramEditor.LOG.error(e);
        }
    }

    @objid ("6677fb67-33f7-11e2-95fe-001ec947c8cc")
    protected void parseCreationTool(IConfigurationElement e) {
        String id = e.getAttribute("id");
        String label = e.getAttribute("label");
        String tooltip = e.getAttribute("tooltip");
        // String iconName = e.getAttribute("icon");
        String interactor = e.getAttribute("interactor");
        IConfigurationElement contextElement = e.getChildren("context")[0];
        IConfigurationElement[] propertyElements = contextElement.getChildren("property");
        ImageDescriptor icon = getIconDescriptor(e);
        
        Map<String, Object> properties = new HashMap<>();
        for (IConfigurationElement pe : propertyElements) {
            properties.put(pe.getAttribute("name"), pe.getAttribute("value"));
        }
        
        switch (InteractorKind.valueOf(interactor)) {
        case point:
            registerPointNodeCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case node:
            registerNodeCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case drawingNode:
            registerDrawingNodeCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case link:
            registerLinkCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case linkAndNode:
            registerLinkAndNodeCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case linkedNode:
            registerLinkedNodeCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case linkToVoid:
            registerLinkToVoidCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        case multipoint:
            registerMultiPointCreationTool(id, label, tooltip, icon, contextElement, properties);
            break;
        default:
            DiagramEditor.LOG.error("ToolRegistry: invalid interactor kind '" + interactor + "' for entry '" + id
                    + "'. Entry has been ignored.");
            break;
        }
    }

    @objid ("7e6bc821-cb11-4a46-8fa0-9f1195226be4")
    private void createDrawingTool(String toolName, Class<? extends Tool> toolClass, CreationFactory context) {
        String label = DiagramEditor.I18N.getString("$Tool." + toolName + ".label");
        String tooltip = DiagramEditor.I18N.getString("$Tool." + toolName + ".tooltip");
        String iconPath = DiagramEditor.I18N.getString("$Tool." + toolName + ".icon");
        ImageDescriptor icon;
        icon = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramEditor.PLUGIN_ID, iconPath);
        
        ToolEntry toolEntry = new CreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(toolClass);
        
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        registerTool(toolName, toolEntry);
    }

    @objid ("b5f2305c-c915-4cc8-91ff-9610883abe6f")
    private void createDrawingTools() {
        createDrawingTool(ToolRegistry.TOOL_CREATE_DRAWING_RECTANGLE, NodeCreationTool.class, new DrawingObjectFactory(GmRectangleDrawing.class));
        
        createDrawingTool(ToolRegistry.TOOL_CREATE_DRAWING_ELLIPSE, NodeCreationTool.class, new DrawingObjectFactory(GmEllipseDrawing.class));
        
        createDrawingTool(ToolRegistry.TOOL_CREATE_DRAWING_TEXT, NodeCreationTool.class, new DrawingObjectFactory(GmTextDrawing.class));
        
        // createDrawingTool("CREATE_DRAWING_POLYGON", MultiPointCreationTool.class, new
        // DrawingObjectFactory(GmEllipseDrawing.class));
        
        createDrawingTool(ToolRegistry.TOOL_CREATE_DRAWING_LINE, ConnectionCreationTool.class, new DrawingObjectFactory(GmLineDrawing.class));
    }

    @objid ("b2ac7d74-58ab-11e2-9574-002564c97630")
    private ToolEntry createDummyTool(String toolId) {
        SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
        selectionToolEntry.setToolClass(PanSelectionTool.class);
        selectionToolEntry.setLabel(toolId);
        selectionToolEntry.setDescription(toolId);
        return selectionToolEntry;
    }

    @objid ("8c22feb4-fffd-497c-b0e6-0c6af0d2084f")
    private void createPopupMenuLinkCreationTool() {
        String toolName = ToolRegistry.TOOL_POPUPMENU_CREATELINK;
        
        String label = DiagramEditor.I18N.getString("$Tool." + toolName + ".label");
        String tooltip = DiagramEditor.I18N.getString("$Tool." + toolName + ".tooltip");
        String iconPath = DiagramEditor.I18N.getString("$Tool." + toolName + ".icon");
        ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramEditor.PLUGIN_ID, iconPath);
        
        ToolEntry toolEntry = new ConnectionCreationToolEntry(label, tooltip, new UserChoiceLinkCreationFactory(), icon, icon);
        toolEntry.setToolClass(BendedConnectionAndNodeCreationTool.class);
        
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        registerTool(toolName, toolEntry);
    }

    @objid ("667cc00b-33f7-11e2-95fe-001ec947c8cc")
    private static ImageDescriptor getIconDescriptor(final IConfigurationElement element) {
        String extendingPluginId = element.getDeclaringExtension().getContributor().getName();
        String iconPath = element.getAttribute("icon");
        if (iconPath != null) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(extendingPluginId, iconPath);
        }
        return null;
    }

    @objid ("667cc000-33f7-11e2-95fe-001ec947c8cc")
    private static ImageDescriptor getStandardIcon(MClass metaclass, Stereotype stereotype) {
        if (stereotype != null) {
            final Profile owner = stereotype.getOwner();
            if (owner != null) {
                Image image = ModuleI18NService.getIcon(stereotype);
                if (image != null) {
                    return ImageDescriptor.createFromImage(image);
                }
            }
        }
        final Image icon = MetamodelImageService.getIcon(metaclass);
        if (icon != null) {
            return ImageDescriptor.createFromImage(icon);
        } else {
            return null;
        }
    }

    @objid ("667cc005-33f7-11e2-95fe-001ec947c8cc")
    private Stereotype getStereotype(MClass metaclass, String stereotypeName) {
        Stereotype stereotype = null;
        if (stereotypeName != null) {
            try {
                stereotype = this.mModelServices.getStereotype(".*", stereotypeName, metaclass);
            } catch (ElementNotUniqueException e1) {
                DiagramEditor.LOG.error(e1);
            }
        }
        return stereotype;
    }

    /**
     * Fills the tool registery from the platform extensions.
     */
    @objid ("6677fb61-33f7-11e2-95fe-001ec947c8cc")
    private void initRegistery() {
        this.toolRegistry = new HashMap<>();
        
        IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(ToolRegistry.FACTORYEXTENSION_ID);
        for (IConfigurationElement e : config) {
            if (e.getName().equals("creationtool")) {
                parseCreationTool(e);
            }
        
        }
        
        createDrawingTools();
        createPopupMenuLinkCreationTool();
        
        dump(Platform.getLogFileLocation());
    }

    /**
     * Read the 'dependency' attribute of the configuration element and decode it to a MDependency.
     * <p>
     * The specification is expected to have the format "maybe.qualifed.metaclass.dependencyName".
     * <p>
     * Bad specifications are reported to log.
     * @param id the tool id
     * @param contextElement the tool configuration
     * @param metamodel the metamodel
     * @return the found dependency or null.
     * @since 3.4.1
     */
    @objid ("4b2a166b-8d53-4135-878d-f6390ceeff8e")
    private MDependency readDependencySpec(String id, IConfigurationElement contextElement, MMetamodel metamodel) {
        String dependencySpec = contextElement.getAttribute("dependency");
        
        MDependency dep = null;
        if (dependencySpec != null && !dependencySpec.isEmpty() && !dependencySpec.equals("null")) {
            int dotIdx = dependencySpec.lastIndexOf(".");
            if (dotIdx > 0) {
                String srcClassName = dependencySpec.substring(0, dotIdx);
                MClass srcMClass = metamodel.getMClass(srcClassName);
                if (srcMClass != null) {
                    String depName = dependencySpec.substring(dotIdx + 1);
                    dep = srcMClass.getDependency(depName);
                    if (dep == null) {
                        DiagramEditor.LOG.warning("ToolRegistry: '%s' tool: '%s' dependency specification invalid : the '%s' dependency does not exist on '%s' metaclass. (see %s)", id, dependencySpec, srcMClass.getQualifiedName(),
                                contextElement.getContributor().getName());
                    }
                } else {
                    DiagramEditor.LOG.warning("ToolRegistry: '%s' tool: '%s' dependency specification invalid : the '%s' metaclass does not exist. (see %s)", id, dependencySpec, srcClassName, contextElement.getContributor().getName());
                }
            } else {
                // no metaclass in spec : obsolete form.
                // Use the target metaclass (argh !) old way to look for the dependency.
                String metaclassName = contextElement.getAttribute("metaclass");
                MClass srcMClass = metamodel.getMClass(metaclassName);
                dep = srcMClass.getDependency(dependencySpec);
                if (dep == null) {
                    DiagramEditor.LOG.warning("ToolRegistry: '%s' tool ERROR: '%s' dependency does not exist on '%s' metaclass. (from %s)", id, dependencySpec, metaclassName, contextElement.getContributor().getName());
                } else {
                    DiagramEditor.LOG.debug("ToolRegistry: %s tool uses deprecated dependency declaration (from %s)", id, contextElement.getContributor().getName());
                }
            }
        }
        return dep;
    }

    @objid ("79392a8c-936b-4950-b588-cad5e42db6e9")
    @SuppressWarnings ("unchecked")
    private void registerDrawingNodeCreationTool(String id, String label, String tooltip, ImageDescriptor anIcon, IConfigurationElement contextElement, Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String className = contextElement.getAttribute("metaclass");
        
        Class<? extends GmDrawing> nodeClass = null;
        try {
            nodeClass = (Class<? extends GmDrawing>) contextElement.createExecutableExtension("metaclass").getClass();
        } catch (CoreException e) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + className + "' for entry '" + id
                    + "'. Entry has been ignored:");
            DiagramEditor.LOG.error(e);
            return;
        }
        if (nodeClass == null || !GmDrawing.class.isAssignableFrom(nodeClass)) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + className + "' for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        // configure modelio creation context
        DrawingObjectFactory context = new DrawingObjectFactory(nodeClass);
        context.setProperties(properties);
        
        // create and register tool entry
        ToolEntry toolEntry = new CreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(NodeCreationTool.class);
        
        // Keep the tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("667a5dac-33f7-11e2-95fe-001ec947c8cc")
    private void registerLinkCreationTool(String id, String label, String tooltip, ImageDescriptor anIcon, IConfigurationElement contextElement, Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        String routerName = contextElement.getAttribute("router");
        
        // Check for existing metaclass
        MClass metaclass = this.mModelServices.getMetamodel().getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + metaclassName + "' for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        Stereotype stereotype = null;
        stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // Configure modelio creation context
        final ModelioLinkCreationContext context = new ModelioLinkCreationContext(metaclass, stereotype);
        context.setProperties(properties);
        
        if (routerName != null && !routerName.isEmpty()) {
            final StyleKey routerStyleKey = StyleKey.getInstance(routerName);
            context.setRoutingModeKey(routerStyleKey);
            properties.put("router", routerStyleKey);
        }
        
        // Create and register tool entry
        ToolEntry toolEntry = new ConnectionCreationToolEntry(label, tooltip, context, icon, icon);
        
        toolEntry.setToolClass(BendedConnectionCreationTool.class);
        
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("667cc011-33f7-11e2-95fe-001ec947c8cc")
    private void registerLinkToVoidCreationTool(final String id, final String label, final String tooltip, final ImageDescriptor anIcon, final IConfigurationElement contextElement, final Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        String routerName = contextElement.getAttribute("router");
        
        MMetamodel metamodel = this.mModelServices.getMetamodel();
        MClass metaclass = metamodel.getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + metaclassName + "' for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        MDependency dep = readDependencySpec(id, contextElement, metamodel);
        
        Stereotype stereotype = null;
        stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // configure modelio creation context
        ModelioCreationContext context = new ModelioCreationContext(metaclass, dep, stereotype);
        context.setProperties(properties);
        
        if (routerName != null && !routerName.isEmpty()) {
            final StyleKey routerStyleKey = StyleKey.getInstance(routerName);
            properties.put("router", routerStyleKey);
        }
        
        // create and register tool entry
        ToolEntry toolEntry = new ConnectionCreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(LinkToVoidCreationTool.class);
        
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("667a5db7-33f7-11e2-95fe-001ec947c8cc")
    private void registerLinkedNodeCreationTool(String id, String label, String tooltip, ImageDescriptor anIcon, IConfigurationElement contextElement, Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        String routerName = contextElement.getAttribute("router");
        
        MMetamodel metamodel = this.mModelServices.getMetamodel();
        MClass metaclass = metamodel.getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + metaclassName + "' for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        MDependency dep = readDependencySpec(id, contextElement, metamodel);
        
        Stereotype stereotype = null;
        stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // configure modelio creation context
        ModelioCreationContext context = new ModelioCreationContext(metaclass, dep, stereotype);
        context.setProperties(properties);
        
        if (routerName != null && !routerName.isEmpty()) {
            final StyleKey routerStyleKey = StyleKey.getInstance(routerName);
            properties.put("router", routerStyleKey);
        }
        
        // create and register tool entry
        ToolEntry toolEntry = new ConnectionCreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(LinkedNodeCreationTool.class);
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("667cc022-33f7-11e2-95fe-001ec947c8cc")
    private void registerMultiPointCreationTool(final String id, final String label, final String tooltip, final ImageDescriptor anIcon, final IConfigurationElement contextElement, final Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        String routerName = contextElement.getAttribute("router");
        
        MClass metaclass = this.mModelServices.getMetamodel().getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + metaclassName + "' for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        Stereotype stereotype = null;
        stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // configure modelio creation context
        ModelioLinkCreationContext context = new ModelioLinkCreationContext(metaclass, stereotype);
        context.setProperties(properties);
        
        if (routerName != null) {
            context.setRoutingModeKey(StyleKey.getInstance(routerName));
        }
        
        // Create and register tool entry
        ToolEntry toolEntry = new CreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(MultiPointCreationTool.class);
        
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("6677fb6a-33f7-11e2-95fe-001ec947c8cc")
    private void registerNodeCreationTool(String id, String label, String tooltip, ImageDescriptor anIcon, IConfigurationElement contextElement, Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        
        MMetamodel metamodel = this.mModelServices.getMetamodel();
        MClass metaclass = metamodel.getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '%s' for entry '%s'. Entry has been ignored.", metaclassName, id);
            return;
        }
        
        MDependency dep = readDependencySpec(id, contextElement, metamodel);
        
        Stereotype stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // configure modelio creation context
        ModelioCreationContext context = new ModelioCreationContext(metaclass, dep, stereotype);
        context.setProperties(properties);
        
        // create and register tool entry
        ToolEntry toolEntry = new CreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(NodeCreationTool.class);
        
        // Keep the tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("667a5dc2-33f7-11e2-95fe-001ec947c8cc")
    private void registerPointNodeCreationTool(String id, String label, String tooltip, ImageDescriptor anIcon, IConfigurationElement contextElement, Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        
        MMetamodel metamodel = this.mModelServices.getMetamodel();
        MClass metaclass = metamodel.getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid '" + metaclassName + "' metaclass for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        MDependency dep = readDependencySpec(id, contextElement, metamodel);
        
        Stereotype stereotype = null;
        stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // configure modelio creation context
        ModelioCreationContext context = new ModelioCreationContext(metaclass, dep, stereotype);
        context.setProperties(properties);
        
        // create and register tool entry
        ToolEntry toolEntry = new CreationToolEntry(label, tooltip, context, icon, icon);
        toolEntry.setToolClass(PointCreationTool.class);
        
        // Keep the tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    @objid ("73716411-7fcf-429e-84e4-607e52b6742a")
    private void registerLinkAndNodeCreationTool(String id, String label, String tooltip, ImageDescriptor anIcon, IConfigurationElement contextElement, Map<String, Object> properties) {
        ImageDescriptor icon = anIcon;
        String metaclassName = contextElement.getAttribute("metaclass");
        String stereotypeName = contextElement.getAttribute("stereotype");
        String routerName = contextElement.getAttribute("router");
        
        // Check for existing metaclass
        MClass metaclass = this.mModelServices.getMetamodel().getMClass(metaclassName);
        if (metaclass == null) {
            DiagramEditor.LOG.error("ToolRegistry: invalid metaclass '" + metaclassName + "' for entry '" + id
                    + "'. Entry has been ignored.");
            return;
        }
        
        Stereotype stereotype = null;
        stereotype = getStereotype(metaclass, stereotypeName);
        
        if (icon == null) {
            icon = getStandardIcon(metaclass, stereotype);
        }
        
        // Configure modelio creation context
        final ModelioLinkCreationContext context = new ModelioLinkCreationContext(metaclass, stereotype);
        context.setProperties(properties);
        
        if (routerName != null && !routerName.isEmpty()) {
            final StyleKey routerStyleKey = StyleKey.getInstance(routerName);
            context.setRoutingModeKey(routerStyleKey);
            properties.put("router", routerStyleKey);
        }
        
        // Create and register tool entry
        ToolEntry toolEntry = new ConnectionCreationToolEntry(label, tooltip, context, icon, icon);
        
        toolEntry.setToolClass(BendedConnectionAndNodeCreationTool.class);
        
        // Return to default selection tool after finished
        toolEntry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.TRUE);
        
        registerTool(id, toolEntry);
    }

    /**
     * Lists the type of handled interaction.
     */
    @objid ("667cc033-33f7-11e2-95fe-001ec947c8cc")
    enum InteractorKind {
        /**
         * box by simple click
         */
        point,
        /**
         * box by click + drag a rectangle
         */
        node,
        /**
         * box by click + drag a rectangle to create a drawing node
         */
        drawingNode,
        /**
         * simple link
         */
        link,
        /**
         * link + optional node creation
         */
        linkAndNode,
        /**
         * Combination of a box with a link (e.g.: Note)
         */
        linkedNode,
        /**
         * simple link
         */
        linkToVoid,
        /**
         * multiple selection interaction (e.g.: constraints, n-ary associations).
         */
        multipoint,
        /**
         * Specific for links in Sequence Diagrams.
         */
        sequenceLink;
    }

}
