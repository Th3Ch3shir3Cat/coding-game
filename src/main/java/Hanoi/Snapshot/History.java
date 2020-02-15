package Hanoi.Snapshot;

import Hanoi.MoveDisks;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<Pair> history = new ArrayList<>();
    private int virtualSize = 0;

    private class Pair{
        MoveDisks move;
        Memento mem;

        Pair(MoveDisks move, Memento mem){
            this.move = move;
            this.mem = mem;
        }

        private MoveDisks getMove(){
            return this.move;
        }
        private Memento getMem(){
            return this.mem;
        }
    }

    public void push(MoveDisks move, Memento mem){
        if (virtualSize != history.size() && virtualSize > 0) {
            history = history.subList(0, virtualSize - 1);
        }
        history.add(new Pair(move, mem));
        virtualSize = history.size();
    }


    public boolean undo() {
        Pair pair = getUndo();
        if (pair == null) {
            return false;
        }
        pair.getMem().restore();
        return true;
    }

    public boolean redo() {
        Pair pair = getRedo();
        if (pair == null) {
            return false;
        }
        pair.getMem().restore();
        pair.getMove().execute();
        return true;
    }

    public MoveDisks getLastMove(){
        return this.history.get(virtualSize).move;
    }

    private Pair getUndo() {
        if (virtualSize == 0) {
            return null;
        }
        virtualSize = Math.max(0, virtualSize - 1);
        return history.get(virtualSize);
    }

    private Pair getRedo() {
        if (virtualSize == history.size()) {
            return null;
        }
        virtualSize = Math.min(history.size(), virtualSize + 1);
        return history.get(virtualSize - 1);
    }
}
