
package main;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.filechooser.FileNameExtensionFilter;

import estadisticas.BorrarDirectorio;
import estadisticas.Comprimir;
import estadisticas.GenerarEstadisticasResultados;
import estadisticas.GenerarEstadisticasTiempos;
import estadisticas.GrabarFichero;

public class Main {
    static final Date date = new Date();

    static Date myDate = new Date();
    static SimpleDateFormat mdyFormat = new SimpleDateFormat("ddMMyyyy HHmm");

    static String mdy = mdyFormat.format(myDate);

//    static String NOMBRE_FICHERO_SALIDA = "Resultado_" + mdy + ".csv";

//    static String NOMBRE_FICHERO_DB2_1 = "db21.json";
//    static String NOMBRE_FICHERO_DB2_2 = "db22.json";
//    static String NOMBRE_FICHERO_DB2_3 = "db23.json";
//    static String NOMBRE_FICHERO_DB2_4 = "db24.json";
//    static String NOMBRE_FICHERO_DB2_5 = "db25.json";
//    static String NOMBRE_FICHERO_DB2_6 = "db26.json";
//    static String NOMBRE_FICHERO_DB2_7 = "db27.json";
//    static String NOMBRE_FICHERO_DB2_0 = "pruebas1_1.json";
//
//    /** Para los tiempos (se comparan los ficheros) */
//    static String NOMBRE_FICHERO_ENTRADA_1_1 = "sql1.json";
//    static String NOMBRE_FICHERO_ENTRADA_2_1 = "host1.json";
//    static String NOMBRE_FICHERO_ENTRADA_1_2 = "sql2.json";
//    static String NOMBRE_FICHERO_ENTRADA_2_2 = "host2.json";
//    static String NOMBRE_FICHERO_ENTRADA_1_3 = "sql3.json";
//    static String NOMBRE_FICHERO_ENTRADA_2_3 = "host3.json";
//    static String NOMBRE_FICHERO_ENTRADA_1_4 = "sql4.json";
//    static String NOMBRE_FICHERO_ENTRADA_2_4 = "host4.json";
//    static String NOMBRE_FICHERO_ENTRADA_1_5 = "sql6.json";
//    static String NOMBRE_FICHERO_ENTRADA_2_5 = "host6.json";
    /** * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /** - - - - - MODIFICAR SOLO ESTO - - - - -*/
	static int numFasesCrear = 9;//Si se quiere ejecutar solo una fase cambiar tambien donde pone carpeta con el mismo numero (L67)
	static String tipoResultado = "Iaa"; //poner SQL o Db2 o Iaa;
	static boolean comprimirYEliminar = false;
	static String tipo = "resultados";//tiempos o resultados
	/**  - - - FIN MODIFICAR SOLO ESTO - - - - -  */
    /** * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	
    public static void main(String[] args) throws IOException {
        /** Estadisticas por transaccion NEWMAN */
        String NOMBRE_FICHERO_SALIDA_FILTRADO = "ResultadoFiltrado_" + mdy +"_" +tipoResultado+ ".csv";
        String FICHERO_LLAMADAS_CON_ERROR = "llamadas_con_error" + mdy +"_" +tipoResultado+ ".txt";

        GenerarEstadisticasResultados resultados = new GenerarEstadisticasResultados();
        GenerarEstadisticasTiempos estadisticas = new GenerarEstadisticasTiempos();

        GrabarFichero grabarFichero = new GrabarFichero();
        grabarFichero.crearFichero("salida/"+tipo + NOMBRE_FICHERO_SALIDA_FILTRADO, true);


