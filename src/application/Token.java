package application;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
/**
 * An abstract class representing all the common features of each objects on the main screen(except snake and burst objects) 
 */
public abstract class Token implements Serializable{
	
	private Point location;
	/**
	 * returns the location of the token
	 * @return Point
	 */
	abstract public Point getLocation();
	/**
	 * sets the location of the token
	 * @param location
	 */
	abstract public void setLocation(Point location);
	/**
	 * moves the object down on the game screen
	 */
	abstract public void move_down();
	/**
	 * reuturns the boundary of the corresponding image of the object
	 * @return Rectangle2D
	 */
	
	abstract public Rectangle2D getBoundary();
}
