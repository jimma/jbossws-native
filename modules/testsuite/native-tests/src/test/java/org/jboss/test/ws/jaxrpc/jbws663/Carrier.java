/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.jaxrpc.jbws663;


public class Carrier {
    protected int carrierID;
    protected java.lang.String carrierName;
    protected java.lang.String country;
    protected int maxTextLength;
    protected java.lang.String networkStandard;
    
    public Carrier() {
    }
    
    public Carrier(int carrierID, java.lang.String carrierName, java.lang.String country, int maxTextLength, java.lang.String networkStandard) {
        this.carrierID = carrierID;
        this.carrierName = carrierName;
        this.country = country;
        this.maxTextLength = maxTextLength;
        this.networkStandard = networkStandard;
    }
    
    public int getCarrierID() {
        return carrierID;
    }
    
    public void setCarrierID(int carrierID) {
        this.carrierID = carrierID;
    }
    
    public java.lang.String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(java.lang.String carrierName) {
        this.carrierName = carrierName;
    }
    
    public java.lang.String getCountry() {
        return country;
    }
    
    public void setCountry(java.lang.String country) {
        this.country = country;
    }
    
    public int getMaxTextLength() {
        return maxTextLength;
    }
    
    public void setMaxTextLength(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }
    
    public java.lang.String getNetworkStandard() {
        return networkStandard;
    }
    
    public void setNetworkStandard(java.lang.String networkStandard) {
        this.networkStandard = networkStandard;
    }
}
