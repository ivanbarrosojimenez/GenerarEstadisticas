
package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import estadisticas.GenerarEstadisticasResultados;
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
    
	static int numFasesCrear = 2;
	static String tipoResultado = "SQL"; //poner SQL o Db2;
	
    static String NOMBRE_FICHERO_SALIDA_FILTRADO = "ResultadoFiltrado_" + mdy +"_" +tipoResultado+ ".csv";

	
	
	static String carpetaF1 = "F1resultados"+tipoResultado+"/";
	static int numColeccionesF1 = 18;
	
	static String carpetaF2 = "F2resultados"+tipoResultado+"/";
	static int numColeccionesF2 = 5;
	
	static String carpetaF3 = "F3resultados"+tipoResultado+"/";
	static int numColeccionesF3 = 5;
	
	static String carpetaF4 = "resultado24feb"+tipoResultado+"/";
	static int numColeccionesF4 = 5;
	
	static String carpetaF5 = "";
	static int numColeccionesF5 = 0;
	
	static String carpetaF6 = "";
	static int numColeccionesF6 = 0;
	
	static String carpetaF7 = "";
	static int numColeccionesF7 = 0;
	
    public static void main(String[] args) throws IOException {
        /** Estadisticas por transaccion NEWMAN */
    	
        GenerarEstadisticasResultados estadisticas = new GenerarEstadisticasResultados();
        GrabarFichero grabarFichero = new GrabarFichero();
        grabarFichero.crearFichero("salida/" + NOMBRE_FICHERO_SALIDA_FILTRADO, true);
        
        for(int fase = 0; fase < numFasesCrear; fase++) {
        	 for (int coleccion = 0; coleccion < obtenerCantidadFases()[fase]; coleccion++) {
                 System.out.println(obtenerNombreFases()[fase] + "respuesta_" + coleccion + ".json");
                 StringBuffer sF0 = estadisticas.obtenerSalidaNewMan(
                		 obtenerNombreFases()[fase] + "respuesta_" + coleccion + ".json", coleccion == 0);
                 grabarFichero.agregarAFichero(sF0.toString());
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

	private static String[] obtenerNombreFases() {
		
		String [] resultado = new String[numFasesCrear];
		if (numFasesCrear >= 1) {
			resultado[0] = carpetaF1;
		}
		if (numFasesCrear >= 2) {
			resultado[1] = carpetaF2;
		}
		if (numFasesCrear >= 3) {
			resultado[2] = carpetaF3;
		}
		if (numFasesCrear >= 4) {
			resultado[3] = carpetaF4;
		}
		if (numFasesCrear >= 5) {
			resultado[4] = carpetaF5;
		}
		if (numFasesCrear >= 6) {
			resultado[5] = carpetaF6;
		}
		if (numFasesCrear >= 7) {
			resultado[6] = carpetaF7;
		}

		return resultado;
	}
	
	private static int[] obtenerCantidadFases() {

		int[] resultado = new int[numFasesCrear];
		if (numFasesCrear >= 1) {
			resultado[0] = numColeccionesF1;
		}
		if (numFasesCrear >= 2) {
			resultado[1] = numColeccionesF2;
		}
		if (numFasesCrear >= 3) {
			resultado[2] = numColeccionesF3;
		}
		if (numFasesCrear >= 4) {
			resultado[3] = numColeccionesF4;
		}
		if (numFasesCrear >= 5) {
			resultado[4] = numColeccionesF5;
		}
		if (numFasesCrear >= 6) {
			resultado[5] = numColeccionesF6;
		}
		if (numFasesCrear >= 7) {
			resultado[6] = numColeccionesF7;
		}

		return resultado;
	}

}
