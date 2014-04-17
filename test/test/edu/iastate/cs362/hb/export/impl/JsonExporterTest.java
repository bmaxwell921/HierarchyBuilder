package test.edu.iastate.cs362.hb.export.impl;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.edu.iastate.cs362.hb.util.ExportUtil;
import edu.iastate.cs362.hb.export.impl.JsonExporter;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;

/**
 * Class used to test to JsonExporter
 * 
 * @author Brandon
 *
 */
public class JsonExporterTest {

	// Test vars
	public static final String DESIGN_NAME = "Design";
	public static final String FILE_PATH = "." + File.separatorChar + "test"
			+ File.separatorChar + "exports" + File.separatorChar + "json";

	public static int fileNum = 0;

	private JsonExporter test;

	@Before
	public void startUp() {
		test = new JsonExporter();
	}

	@Test
	public void testExportEmpty() {
		final String file = FILE_PATH + fileNum;

		IDesignDoc testDoc = new HBDesignDoc(DESIGN_NAME);
		test.doExport(file, testDoc);

		String exported = ExportUtil.readAll(file, true);
		String expected = String.format(
				"" + "{" + "\"name\":\"%s\"," + "\"interfaces\": [" + "],"
						+ "\"classes\": [" + "]" + "}", DESIGN_NAME)
				.replaceAll("\\s*", "");

		Assert.assertEquals(
				"JsonExporter should export correctly on empty designs",
				expected, exported);
	}

	@After
	public void tearDown() {
		test = null;
	}
}
