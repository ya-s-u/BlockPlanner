import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Block {
    int position_x;
    int position_y;
    int width;
    int height;
    String color;
    int velocity_x;
    int velocity_y;
    int count;

    Block(int width, int height, String color) {
    	this.width = width;
    	this.height = height;
    	this.color = color;

//       position_x = (int)(Math.random() * (DrawPanel.window_width - width));
//       position_y = (int)(Math.random() * (DrawPanel.window_height - width));

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
    	
    	position_x = 100;
    	position_y = DrawPanel.window_height - height;
    	
    	velocity_x = 0;
    	velocity_y = -1;

    }

    void update() {
    	count++;
    	
    	if(count<200) {
    		position_y --;
    	} else if(count<300) {
    		position_x ++;
    	} else if(count<500) {
    		position_y ++;
    	}
    	
//    	position_x += velocity_x;
//    	position_y += velocity_y;

        // 壁に衝突すれば反射
//        if (position_x >= (DrawPanel.window_width - width) || position_x <= 0) {
//        	velocity_x = -velocity_x;
//        }
//        if (position_y >= (DrawPanel.window_height - height) || position_y <= 0) {
//        	velocity_y = -velocity_y;
//        }
    	
    }

    void draw(Graphics ball) {
    	ball.setColor(Color.orange);
    	ball.fillRect(position_x, position_y, width, height);
    }
}
