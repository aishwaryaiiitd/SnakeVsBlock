package application;

import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
/**
 * Represents the token which the increases the length of the snake by a particular value when it eats it
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Food extends Token implements Serializable{
	
	private int value,y_velocity=2;
	private Point location;
	private Circle image; 
	private Text text;
	
	/**
	 * constructor of food object, initializes the value and the location and generates its corresponding image and text
	 * Also sets color of food image(circle) to yellow
	 * @param loc
	 * @param val
	 */
	public Food(Point loc,int val) {
		value=val;
		location=loc;
		image = new Circle( loc.getX(),loc.getY(),12);
		image.setFill(Color.YELLOW);
		text = new Text(loc.getX(), loc.getY(), Integer.toString(val));
		text.setFont(new Font(10));
	}
	
	/**
	 * Returns the food value which will be displayed on the screen as a text over each food
	 * @return Text
	 */
	public Text getText() {
		return text;
	}
	
	/**
	 * reuturns the boundary of the corresponding image of the food
	 * @return Rectangle2D
	 */
	
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX()-12,location.getY()-12,24,24);
        return shape;
    }
	/**
	 * returns the corresponding image of food
	 * @return Circle
	 */
	public Circle getImage() {
		return image;
	}
	
	/**
	 * returns the value of food
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * sets the value of the food
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * returns the location of the food object
	 * @return Point
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * sets the location of the food object
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
		text.setTranslateY(location.getY());
	}
}
