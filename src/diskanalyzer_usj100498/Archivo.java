package diskanalyzer_usj100498;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;

public class Archivo extends Fichero {
	// Voy a usar las clases Path Paths File y Files
	Path filePath;
	File archivoRuta;
	String nombreArchivo;
	boolean execPerm;
	boolean readPerm;
	boolean writePerm;
	long tamArchivo;
	double tamEnMB;
	double tamEnKB;
	String lastModified;
	String lastAccesed;
	FileAttributeView basicViewO;
	String extension;

	public Archivo(String rutaArchivo) {
		this.filePath = Paths.get(rutaArchivo);
		this.archivoRuta = filePath.toFile();
		this.nombreArchivo = filePath.getFileName().toString();
		this.execPerm = archivoRuta.canExecute();
		this.readPerm = archivoRuta.canRead();
		this.writePerm = archivoRuta.canWrite();
		int index = nombreArchivo.lastIndexOf('.');
	      if(index > 0) {
	        extension = nombreArchivo.substring(index + 1);
	        System.out.println(nombreArchivo + "\t" + extension);
	      }
		BasicFileAttributeView basicView = (BasicFileAttributeView) Files.getFileAttributeView(filePath, FileAttributeView.class);
		this.tamEnMB = this.tamArchivo/(1000*1000);
		this.tamEnKB = this.tamArchivo/(1000);
		BasicFileAttributeView view = Files.getFileAttributeView(filePath, BasicFileAttributeView.class);

		// method to read the file attributes.
		BasicFileAttributes attribute;
		try {
			attribute = view.readAttributes();
			tamArchivo= attribute.size();
			lastModified=attribute.lastModifiedTime().toString();
			lastAccesed=attribute.lastAccessTime().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public String toString() {
		return nombreArchivo ;
				
	}

}