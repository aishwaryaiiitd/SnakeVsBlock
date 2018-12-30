package application;


import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Power_Down extends Token implements Serializable{
	private Point location;
	private int y_velocity=2;
	private Text text;
	public Power_Down(Point loc) {
		location=loc;
		text=new Text(loc.getX(),loc.getY(),"X");
		text.setFont(new Font(50));
		text.setFill(Color.RED);
	}
	
	public Rectangle2D getBoundary()
    {
    	Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(location.getX(),location.getY()-50,50,50);
        return shape;
    }
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Text getImage() {
		return text;
	}
	
	public void move_down() {
		location.setY(location.getY()+y_velocity);
		text.setTranslateY(location.getY());
	}
}
