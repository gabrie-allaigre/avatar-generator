import com.talanlabs.avatargenerator.cache.ICache;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;

import java.nio.file.Paths;

public class Smiley3Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing.showAvatar(SmileyAvatar.newGhostAvatarBuilder()
				.cache(ICache.defaultCache(Paths.get("temp/avatar/3"))).build());
	}
}
