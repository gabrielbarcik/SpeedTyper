/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JTextArea;

/**
 *
 * @author barcik
 */
public class SpeedTyper extends Application {

    Stage window;
    int maximumSocre;
    HashMap<String, Integer> users = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        window.setTitle("SpeedTyper");
        
        // Defining 
        VBox vbox = new VBox(20);

        // Defining grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // SplitPlane
        SplitPane splitPane = new SplitPane();
        splitPane.setPrefSize(200, 200);
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setPadding(new Insets(15, 0, 15, 0));
                
        // Best score
        Text bestScore = new Text("Best score is: " + maximumSocre);
        splitPane.getItems().add(bestScore);
//        grid.add(bestScore, 0, 1);

        // Insert name and Starting button
        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPadding(new Insets(15, 15, 10, 15));
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);
        Button startBtn = new Button("start");
        startBtn.setOnAction(e -> users.put(userTextField.getText(), 0));
        grid.add(startBtn, 2, 2);
        
        
        nameBox.getChildren().addAll(userName, userTextField, startBtn);
        
        splitPane.getItems().add(nameBox);
        
        // Timing
        HBox timeBox = new HBox(10);
        timeBox.setPadding(new Insets(10, 10, 10, 10));
        Text textTime = new Text("Time");
        grid.add(textTime, 0, 3);
        timeBox.getChildren().add(textTime);
        splitPane.getItems().add(timeBox);
        
        // Current Score
        HBox scoreBox = new HBox(10);
        scoreBox.setPadding(new Insets(10, 10, 10, 10));
        Text scoreText = new Text("Your current score is: ");
        scoreBox.getChildren().add(scoreText);
        splitPane.getItems().add(scoreBox);
        
        // Typing area
        VBox typeBox = new VBox(10);
        typeBox.setPadding(new Insets(10, 10, 10, 10));
        Text typeText = new Text("Type !");
        TextArea textInput = new TextArea();
        textInput.setWrapText(true);
        textInput.setEditable(true);
        Button auxBtn = new Button("Get Text!");
        auxBtn.setOnAction(e -> System.out.println(textInput.getText()));
        typeBox.getChildren().add(auxBtn);
                      
        typeBox.getChildren().addAll(typeText, textInput);

        splitPane.getItems().add(typeBox);
        
        // Incorrect words
        VBox incorrectBox = new VBox(10);
        incorrectBox.setPadding(new Insets(10, 10, 10, 10));
        TextArea ta = new TextArea();
        ta.setEditable(false);
        Text incorrectText = new Text("Incorrect Words");
        incorrectBox.getChildren().add(incorrectText);
        incorrectBox.getChildren().add(ta);
        
        splitPane.getItems().add(incorrectBox);
        
        
        
//        Scene scene = new Scene(grid, 500, 250);
        Scene scene = new Scene(splitPane, 700, 700);
        
        window.setResizable(true);
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
