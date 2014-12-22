import java.awt.*;
import java.lang.Math;

public class Block {
    int position_x;
    int position_y;
    int width;
    int height;
    String color;
    String name;
    int velocity_x;
    int velocity_y;

    Block(int width, int height, String color,String name) {
    	this.width = width;
    	this.height = height;
    	this.color = color;
    	this.name = name;

//       position_x = (int)(Math.random() * (DrawPanel.window_width - width));
//       position_y = (int)(Math.random() * (DrawPanel.window_height - width));
    	position_x = 100;
    	position_y = 300;
    	
    	velocity_x = 0;
    	velocity_y = -1;

        // 進行方向はランダム
//        if (Math.random() > 0.5) {
//        	velocity_x = 1;
//        } else {
//        	velocity_x = -1;
//        }
//        if (Math.random() > 0.5) {
//        	velocity_y = 1;
//        } else {
//        	velocity_y = -1;
//        }
    }

    void update() {
    	position_x += velocity_x;
    	position_y += velocity_y;

        // 壁に衝突すれば反射
        if (position_x >= (DrawPanel.window_width - width) || position_x <= 0) {
        	velocity_x = -velocity_x;
        }
        if (position_y >= (DrawPanel.window_height - height) || position_y <= 0) {
        	velocity_y = -velocity_y;
        }
    }

    void draw(Graphics block) {
    	int size = 20;
    	block.setColor(Color.orange);
    	block.fillRect(position_x, position_y, width, height);
    	block.setColor(Color.black);
    	block.setFont(new Font(null,Font.PLAIN,size));
    	block.drawString(name,position_x+(width*9/20),position_y+(height/2)+(height/8));
    }
}
