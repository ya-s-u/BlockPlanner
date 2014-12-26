import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame extends JFrame {
	final static int fps = 30; //アニメーション画像の秒間表示数
	String[][] place_list = new String[20][2]; //Plannerからの結果を取得するための配列
	ArrayList from = new ArrayList(); //移動元のリスト
	ArrayList to = new ArrayList(); //移動先のリスト
	String[] answer = { //Plannerによる解答例
			"pick up B from the table",
			"Place B on C",
			"pick up A from the table",
			"Place A on B"
	};
	int[][] block_position = { //ブロックの初期位置の配列
			{0,0},
			{1,0},
			{2,0},
			{3,0},
			{3,1},
			{5,0}
	};
	public Frame() {
		place_list = make_place_list(answer); //Plannerによる解答から必要な箇所を抜き出す
		/* TODO:ここにblock_positionを初期化するための関数(石田,中根チーム次第) */
		convert_to_motion_list(place_list); //抜き出した答えによりfrom,toへの代入
		DrawPanel panel = new DrawPanel(from,to,block_position);
		add(panel);

		new Timer(fps, panel).start();
	}

	public static void main(String[] ball) {
		JFrame frame = new Frame();
		frame.setTitle("ブロックプランナー");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setBackground(Color.white);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	//Place A on B -> [A][B] のように変換
	public static String[][] make_place_list(String[] answer){
		String[][] place_list = new String[20][2];
		int p_num = 0;
		int step = 0;
		while(step < answer.length){//Plannerの解答が続く限り
			Matcher place = Pattern.compile("(Place .*)(on .*)").matcher(answer[step]); //matcherを作成
			if(place.find()){ //マッチすれば
				String A = place.group(1);
				String B = place.group(2);
				Matcher from = Pattern.compile("(Place.)(.*)").matcher(A); //fromを抜き出す
				Matcher to = Pattern.compile("(on.)(.*)").matcher(B); //toを抜き出す
				if(from.find()) //それぞれをplace_listに格納
					place_list[p_num][0] = from.group(2);
				if(to.find())
					place_list[p_num][1] = to.group(2);
				p_num++;
			}
			step++;
		}
		return place_list;
	}

	private void convert_to_motion_list(String[][] place_list) {
		// TODO:何かしらの処理によってfrom,toを作る
		from.add(0); to.add(1);
		from.add(2); to.add(0);
		from.add(3); to.add(0);
	}
}