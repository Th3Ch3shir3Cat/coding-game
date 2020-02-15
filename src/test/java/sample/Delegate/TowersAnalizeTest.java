package sample.Delegate;

import org.junit.Assert;
import org.junit.Test;


public class TowersAnalizeTest {

    @Test
    public void analize() {
        String masText[];
        masText = new String[]{"Move(1,3)", "Move(2,3)"};
        TowersAnalize ta = new TowersAnalize(masText);
        Assert.assertEquals(-1,ta.Analize());
    }
}