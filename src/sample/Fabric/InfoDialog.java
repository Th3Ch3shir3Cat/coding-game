package sample.Fabric;

public class InfoDialog extends Dialog {
    @Override
    public Button createButton() {
        return new InfoButton();
    }
}
