import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.cache.FileCache;
import com.talanlabs.avatargenerator.cache.ICache;
import com.talanlabs.avatargenerator.cat.CatAvatar;
import com.talanlabs.avatargenerator.layers.backgrounds.RandomColorPaintBackgroundLayer;
import com.talanlabs.avatargenerator.layers.masks.RoundRectMaskLayer;
import com.talanlabs.avatargenerator.layers.others.ShadowLayer;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {

	public Main() throws Exception {
		super();
	}

	public static void main(String[] args) throws Exception {
		Avatar avatar1 = SmileyAvatar.newAccessoriesAvatarBuilder()
				.cache(ICache.defaultCache(Paths.get("D:/temp/avatar/1"))).build();
		Avatar avatar2 = SmileyAvatar.newEyeMouthAvatarBuilder()
				.cache(ICache.defaultCache(Paths.get("D:/temp/avatar/2"))).build();
		Avatar avatar3 = SmileyAvatar.newGhostAvatarBuilder()
				.cache(ICache.defaultCache(Paths.get("D:/temp/avatar/3"))).build();
		Avatar avatar4 = CatAvatar.newAvatarBuilder()
				.layers(new ShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer())
				.padding(8).margin(8).cache(new FileCache(Paths.get("D:/temp/avatar/4"))).build();
		Avatar avatar5 = CatAvatar.newAvatarBuilder().cache(ICache.defaultCache(Paths.get("D:/temp/avatar/5"))).build();
		Avatar avatar6 = SmileyAvatar.newDefaultAvatarBuilder().build();

		Avatar avatar = avatar6;
		int size = avatar.getWidth();

		int w = 4;
		int h = 4;
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

	private static void showImage(BufferedImage bi) throws Exception {
		Path file = Files.createTempFile("img", ".png");
		ImageIO.write(bi, "png", file.toFile());

		Desktop.getDesktop().open(file.toFile());
	}
}
