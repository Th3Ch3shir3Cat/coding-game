package sample.Fabric;

import sample.Command.Tasks;

import javax.swing.*;

public class TasksButton implements Button {

    @Override
    public void render() {
        onClick();
    }

    @Override
    public void onClick() {
        Tasks tasks = new Tasks();
        tasks.init();
    }
}
