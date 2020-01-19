package Hanoi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHanoi extends JFrame implements ActionListener, ChangeListener {

    private JLabel labelInformation;
    private JSpinner spinnerNumberDisks;
    private JButton buttonStart;
    private PictureHanoi panelHanoi;
    private JPanel panel;
    private int width;
    private int height;


    public MainHanoi(JPanel panelInfo, JLabel labelInfo, JSpinner spinner, JButton button,int width, int height){
        super("Ханойские башни");
        this.panel = panelInfo;
        this.labelInformation = labelInfo;
        this.spinnerNumberDisks = spinner;
        this.width = width;
        this.height = height;
        this.buttonStart = button;
        this.setSize(this.width, this.height);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        inicializeComponents();
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void inicializeComponents() {

        buttonStart.addActionListener(this);
        spinnerNumberDisks.addChangeListener(this);
        add(panel, BorderLayout.SOUTH);
        panelHanoi = new PictureHanoi(this,8);
        add(panelHanoi, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (buttonStart.getText().equals("Пауза")) {
            panelHanoi.pauseAnimacion();
            buttonStart.setText("Продолжить");
        } else {
            if (buttonStart.getText().equals("Сначала")) {
                remove(panelHanoi);
                panelHanoi = new PictureHanoi( this, Integer.parseInt(spinnerNumberDisks.getValue().toString()));
                add(panelHanoi, BorderLayout.CENTER);
                buttonStart.setText("Старт");
                labelInformation.setVisible(false);
                this.setVisible(true);
            } else {
                panelHanoi.startAnimacion();
                buttonStart.setText("Пауза");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        panelHanoi.pauseAnimacion();
        buttonStart.setText("Решение");
        labelInformation.setVisible(false);
        remove(panelHanoi);
        panelHanoi = new PictureHanoi( this,Integer.parseInt(spinnerNumberDisks.getValue().toString()));
        add(panelHanoi, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resultComplete() {
        buttonStart.setText("Решение");
        labelInformation.setVisible(true);
    }
}
