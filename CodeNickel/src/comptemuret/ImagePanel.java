package comptemuret;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private Image image;

	public ImagePanel(String fileName) {

		image = new ImageIcon(fileName).getImage();
			setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
			g.drawImage(image, 0, 0, null);
	}
}