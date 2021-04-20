package rmi;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

public class DemoInterfImpl extends UnicastRemoteObject implements DemoInterf {
	public DemoInterfImpl() throws RemoteException {
	}

	public String sayHello(String userName) throws RemoteException {
		return String.format("WELCOME %s to DemoGhe !", userName);
	}

	public String login(Object user) throws RemoteException {
		String msg = "";
		User demo_user = (User) user;
		msg = msg + String.format("\nLOGGED IN ! Welcome %s to DemoGhe !", demo_user.getName());
		msg = msg + "\nHave a nice day! Bye Bye";
		return msg;
	}
}