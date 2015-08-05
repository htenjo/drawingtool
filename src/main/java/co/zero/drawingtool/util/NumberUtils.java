//=======================================================================
// ARCHIVO NumberUtils.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.util;

import co.zero.drawingtool.exception.DrawingToolException;

/**
 * Generic util class that helps to deal with numeric operations
 * @author Hernán Tenjo
 * @version 1.0
 */
public class NumberUtils {
	public static final int ZERO = org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
	public static final int ONE = org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
	
	/**
	 * This method validate if a string number is an integer positive
	 * @param number The parameter that should be checked
	 * @return The integer represented by number parameter
	 * @throws DrawingToolException
	 */
	public static int validatePositiveInteger(String number) throws DrawingToolException{
		try{
			int intNumer = Integer.parseInt(number); 
			
			if(ZERO <= intNumer){
				return intNumer;
			}else{
				throw new DrawingToolException(DrawingToolException.COMMAND_PARAMS_OUT_OF_RANGE);	
			}
		}catch(NumberFormatException e){
			throw new DrawingToolException(DrawingToolException.COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT);
		}
	}
}
