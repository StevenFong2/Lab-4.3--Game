package ClickingGame;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;


public class Gamecode extends Application {
	private int score = 0;
	private boolean scoring = true;
	private long timeStep;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Click");
		Button btn = new Button();
		btn.setText("Start Clicking");
		Text txt = new Text(10, 0, "Click Score");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(scoring)
				{
					score++;
					btn.setText("Click me");
				}
				
				else
				{
					score--;
					btn.setText("Don't click me");
				}
			}
		});
		
		timeStep = System.nanoTime() + 1000000000L;
		new AnimationTimer()
		{
			public void handle(long now)
			{
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
		
		StackPane root = new StackPane();
		root.setAlignment(txt, Pos.TOP_CENTER);
		root.getChildren().addAll(btn,txt);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}