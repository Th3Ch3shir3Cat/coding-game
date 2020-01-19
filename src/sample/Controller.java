package sample;

import Hanoi.MainHanoi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML private javafx.scene.control.Button Play;


    public void goToTasksForm(javafx.event.ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) Play.getScene().getWindow();

        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/forms/tasks.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Игры");
        stage.setScene(new Scene(root1));
        stage.show();

    }



}
