package sample.Command;

import Hanoi.MainHanoi;
import sample.Builder.Director;
import sample.Builder.TowerBuilder;

public class GoToHanoi extends Command {

    GoToHanoi(Tasks tasks) {
        super(tasks);
    }

    @Override
    public void execute(Director director) {
        TowerBuilder builder = new TowerBuilder();
        director.constractTowerForms(builder);
        MainHanoi hanoi = builder.getResult();
    }
}
