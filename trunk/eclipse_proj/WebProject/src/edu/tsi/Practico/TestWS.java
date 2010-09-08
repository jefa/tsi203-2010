package edu.tsi.Practico;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "TestWSService")
public class TestWS {

   @WebMethod
   public String greet( @WebParam(name = "name")
   String name )
   {
      return "Hello alsdjfaslfa;ldjfal " + name;
   }
}