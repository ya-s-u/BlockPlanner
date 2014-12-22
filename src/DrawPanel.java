import static java.awt.RenderingHints.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements ActionListener {
	static final int window_width = 1600;
	static final int window_height = 900;
	static final int ground_height = 40;

	static final int block_width = 150;
	static final int block_height = 120;
	static final int block_margin = 100;

	int motion_num=0;
	int step;
	int moved;
	
	int[] column = new int[]{
		block_margin,
		block_margin*2 + block_width,
		block_margin*3 + block_width*2,
		block_margin*4 + block_width*3,
		block_margin*5 + block_width*4,
		block_margin*6 + block_width*5,
	};

	Block[] block = new Block[]{
		new Block(column[0], window_height-block_height-ground_height, block_width, block_height, Color.orange, "A"),
		new Block(column[1], window_height-block_height-ground_height, block_width, block_height, Color.red, "B"),
		new Block(column[2], window_height-block_height-ground_height, block_width, block_height, Color.green, "C"),
		new Block(column[3], window_height-block_height-ground_height, block_width, block_height, Color.yellow, "D"),
		new Block(column[4], window_height-block_height-ground_height, block_width, block_height, Color.blue, "E"),
		new Block(column[5], window_height-block_height-ground_height, block_width, block_height, Color.magenta, "F"),
	};

	public DrawPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(window_width, window_height));
	}

	public void actionPerformed(ActionEvent window_width) {
		switch(motion_num){
		case 0: move_block(0, 1);
				break;
		case 1: move_block(2, 3);
				break;
		}
		repaint();
	}

	public void move_block(int from, int to) {
		step++;
		int block_num = from;
		if(step < block_height*3/3) {
			block[block_num].move_top();
		} else if(step < (block_height*3 + block_width+block_margin)/3) {
			block[block_num].move_right();
//		} else if(step < (block_height*3 + block_width+block_margin + block_height*2)/3) {
//			block[block_num].move_bottom();
		} else if(moved < block_height*2){
			moved = block[block_num].fall_down(moved,step-(block_height*3 + block_width+block_margin)/3,block_height*2);
		} else{
			motion_num++;
			moved = 0;
			step = 0;
		}
	}

	public void paintComponent(Graphics window_width) {
		super.paintComponent(window_width);

		Graphics2D panel = (Graphics2D)window_width;
		panel.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

		panel.setColor(Color.gray);
		panel.fillRect(0, window_height-ground_height, 1600, ground_height);

		for(int i=0; i<block.length; i++) {
			block[i].draw(panel);
		}
	}
}
