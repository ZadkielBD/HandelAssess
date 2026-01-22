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

        public String getNombre() {
            return nombre;
        }

        public String getSimbolo() {
            return simbolo;
        }

        public double getTipoCambio() {
            return tipoCambio;
        }
    }

    public enum UnidadPeso {
        ONZA_TROY("t oz",1.0),
        ONZA("oz", 1.09715),
        LIBRA("lb", 14.583),
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

        public String getSimbolo() {
            return simbolo;
        }
    }
    public enum LeyPlata {

        LEY_999(0.999, "Plata fina (Inversión)"),
        LEY_950(0.950, "Plata mexicana"),
        LEY_925(0.925, "Plata Sterling"),
        LEY_900(0.900, "Monedas antiguas"),
        LEY_835(0.835, "Plata europea"),
        LEY_800(0.800, "Cubiertos y monedas"),
        LEY_750(0.750, "Plata antigua"),
        LEY_720(0.720, "Uso artesanal");

        private final double factor;
        private final String descripcion;

        LeyPlata(double factor, String descripcion) {
            this.factor = factor;
            this.descripcion = descripcion;
        }

        public double getUnidad() {
            return factor;
        }
        public String getDescripcion() {
            return descripcion;
        }
    }
}
