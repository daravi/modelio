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

package org.modelio.diagram.editor.sequence.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.CombinedFragmentEditPart;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.GmCombinedFragment;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.CombinedFragmentPrimaryNodeEditPart;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.GmCombinedFragmentPrimaryNode;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.GmInteractionOperandContainer;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.InteractionOperandContainerEditPart;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.OperatorEditPart;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.ExecutionOccurenceSpecificationEditPart;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.GmExecutionOccurenceSpecification;
import org.modelio.diagram.editor.sequence.elements.executionspecification.ExecutionSpecificationEditPart;
import org.modelio.diagram.editor.sequence.elements.executionspecification.GmExecutionSpecification;
import org.modelio.diagram.editor.sequence.elements.gate.GateEditPart;
import org.modelio.diagram.editor.sequence.elements.gate.GatePrimaryNodeEditPart;
import org.modelio.diagram.editor.sequence.elements.gate.GmGate;
import org.modelio.diagram.editor.sequence.elements.gate.GmGatePrimaryNode;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.GmInteractionOperand;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.InteractionOperandEditPart;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode.GmGuardLabel;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode.GmInteractionOperandPrimaryNode;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode.GuardEditPart;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode.InteractionOperandPrimaryNodeEditPart;
import org.modelio.diagram.editor.sequence.elements.interactionuse.GmInteractionUse;
import org.modelio.diagram.editor.sequence.elements.interactionuse.InteractionUseEditPart;
import org.modelio.diagram.editor.sequence.elements.interactionuse.gate.GateOnInteractionUseEditPart;
import org.modelio.diagram.editor.sequence.elements.interactionuse.gate.GateOnInteractionUsePrimaryNodeEditPart;
import org.modelio.diagram.editor.sequence.elements.interactionuse.gate.GmGateOnInteractionUse;
import org.modelio.diagram.editor.sequence.elements.interactionuse.gate.GmGateOnInteractionUsePrimaryNode;
import org.modelio.diagram.editor.sequence.elements.interactionuse.primarynode.GmInteractionUsePrimaryNode;
import org.modelio.diagram.editor.sequence.elements.interactionuse.primarynode.InteractionUsePrimaryNodeEditPart;
import org.modelio.diagram.editor.sequence.elements.lifeline.GmLifeline;
import org.modelio.diagram.editor.sequence.elements.lifeline.LifelineEditPart;
import org.modelio.diagram.editor.sequence.elements.lifeline.body.GmLifelineBody;
import org.modelio.diagram.editor.sequence.elements.lifeline.body.LifelineBodyEditPart;
import org.modelio.diagram.editor.sequence.elements.lifeline.header.GmLifelineHeader;
import org.modelio.diagram.editor.sequence.elements.lifeline.header.GmLifelineHeaderContainer;
import org.modelio.diagram.editor.sequence.elements.lifeline.header.LifelineHeaderContainerEditPart;
import org.modelio.diagram.editor.sequence.elements.lifeline.header.LifelineHeaderContainerImageEditPart;
import org.modelio.diagram.editor.sequence.elements.lifeline.header.LifelineHeaderEditPart;
import org.modelio.diagram.editor.sequence.elements.message.GmMessage;
import org.modelio.diagram.editor.sequence.elements.message.MessageEditPart;
import org.modelio.diagram.editor.sequence.elements.message.label.GmMessageHeader;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.SequenceDiagramEditPart;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.GmStateInvariant;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.GmStateInvariantBodyText;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.StateInvariantEditPart;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.common.text.ElementTextEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * The EditPart factory for Sequence diagrams.
 * <p>
 * Implementation of {@link EditPartFactory}.
 */
@objid ("d9853c4a-55b6-11e2-877f-002564c97630")
public final class SequenceEditPartFactory implements EditPartFactory {
    /**
     * the default factory to use when structured mode is requested.
     */
    @objid ("44516f5a-31fd-414c-93b6-71c19a5df783")
    private static final StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    /**
     * the default factory to use when simple mode is requested.
     */
    @objid ("ffd9732b-a0e8-4394-8815-a2f5e8e6bd77")
    private static final SimpleModeEditPartFactory simpleModeEditPartFactory = new SimpleModeEditPartFactory();

    @objid ("234b4aa0-8b19-420b-bf4e-c43f6b05a365")
    private static final ImageModeEditPartFactory imageModeEditPartFactory = new ImageModeEditPartFactory();

