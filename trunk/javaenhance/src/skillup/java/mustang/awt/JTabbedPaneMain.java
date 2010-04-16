package skillup.java.mustang.awt;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class JTabbedPaneMain {
	public static void main(String[] arguments) {
		JTabbedPane tabbedPane = new JTabbedPane();
		addTab(tabbedPane, "砂糖");
		addTab(tabbedPane, "塩");
		addTab(tabbedPane, "酢");
		addTab(tabbedPane, "醤油");
		addTab(tabbedPane, "味噌");

		JFrame frame = new JFrame("JTabbedPane Sample");
		frame.pack();
		frame.add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void addTab(JTabbedPane tabbedPane, String name) {
		JLabel label = new JLabel(name);
		label.setBorder(new EmptyBorder(0, 0, 0, 0));

		JButton button = new JButton("×");
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Container container = (Container) event.getSource();
				while (!(container instanceof JTabbedPane)) {
					container = container.getParent();
				}
				JTabbedPane tabbedPane = (JTabbedPane) container;
				int index = tabbedPane.getSelectedIndex();
				if (index != -1) {
					tabbedPane.remove(index);
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.add(label);
		panel.add(button);

		tabbedPane.addTab(null, new JScrollPane(new JTextArea()));
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, panel);
	}

}
