package sample;

import FifteenGame.GameOfFifteen;
import FifteenGame.MainFrameFifteen;
import Hanoi.MainHanoi;

public class Tasks {
    private Director director;

    public void goToHanoi(){
        director = new Director();
        TowerBuilder builder = new TowerBuilder();
        director.constractTowerForms(builder);
        MainHanoi hanoi = builder.getResult();
    }

    public void goToFifteen(){
        director = new Director();
        FifteenBuilder builder = new FifteenBuilder();
        director.constractFifteenForms(builder);
        MainFrameFifteen fifteen = builder.getResult();
    }
}
