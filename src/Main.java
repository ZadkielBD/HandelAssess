void main() {
    int opcInicio = 0; //opcion para el inicio de sesion o crear cuenta
    int opcTipo; // opcion para el tipo de cuenta
    String opcionIngresada; // sirve para obtener la opcion ingresada en un string y asignarlo a una opcion tambien para validar en el try catch
    String tipo = ""; // Inicializar tipo
    String nombre; // Inicializar nombre
    String contraseña; // Inicializar contraseña
    boolean correcto = false; // Inicializar correcto
    boolean acceso = false; // Inicializar acceso
    String contraAdmin = "HANDEL_ADMIN123";
    int intentos=0;

    Usuario.verificarArchivo();

    while (!acceso) { //Bucle para el modulo de login
        do { //Bucle para ingresar si se quiere Iniciar sesion o crear una cuenta
            IO.println(Color.MAGENTA_BOLD+"=====Iniciar sesión o crear cuenta====="+Color.RESET);
            IO.println(Color.GREEN_BOLD+"1)Crear cuenta\n2)Iniciar sesión"+Color.RESET);
            opcionIngresada = IO.readln().trim();
            //try catch para validar si la opcion ingresada si sea un numero
            try {
                opcInicio = Integer.parseInt(opcionIngresada); //convirtiendo el string(opcionIngresada) a numero y asignandoselo a opcInicio
                switch (opcInicio) {
                    case 1:
                        IO.println(Color.BLUE_BOLD+"=====Creando Cuenta====="+Color.RESET);
                        correcto = true;
                        break;
                    case 2:
                        IO.println(Color.BLUE_BOLD+"=====Iniciando Sesion====="+Color.RESET);
                        correcto = true;
                        break;
                    default:
                        IO.println(Color.RED_BOLD+"Debes de ingresar 1 o 2"+Color.RESET);
                        break;
                }
            } catch (NumberFormatException e) { //atrapa la excepcion
                IO.println(Color.RED_BOLD+"Debes de ingresar un numero"+Color.RESET);
            }
        } while(!correcto);
        do { //Bucle para el ingreso de los datos
            IO.println(Color.MAGENTA_BOLD+"Tipos de cuentas:"+Color.RESET);
            IO.println(Color.GREEN_BOLD+"1)Usuario\n2)Administrador"+Color.RESET);
            opcionIngresada = IO.readln().trim();
            //try catch para validar si la opcion ingresada si sea un numero
            try {
                opcTipo = Integer.parseInt(opcionIngresada); // convirtiendo a numero opcionIngresada y asignandole a opcTipo
                switch (opcTipo) {
                    case 1:
                        tipo = "Usuario";
                        correcto = true;
                        break;
                    case 2:
                        if (opcInicio==1) {

                            String contraAdminIngresado = IO.readln("Ingresa la contraseña del administrador: ");
                            if (contraAdminIngresado.equalsIgnoreCase(contraAdmin)) {
                                tipo = "Administrador";
                                correcto = true;
                            } else {

                                IO.println("Contraseña del administrador incorrecto");
                                correcto = false;
                            }
                        } else if (opcInicio==2) {
                            tipo = "Administrador";
                            correcto = true;
                        }
                        break;
                    default:
                        tipo = "Error";
                        IO.println(Color.RED_BOLD+"Opcion Invalida"+Color.RESET);
                        correcto=false;
                        break;
                }
            }
            catch (NumberFormatException e) { //atrapa la excepcion
                IO.println(Color.RED_BOLD+"Debes de ingresar un numero"+Color.RESET);
                correcto = false;
            }
        } while (!correcto);
        do {
            nombre = IO.readln("Usuario(con un tamaño menor a 20 caracteres): ").trim();
            if (nombre.length()<=20) {
                correcto = true;
            } else {
                IO.println("Excediste el tamaño");
                correcto = false;
            }
            contraseña = IO.readln("Contraseña(con un tamaño menor a 20 caracteres): ").trim();
            if (contraseña.length()<=20) {
                correcto=true;
            } else {
                IO.println("Excediste el tamaño");
                correcto = false;
            }
            if (nombre.isEmpty() || contraseña.isEmpty()) { //validando si algun valor esta vacio
                correcto = false;
                IO.println(Color.RED_BOLD+"Ningun campo puede ser nulo"+Color.RESET);
            }
        } while (!correcto);
        if (opcInicio == 1) { // Si la opcion es 1 crea una cuenta
            boolean verificarExistente = Usuario.verificarExistente(nombre, contraseña, tipo);
            if (!verificarExistente) {
                Usuario.crearCuenta(nombre, contraseña, tipo); //Insertar los valores de nombre contraseña, tipo al modulo crear cuenta
            }
        } else if (opcInicio == 2) { // Si la opcion es 2 inicia sesion
            Usuario.cargarUsuarios(); //llama al metodo cargar usuarios
            boolean inicioSesion = Usuario.iniciarSesion(nombre, contraseña, tipo); //Insertar los valores de nombre contraseña, tipo al modulo iniciar sesion
            if (inicioSesion) { // verifica si el metodo iniciar sesion es verdadero
                String id = UUID.randomUUID().toString();
                System.out.printf("\033[0;36;100mHola %s, has accedido como %s "+Color.RESET,nombre, tipo); //imprimr que has accedido al sistema
                acceso = true;
            }
        }
    }
}



