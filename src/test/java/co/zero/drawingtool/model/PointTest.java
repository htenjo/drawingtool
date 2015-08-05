//=======================================================================
// ARCHIVO PointTest.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Class that test the {@link Point} class
 * @author Hernán Tenjo
 * @version 1.0
 */
public class PointTest {
	/**
	 * 
	 */
	@Test
	public void testPointConstructorDefault() {
		Point p = new Point();
		assertEquals(0, p.getX());
		assertEquals(0, p.getY());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointConstructorFromValues() {
		int x = 1, y = 1;
		Point p = new Point(x, y);
		assertEquals(x, p.getX());
		assertEquals(y, p.getY());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointConstructorFromPoint() {
		int x = 1, y = 1;
		Point p1 = new Point(x, y);
		Point p2 = new Point(p1);
		assertEquals(x, p2.getX());
		assertEquals(y, p2.getY());
		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointUpper() {
		Point p = new Point(5, 5);
		Point p1 = p.getAbovePoint();
		assertEquals(5, p1.getX());
		assertEquals(4, p1.getY());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointRight() {
		Point p = new Point(5, 5);
		Point p1 = p.getRightPoint();
		assertEquals(6, p1.getX());
		assertEquals(5, p1.getY());
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointDown() {
		Point p = new Point(5, 5);
		Point p1 = p.getBelowPoint();
		assertEquals(5, p1.getX());
		assertEquals(6, p1.getY());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointLeft() {
		Point p = new Point(5, 5);
		Point p1 = p.getLeftPoint();
		assertEquals(4, p1.getX());
		assertEquals(5, p1.getY());
	}
	
	/**
	 * 
	 */
	@Test
	public void testPointToString(){
		Point p = new Point();
		assertTrue(StringUtils.isNotBlank(p.toString()));
		assertEquals("co.zero.drawingtool.model.Point[x=0,y=0]", p.toString());
	}
}
