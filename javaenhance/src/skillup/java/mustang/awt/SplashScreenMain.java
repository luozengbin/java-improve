package skillup.java.mustang.awt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

public class SplashScreenMain {
	public static void main(String[] arguments) {
		animateProgress();
	}

	private static void animateProgress() {
		SplashScreen splashScreen = SplashScreen.getSplashScreen();
		if (splashScreen == null) {
			return;
		}

		Dimension size = splashScreen.getSize();
		Graphics2D graphics = splashScreen.createGraphics();
		graphics.setColor(Color.RED);

		int width = 8;
		int interval = 4;
		int height = 16;
		for (int x = 0; x < size.width; x += width + interval) {
			graphics.fillRect(x, size.height - height * 2, width, height);
			splashScreen.update();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		graphics.dispose();
	}
}
