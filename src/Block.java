import java.awt.*;
import java.lang.Math;

public class Block {
    int width;
    int height;
    Color color;
    String name;
    int position_x;
    int position_y;
//    int velocity_x;
//    int velocity_y;

    Block(int x,int y,int width, int height, Color color,String name) {
    	this.width = width;
    	this.height = height;
    	this.color = color;
    	this.name = name;
    	this.position_x = x;
    	this.position_y = y;
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
    	block.setColor(color);
    	block.fillRect(position_x, position_y, width, height);
    	block.setColor(Color.black);
    	block.setFont(new Font(null, Font.PLAIN,  20));
    	block.drawString(name, position_x+(width*9/20), position_y+(height*11/20));
    }
}
