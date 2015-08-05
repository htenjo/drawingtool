//=======================================================================
// ARCHIVO CommandRectangle.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.command;

import java.util.List;

import co.zero.drawingtool.constant.Constant;
import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;
import co.zero.drawingtool.model.Point;
import co.zero.drawingtool.util.GeometricUtils;
import co.zero.drawingtool.util.NumberUtils;

/**
 * This class represent a command that can create a rectangle in the canvas
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandRectangle extends Command{
	public static final String COMMAND_ID = Constant.COMMAND_RECTANGLE_ID;
	public static final String DEFAULT_LINE_LABEL = "#";
	private static final int COORDINATE_X1_INDEX = 0;
	private static final int COORDINATE_Y1_INDEX = 1;
	private static final int COORDINATE_X2_INDEX = 2;
	private static final int COORDINATE_Y2_INDEX = 3;
	private static final int PARAMETERS_REQUIRED = 4;
	private Point upperLeftCorner;
	private Point lowerRightCorner;
	
	/**
	 * Default constructor of a {@link CommandCanvas}
	 * @param paramaters Required parameters for the specific command
	 * @throws DrawingToolException If there is some invalid parameter
	 */
	public CommandRectangle(List<String> paramaters) throws DrawingToolException{
		super(paramaters);
		Point p1 = GeometricUtils.parsePoint(paramaters.get(COORDINATE_X1_INDEX), paramaters.get(COORDINATE_Y1_INDEX));
		Point p2 = GeometricUtils.parsePoint(paramaters.get(COORDINATE_X2_INDEX), paramaters.get(COORDINATE_Y2_INDEX));
		upperLeftCorner = GeometricUtils.getMinPoint(p1, p2);
		lowerRightCorner = GeometricUtils.getMaxPoint(p1, p2);
	}

	/*
	 * (non-Javadoc)
	 * @see co.zero.drawingtool.model.command.Command#execute(co.zero.drawingtool.model.Canvas)
	 */
	@Override
	public Canvas execute(Canvas canvas) throws DrawingToolException {
		if(canvas == null){
			throw new DrawingToolException(DrawingToolException.CANVAS_NOT_CREATED_YET);
		}
		
		if(!(GeometricUtils.isPointInsideCanvas(canvas, upperLeftCorner) && 
				GeometricUtils.isPointInsideCanvas(canvas, lowerRightCorner))){
			throw new DrawingToolException(DrawingToolException.COMMAND_PAINTING_OUTSIDE_CANVAS);
		}
		
		Point upperRightCorner = new Point(lowerRightCorner.getX(), upperLeftCorner.getY());
		Point lowerLeftCorner = new Point(upperLeftCorner.getX(), lowerRightCorner.getY());
		Canvas updatedCanvas = null;
		
		updatedCanvas = GeometricUtils.drawLine(canvas, upperLeftCorner, upperRightCorner, DEFAULT_LINE_LABEL);
		updatedCanvas = GeometricUtils.drawLine(canvas, lowerLeftCorner, lowerRightCorner, DEFAULT_LINE_LABEL);
		updatedCanvas = GeometricUtils.drawLine(canvas, upperLeftCorner, lowerLeftCorner, DEFAULT_LINE_LABEL);
		updatedCanvas = GeometricUtils.drawLine(canvas, upperRightCorner, lowerRightCorner, DEFAULT_LINE_LABEL);
		return updatedCanvas;
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
		NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_X1_INDEX));
		NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_Y1_INDEX));
		NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_X2_INDEX));
		NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_Y2_INDEX));
	}

	/**
	 * @return the upperLeftCorner
	 */
	protected Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	/**
	 * @return the lowerRightCorner
	 */
	protected Point getLowerRightCorner() {
		return lowerRightCorner;
	}
}