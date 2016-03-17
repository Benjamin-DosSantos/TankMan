package com.tankman.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TankManFrame extends JFrame{
	
	//TODO Make Array Of Tank Images
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		// Generates a screen size framework
    static int Screenwidth = (int) screenSize.getWidth();	// Makes a variable for screen width
	static int Screenheight = (int) screenSize.getHeight();	// Makes a variable for screen height
	
	Color bgColor = Color.DARK_GRAY;
	
	static int image_x = 0;
	static int image_y = 0;
	static double player_x_pos = 0.0;
	static double player_y_pos = 0.0;
	static BufferedImage image = null;
	static int dir = 1;
	
	static double player_speed = 0.01;
	
	static double shell_x_pos = 0.0;
	static double shell_y_pos = 0.0;
	static double shell_speed = 0.0;
	
	public static void main(String[] args) throws IOException {
		final JFrame frame = buildFrame();
		
		final String[] direction = {"Images\\Floor.png", "Images\\Green_Tank_1.png", "Images\\Green_Tank_1_Down.png", "Images\\Green_Tank_1_Left.png", "Images\\Green_Tank_1_Right.png", "Images\\Shell.png"};
		
        image = ImageIO.read(new File(direction[0]));
        
        final Player player_1 = new Player();
        final ShellHandler shell = new ShellHandler();
        
        final JPanel pane = new JPanel() {
        	
        	void genFloor(Graphics g){
        		for(int y = 0; y < Screenheight; y += image.getHeight()){
	    			for(int x = 0; x < Screenwidth; x += image.getWidth()){
		    			g.drawImage(image, image_x, image_y, null);
		    			image_x += image.getWidth() - 1;
		    		}
	    			image_x = 0;
	    			image_y += image.getHeight() - 1;
	    		}
        	};
        	
	    	protected void paintComponent(final Graphics g) {
	    		super.paintComponent(g);
	    		try {
	    			genFloor(g);
					player_1.generatePlayer(g, dir, direction, player_x_pos, player_y_pos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		frame.addKeyListener(new KeyListener()
	    		{
	    			@Override
	    			public void keyPressed(KeyEvent e) 
	    			{	
	    				genFloor(g);
	    				boolean gameOver = false;
						switch (e.getKeyCode()) 
	    				{
	    					case KeyEvent.VK_UP:
	    						if(player_y_pos > 0){
	    							player_y_pos -= 32;
		    						System.out.println(player_y_pos);
		    						dir = 1;
		    						repaint();
		    						genFloor(g);
	    						}else{
	    							break;
	    						}
	    						break;
	    					case KeyEvent.VK_DOWN:
	    						if(player_y_pos < (Screenheight - (image.getHeight() + 60))){
	    							player_y_pos += 32;
		    						System.out.println(player_y_pos);
		    						dir = 2;
		    						repaint();
		    						genFloor(g);
	    						}else{
	    							break;
	    						}
	    						break;
	    					case KeyEvent.VK_LEFT:
	    						if(player_x_pos > 0){
	    							player_x_pos -= 32;
		    						System.out.println(player_x_pos);
		    						dir = 3;
		    						repaint();
		    						genFloor(g);
	    						}else{
	    							break;
	    						}
	    						break;
	    					case KeyEvent.VK_RIGHT:
	    						if(player_x_pos < (Screenwidth - image.getWidth())){
	    							player_x_pos += 32;
		    						System.out.println(player_x_pos);
		    						dir = 4;
		    						repaint();
		    						genFloor(g);
	    						}else{
	    							break;
	    						}
	    						break;
	    					case KeyEvent.VK_SPACE:
	    						
							try {
								shell.generateShell(g, 5, direction, player_x_pos, player_y_pos);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
								repaint();
								genFloor(g);
	    						break;
	    				}
	    			}
	    			@Override
	    			public void keyReleased(KeyEvent e) 
	    			{}
	    			@Override
	    			public void keyTyped(KeyEvent e) 
	    			{}
	    		});
	    		
	    	}
        };
	        frame.add(pane);
	}
	
	private static JFrame buildFrame() {
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setSize(Screenwidth, Screenheight);
	    frame.setVisible(true);
	    return frame;
	}

}
