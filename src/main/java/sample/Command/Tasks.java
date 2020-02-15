package sample.Command;

import sample.Builder.Director;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tasks {

    private Director director;

    public void init() {
        JFrame frame = new JFrame("Игры");

        frame.setSize(504, 315);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/codingMenu.jpg"))));
        frame.setLayout(new FlowLayout());
        JButton buttonHanoi = new JButton("Ханойске башни");
        JButton buttonFifteen = new JButton("Пятнашки");

        director = new Director();
        final Tasks task = this;
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
        frame.add(buttonHanoi);
        frame.add(buttonFifteen);

    }

    private void executeCommand(Command command) {
        command.execute(director);
    }

}
