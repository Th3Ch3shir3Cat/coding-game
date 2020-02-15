package Hanoi.State;

import Hanoi.PictureHanoi;

public class StartState extends State {

    public StartState(PictureHanoi hanoi) {
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
