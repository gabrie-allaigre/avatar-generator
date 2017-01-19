import com.talanlabs.avatargenerator.AvatarBuilder;
import com.talanlabs.avatargenerator.cat.CatAvatar;
import com.talanlabs.avatargenerator.layers.backgrounds.RandomColorPaintBackgroundLayer;
import com.talanlabs.avatargenerator.layers.masks.RoundRectMaskLayer;
import com.talanlabs.avatargenerator.layers.others.ResizeLayer;
import com.talanlabs.avatargenerator.layers.others.ShadowLayer;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {

    public Main() throws Exception {
        super();
    }

    public static void main(String[] args) throws Exception {
        AvatarBuilder avatarBuilder1 = SmileyAvatar.newDefaultAvatarBuilder();
        AvatarBuilder avatarBuilder2 = SmileyAvatar.newEyeMouthAvatarBuilder();
        AvatarBuilder avatarBuilder3 = SmileyAvatar.newGhostAvatarBuilder();
        AvatarBuilder avatarBuilder4 = CatAvatar.newAvatarBuilder().layers(new ResizeLayer(128, 128), new ShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer()).padding(128);

        int w = 4;
        int h = 4;
        BufferedImage dest = new BufferedImage(128 * w, 128 * h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        Random random = new Random(10);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                long code = Math.abs(random.nextLong());
                g2.drawImage(avatarBuilder4.build(code), x * 128, y * 128, null);
                System.out.print(code + ", ");
            }
            System.out.println("");
        }

        g2.dispose();

        //showImage(avatarBuilder3.build(3342383633144681140L));
        showImage(dest);
    }

    private static void showImage(BufferedImage bi) throws Exception {
        Path file = Files.createTempFile("img", ".png");
        ImageIO.write(bi, "png", file.toFile());

        Desktop.getDesktop().open(file.toFile());
    }
}
