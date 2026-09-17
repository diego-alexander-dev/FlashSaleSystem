package pe.edu.utp.flashsale.services;

public class ProxySeguridad implements IServicioVenta {
    private MotorVentasFacade motorReal;
    
    // Lista negra simulada para detectar ataques DDOS o Bots
    private final String[] ipsBloqueadas = {"192.168.0.99", "10.0.0.5"};

    public ProxySeguridad() {
        this.motorReal = new MotorVentasFacade();
    }

    @Override
public void ejecutarCompra(String idUsuario, String idProducto, String metodoPago, double precio) {
    System.out.println("\n[Proxy] Analizando la petición entrante del usuario: " + idUsuario);

    // ESTA ES LA LÍNEA MÁGICA
    if (idUsuario.startsWith("BOT_")) { 
        System.out.println("🛑 [Proxy] ALERTA DE SEGURIDAD: Comportamiento anómalo detectado. Petición bloqueada.");
        return; 
    }

    System.out.println("✔️ [Proxy] Verificación superada. Transfiriendo petición...");
    motorReal.ejecutarCompra(idUsuario, idProducto, metodoPago, precio);
}
}