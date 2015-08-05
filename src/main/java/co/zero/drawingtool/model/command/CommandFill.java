//=======================================================================
// ARCHIVO CommandFill.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.command;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.zero.drawingtool.constant.Constant;
import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;
import co.zero.drawingtool.model.Point;
import co.zero.drawingtool.util.GeometricUtils;
import co.zero.drawingtool.util.NumberUtils;

/**
 * This class represent a command that can fill a free space in the canvas
 * For this implementation the free space is represented by a DOT character 
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandFill extends Command{
	public static final String COMMAND_ID = Constant.COMMAND_FILL_ID;
	private static final int COORDINATE_X_INDEX = 0;
	private static final int COORDINATE_Y_INDEX = 1;
	private static final int COORDINATE_COLOR_INDEX = 2;
	private static final int PARAMETERS_REQUIRED = 3;
	private Point initPoint;
	private String color;
	
	/**
	 * Default constructor of a {@link CommandFill}
	 * @param paramaters Required parameters for the specific command
	 * @throws DrawingToolException If there is some invalid parameter
	 */
	public CommandFill(List<String> paramaters) throws DrawingToolException {
		super(paramaters);
		initPoint = GeometricUtils.parsePoint(paramaters.get(COORDINATE_X_INDEX), paramaters.get(COORDINATE_Y_INDEX));
		color = paramaters.get(COORDINATE_COLOR_INDEX);
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
		
		if(!GeometricUtils.isPointInsideCanvas(canvas, initPoint)){
			throw new DrawingToolException(DrawingToolException.COMMAND_PAINTING_OUTSIDE_CANVAS);
		}
		
		recursivePaint(canvas, initPoint);
		return canvas;
	}
	
	/**
	 * Method that given a point can paint the above, right, below and left points
	 * @param canvas The canvas to paint
	 * @param p Point where starts to paint
	 * @throws DrawingToolException
	 */
	private void recursivePaint(Canvas canvas, Point p) throws DrawingToolException{
		if(GeometricUtils.isPointInsideCanvas(canvas, p) && 
				StringUtils.equals(canvas.getPixel(p.getX(), p.getY()), Constant.DEFAULT_CANVAS_BACKGROUND)){
			canvas.paint(p.getX(), p.getY(), color);
			
			//Paint the above, right, below and left points
			recursivePaint(canvas, p.getAbovePoint());	
			recursivePaint(canvas, p.getRightPoint());
			recursivePaint(canvas, p.getBelowPoint());
			recursivePaint(canvas, p.getLeftPoint());
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
		NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_X_INDEX));
		NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_Y_INDEX));
		
		if(StringUtils.isBlank(parameters.get(2))){
			throw new DrawingToolException(DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
	}

	/**
	 * @return the initPoint
	 */
	public Point getInitPoint() {
		return initPoint;
	}
}