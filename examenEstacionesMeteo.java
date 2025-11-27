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

    // Ejercicio 1
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

    // Ejercicio 2
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

    // Ejercicio 3
    public static boolean actualizar(int[] temperaturas, String[] estaciones, String estacion, int temperatura) {

        int pos = buscar(estacion, estaciones);
        if (pos != -1) {
            temperaturas[pos] += temperatura;
            return true;
        } else {
            return false;
        }
    }

    // Ejercicio 4
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

    // Ejercicio 5
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