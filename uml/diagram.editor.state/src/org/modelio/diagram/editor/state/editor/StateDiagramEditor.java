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

package org.modelio.diagram.editor.state.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("f4eb6557-55b6-11e2-877f-002564c97630")
public class StateDiagramEditor extends AbstractDiagramEditor {
    /**
     * Unique ID of all editors of State Diagrams.
     */
    @objid ("f4eb655b-55b6-11e2-877f-002564c97630")
    public static String ID = "org.modelio.diagram.editor.state.StateDiagramEditorID";

    @objid ("0b5e2a29-87e8-4da0-b2e4-8c38151dd116")
    private static final String POPUP_ID = "org.modelio.diagram.editor.state.menu.popupmenu";

    @objid ("cadd9659-5594-4e7c-aef7-08c7b88c371d")
    @Override
    protected String getPopupId() {
        return StateDiagramEditor.POPUP_ID;
    }

}
