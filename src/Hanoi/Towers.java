package Hanoi;

import java.util.ArrayList;
import java.util.List;

public class Towers {

    private int numberOfDisksOnTower;

    private int x1;
    private int x2;

    private List <Round> rounds;

    Towers(int x1, int x2, int numberOfDisksOnTower){
        this.x1 = x1;
        this.x2 = x2;
        rounds = new ArrayList<>();
        this.numberOfDisksOnTower = numberOfDisksOnTower;
    }

    public void setNumberOfDisksOnTower(int numberOfDisksOnTower){
        if(numberOfDisksOnTower < 8)
            this.numberOfDisksOnTower = numberOfDisksOnTower;
    }

    public int getNumberOfDisksOnTower(){
        return this.numberOfDisksOnTower;
    }

    public void addRound(Round round){
        rounds.add(round);
    }

    public Round getLastRound(){
        return this.rounds.get(rounds.size() - 1);
    }

    public void removeRound(){
        this.rounds.remove(this.rounds.size() - 1);
        this.numberOfDisksOnTower--;
    }

    public boolean checkDisksInTower(int x1, int x2){
        if(x1 >= this.x1 && x2 <= this.x2)
            return true;
        return false;
    }

    public int getX1(){
        return this.x1;
    }

    public int getX2(){
        return this.x2;
    }

}
