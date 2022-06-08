package diskanalyzer_usj100498;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.tree.DefaultMutableTreeNode;

public class main {

	public static void main(String[] args) {
//		Carpeta rootArch = new Carpeta(System.getProperty("user.home"));
//		Carpeta rootArch = new Carpeta("/Users/nyara/Documents/FakeRootDiskAnalyzer");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		Carpeta carpetaRoot = new Carpeta();
		root.setUserObject(carpetaRoot);
//		root.setUserObject(rootArch);
//		rootArch.analizaCarpeta(root);
		GUI interfaz = new GUI(root);
	
		interfaz.setVisible(true);
//		Carpeta rootArch = new Carpeta("/Users/nyara/Documents/FakeRootDiskAnalyzer");
////		Archivo archroot = new Archivo("/Users/nyara/Documents/FakeRootDiskAnalyzer/Document13.pdf");
////		System.out.println(archroot);
//		
//		NodoArbolFicheros<Fichero> rootTree = new NodoArbolFicheros<Fichero>(rootArch);
//		rootArch.analizaCarpeta();
//		System.out.println(rootArch.tamCarpeta);
//		for (NodoArbolFicheros nodo : rootArch.nodo.getChildren()) {
//			System.out.println(nodo.getData());
//		}
//		
//		Path rutaTest = Paths.get("/Users/nyara/Documents/FakeRootDiskAnalyzer/Document13.pdf");
//		File test = rutaTest.toFile();
//		System.out.println(test.canExecute());
//		System.out.println(test.canRead());
//		System.out.println(test.canWrite());
	
		
		
}	
	
	
	


	
	
	
}
