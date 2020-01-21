package sample.Delegate;

public class SintaxAnalizator {

    public IAnalizeText analize;

    public int textAnalize(){
        if(analize.Analize() == 0)
            return 0;
        return analize.Analize();
    }
}
