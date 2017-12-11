package fr.alma2017.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.IObserver;
import fr.alma2017.api.client.IClient;
import fr.alma2017.clientServer.Main;

public class ProxyClient implements InvocationHandler {

	private Object target;
	private List<IObserver> observer;

	public ProxyClient(Object target) {
		this.target = target;
		this.observer = new ArrayList<IObserver>();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret;
		if(method.getName().equals("addObserver")){
			ret = Void.TYPE;
			this.observer.add( (IObserver) args[0] );
		}else if(method.getName().substring(0, 3).equals("set") && this.observer != null){
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println(target.getClass().getName() + " ["+ method.getName().substring(3) + "=" + args[0] + "] is modified");
			}
		}else if(method.getName().equals("sendMessage") && this.observer != null){
			List<String> message = ((IClient)target).makeMessage();
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println("\tProxy Client : " + this.target.getClass().getName() + " est observee par " + this.observer.size() + " objets.");
			}
			for(IObserver observer : this.observer) {
				observer.notify( message );
			}
		}else{
			if(Main.Sysout) {
				System.out.println("\tProxy Client :  call " + method.getName());
			}
			ret = method.invoke(this.target, args);
		}
		return ret;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public List<IObserver> getObserver() {
		return observer;
	}

}
