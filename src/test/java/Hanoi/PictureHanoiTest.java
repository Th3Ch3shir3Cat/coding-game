package Hanoi;

import org.junit.Assert;
import org.junit.Test;
import sample.Builder.Director;
import sample.Builder.TowerBuilder;


public class PictureHanoiTest {

    @Test
    public void addMove() {
        Director director = new Director();
        TowerBuilder builder = new TowerBuilder();
        director.constractTowerForms(builder);
        MainHanoi hanoi = builder.getResult();
        PictureHanoi ph = new PictureHanoi(hanoi,8);
        Assert.assertTrue(ph.addMove(1,2));
        Assert.assertFalse(ph.addMove(1,2));
    }
}