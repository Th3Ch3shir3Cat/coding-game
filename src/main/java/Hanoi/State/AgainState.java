package Hanoi.State;

import Hanoi.PictureHanoi;

public class AgainState extends State {

    public AgainState(PictureHanoi hanoi) {
        super(hanoi);
    }

    @Override
    public String start() {
        return "Старт";
    }

    @Override
    public String pause() {
        return null;
    }

    @Override
    public String again() {
        return "Сначала";
    }

    @Override
    public String continuest() {
        return null;
    }
}
