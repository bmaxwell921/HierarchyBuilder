package test.edu.iastate.cs362.hb.util;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Utility class used for testing the exporters
 * @author Brandon
 *
 */
public class ExportUtil {
	
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
}
