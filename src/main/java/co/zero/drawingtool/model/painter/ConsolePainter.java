//=======================================================================
// ARCHIVO ConsolePainter.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.painter;

import co.zero.drawingtool.model.Canvas;

/**
 * This class represent a {@link Painter} that can show the canvas in standar system output
 * @author Hernán Tenjo
 * @version 1.0
 */
public class ConsolePainter extends Painter{
	/*
	 * (non-Javadoc)
	 * @see co.zero.drawingtool.model.painter.Painter#paint(co.zero.drawingtool.model.Canvas)
	 */
	@Override
	public void paint(Canvas canvas){
		for(int j=0; j < canvas.getHeight(); j++){
			for(int i=0; i < canvas.getWidth(); i++){
				System.out.print(canvas.getPixel(i, j));			
			}
			
			System.out.println();
		}
		
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see co.zero.drawingtool.model.painter.Painter#paint(java.lang.String)
	 */
	@Override
	public void paint(String errorMessage){
		System.out.println(errorMessage);
		System.out.println();
	}
}