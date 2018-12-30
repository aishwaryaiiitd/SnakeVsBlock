package application;

import java.io.Serializable;
/**
 * Represents object containing points on the screen(x and y coordinate)
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Point implements Serializable {
	private int x,y;
	/**
	 * Constructor, intializes the x and y coordinate
	 * @param _x
	 * @param _y
	 */
	public Point(int _x,int _y) {
		x=_x;
		y=_y;
	}
	/**
	 * Returns the x coordinate
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * sets the x coordinate
	 * @param x
	 */	
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Returns the y coordinate
	 * @return y 
	 */
	
	public int getY() {
		return y;
	}
	/**
	 * sets the y coordinate
	 * @param y
	 */	
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * overrides the equals method and checks if two points are equal
	 * @param o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if(o!=null && getClass()==o.getClass()) {
			Point p=(Point)o;
			return (p.getX()==x && p.getY()==y);
			
		}
		else {
			return false;
		}
			
	}
	
}
