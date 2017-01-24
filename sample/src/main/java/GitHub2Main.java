import com.talanlabs.avatargenerator.GitHubAvatar;

public class GitHub2Main {

	public static void main(String[] args) throws Exception {
		AvatarShowing
				.showAvatar(GitHubAvatar.newAvatarBuilder(396, 5).build());
	}
}
