package edu.iastate.cs362.hb.importer.impl;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.model.IDesignDoc;

/**
 * Importer used to turn source code into a DesignDoc. Turns out there's built
 * in support for this since Java 6 O.O
 * 
 * https://today.java.net/pub/a/today/2008/04/10/source-code-analysis-using-java
 * -6-compiler-apis.html
 * 
 * @author Brandon
 * 
 */
public class SourceImporter implements IImporter {

	@Override
	public IDesignDoc doImport(String path) {
		// Compiler instance
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);

		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjects("./src/edu/iastate/cs362/hb/importer/impl/SourceImporter.java");
		
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
		task.call();
		
		return null;
	}
	
	public static void main(String[] args) {
		new SourceImporter().doImport("");
	}

}
