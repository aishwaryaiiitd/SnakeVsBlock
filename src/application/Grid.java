package application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.io.Serializable;
/**
 * class for storing and generating all objects on the game screen except snake
 * @author Aishwarya Kumar
 * @version 1.0
 *
 */
public class Grid implements Serializable{
	private Random rand;
	private boolean[] taken=new boolean[6];
	private Pane root;
	private static LinkedList<Token> list=new LinkedList<Token>(); 
	private static LinkedList<Animation> block_anim_list=new LinkedList<Animation>();
	
	public static LinkedList<Animation> getBlock_anim_list() {
		return block_anim_list;
	}
	/**
	 * constructor, updates animation list and token list
	 * @param p1
	 */
	public Grid(Pane p1) {
		rand = new Random();
		this.root=p1;
		list_update();
		block_anim_list_update();
		}
	
	/**
	 * updates token list,removes those which have exited the screen
	 */
	public void list_update() {
		for (Iterator<Token> iterator = list.iterator(); iterator.hasNext(); ) {
			Token v = iterator.next();
			if (v.getLocation().getY()>650){
				iterator.remove();
			}
		    }
	}
	
	/**
	 * updates animation object list,removes those which have exited the screen
	 */
	
	public void block_anim_list_update() {
		for (Iterator<Animation> iterator = block_anim_list.iterator(); iterator.hasNext(); ) {
			Animation v = iterator.next();
			if (v.getLocation().getY()>650){
				iterator.remove();
			}
		    }
	}
	
	/**
	 * sets the token list on the screen
	 * @param list
	 */
	
	public static void setList(LinkedList<Token> list) {
		Grid.list = list;
	}
	/**
	 * generates a new animation object for the corresponding block to be destroyed and adds it to the pane
	 * @param block
	 */
	public void generate_block_anim(Block block) {
		Animation block_anim=new Animation(block);
		root.getChildren().addAll(block_anim.getCircle());
		block_anim_list.add(block_anim);
	}
	
	/**
	 * generates a new wall object and image and adds it to the pane
	 */
	public void generate_wall() {
		Point p=new Point(rand.nextInt(468)+12,0);
		Wall wall=new Wall(p,rand.nextInt(40)+40);
		root.getChildren().add(wall.getImage());
		list.add(wall);
	}
	/**
	 * generates a random point
	 */
	
	public Point random_point() {
		int y=0;
		int x=rand.nextInt(480);
		return new Point(x,y);
	}
	/**
	 * generates a random number
	 */
	
	public int random_number(int num) {
		return rand.nextInt(num);
	}
	/**
	 * generates a new magnet object and image and adds it to the pane
	 */
	public void generate_magnet() {
		Point p=new Point(rand.nextInt(460)+20,0);
		Magnet magnet=new Magnet(p);
		root.getChildren().addAll(magnet.getImage());
		list.add(magnet);
	}
	/**
	 * generates a new shield object and image and adds it to the pane
	 */
	public void generate_shield() {
		Point p=new Point(rand.nextInt(465)+15,0);
		Shield shield=new Shield(p);
		root.getChildren().add(shield.getImage());
		list.add(shield);
	}
	/**
	 * generates a new destroy_block object and image and adds it to the pane
	 */
	public void generate_destroy_blocks() {
		Point p=new Point(rand.nextInt(455)+25,0);
		Destroy_Blocks db=new Destroy_Blocks(p);
		root.getChildren().add(db.getBody());
		root.getChildren().add(db.getTop());
		list.add(db);
	}
	/**
	 * generates new block objects and images and adds it to the pane
	 */
	public void generate_blocks() {
		if(rand.nextInt(2)==0) {
			generate_all_blocks();
		}
		else {
			for(int i=0;i<6;i++) {
				if(rand.nextInt(2)==0) {
					Block block=new Block(new Point(i*80,0),rand.nextInt(40)+1);
					root.getChildren().add(block.getImage());
					root.getChildren().add(block.getText());
					list.add(block);
					taken[i]=true;
				}
			}
		}
	}
	
	public void generate_all_blocks() {
		for(int i=0;i<6;i++) {
		Block block=new Block(new Point(i*80,0),rand.nextInt(40)+1);
		root.getChildren().add(block.getImage());
		root.getChildren().add(block.getText());
		list.add(block);
		taken[i]=true;
		}
	}
	/**
	 * generates a new food object and image and adds it to the pane
	 */
	public void generate_food() {
		Point p=new Point(rand.nextInt(468)+12,0);
		Food food =new Food(p,rand.nextInt(20)+1);
		root.getChildren().add(food.getImage());
		root.getChildren().add(food.getText());
		list.add(food);
	}
	public static LinkedList<Token> getList() {
		return list;
	}
	public void generate_power_down() {
		Point p=new Point(rand.nextInt(430)+50,0);
		Power_Down pd=new Power_Down(p);
		root.getChildren().add(pd.getImage());
		list.add(pd);
	}
	
}

