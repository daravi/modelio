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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("009712f6-c4be-1fd8-97fe-001ec947cd2a")
public class ArtifactData extends ClassifierData {
    @objid ("fb3aceeb-7a59-466a-b133-ad274396551a")
     Object mFileName = "";

    @objid ("8590fc5f-7dd0-4ae2-8eb3-594ddd8018d0")
     List<SmObjectImpl> mUtilized = null;

    @objid ("4db8c122-3619-4328-94d1-94a0056959a1")
     List<SmObjectImpl> mDeploymentLocation = null;

    @objid ("c8401e13-bd2c-4855-b188-77d1163379b2")
    public ArtifactData(ArtifactSmClass smClass) {
        super(smClass);
    }

}
