package edu.iastate.cs362.hb.importer.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.iastate.cs362.hb.exceptions.MalformattedInputException;
import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.io.DesignDocBox;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;

public class XmlImporter implements IImporter {

	public XmlImporter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IDesignDoc doImport(String path) throws IOException,
			MalformattedInputException {

		//
		InputStream fileStream = new FileInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				fileStream));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		String file = sb.toString();
		for (int i = 0; i < file.length(); i++) {
			if (file.charAt(i) == '<') {
				// Bracket
				System.out.println(file.charAt(i));
				String arg = file.substring(i + 1, file.indexOf('>') - 1);
				if (arg.charAt(0) == '?') { // encoding line
					System.out.println(arg);
					i = file.indexOf('>') + 1;
				} else {

					switch (arg.toUpperCase()) {
					case "CLASS":

						break;
					case "INTERFACE":

						break;
					default:
						throw new MalformattedInputException(
								"Unknown argument: " + arg);
					}
					file = file.substring(file.indexOf('>') + 1);
				}
			}
		}

		return null;
	}

	private IDesignDoc fillInDesign(DesignDocBox box) {
		IDesignDoc d = new HBDesignDoc(box.getName());
		d.addAllInner(box.getInterfaces(), box.getClasses());
		return d;

	}

	private IClass buildFromString(String str) {

		return null;

	}

}
