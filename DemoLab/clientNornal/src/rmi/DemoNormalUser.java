package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.management.BadAttributeValueExpException;

public class DemoNormalUser {
	public static void main(String[] args)
			throws Exception {
		String serverIP = "192.168.14.155";
		int serverPort = 1099;
		String name = "baonguyen2-innocent";
		Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
	      DemoInterf demoInterf = (DemoInterf)registry.lookup("demo");
	      System.out.println(demoInterf.sayHello(name));
	      User user = new User();
	      user.setName(name);
	      user.setAdmin(true);	      
	      System.out.println(demoInterf.login(user));

		}
	  
}