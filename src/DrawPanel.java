import static java.awt.RenderingHints.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements ActionListener {
	Image hand;
	//ウィンドウとテーブルの位置
	static final int window_width = 1600;
	static final int window_height = 900;
	static final int ground_height = 40;
	//表示するブロックの大きさ
	static final int block_width = 150;
	static final int block_height = 120;
	//ブロック列の間隔
	static final int block_margin = 100;

    ArrayList from = new ArrayList(); //移動するブロックのリスト
    ArrayList to = new ArrayList(); //移動先のブロックのリスト

	int motion_num=0; //動作番号
	int step; //fpsと連動しピクセル単位の動きを管理する
	int moved; //自然落下処理のための変数
	
	int[] column = new int[]{ //ブロック列の座標のリスト
		block_margin,
		block_margin*2 + block_width,
		block_margin*3 + block_width*2,
		block_margin*4 + block_width*3,
		block_margin*4 + block_width*3,
		block_margin*6 + block_width*5,
	};

	Block[] block = new Block[]{ //ブロックのリスト
		new Block(column[0], window_height-block_height-ground_height, block_width, block_height, Color.orange, "A"),
		new Block(column[1], window_height-block_height-ground_height, block_width, block_height, Color.red, "B"),
		new Block(column[2], window_height-block_height-ground_height, block_width, block_height, Color.green, "C"),
		new Block(column[3], window_height-block_height*2-ground_height, block_width, block_height, Color.yellow, "D"),
		new Block(column[4], window_height-block_height-ground_height, block_width, block_height, Color.blue, "E"),
		new Block(column[5], window_height-block_height-ground_height, block_width, block_height, Color.magenta, "F"),
	};

	public DrawPanel(ArrayList from,ArrayList to,int[][] block_position) {
		setBackground(Color.white);
		setPreferredSize(new Dimension(window_width, window_height));
		this.from = from;
		this.to = to;
		for(int i=0;i<6;i++){
			block[i].push_position(block_position[i][0],block_position[i][1]); //ブロックの初期位置を記録
		}
	}

	public void actionPerformed(ActionEvent window_width) { //一定時間ごとに
		move_block((int)from.get(motion_num), (int)to.get(motion_num), from.size()); //ブロックの座標を更新し
		repaint(); //ウィンドウを再描画
	}

	//ブロックの座標更新を行うメソッド
	public void move_block(int from, int to, int size) {
		int width = block[to].get_column()-block[from].get_column(); //移動先とのx方向距離(列単位)
		int height = block[to].get_row()-block[from].get_row(); // 移動先とのy方向距離(行単位)
		if(motion_num > size) //全ての移動が終了したら
			return; //何も行わず終了
		step++;
		int block_num = from; //移動するブロックの番号
		if(height<0){ //移動先の方が低い位置にある
			if(step < block_height/3) { //ブロック1つ分移動するまで
				block[block_num].move_top(); //ブロックを上に移動
			} else if(step < (block_height + block_width*Math.abs(width)+block_margin*Math.abs(width))/3) { //移動先とx座標が同じになるまで
				if(width>0){ //移動先が右側なら
					block[block_num].move_right(); //右に移動
				} else{ //移動先が左側なら
					block[block_num].move_left(); //左に移動
				}
//			} else if(step < (block_height + block_width*Math.abs(width)+block_margin*Math.abs(width) + block_height*(-height+1))/3) {
//				block[block_num].move_bottom();
			} else if(moved < block_height*(-height+1)){ //着地するまで
				//下に移動(自然落下)と同時に移動済み距離を更新
				moved = block[block_num].fall_down(moved, step-(block_height + block_width*Math.abs(width)+block_margin*Math.abs(width))/3, block_height*(-height+1)); 
			} else{ //移動が完了したら
				motion_num++; //動作番号を更新
				moved = 0; //使用した変数を初期化
				step = 0; 
				block[block_num].push_position(to, block[to].get_row()+1); //移動したブロックの位置を更新
			}
		}else{ //移動先の方が高い位置にある場合の処理
			if(step < (block_height*(height+2))/3) {
				block[block_num].move_top();
			} else if(step < (block_height*(height+2) + block_width*Math.abs(width)+block_margin*Math.abs(width))/3) {
				if(width>0){
					block[block_num].move_right();
				} else{
					block[block_num].move_left();
				}
//			} else if(step < (block_height*(height+2) + block_width+block_margin + block_height*(height+2) - block_height)/3) {
//				block[block_num].move_bottom();
			} else if(moved < block_height){
				moved = block[block_num].fall_down(moved,step-(block_height*(height+2) + block_width*Math.abs(width)+block_margin*Math.abs(width))/3,block_height);
			} else{
				motion_num++;
				moved = 0;
				step = 0;
				block[block_num].push_position(to, block[to].get_row()+1);
			}
		}
	}

	public void paintComponent(Graphics window_width) {
		super.paintComponent(window_width);

		Graphics2D panel = (Graphics2D)window_width;
		panel.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
		
		// draw hand
		Toolkit tk = Toolkit.getDefaultToolkit();
		hand = tk.getImage(getClass().getResource("data/hand.png"));
		panel.drawImage(hand, 0, 0, this);

		// draw ground
		panel.setColor(Color.gray);
		panel.fillRect(0, window_height-ground_height, 1600, ground_height);

		// draw block
		for(int i=0; i<block.length; i++) {
			block[i].draw(panel);
		}
	}
}
