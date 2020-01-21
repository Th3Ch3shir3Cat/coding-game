package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Fabric.Dialog;
import sample.Fabric.InfoDialog;
import sample.Fabric.TasksDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Application {

    private static Dialog dialog;

    @Override
    public void start(Stage primaryStage){
        JFrame frame = new JFrame("Главная форма");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/res/pictureMain.jpg"))));
        frame.setLayout(new FlowLayout());

        JButton buttonTasks = new JButton("Игры");
        JButton buttonInfo = new JButton("Информация");
        buttonTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog = new TasksDialog();
                onClick();
            }
        });
        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog = new InfoDialog();
                onClick();
            }
        });
        frame.add(buttonTasks);
        frame.add(buttonInfo);
        frame.setSize(504,315);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void onClick(){
        dialog.renderWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
