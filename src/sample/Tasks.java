package sample;

import FifteenGame.MainFrameFifteen;
import Hanoi.MainHanoi;
import sample.Builder.Director;
import sample.Builder.FifteenBuilder;
import sample.Builder.TowerBuilder;

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
