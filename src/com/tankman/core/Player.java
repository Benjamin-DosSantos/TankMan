package com.tankman.core;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player {
	public void generatePlayer(Graphics g, int direction, String[] images, double player_x_pos, double player_y_pos) throws IOException{
		
		BufferedImage image = ImageIO.read(new File(images[direction]));
		 g.drawImage(image, (int)player_x_pos, (int)player_y_pos, null);
	}
}
