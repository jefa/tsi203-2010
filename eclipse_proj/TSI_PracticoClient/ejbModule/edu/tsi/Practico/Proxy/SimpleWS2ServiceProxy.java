package edu.tsi.Practico.Proxy;

public class SimpleWS2ServiceProxy implements edu.tsi.Practico.Proxy.SimpleWS2Service_PortType {
  private String _endpoint = null;
  private edu.tsi.Practico.Proxy.SimpleWS2Service_PortType simpleWS2Service_PortType = null;
  
  public SimpleWS2ServiceProxy() {
    _initSimpleWS2ServiceProxy();
  }
  
  public SimpleWS2ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSimpleWS2ServiceProxy();
  }
  
  private void _initSimpleWS2ServiceProxy() {
    try {
      simpleWS2Service_PortType = (new edu.tsi.Practico.Proxy.SimpleWS2Service_ServiceLocator()).getSimpleWS2ServicePort();
      if (simpleWS2Service_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)simpleWS2Service_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)simpleWS2Service_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (simpleWS2Service_PortType != null)
      ((javax.xml.rpc.Stub)simpleWS2Service_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.tsi.Practico.Proxy.SimpleWS2Service_PortType getSimpleWS2Service_PortType() {
    if (simpleWS2Service_PortType == null)
      _initSimpleWS2ServiceProxy();
    return simpleWS2Service_PortType;
  }
  
  public java.lang.String invoke(java.lang.String param) throws java.rmi.RemoteException{
    if (simpleWS2Service_PortType == null)
      _initSimpleWS2ServiceProxy();
    return simpleWS2Service_PortType.invoke(param);
  }
  
  
}