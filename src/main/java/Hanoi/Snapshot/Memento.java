package Hanoi.Snapshot;

import Hanoi.MoveDisks;
import Hanoi.PictureHanoi;

public class Memento {

    private MoveDisks[] backup;
    private PictureHanoi hanoi;

    public Memento(PictureHanoi hanoi){
        this.hanoi = hanoi;
        this.backup = hanoi.getBackup();
    }

    public void restore(){
        hanoi.restore(backup);
    }
}
