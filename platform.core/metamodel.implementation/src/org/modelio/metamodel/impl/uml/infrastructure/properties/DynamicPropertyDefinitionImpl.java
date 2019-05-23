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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.properties.DynamicPropertyDefinitionData;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("922ada73-a631-45a4-8ccb-b91d7a26cccb")
public class DynamicPropertyDefinitionImpl extends PropertyDefinitionImpl implements DynamicPropertyDefinition {
    @objid ("261a4d72-4079-4832-9c3d-48b507d6b152")
    @Override
    public Object convertToObject(String value, ModelElement object) {
        // If there is no base type, can only return the string
        if (getType().getBaseType() == null) {
            return value;
        }
        
        IDynamicPropertyResolver resolver = getDynamicPropertyResolver();
        if (resolver != null) {
            return resolver.convertToObject(this, value, object);
        } else {
            throw new UnsupportedOperationException("No DynamicBehavior found for the " + this.toString() + " property.");
        }
    }

    @objid ("0b651b9e-97a7-4ce8-8e1f-e15f15e9807b")
    @Override
    public String convertToString(Object value, ModelElement object) {
        IDynamicPropertyResolver resolver = getDynamicPropertyResolver();
        if (resolver != null) {
            return resolver.convertToString(this, value, object);
        } else {
            throw new UnsupportedOperationException("No DynamicBehavior found for the " + this.toString() + " property.");
        }
    }

    @objid ("6b0eecda-d721-4335-9cd4-cd403584761d")
    private IDynamicPropertyResolver getDynamicPropertyResolver() {
        String resolverKey = getProperty(DynamicPropertyDefinition.PROPERTY_RESOLVER_TABLE, DynamicPropertyDefinition.PROPERTY_RESOLVER_KEY);
        return getMClass().getOrigin().getDynamicBehavior(resolverKey, IDynamicPropertyResolver.class);
    }

    @objid ("017ca835-4a72-4ec9-b920-97338440fba8")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("9eb47f7b-925a-4bda-b1f5-eae06312fbb0")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("4f3c863d-f7d6-407d-8e11-85a1a26dab70")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDynamicPropertyDefinition(this);
    }

}
