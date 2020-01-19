package sample;

import Hanoi.MainHanoi;

import javax.swing.*;
import java.awt.*;

public class TowerBuilder implements FormBuilder{

    private JLabel labelNumberDisks;
    private JLabel labelInformation;
    private JSpinner spinnerNumberDisks;
    private JPanel panel;
    private JButton buttonStart;
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
        labelNumberDisks = new JLabel("Кол-во дисков");
        panel.add(labelNumberDisks);

        spinnerNumberDisks = new JSpinner(new SpinnerNumberModel(8, 1, 8, 1));
        panel.add(spinnerNumberDisks);

        buttonStart = new JButton("Решение");
        panel.add(buttonStart);


        labelInformation = new JLabel("Финиш!");
        labelInformation.setForeground(Color.red);
        labelInformation.setVisible(false);
        panel.add(labelInformation);

    }

    @Override
    public void resultCompleted() {

    }

    public MainHanoi getResult(){
        return new MainHanoi(this.panel,this.labelInformation,this.spinnerNumberDisks,this.buttonStart,this.width,this.height);
    }
}
