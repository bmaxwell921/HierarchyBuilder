package edu.iastate.cs362.hb.importer.impl;

import com.google.gson.Gson;

import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.model.IDesignDoc;

public class JsonImporter implements IImporter {

	@Override
	public boolean doImport(String path) {
		Gson gson = new Gson();
		
		return false;
	}

}
