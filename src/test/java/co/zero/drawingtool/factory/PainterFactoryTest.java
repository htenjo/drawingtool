//=======================================================================
// ARCHIVO PainterFactoryTest.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.painter.ConsolePainter;
import co.zero.drawingtool.model.painter.FilePainter;
import co.zero.drawingtool.model.painter.Painter;

/**
 * Class that test the {@link PainterFactory} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class PainterFactoryTest {
	/**
	 * @throws DrawingToolException
	 */
	@Test
	public void testBuildPainterConsoleOk() throws DrawingToolException {
		Painter painter = PainterFactory.buildPainter(PainterFactory.CONSOLE_PAINTER, (String[])null);
		assertTrue(painter instanceof ConsolePainter);
	}

	/**
	 * @throws DrawingToolException 
	 */
	@Test
	public void testBuildPainterFileMissingParams() throws DrawingToolException {
		try{
			PainterFactory.buildPainter(PainterFactory.FILE_PAINTER, (String[])null);
			fail("Exception should be raised: Empty parameters for painter");
		}catch(DrawingToolException e){
			assertEquals(DrawingToolException.MISSING_REQUIRED_ARGUMENTS, e.getErrorCode());
		}
		
	}
	
	/**
	 * @throws DrawingToolException 
	 */
	@Test
	public void testBuildPainterFileOk() throws DrawingToolException {
		Painter painter = PainterFactory.buildPainter(PainterFactory.FILE_PAINTER, "FileName.txt");
		assertTrue(painter instanceof FilePainter);
	}
	
	/**
	 * 
	 */
	@Test
	public void testBuildPainterError() {
		try{
			PainterFactory.buildPainter(-1, (String[])null);
			fail("Exception should be raised: Invalid painter id");
		}catch(DrawingToolException e){
			assertEquals(DrawingToolException.PAINTER_NOT_FOUND, e.getErrorCode());
		}
	}
}
