/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.IClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public interface IServer extends Remote {
    public boolean RegisterToServer (IClient client, String Name) throws RemoteException;
    public void MsgToServer(String msg, String FromUser, String ToName) throws RemoteException;
    public void LogoutToServer(IClient client, String Name) throws RemoteException;

}
