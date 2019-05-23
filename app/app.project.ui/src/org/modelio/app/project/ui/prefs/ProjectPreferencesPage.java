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

package org.modelio.app.project.ui.prefs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.modelio.app.project.core.prefs.ProjectPreferencesKeys;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("d4b36ec0-b27b-49d5-8874-a85f79a8c02a")
class ProjectPreferencesPage extends PreferencePage {
    @objid ("c3bbf9f1-9d56-472a-9acd-a089e2e47ec5")
    private static final String INDENT = "    ";

    @objid ("ff9bd012-1971-451e-abef-397d3d01f381")
    private static final String PROJECT_STYLE_SUBDIR = "data/.config/styles";

    @objid ("a9fe1297-596c-4dea-b37a-80657e88e804")
    private static final String THEME_FILE_EXTENSION = ".theme";

    @objid ("6c131494-d083-4a51-8871-90baf6d5dd4a")
    private String[][] diagramThemeValues;

    @objid ("fcc0b24b-abda-4840-9238-4c500a5f89ed")
    private String[][] noteMimeTypeValues;

    @objid ("82c53c80-64fb-4ad4-8001-685649f8b32c")
    private String[][] predefinedTypesValues;

    @objid ("a18e6adf-c5a4-4f61-9e97-6adf50ad92da")
    private String[][] richNoteTypeValues;

    @objid ("a96c621e-99b0-4892-879b-b2bcb0e17b42")
    private String[][] visibilityValues;

    @objid ("b5617bf4-d474-4d94-be5b-175aa4ad97f9")
    private ComboFieldEditor attDefaultType;

    @objid ("75842ba5-32ae-4c35-97a4-99a7b7c34223")
    private ComboFieldEditor attdefaultVisibility;

    @objid ("a11eed16-2a57-44f3-9e00-e9c71f5c5b95")
    private ComboFieldEditor diagramDefaultTheme;

    @objid ("511b8ac2-436c-4d4d-9206-11aabbf06503")
    private ComboFieldEditor noteDefaultMimeType;

    @objid ("2f7d0606-93ef-43c4-acca-77c1b293dfa5")
    private ComboFieldEditor parameterDefaultType;

    @objid ("a4cc62c4-2fa2-4bad-a1c7-9274d937c793")
    private ComboFieldEditor returnDefaultType;

    @objid ("7f0b870c-5fbf-47cf-b26d-6e8c2287d94f")
    private ComboFieldEditor richNoteDefaultType;

    @objid ("4732c860-11df-4aff-812b-e8574636748c")
    @Inject
    public ProjectPreferencesPage(IProjectService projectService) {
        super();
        init(projectService);
    }

    @objid ("9681f02d-e716-4e99-9b89-4acbf4197d7c")
    public void createAttributeZone(Composite parent) {
        // Note: The INDENT prefix is a trick to indent the field editor because
        // FieldEditors do not allow fine tuning of their grid data :(
        // Should better get rid of them and either implement a plain layout of
        // standard SWT control or use a table
        
        // attribute default type
        this.attDefaultType = new ComboFieldEditor(ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY + ".label"), this.predefinedTypesValues,
                parent);
        this.attDefaultType.setPage(this);
        this.attDefaultType.setPreferenceStore(getPreferenceStore());
        this.attDefaultType.load();
        
