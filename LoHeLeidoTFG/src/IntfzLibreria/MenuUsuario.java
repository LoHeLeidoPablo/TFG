package IntfzLibreria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuUsuario extends JFrame {

  IntfzInfoLibro infoLibro = new IntfzInfoLibro();
  IntfzLogin intfzLogin = new IntfzLogin();
  IntfzMiBiblioteca intfzMiBiblioteca = new IntfzMiBiblioteca();
  IntfzMiCuenta intfzMiCuenta = new IntfzMiCuenta();
  IntfzPrincipal intfzPrincipal = new IntfzPrincipal();
  IntfzRegistro intfzRegistro = new IntfzRegistro();
  IntfzRegLibro intfzRegLibro = new IntfzRegLibro();

  private String colorTema;
  private JLabel lblTituloProyecto;
  private JLabel lblUsuario;
  private JPanel panelMenuUsuario;
  private JComboBox<String> jcbTemas;
  private JButton btnLogIn;
  private JButton btnCuenta;
  private JButton btnBiblioteca;
  private JButton btnLogOut;
  private JButton btnClose;
  private boolean visible = true;
  private Interfaz interfazActiva;
  private JPanel[] jPanelA = {panelMenuUsuario};
  private JLabel[] jLabelA = {lblTituloProyecto, lblUsuario};

  public MenuUsuario() {}

  public MenuUsuario(JPanel jpanel, Interfaz interfazActiva) {
    this.interfazActiva = interfazActiva;

    lblTituloProyecto = new JLabel("¿Lo he leído?");
    lblTituloProyecto.setBounds(10, 10, 250, 50);
    Font fuenteis = new Font("Consola", 3, 30);
    lblTituloProyecto.setFont(fuenteis);
    jpanel.add(lblTituloProyecto);

    panelMenuUsuario = new JPanel();
    panelMenuUsuario.setBounds(1350, 35, 200, 155);
    panelMenuUsuario.setLayout(null);
    panelMenuUsuario.setOpaque(false);
    panelMenuUsuario.hide();

    jpanel.add(panelMenuUsuario);

    String[] colores = {
      "Claro",
      "Oscuro",
      "Amarillo Oscuro",
      "Rojo Oscuro",
      "Verde Oscuro",
      "Azul Oscuro",
      "Naranja Claro",
      "Rojo Claro",
      "Verde Claro",
      "Azul Claro",
    };

    lblUsuario = new JLabel(IntfzLogin.id_Usuario);
    lblUsuario.setBounds(1400, 15, 100, 15);
    jpanel.add(lblUsuario);
    lblUsuario.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panelMenuUsuario.show();
            panelMenuUsuario.setVisible(visible);
            visible = !visible;
          }
        });

    btnLogIn = new JButton("Iniciar Sesion");
    btnLogIn.setBounds(10, 10, 180, 20);
    panelMenuUsuario.add(btnLogIn);
    btnLogIn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            IntfzLogin ventana = new IntfzLogin();
            ventana.iniciar();
          }
        });

    btnCuenta = new JButton("Mi Cuenta");
    panelMenuUsuario.add(btnCuenta);
    btnCuenta.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            jpanel.setVisible(false);
            disposeAll();
          }
        });
    btnBiblioteca = new JButton("Mi biblioteca");
    panelMenuUsuario.add(btnBiblioteca);
    btnBiblioteca.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            jpanel.setVisible(false);
            disposeAll();
          }
        });

    btnLogOut = new JButton("Cerrar Sesion");
    panelMenuUsuario.add(btnLogOut);
    btnLogOut.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            IntfzLogin.id_Usuario = "Invitado";
            jpanel.setVisible(false);
            disposeAll();
            intfzPrincipal.iniciar();
          }
        });

    btnClose = new JButton("Salir");
    panelMenuUsuario.add(btnClose);
    btnClose.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });

    jcbTemas = new JComboBox<String>(colores);
    panelMenuUsuario.add(jcbTemas);
    jcbTemas.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            colorTema = jcbTemas.getSelectedItem().toString();
            interfazActiva.cambioTema(colorTema);
          }
        });
    btnLog();
  }

  public void btnLog() {
    if (lblUsuario == null) {
    } else {
      if (lblUsuario.getText().equals("Invitado")) {
        btnLogOut.setVisible(false);
        btnCuenta.setVisible(false);
        btnBiblioteca.setVisible(false);
        btnClose.setBounds(10, 40, 180, 20);
        jcbTemas.setBounds(10, 70, 180, 20);

      } else {
        btnLogIn.setVisible(false);
        btnCuenta.setBounds(10, 10, 180, 20);
        btnBiblioteca.setBounds(10, 40, 180, 20);
        btnLogOut.setBounds(10, 70, 180, 20);
        btnClose.setBounds(10, 100, 180, 20);
        jcbTemas.setBounds(10, 130, 180, 20);
      }
    }
  }

  public void disposeAll() {
    infoLibro.dispose();
    intfzLogin.dispose();
    intfzMiBiblioteca.dispose();
    intfzMiCuenta.dispose();
    intfzPrincipal.dispose();
    intfzRegistro.dispose();
    intfzRegLibro.dispose();
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, jLabelA, null, null, null, null, null);
  } // Esto no funciona
}
