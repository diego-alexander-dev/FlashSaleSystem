package pe.edu.utp.flashsale.ui;

import pe.edu.utp.flashsale.services.IServicioVenta;
import pe.edu.utp.flashsale.services.ProxySeguridad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;

public class VentanaPrincipal extends JFrame {

    private IServicioVenta plataforma;

    private JTextField txtUsuario;
    private JComboBox<String> cbProducto;
    private JComboBox<String> cbMetodoPago;
    private JTextArea txtConsolaLogs;

    // Paleta de Colores - Modern Dark Theme
    private final Color BG_APP = new Color(24, 24, 37);
    private final Color PANEL_BG = new Color(30, 30, 46);
    private final Color TEXT_MAIN = new Color(205, 214, 244);
    private final Color SUCCESS = new Color(166, 227, 161);
    private final Color SUCCESS_HOVER = new Color(148, 203, 143);
    private final Color DANGER = new Color(243, 139, 168);
    private final Color DANGER_HOVER = new Color(217, 124, 150);

    public VentanaPrincipal() {
        plataforma = new ProxySeguridad();

        setTitle("Dashboard Central - Orquestador de Patrones de Diseño");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_APP);

        inicializarComponentes();
        redirigirConsola();
    }

    private void inicializarComponentes() {
        // ================= PANEL IZQUIERDO (CONTROLES) =================
        JPanel panelIzquierdo = new JPanel(new BorderLayout(0, 20));
        panelIzquierdo.setBackground(BG_APP);
        panelIzquierdo.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelIzquierdo.setPreferredSize(new Dimension(350, 0));

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 1, 0, 5));
        panelFormulario.setBackground(PANEL_BG);
        TitledBorder borderForm = BorderFactory.createTitledBorder(new LineBorder(new Color(69, 71, 90)), "Configuración de Orden (Builder)", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), TEXT_MAIN);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(borderForm, new EmptyBorder(15, 15, 15, 15)));

        JLabel lbl1 = crearLabel("ID de Usuario Autenticado:");
        txtUsuario = crearTextField("USR-10023");
        
        JLabel lbl2 = crearLabel("Seleccionar Producto:");
        cbProducto = crearComboBox(new String[]{"Smartphone Pro ($999.00)", "Laptop Gamer ($1500.00)", "Auriculares BT ($150.00)"});
        
        JLabel lbl3 = crearLabel("Pasarela (Factory Method):");
        cbMetodoPago = crearComboBox(new String[]{"TARJETA", "PAYPAL", "TRANSFERENCIA (Adapter)"});

        panelFormulario.add(lbl1);
        panelFormulario.add(txtUsuario);
        panelFormulario.add(lbl2);
        panelFormulario.add(cbProducto);
        panelFormulario.add(lbl3);
        panelFormulario.add(cbMetodoPago);

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 0, 15));
        panelBotones.setBackground(BG_APP);

        JButton btnComprar = crearBoton("PROCESAR TRANSACCIÓN", SUCCESS, SUCCESS_HOVER, Color.BLACK);
        btnComprar.addActionListener(e -> ejecutarCompraNormal());

        JButton btnAtaqueBot = crearBoton("SIMULAR ATAQUE BOT", DANGER, DANGER_HOVER, Color.WHITE);
        btnAtaqueBot.addActionListener(e -> ejecutarAtaqueBot());

        panelBotones.add(btnComprar);
        panelBotones.add(btnAtaqueBot);

        panelIzquierdo.add(panelFormulario, BorderLayout.NORTH);
        panelIzquierdo.add(panelBotones, BorderLayout.SOUTH);

        // ================= PANEL DERECHO (CONSOLA) =================
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(BG_APP);
        panelDerecho.setBorder(new EmptyBorder(20, 0, 20, 20));

        txtConsolaLogs = new JTextArea();
        txtConsolaLogs.setEditable(false);
        txtConsolaLogs.setBackground(new Color(17, 17, 27)); // Negro muy oscuro
        txtConsolaLogs.setForeground(new Color(166, 227, 161)); // Verde hacker
        txtConsolaLogs.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtConsolaLogs.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollLogs = new JScrollPane(txtConsolaLogs);
        scrollLogs.setBorder(BorderFactory.createLineBorder(new Color(69, 71, 90), 1));
        
        JLabel lblConsola = new JLabel("  Live Server Monitor (Facade & Proxy Tracking)");
        lblConsola.setForeground(TEXT_MAIN);
        lblConsola.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblConsola.setBorder(new EmptyBorder(0, 0, 10, 0));

        panelDerecho.add(lblConsola, BorderLayout.NORTH);
        panelDerecho.add(scrollLogs, BorderLayout.CENTER);

        // Ensamblaje final
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }

    // --- Métodos de diseño UI ---
    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(TEXT_MAIN);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return lbl;
    }

    private JTextField crearTextField(String textoBase) {
        JTextField txt = new JTextField(textoBase);
        txt.setBackground(new Color(49, 50, 68));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(69, 71, 90)), new EmptyBorder(5, 10, 5, 10)));
        return txt;
    }

    private JComboBox<String> crearComboBox(String[] opciones) {
        JComboBox<String> cb = new JComboBox<>(opciones);
        cb.setBackground(new Color(49, 50, 68));
        cb.setForeground(Color.WHITE);
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return cb;
    }

    private JButton crearBoton(String texto, Color bg, Color hover, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 45));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent evt) { btn.setBackground(bg); }
        });
        return btn;
    }

    // --- Lógica del Negocio ---
    private void ejecutarCompraNormal() {
        String usuario = txtUsuario.getText();
        String metodoPago = ((String) cbMetodoPago.getSelectedItem()).split(" ")[0]; // Extrae solo "TRANSFERENCIA"
        
        int index = cbProducto.getSelectedIndex();
        String idProducto = index == 0 ? "PROD-SMART" : (index == 1 ? "PROD-LAPT" : "PROD-AURI");
        double precio = index == 0 ? 999.00 : (index == 1 ? 1500.00 : 150.00);

        System.out.println("\n[APP] ---------------------------------------------");
        System.out.println("[APP] PETICIÓN CAPTURADA DESDE FRONTEND");
        System.out.println("[APP] ---------------------------------------------");
        plataforma.ejecutarCompra(usuario, idProducto, metodoPago, precio);
    }

    private void ejecutarAtaqueBot() {
        System.out.println("\n[APP] ---------------------------------------------");
        System.out.println("[APP] ADVERTENCIA: INYECTANDO TRÁFICO MALICIOSO...");
        System.out.println("[APP] ---------------------------------------------");
        plataforma.ejecutarCompra("BOT_SCRIPT_HACK_01", "PROD-LAPT", "PAYPAL", 1500.00);
    }

    private void redirigirConsola() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() -> {
                    txtConsolaLogs.append(String.valueOf((char) b));
                    txtConsolaLogs.setCaretPosition(txtConsolaLogs.getDocument().getLength());
                });
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
        
        System.out.println("==================================================");
        System.out.println("  MOTOR DE PATRONES INICIADO Y EN ESCUCHA...");
        System.out.println("==================================================");
    }
}