package ClickingGame;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;

public class Gamecode extends Application {
	private static int score;
	private boolean scoring = true;
	private long timeStep;
	public static void main(String[] args) {
		List<String> highscores = Backend.CSVreader("scores.txt");
		score = Integer.parseInt(highscores.get(0));
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Click");
		Button btn = new Button();
		btn.setText("Start Clicking");
		Text txt = new Text(10, 0, "Click me");

		//StackPane root = new StackPane();
		//GridPane root = new GridPane();
		Pane root = new Pane();
		root.getChildren().addAll(btn,txt);
		btn.relocate(230, 240);
		txt.relocate(240, 100);
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(scoring)
				{
					score++;
					Backend.updateCSV("scores.txt", String.valueOf(score));
				}

				else
				{
					score--;
					Backend.updateCSV("scores.txt", String.valueOf(score));
				}
			}
		});

		timeStep = System.nanoTime() + 1000000000L;
		long time = timeStep;
		new AnimationTimer()
		{
			@Override
			public void handle(long now)
			{
				if (now == time + 10000000000L)
				{
					/*timeStep = 0L;
					now = 0L;
					scoring = true;*/
					score = 0;
					txt.setText("Score: " + Integer.toString(score));
				}
				if (now > timeStep)
				{
					timeStep = now + 1000000000L;
					scoring = !scoring;
				}
				if (!scoring)
				{
					btn.setText("Dont't Click");
				}
				else
				{
					btn.setText("Click Me!");
				}

				txt.setText("Score: " + Integer.toString(score));
			}
		}.start();

	}
}