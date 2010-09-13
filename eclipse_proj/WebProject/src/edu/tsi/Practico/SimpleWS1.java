package edu.tsi.Practico;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "SimpleWS1Service")
public class SimpleWS1 {

   @WebMethod
   public String invoke( @WebParam(name = "param")
   String param )
   {
      return "Hola " + param + "!";
   }
}