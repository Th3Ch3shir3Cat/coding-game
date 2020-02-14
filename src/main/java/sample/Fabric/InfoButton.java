package sample.Fabric;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InfoButton implements Button {

    JFrame frame;
    JTextArea label;
    JPanel panel = new JPanel();

    @Override
    public void render() {
        frame = new JFrame("Информация");
        label = new JTextArea("Пятнашки – одна из известнейших миру головоломок.\n" +
                "Она представляет набор, в который входит квадратная коробка,\n" +
                "сторона которой равна 2-4 сторонам костяшки, то есть. 4х4,3х3,2х2.\n" +
                "Внутри этой коробки 15(или 8 или 3) квадратных костяшек. \n" +
                "В коробке остается 1 свободное место под одну костяшку.\n" +
                "Цель игры – упорядочивание костяшек по порядку.\n" +
                "Концом игры считается, когда все костяшки от 1 до 15 стоят друг за другом.\n" +
                "\n" +
                "Ханойсие башни - есть три колышка, на первом стопка дисков, \n" +
                "диаметр которых увеличивается к основанию стопки.\n" +
                "Необходимо переместить все эти диски на другой колышек.\n" +
                "При этом нельзя перемещать одновременно более одного диска, \n" +
                "нельзя класть больший диск над меньшим.\n" +
                "Для временного размещения можно использовать третий колышек.");
        label.setBackground(new Color(235, 233, 126));
        label.setFont(new Font("Dialog", Font.BOLD, 12));
        frame.getContentPane().add(panel);
        panel.add(label);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(504,315);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    @Override
    public void onClick() {

    }
}
