package com.tankman.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShellHandler {
	public void generateShell(Graphics g, int direction, String[] images, double shell_x_pos, double shell_y_pos) throws IOException{
		
		BufferedImage image = ImageIO.read(new File(images[direction]));
		 g.drawImage(image, (int)shell_x_pos, (int)shell_y_pos, null);
	}
}
