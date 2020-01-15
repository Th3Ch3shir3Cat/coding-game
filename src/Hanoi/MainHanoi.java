package Hanoi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHanoi extends JFrame implements ActionListener, ChangeListener {

    private JLabel labelNumberDisks;
    private JLabel labelInformation;
    private JSpinner spinnerNumberDisks;
    private JButton buttonStart;
    private PictureHanoi panelHanoi;

    public MainHanoi(){
        super("Ханойские башни");
        configuration();
        inicializeComponents();
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void configuration() {
        this.setSize(680, 400);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void inicializeComponents() {

        JPanel panel = new JPanel();

        labelNumberDisks = new JLabel("Number of discs");
        panel.add(labelNumberDisks);


        spinnerNumberDisks = new JSpinner(new SpinnerNumberModel(8, 1, 8, 1));
        spinnerNumberDisks.addChangeListener(this);
        panel.add(spinnerNumberDisks);


        buttonStart = new JButton("Solve");
        buttonStart.addActionListener(this);
        panel.add(buttonStart);

        labelInformation = new JLabel("RESOLUTION COMPLETED!");
        labelInformation.setForeground(Color.red);
        labelInformation.setVisible(false);
        panel.add(labelInformation);

        add(panel, BorderLayout.SOUTH);

        panelHanoi = new PictureHanoi(8, this);
        add(panelHanoi, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {

    }
}