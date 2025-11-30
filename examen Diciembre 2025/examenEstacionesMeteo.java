public class examenEstacionesMeteo {

    // Metodo predefinido
    public static int buscar(String estacion, String[] estaciones) {

        for (int i = 0; i < estaciones.length; i++) {
            if (estaciones[i].equals(estacion)) {
                return i;
            }
        }
        return -1;
    }

    // Metodo predefinido
    static int posMastemperatura(int[] valor) {
        int maxTemp = 0;
        for (int i = 1; i < valor.length; i++) {
            if (valor[i] > valor[maxTemp]) {
                maxTemp = i;
            }
        }
        return maxTemp;
    }

    // ============================================================================
    // EJERCICIO 1: obtenerTemperatura
    // ============================================================================
    /**
     * Obtiene la temperatura de una estación específica.
     * 
     * @param estacion     Nombre de la estación a consultar
     * @param estaciones   Array con los nombres de las estaciones
     * @param temperaturas Array de temperaturas
     * @return Temperatura de la estación, o -999 si no existe
     */

    public static int obtenerTemperatura(String estacion, String[] estaciones, int[] temperaturas) {
        int pos = buscar(estacion, estaciones);

        int temperatura = 0;

        if (pos != -1) {
            temperatura = temperaturas[pos];
        } else {
            temperatura = -999;
        }
        return temperatura;
    }

    // ============================================================================
    // EJERCICIO 2: lasActivas
    // ============================================================================
    /**
     * Devuelve un array con las estaciones activas y sus temperaturas.
     * 
     * @param temperaturas Array de temperaturas (-999 = fuera de servicio)
     * @param estaciones   Array con los nombres de las estaciones
     * @return Array de Strings con formato "ESTACION temperatura"
     */

    public static String[] lasEstacionesActivas(int[] temperaturas, String[] estaciones) {
        int count = 0;

        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                count++;
            }
        }

        String[] resultado = new String[count];
        int indice = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                resultado[indice] = estaciones[i] + "" + temperaturas[i];
                indice++;
            }
        }
        return resultado;
    }

    // ============================================================================
    // EJERCICIO 3: actualizar
    // ============================================================================
    /**
     * Actualiza la temperatura de una estación específica.
     * 
     * @param temperaturas Array de temperaturas a modificar
     * @param estaciones   Array con los nombres de las estaciones
     * @param estacion     Nombre de la estación a actualizar
     * @param nuevaTemp    Nueva temperatura a registrar
     * @return true si se actualizó correctamente, false si la estación no existe
     */

    public static boolean actualizar(int[] temperaturas, String[] estaciones, String estacion, int temperatura) {

        int pos = buscar(estacion, estaciones);
        if (pos != -1) {
            temperaturas[pos] += temperatura;
            return true;
        } else {
            return false;
        }
    }

    // ============================================================================
    // EJERCICIO 4: lasActivasOrden
    // ============================================================================
    /**
     * Devuelve las estaciones activas ordenadas por temperatura (decreciente).
     * 
     * @param temperaturas Array de temperaturas (-999 = fuera de servicio)
     * @param estaciones   Array con los nombres de las estaciones
     * @return Array de Strings ordenado de mayor a menor temperatura
     */

    public static String[] lasActivasOrden(int[] temperaturas, String[] estaciones) {

        int count = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                count++;
            }
        }
        int[] temperaturas2 = new int[temperaturas.length];
        for (int i = 0; i < temperaturas.length; i++) {
            temperaturas2[i] = temperaturas[i];

        }
        String[] resultado = new String[count];
        for (int i = 0; i < count; i++) {
            int tempMax = posMastemperatura(temperaturas2);
            resultado[i] = estaciones[tempMax] + " " + temperaturas2[tempMax];
            temperaturas2[tempMax] = -1;
        }
        return resultado;
    }

    // ============================================================================
    // EJERCICIO 5: sinInterferenciasManhattan
    // ============================================================================
    /**
     * Verifica si las estaciones en el mapa tienen suficiente separación
     * usando distancia Manhattan (sin raíces cuadradas).
     * 
     * @param mapa            Cuadrícula con las posiciones de las estaciones
     * @param distanciaMinima Distancia mínima requerida entre estaciones
     * @return true si no hay interferencias, false en caso contrario
     */

    public static boolean sinInterferenciasManhattan(char[][] tablero, int distanciaMinima) {

        int totalSensores = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                char s = tablero[i][j];
                if (s == 'N' || s == 'S' || s == 'C') {
                    totalSensores++;
                }
            }
        }

        int[][] sensores = new int[totalSensores][2];
        int count = 0;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                char s = tablero[i][j];
                if (s == 'N' || s == 'S' || s == 'C') {
                    sensores[count][0] = i;
                    sensores[count][1] = j;
                    count++;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {

                int dx = Math.abs(sensores[i][0] - sensores[j][0]);
                int dy = Math.abs(sensores[i][1] - sensores[j][1]);

                int dist = dx + dy;

                if (dist < distanciaMinima) {
                    return false;
                }
            }
        }
        return true;
    }
}