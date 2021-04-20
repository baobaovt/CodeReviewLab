package rmi;

import java.rmi.registry.LocateRegistry;

import java.rmi.registry.Registry;

public class DemoServer {
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname","192.168.14.155");
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("demo", new DemoInterfImpl());
			System.out.println("Demo server is ready");
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}
}