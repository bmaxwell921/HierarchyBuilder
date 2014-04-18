package edu.iastate.cs362.hb.importer;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.iastate.cs362.hb.model.IDesignDoc;

public interface IImporter {

	/**
	 * Imports the design doc at the given path
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public boolean doImport(String path) throws FileNotFoundException, IOException;
}
