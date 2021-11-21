package controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Board;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class BoardController  {
    final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    @FXML
    VBox boardWindow;
    private int countTime = 0;
    private Label timer = new Label("0 s");

    private Board board;



    public void setKeyboardShortcut(Scene s){
        s.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyCombination.match(keyEvent)){
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\View\\mainMenu.fxml"));
                        Parent root = loader.load();
                        Scene s1 = (Scene) keyEvent.getSource();
                        Stage stage = (Stage) s1.getWindow();
                        stage.setScene(new Scene(root));
                        stage.getScene().getStylesheets().add(getClass().getResource("..\\View\\MainMenuStyle.css").toExternalForm());
                        stage.show();
                    }catch (IOException e){

                        e.printStackTrace();
                    }
                }



            }
        });
    }


    public void generateBoard(Board board){
        boardWindow.requestFocus();
        this.board = board;
        List<Tile> temp = Arrays.asList(board.getTiles());
        Collections.shuffle(temp);
        Tile[] tiles = new Tile[board.getColumn()* board.getRow()];
        temp.toArray(tiles);



        int rowCount = board.getRow();

            boardWindow.getChildren().add(timer);
            boardWindow.setPrefHeight( Tile.tileHeight* board.getRow() + 32);
            boardWindow.setPrefWidth(Tile.tileWidth * board.getColumn() + 20);
            boardWindow.setAlignment(Pos.CENTER);


            boardWindow.setOnMouseClicked(winCondition);



        HBox row = new HBox();

        for(int i =1; i<=tiles.length;i++){



            Tile currentTile = tiles[i-1];

            System.out.println(currentTile.getRed() + " " + currentTile.getGreen() + " " + currentTile.getBlue());

            Border br = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

            Pane pane = new Pane();
            pane.setBorder(br);
            pane.setPrefWidth(Tile.tileWidth);
            pane.setPrefHeight(Tile.tileHeight);
            pane.getChildren().add(currentTile);
            BackgroundImage im = new BackgroundImage( new Image("file:\\C:\\Users\\bruno\\IdeaProjects\\GUIMemory\\src\\static\\download.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)


                    );
            pane.setBackground(new Background(im));




            row.getChildren().add(pane);

             if(i == tiles.length ){
                row.setAlignment(Pos.CENTER);
                boardWindow.getChildren().add(row);
            }
            else if(i % rowCount == 0){
                 row.setAlignment(Pos.CENTER);
                 boardWindow.getChildren().add(row);
                 row = new HBox();
            }





        }

        timerCount.setDaemon(true);
        timerCount.start();



    }

    Thread timerCount = new Thread(() -> {
        Runnable timeUpdate = () -> timePass();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }


            Platform.runLater(timeUpdate);
        }
    });



    public void timePass(){
        countTime++;
        timer.setText(countTime + " s");

    }




    private EventHandler<MouseEvent> winCondition = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            if (board.gameFinished()) {
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\View\\Winner.fxml"));
                     root = loader.load();

                    WinnerController controller = loader.getController();

                    controller.showScore(computeScore());

                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("..\\View\\WinnerStyle.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    };

    private int computeScore(){

        return Integer.parseInt(timer.getText().split(" ")[0]) /( board.getRow() * board.getColumn());
    }


}
