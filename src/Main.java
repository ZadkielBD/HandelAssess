private static final String contraAdmin = "HANDEL_ADMIN123";
private static final String USUARIO = "Usuario";
private static final String ADMINISTRADOR = "Administrador";
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
            opcInicio = convertirInt("Debes ingresar un numero");
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
            opcTipo = convertirInt("Debes de ingresar un numero");
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
                System.out.printf(Color.RESULT+"Hola %s, has accedido como %s \n"+Color.RESET,nombre, tipo); //imprimr que has accedido al sistema
                break;
            }
        }
    }
    while (true) { //bucle para la tasacion
        int opcInicio;
        // ORO
        double PrecioOroGrUSD=1.0;
        while (true) {
            int opcMenu;
            boolean ModoAdmin = false;
            IO.println(Color.MAGENTA_BOLD+"=====Menu de Tasacion====="+Color.RESET);
            if (tipo.equals(ADMINISTRADOR)) {
                opcMenu= convertirInt(Color.GREEN_BOLD+"0)Modo Administrador"+Color.RESET);
                ModoAdmin = true;
            }
            if (ModoAdmin) {
                IO.println(Color.BLUE_BOLD+"1)Actualizar valor de las divisas\n2)Actualizar valor de metales preciosos\n3)Añadir metal precioso\n4)Salir de admin"+Color.RESET);
            } else {
                IO.println(Color.GREEN_BOLD+"1)Calcular Oro\n2)Calcular Plata"+Color.RESET);
                opcInicio = convertirInt("Debes ingresar una opcion valida");
                switch (opcInicio) {
                    case 1:
                        IO.println(Color.BLUE_BOLD+"=====Calcular Oro====="+Color.RESET);
                        break;
                    case 2:
                        IO.println(Color.BLUE_BOLD+"=====Calcular Plata====="+Color.RESET);
                        break;
                    default:
                        continue;
                }
            }
            break;
        }
        break;
    }
}

public int convertirInt(String msg) {
    while (true) {
        try {
            return Integer.parseInt(IO.readln().trim());
        } catch (NumberFormatException e) {
            IO.println(Color.RED_BOLD+msg+Color.RESET);
        }
    }
}

public boolean verificarTexto(String texto, String texto2 , int TamañoMaximo) {
    if (texto.isEmpty() && texto2.isEmpty()) { //validando si algun valor esta vacio
        IO.println(Color.RED_BOLD+"Ningun campo puede ser nulo"+Color.RESET);
        return false;
    }
    if (texto.length() > TamañoMaximo && texto2.length() > TamañoMaximo) {
        IO.println(Color.RED_BOLD+"Excediste el tamaño"+Color.RESET);
        return false;
    }
        return true;
}