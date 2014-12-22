import java.awt.*;
import java.lang.Math;

public class Block {
    int position_x;
    int position_y;
    int width;
    int height;
    Color color;
    String name;
    int velocity_x;
    int velocity_y;

    Block(int x,int y,int width, int height, Color color,String name) {
    	this.width = width;
    	this.height = height;
    	this.color = color;
    	this.name = name;

    	position_x = x;
    	position_y = y;
    	
    	velocity_x = 0;
    	velocity_y = -1;

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
    	block.setColor(color);
    	block.fillRect(position_x, position_y, width, height);
    	block.setColor(Color.black);
    	block.setFont(new Font(null,Font.PLAIN,size));
    	block.drawString(name,position_x+(width*9/20),position_y+(height/2)+(height/8));
    }
}
