package sample.Fabric;

public class TasksDialog extends Dialog {
    @Override
    public Button createButton() {
        return new TasksButton();
    }
}
