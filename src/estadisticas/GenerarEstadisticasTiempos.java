package estadisticas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GenerarEstadisticasTiempos {

	final Date date = new Date();
	final String fechaActual = date.getDay() + "" + date.getMonth() + "_" + date.getYear() + " " + date.getHours() + ":"
			+ date.getYear();

	public StringBuffer obtenerSalida(String f1, String f2, boolean generaCabecera) {
		StringBuffer sfRespuesta = new StringBuffer();
		JSONParser parser = new JSONParser();
		try {
			// Pares de ficheros, poner de dos en dos
			Object obj1 = parser.parse(new FileReader(f1));
			Object obj2 = parser.parse(new FileReader(f2));

			JSONObject jsonObject1 = (JSONObject) obj1;
			JSONObject jsonObject2 = (JSONObject) obj2;

			JSONArray arraysql = (JSONArray) jsonObject1.get("results");
			JSONArray arrayhost = (JSONArray) jsonObject2.get("results");

			// Cabeceras
			String cabecera = "Transaccion;SQL;Host;Codigo Repuesta";
			if (generaCabecera) {
				sfRespuesta.append(cabecera);
				sfRespuesta.append("\r\n");

			}
			for (int i = 0; i < arraysql.size(); i++) {
				JSONObject jsonSql = (JSONObject) arraysql.get(i);
				JSONObject jsonHost = (JSONObject) arrayhost.get(i);
				String nombre = (String) jsonSql.get("name");
				JSONArray tiemposSql = (JSONArray) jsonSql.get("times");
				JSONArray tiemposHost = (JSONArray) jsonHost.get("times");
				JSONObject resultadoSql = (JSONObject) jsonSql.get("responseCode");
				Long responseCode = (Long) resultadoSql.get("code");

				for (int z = 0; z < tiemposSql.size(); z++) {
					String linea = nombre + ";" + tiemposSql.get(z) + ";" + tiemposHost.get(z) + ";" + responseCode;
					System.out.println(linea);
					sfRespuesta.append(linea);
					sfRespuesta.append("\r\n");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return sfRespuesta;

	}

	public StringBuffer obtenerSalidaNewMan(String f1, String f2, boolean generaCabecera) throws IOException {

		StringBuffer sfRespuesta = new StringBuffer();
		JSONParser parser = new JSONParser();
		FileReader fMainFrame = null;
		FileReader fSQL = null;
		try {
			// Pares de ficheros, poner de dos en dos
			fMainFrame = new FileReader(f1);
			Object objMainFrame = parser.parse(fMainFrame);
			fSQL = new FileReader(f2);
			Object objSQL = parser.parse(fSQL);
			
			JSONObject jsonObjectMF = (JSONObject) objMainFrame;
			JSONObject jsonObjectSQL = (JSONObject) objSQL;


			JSONObject runMF = (JSONObject) jsonObjectMF.get("run");
			JSONObject runSQL = (JSONObject) jsonObjectSQL.get("run");

			
			JSONArray executionsMF = (JSONArray) runMF.get("executions");
			JSONArray executionsSQL = (JSONArray) runSQL.get("executions");

			// JSONObject item = (JSONObject) executions.get(0);
			// JSONArray assertions = (JSONArray) item.get("assertions");
			// System.out.println("nº de assertions:" + executions.size());
			// Cabeceras
			String cabecera = "Prueba;Transaccion;status code main;status code sql;tiempo mainfram; tiempo sql server; servidor caido";
			if (generaCabecera) {
				sfRespuesta.append(cabecera);
				sfRespuesta.append("\r\n");
			}

			for (int i = 0; i < executionsMF.size(); i++) {
				JSONObject executionMF = (JSONObject) executionsMF.get(i);
				JSONObject itemMF = (JSONObject) executionMF.get("item");
				String nombreMF = (String) itemMF.get("name");

				JSONObject requestMF = (JSONObject) itemMF.get("request");
				JSONObject bodyMF = (JSONObject) requestMF.get("body");
				String llamadaMF = (String) bodyMF.get("raw");

				String transaccionMF = Util.obtenerTransaccion(nombreMF);

				JSONObject responseMF = (JSONObject) executionMF.get("response");
				Long tiempoMF = (Long) responseMF.get("responseTime");
				//System.out.println(tiempoMF);
				
				Long responseCodeMF = 999l;
				try {
					responseCodeMF = (Long) responseMF.get("code");

				} catch (Exception e) {
					// e.printStackTrace();
					// System.out.println();
				}

				//JSONArray assertions = (JSONArray) executionMF.get("assertions");
				
				JSONObject executionSQL = (JSONObject) executionsSQL.get(i);
				JSONObject itemSQL = (JSONObject) executionSQL.get("item");
				String nombreSQL = (String) itemSQL.get("name");

				JSONObject requestSQL = (JSONObject) itemSQL.get("request");
				JSONObject bodySQL = (JSONObject) requestSQL.get("body");
				String llamadaSQL = (String) bodySQL.get("raw");

				String transaccionSQL = Util.obtenerTransaccion(nombreSQL);

				JSONObject responseSQL = (JSONObject) executionSQL.get("response");
				Long tiempoSQL = (Long) responseSQL.get("responseTime");
				JSONObject streamSQL = (JSONObject) responseSQL.get("stream");
				JSONArray respuestaSQL = (JSONArray) streamSQL.get("data");
				byte[] abRespuesta = new byte[respuestaSQL.size()];
 
				int cont = 0;
				for (Object object : respuestaSQL) {
					long b = (long) object;
					abRespuesta[cont++] = (byte)b;
				}
				boolean servidorCaido = false;
				String respuestaSSQL = new String(abRespuesta);
				if(respuestaSSQL.contains("Internal server error") || respuestaSSQL.contains("Timeout expired")) {
					servidorCaido = true;
				}
				//System.out.println(tiempoSQL);
				Long responseCodeSQL = 999L;
				
				try {
					responseCodeSQL = (Long) responseSQL.get("code");

				} catch (Exception e) {
					// e.printStackTrace();
					// System.out.println();
				}

				//JSONArray assertions = (JSONArray) executionSQL.get("assertions");
				
				// Array con tiempos
				String linea = nombreMF + ";" + transaccionMF + ";" + responseCodeMF + ";" + responseCodeSQL +";"+ tiempoMF + ";" + tiempoSQL +";" +servidorCaido;

				String errorDetectado = "";
				boolean resultadoGlobal = true;
				boolean resultadoPrueba = true;
				
				int contador = 0;
				sfRespuesta.append(linea);
				sfRespuesta.append("\r\n");

			}
		}

		catch (Exception e) {
			System.out.println("Error obtenerSalida: " + e);
			e.printStackTrace();
		} finally {
			fMainFrame.close();
			fSQL.close();

		}
		return sfRespuesta;
	
		
		/*
		StringBuffer sfRespuesta = new StringBuffer();
		JSONParser parser = new JSONParser();
		try {
			// Pares de ficheros, poner de dos en dos
			Object obj1 = parser.parse(new FileReader(f1));
			Object obj2 = parser.parse(new FileReader(f2));

			JSONObject jsonObject1 = (JSONObject) obj1;
			JSONObject jsonObject2 = (JSONObject) obj2;

			JSONArray arraysql = (JSONArray) jsonObject1.get("results");
			JSONArray arrayhost = (JSONArray) jsonObject2.get("results");

			// Cabeceras
			String cabecera = "Transaccion;SQL;Host;Codigo Repuesta";
			if (generaCabecera) {
				sfRespuesta.append(cabecera);
				sfRespuesta.append("\r\n");
			}
			for (int i = 0; i < arraysql.size(); i++) {
				JSONObject jsonSql = (JSONObject) arraysql.get(i);
				JSONObject jsonHost = (JSONObject) arrayhost.get(i);
				String nombre = (String) jsonSql.get("name");
				JSONArray tiemposSql = (JSONArray) jsonSql.get("times");
				JSONArray tiemposHost = (JSONArray) jsonHost.get("times");
				JSONObject resultadoSql = (JSONObject) jsonSql.get("responseCode");
				Long responseCode = (Long) resultadoSql.get("code");

				for (int z = 0; z < tiemposSql.size(); z++) {
					String linea = nombre + ";" + tiemposSql.get(z) + ";" + tiemposHost.get(z) + ";" + responseCode;
					System.out.println(linea);
					sfRespuesta.append(linea);
					sfRespuesta.append("\r\n");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return sfRespuesta;*/

	}
}
