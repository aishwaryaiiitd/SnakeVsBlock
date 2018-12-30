package application;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Represents the token which cannot be crossed by the snake.
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Wall extends Token implements Serializable{
	private int length,y_velocity=2;
	private Point location;
	private Rectangle image;
	/**
	 * constructor of wall object, initializes the location and its length and generates its corresponding image
	 * Also sets color of image
	 * @param loc
	 * @param _length
	 */
	public Wall(Point loc,int _length) {
		location=loc;
		length=_length;
		image = new Rectangle(loc.getX(),loc.getY(),10,_length);
		image.setFill(Color.WHITE);
	}
	
	/**
	 * returns the corresponding image
	 * @return image
	 */
	public Rectangle getImage() {
		return image;
	}
	/**
	 * returns the length of the wall
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * sets the length of the wall
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * returns the location of the wall
	 * @return location
	 */

	
	public Point getLocation() {
		return location;
	}
	/**
	 * reuturns the boundary of the corresponding image of the wall
	 * @return Rectangle2D
	 */
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX(),location.getY(),12,length);
        return shape;
    }
	/**
	 * sets the location of the shield
	 * @param location
	 */
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	/**
	 * moves the image down on the game screen as per its y_velocity, updates the new location as well
	 */
	public void move_down() {
		location.setY(location.getY()+y_velocity);
		image.setTranslateY(location.getY());
	}
	
}

