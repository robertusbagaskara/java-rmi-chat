/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author user
 */
public class ClientObj extends UnicastRemoteObject implements IClient {
    
    public ClientObj() throws RemoteException {
        
    }

    @Override
    public void UpdateUserList(List<String> ClientsName) throws RemoteException {
        MainForm._UpdateUserList(ClientsName);
    }

    @Override
    public void MsgArrived(String msg, String FormUser) throws RemoteException {
        MainForm._MsgArrived(msg, FormUser);
    }
    
}
