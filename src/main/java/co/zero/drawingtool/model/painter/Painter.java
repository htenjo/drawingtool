//=======================================================================
// ARCHIVO Painter.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.painter;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;

/**
 * This class represent a way to write a given canvas in an specific output form (File, Console, etc)
 * @author Hernán Tenjo
 * @version 1.0
 */
public abstract class Painter {
	/**
	 * Method that writes the canvas information in the specific output format
	 * @param canvas Object with the information to write
	 * @throws DrawingToolException
	 */
	public abstract void paint(Canvas canvas) throws DrawingToolException;
	
	/**
	 * Methos that writes an error in the specific output format
	 * @param errorMessage Text to write in the specifica output format
	 * @throws DrawingToolException
	 */
	public abstract void paint(String errorMessage) throws DrawingToolException;
	
	/**
	 * If the specific painter manage resources, those could be finalized here
	 * @throws DrawingToolException
	 */
	public void close() throws DrawingToolException{
	}
}