import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class AvatarShowing {

	private AvatarShowing() {
		super();
	}

	public static void showAvatar(Avatar avatar) {
		int size = avatar.getWidth();

		int w = 4;
		int h = 1;
		BufferedImage dest = new BufferedImage(size * w, size * h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		Random random = new Random(10);
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				long code = Math.abs(random.nextLong());
				g2.drawImage(avatar.create(code), x * size, y * size, size, size, null);
				System.out.print(code + ", ");
				//g2.setColor(Color.RED);
				//g2.drawRect(x * 128, y * 128, 128, 128);
			}
			System.out.println("");
		}

		g2.dispose();

		//showImage(avatar3.build(3342383633144681140L));
		showImage(dest);
	}

	private static void showImage(BufferedImage bi) {
		try {
			Path file = Files.createTempFile("img", ".png");
			ImageIO.write(bi, "png", file.toFile());

			Desktop.getDesktop().open(file.toFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
