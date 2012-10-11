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
package org.jboss.ws.core.client;

import org.jboss.ws.NativeMessages;
import org.jboss.ws.api.util.ServiceLoader;

/**
 * A factory for remote connections 
 *
 * @author Thomas.Diesler@jboss.org
 * @since 10-Jan-2008
 */
public class RemoteConnectionFactory
{
   public RemoteConnection getRemoteConnection(EndpointInfo epInfo)
   {
      String targetAddress = epInfo.getTargetAddress();
      if (targetAddress == null)
         throw NativeMessages.MESSAGES.cannotObtainTargetAddressFrom(epInfo);
      
      String key = null;
      targetAddress = targetAddress.toLowerCase();
      if (targetAddress.startsWith("http"))
         key = RemoteConnection.class.getName() + ".http";
      
      if (key == null)
         throw NativeMessages.MESSAGES.cannotObtainRemoteConnectionFor(targetAddress);
      
      RemoteConnection con = (RemoteConnection)ServiceLoader.loadService(key, null, this.getClass().getClassLoader());
      if (con == null)
         throw NativeMessages.MESSAGES.cannotObtainRemoteConnectionFor(key);
      
      return con;
   }
}
