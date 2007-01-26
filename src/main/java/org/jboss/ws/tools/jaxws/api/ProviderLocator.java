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
package org.jboss.ws.tools.jaxws.api;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Locates a provider.
 * 
 * @author <a href="mailto:jason.greene@jboss.com">Jason T. Greene</a>
 *
 */
class ProviderLocator 
{
   static <T> T locate(Class<T> providerType, String providerProperty, String defaultProvider, ClassLoader loader)
   {
      String provider = null;
      
      try
      {
      
         PrivilegedAction action = new PropertyAccessAction(providerProperty);
         provider = (String)AccessController.doPrivileged(action);
         if (provider == null)
            provider = defaultProvider;
      
         Class<?> clazz = loader.loadClass(provider);
         return (T) clazz.newInstance();
      }
      catch (Throwable t)
      {
         if (provider == null)
            throw new IllegalStateException("Failure reading system property: " + providerProperty);
         
         throw new IllegalStateException("Could not load provider:" + provider);
      }
      
   }
   
   private static class PropertyAccessAction implements PrivilegedAction
   {
      private String name;

      PropertyAccessAction(String name)
      {
         this.name = name;
      }

      public Object run()
      {
         return System.getProperty(name);
      }
   }
   
}