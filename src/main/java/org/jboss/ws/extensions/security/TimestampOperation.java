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
package org.jboss.ws.extensions.security;

import java.util.List;

import org.jboss.ws.extensions.security.element.SecurityHeader;
import org.jboss.ws.extensions.security.element.Timestamp;
import org.w3c.dom.Document;

public class TimestampOperation implements EncodingOperation
{
   private SecurityHeader header;

   private SecurityStore store;

   public TimestampOperation(SecurityHeader header, SecurityStore store)
   {
      this.header = header;
      this.store = store;
   }

   public void process(Document message, List<Target> targets, String alias, String credential, String algorithm, boolean digest, boolean useNonce, boolean useTimestamp) throws WSSecurityException
   {
      Integer ttl = null;

      try
      {
         // Time to live is stuffed in the credential field
         ttl = Integer.valueOf(credential);
      }
      catch (NumberFormatException e)
      {
         // Eat
      }

      header.setTimestamp(new Timestamp(ttl, message));
   }
}