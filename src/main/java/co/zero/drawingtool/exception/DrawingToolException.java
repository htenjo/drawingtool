//=======================================================================
// ARCHIVO DrawingToolException.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.exception;

import co.zero.drawingtool.constant.Constant;

/**
 * Exception that handles most of the application errors
 * @author Hernán Tenjo
 * @version 1.0
 */
public class DrawingToolException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final int GENERIC_EXCEPTION = 0;
	public static final int COMMAND_NOT_FOUND = 1;
	public static final int MISSING_REQUIRED_ARGUMENTS = 2;
	public static final int PAINTER_NOT_FOUND = 3;
	public static final int COMMAND_PARAMS_WITHOUT_REQUIRED_FORMAT = 4;
	public static final int COMMAND_PARAMS_OUT_OF_RANGE = 5;
	public static final int COMMAND_NOT_SUPPORTED = 6;
	public static final int CANVAS_NOT_CREATED_YET = 7;
	public static final int COMMAND_PAINTING_OUTSIDE_CANVAS = 8;
	public static final int CANVAS_SIZE_INVALID = 9;
	private int errorCode;
	
	/**
	 * Exception constructor that handle a code error 
	 * @param errorCode The specific error that cause the problem
	 */
	public DrawingToolException(int errorCode){
		this.errorCode = errorCode;
	}
	
	/**
	 * Exception constructor that handle a code error and maintains the trace of given errors
	 * @param e Previous exception raised
	 * @param errorCode The specific error that cause the problem
	 */
	public DrawingToolException(Throwable e, int errorCode){
		super(e);
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the error code of the exception
	 */
	public int getErrorCode(){
		return errorCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();
		
		switch (errorCode) {
		case 0:
			message.append(Constant.ERROR_CODE_0_MSG);
			break;
		case 1:
			message.append(Constant.ERROR_CODE_1_MSG);
			break;
		case 2:
			message.append(Constant.ERROR_CODE_2_MSG);
			break;
		case 3:
			message.append(Constant.ERROR_CODE_3_MSG);
			break;
		case 4:
			message.append(Constant.ERROR_CODE_4_MSG);
			break;
		case 5:
			message.append(Constant.ERROR_CODE_5_MSG);
			break;
		case 6:
			message.append(Constant.ERROR_CODE_6_MSG);
			break;
		case 7:
			message.append(Constant.ERROR_CODE_7_MSG);
			break;
		case 8:
			message.append(Constant.ERROR_CODE_8_MSG);
			break;
		case 9:
			message.append(Constant.ERROR_CODE_9_MSG);
			break;
		default:
			break;
		}
		
		message.append(" - [::: ErrorCode = ");
		message.append(Integer.toString(errorCode));
		message.append("]");
		message.append(Constant.NEW_LINE);
		return message.toString();
	}
}
