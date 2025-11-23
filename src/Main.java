public static final String RESET = "\u001B[0m";
public static final String ROJO = "\u001B[31m";
public static final String VERDE = "\u001B[32m";
public static final String AMARILLO = "\u001B[33m";
public static final String AZUL = "\u001B[34m";
public static final String MORADO = "\u001B[35m";
public static final String CIAN = "\u001B[36m";
public static final String BLANCO = "\u001B[37m";

void main() {
    int opcInicio = 0; //opcion para el inicio de sesion o crear cuenta
    int opcTipo; // opcion para el tipo de cuenta
    String opcionIngresada; // sirve para obtener la opcion ingresada en un string y asignarlo a una opcion tambien para validar en el try catch
    String tipo = ""; // Inicializar tipo
    String nombre;  // Inicializar nombre
    String contrasena; // Inicializar contraseña
    boolean correcto; // Inicializar correcto
    boolean acceso = false; // Inicializar acceso


    do { //Bucle para el modulo de login
        do { //Bucle para ingresar si se quiere Iniciar sesion o crear una cuenta
            IO.println(MORADO+"Iniciar sesión o crear cuenta"+RESET);
            IO.println("1)Crear cuenta");
            IO.println("2)Iniciar sesión");
            opcionIngresada = IO.readln();
            //try catch para validar si la opcion ingresada si sea un numero
            try {
                opcInicio = Integer.parseInt(opcionIngresada); //convirtiendo el string(opcionIngresada) a numero y asignandoselo a opcInicio
                if (opcInicio <= 0 || opcInicio > 2) { //validando si la opcion es valida
                    correcto = false;
                    IO.println(ROJO + "Debes de ingresar 1 o 2" + RESET);
                } else {
                    correcto = true;
                }
            } catch (NumberFormatException e) { //atrapa la excepcion
                IO.println(ROJO + "Debes de ingresar un numero" + RESET);
                correcto = false;
            }
        } while (!correcto);
        do { //Bucle para el ingreso de los datos
            nombre = IO.readln("Nombre: ").trim().toLowerCase();
            contrasena = IO.readln("Contraseña: ").trim();
            IO.println(MORADO + "Tipos de cuentas:" + RESET);
            IO.println("1)Usuario");
            IO.println("2)Administrador");
            opcionIngresada = IO.readln();
            //try catch para validar si la opcion ingresada si sea un numero
            try {
                opcTipo = Integer.parseInt(opcionIngresada); // convirtiendo a numero opcionIngresada y asignandole a opcTipo
                if (opcTipo == 1) {
                    tipo = "Usuario";
                    correcto = true;
                } else if (opcTipo == 2) {
                    tipo = "Administrador";
                    correcto = true;
                } else {
                    IO.println(ROJO + "Opcion Invalida" + RESET);
                }
                if (nombre.isEmpty() || contrasena.isEmpty() || tipo.isEmpty()) { //validando si algun valor esta vacio
                    correcto = false;
                    IO.println(ROJO + "Ningun campo puede ser nulo" + RESET);
                    IO.println("");
                }
            } catch (NumberFormatException e) { //atrapa la excepcion
                IO.println(ROJO + "Debes de ingresar un numero" + RESET);
            }
        } while (!correcto);
        if (opcInicio == 1) { // Si la opcion es 1 crea una cuenta
            Usuario.crearCuenta(nombre, contrasena, tipo); //Insertar los valores de nombre contraseña, tipo al modulo crear cuenta
        } else { // Si la opcion es 2 inicia sesion
            Usuario.cargarUsuarios(); //llama al metodo cargar usuarios
            boolean inicioSesion = Usuario.iniciarSesion(nombre, contrasena, tipo); //Insertar los valores de nombre contraseña, tipo al modulo iniciar sesion
            if (inicioSesion) { // verifica si el metodo iniciar sesion es verdadero
                System.out.printf("hola %s, has accedido como %s\n",nombre, tipo);
                acceso = true;
            }
        }
    } while (!acceso);
}


