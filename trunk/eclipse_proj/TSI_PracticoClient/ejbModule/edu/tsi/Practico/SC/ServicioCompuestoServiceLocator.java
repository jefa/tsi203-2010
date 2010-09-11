/**
 * ServicioCompuestoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.tsi.Practico.SC;

public class ServicioCompuestoServiceLocator extends org.apache.axis.client.Service implements edu.tsi.Practico.SC.ServicioCompuestoService {

    public ServicioCompuestoServiceLocator() {
    }


    public ServicioCompuestoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioCompuestoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicioCompuestoPort
    private java.lang.String ServicioCompuestoPort_address = "http://localhost:8080/WebProject/ServicioCompuesto";

    public java.lang.String getServicioCompuestoPortAddress() {
        return ServicioCompuestoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicioCompuestoPortWSDDServiceName = "ServicioCompuestoPort";

    public java.lang.String getServicioCompuestoPortWSDDServiceName() {
        return ServicioCompuestoPortWSDDServiceName;
    }

    public void setServicioCompuestoPortWSDDServiceName(java.lang.String name) {
        ServicioCompuestoPortWSDDServiceName = name;
    }

    public edu.tsi.Practico.SC.ServicioCompuesto getServicioCompuestoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicioCompuestoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicioCompuestoPort(endpoint);
    }

    public edu.tsi.Practico.SC.ServicioCompuesto getServicioCompuestoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            edu.tsi.Practico.SC.ServicioCompuestoBindingStub _stub = new edu.tsi.Practico.SC.ServicioCompuestoBindingStub(portAddress, this);
            _stub.setPortName(getServicioCompuestoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicioCompuestoPortEndpointAddress(java.lang.String address) {
        ServicioCompuestoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (edu.tsi.Practico.SC.ServicioCompuesto.class.isAssignableFrom(serviceEndpointInterface)) {
                edu.tsi.Practico.SC.ServicioCompuestoBindingStub _stub = new edu.tsi.Practico.SC.ServicioCompuestoBindingStub(new java.net.URL(ServicioCompuestoPort_address), this);
                _stub.setPortName(getServicioCompuestoPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServicioCompuestoPort".equals(inputPortName)) {
            return getServicioCompuestoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://SC.Practico.tsi.edu/", "ServicioCompuestoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://SC.Practico.tsi.edu/", "ServicioCompuestoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServicioCompuestoPort".equals(portName)) {
            setServicioCompuestoPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
