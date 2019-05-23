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

package org.modelio.core.ui.nattable.viewer.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;

/**
 * Access to services required by the property table.
 * <p>
 * These service usually don't change with the table input, that's why there are separate.
 * 
 * @author cma
 * @since Valkyrie 3.8
 */
@objid ("103a0a5e-9f50-4ff6-991c-a2066e4f8106")
public interface INatTableViewerContext {
    @objid ("d3c26b2b-ba87-44b6-b72f-53d3b582e743")
    IActivationService getActivationService();

    @objid ("9cdaed80-a0a0-4ad4-bc8f-e84f2915c266")
    IMModelServices getModelService();

    @objid ("1e679f22-56ec-468a-a6b2-45f30a902d4e")
    IModelioNavigationService getNavigationService();

    @objid ("eb89ce54-ad2a-45eb-8c87-1b9a9227faa7")
    IModelioPickingService getPickingService();

    @objid ("65d68276-e327-4912-b754-d7f0da03171e")
    ICoreSession getSession();

    @objid ("df492012-d77b-473c-8d0d-03bb75fd6a8b")
    IPreferenceStore getProjectPreferences(String nodeId);

    /**
     * Get the core session model access.
     * @return the IModel or null if there is no session.
     */
    @objid ("b1622837-ac57-47bb-bba4-af81710925ad")
    default IModel getSessionModel() {
        ICoreSession s = getSession();
        if (s == null) {
            return null;
        }
        return s.getModel();
    }

    @objid ("9d51e2a7-bae0-407c-8ab2-34470af63962")
    IMdaExpert getMdaExpert();

}
