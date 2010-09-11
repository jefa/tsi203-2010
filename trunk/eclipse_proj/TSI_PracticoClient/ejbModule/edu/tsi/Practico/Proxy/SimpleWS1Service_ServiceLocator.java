/**
 * SimpleWS1Service_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.tsi.Practico.Proxy;

public class SimpleWS1Service_ServiceLocator extends org.apache.axis.client.Service implements edu.tsi.Practico.Proxy.SimpleWS1Service_Service {

    public SimpleWS1Service_ServiceLocator() {
    }


    public SimpleWS1Service_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SimpleWS1Service_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SimpleWS1ServicePort
    private java.lang.String SimpleWS1ServicePort_address = "http://localhost:8080/WebProject/SimpleWS1";

    public java.lang.String getSimpleWS1ServicePortAddress() {
        return SimpleWS1ServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SimpleWS1ServicePortWSDDServiceName = "SimpleWS1ServicePort";

    public java.lang.String getSimpleWS1ServicePortWSDDServiceName() {
        return SimpleWS1ServicePortWSDDServiceName;
    }

    public void setSimpleWS1ServicePortWSDDServiceName(java.lang.String name) {
        SimpleWS1ServicePortWSDDServiceName = name;
    }

    public edu.tsi.Practico.Proxy.SimpleWS1Service_PortType getSimpleWS1ServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SimpleWS1ServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSimpleWS1ServicePort(endpoint);
    }

    public edu.tsi.Practico.Proxy.SimpleWS1Service_PortType getSimpleWS1ServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            edu.tsi.Practico.Proxy.SimpleWS1ServiceBindingStub _stub = new edu.tsi.Practico.Proxy.SimpleWS1ServiceBindingStub(portAddress, this);
            _stub.setPortName(getSimpleWS1ServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSimpleWS1ServicePortEndpointAddress(java.lang.String address) {
        SimpleWS1ServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (edu.tsi.Practico.Proxy.SimpleWS1Service_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                edu.tsi.Practico.Proxy.SimpleWS1ServiceBindingStub _stub = new edu.tsi.Practico.Proxy.SimpleWS1ServiceBindingStub(new java.net.URL(SimpleWS1ServicePort_address), this);
                _stub.setPortName(getSimpleWS1ServicePortWSDDServiceName());
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
        if ("SimpleWS1ServicePort".equals(inputPortName)) {
            return getSimpleWS1ServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://Practico.tsi.edu/", "SimpleWS1Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://Practico.tsi.edu/", "SimpleWS1ServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SimpleWS1ServicePort".equals(portName)) {
            setSimpleWS1ServicePortEndpointAddress(address);
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
