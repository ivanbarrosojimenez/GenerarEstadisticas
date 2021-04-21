package estadisticas;

public class ComprobarErrores {
	
	/**
	 * Comprueba si la respuesta tiene un codigo 504
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayError504(String respuesta, String respuestaAlmacenada, long responseCode) {
		return(responseCode == 504);
	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorFraction(String respuesta, String respuestaAlmacenada, long responseCode) {
		if(respuesta.contains("OUTPUT_OVERFLOW") && respuestaAlmacenada.contains("FRACTION_TOO_LONG")) {
		System.out.println(respuesta);
		System.out.println(respuestaAlmacenada);
		System.out.println();
		}
		return(respuesta.contains("OUTPUT_OVERFLOW") && respuestaAlmacenada.contains("FRACTION_TOO_LONG"));
	}
	
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayError500(String respuesta, String respuestaAlmacenada, long responseCode) {
		if(responseCode == 500 && !respuesta.contains("ExecuteNonQuery") && !respuesta.contains("Communication link failure")) {
		System.out.println(respuesta);
		System.out.println(respuestaAlmacenada);
		System.out.println();
		}
		
		return(responseCode == 500  && !respuesta.contains("ExecuteNonQuery") && !respuesta.contains("Communication link failure"));
	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorBorraEntrada(String respuesta, String respuestaAlmacenada, long responseCode) {
		if(respuestaAlmacenada.replaceAll("S","").equals(respuesta.replaceAll("S",""))) {
		System.out.println(respuesta);
		System.out.println(respuestaAlmacenada);
		System.out.println();
		}
		return(respuestaAlmacenada.contains("JSON to data transformation failed")
				&& respuesta.contains(" \"tipo_error\": \"B"));
	}
	
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorConversionA(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("JSON to data transformation failed") && respuestaAlmacenada.contains("tipo_error\":\"A"));
	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorConversionB(String respuesta, String respuestaAlmacenada, long responseCode) {

		return (respuesta.contains("JSON to data transformation failed") && respuestaAlmacenada.contains("tipo_error\":\"B"));
			

	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorConversionMainD(String respuesta, String respuestaAlmacenada, long responseCode) {

		return (respuestaAlmacenada.contains("JSON to data transformation failed") && respuesta.contains("tipo_error\":\"D"));
	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorConversionMainA(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuestaAlmacenada.contains("JSON to data transformation failed") && respuesta.contains("tipo_error\":\"A"));
	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorConversionMainB(String respuesta, String respuestaAlmacenada, long responseCode) {

		return (respuestaAlmacenada.contains("JSON to data transformation failed") && respuesta.contains("tipo_error\":\"B"));
			

	}
	
	/**
	 * Comprueba si la respuesta almacenada tiene un codigo 500
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayErrorConversionD(String respuesta, String respuestaAlmacenada, long responseCode) {

		return (respuesta.contains("JSON to data transformation failed") && respuestaAlmacenada.contains("tipo_error\":\"D"));
			

	}
	
	/**
	 * Comprueba si la respuesta tiene un codigo 180 en la respuesta
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 504
	 */
	public static boolean hayError180(String respuesta, String respuestaAlmacenada, long responseCode) {
		return(respuesta.contains(" -180") && !respuestaAlmacenada.contains(" -180"));
	}
	
	/**
	 * Comprueba si la respuesta tiene un codigo 503
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 503
	 */
	public static boolean hayError503(String respuesta, String respuestaAlmacenada, long responseCode) {
		return(responseCode == 503);
	}
	
