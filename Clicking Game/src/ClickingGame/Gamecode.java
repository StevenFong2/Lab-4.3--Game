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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.*;
import javafx.animation.Timeline;
import java.util.List;

public class Gamecode extends Application {

	private static int score;
	private boolean scoring = true;
	private long timeStep;

	private final Integer startTime = 30;
	private Integer timeSeconds = startTime;
	private Text time = new Text();
	
	private AnimationTimer timer;
	

	public static void main(String[] args) {
		List<String> highscores = Backend.CSVreader("scores.txt");
		score = Integer.parseInt(highscores.get(0));
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		//StackPane root = new StackPane();
		//GridPane root = new GridPane();
		Pane root = new Pane();
		
		Button clicker = new Button();
		Text txt = new Text(10, 0, "Click me");
		
		Button replay = new Button();
		Button start = new Button();
		root.getChildren().addAll(start);
		
		start.setText("Start Clicking!!");
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				timeStep = System.nanoTime() + 1000000000L;
				timer = new AnimationTimer() {
					
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

						txt.setText("Score: " + Integer.toString(score));
					}
				};
				
				Timeline timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				
				if (timeline!=null) {
					timeline.stop();
				}
				
				time.setText("Time Left: " + timeSeconds.toString());
				
				KeyFrame keyframe = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						timeSeconds--;
						time.setText("Time Left: " + timeSeconds.toString());
						
						if(timeSeconds<=0) {
							timeline.stop();
							
							root.getChildren().removeAll(clicker,txt,time);
							root.getChildren().add(replay);
							timer.stop();
						}
					}
				});
				
				timeline.getKeyFrames().add(keyframe);
				
				root.getChildren().remove(start);
				root.getChildren().addAll(clicker,time,txt);
				timeline.playFromStart();
				timer.start();
			}
		});
		
		replay.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override 
			public void handle(ActionEvent event) {
				
				root.getChildren().remove(replay);
				root.getChildren().addAll(clicker,txt,time);
				
				timeStep = System.nanoTime() + 1000000000L;
				timer = new AnimationTimer() {
					
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

						txt.setText("Score: " + Integer.toString(score));
					}
				
				};
				
				timer.start();
			}
			
		});
			
		clicker.relocate(230, 240);
		txt.relocate(240, 100);
		time.relocate(100, 100);
		//startTimer();
		
		primaryStage.setTitle("Click");
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();

		clicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(scoring) {
					score++;
				}

				else {
					score--;
				}
				
				Backend.updateCSV("scores.txt", String.valueOf(score));
			}
		});

		/*timeStep = System.nanoTime() + 1000000000L;
		AnimationTimer timer = new AnimationTimer() {
			
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

				txt.setText("Score: " + Integer.toString(score));
			}
		
		};*/

	}
}