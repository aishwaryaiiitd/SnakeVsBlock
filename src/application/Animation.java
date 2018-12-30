package application;

import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import java.io.Serializable;

/**Represents the burst object when a brick is destroyed or a token is collected
 * @author Aishwarya Kumar
 * Version 1.0
 */
public class Animation implements Serializable{
	
	private Block block;
	/**
	 * x and y velocity of burst objects
	 */
	int x_velocity=3,y_velocity=3;
	int i=0;
	/**
	 * list of burst object
	 */
	private Circle[] circle=new Circle[8];
	private Point location;
	private Color color;
	private Color[] color_arr = {Color.RED,Color.GREEN,Color.BLANCHEDALMOND,Color.CORNFLOWERBLUE,Color.ORANGE};
	/**
	 * contructor of the burst objects, initialises the postion and color(copies the color of the block passed through arguement)
	 * @param block
	 */
	public Animation(Block block) {
		this.block=block;
		Point p=block.getLocation();
		location=new Point(p.getX()+40,p.getY()+40);
		initialize_color();
		initialize_circles();
	}
	
	/**initialises the circles which comes when a block is destroyed
	 * 
	 */
	public void initialize_circles() {
		circle[0]=new Circle(location.getX()-20,location.getY()-20,5);
		circle[1]=new Circle(location.getX()-20,location.getY(),5);
		circle[2]=new Circle(location.getX()-20,location.getY()+20,5);
		circle[3]=new Circle(location.getX(),location.getY()-20,5);
		circle[4]=new Circle(location.getX(),location.getY()+20,5);
		circle[5]=new Circle(location.getX()+20,location.getY()-20,5);
		circle[6]=new Circle(location.getX()+20,location.getY(),5);
		circle[7]=new Circle(location.getX()+20,location.getY()+20,5);
		for(int i=0;i<8;i++) {
			circle[i].setFill(color);
		}
	}
	
	/**initialises the color of the burst which comes when a block is destroyed
	 * 
	 */
	public void initialize_color() {
		for(int i=0;i<5;i++) {
			if(block.getImage().getFill().equals(color_arr[i])){
				color=color_arr[i];
			}
		}
			
	}
	
	/**
	 * changes the location of each particular circle of the burst with time 
	 */
	
	public void move_animation() {
			i++;
			circle[0].setTranslateX(circle[0].getCenterX()-i*x_velocity);
			circle[0].setTranslateY(circle[0].getCenterY()-i*y_velocity);
			circle[1].setTranslateX(circle[1].getCenterX()-i*x_velocity);
			circle[2].setTranslateX(circle[2].getCenterX()-i*x_velocity);
			circle[2].setTranslateY(circle[2].getCenterY()+i*y_velocity);
			circle[3].setTranslateY(circle[3].getCenterY()-i*y_velocity);
			circle[4].setTranslateY(circle[4].getCenterY()+i*y_velocity);
			circle[5].setTranslateX(circle[5].getCenterX()+i*x_velocity);
			circle[5].setTranslateY(circle[5].getCenterY()-i*y_velocity);
			circle[6].setTranslateX(circle[6].getCenterX()+i*x_velocity);
			circle[7].setTranslateX(circle[7].getCenterX()+i*x_velocity);
			circle[7].setTranslateY(circle[7].getCenterY()+i*y_velocity);
	}
	/**
	 * returns the list of burst objects(circles)
	 * @return Circle[]
	 */
	
	public Circle[] getCircle() {
		return circle;
	}
	
	/**
	 * returns the location of the centre of the burst objects
	 * @return location
	 */

	public Point getLocation() {
		return location;
	}
}

