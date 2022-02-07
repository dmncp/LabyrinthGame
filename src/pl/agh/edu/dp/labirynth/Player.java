package pl.agh.edu.dp.labirynth;

public class Player{
    private boolean isDead;
    private Room currentRoom;
    private boolean isWinner = false;

    public Player(Room startingRoom){
        this.isDead = false;
        this.currentRoom = startingRoom;
    }

    public void changeRoom(Direction direction){
        this.currentRoom.getSide(direction).Enter(this);
        checkIfPlayerWon();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void killPlayer(){
        this.isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void updateTimer(int time){
        this.currentRoom.updateTimer(time, this);
    }

    public boolean isWinner(){
        return isWinner;
    }

    public void checkIfPlayerWon(){
        if(this.currentRoom.getRoomNumber() == 54){
            this.isWinner = true;
        }
        else{
            this.isWinner = false;
        }
    }
}
