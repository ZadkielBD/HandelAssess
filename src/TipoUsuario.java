public enum TipoUsuario {
    USUARIO("Usuario"),
    ADMINISTRADOR("Administrador");

    private final String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
