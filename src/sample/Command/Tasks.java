package sample.Command;

import FifteenGame.MainFrameFifteen;
import Hanoi.MainHanoi;
import sample.Builder.Director;
import sample.Builder.FifteenBuilder;
import sample.Builder.TowerBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tasks {

    private Director director;
    public void init(){
        JFrame frame = new JFrame("Игры");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JButton buttonHanoi = new JButton("Ханойске башни");
        JButton buttonFifteen = new JButton("Пятнашки");
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        director = new Director();
        Tasks task = this;
        buttonHanoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new GoToHanoi(task));
            }
        });
        buttonFifteen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                executeCommand(new GoToFifteen(task));
            }
        });
        buttons.add(buttonHanoi);
        buttons.add(buttonFifteen);
        frame.add(buttons);

        frame.setSize(504,315);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void executeCommand(Command command){
        command.execute(director);
    }
}
