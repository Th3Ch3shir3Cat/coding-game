package Hanoi.State;

import org.junit.Assert;
import org.junit.Test;
public class AgainStateTest {

    @Test
    public void start() {
        AgainState state = new AgainState(null);
        Assert.assertEquals("Старт",state.start());
    }

    @Test
    public void again() {
        AgainState state = new AgainState(null);
        Assert.assertEquals("Сначала",state.again());
    }
}