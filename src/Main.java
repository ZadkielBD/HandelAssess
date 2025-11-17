import java.util.ArrayList;

public static final String RESET = "\u001B[0m";
public static final String ROJO = "\u001B[31m";
public static final String VERDE = "\u001B[32m";
public static final String AMARILLO = "\u001B[33m";
public static final String AZUL = "\u001B[34m";
public static final String MORADO = "\u001B[35m";
public static final String CIAN = "\u001B[36m";
public static final String BLANCO = "\u001B[37m";
private static final String FILE = "Usuarios.txt";
private static List<Usuario> listaUsuarios = new ArrayList<Usuario>();

void main() {
    int opc = 0;
    String opcionIngresada;
    String tipo = "";
    String nombre;
    String contrasena;
    boolean correcto = true;
    cargarUsuarios();

    do {
        IO.println("Iniciar sesión o crear cuenta");
        IO.println("1)Crear cuenta");
        IO.println("2)Iniciar sesión");
        opcionIngresada = IO.readln();
        try {
            opc = Integer.parseInt(opcionIngresada);
            if (opc <= 0 || opc > 2) {
                correcto = false;
                IO.println(ROJO+"Debes de ingresar 1 o 2"+RESET);
            } else {
                correcto = true;
            }
        } catch (NumberFormatException e) {
            IO.println(ROJO+"Debes de ingresar un numero"+RESET);
            correcto = false;
        }
    } while (!correcto);

    do {
        nombre = IO.readln("Nombre: ").trim().toLowerCase();
        contrasena = IO.readln("Contraseña: ").trim().toLowerCase();
        IO.println("Tipos de cuentas:");
        IO.println("1)Usuario");
        IO.println("2)Administrador");
        opcionIngresada = IO.readln();
        try {
            opc = Integer.parseInt(opcionIngresada);
            if (opc == 1) {
                tipo = "Usuario";
            } else if (opc == 2) {
                tipo = "Administrador";
            } else {
                IO.println(ROJO+"Opcion Invalida"+RESET);
            }
            if (!(nombre.isEmpty() || contrasena.isEmpty() || tipo.isEmpty())) {
            } else {
                correcto = false;
                IO.println(ROJO+"Ningun campo puede ser nulo"+RESET);
                IO.println("");
            }
        } catch (NumberFormatException e) {
            IO.println(ROJO+"Debes de ingresar un numero"+RESET);
        }
    } while (!correcto);

    if (opc == 1) {
        crearCuenta(nombre,contrasena,tipo);
    } else if (opc == 2) {
        iniciarSesion(nombre,contrasena,tipo);
    } else if (opc>0 || opc<0) {
        IO.println(ROJO+"Opcion Invalida"+RESET);
    }

    for (Usuario usuario: listaUsuarios) {
        IO.println("Nombre: " + usuario.getNombre()+ " Contrasena: "+usuario.getContrasena() + " Tipo de cuenta: " + usuario.isTipo());
    }

}

public static void cargarUsuarios() {
    listaUsuarios.clear();
    try {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length == 3) {
                Usuario u = new Usuario(partes[0], partes[1], partes[2]);
                listaUsuarios.add(u); // usuario[0] = nombre, usuario[1] = contraseña
            }
        }
    } catch (IOException e) {
         IO.println(ROJO+"Archivo no encontrado, Se creara uno"+RESET);
    }
}

static void crearCuenta(String nombre, String contrasena, String tipo) {
    try(FileWriter fw = new FileWriter(FILE, true)) {
        fw.write(nombre + "," + contrasena + ","+ tipo + "\n");
        IO.println(AZUL+"Cuenta creada correctamente"+RESET);
        fw.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

static boolean iniciarSesion(String nombre, String contrasena, String tipo) {
    for (Usuario i: listaUsuarios) {
        if (i.getNombre().equals(nombre)&&i.getContrasena().equals(contrasena)&&tipo.equals(tipo)){
            IO.println(AZUL+"Iniciando sesion"+RESET);
            return true;
        } else {
            IO.println(ROJO+"Usuario no encontrado"+RESET);
        }
    }
    return false;
}