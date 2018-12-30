package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.MenuButton;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class game {
	@FXML private MenuButton mb;
	private game connection;
	@FXML private Pane p;
	private int score;
	private final int W=480,H=620;
	private Text text;
	private Snake snake;
	AnimationTimer timer;
	
	
	public MenuButton getMb() {
		return mb;
	}
	
	public game getConnection() {
		return connection;
	}

	public void setConnection(game connection) {
		this.connection = connection;
	}

	public void setMb(MenuButton mb) {
		this.mb = mb;
	}

	public Pane getP() {
		return p;
	}
	
	public static LinkedList<Token> generate_blocks(Pane p1) {
    	Grid grid=new Grid(p1);
    	grid.generate_blocks();
    	return grid.getList();
    }
   
    public static LinkedList<Token> generate_objects(Pane p1) {
    	Grid grid=new Grid(p1);
    	int i=grid.random_number(55);
    	if(i<30) {
    		grid.generate_food();
    	}
    	else if(i<35) {
    		grid.generate_magnet();
    	}
    	else if(i<40) {
    		grid.generate_wall();
    	}
    	else if(i<45) {
    		grid.generate_destroy_blocks();
    	}
    	else if(i<50) {
    		grid.generate_shield();
    	}
    	else {
    		grid.generate_power_down();
    	}
    	return grid.getList();
    }
    
    
    public static LinkedList<Animation> generate_animation(Block block,Grid grid){
    	grid.block_anim_list_update();
    	grid.generate_block_anim(block);
    	return grid.getBlock_anim_list();
    }
    
    
	
	public AnimationTimer getTimer() {
		return timer;
	}

	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}

	public void start() {
		try {
			score=0;
			text = new Text(20, 40, Integer.toString(score));
			text.setFont(new Font(14));
			text.setFill(Color.WHITE);
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/game.fxml"));
            Main.ext = (Pane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(Main.ext);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
            game g=(game)loader.getController();
            g.setConnection(this);
            Pane p1=g.getP();
            Canvas canvas= new Canvas(W,H);
            p1.getChildren().add( canvas );
            GraphicsContext gc = canvas.getGraphicsContext2D();
             
            p1.getChildren().add( text );
            gc.setFill( Color.BLACK );
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            	 
    	    final long startNanoTime = System.nanoTime();
    	    snake=new Snake(new Point(240,400),4);
    	    ArrayList<String> input = new ArrayList<String>();
    	    
    	    
    	    
            p1.getChildren().addAll(snake.getImage());
    	    p1.getChildren().add(snake.getText());
    	   
    	    scene.setOnKeyPressed(
    	    		new EventHandler<KeyEvent>(){
    	    			public void handle(KeyEvent e){
                            String code = e.getCode().toString();
         
                            // only add once... prevent duplicates
                            if ( !input.contains(code) )
                                input.add( code );
                        }
                    });
     
            scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });
           
            
            g.setP(p1);
            timer=new AnimationTimer(){ 
            	
            	int count=0,isBlock=0,isWall_left=0,isWall_right=0,shield_timer=0,magnet_timer=0,power_down=0,isBlockDestroy=0;
            	LinkedList<Token> list= generate_objects(p1);
            	LinkedList<Animation> block_anim_list=new LinkedList<Animation>(); 
            	Grid grid=new Grid(p1);
            	
            	public AnimationTimer gettimer() {
            		return this;
            	}
            	
                public void handle(long currentNanoTime)
                {
                	try {
                	count++;
                	
                	if(power_down>600) {
                		power_down=0;
                	}
                	else if(power_down>0) {
                		power_down++;
                	}
                	
//                	if(power_up>600) {
//                		power_up=0;
//                	}
//                	else if(power_up>0) {
//                		
//                	}
                	
                	if(isBlockDestroy>600) {
                		isBlockDestroy=0;
                		for(Animation v:block_anim_list) {
                			for(int i=0;i<8;i++)
                				v.getCircle()[i].setTranslateY(2000);
                				v.getLocation().setY(2000);
                		}
                	}
                	else if(isBlockDestroy>0) {
                	isBlockDestroy++;
                	for(Animation v:block_anim_list) 
            			v.move_animation();
            		}
                	
                	if(shield_timer>=450) {
                		shield_timer=0;
                		snake.getImage().get(0).setFill(Color.BLUE);
                	}
                	else if(shield_timer>0) {
                		shield_timer++;
                	}
                	
                	if(magnet_timer>=450) {
                		magnet_timer=0;
                		
                	}
                	else if(magnet_timer>0) {
                		magnet_timer++;
                		for(Token u:list) {
        					if(u instanceof Food) {
        						if(snake.is_in_zone((Food) u)) {
        							snake.increase_length(((Food) u).getValue());
                    				((Food) u).getImage().setTranslateY(1000);
                    				((Food) u).getText().setTranslateY(1000);
                    				((Food) u).setLocation(new Point(800,800));
        						}
        					}
        				}
                	}
                	
                	 
                	for(Token v:list) {
                		if(snake.intersects(v)) {
                			if(v instanceof Block) {
                				if(shield_timer>0 && shield_timer<450) {
                					block_anim_list=generate_animation(((Block)v),grid);
                					isBlockDestroy++;
                					score+=((Block) v).getValue();
                					text.setText(Integer.toString(score));
                					((Block) v).getImage().setTranslateY(1000);
                    				((Block) v).getText().setTranslateY(1000);
                    				((Block) v).setLocation(new Point(900,900));
                				}
                				else {
    	            				isBlock=1;
    	            				((Block) v).reduceValue();
    	            				score+=1;
    	            				text.setText(Integer.toString(score));
    	            				snake.reduce();
    	            				if(((Block) v).getValue()<=0) {
    	            					isBlock=0;
    	            					block_anim_list=generate_animation(((Block)v),grid);
    	            					isBlockDestroy++;
    	            					((Block) v).getImage().setTranslateY(1000);
    	                				((Block) v).getText().setTranslateY(1000);
    	                				((Block) v).setLocation(new Point(900,900));
    	            				}
                				}
                			}
                			
                			else if(v instanceof Food) {
                				snake.increase_length(((Food) v).getValue());
                				((Food) v).getImage().setTranslateY(1000);
                				((Food) v).getText().setTranslateY(1000);
                				((Food) v).setLocation(new Point(800,800));
                			}
                			
                			else if(v instanceof Wall) {
                				if(v.getLocation().getX()<snake.getHead().getX())
                					isWall_left=1;
                				else {
                					isWall_right=1;
                				}
                			}
                			
                			else if(v instanceof Destroy_Blocks) {
                				for(Token u:list) {
                					if(u instanceof Block) {
                						block_anim_list=generate_animation(((Block)u),grid);
                						isBlockDestroy++;
                						score+=((Block) u).getValue();
                						text.setText(Integer.toString(score));
                						((Block) u).getImage().setTranslateY(1000);
                        				((Block) u).getText().setTranslateY(1000);
                        				((Block) u).setLocation(new Point(900,900));
                					}
                				}
                				((Destroy_Blocks) v).getBody().setTranslateY(1000);
                				((Destroy_Blocks) v).getTop().setTranslateY(1000);
                				((Destroy_Blocks) v).setLocation(new Point(800,800));
                			}
                			
                			else if(v instanceof Shield) {
                				snake.getImage().get(0).setFill(Color.LIGHTGREEN);
                				shield_timer++;
                				((Shield) v).getImage().setTranslateY(1000);
                				((Shield) v).setLocation(new Point(800,800));
                				
                			}
                			
                			else if(v instanceof Magnet) {
                				magnet_timer++;
                				for(int i=0;i<4;i++) {
                					((Magnet) v).getImage()[i].setTranslateY(1000);
                				}
                				((Magnet) v).setLocation(new Point(800,800));
                				
                			}
                			else if(v instanceof Power_Down) {
                				power_down++;
                			}
                		}
                	}
                	
                	if(isBlock==0) {
                		if(count%150==0) {
                    		list=generate_blocks(p1);
                    	 }
                    	 
                    	 else if(count%50==0) {
                    		 list=generate_objects(p1);
                    	 }
                		for(Token v:list) {
                			v.move_down();
                		}
                	}
                	isBlock=0;

                     if (input.contains("LEFT") && isWall_left==0){
                    	 	if(snake.getHead().getX()>0 && power_down==0)
                    	 		snake.move_left();
                    	 	else if(snake.getHead().getX()<480 && power_down>0)
                    	 		snake.move_right();
                        }
                     if (input.contains("RIGHT") && isWall_right==0){
                    	 if(snake.getHead().getX()<480 && power_down==0)
                            snake.move_right();
                    	 else if(snake.getHead().getX()>0 && power_down>0)
                 	 		snake.move_left();
                            
                        }
                     isWall_left=0;isWall_right=0;

             }
             catch(IndexOutOfBoundsException e) {
            	 
            	
            	 this.stop();
            	 Main.ext.getChildren().removeAll();
            	 select_over();
             }
                	}
                
            };
            
            timer.start();  
              
            
           
            
            
        }
		
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	public void restart_button() {
		connection.getTimer().stop();
		Main.ext.getChildren().removeAll();
		Grid ab=new Grid(null);
		 ab.setList(new LinkedList<Token>());
		Main.G.start();
	}
	
	public void home_button() {
		connection.getTimer().stop();
		Main.ext.getChildren().removeAll();
		//serializehere
		Grid ab=new Grid(null);
		ab.setList(new LinkedList<Token>());
		Main.Rsg.start();
}
	
public void select_over() {
	
	 Grid ab=new Grid(null);
	 ab.setList(new LinkedList<Token>());
	boolean a=Main.Board.check(score);
	check_high();
	if(a) {
		
		
		GameOverWin GOW=new GameOverWin();
		GOW.setWinscore(score);
		GOW.start();
	}
	else {
		GameOverScreen GOS=new GameOverScreen();
		GOS.setScore(score);
		GOS.start();
	}
}


public void setP(Pane p) {
	this.p = p;
}




public void check_high() {
	if(score>Main.Highest_Score) {
		Main.Highest_Score=score;
	}
}


}



