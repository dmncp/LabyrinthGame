package pl.agh.edu.dp.labirynth;

public enum Direction {
    North, South, East, West;

    public Direction opposite(){
        switch (this){
            case East: return West;
            case West: return East;
            case North: return South;
            case South: return North;
            default: return null;
        }
    }
}