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
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tCategoryValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tCategoryValue">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement">
 * &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("c51d9d41-3729-45bf-9485-bc9b4bf16e56")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tCategoryValue")
public class TCategoryValue extends TBaseElement {
    @objid ("ccb84ecf-439e-419a-90d9-e563b1755bfb")
    @XmlAttribute(name = "value")
    protected String value;

    /**
     * Gets the value of the value property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("ce51d2f4-0bdf-4817-8505-bc3030468c93")
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of the value property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("359ffeaf-2d1f-4831-bac6-8d300f742014")
    public void setValue(String value) {
        this.value = value;
    }

}
