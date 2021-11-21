package model;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Tile extends Pane {
    public static final int tileWidth = 80;
    public static final  int tileHeight = 80;
    private int red;
    private int green;
    private int blue;
    private Boolean visible;
    private  static Tile selectedTile1 = null;
    private  static Tile selectedTile2 = null;


    public Tile(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

       setStyle("-fx-background-color: rgb(" + red + "," + green + ", " + blue+ ")");
       Border br = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
       setBorder(br);
       setPrefHeight(tileHeight);
       setPrefWidth(tileWidth);
       setOpacity(0);
       visible = false;
       setOnMouseClicked(openTile);





    }



    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisibleTile(Boolean visible) {
        this.visible = visible;
    }

    private EventHandler openTile = new EventHandler<MouseEvent>(){

        @Override
        public void handle(MouseEvent mouseEvent) {
            Tile tile  = (Tile) mouseEvent.getSource();
            if(tile == selectedTile1)
                ;
            else if(selectedTile1 == null) {
                FadeTransition open = new FadeTransition(Duration.seconds(0.5), tile);
                open.setToValue(1);
                open.play();
                selectedTile1 = tile;
                selectedTile1.setDisable(true);
                tile.setVisibleTile(true);


            }
            else if(selectedTile2 == null) {
                selectedTile2= tile;
                if(selectedTile2.equals(selectedTile1)){
                    FadeTransition open = new FadeTransition(Duration.seconds(0.5), tile);
                    open.setToValue(1);
                    open.play();

                    tile.setVisibleTile(true);

                    selectedTile1.setDisable(true);
                    selectedTile2.setDisable(true);

                    selectedTile2=null;
                    selectedTile1=null;
                }
                else{
                    FadeTransition open = new FadeTransition(Duration.seconds(0.5), tile);
                    open.setToValue(1);
                    open.play();
                    FadeTransition close2 = new FadeTransition(Duration.seconds(1), selectedTile1);
                    close2.setToValue(0);


                    FadeTransition close = new FadeTransition(Duration.seconds(1), selectedTile2);
                    close.setToValue(0);
                    open.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            close.play();
                            close2.play();
                        }
                    });
                    selectedTile1.setVisibleTile(false);
                    selectedTile1.setDisable(false);
                    selectedTile2.setDisable(false);
                    selectedTile1 = null;
                    selectedTile2 = null;

                }




            }


        }
    };




    public void checkMatch(){

         if(!selectedTile1.equals(selectedTile2)){



                 FadeTransition close = new FadeTransition(Duration.seconds(1), selectedTile1);
                 FadeTransition close2 = new FadeTransition(Duration.seconds(1), selectedTile2);
                 close2.setToValue(0);
                 close.setToValue(0);
                 close.play();
                 close2.play();
                 selectedTile1.setVisibleTile(false);
                 selectedTile2.setVisibleTile(false);
                 selectedTile1 = null;
                 selectedTile2 = null;

        }
        else {

             selectedTile1 =null;
             selectedTile2=null;


         }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return red == tile.red && green == tile.green && blue == tile.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }
}
