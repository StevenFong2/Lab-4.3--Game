//CS Java
//Period 2
//Donovan and Steven

package ClickingGame;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Font;

import javafx.scene.text.*;
//import javafx.scene.control.Label;
//import javafx.animation.AnimationTimer;
//import javafx.animation.KeyFrame;
import javafx.animation.*;
//import javafx.animation.Timeline;
import java.util.List;

public class Gamecode extends Application {

	//Keeps track of score
	private int score = 0;
	private boolean scoring = true;
	private List<String> highScores;
	
	//For oscillator
	private long timeStep;
	private AnimationTimer oscillator;
	
	//For game timer/time limit
	private Timeline timeline = new Timeline();
	private final Integer startTime = 3;
	private Integer timeSeconds = startTime;
	private Text timer = new Text();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		StackPane root = new StackPane();
		//GridPane root = new GridPane();
		//Pane root = new Pane();
		
		Text scoreBoard = new Text();
		scoreBoard.setTextAlignment(TextAlignment.CENTER);
		scoreBoard.setFont(new Font(15));
		
		Button clicker = new Button();
		Text currentSc = new Text(10, 0, "Click me");
		currentSc.setFont(new Font(50));
		timer.setFont(new Font(50));
		
		Button start = new Button();
		Button replay = new Button();
		
		StackPane.setAlignment(currentSc, Pos.TOP_CENTER);
		StackPane.setAlignment(timer, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(scoreBoard, Pos.TOP_CENTER);
		root.getChildren().add(start);
		
		start.setText("Start Clicking!!");
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				timeStep = System.nanoTime() + 1000000000L;
				oscillator = new AnimationTimer() {
					
					@Override
					public void handle(long now) {

						if(now>timeStep) {
							timeStep = now + 1000000000L;
							scoring = !scoring;
						}
						if(!scoring) {
							clicker.setText("Dont't Click");
						}
						else {
							clicker.setText("Click Me!");
						}

						currentSc.setText("Score: " + Integer.toString(score));
					}
				};
				
				//Creating Timer
				
				timeline.setCycleCount(Timeline.INDEFINITE);
				
				if (timeline!=null) {
					timeline.stop();
				}
				
				timer.setText("Time Left: " + timeSeconds.toString());
				
				KeyFrame keyframe = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						timeSeconds--;
						timer.setText("Time Left: " + timeSeconds.toString());
						
						if(timeSeconds<=0) {
							timeline.stop();
							oscillator.stop();
							
							scoreBoard.setText(scoreBoard(score));
							
							root.getChildren().removeAll(clicker,currentSc,timer);
							root.getChildren().addAll(replay,scoreBoard);
						}
					}
				});
				
				timeline.getKeyFrames().add(keyframe);
				
				root.getChildren().remove(start);
				root.getChildren().addAll(clicker,timer,currentSc);
				timeline.playFromStart();
				oscillator.start();
			}
		});
		
		replay.setText("Replay");
		replay.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override 
			public void handle(ActionEvent event) {
				
				timeStep = System.nanoTime() + 1000000000L;
				oscillator = new AnimationTimer() {
					
					@Override
					public void handle(long now) {

						if(now>timeStep) {
							timeStep = now + 1000000000L;
							scoring = !scoring;
						}
						if(!scoring) {
							clicker.setText("Dont't Click");
						}
						else {
							clicker.setText("Click Me!");
						}

						currentSc.setText("Score: " + Integer.toString(score));
					}
				};
				
				score = 0;
				currentSc.setText("Score: " + Integer.toString(score));
				
				timeSeconds = startTime;
				timer.setText("Time Left: " + timeSeconds.toString());
				
				root.getChildren().removeAll(replay,scoreBoard);
				root.getChildren().addAll(clicker,currentSc,timer);
				
				oscillator.start();
				timeline.playFromStart();
			}
			
		});
			
		//clicker.relocate(230, 240);
		//currentSc.relocate(240, 100);
		//timer.relocate(100, 100);
		
		primaryStage.setTitle("Klik");
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();

		clicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(scoring) {
					score++;
				}

				else if(!scoring  && score > 0){
					score--;
				}
				else {
					
				}
				
				//Backend.updateCSV("scores.txt", score);
			}
		});
	}
	
	public String scoreBoard(int num) {
		
		int place = Backend.updateCSV("scores.txt", num);
		highScores = Backend.CSVreader("scores.txt");
		int n = highScores.size();
		
		String main = highScores.get(0);
		String addOn = "";
		
		for(int i = 1; i < n - 1; i+=2) {
			main = main + "\n" + highScores.get(i) + highScores.get(i+1); 
		}
		
		if(place > 0) {
			addOn = "Congradulations You Beat Your Previous " + "#" + place + " Best!";
		}
		
		else if(place < 0) {
			addOn = "Unfortunately You Are Not Very Good";
		}
		
		return main + "\n" + addOn;
	}
}