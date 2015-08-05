//=======================================================================
// ARCHIVO Constant.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.constant;

/**
 * This class group some common constants required in the system
 * @author Hernán Tenjo
 * @version 1.0
 */
public class Constant {
	public static final String LINE_HORIZONTAL = "-";
	public static final String LINE_VERTICAL = "|";
	public static final String COMMAND_LINE_ID = "L";
	public static final String COMMAND_FILL_ID = "B";
	public static final String COMMAND_RECTANGLE_ID = "R";
	public static final String COMMAND_CANVAS_ID = "C";
	public static final String NEW_LINE = "\n";
	public static final String DOT = ".";
	public static final String DEFAULT_CANVAS_BACKGROUND = DOT;

	//Message related with exceptions. This could be handle with i18n resource
	//files but I preffer handle this as simple as possible for the exercise 
	public static final String ERROR_CODE_0_MSG = "Generic Exception ";
	public static final String ERROR_CODE_1_MSG = "Command not found";
	public static final String ERROR_CODE_2_MSG = "Missing required arguments";
	public static final String ERROR_CODE_3_MSG = "Painter not found";
	public static final String ERROR_CODE_4_MSG = "Command parameters without required format";
	public static final String ERROR_CODE_5_MSG = "Command parameters out of range";
	public static final String ERROR_CODE_6_MSG = "Command not supported";
	public static final String ERROR_CODE_7_MSG = "Canvas not created yet";
	public static final String ERROR_CODE_8_MSG = "Command trying to paint outside of canvas";
	public static final String ERROR_CODE_9_MSG = "Canvas size invalid";
}
