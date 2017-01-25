import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import java.util.regex.Pattern;

public class ReflectionMain {

	public static void main(String[] args) {
		Reflections reflections =
				new Reflections("com.talanlabs.avatargenerator.cat.images.bodies", new ResourcesScanner());
		System.out.println(reflections.getResources(Pattern.compile(".*.png")));
	}

}
