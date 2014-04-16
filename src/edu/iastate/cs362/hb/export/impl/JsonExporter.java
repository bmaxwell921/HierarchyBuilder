package edu.iastate.cs362.hb.export.impl;

import java.util.Set;

import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.Pair;

import com.google.gson.Gson;

public class JsonExporter implements IExporter {

	@Override
	public boolean doExport(String path, IDesignDoc doc) {
		
		final Gson gson = new Gson();
		final StringBuilder sb = new StringBuilder("");
		doc.traverse(new IHBTreeVisitor(){
			@Override
			public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
				sb.append(gson.toJson(o.getName()));

			}
		});
		System.out.println(sb.toString());
		
		
		return false;
	}

}
