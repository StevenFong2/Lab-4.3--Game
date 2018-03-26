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
		Label label = new Label("Times clicked: " + score);
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
				label.setText("Times clicked: " + score);
			}
		});
		
		timeStep = System.nanoTime() + 1000000000L;

		StackPane root = new StackPane();
		root.setAlignment(label, Pos.TOP_CENTER);
		root.getChildren().add(btn);
		root.getChildren().add(label);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}