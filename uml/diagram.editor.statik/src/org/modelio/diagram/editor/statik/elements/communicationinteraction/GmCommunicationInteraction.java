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

package org.modelio.diagram.editor.statik.elements.communicationinteraction;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link CommunicationInteraction}.
 */
@objid ("3493c831-55b7-11e2-877f-002564c97630")
public class GmCommunicationInteraction extends GmPortContainer {
    @objid ("3493c835-55b7-11e2-877f-002564c97630")
    private CommunicationInteraction element;

    /**
     * Current version of this Gm.
     */
    @objid ("3493c83e-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("3493c841-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3493c843-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("5bb3f745-5bd5-11e2-9e33-00137282c51b")
     static final GmCommunicationInteractionStructuredStyleKeys STRUCTURED_KEYS = new GmCommunicationInteractionStructuredStyleKeys();

    @objid ("5bb3f747-5bd5-11e2-9e33-00137282c51b")
     static final GmCommunicationInteractionSimpleStyleKeys SIMPLE_KEYS = new GmCommunicationInteractionSimpleStyleKeys();

    @objid ("5bb3f749-5bd5-11e2-9e33-00137282c51b")
     static final GmCommunicationInteractionImageStyleKeys IMAGE_KEYS = new GmCommunicationInteractionImageStyleKeys();

    @objid ("ad19cd39-e9e9-42e1-8c77-76b2c256ccfc")
     static final GmCommunicationInteractionUserImageStyleKeys USERIMAGE_KEYS = new GmCommunicationInteractionUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the callBehavior is unmasked.
     * @param el the unmasked callBehavior.
     * @param ref a reference to the unmasked callBehavior.
     */
    @objid ("3493c845-55b7-11e2-877f-002564c97630")
    public GmCommunicationInteraction(final IGmDiagram diagram, final CommunicationInteraction el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmCommunicationInteractionPrimaryNode mainNode = new GmCommunicationInteractionPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmCommunicationInteraction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("3493c854-55b7-11e2-877f-002564c97630")
    public GmCommunicationInteraction() {
        // Nothing specific to do.
    }

    @objid ("34954eba-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("34954ec3-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("34954ecc-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("34954ed3-55b7-11e2-877f-002564c97630")
    @Override
    public CommunicationInteraction getRepresentedElement() {
        return this.element;
    }

    @objid ("34954eda-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmCommunicationInteraction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmCommunicationInteraction.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmCommunicationInteraction.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmCommunicationInteraction.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmCommunicationInteraction.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("34954ee5-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmCommunicationInteraction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmCommunicationInteraction.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmCommunicationInteraction.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmCommunicationInteraction.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmCommunicationInteraction.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("34954eee-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmCommunicationInteraction.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("34954ef5-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmCommunicationInteraction.", GmCommunicationInteraction.MINOR_VERSION);
    }

    @objid ("34954efb-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (CommunicationInteraction) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmCommunicationInteraction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("3496d55e-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmCommunicationInteraction.MAJOR_VERSION;
    }

    @objid ("3496d563-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (CommunicationInteraction) resolveRef(getRepresentedRef());
    }

    @objid ("3496d569-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmCommunicationInteraction.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case IMAGE:
            default: {
                break;
            }
        
            }
        }
        return ret;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("3496d572-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                                        || GmCommunicationInteraction.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("3496d584-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("c4b75801-1bb6-4bbb-b153-94b80554cd94")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmCommunicationInteraction.IMAGE_LABEL_ROLE);
    }

}
