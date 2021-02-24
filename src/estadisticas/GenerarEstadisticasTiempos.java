package estadisticas;

import java.io.FileReader;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GenerarEstadisticasTiempos {

    final Date date = new Date();
    final String fechaActual = date.getDay() + "" + date.getMonth() + "_" + date.getYear() + " "
            + date.getHours() + ":" + date.getYear();

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
                    String linea = nombre + ";" + tiemposSql.get(z) + ";" + tiemposHost.get(z) + ";"
                            + responseCode;
                    System.out.println(linea);
                    sfRespuesta.append(linea);
                    sfRespuesta.append("\r\n");
                }
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            return sfRespuesta;
        }
    }
}
