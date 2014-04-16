package edu.iastate.cs362.hb.export.impl;

import java.util.Set;

import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.Pair;

public class XmlExporter implements IExporter {

	@Override
	public boolean doExport(String path, IDesignDoc doc) {
		final StringBuilder sb = new StringBuilder("");
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
		sb.append("<design>\n");
		doc.traverse(new IHBTreeVisitor(){
			@Override
			public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
				if(o.getClass() == IClass.class){
					IClass cl = (IClass) o;
					sb.append("<class id=" + o.getId() + " name=" + o.getName() + " package=" + o.getPackage() + ">");
					for(String mod : o.getModifiers()){
						sb.append("<modifier name=\"" + mod + "\" />");
					}
					sb.append("</class>");
				}
				else{
					sb.append("<interface id=\"" + o.getId() + "\" name=\"" + o.getName() + "\" package=\"" + o.getPackage() + "\" />");
				}
				sb.append("\n");
			}
		});
		sb.append("</design>");
		System.out.println(sb.toString());
		return false;
	}

}
