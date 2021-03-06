package edu.iastate.cs362.hb.importer.impl;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.importer.creator.MethodCreator;
import edu.iastate.cs362.hb.importer.creator.RelationCreator;
import edu.iastate.cs362.hb.importer.creator.VariableCreator;
import edu.iastate.cs362.hb.io.DesignDocBox;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.IVariable;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;

/**
 * Class used to import design docs in the json format
 * 
 * @author Brandon
 * 
 */
public class JsonImporter implements IImporter {

	@Override
	public IDesignDoc doImport(String path) {
		Gson gson = new GsonBuilder().registerTypeAdapter(IMethod.class, new MethodCreator())
				.registerTypeAdapter(IRelationship.class, new RelationCreator())
				.registerTypeAdapter(IVariable.class, new VariableCreator()).create();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String read = "";

			while ((read = br.readLine()) != null) {
				sb.append(read);
			}

			DesignDocBox box = gson.fromJson(sb.toString(), DesignDocBox.class);
			return fillInDesign(box);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to open saved file: " + path, e);
		}
	}

	private IDesignDoc fillInDesign(DesignDocBox box) {
		IDesignDoc d = new HBDesignDoc(box.getName());
		d.addAllInner(box.getInterfaces(), box.getClasses());
		return d;
	}
}
