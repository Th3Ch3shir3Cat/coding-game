package Hanoi.State;

import Hanoi.PictureHanoi;

public abstract class State {
    PictureHanoi hanoi;

    protected State(PictureHanoi hanoi){
        this.hanoi = hanoi;
    }

    public abstract String start();
    public abstract String pause();
    public abstract String again();
    public abstract String continuest();
}
