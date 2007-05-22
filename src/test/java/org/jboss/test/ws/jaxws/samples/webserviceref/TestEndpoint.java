
package org.jboss.test.ws.jaxws.samples.webserviceref;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 * 
 */
@WebService(name = "TestEndpoint", targetNamespace = "http://org.jboss.ws/wsref", wsdlLocation = "http://localhost.localdomain:8080/jaxws-samples-webserviceref?wsdl")
@SOAPBinding(style = Style.RPC)
public interface TestEndpoint {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "http://org.jboss.ws/wsref", partName = "return")
    public String echo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
