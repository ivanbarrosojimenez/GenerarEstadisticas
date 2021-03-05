package estadisticas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Comprimir {
	static Date myDate = new Date();
	static SimpleDateFormat mdyFormat = new SimpleDateFormat("dd.MM.yyyy_HHmm");
	static String fechaActual = mdyFormat.format(myDate);

	public static boolean comprimirCarpeta(String directorio) throws IOException {
		File carpetaComprimir = new File(directorio + "/");

		if (carpetaComprimir.exists()) {
			ZipOutputStream zous = null;
			ZipEntry entrada = null;
			FileInputStream fis = null;
			File[] ficheros = carpetaComprimir.listFiles();

			// crea un buffer temporal para ir poniendo los archivos a comprimir
			zous = new ZipOutputStream(new FileOutputStream(directorio + "_" + fechaActual + ".zip"));
			try {
				for (int i = 0; i < ficheros.length; i++) {

					entrada = new ZipEntry(ficheros[i].getName());
					zous.putNextEntry(entrada);
					fis = new FileInputStream(directorio + "/" + entrada.getName());
					int leer;
					byte[] buffer = new byte[1024];
					while (0 < (leer = fis.read(buffer))) {
						zous.write(buffer, 0, leer);
					}

					fis.close();

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				zous.closeEntry();
				zous.close();
			}
			

			return true;
		} else {
			System.err.println("No se encontró el directorio..");
			return false;
		}
	}
}