/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package org.jboss.ws.core.jaxws.client;

// $Id$

import org.jboss.ws.integration.CommonServiceRefBinder;
import org.jboss.wsf.spi.metadata.j2ee.serviceref.UnifiedServiceRefMetaData;

import javax.naming.Referenceable;

/**
 * Binds a JAXWS Service object in the client's ENC
 *
 * @author Thomas.Diesler@jboss.org
 * @since 17-Jan-2007
 */
public class ServiceRefBinderJAXWS extends CommonServiceRefBinder
{
   protected Referenceable buildServiceReferenceable(
     String serviceImplClass, String targetClassName, UnifiedServiceRefMetaData serviceRef)
   {
      return new ServiceReferenceable(serviceImplClass, targetClassName, serviceRef);      
   }
}
