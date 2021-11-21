package model;

import java.io.*;
import java.util.ArrayList;

public class HighScores implements Serializable {

    private static final String fileName = "HighScoreList.txt";
    private ArrayList<Score> scores;

    public HighScores(ArrayList<Score> scores) {
        this.scores = scores;
    }


    public void addScore(Score score){

        this.scores.add(score);
    }

    public HighScores() {
        scores = new ArrayList<Score>();
    }

    public void loadScores(){

        File file = new File(fileName);


        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(fileName);
                ObjectInputStream oin = new ObjectInputStream(fin);
                this.scores = (ArrayList) oin.readObject();

                fin.close();
                oin.close();
            } catch (IOException e) {

                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

   public void saveScores(){

       try {
           System.out.println("saving");


           FileOutputStream fou = new FileOutputStream(fileName);

           ObjectOutputStream ou = new ObjectOutputStream(fou);

           ou.writeObject(this.scores);


           ou.close();
           fou.close();
       }catch (IOException e){

           e.printStackTrace();
       }


   }

    public static String getFileName() {
        return fileName;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "HighScores{" +
                "scores=" + scores +
                '}';
    }
}

