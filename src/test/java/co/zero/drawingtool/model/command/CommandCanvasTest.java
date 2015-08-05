//=======================================================================
// ARCHIVO CommandCanvasTest.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import co.zero.drawingtool.constant.Constant;
import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.factory.CommandFactory;
import co.zero.drawingtool.model.Canvas;

/**
 * Class that test the {@link CommandCanvas} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandCanvasTest {
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandCanvas#CommandCanvas(java.util.List)}.
	 */
	@Test
	public void testCommandCanvasEmptyParameters() {
		String createCanvasCommand = Constant.COMMAND_CANVAS_ID;
		
		try{
			CommandFactory.buildCommand(createCanvasCommand);
			fail("Exception should be raised: Required parameters are empty");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandCanvas#CommandCanvas(java.util.List)}.
	 */
	@Test
	public void testCommandCanvasParametersLessThanRequiredOneParameter() {
		String pattern1 = "%s %d";
		String command1 = String.format(pattern1, Constant.COMMAND_CANVAS_ID, 1);
		
		try{
			CommandFactory.buildCommand(command1);
			fail("Exception should be raised: Required parameters are less (1) than required (2)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandCanvas#CommandCanvas(java.util.List)}.
	 */
	@Test
	public void testCommandCanvasParametersNotNumbers() {
		String pattern = "%s %s %d";
		String command = String.format(pattern, Constant.COMMAND_CANVAS_ID, "L", 1);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have letters and numbers are required");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandCanvas#CommandCanvas(java.util.List)}.
	 */
	@Test
	public void testCommandCanvasParametersNotInteger() {
		String pattern = "%s %d %d";
		String command = String.format(pattern, Constant.COMMAND_CANVAS_ID, Long.MAX_VALUE, 1);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have numbers bigger than integers");
		}catch(DrawingToolException e){
			assertEquals(DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT, e.getErrorCode());
		}
	}

	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandCanvas#CommandCanvas(java.util.List)}.
	 */
	@Test
	public void testCommandCanvasParametersOk() {
		String pattern = "%s %d %d";
		int width = 5, height = 5; 
		String commandString = String.format(pattern, Constant.COMMAND_CANVAS_ID, width, height);
		
		try{
			CommandCanvas command = (CommandCanvas)CommandFactory.buildCommand(commandString);
			Canvas canvas = command.execute(null);
			assertNotNull(canvas);
			
			assertEquals(canvas.getWidth(), width + Canvas.CANVAS_BORDER_WITH * 2);
			assertEquals(canvas.getHeight(), height + Canvas.CANVAS_BORDER_WITH * 2);
		}catch(DrawingToolException e){
			fail("Exception should not be raised: First point painted as the second one and viceversa");
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandCanvas#execute(co.zero.drawingtool.model.Canvas)}.
	 */
	@Test
	public void testExecuteCanvasInvalidSize() {
		String commandString = CommandCanvas.COMMAND_ID + " 0 0";
		Canvas canvas = null;
		
		try{
			
			Command command = CommandFactory.buildCommand(commandString);
			command.execute(canvas);
			fail("Exception should be raised: Canvas invalid size");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.CANVAS_SIZE_INVALID);
		}
	}
}
