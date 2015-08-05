//=======================================================================
// ARCHIVO CommandFactory.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.command.Command;
import co.zero.drawingtool.model.command.CommandCanvas;
import co.zero.drawingtool.model.command.CommandFill;
import co.zero.drawingtool.model.command.CommandLine;
import co.zero.drawingtool.model.command.CommandRectangle;

/**
 * Factory that helps to build a {@link Command}
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandFactory {
	/**
	 * It's not required an instance of this class
	 */
	private CommandFactory(){
	}
	
	/**
	 * Method that builds the required {@link Command} given a command parameter
	 * @param command The string with the parameter required (e.g. L 1 1 2 2)
	 * @return The {@link Command} related with command parameter
	 * @throws DrawingToolException If the command parameter is not recognized
	 */
	public static Command buildCommand(String command) throws DrawingToolException{
		List<String> commandList = new ArrayList<String>(Arrays.asList(StringUtils.split(command)));
		String commandId = commandList.get(0);
		commandList.remove(0);
		
		switch(commandId){
			case CommandCanvas.COMMAND_ID:
				return new CommandCanvas(commandList);
			case CommandFill.COMMAND_ID:
				return new CommandFill(commandList);
			case CommandLine.COMMAND_ID:
				return new CommandLine(commandList);
			case CommandRectangle.COMMAND_ID:
				return new CommandRectangle(commandList);
			default:
				throw new DrawingToolException(DrawingToolException.COMMAND_NOT_FOUND);
		}
	}
}