/**
 * ServicioCompuesto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.tsi.Practico.SC;

public interface ServicioCompuesto extends java.rmi.Remote {
    public java.lang.String finalizarSesion(java.lang.String SID) throws java.rmi.RemoteException;
    public java.lang.String inciarSesion() throws java.rmi.RemoteException;
    public java.lang.String invocarCombinacionWS1(java.lang.String SID) throws java.rmi.RemoteException;
    public java.lang.String invocarCombinacionWS2(java.lang.String SID) throws java.rmi.RemoteException;
    public java.lang.String invocarCombinacionWS3(java.lang.String SID) throws java.rmi.RemoteException;
}
