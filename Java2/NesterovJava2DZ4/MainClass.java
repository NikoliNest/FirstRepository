import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainClass {
    public static void main(String[] args) {
        new MyWindow();
    }
}

class MyWindow extends JFrame {

    MyWindow() {
        setTitle("Java Hello");
        setBounds(800, 300, 400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(Color.black.gray);
        bottomPanel.setBackground(Color.black.green);

        bottomPanel.setPreferredSize(new Dimension(1, 40));

        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        JButton jButton = new JButton("Send");

        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        centerPanel.add(jsp, BorderLayout.CENTER);

        JTextField jtf = new JTextField();

        bottomPanel.add(jtf);
        bottomPanel.add(jButton);

        jtf.setPreferredSize(new Dimension(300, 28));
        jta.setEditable(false);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("nick1: " + jtf.getText() + "\n");
                jtf.setText("");
                jtf.grabFocus();
            }
        });

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int s = e.getKeyCode();
                if (e.isControlDown() && (s == 10)) {                     // Всё уже придумано до нас :)
                    jta.append("nick1: " + jtf.getText() + "\n");
                    jtf.setText("");
                    jtf.grabFocus();
                }
            }
        });
        setVisible(true);
    }
}