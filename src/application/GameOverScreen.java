package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * class for game over page of the game when the player does not win
 * @author Deepak Nigam
 * @version 1.0
 *
 */

public class GameOverScreen {
	private int score;
	@FXML private TextField TF;
	@FXML private TextField Highscore;
	/**
	 * returns the highscore which is displayed in a textfield in the screen
	 * @return highscore
	 */
	public TextField getHighscore() {
		return Highscore;
	}
	/**
	 * is the main controller of the game over screen
	 */
	public void start() {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GameOverScreen.fxml"));
          
            Main.ext = (Pane) loader.load();
           
            TextField TF1=((GameOverScreen)(loader.getController())).gettext();
            TF1.setText(Integer.toString(score));
            TextField Highscore1=((GameOverScreen)(loader.getController())).getHighscore();
           
            Highscore1.setText(Integer.toString(Main.Highest_Score));
            // Show the scene containing the root layout.
            Scene scene = new Scene(Main.ext);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * starts the game
	 */
	public void start_button() {
		Main.G.start();
	}
	/**
	 * go to start screen page
	 */
	public void home_button() {
		Main.Ss.start();
}
	/**
	 * sets the score
	 * @param s
	 */
	public void setScore(int s) {
		score=s;
		
	}
	/**
	 * gets the textfield
	 * @return TF
	 */
	public TextField gettext() {
		return TF;
}}
