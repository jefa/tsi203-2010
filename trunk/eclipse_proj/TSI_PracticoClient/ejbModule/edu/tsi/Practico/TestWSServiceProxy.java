package edu.tsi.Practico;

public class TestWSServiceProxy implements edu.tsi.Practico.TestWSService_PortType {
  private String _endpoint = null;
  private edu.tsi.Practico.TestWSService_PortType testWSService_PortType = null;
  
  public TestWSServiceProxy() {
    _initTestWSServiceProxy();
  }
  
  public TestWSServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initTestWSServiceProxy();
  }
  
  private void _initTestWSServiceProxy() {
    try {
      testWSService_PortType = (new edu.tsi.Practico.TestWSService_ServiceLocator()).getTestWSServicePort();
      if (testWSService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)testWSService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)testWSService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (testWSService_PortType != null)
      ((javax.xml.rpc.Stub)testWSService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.tsi.Practico.TestWSService_PortType getTestWSService_PortType() {
    if (testWSService_PortType == null)
      _initTestWSServiceProxy();
    return testWSService_PortType;
  }
  
  public java.lang.String greet(java.lang.String name) throws java.rmi.RemoteException{
    if (testWSService_PortType == null)
      _initTestWSServiceProxy();
    return testWSService_PortType.greet(name);
  }
  
  
}