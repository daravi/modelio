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

package org.modelio.diagram.editor.sequence.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * StyleKey provider for the GmLifeline class.
 */
@objid ("d93f0b87-55b6-11e2-877f-002564c97630")
public class GmLifelineStructuredStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("4fe24969-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("LIFELINE_REPMODE", MetaKey.REPMODE);

    @objid ("4fe2496b-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("LIFELINE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("4fe2496d-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("LIFELINE_FILLMODE", MetaKey.FILLMODE);

    @objid ("4fe2496f-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("LIFELINE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("4fe24971-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("LIFELINE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("4fe24973-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("LIFELINE_FONT", MetaKey.FONT);

    @objid ("4fe24975-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("LIFELINE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("4fe24977-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("LIFELINE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("4fe24979-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("LIFELINE_SHOWTAGS", MetaKey.SHOWTAGS);

}
