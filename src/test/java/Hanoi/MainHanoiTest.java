package Hanoi;

import org.junit.Assert;
import org.junit.Test;
import sample.Builder.Director;
import sample.Builder.TowerBuilder;

public class MainHanoiTest {

    @Test
    public void checkInput() {
        Director director = new Director();
        TowerBuilder builder = new TowerBuilder();
        director.constractTowerForms(builder);
        MainHanoi hanoi = builder.getResult();
        String[] masText = new String[]{"Move(1,2)", "Move(2,3)"};
        Assert.assertTrue(hanoi.checkInput(masText));
    }
}