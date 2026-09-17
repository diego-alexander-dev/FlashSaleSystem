package pe.edu.utp.flashsale;

import pe.edu.utp.flashsale.ui.VentanaBienvenida;
import javax.swing.SwingUtilities;

public class AplicacionFlashSale {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Ahora la aplicación inicia con la pantalla de Login
            VentanaBienvenida bienvenida = new VentanaBienvenida();
            bienvenida.setVisible(true);
        });
    }
}