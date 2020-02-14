package sample.Delegate;

public class AnalizeForTowers extends SintaxAnalizator {

    public AnalizeForTowers(String[] text){
        analize = new TowersAnalize(text);
    }
}
