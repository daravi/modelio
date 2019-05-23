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

package org.modelio.mda.infra.service.plugins;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.osgi.container.Module;
import org.modelio.gproject.module.IMetamodelFragmentHandle;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.version.ModelioVersion;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

@objid ("6088c346-5cf2-4c2c-97ee-bda6f52eaa13")
public class PluginModuleHandle implements IModuleHandle {
    @objid ("371c846b-105e-4a49-87cf-e94a9266fb88")
    private final boolean isMandatory;

    @objid ("0a721414-2da0-48d3-8a6b-234b0eab82f9")
    private final IModuleHandle fileHandle;

    @objid ("e73d4ea9-c9c1-45e5-83e9-5d4eaf4be38c")
    private final Path resourcesPath;

    @objid ("6add9965-ae4c-44eb-a8cc-b5d5b3028ed0")
    private final Bundle plugin;

    @objid ("2b1ec1e5-d03a-4357-a261-b892a3bcbc26")
    public PluginModuleHandle(Bundle plugin, IModuleHandle fileHandle, boolean isMandatory) throws IOException {
        this.plugin = plugin;
        this.fileHandle = fileHandle;
        this.isMandatory = isMandatory;
        
        try {
            this.resourcesPath = Paths.get(FileLocator.toFileURL(plugin.getEntry("/")).toURI());
        } catch (URISyntaxException e) {
            throw (MalformedURLException) new MalformedURLException(e.getMessage()).initCause(e);
        }
    }

    @objid ("cac33caa-b40d-4bce-ba91-a95141cbe924")
    @Override
    public Version getBinaryVersion() {
        return ModelioVersion.VERSION;
    }

    @objid ("75d4e841-26ec-42db-abde-8111391ca353")
    @Override
    public List<VersionedItem<?>> getDependencies() {
        return this.fileHandle.getDependencies();
    }

    @objid ("0da65ba7-3970-4376-9add-6f10a5e740cd")
    @Override
    public List<Path> getDocPaths() {
        return this.fileHandle.getDocPaths();
    }

    @objid ("b3481ce6-233f-4c92-9344-44138c6466ed")
    @Override
    public Path getDynamicModelPath() {
        return this.fileHandle.getDynamicModelPath();
    }

    @objid ("3a8a16c3-4a88-4871-9d49-3397621427dc")
    @Override
    public List<Path> getJarPaths() {
        return Collections.emptyList();
    }

    @objid ("79844bcb-0228-4e8f-b0ec-34182b64d59e")
    @Override
    public String getMainClassName() {
        return this.fileHandle.getMainClassName();
    }

    @objid ("527b78bf-3c32-4212-91a3-866299a62afc")
    @Override
    public Path getModelComponentPath() {
        return this.fileHandle.getModelComponentPath();
    }

    @objid ("ed0efb64-c68c-4a5d-9d89-08edfa7849e3")
    @Override
    public Path getModuleInfosPath() {
        return this.fileHandle.getModuleInfosPath();
    }

    @objid ("d04c6478-a40b-45d7-a4de-5839936bab51")
    @Override
    public String getName() {
        return this.fileHandle.getName();
    }

    @objid ("8cdf119f-cf0c-4c22-9a91-1a0729d8233d")
    @Override
    public Path getResourcePath() {
        return this.resourcesPath;
    }

    @objid ("0898a417-b40c-44a9-990a-071d940137ef")
    @Override
    public String getUid() {
        return this.fileHandle.getUid();
    }

    @objid ("f461fd0d-4064-42c3-9969-58d09f0ea5c1")
    @Override
    public Version getVersion() {
        return this.fileHandle.getVersion();
    }

    @objid ("f15f22be-ed3b-4657-b211-cd86657f0220")
    @Override
    public List<VersionedItem<?>> getWeakDependencies() {
        return this.fileHandle.getWeakDependencies();
    }

    @objid ("3d6fa52d-fb8d-4ffd-acad-4daeee60babe")
    @Override
    public Path getArchive() {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("74a8753b-3bed-407a-b929-e996c949a39d")
    @Override
    public Map<String, Path> getStylePaths() {
        return this.fileHandle.getStylePaths();
    }

    @objid ("11ceb43d-5757-4f18-80a1-45142aab98c9")
    @Override
    public List<IMetamodelFragmentHandle> getMetamodelFragments() {
        return this.fileHandle.getMetamodelFragments();
    }

    @objid ("8efd3168-0a8c-4643-8566-ffd19adb5c65")
    @Override
    public boolean isMandatory() {
        return this.isMandatory;
    }

    @objid ("4bed11f6-6e93-46ef-a1de-e5a57a7ccfa0")
    @Override
    public ClassLoader getProvidedClassLoader() {
        final BundleWiring wiring = this.plugin.adapt(BundleWiring.class);
        if (wiring==null) {
            throw new IllegalStateException(String.format("%s is in %s state.", this.plugin, this.plugin.adapt(Module.class).getState()));
        }
        return wiring.getClassLoader();
    }

}
