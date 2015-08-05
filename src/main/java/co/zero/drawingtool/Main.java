//=======================================================================
// ARCHIVO Main.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.factory.CommandFactory;
import co.zero.drawingtool.factory.PainterFactory;
import co.zero.drawingtool.model.Canvas;
import co.zero.drawingtool.model.command.Command;
import co.zero.drawingtool.model.painter.Painter;

/**
 * This is the entry point of the program, that should be executed from 
 * command line with 2 parameters (inputFilePath and outputFilePath)
 * @author Hernán Tenjo
 * @version 1.0
 */
public class Main {
	private static final int FILE_PAINTER_REQUIRED_PARAMS = 2;
	
	/**
	 * Main mathod that should be executed
	 * @param args Command line arguments:
	 * args[0] = Path of the input file 
	 * args[1] = Path of the output file
	 * @throws DrawingToolException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, DrawingToolException{
		if(args.length < FILE_PAINTER_REQUIRED_PARAMS){
			throw new DrawingToolException(DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
		
		String inputFilePath = args[0];
		String outputFilePath = args[1];
		Reader reader = null;
		BufferedReader bufferedReader = null;
		Painter painter = null;
		
		try{
			painter = PainterFactory.buildPainter(PainterFactory.FILE_PAINTER, outputFilePath);
			reader = new FileReader(inputFilePath);
			bufferedReader = new BufferedReader(reader);
			String commandLine;
			Canvas canvas = null;
			
			while((commandLine = bufferedReader.readLine()) != null){
				canvas = executeCommand(commandLine, canvas, painter);
			}
		}finally{
			if(bufferedReader != null){
				bufferedReader.close();
			}
			
			if(reader != null){
				reader.close();
			}
			
			if(reader != null){
				painter.close();				
			}
		}
	}
	
	/**
	 * This method handles the execution of each command
	 * @param commandLine A command from input file that should be executed 
	 * @param canvas The space where commands can paint
	 * @param painter This knows how to paint the canvas in an specific output
	 * @throws Exception
	 */
	private static Canvas executeCommand(String commandLine, Canvas canvas, Painter painter) throws DrawingToolException{
		try{
			Command command = CommandFactory.buildCommand(commandLine);
			Canvas canvasUpdated = command.execute(canvas);
			painter.paint(canvasUpdated);
			return canvasUpdated;
		}catch(DrawingToolException e){
			painter.paint(e.toString());
			return canvas;
		}
	}
}
