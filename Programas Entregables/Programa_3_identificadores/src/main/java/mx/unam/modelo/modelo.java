package mx.unam.modelo;

public class modelo{

    public static class ResultadoValidacion {
        public boolean esValido;
        public String motivo;

        public ResultadoValidacion(boolean esValido, String motivo) {
            this.esValido = esValido;
            this.motivo = motivo;
        }
    }

    public ResultadoValidacion validarIdentificador(String cadena) {

        if (cadena.isEmpty())
            return new ResultadoValidacion(false, "Identificador vacío");

        int estado = 1;

        for (int i = 0; i < cadena.length(); i++) {

            char simbolo = cadena.charAt(i);

            switch (estado) {

                case 1:
                    if (Character.isLetter(simbolo)) {
                        estado = 3;
                    } else if (Character.isDigit(simbolo)) {
                        return new ResultadoValidacion(false,
                                "No puede comenzar con número");
                    } else {
                        return new ResultadoValidacion(false,
                                "Debe comenzar con letra");
                    }
                    break;

                case 3:
                    if (Character.isLetter(simbolo) ||
                            Character.isDigit(simbolo) ||
                            simbolo == '_') {
                        estado = 3;
                    } else if (simbolo == ' ') {
                        return new ResultadoValidacion(false,
                                "No puede contener espacios");
                    } else {
                        return new ResultadoValidacion(false,
                                "Carácter inválido: '" + simbolo + "'");
                    }
                    break;
            }
        }

        return new ResultadoValidacion(true, "");
    }
}

