package sample.Builder;

import FifteenGame.MainFrameFifteen;

import javax.swing.*;
import java.awt.*;

public class FifteenBuilder implements FormBuilder {

    private JSpinner spinnerNumberSquad;
    private JLabel labelNumberSquad;
    private JLabel labelInformation;
    private JButton buttonStart;
    private JPanel panel;
    private int width;
    private int height;


    @Override
    public void configuration(int width, int height) {
        panel = new JPanel();
        this.width = width;
        this.height = height;
    }

    @Override
    public void initializeComponents() {
        labelNumberSquad = new JLabel("Размер");
        panel.add(labelNumberSquad);

        spinnerNumberSquad = new JSpinner(new SpinnerNumberModel(4, 2, 4, 1));
        panel.add(spinnerNumberSquad);

        buttonStart = new JButton("Решение");
        panel.add(buttonStart);


        labelInformation = new JLabel("Финиш!");
        labelInformation.setForeground(Color.red);
        labelInformation.setVisible(false);
        panel.add(labelInformation);

    }



    public MainFrameFifteen getResult(){
        return new MainFrameFifteen(this.panel,this.labelInformation,this.spinnerNumberSquad,this.buttonStart,this.width,this.height);
    }
}
