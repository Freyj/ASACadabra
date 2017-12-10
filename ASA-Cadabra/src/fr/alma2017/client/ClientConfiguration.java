package fr.alma2017.client;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.configuration.IConfiguration;
import fr.alma2017.configurationClass.AConfiguration;

public class ClientConfiguration extends AConfiguration implements IConfiguration{
	
	
	public ClientConfiguration() {
		for(IComposant composant : this.getComposantsInternes()) {
			System.out.println("CSC bind : " + composant.getClass().getName());
			if(composant instanceof IObservable) {
				System.out.println("\t Is IObservable" + composant.getClass().getName());
				this.getInterface().createBinding(this, (IObservable)composant);
			}
		}		
	}

	@Override
	public void notify(Object source) {
		
	}


}
