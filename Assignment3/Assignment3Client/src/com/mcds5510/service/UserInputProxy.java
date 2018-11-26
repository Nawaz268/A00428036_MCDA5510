package com.mcds5510.service;

public class UserInputProxy implements com.mcds5510.service.UserInput {
  private String _endpoint = null;
  private com.mcds5510.service.UserInput userInput = null;
  
  public UserInputProxy() {
    _initUserInputProxy();
  }
  
  public UserInputProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserInputProxy();
  }
  
  private void _initUserInputProxy() {
    try {
      userInput = (new com.mcds5510.service.UserInputServiceLocator()).getUserInput();
      if (userInput != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userInput)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userInput)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userInput != null)
      ((javax.xml.rpc.Stub)userInput)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mcds5510.service.UserInput getUserInput() {
    if (userInput == null)
      _initUserInputProxy();
    return userInput;
  }
  
  public java.lang.String updateEntry(java.lang.String ID, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String month, java.lang.String year) throws java.rmi.RemoteException{
    if (userInput == null)
      _initUserInputProxy();
    return userInput.updateEntry(ID, nameOnCard, cardNumber, unitPrice, quantity, month, year);
  }
  
  public java.lang.String createEntry(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, double unitPrice, int quantity, int month, int year) throws java.rmi.RemoteException{
    if (userInput == null)
      _initUserInputProxy();
    return userInput.createEntry(ID, nameOnCard, cardNumber, unitPrice, quantity, month, year);
  }
  
  public java.lang.String getEntry(int ID) throws java.rmi.RemoteException{
    if (userInput == null)
      _initUserInputProxy();
    return userInput.getEntry(ID);
  }
  
  public java.lang.String deleteEntry(int ID) throws java.rmi.RemoteException{
    if (userInput == null)
      _initUserInputProxy();
    return userInput.deleteEntry(ID);
  }
  
  
}