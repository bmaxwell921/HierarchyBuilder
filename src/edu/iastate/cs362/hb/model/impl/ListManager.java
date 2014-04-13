package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IManager;
import edu.iastate.cs362.hb.model.IObject;

public class ListManager implements IManager{

	private String toPrint;
	
	public ListManager(){
		toPrint = "";
	}
	
	@Override
	public void manage(IObject obj) {
		toPrint += obj.list();
	}
	
	public String getString(){
		return toPrint;
	}

}
