package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.management.BadAttributeValueExpException;

public class DemoUser {
	public static void main(String[] args)
			throws Exception {
		String serverIP = "127.0.0.1";
		int serverPort = 1099;
		String name = "bao";
		Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
	      DemoInterf demoInterf = (DemoInterf)registry.lookup("demo");
	      
	        System.out.println(demoInterf.sayHello(name));
		}
	  
}