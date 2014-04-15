package edu.iastate.cs362.hb.importer;

import edu.iastate.cs362.hb.model.IDesignDoc;

public interface IImporter {

	/**
	 * Imports the design doc at the given path
	 * @param path
	 * @return
	 */
	public IDesignDoc doImport(String path);
}
