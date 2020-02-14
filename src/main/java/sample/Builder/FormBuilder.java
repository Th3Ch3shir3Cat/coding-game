package sample.Builder;

import javax.swing.*;

public interface FormBuilder {
    void configuration(int width, int height);
    void initializeComponents();
    void resultCompleted();
}
