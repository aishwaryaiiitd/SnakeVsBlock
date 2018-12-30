package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * class for resume game page of the game
 * @author Deepak Nigam
 * @version 1.0
 *
 */
public class ResumeGame {
	@FXML private TextField Highscore;
	/**
	 * returns the highscore which is displayed in a textfield in the screen
	 * @return highscore
	 */
	public TextField getHighscore() {
		return Highscore;
	}
	/**
	 * is the main contoller of the resume game screen
	 */
	public void start() {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ResumeGame.fxml"));
          
            Main.ext = (Pane) loader.load();
            
            // Show the scene containing the root layout.
            TextField Highscore1=((ResumeGame)(loader.getController())).getHighscore();
            
            Highscore1.setText(Integer.toString(Main.Highest_Score));
            Scene scene = new Scene(Main.ext);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * resumes the previous game
	 */
	public void resume_button() {
		Main.G.start();
	}
	/**
	 * starts the game
	 */
	public void start_button() {
		
		Main.G.start();
	}
	/**
	 * sends to the leaderboard page
	 */
	public void leaderboard_button() {
		
		Main.Board.start(1);}
	/**
	 * exits the game
	 */
		public void quit() {
			try {
				Main.Board.serialize();
			}
			catch(Exception e) {
				
			}
			Main.primaryStage.close();
}}