    @objid ("6fd07e7e-2619-4d7e-84bd-af8eccfb3240")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation model.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case IMAGE:
                editPart = SequenceEditPartFactory.imageModeEditPartFactory.createEditPart(context, model);
                break;
            case SIMPLE:
                editPart = SequenceEditPartFactory.simpleModeEditPartFactory.createEditPart(context, model);
                break;
            case STRUCTURED:
                editPart = SequenceEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            default:
                editPart = null;
            }
        
            if (editPart != null) {
                return editPart;
            }
        
            return null;
        }
        // Link models are always in structured mode.
        editPart = SequenceEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
        
        if (editPart != null) {
            return editPart;
        }
        return null;
    }

    /**
     * EditPart factory for sequence elements in structured mode.
     */
    @objid ("d9853c62-55b6-11e2-877f-002564c97630")
    private static final class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("e81a32e5-b162-471d-be4b-1bdddd5805c7")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            Class<? extends Object> cls = model.getClass();
            
            if (cls == GmSequenceDiagram.class) {
                editPart = new SequenceDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifeline.class) {
                editPart = new LifelineEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifelineHeader.class) {
                editPart = new LifelineHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifelineHeaderContainer.class) {
                editPart = new LifelineHeaderContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifelineBody.class) {
                editPart = new LifelineBodyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmMessage.class) {
                editPart = new MessageEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmMessageHeader.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmExecutionOccurenceSpecification.class) {
                editPart = new ExecutionOccurenceSpecificationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmExecutionSpecification.class) {
                editPart = new ExecutionSpecificationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmInteractionUse.class) {
                editPart = new InteractionUseEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmInteractionUsePrimaryNode.class) {
                editPart = new InteractionUsePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.GmOperatorLabel.class) {
                editPart = new OperatorEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == org.modelio.diagram.editor.sequence.elements.interactionuse.primarynode.GmOperatorLabel.class) {
                editPart = new OperatorEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGateOnInteractionUse.class) {
                editPart = new GateOnInteractionUseEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGateOnInteractionUsePrimaryNode.class) {
                editPart = new GateOnInteractionUsePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGate.class) {
                editPart = new GateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGatePrimaryNode.class) {
                editPart = new GatePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmCombinedFragment.class) {
                editPart = new CombinedFragmentEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmCombinedFragmentPrimaryNode.class) {
                editPart = new CombinedFragmentPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmInteractionOperandContainer.class) {
                editPart = new InteractionOperandContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmInteractionOperand.class) {
                editPart = new InteractionOperandEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmInteractionOperandPrimaryNode.class) {
                editPart = new InteractionOperandPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (cls == GmGuardLabel.class) {
                editPart = new GuardEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmStateInvariant.class) {
                editPart = new StateInvariantEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmStateInvariantBodyText.class) {
                editPart = new ElementTextEditPart();
                editPart.setModel(model);
                return editPart;
            }
            return null;
        }

    }

    /**
     * EditPart factory for sequence elements in simple mode.
     */
    @objid ("d986c2b9-55b6-11e2-877f-002564c97630")
    private static final class SimpleModeEditPartFactory implements EditPartFactory {
        @objid ("5462f8c9-0df1-4ea2-8769-4be85dc565f9")
        @Override
        public EditPart createEditPart(final EditPart context, final Object model) {
            EditPart editPart = null;
            
            Class<? extends Object> cls = model.getClass();
            
            if (cls == GmGateOnInteractionUse.class) {
                editPart = new GateOnInteractionUseEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGateOnInteractionUsePrimaryNode.class) {
                editPart = new GateOnInteractionUsePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGate.class) {
                editPart = new GateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmGatePrimaryNode.class) {
                editPart = new GatePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifelineHeaderContainer.class) {
                // editPart = new LifelineHeaderContainerSimpleEditPart();
                editPart = new LifelineHeaderContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifeline.class) {
                editPart = new LifelineEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (cls == GmLifelineHeader.class) {
                editPart = new LifelineHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            return editPart;
        }

    }

    /**
     * EditPart factory for Sequence graphical models in image mode.
     * <p>
     * 
     * @author fpoyer
     */
    @objid ("d986c2c4-55b6-11e2-877f-002564c97630")
    private static final class ImageModeEditPartFactory implements EditPartFactory {
        @objid ("ea4d440c-6951-4b2b-a9ec-d5a981b7186c")
        @Override
        public EditPart createEditPart(final EditPart context, final Object model) {
            EditPart editPart = null;
            
            if (model.getClass() == GmLifelineHeaderContainer.class) {
                editPart = new LifelineHeaderContainerImageEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Lifeline in image mode...
            if (model.getClass() == GmDefaultModelElementLabel.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            return editPart;
        }

    }

}
