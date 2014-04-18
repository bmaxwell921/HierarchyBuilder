package edu.iastate.cs362.hb.importer.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.model.IDesignDoc;

public class XmlImporter implements IImporter {

	public XmlImporter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IDesignDoc doImport(String path) throws IOException {

		//
		InputStream fileStream = new FileInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
		String line;
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
		reader.close();
		
		return null;
	}

}