        // attribute default visibility
        this.attdefaultVisibility = new ComboFieldEditor(ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY + ".label"), this.visibilityValues,
                parent);
        this.attdefaultVisibility.setPage(this);
        this.attdefaultVisibility.setPreferenceStore(getPreferenceStore());
        this.attdefaultVisibility.load();
    }

    @objid ("fb72b346-e347-43de-af9c-62eb55711807")
    @Override
    public Control createContents(Composite parent) {
        Composite top = new Composite(parent, SWT.NONE);
        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        top.setLayout(layout);
        
        if (getPreferenceStore() != null) {
            createTitleZone(top, AppProjectUi.I18N.getString("ProjectPrefs.Diagrams"));
            createDiagramZone(top);
        
            createTitleZone(top, AppProjectUi.I18N.getString("ProjectPrefs.Attributes"));
            createAttributeZone(top);
        
            createTitleZone(top, AppProjectUi.I18N.getString("ProjectPrefs.Operations"));
            createOperationZone(top);
        
            createTitleZone(top, AppProjectUi.I18N.getString("ProjectPrefs.RichNotes"));
            createRichNoteZone(top);
        
            createTitleZone(top, AppProjectUi.I18N.getString("ProjectPrefs.Notes"));
            createNoteZone(top);
        }
        return top;
    }

    @objid ("bd209035-2fc9-4249-8047-71fa822cea5a")
    public void createDiagramZone(Composite parent) {
        // Note: The INDENT prefix is a trick to indent the field editor because
        // FieldEditors do not allow fine tuning of their grid data :(
        // Should better get rid of them and either implement a plain layout of
        // standard SWT control or use a table
        
        // attribute default type
        this.diagramDefaultTheme = new ComboFieldEditor(ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY + ".label"),
                this.diagramThemeValues, parent);
        this.diagramDefaultTheme.setPage(this);
        this.diagramDefaultTheme.setPreferenceStore(getPreferenceStore());
        this.diagramDefaultTheme.load();
    }

    @objid ("882e45e4-6df7-41a8-8330-e7e94e3657a9")
    public void createNoteZone(Composite parent) {
        this.noteDefaultMimeType = new ComboFieldEditor(ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY + ".label"),
                this.noteMimeTypeValues, parent);
        this.noteDefaultMimeType.setPage(this);
        this.noteDefaultMimeType.setPreferenceStore(getPreferenceStore());
        this.noteDefaultMimeType.load();
    }

    @objid ("b534facc-1df3-48ed-a946-049d733b4ca2")
    public void createOperationZone(Composite parent) {
        // parameter default type
        
        this.parameterDefaultType = new ComboFieldEditor(ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY + ".label"),
                this.predefinedTypesValues, parent);
        
        this.parameterDefaultType.setPage(this);
        this.parameterDefaultType.setPreferenceStore(getPreferenceStore());
        this.parameterDefaultType.load();
        
        // return parameter default type
        this.returnDefaultType = new ComboFieldEditor(ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY + ".label"),
                this.predefinedTypesValues, parent);
        
        this.returnDefaultType.setPage(this);
        this.returnDefaultType.setPreferenceStore(getPreferenceStore());
        this.returnDefaultType.load();
    }

    @objid ("09ec982c-cd65-4855-9d6a-79a60be79484")
    public void createRichNoteZone(Composite parent) {
        // Note: The INDENT prefix is a trick to indent the field editor because
        // FieldEditors do not allow fine tuning of their grid data :(
        // Should better get rid of them and either implement a plain layout of
        // standard SWT control or use a table
        
        // attribute default type
        this.richNoteDefaultType = new ComboFieldEditor(ProjectPreferencesKeys.RICHNOTE_DEFAULT_TYPE_PREFKEY, ProjectPreferencesPage.INDENT + AppProjectUi.I18N.getString("$" + ProjectPreferencesKeys.RICHNOTE_DEFAULT_TYPE_PREFKEY + ".label"),
                this.richNoteTypeValues, parent);
        this.richNoteDefaultType.setPage(this);
        this.richNoteDefaultType.setPreferenceStore(getPreferenceStore());
        this.richNoteDefaultType.load();
    }

    @objid ("93d67092-2de9-417e-9336-b30062165ded")
    public void createSpacer(Composite parent) {
        Label spacer = new Label(parent, SWT.NONE);
        GridDataFactory.defaultsFor(spacer).span(2, 1).applyTo(spacer);
    }

    @objid ("398fed0c-8b5d-4429-a222-7da0bf4591a6")
    public void createTitleZone(Composite parent, String title) {
        Label titleLabel = new Label(parent, SWT.NONE);
        titleLabel.setText(title);
        GridDataFactory.defaultsFor(titleLabel).span(2, 1).applyTo(titleLabel);
        
        Label sep = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridDataFactory.defaultsFor(sep).span(2, 1).applyTo(sep);
    }

    @objid ("4fa6dc25-455e-484b-bdba-64adf851d816")
    @Override
    public boolean performOk() {
        final boolean ret = super.performOk();
        
        // Protect against NPE, the fields might be null if the page wasn't shown
        if (this.attDefaultType != null) {
            this.attDefaultType.store();
        }
        
        if (this.attdefaultVisibility != null) {
            this.attdefaultVisibility.store();
        }
        
        if (this.parameterDefaultType != null) {
            this.parameterDefaultType.store();
        }
        
        if (this.returnDefaultType != null) {
            this.returnDefaultType.store();
        }
        
        if (this.richNoteDefaultType != null) {
            this.richNoteDefaultType.store();
        }
        
        if (this.noteDefaultMimeType != null) {
            this.noteDefaultMimeType.store();
        }
        
        if (this.diagramDefaultTheme != null) {
            this.diagramDefaultTheme.store();
        }
        return ret;
    }

    @objid ("c06802dc-7641-48ef-886b-38ddf1020188")
    @Override
    protected void performDefaults() {
        super.performDefaults();
        if (this.attDefaultType != null) {
            this.attDefaultType.loadDefault();
        }
        if (this.attdefaultVisibility != null) {
            this.attdefaultVisibility.loadDefault();
        }
        
        if (this.parameterDefaultType != null) {
            this.parameterDefaultType.loadDefault();
        }
        
        if (this.returnDefaultType != null) {
            this.returnDefaultType.loadDefault();
        }
        
        if (this.richNoteDefaultType != null) {
            this.richNoteDefaultType.loadDefault();
        }
        
        if (this.noteDefaultMimeType != null) {
            this.noteDefaultMimeType.loadDefault();
        }
        
        if (this.diagramDefaultTheme != null) {
            this.diagramDefaultTheme.loadDefault();
        }
    }

    @objid ("8e659095-8d5f-4d20-90de-4d8c56eed2c4")
    @Override
    protected void setControl(Control newControl) {
        super.setControl(newControl);
        
        if (getPreferenceStore() == null) {
            // no currently opened project => no store, page not visible
            this.setVisible(false);
        }
    }

    @objid ("d98d8707-e819-43ce-8348-ef450f72250c")
    private List<DataType> getPredefinedTypes(GProject openedProject) {
        IModel model = openedProject.getSession().getModel();
        DataType booleanDataType = model.findById(DataType.class, PredefinedTypes.BOOLEAN_UID);
        if (booleanDataType != null) {
            return booleanDataType.getOwner().getOwnedElement(DataType.class);
        } else {
            AppProjectUi.LOG.warning("no predefined types found");
            return new ArrayList<>();
        }
    }

    @objid ("1e1fcc4d-d6a8-4915-b0d8-add063bf705d")
    private void init(IProjectService projectService) {
        // use project store if a project is currently opened
        GProject openedProject = projectService.getOpenedProject();
        if (openedProject == null) {
            // no currently opened project => no store, page not visible
            setPreferenceStore(null);
            if (isControlCreated()) {
                this.setVisible(false);
            }
            noDefaultAndApplyButton();
            return;
        }
        
        // branch the preference store
        IPreferenceStore preferenceStore = projectService.getProjectPreferences(ProjectPreferencesKeys.NODE_ID);
        setPreferenceStore(preferenceStore);
        
        // compute future combo values
        List<DataType> predefinedTypes = getPredefinedTypes(openedProject);
        this.predefinedTypesValues = new String[predefinedTypes.size()][2];
        int i = 0;
        for (DataType d : predefinedTypes) {
            this.predefinedTypesValues[i][0] = d.getName();
            this.predefinedTypesValues[i][1] = new MRef(d).toString();
            i++;
        }
        
        this.noteMimeTypeValues = new String[][] {
                { AppProjectUi.I18N.getString("Note.MimeType.HTML"), "text/html" },
                { AppProjectUi.I18N.getString("Note.MimeType.PLAIN"), "text/plain" }
        };
        
        this.richNoteTypeValues = new String[][] {
                { AppProjectUi.I18N.getString("RichNote.Type.HTML"), "text/html" },
                { AppProjectUi.I18N.getString("RichNote.Type.OoHTML"), "text/html/ooo" },
                { AppProjectUi.I18N.getString("RichNote.Type.OoWriter"), "application/vnd.oasis.opendocument.text" },
                { AppProjectUi.I18N.getString("RichNote.Type.MsWord"), "application/vnd.openxmlformats-officedocument.wordprocessingml.document" }
        };
        
        this.visibilityValues = new String[][] {
                { AppProjectUi.I18N.getString("$" + VisibilityMode.PUBLIC.getName()), VisibilityMode.PUBLIC.getName() },
                { AppProjectUi.I18N.getString("$" + VisibilityMode.PACKAGEVISIBILITY.getName()), VisibilityMode.PACKAGEVISIBILITY.getName() },
                { AppProjectUi.I18N.getString("$" + VisibilityMode.PROTECTED.getName()), VisibilityMode.PROTECTED.getName() },
                { AppProjectUi.I18N.getString("$" + VisibilityMode.PRIVATE.getName()), VisibilityMode.PRIVATE.getName() }
        };
        
        // Load themes from the configuration directory
        List<String[]> themes = new ArrayList<>();
        Path stylesDir = projectService.getOpenedProject().getProjectDataPath().resolve(ProjectPreferencesPage.PROJECT_STYLE_SUBDIR);
        try (DirectoryStream<Path> styleFiles = Files.newDirectoryStream(stylesDir, "*" + ProjectPreferencesPage.THEME_FILE_EXTENSION)) {
            for (final Path f : styleFiles) {
                try (InputStreamReader reader = new InputStreamReader(f.toUri().toURL().openStream())) {
                    final Properties loadedValues = new Properties();
                    loadedValues.load(reader);
                    String name = (String) loadedValues.get("stylename");
                    if (name != null) {
                        themes.add(new String[] { name, name });
                    }
                } catch (@SuppressWarnings ("unused") IOException e) {
                    // Ignore theme loading error
                }
            }
        } catch (@SuppressWarnings ("unused") IOException e1) {
            AppProjectUi.LOG.warning("Unable to load diagram themes from " + stylesDir.toString());
        } finally {
            this.diagramThemeValues = themes.toArray(new String[0][0]);
        }
    }

}
