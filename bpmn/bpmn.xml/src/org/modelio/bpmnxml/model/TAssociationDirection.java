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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for tAssociationDirection.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tAssociationDirection">
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="None"/>
 * &lt;enumeration value="One"/>
 * &lt;enumeration value="Both"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@objid ("e4fb3ed4-ae79-4602-9d87-92c25eee9246")
@XmlType(name = "tAssociationDirection")
@XmlEnum
public enum TAssociationDirection {
    @XmlEnumValue("None")
    NONE ("None"),
    @XmlEnumValue("One")
    ONE ("One"),
    @XmlEnumValue("Both")
    BOTH ("Both");

    @objid ("9a986066-9cfd-4a8b-adaf-eca4a7c03f06")
    private final String value;

    @objid ("db12bf72-dfb7-4aa5-b2ea-f35361526883")
    TAssociationDirection(String v) {
        this.value = v;
    }

    @objid ("e8d56e20-6a32-425a-8d9f-531675cce0d7")
    public String value() {
        return this.value;
    }

    @objid ("8caa62e4-e301-4598-8f38-a05f3648fd39")
    public static TAssociationDirection fromValue(String v) {
        for (TAssociationDirection c: TAssociationDirection.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
