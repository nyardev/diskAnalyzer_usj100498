package diskanalyzer_usj100498;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;

public class GUI extends JFrame {
	private JMenuBar menuBar;
	private JMenuItem menuItemNewScan;
	private JMenuItem menuItemClear;
	DefaultMutableTreeNode root;
	DefaultTreeModel treeModel;
	DefaultTableModel tableModel;
	private JTree tree;
	private JScrollPane scrollPane;
	private JTable tableFiles;
	private JPanel panel;
	private JRadioButton rbtnOnlyFiles;
	private JRadioButton rbtnOnlyDirectories;
	private JRadioButton rbtnFilesAndDirs;
	private JRadioButton rbtnExtensionRar;
	private JRadioButton rbtnExtensionPdf;
	private JRadioButton rbtnExtensionPptx;
	private ButtonGroup btnGroupShowFiles;
	private JRadioButton rbtnExtensionMp4;
	private JRadioButton rbtnExtensionC;
	private JRadioButton rbtnShowAllExtensions;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultMutableTreeNode root = new DefaultMutableTreeNode();
					GUI frame = new GUI(root);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI(DefaultMutableTreeNode newRoot) {

		getContentPane().setForeground(Color.DARK_GRAY);
		DefaultMutableTreeNode root = newRoot;
		treeModel = new DefaultTreeModel(root);
		Carpeta carpetaRoot = (Carpeta)root.getUserObject();
		if (carpetaRoot!=null) {
			carpetaRoot.analizaCarpeta(root);
			
		}
//		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
//		root.setUserObject(rootArch);
//		rootArch.analizaCarpeta(root);
		
		setTitle("Disk Analyzer");
		File IconFile = Paths.get("./src/res/disk_drive.png").toFile();
		
		if (IconFile.exists()) {
//			System.out.println("Lo pillo");
			ImageIcon imgIcon = new ImageIcon("./src/res/disk_drive.png");
			this.setIconImage(imgIcon.getImage());
		}
		setForeground(Color.GREEN);
		setBackground(SystemColor.BLACK);
		setFont(new Font("Segoe UI Black", Font.BOLD, 23));
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(new MigLayout("", "[300px,growprio 20,grow][100px,grow]", "[593.00px,grow][100px,growprio 35,grow,bottom]"));

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "flowx,cell 0 0,grow");

		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				tableFiles.setModel(updateTableModel(selectedNode));
				