        for(int fase = 0, carpeta = 1; carpeta <= numFasesCrear; fase++,carpeta++) {
        	
        	String nombreCarpetaFase ="F"+carpeta+"resultados"+tipoResultado; 
        	File files[] = (new File(nombreCarpetaFase+"/")).listFiles(filtro);
        	if(files != null) 
        	if(tipo.equals("resultados")) {
	        	for (int i = 0; i < files.length; i++) {
	        		//System.out.println(files[i].getName());
					StringBuffer sF0 = resultados.obtenerSalidaNewMan(
							nombreCarpetaFase+"/"+files[i].getName(), i == 0 && fase == 0, FICHERO_LLAMADAS_CON_ERROR);
	                grabarFichero.agregarAFichero(sF0.toString());
				}
        	} else if(tipo.equals("tiempos")) {
        		for (int i = 0; i < files.length; i++) {
	        		//System.out.println(files[i].getName());
					StringBuffer sF0 = estadisticas.obtenerSalidaNewMan(
							"F"+carpeta+"resultadosDb2"+"/"+files[i].getName(),
							"F"+carpeta+"resultadosSQL"+"/"+files[i].getName(), 
							i == 0 && fase == 0);					
	                grabarFichero.agregarAFichero(sF0.toString());
				}
        	}
        	//Comprimir y eliminar
			if (comprimirYEliminar) {
				if (Comprimir.comprimirCarpeta(nombreCarpetaFase)) {
					File directorio = new File(nombreCarpetaFase);
					if (BorrarDirectorio.deleteDirectory(directorio)) {
						System.out.println("Directorio " + nombreCarpetaFase + " eliminado correctamente.");
					} else {
						System.err.println("Error al eliminar el directorio " + nombreCarpetaFase);
					}
				}
			}
		}
        grabarFichero.cerrarFichero();
        
             	
 

        /** Estadisticas por transaccion postman */

        // GenerarEstadisticasResultados estadisticas = new GenerarEstadisticasResultados();
        // StringBuffer sF0 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_0, true);
        // StringBuffer sF1 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_1, false);
        // StringBuffer sF2 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_2, false);
        // StringBuffer sF3 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_3, false);
        // StringBuffer sF4 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_4, false);
        // StringBuffer sF5 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_5, false);
        // StringBuffer sF01 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_6, false);
        // StringBuffer sF02 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_7, false);
        // StringBuffer sF6 = estadisticas.obtenerSalidaNewMan(NOMBRE_FICHERO_DB2_6, false);

        // GrabarFichero grabarFichero = new GrabarFichero();

        // grabarFichero.crearFichero(NOMBRE_FICHERO_SALIDA_FILTRADO, false);
        // grabarFichero.agregarAFichero(sF0.toString());
        // grabarFichero.agregarAFichero(sF1.toString());
        // grabarFichero.agregarAFichero(sF2.toString());
        // grabarFichero.agregarAFichero(sF3.toString());
        // grabarFichero.agregarAFichero(sF4.toString());
        // grabarFichero.agregarAFichero(sF5.toString());
        // grabarFichero.agregarAFichero(sF01.toString());
        // grabarFichero.agregarAFichero(sF02.toString()); //
        // grabarFichero.agregarAFichero(sF6.toString());
        // grabarFichero.cerrarFichero();

        /** Fin estadisticas por transaccion postman **/

        /** Estadisticas de tiempos */
        // StringBuffer salida2 = estadisticas.obtenerSalida(NOMBRE_FICHERO_ENTRADA_1_2,
        // NOMBRE_FICHERO_ENTRADA_2_2, false);
        //
        // StringBuffer salida3 = estadisticas.obtenerSalida(NOMBRE_FICHERO_ENTRADA_1_3,
        // NOMBRE_FICHERO_ENTRADA_2_3, false);
        //
        // StringBuffer salida4 = estadisticas.obtenerSalida(NOMBRE_FICHERO_ENTRADA_1_4,
        // NOMBRE_FICHERO_ENTRADA_2_4, false);
        // StringBuffer salida5 = estadisticas.obtenerSalida(NOMBRE_FICHERO_ENTRADA_1_5,
        // NOMBRE_FICHERO_ENTRADA_2_5, false);
        // System.out.println(salida1.toString());

        // grabarFichero.agregarAFichero(salida2.toString());
        // grabarFichero.agregarAFichero(salida3.toString());
        // grabarFichero.agregarAFichero(salida4.toString()); //
        // grabarFichero.agregarAFichero(salida5.toString());

        // grabarFichero.cerrarFichero();

        /** Fin estadisticas de tiempos */

    }

	
	static FilenameFilter filtro = new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
	        return name.toLowerCase().endsWith(".json");
	    }
	};

}
