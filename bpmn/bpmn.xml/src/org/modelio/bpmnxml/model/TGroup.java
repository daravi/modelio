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
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tGroup">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tArtifact">
 * &lt;attribute name="categoryValueRef" type="{http://www.w3.org/2001/XMLSchema}QName" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("2674e1bb-28b5-4aec-89d4-7819409b77e5")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tGroup")
public class TGroup extends TArtifact {
    @objid ("f52e37c6-f7fd-4bd8-9011-9335964d9158")
    @XmlAttribute(name = "categoryValueRef")
    protected QName categoryValueRef;

    /**
     * Gets the value of the categoryValueRef property.
     * @return
     * possible object is
     * {@link QName }
     */
    @objid ("d43a8522-176e-48fc-ae2a-d4d17c2a807b")
    public QName getCategoryValueRef() {
        return this.categoryValueRef;
    }

    /**
     * Sets the value of the categoryValueRef property.
     * @param value allowed object is
     * {@link QName }
     */
    @objid ("8ee668f3-aab0-4b64-b106-90523f3bf065")
    public void setCategoryValueRef(QName value) {
        this.categoryValueRef = value;
    }

}
