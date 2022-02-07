package pl.agh.edu.dp.factory;

import pl.agh.edu.dp.labirynth.Door;
import pl.agh.edu.dp.labirynth.Room;
import pl.agh.edu.dp.labirynth.Wall;

public class MazeFactory {
    //private static final MazeFactory mazeFactoryInstance = new MazeFactory();
    private static MazeFactory mazeFactoryInstance;

    protected MazeFactory(){} //why protected ?

    public static MazeFactory getInstance(){
        //return mazeFactoryInstance;

        if(mazeFactoryInstance == null){
            mazeFactoryInstance = new MazeFactory();
        }
        return mazeFactoryInstance;
    }

    public Room makeRoom(int index){
        return new Room(index);
    }

    public Wall makeWall(){
        return new Wall();
    }

    public Door makeDoor(Room r1, Room r2){
        return new Door(r1, r2);
    }
}
