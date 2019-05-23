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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tConditionalEventDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tConditionalEventDefinition">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tEventDefinition">
 * &lt;sequence>
 * &lt;element name="condition" type="{http://www.omg.org/spec/BPMN/20100524/MODEL}tExpression"/>
 * &lt;/sequence>
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("6530e350-eade-4878-908a-25c0e05aeddd")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tConditionalEventDefinition", propOrder = {
    "condition"
})
public class TConditionalEventDefinition extends TEventDefinition {
    @objid ("19fc1e19-fd7e-4c8b-bfa6-3228e0b8419f")
    @XmlElement(required = true)
    protected TExpression condition;

    /**
     * Gets the value of the condition property.
     * @return
     * possible object is
     * {@link TExpression }
     */
    @objid ("3c0c0534-1570-4692-8f81-5c765a113d80")
    public TExpression getCondition() {
        return this.condition;
    }

    /**
     * Sets the value of the condition property.
     * @param value allowed object is
     * {@link TExpression }
     */
    @objid ("dc3b5f35-5081-4d98-84c4-0c46dfd5e021")
    public void setCondition(TExpression value) {
        this.condition = value;
    }

}
