package edu.iastate.cs362.hb.export;

import edu.iastate.cs362.hb.model.IDesignDoc;

public interface IExport {
	/**
	 * Exports the specific design to the implemented type
	 * @param path
	 * 		Path to the file to export
	 * @param doc
	 * 		Design doc we are exporting
	 * @return
	 * 		True if successful, false otherwise
	 */
	public boolean export(String path, IDesignDoc doc);
}
