//=======================================================================
// ARCHIVO FilePainter.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model.painter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import co.zero.drawingtool.constant.Constant;
import co.zero.drawingtool.exception.DrawingToolException;
import co.zero.drawingtool.model.Canvas;

/**
 * This class represent a {@link Painter} that can show the canvas in a file
 * @author Hernán Tenjo
 * @version 1.0
 */
public class FilePainter extends Painter {
	private String fileName;
	private FileWriter writer = null;
	private BufferedWriter bufferedWriter = null;
	
	/**
	 * Default constructor of the {@link Painter}
	 * @param parameters The required parameters of the {@link Painter}
	 * - parameters[0] = File name of the output file
	 * @throws DrawingToolException
	 */
	public FilePainter(String... parameters) throws DrawingToolException {
		if(parameters == null || parameters.length < 1){
			throw new DrawingToolException(DrawingToolException.MISSING_REQUIRED_ARGUMENTS);
		}
		
		this.fileName = parameters[0];
		
		try{
			writer = new FileWriter(fileName); 
			bufferedWriter = new BufferedWriter(writer);
		}catch(IOException e){
			throw new DrawingToolException(e, DrawingToolException.GENERIC_EXCEPTION);
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.zero.drawingtool.model.painter.Painter#paint(co.zero.drawingtool.model.Canvas)
	 */
	@Override
	public void paint(Canvas canvas) throws DrawingToolException{
		try{
			for(int j=0; j < canvas.getHeight(); j++){
				for(int i=0; i < canvas.getWidth(); i++){
					bufferedWriter.write(canvas.getPixel(i, j));
				}
				
				bufferedWriter.write(Constant.NEW_LINE);
			}
			
			bufferedWriter.write(Constant.NEW_LINE);
		}catch(IOException e){
			throw new DrawingToolException(e, DrawingToolException.GENERIC_EXCEPTION);
		}
	}
	
	/* (non-Javadoc)
	 * @see co.zero.drawingtool.model.painter.Painter#paint(java.lang.String)
	 */
	@Override
	public void paint(String errorMessage) throws DrawingToolException {
		try{
			bufferedWriter.write(errorMessage);
			bufferedWriter.write(Constant.NEW_LINE);
		}catch(IOException e){
			throw new DrawingToolException(e, DrawingToolException.GENERIC_EXCEPTION);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.zero.drawingtool.model.painter.Painter#close()
	 */
	@Override
	public void close() throws DrawingToolException {
		try{
			bufferedWriter.close();
			writer.close();
		}catch(IOException e){
			throw new DrawingToolException(e, DrawingToolException.GENERIC_EXCEPTION);
		}
	}
}