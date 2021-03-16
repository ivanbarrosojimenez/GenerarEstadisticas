
package estadisticas;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GenerarEstadisticasResultados {

	final Date date = new Date();
	final String fechaActual = date.getDay() + "" + date.getMonth() + "_" + date.getYear() + " " + date.getHours() + ":"
			+ date.getYear();

	public StringBuffer obtenerSalidaPostman(String f1, boolean generaCabecera) {
		StringBuffer sfRespuesta = new StringBuffer();
		JSONParser parser = new JSONParser();
		try {
			// Pares de ficheros, poner de dos en dos
			Object obj1 = parser.parse(new FileReader(f1));

			JSONObject jsonObject1 = (JSONObject) obj1;

			JSONArray arrayDb2 = (JSONArray) jsonObject1.get("results");

			// Cabeceras
			String cabecera = "Prueba;Transaccion;status code;Mismo tipo error;Mismo status code; respuesta es un json; Respuesta el la misma que en host;Resultado global;TipoError";
			if (generaCabecera) {
				sfRespuesta.append(cabecera);
				sfRespuesta.append("\r\n");
			}

			for (int i = 0; i < arrayDb2.size(); i++) {
				JSONObject jsonDb2 = (JSONObject) arrayDb2.get(i);
				String nombre = (String) jsonDb2.get("name");
				String transaccion = obtenerTransaccion((String) jsonDb2.get("name"));
				JSONArray tiemposDb2 = (JSONArray) jsonDb2.get("times");
				JSONObject resultadoDb2 = (JSONObject) jsonDb2.get("responseCode");
				Long responseCode = (Long) resultadoDb2.get("code");

				JSONArray allTest = (JSONArray) jsonDb2.get("allTests");
				JSONObject pruebas = (JSONObject) allTest.get(0);
				Set set = pruebas.entrySet();
				Collection hmErrorV = pruebas.values();
				Collection hmErrorK = pruebas.keySet();
				Iterator iteratorK = hmErrorK.iterator();
				Iterator iteratorV = hmErrorV.iterator();

				String linea = nombre + ";" + transaccion + ";" + responseCode;
				String[] partesLlamadas;
				String respuesta;
				String respuestaAlmacenada;
				String errorDetectado = "";
				boolean resultadoGlobal = true;
				while (iteratorK.hasNext()) {
					Boolean clave = (Boolean) iteratorV.next();
					String valor = (String) iteratorK.next();

					if (valor.startsWith("Respuesta es la esperada") && !clave) {
						try {
							partesLlamadas = valor.split("b613ab3bd4de8c5ddf771161c4e27f3a");
							respuesta = partesLlamadas[1];
							respuestaAlmacenada = partesLlamadas[2];
							errorDetectado = obtenerTipoDeError(respuesta, respuestaAlmacenada, responseCode);
							if (errorDetectado.startsWith("Sin error")) {
								clave = true;
								errorDetectado = "";
							}
						} catch (Exception e) {

						}
					}
					resultadoGlobal = resultadoGlobal && clave;

					linea += ";" + clave;
				}

				linea += ";" + resultadoGlobal + ";" + errorDetectado;
				errorDetectado = "";

				// System.out.println(linea);
				sfRespuesta.append(linea);
				sfRespuesta.append("\r\n");
			}

		} catch (Exception e) {
			System.err.println(e);
		}
		return sfRespuesta;

	}

	public StringBuffer obtenerSalidaNewMan(String f1, boolean generaCabecera, String ficheroSalidaErrores)
			throws IOException {
		StringBuffer sfRespuesta = new StringBuffer();
		JSONParser parser = new JSONParser();
		FileReader fr = null;
		GrabarFichero grabarFichero = null;
		try {
			grabarFichero = new GrabarFichero();
			grabarFichero.abrirFichero("salidaErrores/" + ficheroSalidaErrores, true);

			// Pares de ficheros, poner de dos en dos
			fr = new FileReader(f1);
			Object obj1 = parser.parse(fr);
			JSONObject jsonObject1 = (JSONObject) obj1;

			JSONObject run = (JSONObject) jsonObject1.get("run");
			JSONArray executions = (JSONArray) run.get("executions");
			// JSONObject item = (JSONObject) executions.get(0);
			// JSONArray assertions = (JSONArray) item.get("assertions");
			// System.out.println("nº de assertions:" + executions.size());
			// Cabeceras
			String cabecera = "Prueba;Transaccion;status code;mismo status code;es json;Respuesta==mainframe;mismo tipos error;Resultado global;clasificación error; Id Board";
			if (generaCabecera) {
				sfRespuesta.append(cabecera);
				sfRespuesta.append("\r\n");
			}

			for (int i = 0; i < executions.size(); i++) {
				JSONObject execution = (JSONObject) executions.get(i);
				JSONObject item = (JSONObject) execution.get("item");
				String nombre = (String) item.get("name");

				JSONObject request = (JSONObject) item.get("request");
				JSONObject body = (JSONObject) request.get("body");
				String llamada = (String) body.get("raw");

				String transaccion = obtenerTransaccion(nombre);

				JSONObject response = (JSONObject) execution.get("response");
				Long responseCode = 999l;
				try {
					responseCode = (Long) response.get("code");

				} catch (Exception e) {
					// e.printStackTrace();
					// System.out.println();
				}

				JSONArray assertions = (JSONArray) execution.get("assertions");
				// Array con los 4 test
				String linea = nombre + ";" + transaccion + ";" + responseCode;
				String[] partesLlamadas;
				String respuesta;
				String respuestaAlmacenada;
				String errorDetectado = "";
				boolean resultadoGlobal = true;
				boolean resultadoPrueba = true;
				int contador = 0;
				for (Object object : assertions) {
					JSONObject pruebas = (JSONObject) object;
					Set set = pruebas.entrySet();
					Collection hmErrorV = pruebas.values();
					Collection hmErrorK = pruebas.keySet();
					Iterator iteratorK = hmErrorK.iterator();
					Iterator iteratorV = hmErrorV.iterator();
					// orden status, es json, respuesta = , tipo error
					contador++;
					resultadoPrueba = true;
					while (iteratorK.hasNext()) {
						String clave = (String) iteratorK.next();
						// System.out.println("Pasando por " + clave);
						if (clave.equals("error")) {
							resultadoPrueba = false;
							String valor = (String) iteratorV.next();
							if (valor.startsWith("Respuesta es la esperada")) {
								// System.out.println(valor);
								resultadoGlobal = false;
								try {
									partesLlamadas = valor.split("b613ab3bd4de8c5ddf771161c4e27f3a");
									respuesta = partesLlamadas[1];
									respuestaAlmacenada = partesLlamadas[2];
									errorDetectado = obtenerTipoDeError(respuesta, respuestaAlmacenada, responseCode);
									if (errorDetectado.startsWith("Sin error")) {
										errorDetectado = "";
										resultadoGlobal = true;
									}
									if (errorDetectado.startsWith("Error sin detectar") && responseCode == 999) {
										errorDetectado = "Error tratar respuesta (posible json incompleto)";
									}
								} catch (Exception e) {

								}
							}

						}
					}
					linea += ";" + resultadoPrueba;
				}
				linea += ";" + resultadoGlobal + ";" + errorDetectado;
				errorDetectado = "";
				// CREAR FICHERO CON LOS ERRORES PARA CREAR COLECCIONES
				if (!resultadoGlobal) {
					grabarFichero.agregarAFichero("############ JSON DE LLAMADA #############\r\n");
					grabarFichero.agregarAFicheroExistente(llamada.replaceAll("\r\n", "") + "\r\n");
					grabarFichero.agregarAFichero("############ TIEMPOS #############\r\n");

				}

				// System.out.println(linea);
				sfRespuesta.append(linea);
				sfRespuesta.append("\r\n");

			}
		}

		catch (

		Exception e) {
			System.out.println("Error obtenerSalida: " + e);
			e.printStackTrace();
		} finally {
			fr.close();
			grabarFichero.cerrarFichero();
			return sfRespuesta;
		}
	}

	/**
	 * Obtiene el tipo de error localizado comparando las respuesta de mainframe y
	 * raincode
	 * 
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param responseCode
	 * @return tipo de error localizado
	 */
	private String obtenerTipoDeError(String respuesta, String respuestaAlmacenada, Long responseCode) {
		try {
			// AL INICIO SE DEBEN PONER LOS ERRORES NO SOLUCIONADOS PARA OBTENER MEJOR
			// RENDIMIENTO.

			/// Identificar el tipo de error según el retorno de las respuestas
			if (ComprobarErrores.hayError504(respuesta, respuestaAlmacenada, responseCode)) {
				return "error 504[25935];25935";
			}

			if (ComprobarErrores.hayErrorOverflow(respuesta, respuestaAlmacenada, responseCode)) {
				return "OUTPUT_OVERFLOW[25933];25933";
			}

			if (ComprobarErrores.hayErrorPgmiderr(respuesta, respuestaAlmacenada, responseCode)) {
				return "PGMIDERR[25561];25561";
			}

			if (ComprobarErrores.hayErrorInt32(respuesta, respuestaAlmacenada, responseCode)) {
				return "System.Int32[25489];25489";
			}

			if (ComprobarErrores.hayErrorDecimal(respuesta, respuestaAlmacenada, responseCode)) {
				return "System.Int32[25489];25489";
			}

			if (ComprobarErrores.hayErrorUndefinedElement(respuesta, respuestaAlmacenada, responseCode)) {
				return "UNDEFINED_ELEMENT[26151];26151";
			}

			if (ComprobarErrores.hayErrorDfhcommarea(respuesta, respuestaAlmacenada, responseCode)) {
				return "dfhcommarea[25491];25491";
			}

			if (ComprobarErrores.hayError104(respuesta, respuestaAlmacenada, responseCode)) {
				return "SQL -104[25127];25127";
			}

			if (ComprobarErrores.hayError30081(respuesta, respuestaAlmacenada, responseCode)) {
				return "SQL -30081[26558];26558";
			}

			if (ComprobarErrores.hayError99999(respuesta, respuestaAlmacenada, responseCode)) {
				return "SQL -99999[25177];25177";
			}

			if (ComprobarErrores.hayError969(respuesta, respuestaAlmacenada, responseCode)) {
				return "SQL -969[25897];25897";
			}

			// TODO ADAPTAR PARA OTROS ERRORES
			// Parche para error 593 (llega a en lugar de rgsao593)
			if (respuesta.replaceAll("\"a\"", "\"rgsao593e\"").equals(respuestaAlmacenada)) {
				return "Error redefines[25494];25494";
			}

			if (ComprobarErrores.hayErrorRlel(respuesta, respuestaAlmacenada, responseCode)) {
				return "RLEL mainframe;;";
			}

			if (ComprobarErrores.hayErrorCouldNotLoadModule(respuesta, respuestaAlmacenada, responseCode)) {
				return "Could not load module[26119];26119";
			}

			if (ComprobarErrores.hayErrorCurrentStateIsClosed(respuesta, respuestaAlmacenada, responseCode)) {
				return "ExecuteNonQuery[26240];26240";
			}
			if (ComprobarErrores.hayErrorRangeError(respuesta, respuestaAlmacenada, responseCode)) {
				return "Range error[25493];25493";
			}

			if (ComprobarErrores.hayErrorQIX(respuesta, respuestaAlmacenada, responseCode)) {
				return "QIX LINK;26373";
			}

			// Parche para validar la parte dinamica en respuesta del 631
			if (ComprobarErrores.hayErrorPOSAZ631(respuesta, respuestaAlmacenada, responseCode)) {
				return "error posaz631 revisar";
			}

			if (ComprobarErrores.hayErrorPOSAZ611CaracterCod(respuesta, respuestaAlmacenada, responseCode)) {
				return "Error caracter codificación;26963";
			}

			if (respuesta.contains("Communication link failure")) {
				return "Communication link failure;26030";
			}

			if (respuesta.startsWith("{\"POSMZ140OperationResponse")) {
				return "Bloquea prueba por error QIX;26373";
			}

			if (respuesta.replaceAll(" ", "").equals(respuestaAlmacenada.replaceAll(" ", ""))) {
				return "TRIM EN VALOR[25497];25497";
			}

			// if(respuestaAlmacenada.replaceAll("ƒÂ", "?").equals(respuesta)) {
			// return "error codificación ƒ";
			// }..

			if (respuesta.contains("") || respuesta.contains("\\u0000") || respuesta.contains("\\u0007")
					|| respuesta.contains("\\u0017") || respuestaAlmacenada.contains("Â“")) {
				System.out.println(respuesta);
				System.out.println(respuestaAlmacenada);
				return "CARACTER-DESBORDAMIENTO;25493";
			}

			/**Para validar distinto orden junto a espacios de mas*/
			try {
				char[] caracteresRespuesta = respuesta.replaceAll(" ", "").toCharArray();
				List<Character> listaRespuesta = new ArrayList<Character>();
				for (char c : caracteresRespuesta) {
					listaRespuesta.add(c);
				}
				char[] caracteresRespuestaAlmacenada = respuestaAlmacenada.replaceAll(" ", "").toCharArray();
				List<Character> listaRespuestaAlmacecnada = new ArrayList<Character>();
				for (char c : caracteresRespuestaAlmacenada) {
					listaRespuestaAlmacecnada.add(c);
				}
				Collections.sort(listaRespuesta);
				Collections.sort(listaRespuestaAlmacecnada);

//					System.out.println(listaRespuesta);
//					System.out.println(listaRespuestaAlmacecnada);
//					System.out.println("");

				boolean diferente = false;
				diferente = !(listaRespuesta.size() == listaRespuestaAlmacecnada.size());
				if (listaRespuesta.size() > listaRespuestaAlmacecnada.size()) {
					for (int i = 0; i < listaRespuesta.size() && i < listaRespuestaAlmacecnada.size()
							&& !diferente; i++) {
						if (!listaRespuesta.get(i).equals(listaRespuestaAlmacecnada.get(i))) {
							diferente = true;
						}

					}

				} else {
					for (int i = 0; i < listaRespuesta.size() && i < listaRespuesta.size() && !diferente; i++) {
						if (!listaRespuestaAlmacecnada.get(i).equals(listaRespuesta.get(i))) {
							diferente = true;
						}
					}
				}
				if (!diferente) {
					System.out.println("orden raro");
					return ("Respuesta distinto orden / TRIM VALOR  [26959][25497];26959-25497");
				}

			} catch (Exception e) {
				System.err.println(e);
			}
			
			try {
				char[] caracteresRespuesta = respuesta.toCharArray();
				List<Character> listaRespuesta = new ArrayList<Character>();
				for (char c : caracteresRespuesta) {
					listaRespuesta.add(c);
				}
				char[] caracteresRespuestaAlmacenada = respuestaAlmacenada.toCharArray();
				List<Character> listaRespuestaAlmacecnada = new ArrayList<Character>();
				for (char c : caracteresRespuestaAlmacenada) {
					listaRespuestaAlmacecnada.add(c);
				}
				Collections.sort(listaRespuesta);
				Collections.sort(listaRespuestaAlmacecnada);

//					System.out.println(listaRespuesta);
//					System.out.println(listaRespuestaAlmacecnada);
//					System.out.println("");

				boolean diferente = false;
				diferente = !(listaRespuesta.size() == listaRespuestaAlmacecnada.size());
				if (listaRespuesta.size() > listaRespuestaAlmacecnada.size()) {
					for (int i = 0; i < listaRespuesta.size() && i < listaRespuestaAlmacecnada.size()
							&& !diferente; i++) {
						if (!listaRespuesta.get(i).equals(listaRespuestaAlmacecnada.get(i))) {
							diferente = true;
						}

					}

				} else {
					for (int i = 0; i < listaRespuesta.size() && i < listaRespuesta.size() && !diferente; i++) {
						if (!listaRespuestaAlmacecnada.get(i).equals(listaRespuesta.get(i))) {
							diferente = true;
						}
					}
				}
				if (!diferente) {
					System.out.println("orden raro");
					return ("Respuesta con distinto orden[26959];26959");
				}

			} catch (Exception e) {
				System.err.println(e);
			}

			if (respuestaAlmacenada.contains("Failure interacting with CICS")
					&& !respuesta.contains("Failure interacting with CICS")) {
				return "CICS mainframe";
			}

			// Comprobacion de caracteres raros
//			if(respuesta.startsWith("{\"POSAZ592OperationResponse")) {
			try {
				String cleanAlmacenado = respuestaAlmacenada.replaceAll("\\P{Print}", "");
				String clean = respuesta.replaceAll("\\P{Print}", "");

//					System.out.println(respuesta);
//					System.out.println(respuestaAlmacenada);
				cleanAlmacenado = cleanAlmacenado.replaceAll(" ", "");
				clean = clean.replaceAll(" ", "");

//					System.err.println(cleanAlmacenado);
//					System.err.println(clean);
//					System.out.println(".");
				if (clean.equals(cleanAlmacenado)) {
					System.out.println("Error codificacion");
					return "Error codificacion";
				}

				// Validar partes dinamicas 503
				if (respuesta.startsWith("{\"POSAZ503OperationResponse")) {
					if (cleanAlmacenado.replaceAll(" ", "").substring(0, 2292)
							.equals(clean.replaceAll(" ", "").substring(0, 2292))
							&& (cleanAlmacenado.replaceAll(" ", "").substring(2300)
									.equals(clean.replaceAll(" ", "").substring(2300))
									|| cleanAlmacenado.replaceAll(" ", "").substring(2300)
											.equals(clean.replaceAll(" ", "").substring(2301)))) {
						return "error codificación caracteres";
					}
				}

			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
//			}

			// fin de comprobar caracteres raros

			if (respuestaAlmacenada.contains("\\//")) {
				// TODO TRATAMIENTO SACAR PATRON

				return "error backslash doble;25490";
			} else if (respuesta.equals("undefined")) {
				// TODO TRATAMIENTO SACAR PATRON
				return "error respuesta vacia;";
			} else if (respuestaAlmacenada.contains("\\/")) {
				return "error backslash simple;25490";
			}

//			if (respuesta.length() > 356 && respuestaAlmacenada.length() > 356) {
//				if (respuesta.substring(0, 357).equals(respuestaAlmacenada.substring(0, 357))) {
//					return "Error en orden salida occurs;";
//				}
//			}

			// }
			if (respuesta.startsWith("{\"POSAZ620OperationResponse")) {
				if (!respuesta.substring(592, 593).equals(respuestaAlmacenada.substring(592, 593))) {
					return "num reg. diferente[27526];27526";
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

		// System.err.println(respuesta);
		// System.err.println(respuestaAlmacenada);

		return "Error sin detectar";
		// TODO PARCHE FECHA PROCESO PARA 631 fec_ult_proceso_s
	}

	private void localizarPatronSlash(String respuesta, String respuestaAlmacenada) {
		TreeSet<Character> simple = new TreeSet<>();
		TreeSet<Character> dobleDel = new TreeSet<>();
		TreeSet<Character> dobleDet = new TreeSet<>();
		char[] arrayChar = respuestaAlmacenada.toCharArray();
		for (int i = 0; i < arrayChar.length; i++) {
			// Baso base, un slash
			if (arrayChar[i] == '/') {
				// Si tiene una barra \ detras
				if (arrayChar[i - 1] == '\\') {
					// doble backSlash
					if (arrayChar[i - 2] == '\\') {
						// System.out.println("doble detras, detras;[" + arrayChar[i-3] + "] contexto:"
						// + respuestaAlmacenada.substring(i-5, i+10));
						dobleDel.add(arrayChar[i - 2]);
						// doble slash
					} else if (arrayChar[i + 1] == '/') {
						// System.out.println("doble delante, detras;[" + arrayChar[i-3] + "] contexto:"
						// + respuestaAlmacenada.substring(i-5, i+10));
						dobleDet.add(arrayChar[i - 2]);
					} else {
						// simple slash
						simple.add(arrayChar[i - 2]);
						// System.out.println("simple, detras;[" + arrayChar[i-2]+"]; contexto:" +
						// respuestaAlmacenada.substring(i-5, i+10));
					}
				}
			}
		}
		// System.out.println("Simple \\/");
		for (char c : simple) {
			System.out.println(c);
		}
		if (dobleDel.size() > 0) {
			System.out.println("doble delante \\\\/");
			for (char c : dobleDel) {
				System.out.println(c);
			}
		}
		if (dobleDet.size() > 0) {
			System.out.println("doble detras \\//");
			for (char c : dobleDet) {
				System.out.println(c);
			}
		}
	}

	private String obtenerTransaccion(String texto) {
		String[] partesNombrePrueba = texto.split(" ");
		for (String string : partesNombrePrueba) {
			if (string.startsWith("PW") || string.startsWith("PG")) {
				if (!string.endsWith("-js"))
					return string + "-js";

				return string;
			}
		}
		return "";

	}
}
