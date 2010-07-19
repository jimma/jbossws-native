/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.jbws2698;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import junit.framework.Test;
import javax.xml.ws.handler.Handler;
import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestSetup;

/**
 * [JBWS-2698] Calling setPrefix(newPrefix) on any SOAPElement the 
 * prefix is not used for marshalling.
 * 
 * @author darran.lofthouse@jboss.com
 * @since 7th July 2009
 * @see https://jira.jboss.org/jira/browse/JBWS-2698
 */
public class JBWS2698TestCase extends JBossWSTest
{

   public final String TARGET_ENDPOINT_ADDRESS = "http://" + getServerHost() + ":8080/jaxws-jbws2698/";

   private static Endpoint port;

   public static Test suite() throws Exception
   {
      return new JBossWSTestSetup(JBWS2698TestCase.class, "jaxws-jbws2698.war");
   }

   public void setUp() throws Exception
   {
      super.setUp();
      URL wsdlURL = new URL(TARGET_ENDPOINT_ADDRESS + "?wsdl");
      QName serviceName = new QName("http://ws.jboss.org/jbws2698", "EndpointImplService");

      Service service = Service.create(wsdlURL, serviceName);
      port = service.getPort(Endpoint.class);

      BindingProvider bindingProvider = (BindingProvider)port;
      List<Handler> handlerChain = new ArrayList<Handler>();
      handlerChain.add(new SOAPHandler());
      bindingProvider.getBinding().setHandlerChain(handlerChain);
   }

   public void testCall() throws Exception
   {
      String message = "Howdy";

      String response = port.echo(message);
      assertEquals("Response", message, response);
   }

}