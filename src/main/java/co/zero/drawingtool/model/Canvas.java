//=======================================================================
// ARCHIVO Canvas.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model;


/**
 * This class represent a space where commands are painted
 * @author Hernán Tenjo
 * @version 1.0
 */
public class Canvas {
	public static final int CANVAS_BORDER_WITH = 1;
	public static final int MIN_WIDTH = 1;
	public static final int MIN_HEIGHT = 1;
	private String[][] image;
	private int width;
	private int height;
	
	/**
	 * Default canvas constructor
	 * @param width The width of the space to paint
	 * @param height The height of the space to paint
	 */
	public Canvas(int width, int height){
		this.width = width + CANVAS_BORDER_WITH + CANVAS_BORDER_WITH;
		this.height = height + CANVAS_BORDER_WITH + CANVAS_BORDER_WITH;
		image = new String[this.height][this.width];
	}
	
	/**
	 * Change a pixel in the canvas with the given color 
	 * @param x Horizontal coordinate of the pixel
	 * @param y Vertical coordinate of the pixel
	 * @param color The new content of the pixel
	 */
	public void paint(int x, int y, String color){
		image[y][x] = color;
	}
	
	/**
	 * Obtains the content of a pixel given its coordinates
	 * @param x Horizontal coordinate of the pixel
	 * @param y Vertical coordinate of the pixel
	 * @return The content of the pixel
	 */
	public String getPixel(int x, int y){
		return image[y][x];
	}
	
	/**
	 * Obtains the real width of the canvas (the given width on the constructor + borders width)
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Obtains the real height of the canvas (the given height on the constructor + borders width)
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
}
