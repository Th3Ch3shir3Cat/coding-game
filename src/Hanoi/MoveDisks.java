package Hanoi;

public class MoveDisks {

    private int numberOfDisks;
    private int fromTowers;
    private int toTowers;

    public MoveDisks(int numberOfDisks, int fromTowers, int toTowers){

        this.numberOfDisks = numberOfDisks;
        this.fromTowers = fromTowers;
        this.toTowers = toTowers;

    }

    public int getNumberOfDisks(){
        return this.numberOfDisks;
    }

    public int getFromTowers() {
        return fromTowers;
    }

    public int getToTowers() {
        return toTowers;
    }

    public void execute(){

    }
}
