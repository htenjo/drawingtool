//=======================================================================
// ARCHIVO CommandFillTest.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import co.zero.drawingtool.constant.Constant;
import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.factory.CommandFactory;
import co.zero.drawingtool.model.Canvas;

/**
 * Class that test the {@link CommandFill} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandFillTest {
	private static final String DEFAULT_FILL_COLOR = "C";
	private static final String DEFAULT_COMMAND_PATTERN = "%s %s %d %s";
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillEmptyParameters() {
		String createFillCommand = Constant.COMMAND_FILL_ID;
		
		try{
			CommandFactory.buildCommand(createFillCommand);
			fail("Exception should be raised: Required parameters are empty");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillParametersLessThanRequiredOneParameter() {
		String pattern1 = "%s %d";
		String command1 = String.format(pattern1, Constant.COMMAND_FILL_ID, 1);
		
		try{
			CommandFactory.buildCommand(command1);
			fail("Exception should be raised: Required parameters are less (1) than required (3)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillParametersLessThanRequiredTwoParameters() {
		String pattern2 = "%s %d %d";
		String command2 = String.format(pattern2, Constant.COMMAND_FILL_ID, 1, 1);
		
		try{
			CommandFactory.buildCommand(command2);
			fail("Exception should be raised: Required parameters are less (2) than required (3)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillParametersNotNumbers() {
		String pattern = DEFAULT_COMMAND_PATTERN;
		String command = String.format(pattern, Constant.COMMAND_FILL_ID, "B", 1, DEFAULT_FILL_COLOR);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have letters and numbers are required");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillParametersNotInteger() {
		String pattern = DEFAULT_COMMAND_PATTERN;
		String command = String.format(pattern, Constant.COMMAND_FILL_ID, Long.MAX_VALUE, 1, DEFAULT_FILL_COLOR);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have numbers bigger than integers");
		}catch(DrawingToolException e){
			assertEquals(DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT, e.getErrorCode());
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillParametersNegatives() {
		String pattern = DEFAULT_COMMAND_PATTERN;
		String command = String.format(pattern, Constant.COMMAND_FILL_ID, -1, 1, DEFAULT_FILL_COLOR);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have negative numbers");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_OUT_OF_RANGE);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#CommandFill(java.util.List)}.
	 */
	@Test
	public void testCommandFillParametersOk() {
		String pattern = DEFAULT_COMMAND_PATTERN;
		int x1 = 1, y1 = 1; 
		String commandString = String.format(pattern, Constant.COMMAND_FILL_ID, x1, y1, DEFAULT_FILL_COLOR);
		
		try{
			CommandFill command = (CommandFill)CommandFactory.buildCommand(commandString);
			assertEquals(command.getInitPoint().getX(), x1);
			assertEquals(command.getInitPoint().getY(), y1);
		}catch(DrawingToolException e){
			fail("Exception should not be raised: First point painted as the second one and viceversa");
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#execute(co.zero.drawingtool.model.Canvas)}.
	 */
	@Test
	public void testExecuteCanvasNotCreatedYet() {
		String pattern = DEFAULT_COMMAND_PATTERN;
		int x1 = 1, y1 = 1; 
		String commandString = String.format(pattern, Constant.COMMAND_FILL_ID, x1, y1, DEFAULT_FILL_COLOR);
		
		try{
			CommandFill command = (CommandFill)CommandFactory.buildCommand(commandString);
			command.execute(null);
			fail("Exception should be raised: Canvas not created yet");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.CANVAS_NOT_CREATED_YET);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#execute(co.zero.drawingtool.model.Canvas)}.
	 * @throws DrawingToolException 
	 */
	@Test
	public void testExecuteParametersOutsideOfCanvas() throws DrawingToolException {
		String patternCanvas = CommandCanvas.COMMAND_ID + " 5 5";
		String patternLine1 = CommandFill.COMMAND_ID + " 6 6 C";
		Canvas canvas = null;
		Command command = null;
		
		command = CommandFactory.buildCommand(patternCanvas);
		canvas = command.execute(canvas);
		
		try{
			command = CommandFactory.buildCommand(patternLine1);
			canvas = command.execute(canvas);
			fail("Exception should be raised: Point 1 outside of canvas");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PAINTING_OUTSIDE_CANVAS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandFill#execute(co.zero.drawingtool.model.Canvas)}.
	 * @throws DrawingToolException 
	 */
	@Test
	public void testExecuteOK() throws DrawingToolException {
		String patternCanvas = CommandCanvas.COMMAND_ID + " 5 5";
		String patternLine1 = CommandFill.COMMAND_ID + " 1 1 C";
		Canvas canvas = null;
		Command command = null;
		
		command = CommandFactory.buildCommand(patternCanvas);
		canvas = command.execute(canvas);
		command = CommandFactory.buildCommand(patternLine1);
		canvas = command.execute(canvas);
		
		for(int y=Canvas.CANVAS_BORDER_WITH; y < canvas.getHeight() - Canvas.CANVAS_BORDER_WITH; y++){
			for(int x=Canvas.CANVAS_BORDER_WITH; x < canvas.getWidth() - Canvas.CANVAS_BORDER_WITH; x++){
				assertEquals(DEFAULT_FILL_COLOR, canvas.getPixel(x, y));
			}
		}
	}
}
