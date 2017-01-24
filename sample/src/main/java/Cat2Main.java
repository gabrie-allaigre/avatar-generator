import com.talanlabs.avatargenerator.cache.FileCache;
import com.talanlabs.avatargenerator.cat.CatAvatar;
import com.talanlabs.avatargenerator.layers.backgrounds.RandomColorPaintBackgroundLayer;
import com.talanlabs.avatargenerator.layers.masks.RoundRectMaskLayer;
import com.talanlabs.avatargenerator.layers.others.ShadowLayer;

import java.nio.file.Paths;

public class Cat2Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing.showAvatar(CatAvatar.newAvatarBuilder()
				.layers(new ShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer())
				.padding(8).margin(8).cache(new FileCache(Paths.get("temp/avatar/4"))).build());
	}
}
