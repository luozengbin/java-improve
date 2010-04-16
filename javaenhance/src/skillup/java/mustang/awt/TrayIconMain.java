package skillup.java.mustang.awt;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TrayIconMain {
	public static void main(String[] arguments) throws AWTException
    {
        // システム・トレイがサポートされていなければ終了.
        if(!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported.");
            return;
        }

        // SystemTray インスタンスは static メソッドで取得する.
        SystemTray systemTray = SystemTray.getSystemTray();

        // トレイ・アイコンのイメージ. 白枠に黒く塗りつぶす.
        Dimension trayIconSize = systemTray.getTrayIconSize();
        BufferedImage trayIconImage = new BufferedImage(trayIconSize.width,
                                                        trayIconSize.height,
                                                        BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = trayIconImage.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, trayIconSize.width-1, trayIconSize.height-1);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, trayIconSize.width-1, trayIconSize.height-1);
        graphics.dispose();

        // トレイ・アイコンに持たせるポップアップメニューの作成.
        MenuItem menuItem = new MenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        PopupMenu trayIconMenu = new PopupMenu();
        trayIconMenu.add(menuItem);

        // トレイ・アイコンを作成し, システム・トレイに追加する.
        TrayIcon trayIcon = new TrayIcon(trayIconImage);
        trayIcon.setToolTip("Hello, TrayIcon.");
        trayIcon.setPopupMenu(trayIconMenu);
        systemTray.add(trayIcon);   // 失敗した場合は AWTException
    }
}

