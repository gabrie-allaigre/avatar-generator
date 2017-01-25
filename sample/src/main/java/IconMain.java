import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.GitHubAvatar;
import com.talanlabs.avatargenerator.IdenticonAvatar;
import com.talanlabs.avatargenerator.TriangleAvatar;
import com.talanlabs.avatargenerator.cat.CatAvatar;
import com.talanlabs.avatargenerator.eightbit.EightBitAvatar;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class IconMain {

	public static void main(String[] args) {
		int size = 64;
		Avatar[] avatars = new Avatar[]{
				CatAvatar.newAvatarBuilder().size(size, size).build(), // 1
				IdenticonAvatar.newAvatarBuilder().size(size, size).build(), // 2
				IdenticonAvatar.newAvatarBuilder().size(size, size).build(), // 3
				CatAvatar.newAvatarBuilder().size(size, size).build(), // 4
				GitHubAvatar.newAvatarBuilder().size(size, size).build(), // 5
				TriangleAvatar.newAvatarBuilder().size(size, size).build(), // 6
				TriangleAvatar.newAvatarBuilder().size(size, size).build(), // 7
				SmileyAvatar.newAccessoriesAvatarBuilder().size(size, size).build(), // 8
				GitHubAvatar.newAvatarBuilder().size(size, size).build(), // 9
				EightBitAvatar.newMaleAvatarBuilder().size(size, size).build(), // 10
				EightBitAvatar.newFemaleAvatarBuilder().size(size, size).build(), // 11
				SmileyAvatar.newEyeMouthAvatarBuilder().size(size, size).build(), // 12
				CatAvatar.newAvatarBuilder().size(size, size).build(), // 13
				SmileyAvatar.newGhostAvatarBuilder().size(size, size).build(), // 14
				SmileyAvatar.newGhostAvatarBuilder().size(size, size).build(), // 15
				CatAvatar.newAvatarBuilder().size(size, size).build() // 16
		};

		int w = 4;
		int h = 4;
		BufferedImage dest = new BufferedImage(size * w, size * h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		Random random = new Random(10);
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				long code = Math.abs(random.nextLong());
				Avatar avatar = avatars[(x + y * w) % avatars.length];
				g2.drawImage(avatar.create(code), x * size, y * size, size, size, null);
			}
			System.out.println("");
		}

		g2.dispose();

		AvatarShowing.showImage(dest);
	}
}
