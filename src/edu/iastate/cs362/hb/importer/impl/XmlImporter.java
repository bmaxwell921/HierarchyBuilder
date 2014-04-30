package edu.iastate.cs362.hb.importer.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.MalformattedInputException;
import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.io.DesignDocBox;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.impl.HBInterface;
import edu.iastate.cs362.hb.model.impl.HBMethod;

public class XmlImporter implements IImporter {

	public XmlImporter() {
		// TODO Auto-generated constructor stub
	}

	private DesignDocBox box;

	@Override
	public IDesignDoc doImport(String path) throws IOException,
			MalformattedInputException {
		box = new DesignDocBox();
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

		ArrayList<IClass> classList = new ArrayList<IClass>();
		String file = sb.toString();
		for (int i = 0; i < file.length(); i++) {
			System.out.println(file.charAt(i));
			if (file.charAt(i) == '<') {
				// Bracket

				String arg = file.substring(i + 1, file.indexOf('>', i + 1));
				if (arg.charAt(0) == '?') { // encoding line
					System.out.println(arg);
					i = file.indexOf('>', i + 1);
				} else {
					try {
						System.out.println(file.substring(i));
						switch (arg.toUpperCase()) {
						case "DESIGN":
							// Entire item is in design
							i += 6;
							break;
						case "CLASS":
							int start = i + "class".length() + 2;
							int end = file.indexOf("</class>", i);
							classList.add(buildClassFromString(file.substring(
									start, end)));
							i = end + 7;
							break;
						case "INTERFACE":
							classList.add(buildClassFromString(file.substring(
									file.indexOf('>', i + 1) + 1,
									file.indexOf("</interface>"))));
							i = file.indexOf("</interface>")
									+ "</interface>".length() - 1;
							break;
						case "PACKAGE":
							i = file.indexOf("</package>")
									+ "</package>".length() - 1;
							break;
						case "/DESIGN":
							i = file.length();
							break;
						default:
							System.out.println("Unknown argument: " + arg);
						}
					} catch (Exception e) {
						System.out.println(arg);
						System.out.println(e.getMessage());
						if(i < 0){
							i = file.length();
						}
					}
				}
			}
		}
		return fillInDesign(box);
	}

	private IDesignDoc fillInDesign(DesignDocBox box) {
		IDesignDoc d = new HBDesignDoc(box.getName());
		d.addAllInner(box.getInterfaces(), box.getClasses());
		return d;

	}

	private IClass buildClassFromString(String str) throws MalformattedInputException {
		
		System.out.println("Building Class");
		

			IClass clazz = new HBClass(getName(str));
			clazz.addPackage(getPackage(str));
			clazz.setId(getID(str));
			getMethods(str, clazz);

			box.add(clazz);
			return clazz;
		
	}

	private void getMethods(String classString, IClass clazz) throws MalformattedInputException {
		if (classString.contains("<method>")) {
			for (int i = 0; i < classString.length(); i++) {
				int startIndex = classString.indexOf("<method>", i) + 8;
				int endIndex = classString.indexOf("</method>", i);
				i = endIndex + "</method".length();
				buildMethod(classString.substring(startIndex, endIndex), clazz);
			}
		}
	}

	private void buildMethod(String methodString, IClass clazz) throws MalformattedInputException {
		IMethod meth = new HBMethod(getName(methodString));
		meth.setId(getID(methodString));
		meth.addReturnType(getReturn(methodString));
		if (methodString.contains("<argument>")) {
			for (int i = 0; i < methodString.length(); i++) {
				int startIndex = methodString.indexOf("<argument>", i) + 10;
				int endIndex = methodString.indexOf("</argument>", i);
				i = endIndex + "</argument".length();
				meth.addArguments(buildArg(methodString.substring(startIndex, endIndex)));
			}
		}
		try {
			clazz.addMethod(meth);
		} catch (HBDuplicateMethodException e) {
			throw new MalformattedInputException("Duplicate methods found");
		}
	}
	
	private String buildArg(String argString){
		return getType(argString) + ":" + getName(argString);
	}
	
	private String getType(String str){
		return getTag("type", str, 0);
	}

	private long getID(String str) {
		return Long.parseLong(getTag("id", str, 0));
	}

	private String getPackage(String str) {
		return getTag("package", str, 0);
	}

	private String getName(String str) {
		return getTag("name", str, 0);
	}
	private String getReturn(String str){
		return getTag("return", str, 0);
	}
	
	private String getTag(String tag, String str, int startLoc) {
		str = str.toUpperCase();
		tag = tag.toUpperCase();
		String startTag = '<' + tag + '>';
		String endTag = "</" + tag + '>';
		int startIndex = str.indexOf(startTag, startLoc) + startTag.length();
		int endIndex = str.indexOf(endTag, startLoc);
		return str.substring(startIndex, endIndex);
	}

}
