package application;

import java.awt.geom.Rectangle2D;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Represents the block object which the snake needs to destroy
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Block extends Token implements Serializable{
	private int value,y_velocity=2;
	private Point location;
	private Rectangle image;
	/**
	 * list of possible colors of the block
	 */
	private Color[] color = {Color.RED,Color.GREEN,Color.BLANCHEDALMOND,Color.CORNFLOWERBLUE,Color.ORANGE};
	private Text text;
	
	/**
	 * Returns the block value which will be displayed on the screen as a text over each block
	 * @return Text
	 */
	public Text getText() {
		return text;
	}

	/**
	 * constructor of block object, initialises the value and the location and generates its corresponding image
	 * Also initializes colour of each block randomly from the color list
	 * @param loc
	 * @param _value
	 */
	public Block(Point loc,int _value) {
		location=loc;
		value=_value;
		Random rand=new Random();
		text = new Text(loc.getX()+30, loc.getY()+20, Integer.toString(_value));
		text.setFont(new Font(20));
		text.setFill(Color.BLACK);
		image = new Rectangle(loc.getX(),loc.getY(),80,80);
		image.setFill(color[rand.nextInt(5)]);
		image.setStroke(Color.BLACK);
	}
	
	/**
	 * returns the corresponding image of each block
	 * @return Rectangle
	 */
	public Rectangle getImage() {
		return image;
	}
	
	/**
	 * returns the value of block
	 * @return
	 */

	public int getValue() {
		return value;
	}
	
	/**
	 * reduces the value of the block(happens when the snake start to break the block)
	 */
	public void reduceValue() {
		
		if(value>0) {
			value = value-1;
			text.setText(Integer.toString(value));
		}
	}
	/**
	 * returns the location of the block
	 * @return Point
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * sets the location of the block
	 * @param location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	/**
	 * reuturns the boundary of the corresponding image of the block
	 * @return Rectangle2D
	 */
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX(),location.getY()+60,80,20);
        return shape;
    }
	/**
	 * moves the block down on the game screen as per its y_velocity
	 */
	public void move_down() {
		location.setY(location.getY()+y_velocity);
		image.setTranslateY(location.getY());
		text.setTranslateY(location.getY()+20);
	}
}

