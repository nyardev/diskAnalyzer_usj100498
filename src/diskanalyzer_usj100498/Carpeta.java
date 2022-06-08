package diskanalyzer_usj100498;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;

import javax.swing.tree.DefaultMutableTreeNode;

public class Carpeta extends Fichero {
	Path filePath;
	String nombreCarpeta;
	int numHijos;
	long tamCarpeta;
	double tamEnMB;
	File[] fileList;
	String lastModified;
	String lastAccesed;
	long tamEnKB;

	public Carpeta(String rutaArchivo) {

		this.filePath = Paths.get(rutaArchivo);
		this.numHijos = filePath.getNameCount();
		this.tamCarpeta = calculaTamCarpeta(filePath.toFile());
		this.nombreCarpeta = filePath.getFileName().toString();
		this.fileList = filePath.toFile().listFiles();
		this.tamEnMB = this.tamCarpeta / (1000 * 1000);
		this.tamEnKB = this.tamCarpeta / (1000);
		
		
		
		
		
		
		// setting all the file data to the attributes
		// in class file of BasicFileAttributeView.
		BasicFileAttributeView view = Files.getFileAttributeView(filePath, BasicFileAttributeView.class);

		// method to read the file attributes.
		BasicFileAttributes attribute;
		try {
			attribute = view.readAttributes();
			lastModified = attribute.lastModifiedTime().toString();
			lastAccesed = attribute.lastAccessTime().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Carpeta() {
	}

	public void analizaCarpeta(DefaultMutableTreeNode nodoPadre) {
		if (this.fileList != null) {
			for (File fileName : this.fileList) {
				Path fichero = fileName.toPath();
				DefaultMutableTreeNode nuevoHijo = new DefaultMutableTreeNode();
				if (Files.isDirectory(fichero)) {

					Carpeta subCarpeta = new Carpeta(fichero.toString());
					nuevoHijo.setUserObject(subCarpeta);
					nodoPadre.add(nuevoHijo);
					subCarpeta.analizaCarpeta(nuevoHijo);

				}

				else {

					Archivo subArchivo = new Archivo(fichero.toString());
					nodoPadre.add(nuevoHijo);
					nuevoHijo.setUserObject(subArchivo);

				}

			}
		}

	}

	@Override
	public String toString() {
		return nombreCarpeta;
	}

	private static long calculaTamCarpeta(File carpeta) {
		long length = 0;

		// listFiles() is used to list the
		// contents of the given folder
		File[] files = carpeta.listFiles();

		// loop for traversing the directory
		if (files != null) {
			for (File subFichero : files) {
				
				if (subFichero.isFile()) {
					length += subFichero.length();
				} else {
					length += calculaTamCarpeta(subFichero);
				}
			}
			return length;
		}
		return 0;
	}

}
