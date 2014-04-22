package edu.iastate.cs362.hb.export.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Set;

import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.IVariable;
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
				addClass(sb, (IClass) o);
			}
			else{
				addInterface(sb, o);
				}
			sb.append("\n");
		}
		
		
		sb.append("</design>");
		System.out.println(sb.toString());
		
		File out = new File(path);
		// Create things as needed

		try (FileWriter fw = new FileWriter(out)) {
			fw.write(sb.toString());
		} catch (Exception e) {
			// TODO something more sophisticated
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private void addMethod(StringBuilder sb, IMethod meth){
		sb.append("<method>");
		sb.append("<id>");
		sb.append(meth.getId());
		sb.append("</id>\n");
		sb.append("<name>");
		sb.append(meth.getName());
		sb.append("</name>\n");
		for(IVariable arg : meth.getArguments()){
			sb.append("<argument>");
			sb.append("<type>");
			sb.append(arg.getType());
			sb.append("</type>\n");
			sb.append("<name>");
			sb.append(arg.getName());
			sb.append("</name>\n");
			sb.append("</argument>\n");
		}
		sb.append("</method>\n");
	}
	
	private void addClass(StringBuilder sb, IClass cl){
		sb.append("<class>");
		sb.append("<id>");
		sb.append(cl.getId());
		sb.append("</id>\n");
		sb.append("<name>");
		sb.append(cl.getName());
		sb.append("</name>\n");
		sb.append("<package>");
		sb.append(cl.getPackage());
		sb.append("</package>\n");
		for(String mod : cl.getModifiers()){
			sb.append("<modifier>");
			sb.append(mod);
			sb.append("</modifier>\n");
		}
		for(IMethod meth : cl.getMethods()){
			addMethod(sb, meth);
		}
		sb.append("</class>\n");
	}
	
	private void addInterface(StringBuilder sb, IObject inter){
		sb.append("<interface>");
		sb.append("<id>");
		sb.append(inter.getId());
		sb.append("</id>\n");
		sb.append("<name>");
		sb.append(inter.getName());
		sb.append("</name>\n");
		sb.append("<package>");
		sb.append(inter.getPackage());
		sb.append("</package>\n");
		sb.append("</interface>\n");
	}
}
