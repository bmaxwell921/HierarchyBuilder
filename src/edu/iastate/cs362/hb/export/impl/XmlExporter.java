package edu.iastate.cs362.hb.export.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Set;

import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.model.IArgument;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.Pair;

public class XmlExporter implements IExporter {

	@Override
	public boolean doExport(String path, IDesignDoc doc) {
		final StringBuilder sb = new StringBuilder("");
		final ArrayList<IObject> list = new ArrayList<IObject>();
		;
		
		doc.traverse(new IHBTreeVisitor(){
			@Override
			public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
				list.add(o);
			}
		});
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
		sb.append("<design>\n");
		for(IObject o : list){
			if(o.getClass() == HBClass.class){
				IClass cl = (IClass) o;
				sb.append("<class id=" + o.getId() + " name=" + o.getName() + " package=" + o.getPackage() + ">");
				for(String mod : o.getModifiers()){
					sb.append("<modifier name=\"" + mod + "\" />");
				}
				for(IMethod meth : cl.getMethods()){
					sb.append("<method id=\"" + meth.getId() + "\" name=\"" + meth.getName() + "\">");
					for(IArgument arg : meth.getArguments()){
						sb.append("<argument -name=\"" + arg.getName() + "\" -type=\"" + arg.getType() + "\"/>");
					}
					sb.append("</method>");
				}
				sb.append("</class>");
			}
			else{
				sb.append("<interface id=\"" + o.getId() + "\" name=\"" + o.getName() + "\" package=\"" + o.getPackage() + "\" />");
			}
			sb.append("\n");
		}
		
		
		sb.append("</design>");
		System.out.println(sb.toString());
		
		File out = new File(path);
		// Create things as needed
		if (!out.exists()) {
			System.out.println(out.getParentFile().mkdirs());
		}
		try (FileWriter fw = new FileWriter(out)) {
			fw.write(sb.toString());
		} catch (Exception e) {
			// TODO something more sophisticated
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
