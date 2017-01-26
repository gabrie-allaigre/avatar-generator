import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class HexaMain {

	public static void main(String[] args) {
		int size = 128;

		BufferedImage dest = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);

		g2.setColor(Color.CYAN);
		g2.drawLine(size / 2, 0, size / 2, size);
		g2.drawLine(0, size / 2, size, size / 2);

		double d = size / 4.0;
		for (int i = 0; i < 1; i++) {
			double cx = (Math.cos(Math.toRadians(i * 60)) * d + size / 2);
			double cy = (Math.sin(Math.toRadians(i * 60)) * d + size / 2);
			drawTriangle(g2, cx, cy, d, i * 60 + 180);
		}

		g2.dispose();

		AvatarShowing.showImage(dest);
	}

	private static void drawTriangle(Graphics2D g2, double cx, double cy, double d, int a) {
		double xs[] = new double[6];
		double ys[] = new double[6];
		for (int i = 0; i < 6; i++) {
			xs[i] = (Math.cos(Math.toRadians(i * 60 + a)) * d + cx);
			ys[i] = (Math.sin(Math.toRadians(i * 60 + a)) * d + cy);
		}

		for (int i = 0; i < 6; i++) {
			g2.setColor(AvatarUtils.defaultColors.get(i));

			GeneralPath gp = new GeneralPath();
			gp.moveTo(cx, cy);
			gp.lineTo(xs[i], ys[i]);
			gp.lineTo(xs[(i + 1) % xs.length], ys[(i + 1) % ys.length]);
			gp.lineTo(cx, cy);

			g2.fill(gp);
		}
//		g2.fill(new Polygon(new int[]{cx, xs[i], xs[(i + 1) % 3]}, new int[]{cy, ys[i], ys[(i + 1) % 3]}, 3));
	}
}
