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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tCorrelationSubscription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tCorrelationSubscription">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement">
 * &lt;sequence>
 * &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}correlationPropertyBinding" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;attribute name="correlationKeyRef" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("e457c038-0c1d-4788-95b6-a32475db73c8")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tCorrelationSubscription", propOrder = {
    "correlationPropertyBinding"
})
public class TCorrelationSubscription extends TBaseElement {
    @objid ("6b2e1bbb-5324-446d-ab9d-ddbf63e5d3bf")
    protected List<TCorrelationPropertyBinding> correlationPropertyBinding;

    @objid ("41ab2050-ac4d-4b28-9e44-0ae5070fb995")
    @XmlAttribute(name = "correlationKeyRef", required = true)
    protected QName correlationKeyRef;

    /**
     * Gets the value of the correlationPropertyBinding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the correlationPropertyBinding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getCorrelationPropertyBinding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCorrelationPropertyBinding }
     */
    @objid ("ca9c1e57-37fc-48eb-8a41-5c6f6c018d6f")
    public List<TCorrelationPropertyBinding> getCorrelationPropertyBinding() {
        if (this.correlationPropertyBinding == null) {
            this.correlationPropertyBinding = new ArrayList<>();
        }
        return this.correlationPropertyBinding;
    }

    /**
     * Gets the value of the correlationKeyRef property.
     * @return
     * possible object is
     * {@link QName }
     */
    @objid ("4f644554-89a9-4101-9c41-27fc76a5ae4b")
    public QName getCorrelationKeyRef() {
        return this.correlationKeyRef;
    }

    /**
     * Sets the value of the correlationKeyRef property.
     * @param value allowed object is
     * {@link QName }
     */
    @objid ("bcc23b65-0d64-48bd-9e7b-f82a9b5c445f")
    public void setCorrelationKeyRef(QName value) {
        this.correlationKeyRef = value;
    }

}
