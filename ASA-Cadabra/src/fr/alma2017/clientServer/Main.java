package fr.alma2017.clientServer;

import java.lang.reflect.Proxy;

import fr.alma2017.api.IObservable;
import fr.alma2017.proxy.ProxyHandler;

/**
 * Lancement d'application
 * 
 *
 */
public class Main {
	
	private static ClientServerConfiguration clientServeurConfig;
	
	
	private static Class<?>[] concat(Class<?>[] interfaces, Class<?> class1) {
		Class<?>[] res;
		res = new Class<?>[interfaces.length + 1];
		for(int i = 0; i < res.length; ++i){
			if(i < interfaces.length){
				res[i] = interfaces[i];
			}else{
				res[i] = class1;
			}
		}
		return res;
	}
	


	private static Object getProxyFor(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				concat(target.getClass().getInterfaces(), IObservable.class),
				new ProxyHandler(target));
	}
	
	public static void main(String[] args) {
		clientServeurConfig = new ClientServerConfiguration();
		
	}

}
