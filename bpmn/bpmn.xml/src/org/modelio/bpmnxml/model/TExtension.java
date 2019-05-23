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
 * <p>Java class for tExtension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tExtension">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}documentation" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;attribute name="definition" type="{http://www.w3.org/2001/XMLSchema}QName" />
 * &lt;attribute name="mustUnderstand" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("ce023e88-40f9-4a07-92f3-b428a28138f9")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tExtension", propOrder = {
    "documentation"
})
public class TExtension {
    @objid ("d0d1ce0e-4064-468d-9b6b-02019671d6bc")
    @XmlAttribute(name = "mustUnderstand")
    protected Boolean mustUnderstand;

    @objid ("28588f0b-7f48-4fac-bfd7-56dc19d52a86")
    protected List<TDocumentation> documentation;

    @objid ("4c3d0ea7-0ba7-49fb-a7f2-fd439c9c7eca")
    @XmlAttribute(name = "definition")
    protected QName definition;

    /**
     * Gets the value of the documentation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getDocumentation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TDocumentation }
     */
    @objid ("e910c878-b787-470b-8423-96f6f12081b5")
    public List<TDocumentation> getDocumentation() {
        if (this.documentation == null) {
            this.documentation = new ArrayList<>();
        }
        return this.documentation;
    }

    /**
     * Gets the value of the definition property.
     * @return
     * possible object is
     * {@link QName }
     */
    @objid ("81093ec3-b979-4241-b5b4-0aeee8052920")
    public QName getDefinition() {
        return this.definition;
    }

    /**
     * Sets the value of the definition property.
     * @param value allowed object is
     * {@link QName }
     */
    @objid ("14a2a04c-897c-4aeb-8940-cfcaa4382589")
    public void setDefinition(QName value) {
        this.definition = value;
    }

    /**
     * Gets the value of the mustUnderstand property.
     * @return
     * possible object is
     * {@link Boolean }
     */
    @objid ("01df9946-1967-484d-aa96-ca42b1735a46")
    public boolean isMustUnderstand() {
        if (this.mustUnderstand == null) {
            return false;
        } else {
            return this.mustUnderstand;
        }
    }

    /**
     * Sets the value of the mustUnderstand property.
     * @param value allowed object is
     * {@link Boolean }
     */
    @objid ("af0b40a5-c0fe-4a7f-b8dc-996331a89ca0")
    public void setMustUnderstand(Boolean value) {
        this.mustUnderstand = value;
    }

}
