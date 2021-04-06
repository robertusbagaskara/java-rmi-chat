/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.IClient;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServerObj extends UnicastRemoteObject implements IServer {
    static Registry reg = null;
    public ServerObj() throws RemoteException {
        
    }
    
    public static void main(String[] args) {
        try {
            reg = java.rmi.registry.LocateRegistry.createRegistry(1099);
            Naming.bind("ChatServer", new ServerObj());
            System.out.println("Server is Ready");
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerObj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerObj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void StartServer() {
        main(new String[0]);
    }
    
    public static void StopServer() {
        try {
            reg.unbind("ChatServer");
            UnicastRemoteObject.unexportObject(reg, true);
            System.out.println("Server Stopped");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerObj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List<IClient> clients = new ArrayList<IClient>();
    List<String> clientsName = new ArrayList<String>();

    @Override
    public boolean RegisterToServer(IClient client, String Name) throws RemoteException {
        boolean resultToReturn = false;
        try {
            System.out.println(clientsName.indexOf(Name));
            
            if (clientsName.indexOf(Name) > -1) {
                // user already exist with same name..
            } else {
                clients.add(client);
                clientsName.add(Name);
                resultToReturn = true;
                for(int i=0; i<clients.size(); i++) {
                    try {
                        clients.get(i).UpdateUserList(clientsName);
                    } catch (Exception e) {
                        System.out.println(e);
                        clients.remove(i);
                        clientsName.remove(i);
                    }
                }
            }
        } catch (Exception e) {
        }
        
        return resultToReturn;
    }

    @Override
    public void MsgToServer(String msg, String FromUser, String ToName) throws RemoteException {
        if(ToName.equalsIgnoreCase("All Users")) {
            for(int i=0; i < clients.size(); i++) {
                try {
                    if (clientsName.get(i).equals(FromUser)) {
                        clients.get(i).MsgArrived(msg, "You");
                    } else {
                        clients.get(i).MsgArrived(msg, FromUser);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    clients.remove(i);
                    clientsName.remove(i);
                    
                    for(IClient icl : clients) {
                        icl.UpdateUserList(clientsName);
                    }
                }
            }
        } else {
            int count = 0;
            for(int i=0; i<clientsName.size(); i++) {
                if(clientsName.get(i).equals(ToName)) {
                    count++;
                    try {
                        clients.get(i).MsgArrived(msg, FromUser);
                    } catch (Exception e) {
                        System.out.println(e);
                        clients.remove(i);
                        clientsName.remove(i);
                        
                        for(IClient icl : clients) {
                            icl.UpdateUserList(clientsName);
                        }
                        
                        MsgToServer("user " + ToName + " seems unavailable.", "Server", FromUser);
                        count++;
                    }
                } else if(clientsName.get(i).equals(FromUser)) {
                    count++;
                    try {
                        clients.get(i).MsgArrived(msg, "You");
                    } catch (Exception e) {
                        System.out.println(e);
                        clients.remove(i);
                        clientsName.remove(i);
                        
                        for(IClient icl : clients) {
                            icl.UpdateUserList(clientsName);
                        }
                    }
                }
                
                if(count==2) {
                    break;
                }
            }
        }
    }

    @Override
    public void LogoutToServer(IClient client, String Name) throws RemoteException {
        clients.remove(client);
        clientsName.remove(Name);
        for(IClient icl : clients) {
            icl.UpdateUserList(clientsName);
        }
    }
}
