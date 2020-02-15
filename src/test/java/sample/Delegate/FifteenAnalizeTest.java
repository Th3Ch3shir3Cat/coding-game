package sample.Delegate;

import org.junit.Assert;
import org.junit.Test;


public class FifteenAnalizeTest {

    @Test
    public void analize() {
        String masText[];
        masText = new String[]{"Move(1)", "Move(2)", "Move(3)"};
        FifteenAnalize ft = new FifteenAnalize(masText);
        Assert.assertEquals(-1,ft.Analize());
    }
}