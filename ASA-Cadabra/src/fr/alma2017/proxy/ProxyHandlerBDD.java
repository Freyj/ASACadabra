package fr.alma2017.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import fr.alma2017.api.IObservable;


public class ProxyHandlerBDD implements InvocationHandler{


	private Object target;
	private IObservable observer;

	public ProxyHandlerBDD(Object target) {
		this.target = target;
		this.observer = null;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret;
		if(method.getName().equals("setObserver")){
			//Ne fonctionne pas : java.lang.IllegalArgumentException: object is not an instance of declaring class
			//ret = method.invoke(this.target, args);

			//Fonctionne correctement
			ret = Void.TYPE;
			this.observer = (IObservable) args[0];
		}else if(method.getName().substring(0, 3).equals("set") && this.observer != null){
			ret = method.invoke(this.target, args);
			//this.observer.notify(this.target);
			System.out.println(target.getClass().getName() + " ["+ method.getName().substring(3) + "=" + args[0] + "] is modified");
		}else{
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

}
