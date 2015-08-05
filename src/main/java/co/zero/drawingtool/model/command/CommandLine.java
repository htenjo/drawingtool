//=======================================================================
// ARCHIVO CommandLine.java
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
 * This class represent a command that can create a line
 * @author Hernán Tenjo
 * @version 1.0
 */
public class CommandLine extends Command{
	public static final String COMMAND_ID = Constant.COMMAND_LINE_ID;
	public static final String DEFAULT_LINE_LABEL = "x";
	private static final int COORDINATE_X1_INDEX = 0;
	private static final int COORDINATE_Y1_INDEX = 1;
	private static final int COORDINATE_X2_INDEX = 2;
	private static final int COORDINATE_Y2_INDEX = 3;
	private static final int PARAMETERS_REQUIRED = 4;
	//Points to define the line
	private Point start;
	private Point end;
	
	/**
	 * Default constructor of a {@link CommandCanvas}
	 * @param paramaters Required parameters for the specific command
	 * @throws DrawingToolException If there is some invalid parameter
	 */
	public CommandLine(List<String> paramaters) throws DrawingToolException{
		super(paramaters);
		Point p1 = GeometricUtils.parsePoint(paramaters.get(COORDINATE_X1_INDEX), paramaters.get(COORDINATE_Y1_INDEX));
		Point p2 = GeometricUtils.parsePoint(paramaters.get(COORDINATE_X2_INDEX), paramaters.get(COORDINATE_Y2_INDEX));
		start = GeometricUtils.getMinPoint(p1, p2);
		end = GeometricUtils.getMaxPoint(p1, p2);
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
		
		if(!(GeometricUtils.isPointInsideCanvas(canvas, start) && GeometricUtils.isPointInsideCanvas(canvas, end))){
			throw new DrawingToolException(DrawingToolException.COMMAND_PAINTING_OUTSIDE_CANVAS);
		}
		
		return GeometricUtils.drawLine(canvas, start, end, DEFAULT_LINE_LABEL);
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
		int x1 = NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_X1_INDEX));
		int y1 = NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_Y1_INDEX));
		int x2 = NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_X2_INDEX));
		int y2 = NumberUtils.validatePositiveInteger(parameters.get(COORDINATE_Y2_INDEX));
		
		//3. Validate parameters are part of horizontal or vertical line (current function)
		GeometricUtils.validateHorizontalOrVerticalLine(x1, y1, x2, y2);
	}

	/**
	 * @return the start
	 */
	protected Point getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	protected Point getEnd() {
		return end;
	}
}
