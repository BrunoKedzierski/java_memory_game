package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HighScores;
import model.Score;

public class WinnerController {


    public AnchorPane winWindow;
    public TextField nickInput;
    public Label scoreLabel;
    public VBox menu;
    private int finalScore;


    public void showScore(int score){

            finalScore = score;
            scoreLabel.setText("Your Score is: " + score);



    }

    public void submitScore(MouseEvent mouseEvent) {

        String nick = nickInput.getText();

        Score sc = new Score(nick,finalScore);

        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();

        HighScores hs = (HighScores) stage.getUserData();
        hs.addScore(sc);
        stage.fireEvent( new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST));



    }
}
