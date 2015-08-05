//=======================================================================
// ARCHIVO CommandLineTest.java
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
 * Class that test the {@link CommandLine} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandLineTest {
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineEmptyParameters() {
		String createLineCommand = Constant.COMMAND_LINE_ID;
		
		try{
			CommandFactory.buildCommand(createLineCommand);
			fail("Exception should be raised: Required parameters are empty");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersLessThanRequiredOneParameter() {
		String pattern1 = "%s %d";
		String command1 = String.format(pattern1, Constant.COMMAND_LINE_ID, 1);
		
		try{
			CommandFactory.buildCommand(command1);
			fail("Exception should be raised: Required parameters are less (1) than required (4)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersLessThanRequiredTwoParameters() {
		String pattern2 = "%s %d %d";
		String command2 = String.format(pattern2, Constant.COMMAND_LINE_ID, 1, 1);
		
		try{
			CommandFactory.buildCommand(command2);
			fail("Exception should be raised: Required parameters are less (2) than required (4)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersLessThanRequiredThreeParameters() {
		String pattern3 = "%s %d %d %d";
		String command3 = String.format(pattern3, Constant.COMMAND_LINE_ID, 1, 1, 1);
		
		try{
			CommandFactory.buildCommand(command3);
			fail("Exception should be raised: Required parameters are less (3) than required (4)");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersNotNumbers() {
		String pattern = "%s %s %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_LINE_ID, "L", 1, 1, 2);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have letters and numbers are required");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersNotInteger() {
		String pattern = "%s %d %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_LINE_ID, Long.MAX_VALUE, 1, 1, 1);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have numbers bigger than integers");
		}catch(DrawingToolException e){
			assertEquals(DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT, e.getErrorCode());
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineNotHorizontalOrVertical() {
		String pattern = "%s %d %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_LINE_ID, 1, 1, 3, 3);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Lines different than horizontal or vertical are not supported yet");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_NOT_SUPPORTED);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersNegatives() {
		String pattern = "%s %d %d %d %d";
		String command = String.format(pattern, Constant.COMMAND_LINE_ID, -1, 1, -1, 1);
		
		try{
			CommandFactory.buildCommand(command);
			fail("Exception should be raised: Parameters have negative numbers");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.COMMAND_PARAMS_OUT_OF_RANGE);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersFirstPointHigherThanSecondPoint() {
		String pattern = "%s %d %d %d %d";
		int x1 = 5, y1 = 5, x2 = 1, y2 = 5; 
		String commandString = String.format(pattern, Constant.COMMAND_LINE_ID, x1, y1, x2, y2);
		
		try{
			CommandLine command = (CommandLine)CommandFactory.buildCommand(commandString);
			assertEquals(command.getStart().getX(), x2);
			assertEquals(command.getStart().getY(), y2);
			assertEquals(command.getEnd().getX(), x1);
			assertEquals(command.getEnd().getY(), y1);
		}catch(DrawingToolException e){
			fail("Exception should not be raised: First point painted as the second one and viceversa");
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#CommandLine(java.util.List)}.
	 */
	@Test
	public void testCommandLineParametersOk() {
		String pattern = "%s %d %d %d %d";
		int x1 = 1, y1 = 5, x2 = 5, y2 = 5; 
		String commandString = String.format(pattern, Constant.COMMAND_LINE_ID, x1, y1, x2, y2);
		
		try{
			CommandLine command = (CommandLine)CommandFactory.buildCommand(commandString);
			assertEquals(command.getStart().getX(), x1);
			assertEquals(command.getStart().getY(), y1);
			assertEquals(command.getEnd().getX(), x2);
			assertEquals(command.getEnd().getY(), y2);
		}catch(DrawingToolException e){
			fail("Exception should not be raised: First point painted as the second one and viceversa");
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#execute(co.zero.drawingtool.model.Canvas)}.
	 */
	@Test
	public void testExecuteCanvasNotCreatedYet() {
		String pattern = "%s %d %d %d %d";
		int x1 = 1, y1 = 5, x2 = 5, y2 = 5; 
		String commandString = String.format(pattern, Constant.COMMAND_LINE_ID, x1, y1, x2, y2);
		
		try{
			CommandLine command = (CommandLine)CommandFactory.buildCommand(commandString);
			command.execute(null);
			fail("Exception should be raised: Canvas not created yet");
		}catch(DrawingToolException e){
			assertEquals(e.getErrorCode(), DrawingToolException.CANVAS_NOT_CREATED_YET);
		}
	}
	
	/**
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#execute(co.zero.drawingtool.model.Canvas)}.
	 * @throws DrawingToolException 
	 */
	@Test
	public void testExecuteParametersOutsideOfCanvas() throws DrawingToolException {
		String patternCanvas = CommandCanvas.COMMAND_ID + " 5 5";
		String patternLine1 = CommandLine.COMMAND_ID + " 1 1 6 1";
		String patternLine2 = CommandLine.COMMAND_ID + " 6 1 1 1";
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
	 * Test method for {@link co.zero.drawingtool.model.command.CommandLine#execute(co.zero.drawingtool.model.Canvas)}.
	 * @throws DrawingToolException 
	 */
	@Test
	public void testExecuteOK() throws DrawingToolException {
		String patternCanvas = CommandCanvas.COMMAND_ID + " 5 5";
		String patternLine1 = CommandLine.COMMAND_ID + " 1 1 4 1";
		String patternLine2 = CommandLine.COMMAND_ID + " 1 1 1 4";
		Canvas canvas = null;
		Command command = null;
		
		command = CommandFactory.buildCommand(patternCanvas);
		canvas = command.execute(canvas);
		command = CommandFactory.buildCommand(patternLine1);
		canvas = command.execute(canvas);
		command = CommandFactory.buildCommand(patternLine2);
		canvas = command.execute(canvas);
		
		for(int y=1; y <= 4; y++){
			assertEquals(CommandLine.DEFAULT_LINE_LABEL, canvas.getPixel(1, y));
		}

		for(int x=1; x <= 4; x++){
			assertEquals(CommandLine.DEFAULT_LINE_LABEL, canvas.getPixel(x, 1));
		}
	}
}
