package fes.aragon.ico.leertxt.modelo;

public class Modelo {

    // Autómata que reconoce "aa"
    public boolean tieneAA(String cadena) {

        if (cadena == null || cadena.length() == 0) {
            return false;
        }

        int estado = 0;

        for (int i = 0; i < cadena.length(); i++) {

            char simbolo = cadena.charAt(i);

            switch (estado) {

                case 0:
                    if (simbolo == 'a') {
                        estado = 1;
                    } else {
                        estado = 0;
                    }
                    break;

                case 1:
                    if (simbolo == 'a') {
                        estado = 2; // encontró "aa"
                    } else {
                        estado = 0;
                    }
                    break;

                case 2:
                    estado = 2; // permanece en estado válido
                    break;
            }
        }

        return estado == 2;
    }
}
