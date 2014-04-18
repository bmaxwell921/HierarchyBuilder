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

	private String name;
	// The interfaces in the design doc
	private Set<HBInterface> interfaces;
	
	private Set<HBClass> classes;
	
	public DesignDocBox() {
		name = "";
		interfaces = new HashSet<>();
		classes = new HashSet<>();
	}
	
	public void addName(String dName) {
		this.name = dName;
	}
	
	public String getName() {
		return this.name;
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
	
	public Set<HBInterface> getInterfaces() {
		return interfaces;
	}
	
	public Set<HBClass> getClasses() {
		return classes;
	}

}
