package sample.Delegate;

public class AnalizeForFifteen extends SintaxAnalizator {

    public AnalizeForFifteen(String[] text){
         analize = new FifteenAnalize(text);
    }
}
