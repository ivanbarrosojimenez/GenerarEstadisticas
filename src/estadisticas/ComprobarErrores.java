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
	 * Comprueba si la respuesta contiene la cadena  RLEL
	 * @param respuesta
	 * @param respuestaAlmacenada
	 * @param codigoRetorno
	 * @return true si contiene la cadena
	 */
	public static boolean hayErrorRlel(String respuesta, String respuestaAlmacenada, long responseCode) {
		return (respuesta.contains("RLEL"));
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
	
	
}
