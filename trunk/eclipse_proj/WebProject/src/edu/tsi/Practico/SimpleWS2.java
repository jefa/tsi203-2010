package edu.tsi.Practico;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "SimpleWS2Service")
public class SimpleWS2 {

   @WebMethod
   public String invoke( @WebParam(name = "param")
   String param )
   {
      return "SimpleWS2: " + param;
   }
}