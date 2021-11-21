package controller;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Board;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PickerController{


    public Button start;
    public TextField height;
    public TextField width;
    public Label errorPrompt;


    boolean isValidSize(int w, int h){

        int area = w*h;

        return w==h && area <= 100 && area > 2 && area%2==0;


    }

    public void ClickStart(MouseEvent mouseEvent) throws IOException {

        try {
            int h = Integer.parseInt(height.getText());
            int w = Integer.parseInt(width.getText());
            if(!isValidSize(w,h)){

                errorPrompt.setText("the sizes must be even, max size is 10x10");
                errorPrompt.setVisible(true);
                return;
            }
            else {

                Board gameBoard = new Board(w,h);
                errorPrompt.setVisible(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\View\\GameBoard.fxml"));
                Parent root = loader.load();

                BoardController boardController = loader.getController();

                boardController.generateBoard(gameBoard);


                Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);

                boardController.setKeyboardShortcut(scene);


                scene.getStylesheets().add(getClass().getResource("..\\View\\GameBoardStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            }
        }catch (NumberFormatException n){
            errorPrompt.setText("Type only numbers");
            errorPrompt.setVisible(true);
            return;

        }



    }





}
