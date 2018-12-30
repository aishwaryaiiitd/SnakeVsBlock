package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * class for start screen of the game
 * @author Deepak Nigam
 * @version 1.0
 *
 */
public class Startscreen {
	@FXML private TextField Highscore;
	
	/**
	 * returns the highscore which is diplayed in a textfield in the screen
	 * @return highscore
	 */
	public TextField getHighscore() {
		return Highscore;
	}
	/**
	 * is the main contoller of the start screen
	 */
	public void start() {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Startscreen.fxml"));
          
            Main.ext = (Pane) loader.load();
            TextField Highscore1=((Startscreen)(loader.getController())).getHighscore();
            
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
	 * sends to the leaderboard page
	 */
	public void leaderboard_button() {
	
		Main.Board.start(2);
	
}
	/**
	 * exits the game
	 */
	public void Quit_button() {
		try {
			Main.Board.serialize();
		}
		catch(Exception e) {
			
		}
		Main.primaryStage.close();
	}}
