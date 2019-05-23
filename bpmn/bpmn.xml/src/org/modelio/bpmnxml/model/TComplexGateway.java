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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.12.02 at 02:29:48 PM CET
//
package org.modelio.bpmnxml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tComplexGateway complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tComplexGateway">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tGateway">
 * &lt;sequence>
 * &lt;element name="activationCondition" type="{http://www.omg.org/spec/BPMN/20100524/MODEL}tExpression" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("8a15f95d-0253-4651-9721-a6296724743a")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tComplexGateway", propOrder = {
    "activationCondition"
})
public class TComplexGateway extends TGateway {
    @objid ("318ae81e-b4df-4913-b098-5b62d1e18601")
    protected TExpression activationCondition;

    @objid ("d48cdd9f-deae-4847-9d85-f59e1f8d96b0")
    @XmlAttribute(name = "default")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object _default;

    /**
     * Gets the value of the activationCondition property.
     * @return
     * possible object is
     * {@link TExpression }
     */
    @objid ("5e2d9ad2-55d8-4be4-9c12-5d8f9188282e")
    public TExpression getActivationCondition() {
        return this.activationCondition;
    }

    /**
     * Sets the value of the activationCondition property.
     * @param value allowed object is
     * {@link TExpression }
     */
    @objid ("cb8a7654-ee5c-4829-bd34-da9341410786")
    public void setActivationCondition(TExpression value) {
        this.activationCondition = value;
    }

    /**
     * Gets the value of the default property.
     * @return
     * possible object is
     * {@link Object }
     */
    @objid ("ddda739f-ce1d-4289-b271-2ce6d323f576")
    public Object getDefault() {
        return this._default;
    }

    /**
     * Sets the value of the default property.
     * @param value allowed object is
     * {@link Object }
     */
    @objid ("7022563b-389e-4970-868f-3686ae82207a")
    public void setDefault(Object value) {
        this._default = value;
    }

}
