public class Tasacion {
    public enum Divisa {
        USD("Dolar Estadounidense", "$", 1.0),
        MXN("Peso Mexicano", "$",  17.59284),
        EUR("Euro", "€", 0.859095),
        GBP("Libra Esterlina","£",0.745281),
        CNY("Yuan Renminbi Chino","¥",6.961037),
        JPY("Yen Japones","¥",158.1311),
        RUB("Rublo Ruso","₽", 77.49925),
        INR("Rupia India", "₹", 90.93269);

        private final String nombre;
        private final String simbolo;
        private final double tipoCambio;

        Divisa(String nombre, String simbolo, double tipoCambio) {
            this.nombre = nombre;
            this.simbolo = simbolo;
            this.tipoCambio = tipoCambio;
        }
    }

    public enum UnidadPeso {
        ONZA_TROY("t oz",1.0),
        ONZA("oz", 1.09715),
        LIBRA("lb", 1.09715),
        GRAMOS("g",31.1034768),
        KILOGRAMOS("kg", 0.0311);

        private final String simbolo;
        private final double factorOnzasTroy;

        UnidadPeso(String simbolo ,double factorOnzasTroy) {
            this.simbolo = simbolo;
            this.factorOnzasTroy = factorOnzasTroy;
        }

        public double getUnidad() {
            return factorOnzasTroy;
        }

        public double convertir_Unidad(double valor, UnidadPeso unidadQuerida) {
            return  (valor * this.factorOnzasTroy)/ unidadQuerida.getUnidad();
        }
    }

}
