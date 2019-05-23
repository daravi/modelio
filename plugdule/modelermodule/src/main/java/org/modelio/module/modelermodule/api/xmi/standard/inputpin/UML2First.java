/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link InputPin} with << UML2First >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1d8bbfa9-4d67-49bd-9cf2-8020cc4981d7")
public class UML2First {
    @objid ("a0882572-4d85-4702-82e8-e84e36ff2525")
    public static final String STEREOTYPE_NAME = "UML2First";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7571e09d-411b-4e82-991d-ff81d4f6f14e")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2First proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2First >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fb3f344c-2a4a-437d-a69b-0e50c202ecd4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2First >> then instantiate a {@link UML2First} proxy.
     * 
     * @return a {@link UML2First} proxy on the created {@link InputPin}.
     */
    @objid ("2507cb07-02b3-4ce1-859e-f5c10ff63734")
    public static UML2First create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME);
        return UML2First.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2First} proxy or <i>null</i>.
     */
    @objid ("b93924f3-7f3d-4cea-871a-6e5985583f84")
    public static UML2First instantiate(InputPin obj) {
        return UML2First.canInstantiate(obj) ? new UML2First(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2First} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b03ae9f1-b7c6-448c-ac42-0e51d59f910c")
    public static UML2First safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2First.canInstantiate(obj))
        	return new UML2First(obj);
        else
        	throw new IllegalArgumentException("UML2First: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("da265872-7ed5-4cc2-9cb4-cd0a01adb49a")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2First other = (UML2First) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("2c745d44-f543-466f-96ac-c2f8b8f4b8af")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("ca32f310-3797-42b2-96fb-49de087c6059")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8143e2ab-35ce-4aba-9240-478de410fce0")
    protected UML2First(InputPin elt) {
        this.elt = elt;
    }

    @objid ("231fcc4f-1387-4364-b0db-960af406ba29")
    public static final class MdaTypes {
        @objid ("cddd23e7-f01b-4a4e-81a4-122adee44eae")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("09ed86d2-8c9b-4886-9a52-04ec5bb405cc")
        private static Stereotype MDAASSOCDEP;

        @objid ("778733e4-8b7e-4846-81fa-5127b475c8b9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7f0d8f95-52ad-4b5d-ad44-9edcd6996fdc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "37d0688a-c308-11de-8ac8-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
