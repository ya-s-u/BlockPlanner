import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class DrawPanel extends JPanel implements ActionListener {
    static final int window_width = 1400;
    static final int window_height = 900;

    Block block1 = new Block(100, 300, 100, 50, "orange","A");
    Block block2 = new Block(300, 300, 100, 50, "orange","B");
    
    public DrawPanel() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(window_width, window_height));
    }

    public void actionPerformed(ActionEvent window_width) {
    	block1.update();
    	block2.update();
       repaint();
    }

    public void paintComponent(Graphics window_width) {
        super.paintComponent(window_width);

        Graphics2D panel = (Graphics2D)window_width;
        panel.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        block1.draw(panel);
        block2.draw(panel);
    }
}
