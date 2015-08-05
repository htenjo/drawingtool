//=======================================================================
// ARCHIVO MainTest.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import co.zero.drawingtool.exception.DrawingToolException;

/**
 * Class that test the {@link Main} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class MainTest {
	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testMainEmptyParameters() throws FileNotFoundException, IOException {
		String[] params = new String[]{};
		
		try{
			Main.main(params);
			fail("Exception should be raised: Required parameters are empty");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testMainEmptyMissingParams() throws FileNotFoundException, IOException {
		String[] params = new String[]{"src/test/resources/test.txt"};
		
		try{
			Main.main(params);
			fail("Exception should be raised: Output file parameters is lost");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testMainWithOKCommands() throws FileNotFoundException, IOException {
		String[] params = new String[]{"src/test/resources/test.txt", "src/test/resources/outputWithErrorsOk.txt"};
		testMainOK(params);
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testMainWithBadCommands() throws FileNotFoundException, IOException {
		String[] params = new String[]{"src/test/resources/testWithErrors.txt", "src/test/resources/outputWithErrorsBad.txt"};
		testMainOK(params);
	}
	
	/**
	 * @param params
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void testMainOK(String[] params) throws FileNotFoundException, IOException {
		File file = new File(params[1]);
		
		try{
			Main.main(params);
			assertTrue(file.isFile() && file.exists());
		}catch(DrawingToolException e){
			fail("Exception should not be raised: All is ok");
		}finally{
			file.delete();
		}
	}
}
