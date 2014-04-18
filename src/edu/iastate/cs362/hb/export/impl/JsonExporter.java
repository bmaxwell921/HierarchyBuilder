package edu.iastate.cs362.hb.export.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.io.DesignDocBox;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.Pair;

public class JsonExporter implements IExporter {

	@Override
	public boolean doExport(String path, IDesignDoc doc) {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final DesignDocBox ddBox = new DesignDocBox();
		ddBox.addName(doc.getName());
		// Get all of the classes and interfaces
		doc.traverse(new IHBTreeVisitor() {
			@Override
			public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
				ddBox.add(o);
			}
		});
		File out = new File(path);
		// Create things as needed
		if (!out.exists()) {
			out.getParentFile().mkdirs();
		}
		try (FileWriter fw = new FileWriter(out)) {
			fw.write(gson.toJson(ddBox));
		} catch (Exception e) {
			// TODO something more sophisticated
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
