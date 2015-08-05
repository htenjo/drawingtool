//=======================================================================
// ARCHIVO CommandCanvas.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.command;

import java.util.List;

import co.zero.drawingtool.constant.Constant;
import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;
import co.zero.drawingtool.util.NumberUtils;

/**
 * This class represent a command that can create a canvas
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandCanvas extends Command{
	public static final String COMMAND_ID = Constant.COMMAND_CANVAS_ID;
	private static final int WIDTH_INDEX = 0;
	private static final int HEIGH_INDEX = 1;
	private static final int PARAMETERS_REQUIRED = 2;
	private int width;
	private int height;
	
	/**
	 * Default constructor of a {@link CommandCanvas}
	 * @param paramaters Required parameters for the specific command
	 * @throws DrawingToolException If there is some invalid parameter
	 */
	public CommandCanvas(List<String> paramaters) throws DrawingToolException{
		super(paramaters);
	}

	/*
	 * (non-Javadoc)
	 * @see co.zero.drawingtool.model.command.Command#execute(co.zero.drawingtool.model.Canvas)
	 */
	@Override
	public Canvas execute(Canvas canvas) {
		if(canvas != null){
			return canvas;
		}else{
			Canvas newCanvas = new Canvas(width, height);
			
			//Init canvas background
			for(int y=0; y < newCanvas.getHeight(); y++){
				for(int x=0; x < newCanvas.getWidth(); x++){
					newCanvas.paint(x, y, Constant.DEFAULT_CANVAS_BACKGROUND);
				}
			}
					
			//Paint the left and right canvas border
			for(int y=0; y < newCanvas.getHeight(); y++){
				for(int x=0; x < Canvas.CANVAS_BORDER_WITH; x++){
					newCanvas.paint(x, y, Constant.LINE_VERTICAL);
					newCanvas.paint(newCanvas.getWidth() - 1 - x, y, Constant.LINE_VERTICAL);
				}
			}
			
			//Paint the upper and lower canvas border
			for(int x=0; x < newCanvas.getWidth(); x++){
				for(int y=0; y < Canvas.CANVAS_BORDER_WITH; y++){
					newCanvas.paint(x, y, Constant.LINE_HORIZONTAL);
					newCanvas.paint(x, newCanvas.getHeight() - 1 - y, Constant.LINE_HORIZONTAL);					
				}
			}
			
			return newCanvas;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.zero.drawingtool.model.command.Command#validateParameters()
	 */
	@Override
	protected void validateParameters() throws DrawingToolException {
		//1. Validate minimum amount of parameters 
		if(parameters.size() < PARAMETERS_REQUIRED){
			throw new DrawingToolException(DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
		
		//2. Validate parameters type
		width = NumberUtils.validatePositiveInteger(parameters.get(WIDTH_INDEX));
		height = NumberUtils.validatePositiveInteger(parameters.get(HEIGH_INDEX));
		
		//3. Validate canvas size
		if(width <= Canvas.MIN_WIDTH || height <= Canvas.MIN_HEIGHT){
			throw new DrawingToolException(DrawingToolException.CANVAS_SIZE_INVALID);
		}
	}
}