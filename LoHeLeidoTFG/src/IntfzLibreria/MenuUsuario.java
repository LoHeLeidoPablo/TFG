package IntfzLibreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuUsuario extends JFrame {

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://PabloBibTFG:7Infantes@biblioteca.w5wrr.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecUsuario = DDBB.getCollection("usuario");

  IntfzInfoLibro infoLibro = new IntfzInfoLibro();
  IntfzLogin intfzLogin = new IntfzLogin();
  IntfzMiBiblioteca intfzMiBiblioteca = new IntfzMiBiblioteca();
  IntfzMiCuenta intfzMiCuenta = new IntfzMiCuenta();
  IntfzPrincipal intfzPrincipal = new IntfzPrincipal();
  IntfzRegistro intfzRegistro = new IntfzRegistro();
  IntfzRegLibro intfzRegLibro = new IntfzRegLibro();

  String colorTema;
  JLabel lblTituloProyecto;
  JLabel lblUsuario = new JLabel();
  JPanel panelMenuUsuario;
  JComboBox<String> jcbTemas;
  JButton btnLogIn;
  JButton btnCuenta;
  JButton btnBiblioteca;
  JButton btnLogOut;
  JButton btnClose;
  Boolean visible = true;
  Interfaz interfazActiva;
  JPanel[] jPanelA = {panelMenuUsuario};
  JLabel[] jLabelA = {lblTituloProyecto, lblUsuario};

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
    lblUsuario.setText(IntfzLogin.UsuCuenta.getString("Nombre"));
    lblUsuario.setText(lblUsuario.getText() == null ? "Invitado" : lblUsuario.getText());
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
            disposeAll();
            intfzMiCuenta.iniciar();
          }
        });
    btnBiblioteca = new JButton("Mi biblioteca");
    panelMenuUsuario.add(btnBiblioteca);
    btnBiblioteca.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            disposeAll();
            intfzMiBiblioteca.iniciar();
          }
        });

    btnLogOut = new JButton("Cerrar Sesion");
    panelMenuUsuario.add(btnLogOut);
    btnLogOut.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            lblUsuario.setText("Invitado");
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
      if ("Invitado".equals(lblUsuario.getText())) {
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

    intfzPrincipal.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            dispose();
          }
        });
    intfzLogin.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            dispose();
          }
        });
    intfzMiBiblioteca.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            dispose();
          }
        });
    intfzMiCuenta.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            dispose();
          }
        });
    intfzRegistro.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            dispose();
          }
        });
    intfzRegLibro.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            dispose();
          }
        });
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, jLabelA, null, null, null, null, null);
  } // Esto no funciona
}
