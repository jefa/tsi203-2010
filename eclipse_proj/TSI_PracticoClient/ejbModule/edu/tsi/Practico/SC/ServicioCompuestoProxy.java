package edu.tsi.Practico.SC;

public class ServicioCompuestoProxy implements edu.tsi.Practico.SC.ServicioCompuesto {
  private String _endpoint = null;
  private edu.tsi.Practico.SC.ServicioCompuesto servicioCompuesto = null;
  
  public ServicioCompuestoProxy() {
    _initServicioCompuestoProxy();
  }
  
  public ServicioCompuestoProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioCompuestoProxy();
  }
  
  private void _initServicioCompuestoProxy() {
    try {
      servicioCompuesto = (new edu.tsi.Practico.SC.ServicioCompuestoServiceLocator()).getServicioCompuestoPort();
      if (servicioCompuesto != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicioCompuesto)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicioCompuesto)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicioCompuesto != null)
      ((javax.xml.rpc.Stub)servicioCompuesto)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.tsi.Practico.SC.ServicioCompuesto getServicioCompuesto() {
    if (servicioCompuesto == null)
      _initServicioCompuestoProxy();
    return servicioCompuesto;
  }
  
  public java.lang.String finalizarSesion(java.lang.String SID) throws java.rmi.RemoteException{
    if (servicioCompuesto == null)
      _initServicioCompuestoProxy();
    return servicioCompuesto.finalizarSesion(SID);
  }
  
  public java.lang.String inciarSesion() throws java.rmi.RemoteException{
    if (servicioCompuesto == null)
      _initServicioCompuestoProxy();
    return servicioCompuesto.inciarSesion();
  }
  
  public java.lang.String invocarCombinacionWS1(java.lang.String SID) throws java.rmi.RemoteException{
    if (servicioCompuesto == null)
      _initServicioCompuestoProxy();
    return servicioCompuesto.invocarCombinacionWS1(SID);
  }
  
  public java.lang.String invocarCombinacionWS2(java.lang.String SID) throws java.rmi.RemoteException{
    if (servicioCompuesto == null)
      _initServicioCompuestoProxy();
    return servicioCompuesto.invocarCombinacionWS2(SID);
  }
  
  public java.lang.String invocarCombinacionWS3(java.lang.String SID) throws java.rmi.RemoteException{
    if (servicioCompuesto == null)
      _initServicioCompuestoProxy();
    return servicioCompuesto.invocarCombinacionWS3(SID);
  }
  
  
}