package edu.iastate.cs362.hb.importer.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.sun.source.tree.ClassTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;

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
				.getJavaFileObjects("./src/blah/SimpleClass.java");
		
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
		LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();
		processors.add(new Processor());
		task.setProcessors(processors);
		task.call();
		
		try {
			fileManager.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return new HBDesignDoc("Design");
	}
	
	public static void main(String[] args) {
		new SourceImporter().doImport("");
	}
	
	@SupportedSourceVersion(SourceVersion.RELEASE_7)
	@SupportedAnnotationTypes("*")
	private class Processor extends AbstractProcessor {
		
		private Trees trees;
		private CodeClassVisitor visitor;
		
		@Override
		public void init(ProcessingEnvironment pe) {
			super.init(pe);
			trees = Trees.instance(pe);
			visitor = new CodeClassVisitor();
		}
		
		@Override
		public boolean process(Set<? extends TypeElement> annotation, RoundEnvironment re) {
			for (Element e : re.getRootElements()) {
				visitor.scan(trees.getPath(e), trees);
			}
			return true;
		}
	}
	
	private class CodeClassVisitor extends TreePathScanner<Object, Trees> {
		
		private Set<IObject> classes;
		private Set<IObject> interfaces;
		
		public CodeClassVisitor() {
			classes = new HashSet<>();
			interfaces = new HashSet<>();
		}
		
		@Override
		public Object visitClass(ClassTree clazzTree, Trees trees) {
			System.out.println(clazzTree.getKind());
			// TODO use getKind to see if it's an interface or class, then fill in the info
			super.visitClass(clazzTree, trees);
			return null;
		}
		
		private void fillInValues(ClassTree clazzTree, Trees trees) {
			TypeElement e = (TypeElement) trees.getElement(getCurrentPath());
		}
	}

}
