package sample.Command;

import sample.Builder.Director;

public abstract class Command {

    public Tasks tasks;

    Command(Tasks tasks){
        this.tasks = tasks;
    }

    public abstract void execute(Director director);
}
