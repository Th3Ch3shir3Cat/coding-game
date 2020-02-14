package sample.Delegate;

public class SintaxAnalizator {

    public IAnalizeText analize;

    public int textAnalize(){
        if(analize.Analize() == -1)
            return -1;
        return analize.Analize();
    }

    public int[] getNumbers(){
        return analize.getNumbers();
    }
}
