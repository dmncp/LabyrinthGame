package pl.agh.edu.dp.labirynth;

public class Door extends MapSite {
    private Room room1;
    private Room room2;

    public Door(Room r1, Room r2){
        this.room1 = r1;
        this.room2 = r2;
    }


    @Override
    public void Enter(Player player){
        if(player.getCurrentRoom() == this.room1){
            this.room2.Enter(player);
        }
        else {
            this.room1.Enter(player);
        }
    }

    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
        this.room2 = room2;
    }
}
