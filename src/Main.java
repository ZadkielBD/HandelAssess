private static final String contraAdmin = "HANDEL_ADMIN123";
private static final String USUARIO = "Usuario";
private static final String ADMINISTRADOR = "Administrador";
private static double peso;
private static int pureza;
private static String simbolo;
void main() {
    // #####LOGIN#####
    String tipo;
    String nombre; // Inicializar nombre
    String contraseña; // Inicializar contraseña

    // #####TASACION#####

    Usuario.VerificarArchivo();

    while (true) { //Bucle para el modulo de login
        int opcInicio; //opcion para el inicio de sesion o crear cuenta
        int opcTipo; // opcion para el tipo de cuenta
        while (true){ //Bucle para ingresar si se quiere Iniciar sesion o crear una cuenta
            IO.println(Color.MAGENTA_BOLD+"=====Iniciar sesión o crear cuenta====="+Color.RESET);
            IO.println(Color.GREEN_BOLD+"1)Crear cuenta\n2)Iniciar sesión"+Color.RESET);
            opcInicio = convertirInt("","Debes ingresar un numero");
            switch (opcInicio) {
                    case 1:
                        IO.println(Color.BLUE_BOLD+"=====Creando Cuenta====="+Color.RESET);
                        break;
                    case 2:
                        IO.println(Color.BLUE_BOLD+"=====Iniciando Sesion====="+Color.RESET);
                        break;
                    default:
                        IO.println(Color.RED_BOLD+"Ingresa 1 o 2"+Color.RESET);
                        continue;
                }
                break;
        }
        while (true){ //Bucle para el ingreso de los datos
            IO.println(Color.MAGENTA_BOLD+"Tipos de cuentas:"+Color.RESET);
            IO.println(Color.GREEN_BOLD+"1)Usuario\n2)Administrador"+Color.RESET);
            opcTipo = convertirInt("","Debes de ingresar un numero");
            switch (opcTipo) {
                    case 1:
                        tipo = USUARIO;
                        break;
                    case 2:
                        String contraAdminIngresado = IO.readln("Ingresa la contraseña del administrador: ");
                        if (contraAdminIngresado.equals(contraAdmin)) {
                            tipo = ADMINISTRADOR;
                            break;
                        } else {
                            IO.println("Contraseña del administrador incorrecto");
                            continue;
                        }
                    default:
                        IO.println(Color.RED_BOLD+"Ingresa 1 o 2"+Color.RESET);
                        continue;
                }
                break;
        }
        do {
            nombre = IO.readln("Usuario(con un tamaño menor a 20 caracteres): ").trim();
            contraseña = IO.readln("Contraseña(con un tamaño menor a 20 caracteres): ").trim();
        } while (!verificarTexto(nombre, contraseña, 20));
        if (opcInicio == 1) { // Si la opcion es 1 crea una cuenta
            if (!Usuario.verificarExistente(nombre, contraseña, tipo)) {
                Usuario.crearCuenta(nombre, contraseña, tipo); //Insertar los valores de nombre contraseña, tipo al modulo crear cuenta
            }
        } else { // Si la opcion es 2 inicia sesion
            if (Usuario.iniciarSesion(nombre, contraseña, tipo)) { // Insertar y verificar si el metodo iniciar sesion es verdadero
                System.out.printf(Color.RESULT+"Hola %s, has accedido como un %s \n"+Color.RESET,nombre, tipo); //imprimr que has accedido al sistema
                break;
            }
        }
    }
    while (true) { //bucle para la tasacion
        int opcInicio;
        boolean salir= false;
        while (true) {
            int opcMenu;

            boolean ModoAdmin = false;
            IO.println(Color.MAGENTA_BOLD+"=====Menu de Tasacion====="+Color.RESET);
//            if (tipo.equals(ADMINISTRADOR)) {
//                opcMenu= convertirInt(Color.GREEN_BOLD+"0)Modo Administrador"+Color.RESET);
//                ModoAdmin = true;
//            }
            if (ModoAdmin) {
                IO.println(Color.BLUE_BOLD+"1)Actualizar valor de las divisas\n2)Actualizar valor de metales preciosos\n3)Añadir metal precioso\n4)Salir de admin"+Color.RESET);
            } else {
                IO.println(Color.GREEN_BOLD+"1)Calcular Oro\n2)Calcular Plata\n3)Salir"+Color.RESET);
                opcInicio = convertirInt("","Debes ingresar una opcion valida");
                switch (opcInicio) {
                    case 1:
                        IO.println(Color.BLUE_BOLD+"=====Calcular Oro====="+Color.RESET);
                        pedirDatosOro();
                        break;
                    case 2:
                        IO.println(Color.BLUE_BOLD+"=====Calcular Plata====="+Color.RESET);
                        pedirDatosPlata();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        IO.println(Color.RED_BOLD + "Opcion invalida"+ Color.RESET);
                        continue;
                }
            }
            break;
        }
        if (salir) {
            IO.println(Color.MAGENTA_BOLD + "*****Saliendo del programa*****"+ Color.RESET);
            break;
        }
    }
}

