package pl.agh.edu.dp.builder;

import pl.agh.edu.dp.factory.MazeFactory;
import pl.agh.edu.dp.labirynth.*;

public class StandardBuilderMaze implements IMazeBuilder{
    private final Maze currentMaze;
    private MazeFactory factory = MazeFactory.getInstance();

    public StandardBuilderMaze(){
        currentMaze = new Maze();
    }
    public StandardBuilderMaze(MazeFactory factory){
        currentMaze = new Maze();
        this.factory = factory;
    }
    public MazeFactory getFactory(){
        return factory;
    }


    @Override
    public Maze build() {
        return currentMaze;
    }

    @Override
    public void addRoom(int r) {
        Room room = factory.makeRoom(r);

        currentMaze.addRoom(room);

        for(Direction dir: Direction.values()){
            addWall(r, dir);
        }
    }

    @Override
    public void addWall(int r, Direction dir) {
        Wall wall = factory.makeWall();
        currentMaze.getRoom(r).setSide(dir, wall);
    }

    @Override
    public void addDoor(int r1, int r2, Direction dir) {
        if(currentMaze.getRoom(r1) == null || currentMaze.getRoom(r2) == null) {
            throw new IllegalArgumentException("Nie mozna polaczyc nieistniejacych pokoi");
        }
        Room room1 = currentMaze.getRoom(r1);
        Room room2 = currentMaze.getRoom(r2);
        if(!canConnectRoomsInDir(room1, room2, dir)) {
            throw new IllegalArgumentException("Nie mozna polaczyc pokoi z tym kierunkiem");
        }
        Door door = factory.makeDoor(room1, room2);
        room1.setSide(dir, door);
        room2.setSide(dir.opposite(), door);
    }

    private boolean canConnectRoomsInDir(Room room1, Room room2, Direction dir) {
        return (room1.getSide(dir) instanceof Wall && room2.getSide(dir.opposite()) instanceof Wall);
    }

    @Override
    public void addDoor(int r1, int r2) {
        if(currentMaze.getRoom(r1) == null || currentMaze.getRoom(r2) == null) {
            throw new IllegalArgumentException("Nie mozna polaczyc nieistniejacych pokoi");
        }
        Room room1 = currentMaze.getRoom(r1);
        Room room2 = currentMaze.getRoom(r2);
        Direction dir = commonWall(room1, room2);
        Door door = factory.makeDoor(room1, room2);
        room1.setSide(dir, door);
        room2.setSide(dir.opposite(), door);
    }

    private Direction commonWall(Room from, Room to)
    {
        for(Direction dir : Direction.values()) {
            if(from.getSide(dir) instanceof Wall && to.getSide(dir.opposite()) instanceof Wall) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Polaczenie niemozliwe w tym przypadku");
    }

}
