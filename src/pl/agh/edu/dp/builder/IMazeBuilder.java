package pl.agh.edu.dp.builder;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Maze;

public interface IMazeBuilder {
    Maze build();
    void addRoom(int r);
    void addWall(int r, Direction dir);
    void addDoor(int r1, int r2, Direction dir1);
    void addDoor(int r1, int r2);
}
