package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * class for leaderboard page of the game
 * @author Deepak Nigam
 * @version 1.0
 *
 */
public class LeaderBoard implements Serializable{
	 private String[] player;
	 private  String[] score;
	 private int HighScore;
	 private int marker;
	
	 	@FXML TextField P1;
	 	@FXML TextField P2;
		@FXML TextField P3;
		@FXML TextField P4;
		@FXML TextField P5;
		@FXML TextField P6;
		@FXML TextField P7;
		@FXML TextField P8;
		@FXML TextField P9;
		@FXML TextField P10;
		@FXML TextField s1;
		@FXML TextField s2;
		@FXML TextField s3;
		@FXML TextField s4;
		@FXML TextField s5;
		@FXML TextField s6;
		@FXML TextField s7;
		@FXML TextField s8;
		@FXML TextField s9;
		@FXML TextField s10;
	/**
	 * returns the list of all players name in the leaderboard
	 * @return player(String[])
	 */
	public String[] getPlayer() {
		return player;
	}
	/**
	 * returns the score of all players name in the leaderboard
	 * @return score(String[])
	 */
	public String[] getScore() {
		return score;
	}
	
	public TextField getP1() {
		return P1;
	}
	
	public TextField getP2() {
		return P2;
	}
	
	public TextField getP3() {
		return P3;
	}
	public TextField getP4() {
		return P4;
	}
	
	public TextField getP5() {
		return P5;
	}
	public TextField getP6() {
		return P6;
	}
	public TextField getP7() {
		return P7;
	}
	public TextField getP8() {
		return P8;
	}
	public TextField getP9() {
		return P9;
	}
	public TextField getP10() {
		return P10;
	}
	public TextField getS1() {
		return s1;
	}
	public TextField getS2() {
		return s2;
	}
	public TextField getS3() {
		return s3;
	}
	public TextField getS4() {
		return s4;
	}
	public TextField getS5() {
		return s5;
	}
	public TextField getS6() {
		return s6;
	}
	public TextField getS7() {
		return s7;
	}
	public TextField getS8() {
		return s8;
	}
	public TextField getS9() {
		return s9;
	}
	public TextField getS10() {
		return s10;
	}
	
	public LeaderBoard() {
		player=new String[10];
		score=new String[10];
		for(int i=0;i<10;++i) {
			player[i]="";
			score[i]="";
			}
	}
	
	public int getMarker() {
		return marker;
	}

	public void setMarker(int marker) {
		this.marker = marker;
	}
	
	/**
	 * is the main contoller of the leaderboard screen
	 */

	public void start(int v) {
		try {
			// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/LeaderBoard.fxml"));
            Main.ext = (Pane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(Main.ext);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
            LeaderBoard controller=((LeaderBoard)(loader.getController()));
            controller.setMarker(v);
            TextField p1=controller.getP1();
            p1.setText(player[0]);
            TextField p2=controller.getP2();
            p2.setText(player[1]);
            TextField p3=controller.getP3();
            p3.setText(player[2]);
            TextField p4=controller.getP4();
            p4.setText(player[3]);
            TextField p5=controller.getP5();
            p5.setText(player[4]);
            TextField p6=controller.getP6();
            p6.setText(player[5]);
            TextField p7=controller.getP7();
            p7.setText(player[6]);
            TextField p8=controller.getP8();
            p8.setText(player[7]);
            TextField p9=controller.getP9();
            p9.setText(player[8]);
            TextField p10=controller.getP10();
            p10.setText(player[9]);
            TextField S1=controller.getS1();
            S1.setText(score[0]);
            TextField S2=controller.getS2();
            S2.setText(score[1]);
            TextField S3=controller.getS3();
            S3.setText(score[2]);
            TextField S4=controller.getS4();
            S4.setText(score[3]);
            TextField S5=controller.getS5();
            S5.setText(score[4]);
            TextField S6=controller.getS6();
            S6.setText(score[5]);
            TextField S7=controller.getS7();
            S7.setText(score[6]);
            TextField S8=controller.getS8();
            S8.setText(score[7]);
            TextField S9=controller.getS9();
            S9.setText(score[8]);
            TextField S10=controller.getS10();
            S10.setText(score[9]);
        }
		
		catch (IOException e){
            e.printStackTrace();
        }
	}
	/**
	 * returns to the start game or resume game screen
	 */
	public void back_button() {
		if(marker==1)
			Main.Rsg.start();
		else if(marker==2)
			Main.Ss.start();
	}
	/**
	 * updates the leaderboard as per the current score
	 * @param name1
	 * @param score1
	 */
	void update(String name1, int score1) {
		int c=0;
		String p1="";
		String s1="";
		String p2="";
		String s2="";
		for(int i=0;i<10;++i) {
			if(c==0) {
				if( score[i].equals("") || score1>Integer.parseInt(score[i])) {
					s1=score[i];
					p1=player[i];
				 	score[i]=Integer.toString(score1);
				 	player[i]=name1;
				 	c=1;
				}
			}
			else {
				p2=player[i];
				s2=score[i];
				player[i]=p1;
				score[i]=s1;
				p1=p2;
				s1=s2;
			}
		}
		HighScore=Integer.parseInt(score[0]);
	}
	
	String[] getPlayerList() {
		return player;
	}
	
	String[] getScoreList() {
		return score;
	}
	/**
	 * returns the highscore
	 * @return highscore
	 */
	int getHighScore() {
		return HighScore;
	}
	/**
	 * checks whether a score can be added to the leaderboard
	 * @param score1
	 * @return boolean
	 */
	boolean check(int score1) {
		if(score[9].equals("") || score1>Integer.parseInt(score[9]))
			return true;
		else
			return false;
	}
	
Object deserialize()throws IOException ,ClassNotFoundException
	
	{ObjectInputStream in=null;
	
	try{
		in =  new ObjectInputStream (new FileInputStream("leaderboard.txt"));
		Object tem=in.readObject();
		return tem;
	}
	catch(IOException e){
		return null;
	}
	finally {
	if(in!=null)
		in.close();
		}
	

}

	
	void serialize()throws IOException ,ClassNotFoundException{
	ObjectOutputStream out = null; 
	try {
		out = new ObjectOutputStream (new FileOutputStream("leaderboard.txt"));
		out.writeObject(this);
	} 
		 finally {
			 out.close();
	}
	}
}

