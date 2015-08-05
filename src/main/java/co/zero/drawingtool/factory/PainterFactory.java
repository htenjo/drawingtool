//=======================================================================
// ARCHIVO PainterFactory.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.factory;

import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.painter.ConsolePainter;
import co.zero.drawingtool.model.painter.FilePainter;
import co.zero.drawingtool.model.painter.Painter;

/**
 * Factory that helps to build a {@link Painter}
 * @author Hernán Tenjo
 * @version 1.0
 */
public class PainterFactory {
	public static final int FILE_PAINTER = 0;
	public static final int CONSOLE_PAINTER = 1;

	/**
	 * It's not required an instance of this class
	 */
	private PainterFactory(){
	}
	
	/**
	 * Method that builds the required {@link Painter} given an id and some parameters
	 * @param painterId The identifier of the required painter
	 * @param params Required parameters for the builded {@link Painter}
	 * @return The {@link Painter} related with the given painterId
	 * @throws DrawingToolException If the parentId parameter is not recognized
	 */
	public static Painter buildPainter(int painterId, String... params) throws DrawingToolException{
		switch (painterId) {
		case FILE_PAINTER:
			return new FilePainter(params);
		case CONSOLE_PAINTER:
			return new ConsolePainter();
		default:
			throw new DrawingToolException(DrawingToolException.PAINTER_NOT_FOUND);
		}
	}
}
