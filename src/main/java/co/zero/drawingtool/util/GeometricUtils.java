//=======================================================================
// ARCHIVO GeometricUtils.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.util;

import org.apache.commons.lang3.math.NumberUtils;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;
import co.zero.drawingtool.model.Point;

/**
 * Generic util class that helps to deal with geometric operations
 * @author Hernán Tenjo
 * @version 1.0
 */
public class GeometricUtils {
	/**
	 * This method reads x,y string parameters and builds a related {@link Point}  
	 * @param xString X coordinate of the point
	 * @param yString Y coordinate of the point
	 * @return The related point
	 */
	public static Point parsePoint(String xString, String yString){
		int x = NumberUtils.toInt(xString);
		int y = NumberUtils.toInt(yString);
		return new Point(x, y);
	}
	
	/**
	 * This methos compares two points and gets the farthest point of (0,0) 
	 * @param p1 First point to compare
	 * @param p2 Second point to compare
	 * @return The farthest point of (0,0)
	 */
	public static Point getMaxPoint(Point p1, Point p2){
		if((p1.getX() - p2.getX() < 0) || (p1.getY() - p2.getY() < 0)){
			return p2;
		}else{
			return p1;
		}
	}
	
	/**
	 * This methos compares two points and gets the nearest point of (0,0) 
	 * @param p1 First point to compare
	 * @param p2 Second point to compare
	 * @return The nearest point of (0,0)
	 */
	public static Point getMinPoint(Point p1, Point p2){
		if((p1.getX() - p2.getX() < 0) || (p1.getY() - p2.getY() < 0)){
			return p1;
		}else{
			return p2;
		}
	}
	
	/**
	 * This method check if the given coordinates build a vertical or horizontal line
	 * @param x1 X coordinate of first point
	 * @param y1 Y coordinate of first point
	 * @param x2 X coordinate of second point
	 * @param y2 Y coordinate of second point
	 * @throws DrawingToolException
	 */
	public static void validateHorizontalOrVerticalLine(int x1, int y1, int x2, int y2) throws DrawingToolException{
		if(x1 != x2 && y1 != y2){
			throw new DrawingToolException(DrawingToolException.COMMAND_NOT_SUPPORTED);
		}
	}
	
	/**
	 * This method check if a given point in inside of the valid are inside the canvas
	 * @param canvas The space to paint
	 * @param p The point to be painted
	 * @return True if the point is inside of the canvas, false otherwise
	 */
	public static boolean isPointInsideCanvas(Canvas canvas, Point p){
		int offset = Canvas.CANVAS_BORDER_WITH - 1;
		
		if(offset < p.getX() && p.getX() < (canvas.getWidth() - offset - 1) && 
				offset < p.getY() && p.getY() < (canvas.getHeight() - offset - 1)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method paint a line inside the given canvas
	 * @param canvas Spece to paint
	 * @param start First point of the line
	 * @param end Second point of the line
	 * @param color The content to paint in the canvas
	 * @return The canvas with the updated information
	 */
	public static Canvas drawLine(Canvas canvas, Point start, Point end, String color){
		if(start.getX() == end.getX()){
			//Vertical line 
			for(int i=start.getY(); i <= end.getY(); i++){
				canvas.paint(start.getX(), i, color);
			}
		}else{
			//horizontal line
			for(int i=start.getX(); i <= end.getX(); i++){
				canvas.paint(i, start.getY(), color);
			}
		}
		
		return canvas;
	}
}
