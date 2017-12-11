package fr.alma2017.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.alma2017.api.IObserver;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.clientServer.Main;

public class ProxyConnectionManager implements InvocationHandler{

	private Object target;
	private List<IObserver> observer;

	public ProxyConnectionManager(Object target) {
		this.target = target;
		this.observer = new ArrayList<IObserver>();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret;
		if(method.getName().equals("addObserver")){
			ret = Void.TYPE;
			this.observer.add( (IObserver) args[0] );
		}
		else if (method.getName().equals("requestConnection") && this.observer != null) {
			ret = method.invoke(this.target, args);
			if(Main.Sysout) {
				System.out.println("\tProxy ConnectionManager : " + this.target.getClass().getName() + " est observe par " + this.observer.size() + " objets.");
			}
			for(IObserver observer : this.observer) {
				if (args[0] instanceof List<?>) {					
					List<Object> sourceList = new ArrayList<Object>();
					sourceList.add(IConnectionManager.class);
					sourceList.addAll((List<Object>) args[0]);
					observer.notify(sourceList);
				}
			}
		}	
		else if(method.getName().substring(0, 3).equals("set") && this.observer != null){
			ret = method.invoke(this.target, args);
			System.out.println(target.getClass().getName() + " ["+ method.getName().substring(3) + "=" + args[0] + "] is modified");
		}else{
			if(Main.Sysout) {
				System.out.println("\tProxy configurationServer :  call " + method.getName());
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