				tableModel.fireTableStructureChanged();

			}
		});
		tree.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		tree.setForeground(Color.GREEN);
		tree.setBackground(Color.BLACK);
		tree.setCellRenderer(new MyCellRenderer());

		scrollPane.setViewportView(tree);

		panel = new JPanel();
		panel.setForeground(Color.GREEN);
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[][][]", "[][][]"));

		rbtnOnlyFiles = new JRadioButton("Solo Archivos");
		rbtnOnlyFiles.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnOnlyFiles.setForeground(Color.GREEN);
		rbtnOnlyFiles.setBackground(Color.BLACK);
		panel.add(rbtnOnlyFiles, "cell 0 0");

		rbtnExtensionRar = new JRadioButton("Only .rar");
		rbtnExtensionRar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnExtensionRar.setBackground(Color.BLACK);
		rbtnExtensionRar.setForeground(Color.GREEN);
		panel.add(rbtnExtensionRar, "cell 1 0");

		rbtnOnlyDirectories = new JRadioButton("Solo Carpetas");
		rbtnOnlyDirectories.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		
		rbtnExtensionC = new JRadioButton("Only .c");
		rbtnExtensionC.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnExtensionC.setForeground(Color.GREEN);
		rbtnExtensionC.setBackground(Color.BLACK);
		panel.add(rbtnExtensionC, "cell 2 0");
		rbtnOnlyDirectories.setForeground(Color.GREEN);
		rbtnOnlyDirectories.setBackground(Color.BLACK);
		panel.add(rbtnOnlyDirectories, "cell 0 1");

		rbtnExtensionPdf = new JRadioButton("Only .pdf");
		rbtnExtensionPdf.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnExtensionPdf.setBackground(Color.BLACK);
		rbtnExtensionPdf.setForeground(Color.GREEN);
		panel.add(rbtnExtensionPdf, "cell 1 1");

		rbtnFilesAndDirs = new JRadioButton("Carpetas y Archivos");
		rbtnFilesAndDirs.setSelected(true);
		rbtnFilesAndDirs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				tableFiles.setModel(updateTableModel(root));
				tableModel.fireTableStructureChanged();
			}
		});
		
		rbtnExtensionMp4 = new JRadioButton("Only .mp4");
		rbtnExtensionMp4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnExtensionMp4.setBackground(Color.BLACK);
		rbtnExtensionMp4.setForeground(Color.GREEN);
		panel.add(rbtnExtensionMp4, "cell 2 1");
		rbtnFilesAndDirs.setForeground(Color.GREEN);
		rbtnFilesAndDirs.setBackground(Color.BLACK);
		panel.add(rbtnFilesAndDirs, "cell 0 2");

		rbtnExtensionPptx = new JRadioButton("Only .pptx");
		rbtnExtensionPptx.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnExtensionPptx.setForeground(Color.GREEN);
		rbtnExtensionPptx.setBackground(Color.BLACK);
		panel.add(rbtnExtensionPptx, "cell 1 2");
		btnGroupShowFiles = new ButtonGroup();
		btnGroupShowFiles.add(rbtnFilesAndDirs);
		btnGroupShowFiles.add(rbtnOnlyDirectories);
		btnGroupShowFiles.add(rbtnOnlyFiles);
		rbtnShowAllExtensions = new JRadioButton("Todas");
		rbtnShowAllExtensions.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateTableModel(root);
				tableModel.fireTableStructureChanged();
			}
		});
		rbtnShowAllExtensions.setSelected(true);
		rbtnShowAllExtensions.setForeground(Color.GREEN);
		rbtnShowAllExtensions.setBackground(Color.BLACK);
		panel.add(rbtnShowAllExtensions, "cell 2 2");
		ButtonGroup btnGroupExtensions = new ButtonGroup();
		btnGroupExtensions.add(rbtnExtensionPdf);
		btnGroupExtensions.add(rbtnExtensionRar);
		btnGroupExtensions.add(rbtnExtensionPptx);
		btnGroupExtensions.add(rbtnExtensionC);
		btnGroupExtensions.add(rbtnExtensionMp4);
		btnGroupExtensions.add(rbtnShowAllExtensions);
		tableFiles = new JTable();
		tableFiles.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		tableFiles.setSurrendersFocusOnKeystroke(true);
		tableFiles.setRowSelectionAllowed(false);
		tableFiles.setForeground(Color.GREEN);
		tableFiles.setBackground(Color.BLACK);
		tableFiles.setModel(updateTableModel(root));
		getContentPane().add(tableFiles, "cell 1 0,grow");
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, "cell 1 1,grow");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1488, 831);

		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 100, 0, 0));
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.GREEN);
		setJMenuBar(menuBar);

		JTree tree = new JTree(treeModel);

		menuItemNewScan = new JMenuItem("New Scan");
		menuItemNewScan.setHorizontalAlignment(SwingConstants.TRAILING);
		menuItemNewScan.setForeground(Color.GREEN);
		menuItemNewScan.setBackground(Color.BLACK);
		menuItemNewScan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newScanHandler(root, e);
			}

			// I take root node to update JTree
			private void newScanHandler(DefaultMutableTreeNode root, MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				fileChooser.setFileFilter(imgFilter);

				int result = fileChooser.showOpenDialog(e.getComponent());

				if (result != JFileChooser.CANCEL_OPTION) {

					File fileName = fileChooser.getSelectedFile();

					if ((fileName == null) || (fileName.getName().equals(""))) {
						System.out.println("Not a file");
					} else {
						String newRootString = (fileName.toString());
						updateTreeRootAndRepaint(newRootString);
					}
				}
			}

		});
		menuBar.add(menuItemNewScan);

		menuItemClear = new JMenuItem("Clear Data");
		menuItemClear.setHorizontalAlignment(SwingConstants.CENTER);
		menuItemClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearTree();
			}
		});
		menuItemClear.setForeground(Color.GREEN);
		menuItemClear.setForeground(Color.GREEN);
		menuItemClear.setBackground(Color.BLACK);
		menuBar.add(menuItemClear);
