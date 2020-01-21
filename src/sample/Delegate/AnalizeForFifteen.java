package sample.Delegate;

public class AnalizeForFifteen extends SintaxAnalizator {

     AnalizeForFifteen(String[] text){
         analize = new FifteenAnalize(text);
    }
}
