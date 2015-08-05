//=======================================================================
// ARCHIVO CommandRectangleTest.java
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
 * Class that test the {@link CommandRectangle} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandRectangleTest {
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleEmptyParameters() {
		String createLineCommand = Constant.COMMAND_RECTANGLE_ID;
		
		try{
			CommandFactory.buildCommand(createLineCommand);
			fail("Exception should be raised: Required parameters are empty");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersLessThanRequiredOneParameter() {
		String pattern1 = "%s %d";
		String command1 = String.format(pattern1, Constant.COMMAND_RECTANGLE_ID, 1);
		
		try{
			CommandFactory.buildCommand(command1);
			fail("Exception should be raised: Required parameters are less (1) than required (4)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersLessThanRequiredTwoParameters() {
		String pattern2 = "%s %d %d";
		String command2 = String.format(pattern2, Constant.COMMAND_RECTANGLE_ID, 1, 1);
		
		try{
			CommandFactory.buildCommand(command2);
			fail("Exception should be raised: Required parameters are less (2) than required (4)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersLessThanRequiredThreeParameters() {
		String pattern3 = "%s %d %d %d";
		String command3 = String.format(pattern3, Constant.COMMAND_RECTANGLE_ID, 1, 1, 1);
		
		try{
			CommandFactory.buildCommand(command3);
			fail("Exception should be raised: Required parameters are less (3) than required (4)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersNotNumbers() {
		String pattern = "%s %s %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_RECTANGLE_ID, "L", 1, 1, 2);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have letters and numbers are required");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersNotInteger() {
		String pattern = "%s %d %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_RECTANGLE_ID, Long.MAX_VALUE, 1, 1, 1);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have numbers bigger than integers");
		}catch(DrawingToolException e){
			assertEquals(DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT, e.getErrorCode());
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersNegatives() {
		String pattern = "%s %d %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_RECTANGLE_ID, -1, 1, -1, 1);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have negative numbers");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_OUT_OF_RANGE);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersFirstPointHigherThanSecondPoint() {
		String pattern = "%s %d %d %d %d";
		int x1 = 5, y1 = 5, x2 = 1, y2 = 1; 
		String commandString = String.format(pattern, Constant.COMMAND_RECTANGLE_ID, x1, y1, x2, y2);
		
		try{
			CommandRectangle command = (CommandRectangle)CommandFactory.buildCommand(commandString);
			assertEquals(command.getUpperLeftCorner().getX(), x2);
			assertEquals(command.getUpperLeftCorner().getY(), y2);
			assertEquals(command.getLowerRightCorner().getX(), x1);
			assertEquals(command.getLowerRightCorner().getY(), y1);
		}catch(DrawingToolException e){
			fail("Exception should not be raised: First point painted as the second one and viceversa");
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#CommandRectangle(java.util.List)}.
	 */
	@Test
	public void testCommandRectangleParametersOk() {
		String pattern = "%s %d %d %d %d";
		int x1 = 1, y1 = 1, x2 = 5, y2 = 5; 
		String commandString = String.format(pattern, Constant.COMMAND_RECTANGLE_ID, x1, y1, x2, y2);
		
		try{
			CommandRectangle command = (CommandRectangle)CommandFactory.buildCommand(commandString);
			assertEquals(command.getUpperLeftCorner().getX(), x1);
			assertEquals(command.getUpperLeftCorner().getY(), y1);
			assertEquals(command.getLowerRightCorner().getX(), x2);
			assertEquals(command.getLowerRightCorner().getY(), y2);
		}catch(DrawingToolException e){
			fail("Exception should not be raised: First point painted as the second one and viceversa");
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#execute(co.zero.drawingtool.model.Canvas)}.
	 */
	@Test
	public void testExecuteCanvasNotCreatedYet() {
		String pattern = "%s %d %d %d %d";
		int x1 = 1, y1 = 1, x2 = 5, y2 = 5; 
		String commandString = String.format(pattern, Constant.COMMAND_RECTANGLE_ID, x1, y1, x2, y2);
		
		try{
			CommandRectangle command = (CommandRectangle)CommandFactory.buildCommand(commandString);
			command.execute(null);
			fail("Exception should be raised: Canvas not created yet");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.CANVAS_NOT_CREATED_YET);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#execute(co.zero.drawingtool.model.Canvas)}.
	 * @throws DrawingToolException 
	 */
	@Test
	public void testExecuteParametersOutsideOfCanvas() throws DrawingToolException {
		String patternCanvas = CommandCanvas.COMMAND_ID + " 5 5";
		String patternLine1 = CommandRectangle.COMMAND_ID + " 1 1 6 1";
		String patternLine2 = CommandRectangle.COMMAND_ID + " 6 1 1 1";
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
		
		try{
			command = CommandFactory.buildCommand(patternLine2);
			canvas = command.execute(canvas);
			fail("Exception should be raised: Point 2 outside of canvas");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PAINTING_OUTSIDE_CANVAS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandRectangle#execute(co.zero.drawingtool.model.Canvas)}.
	 * @throws DrawingToolException 
	 */
	@Test
	public void testExecuteOK() throws DrawingToolException {
		String patternCanvas = CommandCanvas.COMMAND_ID + " 5 5";
		String patternLine1 = CommandRectangle.COMMAND_ID + " 1 1 4 4";
		Canvas canvas = null;
		Command command = null;
		
		command = CommandFactory.buildCommand(patternCanvas);
		canvas = command.execute(canvas);
		command = CommandFactory.buildCommand(patternLine1);
		canvas = command.execute(canvas);
		
		for(int y=1; y <= 4; y++){
			assertEquals(CommandRectangle.DEFAULT_LINE_LABEL, canvas.getPixel(1, y));
			assertEquals(CommandRectangle.DEFAULT_LINE_LABEL, canvas.getPixel(4, y));
		}
		
		for(int x=1; x <= 4; x++){
			assertEquals(CommandRectangle.DEFAULT_LINE_LABEL, canvas.getPixel(x, 1));
			assertEquals(CommandRectangle.DEFAULT_LINE_LABEL, canvas.getPixel(x, 4));
		}
	}
}
