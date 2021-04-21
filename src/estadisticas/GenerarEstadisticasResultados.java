
package estadisticas;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import estadisticas.*;

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
				String transaccion = Util.obtenerTransaccion((String) jsonDb2.get("name"));
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
				String respuesta = "";
				String respuestaAlmacenada = "";
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
							errorDetectado = Util.obtenerTipoDeError(respuesta, respuestaAlmacenada, responseCode)+";";
							if (errorDetectado.startsWith("Sin error")) {
								clave = true;
								errorDetectado = "";
							}
						} catch (Exception e) {
							System.out.println("error " + e);	
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
			String cabecera = "Prueba;Transaccion;status code;mismo status code;es json;Respuesta==mainframe;mismo tipos error;Resultado global;clasificación error; Id Board;RAINCODE;MAINFRAME";
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

				String transaccion = Util.obtenerTransaccion(nombre);

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
				String respuesta = "";
				String respuestaAlmacenada="";
				String errorDetectado = "";
				boolean resultadoGlobal = true;
				boolean resultadoPrueba = true;
				int contador = 0;
				if(assertions != null) {
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
										errorDetectado = Util.obtenerTipoDeError(respuesta, respuestaAlmacenada, responseCode)+";";
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
				}
				
				linea += ";" + resultadoGlobal + ";" + errorDetectado;
//				if(!resultadoGlobal) {
					//linea+=";" + respuesta + ";" + respuestaAlmacenada;
			//	}
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

	
}
