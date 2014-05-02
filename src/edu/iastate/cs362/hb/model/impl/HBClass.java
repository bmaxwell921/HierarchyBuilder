package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.main.IdManager;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.IVariable;
import edu.iastate.cs362.hb.model.tree.Pair;

/**
 * Class representing a Class in the Hierarchy
 * @author Brandon
 *
 */
public class HBClass extends AHBObject implements IClass{
	
	private List<IVariable> fields;
	public HBClass(String name){
		super(name);
		fields = new ArrayList<>();
	}

	@Override
	public boolean addInstanceField(IVariable i) {
		IdManager.getInstance().registerObject(i, i.getName());
		return fields.add(i);
	}	
	
	@Override
	public String list(){
		String toRet = "";
		List<String> mods = new ArrayList<>(this.getModifiers());
		Collections.sort(mods);
		for(String mod : mods){
			toRet += mod + " ";
		}
		toRet += this.getPackage() + "." + this.getName() + "\n" + "Fields: ";
		if(fields.size() == 0){
			toRet += "(none)";
		}
		toRet += "\n";
		for(int i = 0; i < fields.size(); i++){
			toRet += " " + fields.get(i).list();
			toRet += "\n";
		}
		toRet += "Methods: ";
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
	public List<IMethod> getMethods() {
		return methods;
	}
	
	@Override
	public boolean changeClassField(long fieldId, String fieldName, String type, Set<String> modifiers){
		for(int j = 0; j < fields.size(); j++){
			IVariable toCheck = fields.get(j);
			if(toCheck.getId() == fieldId){
				return toCheck.update(fieldName, type, modifiers);
			}
		}
		return false;
	}

	public boolean removeField(String name) {
		for(int i = 0; i < fields.size(); i++){
			if(fields.get(i).getName().equals(name)){
				fields.remove(i);
				return true;
			}
		}
		return false;
	}
}
