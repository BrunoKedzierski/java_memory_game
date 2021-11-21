package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HighScores;
import model.Score;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuController {
    public Button exitBtn;



    public GridPane StartMenu;
    public Button startGame;




    public MainMenuController() {








    }



    public void ClickNexit(){

        Stage stage = (Stage) StartMenu.getScene().getWindow();

        stage.fireEvent( new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST));


    }


    public void ClickStart(MouseEvent mouseEvent) throws IOException {





        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\GameSizePicker.fxml"));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("..\\View\\SizePickerStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void clickedScores(MouseEvent mouseEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\View\\HighScoreMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            HighScoresController controller = loader.getController();
            controller.showHighScores(stage);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){

            e.printStackTrace();
        }




    }
}
