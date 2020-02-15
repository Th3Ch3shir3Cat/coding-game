package FifteenGame;

import org.junit.Assert;
import org.junit.Test;
import sample.Builder.Director;
import sample.Builder.FifteenBuilder;


public class MainFrameFifteenTest {

    @Test
    public void checkInput() {
        Director director = new Director();
        FifteenBuilder builder = new FifteenBuilder();
        director.constractTowerForms(builder);
        MainFrameFifteen fifteen = builder.getResult();
        String[] masText = new String[]{"Move(1)","Move(2)","Move(3)"};
        Assert.assertTrue(fifteen.checkInput(masText));
    }
}