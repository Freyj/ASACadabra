package fr.alma2017.composantClass;

import java.util.List;
import fr.alma2017.api.composant.IInterfaceComposantFournie;

public class InterfaceComposantFournie implements IInterfaceComposantFournie {
	private List<Class<?>> portFournis;
	private List<Class<?>> serviceFournis;

	public InterfaceComposantFournie(List<Class<?>> portFournis, List<Class<?>> serviceFournis) {
		super();
		this.portFournis = portFournis;
		this.serviceFournis = serviceFournis;
	}
	
	@Override
	public List<Class<?>> getServiceFournis() {
		return this.serviceFournis;
	}
	
	@Override
	public List<Class<?>> getPortFournis() {
		return this.portFournis;
	}
	
}
