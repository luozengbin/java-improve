package skillup.java.mustang.awt;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTableMain {
	public static void main(String[] arguments) {
		String[] columns = { "人名", "金額", };
		Object[][] rows = { { "夏目漱石", 1000 }, { "野口英世", 1000 },
				{ "守礼門", 2000 }, { "新渡戸稲造", 5000 }, { "樋口一葉", 5000 },
				{ "福沢諭吉", 10000 }, };

		// ソーティング時に文字列以外の比較を行いたい列がある場合は
		// getColumnClass メソッドをオーバーライドして適切な Class オブジェクトを返す.
		TableModel tableModel = new DefaultTableModel(rows, columns) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				default:
					throw new RuntimeException();
				}
			}
		};

		// TableRowSorter クラスによって行のソーティング機能を付加する.
		JTable table = new JTable(tableModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(
				tableModel);
		table.setRowSorter(rowSorter);

		JFrame frame = new JFrame("JTable Sorting Sample");
		frame.pack();
		frame.add(new JScrollPane(table));
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
//		JTable table = new JTable(tableModel);
//		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
//		table.setRowSorter(rowSorter);
//
//		// 人名が野口英世または金額が 5000 円フィルタを作成し, rowSorter に設定する.
//		List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
//		filters.add(RowFilter.regexFilter("野口英世", 0));
//		filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, 5000, 1));
//		rowSorter.setRowFilter(RowFilter.orFilter(filters));

	}
}
