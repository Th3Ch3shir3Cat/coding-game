package Hanoi.State;

import Hanoi.PictureHanoi;

public class ContinueState extends State {
    public ContinueState(PictureHanoi hanoi) {
        super(hanoi);
    }

    @Override
    public String start() {
        return null;
    }

    @Override
    public String pause() {
        return "Пауза";
    }

    @Override
    public String again() {
        return null;
    }

    @Override
    public String continuest() {
        return null;
    }
}
