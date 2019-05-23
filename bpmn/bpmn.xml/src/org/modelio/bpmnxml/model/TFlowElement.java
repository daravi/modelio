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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tFlowElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tFlowElement">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement">
 * &lt;sequence>
 * &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}auditing" minOccurs="0"/>
 * &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}monitoring" minOccurs="0"/>
 * &lt;element name="categoryValueRef" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("64ff5550-1520-41d5-a77a-a3749c57772b")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tFlowElement", propOrder = {
    "auditing",
    "monitoring",
    "categoryValueRef"
})
@XmlSeeAlso({
    TSequenceFlow.class,
    TDataStoreReference.class,
    TDataObject.class,
    TDataObjectReference.class,
    TFlowNode.class
})
public abstract class TFlowElement extends TBaseElement {
    @objid ("a422b5ad-eab6-45b1-9235-674765dc9a1a")
    @XmlAttribute(name = "name")
    protected String name;

    @objid ("72306156-316f-456a-a8e9-c3507ac0b952")
    protected TAuditing auditing;

    @objid ("7f778e90-fe39-4b3e-9a8d-177eea790fe7")
    protected TMonitoring monitoring;

    @objid ("35df918b-bdc2-491a-b949-490d26586b5a")
    protected List<QName> categoryValueRef;

    /**
     * Gets the value of the auditing property.
     * @return
     * possible object is
     * {@link TAuditing }
     */
    @objid ("f9815ea6-1e4c-44c4-9333-19cadce18897")
    public TAuditing getAuditing() {
        return this.auditing;
    }

    /**
     * Sets the value of the auditing property.
     * @param value allowed object is
     * {@link TAuditing }
     */
    @objid ("53803556-2f5d-4549-8dc6-7b36ab2fcc63")
    public void setAuditing(TAuditing value) {
        this.auditing = value;
    }

    /**
     * Gets the value of the monitoring property.
     * @return
     * possible object is
     * {@link TMonitoring }
     */
    @objid ("6b541861-991f-43bf-b509-3ef8541a931d")
    public TMonitoring getMonitoring() {
        return this.monitoring;
    }

    /**
     * Sets the value of the monitoring property.
     * @param value allowed object is
     * {@link TMonitoring }
     */
    @objid ("14c0e8a5-8135-422f-b119-fe554dbe484f")
    public void setMonitoring(TMonitoring value) {
        this.monitoring = value;
    }

    /**
     * Gets the value of the categoryValueRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryValueRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getCategoryValueRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     */
    @objid ("0b5bd5c9-204a-4767-97a4-bcc0a18dd773")
    public List<QName> getCategoryValueRef() {
        if (this.categoryValueRef == null) {
            this.categoryValueRef = new ArrayList<>();
        }
        return this.categoryValueRef;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("e0cd787f-2320-4cd1-8f82-4fe327961a11")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("e6280bc9-1aee-47df-b7e3-581ad61bb3ff")
    public void setName(String value) {
        this.name = value;
    }

}
