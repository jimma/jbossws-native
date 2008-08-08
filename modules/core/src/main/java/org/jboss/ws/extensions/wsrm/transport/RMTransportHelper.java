package org.jboss.ws.extensions.wsrm.transport;

import static org.jboss.ws.extensions.wsrm.RMConstant.*;

import java.net.URI;
import java.util.Map;

import org.jboss.logging.Logger;
import org.jboss.ws.extensions.wsrm.RMConstant;
import org.jboss.ws.extensions.wsrm.RMClientSequence;

/**
 * Utility class heavily used in this transport implementation
 *
 * @author richard.opalka@jboss.com
 */
public final class RMTransportHelper
{

   private static Logger logger = Logger.getLogger(RMTransportHelper.class);
   
   private RMTransportHelper()
   {
      // no instances
   }
   
   public static boolean isRMMessage(Map<String, Object> ctx)
   {
      return (ctx != null) && (ctx.containsKey(RMConstant.REQUEST_CONTEXT)); 
   }
   
   public static String getAddressingMessageId(RMMessage rmRequest)
   {
      return (String)getWsrmRequestContext(rmRequest).get(WSA_MESSAGE_ID);
   }
   
   public static URI getBackPortURI(RMMessage rmRequest)
   {
      return getSequence(rmRequest).getBackPort();
   }
   
   private static Map<String, Object> getWsrmRequestContext(RMMessage rmRequest)
   {
      Map<String, Object> invocationCtx = (Map<String, Object>)rmRequest.getMetadata().getContext(RMChannelConstants.INVOCATION_CONTEXT);
      return (Map<String, Object>)invocationCtx.get(REQUEST_CONTEXT);
   }
   
   public static RMClientSequence getSequence(RMMessage rmRequest)
   {
      return (RMClientSequence)getWsrmRequestContext(rmRequest).get(SEQUENCE_REFERENCE);
   }
   
   public static boolean isOneWayOperation(RMMessage rmRequest)
   {
      RMMetadata meta = rmRequest.getMetadata();
      if (meta == null) throw new RuntimeException("Unable to obtain wsrm metadata");
      Map<String, Object> invCtx = meta.getContext(RMChannelConstants.INVOCATION_CONTEXT);
      if (invCtx == null) throw new RuntimeException("Unable to obtain invocation context");
      Map<String, Object> wsrmReqCtx = (Map<String, Object>)invCtx.get(RMConstant.REQUEST_CONTEXT);
      Boolean isOneWay = (Boolean)wsrmReqCtx.get(ONE_WAY_OPERATION); 
      return isOneWay == null ? false : isOneWay.booleanValue();
   }

}