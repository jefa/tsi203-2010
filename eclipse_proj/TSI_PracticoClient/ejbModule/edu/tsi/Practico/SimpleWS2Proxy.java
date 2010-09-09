package edu.tsi.Practico;

public class SimpleWS2Proxy implements edu.tsi.Practico.SimpleWS2 {
  private String _endpoint = null;
  private edu.tsi.Practico.SimpleWS2 simpleWS2 = null;
  
  public SimpleWS2Proxy() {
    _initSimpleWS2Proxy();
  }
  
  public SimpleWS2Proxy(String endpoint) {
    _endpoint = endpoint;
    _initSimpleWS2Proxy();
  }
  
  private void _initSimpleWS2Proxy() {
    try {
      simpleWS2 = (new edu.tsi.Practico.SimpleWS2ServiceLocator()).getSimpleWS2();
      if (simpleWS2 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)simpleWS2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)simpleWS2)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (simpleWS2 != null)
      ((javax.xml.rpc.Stub)simpleWS2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.tsi.Practico.SimpleWS2 getSimpleWS2() {
    if (simpleWS2 == null)
      _initSimpleWS2Proxy();
    return simpleWS2;
  }
  
  public java.lang.String invoke(java.lang.String param) throws java.rmi.RemoteException{
    if (simpleWS2 == null)
      _initSimpleWS2Proxy();
    return simpleWS2.invoke(param);
  }
  
  
}