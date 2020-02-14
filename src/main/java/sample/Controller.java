package sample;

import sample.Command.Tasks;

import java.io.IOException;

public class Controller {

    public void goToTasksForm(){

        Tasks tasks = new Tasks();
        tasks.init();
    }



}
