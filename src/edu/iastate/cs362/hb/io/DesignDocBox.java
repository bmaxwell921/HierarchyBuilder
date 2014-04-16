package edu.iastate.cs362.hb.io;

import java.util.HashSet;
import java.util.Set;

import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.impl.HBInterface;

/**
 * Class used to box up information about a design doc for
 * serialization and deserialization
 * @author Brandon
 *
 */
public class DesignDocBox {

	private String designDocName;
	// The interfaces in the design doc
	private Set<HBInterface> interfaces;
	
	private Set<HBClass> classes;
	
	public DesignDocBox() {
		designDocName = "";
		interfaces = new HashSet<>();
		classes = new HashSet<>();
	}
	
	public void addName(String dName) {
		this.designDocName = dName;
	}
	
	public void add(IObject obj) {
		if (obj.getClass() == HBClass.class) {
			addClass(obj);
			return;
		}
		addInterface(obj);
	}
	
	private void addClass(IObject clazz) {
		classes.add((HBClass)clazz);
	}
	
	private void addInterface(IObject inter) {
		interfaces.add((HBInterface)inter);
	}

}
