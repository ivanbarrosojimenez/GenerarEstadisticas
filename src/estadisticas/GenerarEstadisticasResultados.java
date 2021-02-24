package estadisticas;

import java.io.FileReader;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GenerarEstadisticasResultados {

    final Date date = new Date();
    final String fechaActual = date.getDay() + "" + date.getMonth() + "_" + date.getYear() + " "
            + date.getHours() + ":" + date.getYear();

    public StringBuffer obtenerSalidaPostman(String f1, boolean generaCabecera) {
        StringBuffer sfRespuesta = new StringBuffer();
        JSONParser parser = new JSONParser();
        try {
            // Pares de ficheros, poner de dos en dos
            Object obj1 = parser.parse(new FileReader(f1));

            JSONObject jsonObject1 = (JSONObject) obj1;

            JSONArray arrayDb2 = (JSONArray) jsonObject1.get("results");

            // Cabeceras
            String cabecera =
                    "Prueba;Transaccion;status code;Mismo tipo error;Mismo status code; respuesta es un json; Respuesta el la misma que en host;Resultado global;TipoError";
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
                            errorDetectado = obtenerTipoDeError(respuesta, respuestaAlmacenada,
                                    responseCode);
                            if (errorDetectado.startsWith("Sin error")) {
                                clave = true;
                                errorDetectado = "";
                            }
                        }
                        catch (Exception e) {

                        }
                    }
                    resultadoGlobal = resultadoGlobal && clave;

                    linea += ";" + clave;
                }

                linea += ";" + resultadoGlobal + ";" + errorDetectado;
                errorDetectado = "";

                System.out.println(linea);
                sfRespuesta.append(linea);
                sfRespuesta.append("\r\n");
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            return sfRespuesta;
        }
    }

    public StringBuffer obtenerSalidaNewMan(String f1, boolean generaCabecera) {
        StringBuffer sfRespuesta = new StringBuffer();
        JSONParser parser = new JSONParser();
        try {
            // Pares de ficheros, poner de dos en dos
            Object obj1 = parser.parse(new FileReader(f1));

            JSONObject jsonObject1 = (JSONObject) obj1;

            JSONObject run = (JSONObject) jsonObject1.get("run");
            JSONArray executions = (JSONArray) run.get("executions");
            // JSONObject item = (JSONObject) executions.get(0);
            // JSONArray assertions = (JSONArray) item.get("assertions");
            System.out.println("nº de assertions:" + executions.size());
            // Cabeceras
            String cabecera =
                    "Prueba;Transaccion;status code;mismo status code;es json;Respuesta==mainframe;mismo tipos error;Resultado global;clasificación error; Id Board";
            if (generaCabecera) {
                sfRespuesta.append(cabecera);
                sfRespuesta.append("\r\n");
            }

            for (int i = 0; i < executions.size(); i++) {
                JSONObject execution = (JSONObject) executions.get(i);
                JSONObject item = (JSONObject) execution.get("item");
                String nombre = (String) item.get("name");

                String transaccion = obtenerTransaccion(nombre);

                JSONObject response = (JSONObject) execution.get("response");
                Long responseCode = (Long) response.get("code");

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
                                    partesLlamadas =
                                            valor.split("b613ab3bd4de8c5ddf771161c4e27f3a");
                                    respuesta = partesLlamadas[1];
                                    respuestaAlmacenada = partesLlamadas[2];
                                    errorDetectado = obtenerTipoDeError(respuesta,
                                            respuestaAlmacenada, responseCode);
                                    if (errorDetectado.startsWith("Sin error")) {
                                        errorDetectado = "";
                                        resultadoGlobal = true;
                                    }
                                }
                                catch (Exception e) {

                                }
                            }

                        }
                    }
                    linea += ";" + resultadoPrueba;
                }
                linea += ";" + resultadoGlobal + ";" + errorDetectado;
                errorDetectado = "";

                System.out.println(linea);
                sfRespuesta.append(linea);
                sfRespuesta.append("\r\n");

            }
        }
        catch (

        Exception e) {
            System.out.println(e);
        }
        finally {
            return sfRespuesta;
        }
    }

    private String obtenerTipoDeError(String respuesta, String respuestaAlmacenada,
            Long responseCode) {
        /// Identificar el tipo de error según el retorno de las respuestas
        if (responseCode.equals(504l)) {
            return "error 504;25935";
        }
        if (respuesta.startsWith("{\"POSAZ526OperationResponse")) {
            return "Copy modificada por mainframe";
        }

        if (respuesta.replaceAll(" ", "").equals(respuestaAlmacenada.replaceAll(" ", ""))) {
            return "TRIM EN VALOR;25497";
        }
        if (respuesta.contains("OUTPUT_OVERFLOW")) {
            return "OUTPUT_OVERFLOW;25933";
        }
        if (respuesta.contains("PGMIDERR")) {
            return "PGMIDERR;25561";
        }
        if (respuesta.contains("System.Int32")) {
            return "System.Int32;25489";
        }
        if (respuesta.contains("UNDEFINED_ELEMENT")) {
            return "UNDEFINED_ELEMENT;26151";
        }
        if (respuesta.contains("") || respuesta.contains("\\u0000")
                || respuesta.contains("\\u0007") || respuesta.contains("\\u0017")) {
            return "CARACTER-DESBORDAMIENTO;25493";
        }
        if (respuesta.contains("dfhcommarea")) {
            return "dfhcommarea;25491";
        }

        if (respuesta.contains(" -104")) {
            return "SQL ERROR -104;25127";
        }
        if (respuesta.contains(" -99999")) {
            return "SQL ERROR -99999;25177";
        }
        if (respuesta.startsWith("{\"POSAZ528OperationResponse")) {
            System.out.println();
        }
        if (respuesta.contains(" -969")) {
            return "SQL ERROR -969;25897";
        }

        if (respuestaAlmacenada.contains("\\/")) {
            return "error backslash;25490";
        }
        // Parche para error 593 (llega a en lugar de rgsao593)
        if (respuesta.replaceAll("\"a\"", "\"rgsao593e\"").equals(respuestaAlmacenada)) {
            return "Error redefines copy;25494";
        }
        if (respuestaAlmacenada.contains("RLEL")) {
            return "RLEL en respuesta host, repetir prueba";
        }

        if (respuesta.contains("Could not load module")) {
            return "Could not load module;26119";
        }
        if (respuesta.contains("current state is Closed")) {
            return "ExecuteNonQuery;26240";
        }
        if (respuesta.contains("Range error")) {
            return "ERange error;25493";
        }

        // Parche para validar la parte dinamica en respuesta del 631
        if (respuesta.startsWith("{\"POSAZ631OperationResponse")) {
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
            //
            return "Sin error, diferencia en salida fechas";
        }

        if (respuesta.contains("Communication link failure")) {
            return "Communication link failure;26030";
        }

        System.out.println(respuesta);
        System.out.println(respuestaAlmacenada);
        System.out.println();
        return "Error sin detectar";
        // TODO PARCHE FECHA PROCESO PARA 631 fec_ult_proceso_s
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
