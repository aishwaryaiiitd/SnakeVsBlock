package application;

import java.awt.geom.Rectangle2D;

import java.io.Serializable;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * Represents the token eating which length of the snake does not decrease for a given amount of time even if encounters a block and is still able to break it and collect points.
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Shield extends Token implements Serializable{
	
	private Polygon image;
	private Point location;
	private int y_velocity=2;
	/**
	 * constructor of shield object, initializes the location and generates its corresponding image
	 * Also sets color of image
	 * @param loc
	 */

	public Shield(Point loc) {
		location=loc;
		image = new Polygon();
		double x=loc.getX();
		double y=loc.getY();
		image.getPoints().addAll(new Double[]{ 
		         x-15, y-15, 
		         x+15, y-15, 
		         x+15, y+15,
		         x,y+22,
		         x-15, y+15
		         
		      });
		image.setFill(Color.PINK);
		image.setStroke(Color.WHITE);
		
	}
	
	/**
	 * returns the corresponding image
	 * @return image
	 */
	public Polygon getImage() {
		return image;
	}
	
	

	/**
	 * returns the location of the shield
	 * @return Point
	 */

	public Point getLocation() {
		return location;
	}
	/**
	 * sets the location of the shield
	 * @param location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	
	/**
	 * reuturns the boundary of the corresponding image of the magnet
	 * @return Rectangle2D
	 */
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX()-15,location.getY()-15,30,32);
        return shape;
    }
	/**
	 * moves the image down on the game screen as per its y_velocity, updates the new location as well
	 */
	public void move_down() {
		location.setY(location.getY()+y_velocity);
		image.setTranslateY(location.getY());
	}
	
	
	
}

