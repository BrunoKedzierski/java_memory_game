package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.HighScores;
import model.Score;

public class HighScoresController {

    public TableColumn scoreColumn;
    public TableColumn nickColumn;
    public TableView table;






    public void showHighScores(Stage st){

        HighScores scores = (HighScores) st.getUserData();
        nickColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("playerName"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Score,String>("score"));


        scores.getScores().stream().forEach(c ->{

            table.getItems().add(c);


        });




    }
}
