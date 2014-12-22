import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame extends JFrame {
    final static int fps = 30;
    String[][] m_list = new String[20][2];
    String[] answer = {
    		"pick up from the table",
    		"Place blue rectangle on C",
    		"pick up A from the table",
    		"Place A on B"
    };
    public Frame() {
    	m_list = make_motion_list(answer);
        DrawPanel panel = new DrawPanel();
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
    
    public static String[][] make_motion_list(String[] answer){
    	String[][] m_list = new String[6][2];
    	int m_num = 0;
    	int step = 0;
    	while(step < answer.length){
			Matcher motion = Pattern.compile("(Place .*)(on .*)").matcher(answer[step]);
			if(motion.find()){
				String A = motion.group(1);
				String B = motion.group(2);
				Matcher from = Pattern.compile("(Place.)(.*)").matcher(A);
				Matcher to = Pattern.compile("(on.)(.*)").matcher(B);
				if(from.find())
					m_list[m_num][0] = from.group(2);
				if(to.find())
					m_list[m_num][1] = to.group(2);
				m_num++;
			}
			step++;
    	}
    	return m_list;
    }
}