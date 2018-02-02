import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class SlidingPanel extends JPanel {

	public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	public static final int ON = 4, OFF = 5;

	
	/**
	 * Gets a BufferedImage of the specified component
	 * @param c - Component to capture image of
	 * @return an image of the specified component
	 */
    public BufferedImage getBufferedImageOfComponent(Component c) {
    	BufferedImage bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        this.paint(g);
        
        return bi;
    }
        
    /**
     * Method to make the SlidingPanel slide
     * @param direction - Where the SlidingPanel comes from
     * @param onOrOff - slide on to or slide off of its container
     */
    public void slide(int direction, int onOrOff) {
    	Container parent = getParent();
    	BufferedImage img = getBufferedImageOfComponent(this);
    	JLabel imgLbl = new JLabel(new ImageIcon(img));
    	
        parent.remove(this);
        parent.add(imgLbl);          
        
        Timer t = new Timer(10, new ActionListener(){
			int border = 0;
			int newBorder = 0;
			
            @Override
            public void actionPerformed(ActionEvent e) {            	
            	if(direction == LEFT) {
            		newBorder = onOrOff == ON ? SlidingPanel.this.getWidth()*2 - border : border;
            		if(border <= SlidingPanel.this.getWidth() * 2) {
            			imgLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, newBorder));
            		} else {
            			if(onOrOff == ON) {
            				SlidingPanel.this.setBorder(new EmptyBorder(0, 0, 0, 0));
            			} else {
            				SlidingPanel.this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, SlidingPanel.this.getWidth() * 2));
            	    	}
            			parent.remove(imgLbl);
        	    		parent.add(SlidingPanel.this);
            			endSlide(imgLbl, e, newBorder);
            		}
            	} else if(direction == RIGHT) {
            		newBorder = onOrOff == ON ? SlidingPanel.this.getWidth()*2 - border : border;
            		if(border <= SlidingPanel.this.getWidth() * 2) {
            			imgLbl.setBorder(BorderFactory.createEmptyBorder(0, newBorder, 0, 0));
            		} else {
            			if(onOrOff == ON) {
            	    		SlidingPanel.this.setBorder(new EmptyBorder(0, 0, 0, 0));
            			} else {
            				SlidingPanel.this.setBorder(BorderFactory.createEmptyBorder(0, SlidingPanel.this.getWidth() * 2, 0, 0));
            			}
            			parent.remove(imgLbl);
        	    		parent.add(SlidingPanel.this);
            			endSlide(imgLbl, e, newBorder);
            		}
            	} else if(direction == DOWN) {
            		newBorder = onOrOff == ON ? SlidingPanel.this.getWidth()*2 - border : border;
            		if(border <= SlidingPanel.this.getHeight() * 2) {
            			imgLbl.setBorder(BorderFactory.createEmptyBorder(newBorder, 0, 0, 0));
            		} else {
            			if(onOrOff == ON) {
            				SlidingPanel.this.setBorder(new EmptyBorder(0, 0, 0, 0));
            	    		parent.add(SlidingPanel.this);
            			} else {
            				SlidingPanel.this.setBorder(BorderFactory.createEmptyBorder(SlidingPanel.this.getWidth() * 2, 0, 0, 0));
            				
            			}
            			parent.remove(imgLbl);
            			endSlide(imgLbl, e, onOrOff);
            		}
            	} else if(direction == UP) {
            		newBorder = onOrOff == ON ? SlidingPanel.this.getWidth()*2 - border : border;
            		if(border <= SlidingPanel.this.getHeight() * 2) {
            			imgLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, newBorder, 0));
            		} else {
            			if(onOrOff == ON) {
            	    		SlidingPanel.this.setBorder(new EmptyBorder(0, 0, 0, 0));
            			} else {
            				SlidingPanel.this.setBorder(BorderFactory.createEmptyBorder(0, 0, SlidingPanel.this.getWidth() * 2, 0));
            			}
            			parent.remove(imgLbl);
        	    		parent.add(SlidingPanel.this);
            			endSlide(imgLbl, e, onOrOff);
            		}
            	} else {
            		System.err.println("Invalid Direction");
            	}
            	
            	border += 30;
            }
        });
        
        t.setInitialDelay(0);
        t.start();
    }
    
    void endSlide(Container p, ActionEvent ae, int onOff){
		
		p.repaint();
		((Timer)ae.getSource()).stop();                
    }
}