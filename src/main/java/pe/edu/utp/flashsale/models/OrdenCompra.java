package pe.edu.utp.flashsale.models;

/**
 * Patrón BUILDER: Permite construir paso a paso un objeto complejo.
 * Evita constructores con demasiados parámetros y valida la integridad de los datos.
 */
public class OrdenCompra {
    private final String idUsuario;
    private final String idProducto;
    private final double montoTotal;
    private final boolean envioExpress;

    private OrdenCompra(Builder builder) {
        this.idUsuario = builder.idUsuario;
        this.idProducto = builder.idProducto;
        this.montoTotal = builder.montoTotal;
        this.envioExpress = builder.envioExpress;
    }

    public String getIdUsuario() { return idUsuario; }
    public double getMontoTotal() { return montoTotal; }

    @Override
    public String toString() {
        return "Orden[Usuario=" + idUsuario + ", Prod=" + idProducto + ", Monto=$" + montoTotal + "]";
    }

    public static class Builder {
        private String idUsuario;
        private String idProducto;
        private double montoTotal;
        private boolean envioExpress = false; // Valor por defecto

        public Builder(String idUsuario, String idProducto) {
            this.idUsuario = idUsuario;
            this.idProducto = idProducto;
        }

        public Builder setMontoTotal(double montoTotal) {
            this.montoTotal = montoTotal;
            return this;
        }

        public Builder setEnvioExpress(boolean envioExpress) {
            this.envioExpress = envioExpress;
            return this;
        }

        public OrdenCompra build() {
            if (montoTotal <= 0) {
                throw new IllegalStateException("El monto total debe ser mayor a cero.");
            }
            return new OrdenCompra(this);
        }
    }
}
