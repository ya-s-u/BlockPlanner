import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Block {
	//ブロックの大きさ、色、名前
    int width;
    int height;
    Color color;
    String name;
    //ブロックのピクセル座標
    int position_x;
    int position_y;
    //一度に移動する距離(ピクセル)
    int speed = 3;
    //ブロックの行列単位での座標
    int column;
    int row;

    Block(int x,int y,int width, int height, Color color,String name) {
    	this.width = width;
    	this.height = height;
    	this.color = color;
    	this.name = name;
    	this.position_x = x;
    	this.position_y = y;
    }
    //ブロックの移動用のメソッド
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
    //自然落下処理のメソッド
    int fall_down(int moved,int step,int remit){
    	//設定した重力加速度より計算した今回の移動距離を加算(現在は0.5、変数化するとintでなくなってしまう)
    	moved += step/2; //stepが増えれば移動距離が増える
    	if(moved > remit){ //目標とする移動距離を超えてしまう場合
        	position_y += (step/2-moved+remit); //ブロックの座標と移動距離を調整
    		moved = remit;
    	}else{ //通常の処理
    		position_y += step/2;
    	}
    	return moved;
    }
    //ブロックの現在位置(行列単位)を更新するメソッド
    void push_position(int column,int row){ 
    	this.column = column;
    	this.row = row;
    }
    //ブロックの行や列を返すメソッド
    int get_column(){
    	return column;
    }
    
    int get_row(){
    	return row;
    }
    //ブロックの描画に関するメソッド
    void draw(Graphics block) {
    	block.setColor(color);
    	block.fillRect(position_x, position_y, width, height);
    	block.setColor(Color.black);
    	block.setFont(new Font(null, Font.PLAIN,  20));
    	block.drawString(name, position_x+(width*9/20), position_y+(height*11/20));
    }
}
