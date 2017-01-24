import com.talanlabs.avatargenerator.cache.ICache;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;

import java.nio.file.Paths;

public class Smiley2Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing.showAvatar(SmileyAvatar.newEyeMouthAvatarBuilder()
				.cache(ICache.defaultCache(Paths.get("temp/avatar/2"))).build());
	}
}
