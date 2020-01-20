package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        JButton buttonTasks = new JButton("Игры");
        JButton buttonInfo = new JButton("Информация");
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        buttons.add(buttonTasks);
        buttons.add(buttonInfo);
        frame.add(buttons);
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
