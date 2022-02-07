package pl.agh.edu.dp.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.agh.edu.dp.builder.StandardBuilderMaze;
import pl.agh.edu.dp.factory.BombedMazeFactory;
import pl.agh.edu.dp.factory.EnchantedMazeFactory;
import pl.agh.edu.dp.factory.MazeFactory;
import pl.agh.edu.dp.gui.GameController;
import pl.agh.edu.dp.labirynth.*;


public class Main extends Application{
    private static final MazeGame mazeGame = new MazeGame();
    private static final StandardBuilderMaze builder = new StandardBuilderMaze(BombedMazeFactory.getInstance());
    private static final Maze maze = mazeGame.buildExampleMaze(builder);
    private final Player player = new Player(maze.getRoom(1));
    private static GameController controller;

    public static void main(String[] args) {
        if(BombedMazeFactory.getInstance() == BombedMazeFactory.getInstance()) {
            System.out.println("BombedMazeFactory jest singletonem");
        } else {
            System.out.println("BombedMazeFactory nie jest singletonem");
        }
        if(EnchantedMazeFactory.getInstance() == EnchantedMazeFactory.getInstance()) {
            System.out.println("EnchantedMazeFactory jest singletonem");
        } else {
            System.out.println("EnchantedMazeFactory nie jest singletonem");
        }
        if(MazeFactory.getInstance() == MazeFactory.getInstance()) {
            System.out.println("MazeFactory jest singletonem");
        } else {
            System.out.println("MazeFactory nie jest singletonem");
        }


        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/main.fxml"));

        Parent root = loader.load();
        controller = loader.getController();
        controller.setPlayer(player);
        controller.drawLabyrinth();

        Scene scene = new Scene(root);
        scene.setOnKeyReleased(e -> controller.movePlayer(e));

        stage.setScene(scene);

        stage.setTitle("Labyrinth Game");
        stage.show();

    }

}



