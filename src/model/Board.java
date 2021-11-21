package model;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Board {

    private Tile[] tiles;
    private int row;
    private int column;

    public Board(Tile[] tiles) {
        this.tiles = tiles;
    }

    public Board(int row, int column) {

        this.row = row;
        this.column =column;
        Tile[] tiles = fillWithColors(new Tile[row*column]);
        this.tiles = tiles;


    }


    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    Tile[] fillWithColors(Tile[] board){

        int size = board.length;

        for(int i =0; i <size-1;i+=2){

            int r =  (int) ((Math.random() * (255 - 0)) + 0);
            int g =  (int) ((Math.random() * (255 - 0)) + 0);
            int b =  (int) ((Math.random() * (255 - 0)) + 0);
            board[i] = new Tile(r,g,b);
            board[i + 1] = new Tile(r,g,b);

        }

    return board;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean gameFinished(){




        for (int i = 0; i < tiles.length ; i++) {

            if(!tiles[i].getVisible())
                    return false;
        }

        return true;
    }



}
