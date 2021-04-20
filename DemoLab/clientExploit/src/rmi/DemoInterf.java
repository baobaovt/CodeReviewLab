package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DemoInterf extends Remote {
	String sayHello(String var1) throws RemoteException;

	String login(Object var1) throws RemoteException;
}