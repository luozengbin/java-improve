package skillup.java.mustang.awt;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DesktopMain {
	public static void main(String[] arguments) throws URISyntaxException {
		// デスクトップがサポートされているか.
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported.");
			return;
		}

		Desktop desktop = Desktop.getDesktop();

		// 「開く」操作がサポートされているか.
		if (!desktop.isSupported(Desktop.Action.OPEN)) {
			System.out.println("Desktop.Action.OPEN is not supported.");
			return;
		}

		// 関連付けられたアプリケーションでファイルを開く.
		try {
			desktop.open(new File("c:\\mywork\\env.txt"));
			desktop.browse(new URI("www.google.com"));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
