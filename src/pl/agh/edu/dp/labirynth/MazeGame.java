package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.builder.IMazeBuilder;
import pl.agh.edu.dp.factory.MazeFactory;

public class MazeGame{
    public Maze createMaze(){
        Maze maze = new Maze();

        Room r1 = new Room(1);
        Room r2 = new Room(2);

        Door door = new Door(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(Direction.North, new Wall());
        r1.setSide(Direction.East, new Wall());
        r1.setSide(Direction.South, new Wall());
        r1.setSide(Direction.West, new Wall());

        r2.setSide(Direction.North, new Wall());
        r2.setSide(Direction.East, new Wall());
        r2.setSide(Direction.South, new Wall());
        r2.setSide(Direction.West, new Wall());

        return maze;
    }

    public Maze createMaze(IMazeBuilder builder){
        builder.addRoom(1);
        builder.addRoom(2);
        builder.addDoor(1, 2, Direction.North);

        return builder.build();
    }

    public Maze createMaze(MazeFactory factory){
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        Door door = factory.makeDoor(r1, r2);
        Maze maze = new Maze();
        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(Direction.North, door);
        r1.setSide(Direction.East, factory.makeWall());
        r1.setSide(Direction.South, factory.makeWall());
        r1.setSide(Direction.West, factory.makeWall());

        r2.setSide(Direction.North, factory.makeWall());
        r2.setSide(Direction.East, factory.makeWall());
        r2.setSide(Direction.South, door);
        r2.setSide(Direction.West, factory.makeWall());

        return maze;
    }

    public void createTunnel(IMazeBuilder builder, int start, int end, Direction dir, int additionalRoom){
        if(additionalRoom != -1) {
            builder.addDoor(additionalRoom, start - 1, dir);
        }
        for(int i = start; i <= end; i++){
            builder.addDoor(i - 1, i, dir);
        }
    }

    public Maze buildExampleMaze(IMazeBuilder builder){
        for(int i = 1; i <= 57; i++){
            builder.addRoom(i);
        }

        int[][] startEndPairs = {{2, 8}, {9, 10}, {12, 13}, {14, 18}, {20, 20}, {22, 29},
                {31, 35}, {39, 39}, {37, 37}, {41, 42}, {44, 45}, {46, 47}, {48, 52}, {54, 55}, {57, 57}};
        int[] additionalRooms = {-1, -1, 8, -1, 16, 4, 24, 35, 35, 24, 27, -1, -1, 49, 49};

        Direction[] directions = {Direction.East, Direction.South, Direction.North, Direction.East, Direction.South,
                Direction.South, Direction.East, Direction.North, Direction.South, Direction.West, Direction.East, Direction.South,
                Direction.East, Direction.South, Direction.North};

        for(int i = 0; i < startEndPairs.length; i++){
            createTunnel(builder, startEndPairs[i][0], startEndPairs[i][1], directions[i], additionalRooms[i]);
        }

        return builder.build();
    }
}
