package application;

import java.awt.geom.Rectangle2D;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Represents the token which allows snake to automatically eat all the food within a given distance from snake's head 
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Magnet extends Token implements Serializable{
	
	private Rectangle[] image=new Rectangle[4];
	private Point location;
	private int y_velocity=2;
	/**
	 * constructor of magnet object, initializes the location and generates its corresponding image
	 * Also initializes color of each part of image
	 * @param loc
	 */
	public Magnet(Point loc) {
		location=loc;
		image[0] = new Rectangle( loc.getX(),loc.getY(),40,40);
		image[0].setFill(Color.BURLYWOOD);
		image[1] = new Rectangle( loc.getX()+10,loc.getY()+10,20,30);
		image[1].setFill(Color.BLACK);
		image[2] = new Rectangle(loc.getX(),loc.getY()+30,10,10);
		image[2].setFill(Color.WHITE);
		image[3] = new Rectangle(loc.getX()+30,loc.getY()+30,10,10);
		image[3].setFill(Color.WHITE);
		}
	/**
	 * reuturns the boundary of the corresponding image of the magnet
	 * @return Rectangle2D
	 */
	
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX(),location.getY(),40,40);
        return shape;
    }
	
	/**
	 * returns the array of Rectangle shapes which accordingly makes up the entire image of magnet
	 * @return Rectangle[]
	 */
	public Rectangle[] getImage() {
		return image;
	}
	
	/**
	 * returns the location of the magnet object
	 * @return Point
	 */
	
	public Point getLocation() {
		return location;
	}
	/**
	 * sets the location of the magnet object
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
		for(int i=0;i<4;i++) {
			image[i].setTranslateY(location.getY());
		}
	}
}

