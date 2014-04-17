package test.edu.iastate.cs362.hb.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.Pair;

/**
 * Utility class used for testing the exporters
 * @author Brandon
 *
 */
public class ExportUtil {
	
	// Format strings used for build
	public static final String JSON_FORMAT = "JSON";
	public static final String XML_FORMAT = "XML";
	public static final String SRC_FORMAT = "SOURCE";
	
	/**
	 * Checks whether the src string contains all of the given strings to 
	 * check
	 * @param src
	 * @param check
	 * @return
	 */
	public static boolean containsAll(String src, String... checks) {
		return containsCount(src, checks.length, checks);
	}
	
	/**
	 * Checks whether the src string contains <amt> number of the given check strings
	 * @param src
	 * @param amt
	 * @param checks
	 * @return
	 */
	private static boolean containsCount(String src, int amt, String...checks) {
		int foundAmt = 0;
		for (String check : checks) {
			if (src.contains(check)) {
				++foundAmt;
			}
		}	
		return amt == foundAmt;
	}
	
	/**
	 * Reads the entire file at the given file path and optionally
	 * strips out all whitespace
	 * @param filePath
	 * @param stripWhitespace
	 * @return
	 */
	public static String readAll(String filePath, boolean stripWhitespace) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String read = "";
			StringBuilder sb = new StringBuilder();
			while ((read = br.readLine()) != null) {
				sb.append(read);
			}
			String file = sb.toString();
			return (!stripWhitespace) ? file : file.replaceAll("\\s*", "");
		} catch (Exception e) {
			throw new RuntimeException(String.format("Unable to read file: %s", filePath), e);
		}
	}
	
	/**
	 * Returns the string that is the properly exported string for the given
	 * design doc.
	 * @param d
	 * @param format
	 * 		Either JSON, XML, or SOURCE 
	 * @return
	 */
	public static String buildProperExportString(IDesignDoc d, String format) {
		throw new RuntimeException("Unimplemented");
//		format = format.toUpperCase();
//		if (format.equals(JSON_FORMAT)) {
//			return buildJsonExportString(d);
//		} else if (format.equals(XML_FORMAT)) {
//			
//		} else if (format.equals(SRC_FORMAT)) {
//			
//		}
//		
//		throw new RuntimeException("Unknown export format: " + format);
	}
	
	private static String buildJsonExportString(IDesignDoc d) {
		// TODO shelved. Idk if there's any point since I'm basically just testing
		// the Gson library
		throw new RuntimeException("Unimplemented");
//		StringBuilder sb = new StringBuilder();
//		String output = ""
//				+ "{"
//				+ "\"name\":\"%s\","
//				+ "\"interfaces\":%s,"
//				+ "\"classes\":%s"
//				+ "}";
//		
//		final List<IObject> classes = new ArrayList<>();
//		final List<IObject> interf = new ArrayList<>();
//		
//		getAllObjs(d, classes, interf);
//		
//		return "";
	}
	
	// Puts all of the interfaces and classes into the given lists
	private static void getAllObjs(IDesignDoc d, final List<IObject> classes, final List<IObject> interf) {
		// TODO shelved. Idk if there's any point since I'm basically just testing
		// the Gson library
		d.traverse(new IHBTreeVisitor() {
			@Override
			public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
				if (o.getClass() == HBClass.class) {
					classes.add(o);
				} else {
					interf.add(o);
				}
			}
		});
	}
	
	private static String buildJsonClassesString(final List<IObject> classes) {
		// TODO shelved. Idk if there's any point since I'm basically just testing
		// the Gson library
		throw new RuntimeException("Unimplemented");
//		String classFormatStr = ""
//				+ "{"
//				+ "\"fields\":%s" // Build a fields string as well
//				+ "\"id\":%d"
//				+ "\"pkg\":%s"
//				+ "\"name\":%s"
//				+ "\"modifiers\":" // Build a modifiers string
//				+ "\"methods\":%s"; // Build a methods string
//		String output = "[" + classFormatStr + "]";
//		for (int i = 0; i < classes.size(); ++i) {
//			
//		}
//		
//		return "";
	}
}
