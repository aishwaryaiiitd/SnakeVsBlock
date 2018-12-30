package application;

import java.awt.geom.Rectangle2D;

import java.io.Serializable;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
/**
 * Represents the token which the destroys all blocks on the screen
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Destroy_Blocks extends Token implements Serializable{
	
	private Circle body;
	private Rectangle top;
	
	private Point location;
	private int y_velocity=2;
	
	/**
	 * constructor of destry_block object, initialises location and generates its corresponding image
	 * image consists of a red circle(body) with a white rectangle(top) over it
	 * @param loc
	 */

	public Destroy_Blocks(Point loc) {
		body = new Circle( loc.getX(),loc.getY(),25);
		body.setFill(Color.RED);
		top = new Rectangle(loc.getX()-5,loc.getY()-32,10,14);
		top.setFill(Color.WHITE);
		location=loc;
	}
	/**
	 * reuturns the boundary of the corresponding image of the destroy_block object
	 * @return Rectangle2D
	 */
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX()-25,location.getY()-25,25,25);
        return shape;
    }
	/**
	 * reuturns the corresponding circle of the image
	 * @return Circle
	 */
	public Circle getBody() {
		return body;
	}
	/**
	 * returns the corresponding rectangle of the image
	 * @return Rectangle
	 */
	public Rectangle getTop() {
		return top;
	}
	/**
	 * returns the location of the object
	 * @return Point
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * sets the location of the object
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
		body.setTranslateY(location.getY());
		top.setTranslateY(location.getY());
	}
	
}
