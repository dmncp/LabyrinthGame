package pl.agh.edu.dp.gui;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Player;
import pl.agh.edu.dp.labirynth.Wall;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    private final Image personImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../resources/walk3.jpg")));
    private final Image wallImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../resources/wall.png")));
    private Player player;
    private Timer timer;
    private TimerTask timerTask;


    public void setPlayer(Player player){
        this.player = player;
    }

    @FXML
    public Canvas canvas;

    @FXML
    public Canvas canvas2;


    @FXML
    public void initialize(){

    }

    public void startTimer(){
        if(timerTask != null && timer != null) {
            timerTask.cancel();
            timer.cancel();
        }

        timer = new Timer();
        timerTask = new TimerTask(){
            private int i = 0;
            public void run(){
                if (i <= 15) {
                    player.updateTimer(1);
                    updateDisplayTime(i++);

                    if(player.isDead()){
                        timerTask.cancel();
                        timer.cancel();
                        endGame("Koniec gry!\nZa długi czas bez ruchu!");
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000); //1000ms = 1sec
    }

    public void drawLabyrinth(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double width = 0;
        double height = 0;
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++) {
                gc.drawImage(wallImg, width, height);
                width = j * 100;
            }
            height = i * 100;
            width = 0;
        }

        gc.drawImage(personImg, 100, 100);
        gc.setFill(Color.GREEN);

        if(player != null) {
            if (!(player.getCurrentRoom().getSide(Direction.North) instanceof Wall)) {
                gc.fillRect(100, 0, 100, 100);
            }
            if (!(player.getCurrentRoom().getSide(Direction.East) instanceof Wall)) {
                gc.fillRect(200, 100, 100, 100);
            }
            if (!(player.getCurrentRoom().getSide(Direction.South) instanceof Wall)) {
                gc.fillRect(100, 200, 100, 100);
            }
            if (!(player.getCurrentRoom().getSide(Direction.West) instanceof Wall)) {
                gc.fillRect(0, 100, 100, 100);
            }
        }

        startTimer();
    }

    public void movePlayer(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.UP){
            player.changeRoom(Direction.North);
        }
        else if(keyEvent.getCode() == KeyCode.DOWN){
            player.changeRoom(Direction.South);
        }
        else if(keyEvent.getCode() == KeyCode.LEFT){
            player.changeRoom(Direction.West);
        }
        else if(keyEvent.getCode() == KeyCode.RIGHT){
            player.changeRoom(Direction.East);
        }
        drawLabyrinth();

        if(player.isWinner()){
            endGame("Odnalazłeś wyjście z labiryntu!\nGratulacje!");
        }
    }

    public void endGame(String message){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 20));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                message,
                Math.round(canvas.getWidth()  / 2),
                Math.round(canvas.getHeight() / 2)
        );

        if(timerTask != null && timer != null) {
            timerTask.cancel();
            timer.cancel();
        }
    }

    public void updateDisplayTime(int time){
        GraphicsContext gc = canvas2.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());

        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 20));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(String.valueOf(time + 1), canvas2.getWidth() / 2, canvas2.getHeight() / 2);
    }

}
