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
    static final int window_width = 500;
    static final int window_height = 400;

    Block block = new Block(100, 50, "orange");
    
    public DrawPanel() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(window_width, window_height));
    }

    public void actionPerformed(ActionEvent window_width) {
    	block.update();
       repaint();
    }

    public void paintComponent(Graphics window_width) {
        super.paintComponent(window_width);

        Graphics2D panel = (Graphics2D)window_width;
        panel.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        block.draw(panel);
    }
}