	/**
	 * Comprueba si la respuesta tiene un codigo 502
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene error 503
	 */
	public static boolean hayError502(String respuesta, String respuestaAlmacenada, long responseCode) {
		return(responseCode == 502);
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena OUTPUT_OVERFLOW
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorOverflow(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("OUTPUT_OVERFLOW"));
	}
	
	
	/**
	 * Comprueba si la respuesta contiene la cadena PGMIDERR
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorPgmiderr(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("PGMIDERR"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena System.Int32
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorInt32(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("System.Int32"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena System.Decimal
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorDecimal(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("System.Decimal"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena UNDEFINED_ELEMENT
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorUndefinedElement(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("UNDEFINED_ELEMENT"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -104
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError104(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains(" -104"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena UNDEFINED_ELEMENT
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorDfhcommarea(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("dfhcommarea"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena -30081
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError30081(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("-30081"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -99999
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError99999(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains(" -99999"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -969
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError969(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains(" -969"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -902
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError902(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains(" -902"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -305
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError305(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains(" -305"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -206
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError206(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuestaAlmacenada.contains(" -206"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  -911
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayError911(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuestaAlmacenada.contains(" -911"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  RLEL
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorRlel(String respuesta, String respuestaAlmacenada, long responseCode) {
		if(respuesta.contains("ExecuteNonQuery")) return false;
		return (respuestaAlmacenada.contains("RLEL"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  Could not load module
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorCouldNotLoadModule(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("Could not load module"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  current state is Closed
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorCurrentStateIsClosed(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("current state is Closed"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  Range error
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorRangeError(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("Range error"));
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena  QIX LINK
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorQIX(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("QIX LINK"));
	}
	
	/**
	 * Comprueba si la respuesta es valida para la transaccion 631 (contiene parte dinamica con la fecha hh mm ss actualues)
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si es valida
	 */
	public static boolean hayErrorPOSAZ631(String respuesta, String respuestaAlmacenada, long responseCode) {
		if (respuesta.startsWith("{\"POSAZ631OperationResponse")) {
			/**Esta parte valida la fecha de modificacion tambien
			String respuestaSinFechas = respuesta
					.replace(respuesta.substring(respuesta.indexOf("fec_ult_proceso_s"),
							respuesta.indexOf("fec_ult_proceso_s") + 39), "")
					.replace(respuesta.substring(respuesta.indexOf("fec_modificacion_s"),
							respuesta.indexOf("fec_modificacion_s") + 39), "");

			String respuestaAlmacenadaSinFechas = respuesta
					.replace(respuesta.substring(respuesta.indexOf("fec_ult_proceso_s"),
							respuesta.indexOf("fec_ult_proceso_s") + 39), "")
					.replace(respuesta.substring(respuesta.indexOf("fec_modificacion_s"),
							respuesta.indexOf("fec_modificacion_s") + 39), "");
			// System.out.println(respuestaSinFechas);
			// System.out.println(respuestaAlmacenadaSinFechas);
			// System.out.println(respuestaSinFechas.equals(respuestaAlmacenadaSinFechas));
			
	*/
			//Validar sin tener en cuenta la parte dinamica, la fecha ultimo proceso
			
//			if(!(respuesta.substring(0, 2461).equals(respuestaAlmacenada.substring(0, 2461)) && 
//					respuesta.substring(2511).equals(respuestaAlmacenada.substring(2511)))) {
//				System.out.println(respuesta.substring(0, 2461));
//				System.out.println(respuestaAlmacenada.substring(0, 2461));
//				System.out.println(respuesta.substring(2511));
//				System.out.println(respuestaAlmacenada.substring(2511));
//				System.out.println(respuesta);
//				System.out.println(respuestaAlmacenada);
//
//				System.out.println();
//			}
			return(respuesta.substring(0, 2461).equals(respuestaAlmacenada.substring(0, 2461)) && 
					respuesta.substring(2511).equals(respuestaAlmacenada.substring(2511)));
			
//			System.out.println(respuestaSinFechas.equals(respuestaAlmacenadaSinFechas));
//			return respuestaSinFechas.equals(respuestaAlmacenadaSinFechas);
		}
		return false;
	}
	
