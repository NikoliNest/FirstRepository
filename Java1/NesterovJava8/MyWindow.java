package NesterovJava8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyWindow extends JFrame {
    final static int INT = 10;

    MyWindow(String name, int a, int b) {
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(a, a, b, b);

        setLayout(new BorderLayout());

        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        add(jp1, BorderLayout.NORTH);

        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension((b - 20), 30));
        jTextField.setAlignmentX(CENTER_ALIGNMENT);
        jTextField.setAlignmentY(CENTER_ALIGNMENT);
        jp1.add(jTextField);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(4, 3));
        add(jp2, BorderLayout.CENTER);


        JButton[] jButtonsNumber = new JButton[INT];
        for (int i = 0; i < INT; i++) {
            jButtonsNumber[i] = new JButton( "" + i);
            jButtonsNumber[i].setPreferredSize(new Dimension(30, 30));
            jButtonsNumber[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                   jTextField.setText(actionEvent.getActionCommand());
                }
            });
        }

        for (int i = 0; i < INT; i++) {
            jp2.add(jButtonsNumber[i]);
        }

        JPanel jp3 = new JPanel();
        jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
        add(jp3, BorderLayout.EAST);


        JButton plus = new JButton(" + ");
        plus.setMinimumSize(new Dimension(40, 25));
            plus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                     Main.i = 1; // Сложение
                    jTextField.setText(actionEvent.getActionCommand());

                }
            });
            JButton minus = new JButton(" - ");
            minus.setMinimumSize(new Dimension(40, 25));
            minus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.i = 2; // Вычитание
                    jTextField.setText(actionEvent.getActionCommand());

                }
            });
            JButton multiple = new JButton(" * ");
            multiple.setMinimumSize(new Dimension(40, 25));
            multiple.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.i = 3; // Умножение
                    jTextField.setText(actionEvent.getActionCommand());
                }
            });

            JButton divide = new JButton(" / ");
            divide.setMinimumSize(new Dimension(40, 25));
            divide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.i = 4; // Деление
                    jTextField.setText(actionEvent.getActionCommand());
                }
            });

            jp3.add(plus);
            jp3.add(minus);
            jp3.add(multiple);
            jp3.add(divide);

            JButton enter = new JButton("  ENTER ");
            enter.setMinimumSize(new Dimension(40, 30));
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    jTextField.setText("" + Main.result);
                }
            });

            jp3.add(enter);


            setVisible(true);
    }
}
