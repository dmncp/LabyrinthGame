package pl.agh.edu.dp.builder;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Maze;

public class CountingMazeBuilder implements IMazeBuilder{
    private int roomsCounter;
    private int doorsCounter;
    private int wallsCounter;


    @Override
    public Maze build() {
        return null;
    }

    @Override
    public void addRoom(int r) {
        roomsCounter++;
    }

    @Override
    public void addWall(int r, Direction dir) {
        wallsCounter++;
    }

    @Override
    public void addDoor(int r1, int r2, Direction dir1) {
        doorsCounter++;
        wallsCounter -= 2; // usuwam dwie sciany (jedna dla kazdego pokoju)
    }

    @Override
    public void addDoor(int r1, int r2) {
        doorsCounter++;
        wallsCounter -= 2; // usuwam dwie sciany (jedna dla kazdego pokoju)
    }

    public int getRoomsCounter(){
        return roomsCounter;
    }
    public int getDoorsCounter(){
        return doorsCounter;
    }
    public int getWallsCounter(){
        return wallsCounter;
    }
    public int getCounterAll(){
        return wallsCounter + roomsCounter + doorsCounter;
    }
}
