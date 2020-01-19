package sample;

public class Director {

    public void constractFifteenForms(FormBuilder builder){
        builder.configuration(680,600);
        builder.initializeComponents();
        builder.resultCompleted();
    }

    public void constractTowerForms(FormBuilder builder){
        builder.configuration(680,400);
        builder.initializeComponents();
        builder.resultCompleted();
    }

}