public int convertirInt(String prompt,String msg) {
    while (true) {
        try {
            return Integer.parseInt(IO.readln(prompt).trim());
        } catch (NumberFormatException e) {
            IO.println(Color.RED_BOLD+msg+Color.RESET);
        }
    }
}

public double convertirDouble(String prompt,String msgError) {
    while (true) {
        try {
            return Double.parseDouble(IO.readln(prompt).trim());
        } catch (NumberFormatException e) {
            IO.println(Color.RED_BOLD+msgError+Color.RESET);
        }
    }
}

public boolean verificarTexto(String texto, String texto2 , int TamañoMaximo) {
    if (texto.length() > TamañoMaximo || texto2.length() > TamañoMaximo) {
        IO.println(Color.RED_BOLD+"Excediste el tamaño"+Color.RESET);
        return false;
    } else if (texto.isEmpty() && texto2.isEmpty()) {
        IO.println(Color.RED_BOLD+"Ningun campo puede ser nulo"+Color.RESET);
        return false;
    } else {
        return true;
    }
}

public void pedirDatosOro() {
    double precioOroOztUSD = 149.84;
    double purezaTotal;
    while (true) {
        peso = convertirDouble("Cuanto pesa tu pieza de oro (gramos): ","Debes ingresar un numero");
        pureza = convertirInt("Cuantos kilates tiene tu pieza de oro (desde 8k hasta 24k): ","Ingresa un numero");
        if ((pureza > 8 && pureza <=24) && peso > 0) {
            purezaTotal = (double) pureza/24;
            break;
        } else {
            IO.println("No puede ser menor a 0 o mayor a 24");
        }
    }
    double resultado = peso*purezaTotal* precioOroOztUSD;
    resultado = conversionDivisa(resultado);
    System.out.printf(Color.RESULT+"El precio de tu pieza de oro es de : %s%,.2f\n"+Color.RESET, simbolo, resultado);
}

public void pedirDatosPlata() {
    double precioPlataUSD = 3;
    double purezaTotal = 0;
    peso = convertirDouble("Cuenta pesa tu pieza de plata (gramos): ","Debes ingresar un numero");
    while (true) {
        try {
            Tasacion.LeyPlata[] leyes = Tasacion.LeyPlata.values();
            int i = 0;
            IO.println(Color.MAGENTA_BOLD+"=====Leyes Plata====="+Color.RESET);
            for (Tasacion.LeyPlata ley : Tasacion.LeyPlata.values()) {
                i++;
                System.out.printf(Color.GREEN_BOLD+"%d)%s - %s\n"+Color.RESET, i, ley.name(), ley.getDescripcion());
            }
            int opcPureza = convertirInt("","Debes ingresar un numero");

            if (opcPureza < 1 || opcPureza > leyes.length) {
                throw new IllegalArgumentException("Opción inválida");
            }

            Tasacion.LeyPlata leySeleccionada = leyes[opcPureza - 1];

            purezaTotal = leySeleccionada.getUnidad();
            break;
        } catch (IllegalArgumentException e) {
            IO.println(Color.RED_BOLD+e.getMessage()+Color.RESET);
        }
    }
    double resultado = peso*purezaTotal*precioPlataUSD;
    resultado = conversionDivisa(resultado);
    System.out.printf(Color.RESULT+"El precio de tu pieza de plata es de: %s%,.2f\n"+Color.RESET,simbolo, resultado);
}

public double conversionDivisa(double resultado) {
    int opcDivisa;
    double resultadoFinal = 0;
    while (true) {
        try {
            Tasacion.Divisa[] divisas = Tasacion.Divisa.values();
            IO.println(Color.MAGENTA_BOLD + "=====Menu Divisas=====" + Color.RESET);
            int i = 0;
            for (Tasacion.Divisa divisa : Tasacion.Divisa.values()) {
                i++;
                System.out.printf(Color.GREEN_BOLD + "%d)%s\n" + Color.RESET, i, divisa.name());
            }
            opcDivisa = convertirInt("", "Debes ingresar un numero");

            if (opcDivisa < 1 || opcDivisa > divisas.length) {
                throw new IllegalArgumentException("Opción inválida");
            }

            Tasacion.Divisa divisaSeleccionada = divisas[opcDivisa - 1];
            resultadoFinal = divisaSeleccionada.getTipoCambio() * resultado;
            simbolo = divisaSeleccionada.getSimbolo();
            break;
        } catch (IllegalArgumentException e) {
            IO.println(Color.RED_BOLD + e.getMessage() + Color.RESET);
        }
    }
    return resultadoFinal;
}
