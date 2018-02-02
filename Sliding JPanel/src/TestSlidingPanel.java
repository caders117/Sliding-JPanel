import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TestSlidingPanel {
	
	static SlidingPanel spnl = new SlidingPanel();
	static JFrame frame = new JFrame();
	static SlidingPanel spnl2 = new SlidingPanel();

	
	public static void main(String[] args){
        JLabel lbl = new JLabel("Label", 7);
        JPanel pnl = new JPanel();
        JButton[] btns = new JButton[8];
        String[] txt = {"Left on", "Left off", "Right on", "Right off",
        		"Down on", "Down off", "Up on", "Up off"};
        
        for(int i = 0; i < btns.length; i++) {
        	btns[i] = new JButton();
        	btns[i].addActionListener(new al());
        	btns[i].setActionCommand("" + i);
        	btns[i].setText(txt[i]);
        	pnl.add(btns[i]);
        }
        
        

        spnl.setLayout(new BorderLayout());
        System.out.println(SwingConstants.LEFT);
        spnl.add(lbl, BorderLayout.NORTH);
        spnl.add(new JButton("TEST BUTTON"), BorderLayout.CENTER);
        spnl2.add(new JButton("2nd slide button"));
        frame.getContentPane().add(spnl, BorderLayout.CENTER);
   //     frame.getContentPane().add(spnl2, BorderLayout.CENTER);
        frame.getContentPane().add(pnl, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        System.out.println(frame.getWidth());

        System.out.println(spnl.getParent());
	//	frame.getContentPane().remove(spnl);

	}

	static class al implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch (e.getActionCommand()) {
			
			case "0":
				spnl.slide(SlidingPanel.LEFT, SlidingPanel.ON);
				break;
				
			case "1":
				spnl.slide(SlidingPanel.LEFT, SlidingPanel.OFF);
				break;
				
			case "2":
				spnl.slide(SlidingPanel.RIGHT, SlidingPanel.ON);
				break;
				
			case "3":
				spnl.slide(SlidingPanel.RIGHT, SlidingPanel.OFF);
				break;
				
			case "4":
				spnl.slide(SlidingPanel.DOWN, SlidingPanel.ON);
				break;
				
			case "5":
				spnl.slide(SlidingPanel.DOWN, SlidingPanel.OFF);
				spnl2.slide(SlidingPanel.DOWN, SlidingPanel.ON);
				break;
				
			case "6":
				spnl.slide(SlidingPanel.UP, SlidingPanel.ON);
				break;
				
			case "7":
				spnl.slide(SlidingPanel.UP, SlidingPanel.OFF);
				break;
			}
		}
		
	}
}
