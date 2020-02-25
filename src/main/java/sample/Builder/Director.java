package sample.Builder;

public class Director {

    public void constractFifteenForms(FormBuilder builder){
        builder.configuration(680,600);
        builder.initializeComponents();
    }

    public void constractTowerForms(FormBuilder builder){
        builder.configuration(880,400);
        builder.initializeComponents();
    }


}
