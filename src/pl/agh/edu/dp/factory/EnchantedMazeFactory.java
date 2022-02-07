package pl.agh.edu.dp.factory;

import pl.agh.edu.dp.factory.EnchantedMazeElements.EnchantedDoor;
import pl.agh.edu.dp.factory.EnchantedMazeElements.EnchantedRoom;
import pl.agh.edu.dp.factory.EnchantedMazeElements.EnchantedWall;
import pl.agh.edu.dp.labirynth.Door;
import pl.agh.edu.dp.labirynth.Room;
import pl.agh.edu.dp.labirynth.Wall;

public class EnchantedMazeFactory extends MazeFactory{
    private static EnchantedMazeFactory instance;

    protected EnchantedMazeFactory() {};

    public static EnchantedMazeFactory getInstance() {
        if(instance == null) {
            instance = new EnchantedMazeFactory();
        }
        return instance;
    }

    @Override
    public Room makeRoom(int index) {
        return new EnchantedRoom(index);
    }

    @Override
    public Wall makeWall() {
        return new EnchantedWall();
    }

    @Override
    public Door makeDoor(Room first, Room second) {
        return new EnchantedDoor(first, second);
    }
}
