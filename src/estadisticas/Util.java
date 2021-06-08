package estadisticas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Util {
	/**
	 * Obtiene el tipo de error localizado comparando las respuesta de mainframe y
	 * raincode
	 * 
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param responseCode
	 * @return tipo de error localizado
	 * @throws IOException 
	 */ 
	protected static String obtenerTipoDeError(String respuesta, String respuestaAlmacenada, Long responseCode) throws IOException {
		try {
			// AL INICIO SE DEBEN PONER LOS ERRORES NO SOLUCIONADOS PARA OBTENER MEJOR
			// RENDIMIENTO.
			if(respuesta.startsWith("{\"POSAZ500OperationResponse")) {
				System.out.println();
			}
			
			if(respuesta.contains(" \"message\": \"Resource not found\"")) {
				return "error 404 [31648];31648)";
			}
			if (ComprobarErrores.hayError29226(respuesta, respuestaAlmacenada, responseCode)) {
				return "error [29226];29226";
			}

			/// Identificar el tipo de error segï¿½n el retorno de las respuestas
			if (ComprobarErrores.hayError504(respuesta, respuestaAlmacenada, responseCode)) {
				return "error 504[25935];25935";
			}
			
			if (ComprobarErrores.hayErrorFraction(respuesta, respuestaAlmacenada, responseCode)) {
				return "error FRACTION_TOO_LONG[28895];28895";
			}
			
			
			if (ComprobarErrores.hayErrorBorraEntrada(respuesta, respuestaAlmacenada, responseCode)) {
				return "error borra entrada[28880];28880";
			}
			if(responseCode == 500 || respuesta.contains("{\"Fault\":{\"faultstring\":\"Failure in") || respuestaAlmacenada.contains("{\"Fault\":{\"faultstring\":\"Failure in")) {
				System.out.println(respuesta);
				System.out.println(respuestaAlmacenada);

			}
			//Error en mainframe pero no en rai
			
			
			if (ComprobarErrores.hayErrorConversionA(respuesta, respuestaAlmacenada, responseCode)) {
				return "error conversion vs A[28887];28887";
			}
			
			if (ComprobarErrores.hayErrorConversionB(respuesta, respuestaAlmacenada, responseCode)) {
				if(respuesta.startsWith("{\"POSAZ130OperationResponse")) {
					return "error conversion vs B (Cambiar en parte front)[28887]";
				}
				return "error conversion vs B [28887]";

			}
			
			if (ComprobarErrores.hayErrorConversionD(respuesta, respuestaAlmacenada, responseCode)) {
				return "error conversion vs D[28887];28887";
			}
			
			if (ComprobarErrores.hayErrorConversionMainA(respuesta, respuestaAlmacenada, responseCode)) {
				return "error A VS CONVERSION[28908];28908";
			}
			
			if (ComprobarErrores.hayErrorConversionMainB(respuesta, respuestaAlmacenada, responseCode)) {
				return "error B VS CONVERSION[28908];28908";
			}
			
			if (ComprobarErrores.hayErrorConversionMainD(respuesta, respuestaAlmacenada, responseCode)) {
				return "error D VS CONVERSION[28908];28908";
			}
			
			if (ComprobarErrores.hayError500(respuesta, respuestaAlmacenada, responseCode)) {
				//return "error 500 rep. pru. main[XXXXX];XXXXX";
			}
			
			if (ComprobarErrores.hayError305(respuesta, respuestaAlmacenada, responseCode)) {
				return "error -305[28665];28665";
			}
			
			if (ComprobarErrores.hayError206(respuesta, respuestaAlmacenada, responseCode)) {
				return "error -206 respuesta D vs respuesta B";
			}
			
			if (ComprobarErrores.hayError911(respuesta, respuestaAlmacenada, responseCode)) {
				return "error -911 bloqueo de tablas";
			}
			
			if (ComprobarErrores.hayError502(respuesta, respuestaAlmacenada, responseCode)) {
				return "xml 502[27997];27997;";
			}
			
			if (ComprobarErrores.hayErrorConvVsAbend(respuesta, respuestaAlmacenada, responseCode)) {
				return "Conversion vs abend[26933];26933;";
			}
			
			if (ComprobarErrores.hayError503(respuesta, respuestaAlmacenada, responseCode)) {
				return "error 503 servidor caido[27997];27997;";
			}
			
			if (ComprobarErrores.hayErrorCodif(respuesta, respuestaAlmacenada, responseCode)) {
				return "Error codificacion[28496];28496";
			}
			
			if (ComprobarErrores.hayErrorCodRedPrestacional(respuesta, respuestaAlmacenada, responseCode)) {
				return "RedPrestacional Modulo polizas[28870]";
			}
			
			if (ComprobarErrores.hayErrorCambiarColeccion(respuesta, respuestaAlmacenada, responseCode)) {
				return "Error Cambiar Coleccion[Distinto Orden]";
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
			
			if (ComprobarErrores.hayError902(respuesta, respuestaAlmacenada, responseCode)) {
				return "SQL -902[25897];25897";
			}
			
			if (ComprobarErrores.hayErrorPosaz538(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, diferencia en audit usuario";
			}

			if(ComprobarErrores.hayErrorOccurs528(respuesta, respuestaAlmacenada, responseCode)) {
				return "datos diferentes occurs[28419];28419";
			}
			
			// TODO ADAPTAR PARA OTROS ERRORES
			// Parche para error 593 (llega a en lugar de rgsao593)
			if (respuesta.replaceAll("\"a\"", "\"rgsao593e\"").equals(respuestaAlmacenada)) {
				return "Error redefines[25494];25494";
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
				return "QIX LINK[26373];26373";
			}

			// Parche para validar la parte dinamica en respuesta del 631
			if (ComprobarErrores.hayErrorPOSAZ631(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ503(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ514(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ516(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ558(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ582(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ626(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ518(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ130(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ533(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ593(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSLZ168(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSAZ610(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}
			
			if (ComprobarErrores.hayErrorPOSMZ142(respuesta, respuestaAlmacenada, responseCode)) {
				return "Sin error, parte dinamica en respuesta";
			}

			if (ComprobarErrores.hayErrorPOSAZ611CaracterCod(respuesta, respuestaAlmacenada, responseCode)) {
				return "Error caracter codificacion retorno[26963];26963";
			}

			if (ComprobarErrores.hayErrorCommunicationLink(respuesta, respuestaAlmacenada, responseCode)) {
				return "Communication link failure[26030];26030";
			}

			if (respuesta.replaceAll(" ", "").equals(respuestaAlmacenada.replaceAll(" ", ""))) {
				System.out.println(respuesta);
				System.out.println(respuestaAlmacenada);
				return "TRIM EN VALOR[25497];25497";
			}
			
			if (ComprobarErrores.validar611(respuesta, respuestaAlmacenada, responseCode)) {
				System.out.println(respuesta);
				System.out.println(respuestaAlmacenada);
				return "Sin error fecha dinamica";
			}
			
			
			
//			if (ComprobarErrores.hayErrorPOSAZ631Trim(respuesta, respuestaAlmacenada, responseCode)) {
//				return "TRIM EN VALOR[25497];25497";
//			}

			// if(respuestaAlmacenada.replaceAll("ï¿½ï¿½", "?").equals(respuesta)) {
			// return "error codificaciï¿½n ï¿½";
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
			
			
			/*** VALIDAR ORDEN DEL POSMZ143 Y TEXTO CURSOR*****/
			if (respuestaAlmacenada.startsWith("{\"POSMZ143OperationResponse")) {
				try {
					System.out.println(respuesta.substring(1000, respuesta.length()));
					char[] caracteresRespuesta = respuesta.substring(1000, respuesta.length()).replaceAll(" ", "").toCharArray();
					List<Character> listaRespuesta = new ArrayList<Character>();
					for (char c : caracteresRespuesta) {
						listaRespuesta.add(c);
					}
					char[] caracteresRespuestaAlmacenada = respuestaAlmacenada.substring(1000, respuestaAlmacenada.length()).replaceAll(" ", "").toCharArray();
					List<Character> listaRespuestaAlmacecnada = new ArrayList<Character>();
					for (char c : caracteresRespuestaAlmacenada) {
						listaRespuestaAlmacecnada.add(c);
					}
					Collections.sort(listaRespuesta);
					Collections.sort(listaRespuestaAlmacecnada);

//						System.out.println(listaRespuesta);
//						System.out.println(listaRespuestaAlmacecnada);
//						System.out.println("");

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
						return ("Respuesta distinto orden y distinto error en parrafo [26959];26959");
					}

				} catch (Exception e) {
					System.err.println(e);
				}
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
				if(respuestaAlmacenada.contains("INVALID_ZONED_DEC")) {
					return "CICS INVALID_ZONED_DEC mainframe vs other[27992];27992";
				} else if(responseCode == 502) {
					return "XML 502[27997];27997";
				} else if(respuesta.contains("OperationResponse") && respuestaAlmacenada.contains("abended 'ASRA'")){

					return "abend ASRA vs error A [27999];27999";
				} else {
//					System.out.println(respuestaAlmacenada);
//					System.out.println(respuesta.substring(0, 1000));
					return "Error cics vs other [27992];27992";
				}
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
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
//			}
			
			
			
			// Validar partes dinamicas 503
			if (respuesta.startsWith("{\"POSAZ503OperationResponse")) {
				if (respuestaAlmacenada.substring(0, 2292)
						.equals(respuesta.substring(0, 2292))
						&& (respuestaAlmacenada.substring(2300)
								.equals(respuesta.substring(2300))
								|| respuestaAlmacenada.substring(2300)
										.equals(respuesta.substring(2301)))) {
					return "sin error, parte dinamica (secuencia consulta)";
				}
				//System.out.println(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("nombre_via_e")));
				//System.out.println(respuestaAlmacenada.substring(2300));
				if(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("nombre_via_e")).equals(respuesta.substring(0, respuesta.indexOf("nombre_via_e")))) {
					System.out.println("La primera parte es igual");
					if(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("numero_e"), respuestaAlmacenada.indexOf("cod_consulta_s"))
								.equals((respuesta.substring(respuesta.indexOf("numero_e"), respuesta.indexOf("cod_consulta_s"))))) {
						System.out.println("Segunda parte con variable pero igual");
						if(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("rgsao503_salida"))
								.equals((respuesta.substring(respuesta.indexOf("rgsao503_salida"))))) {
							return "Error codificacion[28496];28496";
							
						}
					}
				} else if (respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("vacaciones_e"))
						.equals(respuesta.substring(0, respuesta.indexOf("vacaciones_e")))) {
					System.out.println("La primera parte es igual");
					if (respuestaAlmacenada
							.substring(respuestaAlmacenada.indexOf("observaciones_e"),
									respuestaAlmacenada.indexOf("nombre_via_e"))
							.equals((respuesta.substring(respuesta.indexOf("observaciones_e"),
									respuesta.indexOf("cod_consulta_s"))))) {
						if (respuestaAlmacenada
								.substring(respuestaAlmacenada.indexOf("numero_e"),
										respuestaAlmacenada.indexOf("cod_consulta_s"))
								.equals((respuesta.substring(respuesta.indexOf("numero_e"),
										respuesta.indexOf("cod_consulta_s"))))) {
							System.out.println("Segunda parte con variable pero igual");
							if (respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("rgsao503_salida"))
									.equals((respuesta.substring(respuesta.indexOf("rgsao503_salida"))))) {
								return "Error codificacion[28496];28496";

							}
						}
					}
				}
			}
			

			// fin de comprobar caracteres raros

//			if (respuestaAlmacenada.contains("\\//")) {
//				// TODO TRATAMIENTO SACAR PATRON
//
//				return "error backslash doble;25490";
//			} else if (respuesta.equals("undefined")) {
//				// TODO TRATAMIENTO SACAR PATRON
//				return "error respuesta vacia;";
//			} else if (respuestaAlmacenada.contains("\\/")) {
//				System.err.println(respuestaAlmacenada);
//				System.err.println(respuesta);
//
//				return "error backslash simple;25490";
//			}

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
			
			if (ComprobarErrores.hayError180(respuesta, respuestaAlmacenada, responseCode)) {
				return "error 180[XXXXX];XXXXX";
			}
			 
		
		 System.out.println();
		if (respuestaAlmacenada.startsWith("{\"POSAZ631OperationResponse")) {
			try {
				System.out.println(respuesta.substring(0, respuesta.indexOf("tbl_obser")+2124));
				System.out.println(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("tbl_obser")+2124));
				if(respuesta != null && respuesta.substring(0, respuesta.indexOf("tbl_obser")+2124).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("tbl_obser")+2124))) {
					System.out.println("Si");
					System.out.println(respuesta.length());
					System.out.println(respuestaAlmacenada.length());
					System.out.println((respuesta.indexOf("\"tbl_obser\"")+2254));
					System.out.println((respuestaAlmacenada.indexOf("\"tbl_obser\"")+2254));
					String res = respuesta.substring(respuesta.indexOf("\"tbl_obser\"")+2254, respuesta.length());
					String resA = respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("\"tbl_obser\"")+2254, respuestaAlmacenada.length());
					System.out.println(res);
					System.out.println(resA);
					if( res.equals(resA))
						return "Sin error";
					}
					//siguiente revisar TRIM EN VALOR[25497]
					System.out.println(respuesta);
					System.out.println(respuestaAlmacenada);
					System.out.println("");
				
					
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		if (respuestaAlmacenada.startsWith("{\"POSAZ611OperationResponse")) {
			System.out.println();
		}
		
		
		//Validacion de orden en el POSLZ165
		else if (respuestaAlmacenada.startsWith("{\"POSLZ165OperationResponse")) {
			
			try {
				if(respuestaAlmacenada.contains("{\"tipo_error\":\"A\"}") && respuesta.contains("{\"tipo_error\":\"A\"}")) {
					if(respuestaAlmacenada.contains("TIPO DE COLEGIADO NO PERMITIDO") && respuesta.contains("PROFESIONAL YA PERTENECE A LAVINIA")) {
						return "error cambiar orden [31195];31195";
					}
				} else if(respuestaAlmacenada.contains("{\"tipo_error\":\"B\"}") && respuesta.contains("{\"tipo_error\":\"B\"}")) {
					if(respuesta.substring(0, respuesta.indexOf("nro_prov_s")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("nro_prov_s")))) {
						if(respuesta.substring(respuesta.indexOf("nif_prof_s"), respuesta.length()).equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("nif_prof_s"), respuestaAlmacenada.length()))) {
							return "error cambiar orden [31195];31195";
						}

					
					}
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (respuestaAlmacenada.startsWith("{\"POSLZ169OperationResponse")) {
			System.out.println(respuesta);
			System.out.println(respuestaAlmacenada);
			System.out.println();
		}

		
		if(respuesta.contains("Invalid numeric value")) {
			System.out.println(respuesta);
			System.out.println(respuestaAlmacenada);
			if(respuestaAlmacenada.contains("abended")){
				return "Sin error";
			} else {
				return "error corta llamada invalid numeric[31180];[31180]";
			}
		}
		
		if (respuestaAlmacenada.startsWith("{\"POSMZ143OperationResponse")) {
			System.out.println(respuesta.substring(respuesta.length()-200, respuesta.length()));
			if(respuesta.contains("") || respuesta.contains("48200")) {
				return "error codificación 143";
			}
		}
		
		
		if (respuestaAlmacenada.startsWith("{\"POSMZ150OperationResponse")) {
			System.out.println(respuesta.substring(0, respuesta.length()-2300));
			System.out.println(respuestaAlmacenada.substring(0, respuestaAlmacenada.length()-2300));
			System.out.println();

			if(respuesta.substring(0, respuesta.length()-2300).equals(respuestaAlmacenada.substring(respuestaAlmacenada.length()-2300))){
				return "error volcado datos[];";
			}
		}

		if (ComprobarErrores.hayErrorRlel(respuesta, respuestaAlmacenada, responseCode)) {
			return "RLEL mainframe;;";
		}	

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}	
		if (respuesta.startsWith("{\"POSAZ560OperationResponse") || respuesta.startsWith("{\"POSAZ535OperationResponse") || respuesta.startsWith("{\"POSAZ508OperationResponse") || respuesta.startsWith("{\"POSAZ524OperationResponse")) {
//			System.out.println(respuesta);
//			System.out.println(respuestaAlmacenada);
			return "Respuesta distinto orden / TRIM VALOR  [26959][25497];26959-25497";
		}
		
		if (respuestaAlmacenada.startsWith("{\"POSAZ508OperationResponse") ) {
//			System.out.println(respuesta);
//			System.out.println(respuestaAlmacenada);
			return "Respuesta distinto orden / TRIM VALOR  [26959][25497];26959-25497";
		}
		
		if ( respuesta.startsWith("{\"POSAZ527OperationResponse")) {
			System.out.println(respuesta);
			System.out.println(respuestaAlmacenada);
			System.out.println();

			//return "Respuesta distinto orden / TRIM VALOR  [26959][25497];26959-25497";

		}

	
		return "Error sin detectar";
		// TODO PARCHE FECHA PROCESO PARA 631 fec_ult_proceso_s
	}

	protected static void localizarPatronSlash(String respuesta, String respuestaAlmacenada) {
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

	protected static String obtenerTransaccion(String texto) {
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
