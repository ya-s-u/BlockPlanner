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
    
    void move_right() {
    	position_x ++;
    }
    
    void move_left() {
    	position_x --;
    }
    
    void move_top() {
    	position_y --;
    }
    
    void move_bottom() {
    	position_y ++;
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
