package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.IVariable;
import edu.iastate.cs362.hb.model.tree.Pair;


/**
 * Class representing an interface. Do we even need it if it's empty?? I guess it's nice to have
 * @author Brandon
 *
 */
public class HBInterface extends AHBObject{
	
	public HBInterface(String name) {
		super(name);
	}
	
	// TODO add method should check that it's not an instance method
	
	@Override
	public String list(){
		String toRet = "";
		List<String> mods = new ArrayList<>(this.getModifiers());
		Collections.sort(mods);
		for(String mod: mods){
			toRet += mod + " ";
		}
		toRet += this.getPackage() + "." + this.getName() + "\n" + "Methods: ";
		if(this.getNumMethods() == 0){
			toRet += "(none)";
		}
		toRet += "\n";
		for(int i = 0; i < this.getNumMethods(); i++)
		{
			toRet += " " + methods.get(i).list();
			toRet += "\n";
		}
		toRet += "Relationships: ";
		Set<Pair<IRelationship, IObject>> relations = this.getRelationships();
		if(relations.isEmpty()){
			toRet += "(none)";
		}
		toRet += "\n";
		for(Pair<IRelationship, IObject> pair: relations) {
			toRet += " " + this.getName() + " " + pair.fir.getName() + " " + pair.sec.getName();
			toRet += "\n";
		}
		return toRet;
	}

	@Override
	public boolean changeClassField(long fieldId, String name, String type, Set<String> modifiers) {
		return false;
	}
}
