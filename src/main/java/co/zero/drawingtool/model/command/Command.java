//=======================================================================
// ARCHIVO Command.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.command;

import java.util.List;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;

/**
 * This class represent a given command to paint in the canvas
 * @author Hernán Tenjo
 * @version 1.0
 */
public abstract class Command {
	//Required parameters for the command
	protected List<String> parameters;
	
	/**
	 * Default constructor of a {@link Command}
	 * @param paramaters Required parameters for the specific command
	 * @throws DrawingToolException If there is some invalid parameter
	 */
	public Command(List<String> paramaters) throws DrawingToolException{
		this.parameters = paramaters;
		validateParameters();
	}

	/**
	 * Method that paint the command in the given canvas
	 * @return A canvas with the updated information
	 * @throws DrawingToolException 
	 */
	public abstract Canvas execute(Canvas canvas) throws DrawingToolException;
	
	
	/**
	 * Method that validate the parameters given for the command
	 * @throws DrawingToolException
	 */
	protected abstract void validateParameters() throws DrawingToolException;
}
