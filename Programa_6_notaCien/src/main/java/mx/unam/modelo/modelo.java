package mx.unam.modelo;


public class modelo {
        public static class Resultado {
            public boolean valida;
            public String mensaje;

            public Resultado(boolean valida, String mensaje) {
                this.valida = valida;
                this.mensaje = mensaje;
            }
        }

        public Resultado validar(String cadena) {

            if (cadena == null || cadena.trim().isEmpty()) {
                return new Resultado(false, "Cadena vacía");
            }

            cadena = cadena.trim();

            int estado = 0;

            for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);

                switch (estado) {

                    case 0: // estado inicial
                        if (c == '+' || c == '-') {
                            estado = 7;
                        } else if (Character.isDigit(c)) {
                            estado = 1;
                        } else if (c == '.') {
                            estado = 8;
                        } else {
                            return error(i, "Se esperaba dígito, signo o punto");
                        }
                        break;

                    case 7: // signo inicio
                        if (Character.isDigit(c)) {
                            estado = 1;
                        } else if (c == '.') {
                            estado = 8;
                        } else {
                            return error(i, "Se esperaba dígito o punto después del signo");
                        }
                        break;

                    case 1:
                        if (Character.isDigit(c)) {
                            estado = 1;
                        } else if (c == '.') {
                            estado = 2;
                        } else if (c == 'e' || c == 'E') {
                            estado = 4;
                        } else {
                            return error(i, "Carácter inválido en entero");
                        }
                        break;

                    case 2:
                        if (Character.isDigit(c)) {
                            estado = 3;
                        } else {
                            return error(i, "Se esperaba dígito después del punto");
                        }
                        break;

                    case 8:
                        if (Character.isDigit(c)) {
                            estado = 3;
                        } else {
                            return error(i, "Se esperaba dígito después del punto");
                        }
                        break;

                    case 3:
                        if (Character.isDigit(c)) {
                            estado = 3;
                        } else if (c == 'e' || c == 'E') {
                            estado = 4;
                        } else {
                            return error(i, "Carácter inválido en decimal");
                        }
                        break;

                    case 4:
                        if (c == '+' || c == '-') {
                            estado = 5;
                        } else if (Character.isDigit(c)) {
                            estado = 6;
                        } else {
                            return error(i, "Se esperaba signo o dígito en exponente");
                        }
                        break;

                    case 5:
                        if (Character.isDigit(c)) {
                            estado = 6;
                        } else {
                            return error(i, "Se esperaba dígito después del signo en exponente");
                        }
                        break;

                    case 6:
                        if (Character.isDigit(c)) {
                            estado = 6;
                        } else {
                            return error(i, "Carácter inválido en exponente");
                        }
                        break;
                }
            }

            switch (estado) {
                case 1:
                    return new Resultado(true, "Entero válido");
                case 3:
                    return new Resultado(true, "Decimal válido");
                case 6:
                    return new Resultado(true, "Notación científica válida");
                case 7:
                    return new Resultado(false, "Número incompleto después del signo");
                case 2:
                case 8:
                    return new Resultado(false, "Número incompleto después del punto");
                case 4:
                case 5:
                    return new Resultado(false, "Exponente incompleto");
            }

            return new Resultado(false, "Expresión inválida");
        }

        private Resultado error(int posicion, String mensaje) {
            return new Resultado(false,
                    "Error en posición " + posicion + ": " + mensaje);
        }


}
