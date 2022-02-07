package pl.agh.edu.dp.factory.BombedMazeElements;

import pl.agh.edu.dp.labirynth.Player;
import pl.agh.edu.dp.labirynth.Room;

public class BombedRoom extends Room {
    static final int detonateTime = 15; //seconds
    private int timer = 0;

    public BombedRoom(int number) {
        super(number);
    }

    @Override
    public void Enter(Player player){
        super.Enter(player);
        timer = 0;
    }

    public void updateTimer(int time, Player player){
        timer += time;
        if(timer >= detonateTime){
            player.killPlayer();
        }
    }
}
