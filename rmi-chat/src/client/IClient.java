/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author user
 */
public interface IClient extends Remote {
    public void UpdateUserList (List<String> ClientsName) throws RemoteException;
    public void MsgArrived (String msg, String FormUser) throws RemoteException;
}
