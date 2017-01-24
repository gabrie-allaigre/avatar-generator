import com.talanlabs.avatargenerator.GitHubAvatar;
import com.talanlabs.avatargenerator.layers.backgrounds.ColorPaintBackgroundLayer;

import java.awt.Color;

public class GitHub1Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing
				.showAvatar(GitHubAvatar.newAvatarBuilder().layers(new ColorPaintBackgroundLayer(Color.WHITE)).build());
	}
}
