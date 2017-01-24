import com.talanlabs.avatargenerator.cache.ICache;
import com.talanlabs.avatargenerator.smiley.SmileyAvatar;

import java.nio.file.Paths;

public class Smiley1Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing.showAvatar(SmileyAvatar.newAccessoriesAvatarBuilder()
				.cache(ICache.defaultCache(Paths.get("temp/avatar/1"))).build());
	}
}
