package test.edu.iastate.cs362.hb.export.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.export.impl.JsonExporter;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;

/**
 * Class used to test to JsonExporter
 * @author Brandon
 *
 */
public class JsonExporterTest {

	// Test vars
	public static final String DESIGN_NAME = "Design";
	
	private JsonExporter test;
	
	@Before
	public void startUp() {
		test = new JsonExporter();
	}
	
	@Test
	public void testExportEmpty() {
		IDesignDoc testDoc = new HBDesignDoc(DESIGN_NAME);
		
	}
	
	@After
	public void tearDown() {
		test = null;
	}
}
