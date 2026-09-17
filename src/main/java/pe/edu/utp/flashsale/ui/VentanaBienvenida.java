package pe.edu.utp.flashsale.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaBienvenida extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    // Paleta de Colores - Modern Dark Theme
    private final Color BG_DARK = new Color(30, 30, 46);
    private final Color PANEL_DARK = new Color(49, 50, 68);
    private final Color ACCENT_BLUE = new Color(137, 180, 250);
    private final Color ACCENT_HOVER = new Color(116, 152, 211);
    private final Color TEXT_LIGHT = new Color(205, 214, 244);
    private final Color INPUT_BG = new Color(24, 24, 37);

    public VentanaBienvenida() {
        setTitle("Sistema de Acceso - Enterprise Flash Sale");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(false);
        setUndecorated(false); 

        // Configurar panel principal
        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(BG_DARK);
        panelFondo.setBorder(new EmptyBorder(30, 40, 40, 40));

        inicializarComponentes(panelFondo);
        add(panelFondo);
    }

    private void inicializarComponentes(JPanel panelFondo) {
        // ================= HEADER =================
        JPanel panelTitulo = new JPanel(new GridLayout(3, 1));
        panelTitulo.setBackground(BG_DARK);
        panelTitulo.setBorder(new EmptyBorder(0, 0, 30, 0));

        JLabel lblLogo = new JLabel("⚡", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblLogo.setForeground(ACCENT_BLUE);

        JLabel lblTitulo = new JLabel("FLASH SALE ENGINE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Universidad Tecnológica del Perú - Ing. de Software", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.GRAY);

        panelTitulo.add(lblLogo);
        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblSubtitulo);

        // ================= FORMULARIO =================
        JPanel panelLogin = new JPanel(new GridLayout(4, 1, 0, 10));
        panelLogin.setBackground(BG_DARK);

        JLabel lblUser = new JLabel("Usuario de Red:");
        lblUser.setForeground(TEXT_LIGHT);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));

        txtUsuario = crearTextField("admin");

        JLabel lblPass = new JLabel("Clave de Acceso:");
        lblPass.setForeground(TEXT_LIGHT);
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));

        txtPassword = new JPasswordField("12345");
        estilizarInput(txtPassword);

        panelLogin.add(lblUser);
        panelLogin.add(txtUsuario);
        panelLogin.add(lblPass);
        panelLogin.add(txtPassword);

        // ================= BOTÓN =================
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(BG_DARK);
        panelBotones.setBorder(new EmptyBorder(30, 0, 0, 0));

        btnIngresar = new JButton("AUTENTICAR SISTEMA");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setBackground(ACCENT_BLUE);
        btnIngresar.setForeground(BG_DARK);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setPreferredSize(new Dimension(100, 45));

        // Efecto Hover para el botón
        btnIngresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btnIngresar.setBackground(ACCENT_HOVER); }
            public void mouseExited(MouseEvent evt) { btnIngresar.setBackground(ACCENT_BLUE); }
        });

        btnIngresar.addActionListener(e -> validarAcceso());
        panelBotones.add(btnIngresar, BorderLayout.CENTER);

        // Ensamblar
        panelFondo.add(panelTitulo, BorderLayout.NORTH);
        panelFondo.add(panelLogin, BorderLayout.CENTER);
        panelFondo.add(panelBotones, BorderLayout.SOUTH);
    }

    private JTextField crearTextField(String textoBase) {
        JTextField txt = new JTextField(textoBase);
        estilizarInput(txt);
        return txt;
    }

    private void estilizarInput(JTextField txt) {
        txt.setBackground(INPUT_BG);
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(PANEL_DARK, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
    }

    private void validarAcceso() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        if (usuario.equals("admin") && password.equals("12345")) {
            this.dispose(); // Cierra el login
            // Lanza el dashboard principal
            SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
        } else {
            UIManager.put("OptionPane.background", BG_DARK);
            UIManager.put("Panel.background", BG_DARK);
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            JOptionPane.showMessageDialog(this, "Acceso denegado por Proxy de Seguridad", "Error 403", JOptionPane.ERROR_MESSAGE);
        }
    }
}