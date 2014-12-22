import java.awt.Color;
import java.lang.String;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame extends JFrame {
    final static int fps = 30;

    public Frame() {
        DrawPanel panel = new DrawPanel();
        add(panel);

        new Timer(fps, panel).start();
    }

    public static void main(String[] ball) {
        JFrame frame = new Frame();
        frame.setTitle("ブロックプランナー");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}