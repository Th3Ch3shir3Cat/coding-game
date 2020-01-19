package Hanoi.State;

import Hanoi.PictureHanoi;

public class PauseState extends State {

    public PauseState(PictureHanoi hanoi) {
        super(hanoi);
    }

    @Override
    public String start() {
        return null;
    }

    @Override
    public String pause() {
        return null;
    }

    @Override
    public String again() {
        return null;
    }

    @Override
    public String continuest() {
        return "Продолжить";
    }
}