	/**
	 * Comprueba si la respuesta es valida para la transaccion 611 controlando \n por ?
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si es valida
	 */
	public static boolean hayErrorPOSAZ611CaracterCod(String respuesta, String respuestaAlmacenada, long responseCode) {
		if (respuesta.startsWith("{\"POSAZ611OperationResponse")) {
			try {
				// System.out.println(respuestaAlmacenada);
				// System.out.println();
				// System.out.println(respuesta.replaceAll("\\?", "\\\\n"));
				return (respuestaAlmacenada.equals(respuesta.replaceAll("\\?", "\\\\n")));
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return false;
	}
	
	/**
	 * Comprueba si la respuesta contiene la cadena Communication link failure
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorCommunicationLink(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("Communication link failure"));
	}
	
	/**
	 * Comprueba si la respuesta contiene Trim en valor
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorTrimValor(String respuesta, String respuestaAlmacenada, long responseCode) {
		if (respuesta.replaceAll(" ", "").equals(respuestaAlmacenada.replaceAll(" ", ""))) {
			try {
				return (respuesta.contains("TRIM EN VALOR"));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return false;
	
	}
	
	/**
	 * Comprueba si la respuesta contiene error validacion vs error abend
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorConvVsAbend(String respuesta, String respuestaAlmacenada, long responseCode) {

		return (respuesta != null && respuestaAlmacenada != null &&
				respuestaAlmacenada.contains("OUTPUT_OVERFLOW") && 
				respuesta.contains("abended"));
				
	}
	
	
	/**
	 * Comprueba si la respuesta contiene cambio en auditusuario
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorPosaz538(String respuesta, String respuestaAlmacenada, long responseCode) {
		
		try {
			if (respuestaAlmacenada.startsWith("{\"POSAZ538OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("audit_cod_usuario")).equals(respuestaAlmacenada.substring(0, respuesta.indexOf("audit_cod_usuario")))) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	
	}
	
	/**
	 * Comprueba si la respuesta puede contener un error de diferencia en occurs
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorOccurs528(String respuesta, String respuestaAlmacenada, long responseCode) {
		if (respuestaAlmacenada.startsWith("{\"POSAZ528OperationResponse")) {
			int maxTamanio = respuesta.length();
			int indiceActual = 0;
			int indiceFinActual = 0;
			String cadenaRai = respuesta.replace(" ", "");
			String cadenaMai = respuestaAlmacenada.replace(" ", "");
			String cadenaReferencia = cadenaRai;
			String cadenaReferenciaF = cadenaRai;
			while(indiceActual < maxTamanio) {
//				System.out.println(cadenaReferencia.length());
//				System.out.println(cadenaReferenciaF.length());
				indiceActual += cadenaReferencia.indexOf("datos_idiomas");
				indiceFinActual += cadenaReferenciaF.indexOf("datos_gerentes");
				try {
					if(indiceActual == -1) {
						indiceActual = maxTamanio;
						return false;
					}
					else {
//						System.out.println(cadenaRai.substring(indiceActual, indiceFinActual));
//						System.out.println(cadenaMai.substring(indiceActual, indiceFinActual));
//						System.out.println(cadenaRai);
//						System.out.println(cadenaMai);
						if(!cadenaRai.substring(indiceActual, indiceFinActual).equals(cadenaMai.substring(indiceActual, indiceFinActual))) {
							return true;
						}
					}
						
					cadenaReferencia = cadenaRai.substring(indiceFinActual);
					cadenaReferenciaF = cadenaRai.substring(indiceFinActual+200);

				} catch (Exception e) {
					System.out.println("error");
					return false;
				}
				
			}
		} 
		return false;
	
	}
	
	/**
	 * Comprueba si la respuesta contiene cambio en auditusuario
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorCodif(String respuesta, String respuestaAlmacenada, long responseCode) {
		int indexResp, indexRespAl;
		String strResp, strRespAl;
		
		try {
			if (respuestaAlmacenada.startsWith("{\"POSMZ135OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("nif_prof_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("nif_prof_e")))
						&& respuesta.substring(respuesta.indexOf("cod_delegacion_e"), respuesta.length())
							.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("cod_delegacion_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ565OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("lista_especialidades")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("lista_especialidades")))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ558OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("des_servicio_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("des_servicio_e")))
						&& respuesta.substring(respuesta.indexOf("ind_activo_e"), respuesta.length())
							.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("ind_activo_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ520OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("nombre_tarjeta")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("nombre_tarjeta")))
						&& respuesta.substring(respuesta.indexOf("id_sexo"), respuesta.length())
							.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("id_sexo"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ516OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("desc_subespecialidad_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("desc_subespecialidad_e")))
						&& respuesta.substring(respuesta.indexOf("tipo_subespecialidad_e"), respuesta.length())
							.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("tipo_subespecialidad_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ515OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("lista_subespec")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("lista_subespec")))) {
					while(respuesta.indexOf("des_subespecialidad_s") != -1 && respuestaAlmacenada.indexOf("des_subespecialidad_s") != -1) {
						indexResp = respuesta.indexOf("des_subespecialidad_s");
						strResp = respuesta.substring(indexResp - 1, respuesta.indexOf("tipo_subespecialidad_s"));
						
						indexRespAl = respuestaAlmacenada.indexOf("des_subespecialidad_s");
						strRespAl = respuestaAlmacenada.substring(indexRespAl - 1, respuesta.indexOf("tipo_subespecialidad_s"));
						
						if (!strResp.equals(strRespAl)) {
							return true;
						} else {
							respuesta = respuesta.replaceFirst("des_subespecialidad_s", "");
							respuesta = respuesta.replaceFirst("tipo_subespecialidad_s", "");
							respuestaAlmacenada = respuestaAlmacenada.replaceFirst("des_subespecialidad_s", "");
							respuestaAlmacenada = respuestaAlmacenada.replaceFirst("tipo_subespecialidad_s", "");
						}						
					}
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ514OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("descripcion_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("descripcion_e")))
						&& respuesta.substring(respuesta.indexOf("rgsao514_errores"), respuesta.length())
							.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("rgsao514_errores"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;	
	}
	
	/**
	 * Comprueba si la respuesta contiene cambio en auditusuario
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorCambiarColeccion(String respuesta, String respuestaAlmacenada, long responseCode) {
		int indexResp, indexRespAl;
		String strResp, strRespAl;
		
		try {
			if (respuestaAlmacenada.startsWith("{\"POSAZ626OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("num_reg_totales_s")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("num_reg_totales_s")))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ582OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("cod_operacion_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("cod_operacion_e")))
						&& respuesta.substring(respuesta.indexOf("des_tipo_infra_e"), respuesta.length())
						.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("des_tipo_infra_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ574OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("lista_catalogos")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("lista_catalogos")))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ558OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("cod_operacion_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("cod_operacion_e")))
						&& respuesta.substring(respuesta.indexOf("des_servicio_e"), respuesta.length())
						.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("des_servicio_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ538OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("audit_fec_modificacion")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("audit_fec_modificacion")))
						&& respuesta.substring(respuesta.indexOf("audit_cod_usuario"), respuesta.length())
						.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("audit_cod_usuario"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ533OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("audit_fec_modificacion")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("audit_fec_modificacion")))
						&& respuesta.substring(respuesta.indexOf("audit_cod_usuario"), respuesta.length())
						.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("audit_cod_usuario"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ518OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("lista_redes")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("lista_redes")))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ516OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("cod_subespecialidad_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("cod_subespecialidad_e")))
						&& respuesta.substring(respuesta.indexOf("desc_subespecialidad_e"), respuesta.length())
						.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("desc_subespecialidad_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ515OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("lista_subespec")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("lista_subespec")))) {
					while(respuesta.indexOf("fecha_modificacion_s") != -1 && respuestaAlmacenada.indexOf("fecha_modificacion_s") != -1) {
						indexResp = respuesta.indexOf("fecha_modificacion_s");
						strResp = respuesta.substring(indexResp - 1, indexResp + 35);
						
						indexRespAl = respuestaAlmacenada.indexOf("fecha_modificacion_s");
						strRespAl = respuestaAlmacenada.substring(indexRespAl - 1, indexRespAl + 35);
						
						if (!strResp.equals(strRespAl)) {
							return true;
						} else {
							respuesta = respuesta.replaceFirst("fecha_modificacion_s", "");
							respuestaAlmacenada = respuestaAlmacenada.replaceFirst("fecha_modificacion_s", "");
						}						
					}
				}
			}
			else if (respuestaAlmacenada.startsWith("{\"POSAZ514OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("id_grupo_empresarial_e")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("id_grupo_empresarial_e")))
						&& respuesta.substring(respuesta.indexOf("descripcion_e"), respuesta.length())
						.equals(respuestaAlmacenada.substring(respuestaAlmacenada.indexOf("descripcion_e"), respuestaAlmacenada.length()))) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;	
	}
	
	/**
	 * Comprueba si la respuesta contiene cambio en auditusuario
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorCodRedPrestacional(String respuesta, String respuestaAlmacenada, long responseCode) {
		int indexCodResp, indexCodRespAl;
		String strCodResp, strCodRespAl;
		try {
			if (respuestaAlmacenada.startsWith("{\"POSAZ586OperationResponse")) {
				if(respuesta.substring(0, respuesta.indexOf("lista_clientes")).equals(respuestaAlmacenada.substring(0, respuestaAlmacenada.indexOf("lista_clientes")))) {
					while(respuesta.indexOf("cod_red_prestacional_s") != -1 && respuestaAlmacenada.indexOf("cod_red_prestacional_s") != -1) {
						indexCodResp = respuesta.indexOf("cod_red_prestacional_s");
						strCodResp = respuesta.substring(indexCodResp - 1, indexCodResp + 25);
						
						indexCodRespAl = respuestaAlmacenada.indexOf("cod_red_prestacional_s");
						strCodRespAl = respuestaAlmacenada.substring(indexCodRespAl - 1, indexCodRespAl + 25);
						
						if (!strCodResp.equals(strCodRespAl)) {
							return true;
						} else {
							respuesta = respuesta.replaceFirst("cod_red_prestacional_s", "");
							respuestaAlmacenada = respuestaAlmacenada.replaceFirst("cod_red_prestacional_s", "");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;	
	}
}
		
	