package application;

import java.util.LinkedList;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
/**
 * Represents the snake object
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Snake implements Serializable {
	private int length,radius=12;
	private int effect=200;
	private Point head;
	private LinkedList<Point> points;
	private boolean alive;
	private int x_velocity=4;
	private LinkedList<Circle> images;
	private Text text;
	private LinkedList<Integer> origin;
	
	/**
	 * returns the snake's length which will be displayed on the screen as a text over each snake
	 * @return text
	 */
	public Text getText() {
		return text;
	}
	/**
	 * returns radius of the each body(circle) snake
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 * returns x_velocity of snake(speed at which snakes moves)
	 * @return x_velocity
	 */
	public int getX_velocity() {
		return x_velocity;
	}

	/**
	 * constructor of snake object, intializes the head of the snake and the length and generates the rest of the body as per its length
	 * text and images are also initialized here and color is assigned to image and text
	 * @param _head
	 * @param _length
	 */

	public Snake(Point _head,int _length) {
		head=_head;
		length=_length;
		points=new LinkedList<Point>();
		origin=new LinkedList<Integer>();
		text = new Text(head.getX(), head.getY(), Integer.toString(_length));
		text.setFont(new Font(14));
		text.setFill(Color.WHITE);
		images=new LinkedList<Circle>();
		images.add(new Circle(head.getX(),head.getY(),radius));
		images.get(0).setFill(Color.BLUE);
		origin.add(head.getX());
		points.add(head);
		for(int i=1;i<=length-1;i++) {
			Point prev=points.get(i-1);
			Point p=new Point(prev.getX(),prev.getY()+2*radius);
			points.add(p);
			origin.add(p.getX());
			images.add(new Circle(prev.getX(),prev.getY()+2*radius,radius));
			images.get(i).setFill(Color.BLUE);
		}
	}
	/**
	 * returns the list of images of each body of the snakes from its head to tail
	 * @return images
	 */
	public LinkedList<Circle> getImage() {
		return images;
	}
	/**
	 * returns the length of the snake
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * sets the length of the snake
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * returns the location of head of the snake
	 * @return head
	 */
	public Point getHead() {
		return head;
	}
	/**
	 * sets the location of head of the snake
	 * @param head
	 */
	public void setHead(Point head) {
		this.head = head;
	}
	/**
	 * returns the list of location of each body of the snake
	 * @return points
	 */
	public LinkedList<Point> getPoints() {
		return points;
	}
	/**
	 * sets the location of each body of the snake
	 * @param points
	 */
	public void setPoints(LinkedList<Point> points) {
		this.points = points;
	}
	
	/**
	 * reduces the length of the snake by 1
	 * throws an exception when snake's length is less than or equal to zero
	 * @throws IndexOutOfBoundsException
	 */
	
	public void reduce() throws IndexOutOfBoundsException{
		try {
		points.remove(length-1);
		images.get(length-1).setTranslateY(900);
		images.remove(length-1);
		
		origin.remove(length-1);
		length-=1;
		
		text.setText(Integer.toString(length));}
		catch(IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * increases the length of the snake by 1
	 */
	public void extend() {
		Pane root=Main.ext;
		Point tail=points.get(length-1);
		Point p=new Point(tail.getX(),tail.getY()+2*radius);
		points.add(p);
		images.add(new Circle(p.getX(),p.getY(),radius));
		origin.add(p.getX());
		images.get(length).setFill(Color.BLUE);
		root.getChildren().add(images.get(length));
		length+=1;
		text.setText(Integer.toString(length));
	}
	
	/**
	 * increases the length of the snake by n
	 * @param n
	 */
	public void increase_length(int n) {
		for(int i=0;i<n;i++)
			extend();
	}
	
	/**
	 * moves the entire snake to the left as per its x-velocity
	 */
	public void move_left() {
		for(int i=0;i<=length-1;i++) {
			Point p=points.get(i);
			p.setX(p.getX()-x_velocity);
			images.get(i).setTranslateX(p.getX()-origin.get(i));
			
		}
		text.setTranslateX(points.get(0).getX()-240);
	}
	/**
	 * moves the entire snake to the right as per its x-velocity
	 */
	public void move_right() {
		for(int i=0;i<=length-1;i++) {
			Point p=points.get(i);
			p.setX(p.getX()+x_velocity);
			images.get(i).setTranslateX(p.getX()-origin.get(i));
		}
		text.setTranslateX(points.get(0).getX()-240);
	}
	/**
	 * returns the boundary of the corresponding head of the snake
	 * @return Rectangle2D
	 */
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(head.getX()-12,head.getY()-12,24,18);
        return shape;
    }
	
	/**
	 * returns the boundary of the corresponding whole body of the snake
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	
	public Rectangle2D getBoundary_for_wall() throws IndexOutOfBoundsException
    {try {
    	Rectangle2D shape = new Rectangle2D.Float();
    	int y=points.get(length-1).getY();
    	if (y>600)
    		shape.setFrame(head.getX()-12,head.getY()-12,24,600-(head.getY()-12));
    	else {
    		shape.setFrame(head.getX()-12,head.getY()-12,24,head.getY()-y+24);
    	}
        return shape;}
    catch(IndexOutOfBoundsException e){
    	throw e;
    }
    }
	
	/**
	 * checks if the snake's length is greater than zero or not
	 * @return boolean
	 */
	public boolean isalive() {
		if(length>=1) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * checks if the food is with a required distance from snake's head
	 * @return boolean
	 */
	public boolean is_in_zone(Food food) {
		int food_x=food.getLocation().getX(),food_y=food.getLocation().getY();
		int mag_x=this.getHead().getX(),mag_y=this.getHead().getY();
		if(food_x>(mag_x-effect) && food_x<(mag_x+effect) && food_y>(mag_y-effect) && food_y<(mag_y+effect) ) {
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * check if the snake intersercts with any other object on the screen
	 * @param s
	 * @return boolean
	 * @throws IndexOutOfBoundsException
	 */
	public boolean intersects(Token s)throws IndexOutOfBoundsException
    {	try{if(s instanceof Wall) {
    	return s.getBoundary().intersects( this.getBoundary_for_wall() );
    	}
    else
        return s.getBoundary().intersects( this.getBoundary() );
    }
	catch(IndexOutOfBoundsException e) {
		throw e;
	}
}}

