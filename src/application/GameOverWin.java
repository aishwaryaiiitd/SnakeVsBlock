package application;


import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/**
 * class for game over page of the game when the player wins
 * @author Deepak Nigam
 * @version 1.0
 *
 */
public class GameOverWin {
	GameOverWin controller;
	private String Winner;
	public int winscore; 
	@FXML private TextField Highscore;
	/**
	 * returns the highscore which is displayed in a textfield in the screen
	 * @return highscore
	 */
	public TextField getHighscore() {
		return Highscore;
	}
	
	@FXML
	private TextField Wintext;
	/**
	 * returns the text to be displayed notifying that the user has a new highscore
	 * @return Winttext
	 */
	public TextField getWintext() {
		return Wintext;
	}
	/**
	 * sets the text to be displayed notifying that the user has a new highscore
	 * @param wintext
	 */
	public void setWintext(TextField wintext) {
		Wintext = wintext;
	}
	@FXML
	private TextField NameText;
	/**
	 * returns the name of the user 
	 * @return NameText
	 */
	public TextField getNameText() {
		return NameText;
	}
	/**
	 * sets the name of the user
	 * @param nameText
	 */
	public void setNameText(TextField nameText) {
		NameText = nameText;
	}
	/**
	 * is the main controller of the game over screen
	 */
	public void start() {
		try {
            // Load root layout from fxml file.
            
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GameOverWin.fxml"));
            Main.ext = (Pane) loader.load();
            Scene scene = new Scene(Main.ext);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
    		GameOverWin controller=((GameOverWin)(loader.getController()));
    		controller.setWinscore(winscore);
            TextField Wintext1=controller.getWintext();
            TextField HighScore1=controller.getHighscore();
            
            HighScore1.setText(Integer.toString(Main.Highest_Score));
            Wintext1.setText(Integer.toString(winscore));
           
           
            
            // Show the scene containing the root layout.
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * saves the players name and score to the leaderboard and returns to the start screen
	 */
	public void continue_button() {
		
		
		
		if(! NameText.getText().equals("")) {
			Winner=NameText.getText();
			
			Main.Board.update(Winner, winscore);
			Main.Ss.start();
		}
	}
	/**
	 * sets the score of the player
	 * @param winscore
	 */
	public void setWinscore(int winscore) {
		
		this.winscore=winscore;
		
	}
	
	

}
