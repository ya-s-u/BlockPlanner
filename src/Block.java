import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Block {
    int width;
    int height;
    Color color;
    String name;
    int position_x;
    int position_y;
    int speed = 3;
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
    	position_x += speed;
    }
    
    void move_left() {
    	position_x -= speed;
    }
    
    void move_top() {
    	position_y -= speed;
    }
    
    void move_bottom() {
    	position_y += speed;
    }
    
    int fall_down(int moved,int step,int remit){
    	moved += step/2;
    	if(moved > remit){
        	position_y += (step/2-moved+remit);
    		moved = remit;
    	}else{
    		position_y += step/2;
    	}
    	return moved;
    }

    void draw(Graphics block) {
    	block.setColor(color);
    	block.fillRect(position_x, position_y, width, height);
    	block.setColor(Color.black);
    	block.setFont(new Font(null, Font.PLAIN,  20));
    	block.drawString(name, position_x+(width*9/20), position_y+(height*11/20));
    }
}
