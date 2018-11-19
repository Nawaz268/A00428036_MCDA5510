/**
 * UserInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcds5510.service;

public interface UserInput extends java.rmi.Remote {
    public java.lang.String updateEntry(java.lang.String ID, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String month, java.lang.String year) throws java.rmi.RemoteException;
    public java.lang.String createEntry(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, double unitPrice, int quantity, int month, int year) throws java.rmi.RemoteException;
    public java.lang.String getEntry(int ID) throws java.rmi.RemoteException;
    public java.lang.String deleteEntry(int ID) throws java.rmi.RemoteException;
}
