package sample.Delegate;

public class FifteenAnalize implements IAnalizeText {

    String[] text;

    FifteenAnalize(String[] text){
        this.text = text;
    }

    @Override
    public int Analize() {
        return 0;
    }

}
