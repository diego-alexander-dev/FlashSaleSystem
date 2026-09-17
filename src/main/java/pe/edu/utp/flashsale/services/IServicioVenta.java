package pe.edu.utp.flashsale.services;

public interface IServicioVenta {
    void ejecutarCompra(String idUsuario, String idProducto, String metodoPago, double precio);
}