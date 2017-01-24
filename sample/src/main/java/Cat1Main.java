import com.talanlabs.avatargenerator.cache.ICache;
import com.talanlabs.avatargenerator.cat.CatAvatar;

import java.nio.file.Paths;

public class Cat1Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing.showAvatar(CatAvatar.newAvatarBuilder().cache(ICache.defaultCache(Paths.get("temp/avatar/5")))
				.build());
	}
}
