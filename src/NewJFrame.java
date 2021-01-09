import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class NewJFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton6) {
				DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
				Vector<String> row = new Vector<String>();
				row.add("");
				row.add("");
				row.add("");
				if (jTable2.getSelectedRow() != -1) {
					model.insertRow(jTable2.getSelectedRow(), row);
					jTable2.setRowSelectionInterval(jTable2.getSelectedRow(), jTable2.getSelectedRow());
				} else {
					model.addRow(row);
				}
			} else if (e.getSource() == jButton7 && jTable2.getSelectedRow() != -1) {
				DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
				int q = jTable2.getSelectedRow();
				model.removeRow(q);
				if (jTable2.getRowCount() != q) {
					jTable2.setRowSelectionInterval(q, q);
				} else if (jTable2.getRowCount() != 0) {
					jTable2.setRowSelectionInterval(q - 1, q - 1);
				}
			} else if (e.getSource() == jButton3) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				Vector<String> row = new Vector<String>();
				row.add("");
				row.add("");
				row.add("");
				if (jTable1.getSelectedRow() != -1) {
					model.insertRow(jTable1.getSelectedRow(), row);
					jTable1.setRowSelectionInterval(jTable1.getSelectedRow(), jTable1.getSelectedRow());
				} else {
					model.addRow(row);
				}
			} else if (e.getSource() == jButton4 && jTable1.getSelectedRow() != -1) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				int q = jTable1.getSelectedRow();
				model.removeRow(q);
				if (jTable1.getRowCount() != q) {
					jTable1.setRowSelectionInterval(q, q);
				} else if (jTable1.getRowCount() != 0) {
					jTable1.setRowSelectionInterval(q - 1, q - 1);
				}
			} else if (e.getSource() == jButton11 && jTable1.getSelectedRow() > 0) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				model.moveRow(jTable1.getSelectedRow(), jTable1.getSelectedRow(), jTable1.getSelectedRow() - 1);
				jTable1.setRowSelectionInterval(jTable1.getSelectedRow() - 1, jTable1.getSelectedRow() - 1);
			} else if (e.getSource() == jButton5 && jTable1.getSelectedRow() < jTable1.getRowCount() - 1) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				model.moveRow(jTable1.getSelectedRow(), jTable1.getSelectedRow(), jTable1.getSelectedRow() + 1);
				jTable1.setRowSelectionInterval(jTable1.getSelectedRow() + 1, jTable1.getSelectedRow() + 1);
			} else if (e.getSource() == jButton1) {
				try {
					for (int o = 0; o < jTable2.getRowCount(); o++) {
						for (int q = 0; q < jTable2.getColumnCount(); q++) {
							if (jTable2.getValueAt(o, q).toString().equals("") || (q == jTable2.getColumnCount() - 1
									&& !isDigit(jTable2.getValueAt(o, q).toString()))) {
								jTabbedPane1.setSelectedIndex(0);
								jTable2.setRowSelectionInterval(o, o);
								return;
							}
						}
					}
					for (int o = 0; o < jTable1.getRowCount(); o++) {
						for (int q = 0; q < jTable1.getColumnCount(); q++) {
							if (jTable1.getValueAt(o, q).toString().equals("")) {
								jTabbedPane1.setSelectedIndex(1);
								jTable1.setRowSelectionInterval(o, o);
								return;
							}
						}
					}
					jButton1.setEnabled(false);
					String[][] j = new String[jTable2.getRowCount()][3];
					String[] h = new String[jTable1.getRowCount()];
					String[][] g = new String[jTable1.getRowCount()][3];
					for (int i = 0; i < j.length; i++) {
						j[i] = new String[] { jTable2.getValueAt(i, 0).toString(), jTable2.getValueAt(i, 1).toString(),
								jTable2.getValueAt(i, 2).toString() };
					}
					for (int f = 0; f < h.length; f++) {
						h[f] = jTable1.getValueAt(f, 0).toString();
						g[f] = new String[] { jTable1.getValueAt(f, 1).toString(), jTable1.getValueAt(f, 2).toString(),
								jTable1.getValueAt(f, 3).toString() };
					}
					p = new Alk(j, h, g);
					new MyDialog(p.resultat(h.length, h.length));
					jButton1.setEnabled(true);
					error = null;
				} catch (Exception errr) {
					jButton1.setEnabled(true);
					getToolkit().getSystemEventQueue().push(new java.awt.EventQueue());
					actionPerformed(new ActionEvent(jButton3, ActionEvent.ACTION_PERFORMED, "Neue Zeile"));
					actionPerformed(new ActionEvent(jButton4, ActionEvent.ACTION_PERFORMED, "Zeile löschen"));
					actionPerformed(new ActionEvent(jButton1, ActionEvent.ACTION_PERFORMED, "Los!"));
					error = errr;
				}
			} else if (e.getSource() == jButton2) {
				jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
						new String[] { "SchÃ¼ler", "Wahl 1", "Wahl 2", "Wahl 3" }));
				jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
						new String[] { "Fach", "Lehrer", "PlÃ¤tze" }));
			} else if (e.getSource() == jButton8) {
				double chsm = Double.NaN;
				if (p != null) {
					chsm = p.larechsu() + 1;
				}
				javax.swing.JOptionPane.showMessageDialog(getParent(),
						"Letzter Durchschnitt: " + chsm + "\nLetzte Exception: " + error
								+ "\nVersion: 1.1.1-0001\nAutor: Lars Bodewig  (fan2404@aol.com)",
						"Info", javax.swing.JOptionPane.INFORMATION_MESSAGE, null);
			} else if (e.getSource() == jButton12) {
				try {
					jTable3.print(JTable.PrintMode.valueOf("FIT_WIDTH"), new java.text.MessageFormat("Facharbeiten"),
							null);
				} catch (PrinterException error) {
					System.out.println("fail");
				}
			}
		}

		private boolean isDigit(String s) {
			for (int i = 0; i < s.length(); i++) {
				if (!Character.isDigit(s.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * Creates new form NewJFrame
	 */
	public NewJFrame() {
		super.setTitle("Shuffle");
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jSplitPane1 = new javax.swing.JSplitPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		jPanel4 = new javax.swing.JPanel();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jSplitPane2 = new javax.swing.JSplitPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel3 = new javax.swing.JPanel();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton11 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		error = null;

		jButton6.addActionListener(new MyActionListener());
		jButton7.addActionListener(new MyActionListener());
		jButton3.addActionListener(new MyActionListener());
		jButton4.addActionListener(new MyActionListener());
		jButton11.addActionListener(new MyActionListener());
		jButton5.addActionListener(new MyActionListener());
		jButton1.addActionListener(new MyActionListener());
		jButton2.addActionListener(new MyActionListener());
		jButton8.addActionListener(new MyActionListener());

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jSplitPane1.setDividerLocation(380);
		jSplitPane1.setName(""); // NOI18N

		jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Fach", "Lehrer", "Plätze" }));
		jTable2.getTableHeader().setReorderingAllowed(false);
		jTable2.setColumnSelectionAllowed(false);
		jScrollPane2.setViewportView(jTable2);

		jSplitPane1.setLeftComponent(jScrollPane2);

		jButton6.setText("Neue Zeile");

		jButton7.setText("Zeile löschen");
		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(15, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jButton6)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton7)
						.addContainerGap(362, Short.MAX_VALUE)));

		jSplitPane1.setRightComponent(jPanel4);

		jTabbedPane1.addTab("Fächer", jSplitPane1);

		jSplitPane2.setDividerLocation(380);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Schüler", "Wahl 1", "Wahl 2", "Wahl 3" }));
		jTable1.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(jTable1);

		jSplitPane2.setLeftComponent(jScrollPane1);

		jButton3.setText("Neue Zeile");

		jButton4.setText("Zeile löschen");

		jButton11.setText("Zeile hoch");

		jButton5.setText("Zeile runter");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(16, 16, 16)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(jButton3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton11)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton5)
						.addContainerGap(294, Short.MAX_VALUE)));

		jSplitPane2.setRightComponent(jPanel3);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSplitPane2));

		jTabbedPane1.addTab("Schüler", jPanel2);

		jButton1.setText("Los!");

		jButton2.setText("Alles löschen");

		jButton8.setText("Info");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jButton8)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jButton1).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton2).addComponent(jButton8))
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JSplitPane jSplitPane2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	private javax.swing.JButton jButton12;
	private javax.swing.JDialog jDialog;
	private javax.swing.JTable jTable3;
	private Alk p;
	private Exception error;
	// End of variables declaration

	class MyDialog extends JDialog {
		private static final long serialVersionUID = 1L;

		public MyDialog(String[][] s) {
			jTable3 = new JTable(s, new String[] { "Name", "Fach", "Lehrer" });
			jButton12 = new javax.swing.JButton("Drucken");
			jButton12.addActionListener(new MyActionListener());
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
			if (s != null) {
				scrollPane.setViewportView(jTable3);
			} else if (s == null) {
				javax.swing.JLabel jLabel = new javax.swing.JLabel(
						"                                    Keine Lösung gefunden, bitte Eingabe kontrollieren und erneut versuchen.");
				scrollPane.setViewportView(jLabel);
				jButton12.setEnabled(false);
			}
			scrollPane.setPreferredSize(new java.awt.Dimension(595, 400));
			c.add(scrollPane);
			c.add(jButton12);
			jDialog = new JDialog();
			jDialog.setSize(650, 500);
			jDialog.add(c);
			jDialog.setVisible(true);
		}
	}
}