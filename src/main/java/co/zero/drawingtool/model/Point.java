//=======================================================================
// ARCHIVO Point.java
// FECHA CREACIÓN: 2015/08/02
//=======================================================================
package co.zero.drawingtool.model;

/**
 * This class represent a point in the canvas managed by integer coordinates
 * @author Hernán Tenjo
 * @version 1.0
 */
public class Point {
	private static final int DEFAULT_OFFSET = 1;
	//Components of the point
	private int x;
	private int y;

	/**
	 * Constructs and initializes a point at the origin (0,&nbsp;0) of the
	 * coordinate space.
	 */
	public Point() {
		this(0, 0);
	}

	/**
	 * Constructs and initializes a point with the same location as the
	 * specified <code>Point</code> object.
	 * @param p a point
	 */
	public Point(Point p) {
		this(p.x, p.y);
	}

	/**
	 * Constructs and initializes a point at the specified {@code (x,y)}
	 * location in the coordinate space.
	 * @param x the X coordinate of the newly constructed <code>Point</code>
	 * @param y the Y coordinate of the newly constructed <code>Point</code>
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
     * Determines whether or not two points are equal. Two instances of
     * <code>Point</code> are equal if the values of their
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Point2D</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Point2D</code> and has
     *         the same values; <code>false</code> otherwise.
     */
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pt = (Point)obj;
            return (x == pt.x) && (y == pt.y);
        }
        
        return super.equals(obj);
    }
    
    /**
     * Returns the hashcode for this <code>Point2D</code>.
     * @return a hash code for this <code>Point2D</code>.
     */
	@Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(getX());
        bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
        return ((int) bits) ^ ((int) (bits >> 32));
    }

    /**
     * Returns a string representation of this point and its location
     * in the {@code (x,y)} coordinate space. This method is
     * intended to be used only for debugging purposes, and the content
     * and format of the returned string may vary between implementations.
     * The returned string may be empty but may not be <code>null</code>.
     * @return  a string representation of this point
     */
	@Override
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }

	/**
	 * @return the x
	 */
    public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
    public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
    public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
    public void setY(int y) {
		this.y = y;
	}
    
    /**
     * Build a point just above to this point
     * @return The point above of this point
     */
    public Point getAbovePoint(){
    	Point p = new Point(this);
    	p.setY(this.getY() - DEFAULT_OFFSET);
    	return p;
    }
    
    /**
     * Build a point just at right to this point
     * @return The point at right of this point
     */
    public Point getRightPoint(){
    	Point p = new Point(this);
    	p.setX(this.getX() + DEFAULT_OFFSET);
    	return p;
    }
    
    /**
     * Build a point just below to this point
     * @return The point below of this point
     */
    public Point getBelowPoint(){
    	Point p = new Point(this);
    	p.setY(this.getY() + DEFAULT_OFFSET);
    	return p;
    }
    
    /**
     * Build a point just at left to this point
     * @return The point at left of this point
     */
    public Point getLeftPoint(){
    	Point p = new Point(this);
    	p.setX(this.getX() - DEFAULT_OFFSET);
    	return p;
    }
}
