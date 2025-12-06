//Codigos ANSI color del texto
public static final String RESET = "\u001B[0m";
public static final String ROJO = "\u001B[31m";
public static final String VERDE = "\u001B[32m";
public static final String AMARILLO = "\u001B[33m";
public static final String AZUL = "\u001B[34m";
public static final String MORADO = "\u001B[35m";
public static final String CIAN = "\u001B[36m";
public static final String NEGRO = "\u001B[30m";

//Codigos ANSI color del fondo
public static final String NEGRO_FONDO = "\u001B[40m";
public static final String ROJO_FONDO = "\u001B[41m";
public static final String VERDE_FONDO = "\u001B[42m";
public static final String AMARILLO_FONDO = "\u001B[43m";
public static final String AZUL_FONDO = "\u001B[44m";
public static final String MORADO_FONDO = "\u001B[45m";
public static final String CIAN_FONDO = "\u001B[46m";
public static final String BLANCO_FONDO = "\u001B[47m";


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

    Usuario.verificarArchivo();

    while (!acceso) { //Bucle para el modulo de login
        do { //Bucle para ingresar si se quiere Iniciar sesion o crear una cuenta
            IO.println(MORADO+"=====Iniciar sesión o crear cuenta====="+RESET);
            IO.println(VERDE+"1)Crear cuenta\n2)Iniciar sesión"+RESET);
            opcionIngresada = IO.readln().trim();
            //try catch para validar si la opcion ingresada si sea un numero
            try {
                opcInicio = Integer.parseInt(opcionIngresada); //convirtiendo el string(opcionIngresada) a numero y asignandoselo a opcInicio
                switch (opcInicio) {
                    case 1:
                        IO.println(AZUL+"=====Creando Cuenta====="+RESET);
                        correcto = true;
                        break;
                    case 2:
                        IO.println(AZUL+"=====Iniciando Sesion====="+RESET);
                        correcto = true;
                        break;
                    default:
                        IO.println(ROJO+"Debes de ingresar 1 o 2"+RESET);
                        break;
                }
            } catch (NumberFormatException e) { //atrapa la excepcion
                IO.println(ROJO+"Debes de ingresar un numero"+RESET);
            }
        } while(!correcto);
        do { //Bucle para el ingreso de los datos
            IO.println(MORADO+"Tipos de cuentas:"+RESET);
            IO.println(VERDE+"1)Usuario\n2)Administrador"+RESET);
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
                        IO.println(ROJO+"Opcion Invalida"+RESET);
                        correcto=false;
                        break;
                }
            }
            catch (NumberFormatException e) { //atrapa la excepcion
                IO.println(ROJO+"Debes de ingresar un numero"+RESET);
                correcto = false;
            }
        } while (!correcto);
        do {
            nombre = IO.readln("Nombre: ").trim();
            contraseña = IO.readln("Contraseña: ").trim();
            if (nombre.isEmpty() || contraseña.isEmpty()) { //validando si algun valor esta vacio
                correcto = false;
                IO.println(ROJO+"Ningun campo puede ser nulo"+RESET);
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
                System.out.printf(AZUL_FONDO+" Hola %s, has accedido como %s "+RESET+RESET,nombre, tipo);
                acceso = true;
            }
        }
    }
}



