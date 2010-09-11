package edu.tsi.Practico.Proxy;

public class SimpleWS1ServiceProxy implements edu.tsi.Practico.Proxy.SimpleWS1Service_PortType {
  private String _endpoint = null;
  private edu.tsi.Practico.Proxy.SimpleWS1Service_PortType simpleWS1Service_PortType = null;
  
  public SimpleWS1ServiceProxy() {
    _initSimpleWS1ServiceProxy();
  }
  
  public SimpleWS1ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSimpleWS1ServiceProxy();
  }
  
  private void _initSimpleWS1ServiceProxy() {
    try {
      simpleWS1Service_PortType = (new edu.tsi.Practico.Proxy.SimpleWS1Service_ServiceLocator()).getSimpleWS1ServicePort();
      if (simpleWS1Service_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)simpleWS1Service_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)simpleWS1Service_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (simpleWS1Service_PortType != null)
      ((javax.xml.rpc.Stub)simpleWS1Service_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.tsi.Practico.Proxy.SimpleWS1Service_PortType getSimpleWS1Service_PortType() {
    if (simpleWS1Service_PortType == null)
      _initSimpleWS1ServiceProxy();
    return simpleWS1Service_PortType;
  }
  
  public java.lang.String invoke(java.lang.String param) throws java.rmi.RemoteException{
    if (simpleWS1Service_PortType == null)
      _initSimpleWS1ServiceProxy();
    return simpleWS1Service_PortType.invoke(param);
  }
  
  
}