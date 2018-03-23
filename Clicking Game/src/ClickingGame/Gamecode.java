package ClickingGame;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class Gamecode extends Application {
	private int score = 0;
	private boolean scoring = true;
	private lon timeStep;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Click");
		Button btn = new Button();
		Text txt = new Text(10, 0, "Click Score");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(scoring)
				{
					score++;
				}
				
				else
				{
					score--;
				}
			}
		});
		
		timeStep = System.nanoTime() + 1000000000L;

		StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}