//		}
	}

	private void updateTreeRootAndRepaint(String newRootString) {
		DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode();
		Carpeta nuevaCarpetaScan = new Carpeta(newRootString); // fileName.toString()
		nuevaCarpetaScan.analizaCarpeta(newRoot);
		newRoot.setUserObject(nuevaCarpetaScan);
		DefaultTreeModel model = getTreeModel();
		model.setRoot(newRoot);
		model.reload(newRoot);
		JTree myTree = getTree();
		myTree.repaint();
	}

	private void clearTree() {
		DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode();
		DefaultTreeModel model = getTreeModel();
		model.setRoot(newRoot);
		model.reload(newRoot);
		JTree myTree = getTree();
		myTree.repaint();
	}

	public class MyCellRenderer extends DefaultTreeCellRenderer {
		@Override
		public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel,
				final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
			final Component ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

			final DefaultMutableTreeNode node = ((DefaultMutableTreeNode) (value));

			Fichero fichero = (Fichero) node.getUserObject();

			if (fichero != null) {
				if (fichero.getClass() == Archivo.class) {
					// Render Node that is a file
					this.setText(value.toString());
					if (sel) {
						// Focus
						this.setOpaque(true);
						this.setBackground(Color.BLACK);
						this.setForeground(Color.WHITE);

					} else {
						// Sin Focus
						this.setOpaque(true);
						this.setBackground(Color.black);
						this.setForeground(Color.green);
					}

				} else {
					// Render a node that is a directory
					this.setText(value.toString());
					if (sel) {
						// Focus
						this.setOpaque(true);
						this.setBackground(Color.BLACK);
						this.setForeground(Color.WHITE);

					} else {
						// Sin Focus
						this.setOpaque(true);
						this.setBackground(Color.BLACK);
						this.setForeground(Color.BLUE);
					}

				}
			}
			return ret;
		}
	}

	private DefaultTableModel updateTableModel(DefaultMutableTreeNode node) {
		Object[] header = new Object[]{"Titulo","Tamano en Bytes","Ultima Modificacion","Ultimo Acceso"};
		boolean _files=false;
		boolean _dirs=false;
		boolean _rar=false;
		boolean _pdf=false;
		boolean _pptx=false;
		boolean _c=false;
		boolean _mp4 =false;
		boolean _all =false;
		
		JRadioButton checkButtonsDirs = getRbtnOnlyDirectories();
		JRadioButton checkButtonsFiles = getRbtnOnlyFiles();
		JRadioButton checkButtonsDirsAndFiles = getRbtnFilesAndDirs();
		
		JRadioButton checkExtensionsRar = getRbtnExtensionRar();
		JRadioButton checkExtensionsPdf= getRbtnExtensionPdf();
		JRadioButton checkExtensionsPptx = getRbtnExtensionPptx();
		JRadioButton checkExtensionsC = getRbtnExtensionC();
		JRadioButton checkExtensionsMp4= getRbtnExtensionMp4();
		JRadioButton checkExtensionsAll = getRbtnShowAllExtensions();
		
		
		
		
		
		_files= checkButtonsFiles.isSelected();
		_dirs=checkButtonsDirs.isSelected();
		_rar= checkExtensionsRar.isSelected();
		_pdf=checkExtensionsPdf.isSelected();
		_pptx= checkExtensionsPptx.isSelected();
		_c =checkExtensionsC.isSelected();
		_mp4 = checkExtensionsMp4.isSelected();
		_all = checkExtensionsAll.isSelected();
		
		if(checkButtonsDirsAndFiles.isSelected()) {
			_files = true;
			_dirs = true;
		}
		int numElementos = node.getChildCount();
		 

		tableModel = new DefaultTableModel(header, 0);
		
		// model.addRow(new Object[]{persona.getNombre(),
		// persona.getFechaDeNacimiento(), persona.getDocumento()});

		for (int i = 0; i < numElementos; i++) {
			DefaultMutableTreeNode nodoShow = (DefaultMutableTreeNode) node.getChildAt(i);
			Fichero fichero = (Fichero) nodoShow.getUserObject();
			if (fichero.getClass() == Archivo.class && _files) {
				Archivo archivoShow = (Archivo) fichero;
				boolean passFilter = false;
//				
//				if(_all) {passFilter = true;break;}else
//				if(_rar) {passFilter = (archivoShow.extension=="rar");break;}else
//				if(_pdf) {passFilter = (archivoShow.extension=="pdf");break;}else
//				if(_pptx) {passFilter = (archivoShow.extension=="pptx");break;}else
//				if(_c) {passFilter = (archivoShow.extension=="c");break;}else
//				if(_mp4) {passFilter = (archivoShow.extension=="mp4");}
//				
				
				tableModel.addRow(
						new Object[] { archivoShow.nombreArchivo, archivoShow.tamArchivo, archivoShow.lastModified,archivoShow.lastAccesed });
				
			}else if(fichero.getClass() == Carpeta.class && _dirs){
				Carpeta carpetaShow = (Carpeta) fichero;
				
				tableModel.addRow(
						new Object[] { carpetaShow.nombreCarpeta, carpetaShow.tamCarpeta, carpetaShow.lastModified,carpetaShow.lastAccesed });
			}
			}
		return tableModel;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(350, 300);
	}

	public JTree getTree() {
		return tree;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public JTable getTable_() {
		return tableFiles;
	}

	public ButtonGroup getBtnGroup() {
		return btnGroupShowFiles;
	}

//	private void expandAll(TreePath parent, boolean expand) {
//	    // Traverse children
//	    TreeNode node = (TreeNode) parent.getLastPathComponent();
//	    if (node.getChildCount() >= 0) {
//	      for (Enumeration<? extends TreeNode> e = node.children(); e.hasMoreElements();) {
//	        TreeNode n = e.nextElement();
//	        TreePath path = parent.pathByAddingChild(n);
//	        expandAll(path, expand);
//	      }
//	    }
	public JRadioButton getRbtnOnlyFiles() {
		return rbtnOnlyFiles;
	}

	public JRadioButton getRbtnOnlyDirectories() {
		return rbtnOnlyDirectories;
	}

	public JRadioButton getRbtnFilesAndDirs() {
		return rbtnFilesAndDirs;
	}
	public JRadioButton getRbtnExtensionRar() {
		return rbtnExtensionRar;
	}
	public JRadioButton getRbtnExtensionPdf() {
		return rbtnExtensionPdf;
	}
	public JRadioButton getRbtnExtensionPptx() {
		return rbtnExtensionPptx;
	}
	public JRadioButton getRbtnExtensionC() {
		return rbtnExtensionC;
	}
	public JRadioButton getRbtnExtensionMp4() {
		return rbtnExtensionMp4;
	}
	public JRadioButton getRbtnShowAllExtensions() {
		return rbtnShowAllExtensions;
	}
}
