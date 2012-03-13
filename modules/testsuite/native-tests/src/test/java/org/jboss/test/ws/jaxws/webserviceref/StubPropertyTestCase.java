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
package org.jboss.test.ws.jaxws.webserviceref;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import junit.framework.Test;

import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestHelper;
import org.jboss.wsf.test.JBossWSTestSetup;

/**
 * Test the JAXWS annotation: javax.xml.ws.WebServiceRef
 *
 * @author Thomas.Diesler@jboss.com
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public class StubPropertyTestCase extends JBossWSTest
{
   public final String TARGET_ENDPOINT_ADDRESS = "http://" + getServerHost() + ":8080/jaxws-webserviceref-secure";

   public static Test suite()
   {
      return new JBossWSTestSetup(StubPropertyTestCase.class, "jaxws-webserviceref-secure.jar", true);
   }

   public void testWSDLAccess() throws Exception
   {
      URL wsdlURL = new URL(TARGET_ENDPOINT_ADDRESS + "?wsdl");
      InputStream inputStream = wsdlURL.openStream();
      assertNotNull(inputStream);
      inputStream.close();
   }

   public void testDynamicProxy() throws Exception
   {
      URL wsdlURL = new URL(TARGET_ENDPOINT_ADDRESS + "?wsdl");
      QName qname = new QName("http://org.jboss.ws/wsref", "SecureEndpointService");
      Service service = Service.create(wsdlURL, qname);
      SecureEndpoint port = (SecureEndpoint)service.getPort(SecureEndpoint.class);

      BindingProvider bindingProvider = (BindingProvider)port;
      bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "kermit");
      bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "thefrog");

      String helloWorld = "Hello World";
      Object retObj = port.echo(helloWorld);
      assertEquals(helloWorld, retObj);
   }

   public void testExplicitSecureService1() throws Throwable
   {
      String reqMsg = "SecureService1";
      final String respMsg = executeApplicationClient(reqMsg, "kermit", "thefrog");
      assertEquals(reqMsg, respMsg);
   }

   public void testExplicitSecureService2() throws Throwable
   {
      String reqMsg = "SecureService2";
      final String respMsg = executeApplicationClient(reqMsg, "kermit", "thefrog");
      assertEquals(reqMsg, respMsg);
   }

   public void testExplicitSecurePort1() throws Throwable
   {
      String reqMsg = "SecurePort1";
      final String respMsg = executeApplicationClient(reqMsg, "kermit", "thefrog");
      assertEquals(reqMsg, respMsg);
   }

   public void testImplicitSecureService1() throws Throwable
   {
      String reqMsg = "SecureService1";
      final String respMsg = executeApplicationClient(reqMsg);
      assertEquals(reqMsg, respMsg);
   }

   public void testImplicitSecureService2() throws Throwable
   {
      final String reqMsg = "SecureService2";
      final String respMsg = executeApplicationClient(reqMsg);
      assertEquals(reqMsg, respMsg);
   }

   public void testImplicitSecurePort1() throws Throwable
   {
      final String reqMsg = "SecurePort1";
      final String respMsg = executeApplicationClient(reqMsg);
      assertEquals(reqMsg, respMsg);
   }

   private String executeApplicationClient(final String... appclientArgs) throws Throwable
   {
      try
      {
         final OutputStream appclientOS = new ByteArrayOutputStream();
         JBossWSTestHelper.deployAppclient("jaxws-webserviceref-secure-appclient.ear#jaxws-webserviceref-secure-appclient.jar", appclientOS, appclientArgs);
         // wait till appclient stops
         String appclientLog = appclientOS.toString();
         while (!appclientLog.contains("stopped in")) {
            Thread.sleep(100);
            appclientLog = appclientOS.toString();
         }
         // assert appclient logs
         assertTrue(appclientLog.contains("TEST START"));
         assertTrue(appclientLog.contains("TEST END"));
         assertTrue(appclientLog.contains("RESULT ["));
         assertTrue(appclientLog.contains("] RESULT"));
         int indexOfResultStart = appclientLog.indexOf("RESULT [");
         int indexOfResultStop = appclientLog.indexOf("] RESULT");
         assertTrue(indexOfResultStart > 0);
         assertTrue(indexOfResultStop > 0);
         return appclientLog.substring(indexOfResultStart + 8, indexOfResultStop);
      }
      finally
      {
         JBossWSTestHelper.undeployAppclient("jaxws-webserviceref-secure-appclient.ear#jaxws-webserviceref-secure-appclient.jar", false);
      }
   }
   
}
