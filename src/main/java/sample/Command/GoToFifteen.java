package sample.Command;

import FifteenGame.MainFrameFifteen;
import sample.Builder.Director;
import sample.Builder.FifteenBuilder;

public class GoToFifteen extends Command {

    GoToFifteen(Tasks tasks) {
        super(tasks);
    }

    @Override
    public void execute(Director director) {
        FifteenBuilder builder = new FifteenBuilder();
        director.constractFifteenForms(builder);
        MainFrameFifteen fifteen = builder.getResult();
    }
}
