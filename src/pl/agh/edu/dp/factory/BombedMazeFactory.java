package pl.agh.edu.dp.factory;

import pl.agh.edu.dp.factory.BombedMazeElements.BombedRoom;
import pl.agh.edu.dp.factory.BombedMazeElements.BombedWall;
import pl.agh.edu.dp.labirynth.Room;
import pl.agh.edu.dp.labirynth.Wall;

public class BombedMazeFactory extends MazeFactory{
    private static BombedMazeFactory instance;

    protected BombedMazeFactory(){}

    public static BombedMazeFactory getInstance(){
        if(instance == null){
            instance = new BombedMazeFactory();
        }
        return instance;
    }

    @Override
    public Wall makeWall(){
        return new BombedWall();
    }

    @Override
    public Room makeRoom(int nr) {
        return new BombedRoom(nr);
    }
}
