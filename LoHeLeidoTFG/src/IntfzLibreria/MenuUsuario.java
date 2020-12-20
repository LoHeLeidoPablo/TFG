package IntfzLibreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Map;

public class MenuUsuario extends JFrame {

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://AdminUser:iReadIt@loheleido.idhnu.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecUsuario = DDBB.getCollection("Usuario");

  IntfzInfoLibro infoLibro = new IntfzInfoLibro();
  IntfzLogin intfzLogin = new IntfzLogin();
  IntfzMiBiblioteca intfzMiBiblioteca = new IntfzMiBiblioteca();
  IntfzMiCuenta intfzMiCuenta = new IntfzMiCuenta();
  IntfzPrincipal intfzPrincipal = new IntfzPrincipal();
  IntfzRegistro intfzRegistro = new IntfzRegistro();
  IntfzRegLibro intfzRegLibro = new IntfzRegLibro();

  JPanel panelMenuUsuario;
  JPanel panelBusqueda;

  JLabel lblTituloProyecto;
  JLabel lblUsuario = new JLabel();
  JLabel lblRegLibro =
      new JLabel("No encunetra el libro que busca en nuestra Libreria, pulse aqui para añadirlo.");
  JTextField txtPBuscador;
  JTextField txtBuscador;

  JComboBox<String> jcbTemas;
  JComboBox<String> jcbElementos;

  JButton btnLogIn = new JButton("Iniciar Sesion");
  JButton btnCuenta = new JButton("Mi Cuenta");
  JButton btnBiblioteca = new JButton("Mi biblioteca");
  JButton btnLogOut = new JButton("Cerrar Sesion");
  JButton btnClose = new JButton("Salir");

  JButton btnEscape;

  String colorTema;
  Boolean visiblePanelMUsuario = true;
  Boolean visiblePanelBuscador = true;
  Font fuenteis = new Font("Book Antiqua", 3, 45);
  Font fuenteUsu = new Font("Book Antiqua", 1, 22);
  Font font = lblRegLibro.getFont();

  Interfaz interfazActiva;
  JPanel[] jPanelA = {panelMenuUsuario, panelBusqueda};
  JLabel[] jLabelA = {lblTituloProyecto, lblUsuario};
  JButton[] jButtonA = {btnLogIn, btnCuenta, btnBiblioteca, btnLogOut, btnClose};

  JLabel jPrueba;

  public MenuUsuario(JPanel jpanel, Interfaz interfazActiva) {
    this.interfazActiva = interfazActiva;

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

    lblTituloProyecto = new JLabel("¿Lo he leído?");
    lblTituloProyecto.setBounds(20, 18, 310, 54);
    lblTituloProyecto.setFont(fuenteis);
    jpanel.add(lblTituloProyecto);

    txtBuscador = new JTextField("Buscar por ISBN, Titulo, Autor, Serie, Saga, Autor...");
    txtBuscador.setBounds(315, 30, 1000, 30);
    jpanel.add(txtBuscador);

    panelBusqueda = new JPanel();
    panelBusqueda.setBounds(315, 15, 1000, 500);
    panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    panelBusqueda.setLayout(null);
    panelBusqueda.hide();
    jpanel.add(panelBusqueda);

    txtPBuscador = new JTextField();
    txtPBuscador.setBounds(50, 50, 900, 25);
    panelBusqueda.add(txtPBuscador);

    btnEscape = new JButton();
    btnEscape.setBounds(5, 5, 25, 25);
    panelBusqueda.add(btnEscape);
    ImageIcon portadaIco = new ImageIcon("LoHeLeidoTFG/src/puntoJAR/close.png");
    Icon icono =
        new ImageIcon(
            portadaIco
                .getImage()
                .getScaledInstance(
                    btnEscape.getWidth() + 5, btnEscape.getHeight() + 10, Image.SCALE_DEFAULT));
    btnEscape.setIcon(icono);

    jPrueba = new JLabel("Prueba");
    jPrueba.setBounds(200, 200, 100, 100);
    panelBusqueda.add(jPrueba);
    jPrueba.setBorder(txtBuscador.getBorder());
    jPrueba.setVisible(true);

    lblRegLibro.setBounds(200, 475, 600, 20);
    lblRegLibro.setForeground(Color.blue);
    panelBusqueda.add(lblRegLibro);
    if (IntfzLogin.id_Usuario.equals("Invitado")) {
      lblRegLibro.setText(
          "Si no encuentras el libro que buscas, unete a 'Lo He Leído', para registrarlo");
    } else {
      abrirRegLibro();
    }

    lblUsuario.setText(IntfzLogin.UsuCuenta.getString("Nombre"));
    lblUsuario.setText(lblUsuario.getText() == null ? "Invitado" : lblUsuario.getText());
    lblUsuario.setFont(fuenteUsu);
    lblUsuario.setBounds(1400, 23, 100, 27);
    jpanel.add(lblUsuario);

    panelMenuUsuario = new JPanel();
    panelMenuUsuario.setBounds(1350, 35, 200, 155);
    panelMenuUsuario.setLayout(null);
    panelMenuUsuario.setOpaque(false);
    panelMenuUsuario.hide();
    jpanel.add(panelMenuUsuario);

    btnLogIn.setBounds(10, 10, 180, 20);
    panelMenuUsuario.add(btnLogIn);
    panelMenuUsuario.add(btnCuenta);
    panelMenuUsuario.add(btnBiblioteca);
    panelMenuUsuario.add(btnLogOut);
    panelMenuUsuario.add(btnClose);

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

    // crearComponentes(jpanel);
    btnLog();
    despliegePaneles();
    botonesUsuario();
    busqueda();
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

  public void despliegePaneles() {
    txtBuscador.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panelBusqueda.show();
            panelBusqueda.setVisible(true);
            txtBuscador.setVisible(false);
          }
        });

    lblUsuario.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panelMenuUsuario.show();
            panelMenuUsuario.setVisible(visiblePanelMUsuario);
            visiblePanelMUsuario = !visiblePanelMUsuario;
          }
        });
  }

  public void botonesUsuario() {

    btnEscape.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            panelBusqueda.setVisible(false);
            txtBuscador.setVisible(true);
          }
        });

    btnLogIn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            IntfzLogin ventana = new IntfzLogin();
            ventana.iniciar();
          }
        });

    btnCuenta.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            disposeAll();
            intfzMiCuenta.iniciar();
          }
        });

    btnBiblioteca.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            disposeAll();
            intfzMiBiblioteca.iniciar();
          }
        });

    btnLogOut.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            lblUsuario.setText("Invitado");
            disposeAll();
            intfzPrincipal.iniciar();
          }
        });

    btnClose.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });
  }

  public void abrirRegLibro() {
    lblRegLibro.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panelBusqueda.setVisible(false);
            txtBuscador.setVisible(true);
            dispose();
            intfzRegLibro.iniciar();
          }

          @Override
          public void mouseEntered(MouseEvent e) {
            lblRegLibro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Font subrayado = lblRegLibro.getFont();
            Map attributes = subrayado.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            lblRegLibro.setFont(subrayado.deriveFont(attributes));
          }

          @Override
          public void mouseExited(MouseEvent e) {
            lblRegLibro.setFont(font);
          }
        });
  }

  public void busqueda() {
    EscuchaTexto busqueda = new EscuchaTexto();
    javax.swing.text.Document campo = txtPBuscador.getDocument();
    campo.addDocumentListener(busqueda);
  }

  // TODO Por terminar

  private class EscuchaTexto implements DocumentListener {
    @Override
    public void insertUpdate(DocumentEvent e) {
      jPrueba.setText(txtPBuscador.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
      jPrueba.setText(txtPBuscador.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      // Este no es necesario para el funcionamiento de la app
    }
  }

  public void disposeAll() {

    if (intfzPrincipal.isShowing())
      ;
    intfzPrincipal.dispose();
    if (intfzLogin.isShowing())
      ;
    intfzLogin.dispose();
    if (intfzMiBiblioteca.isShowing())
      ;
    intfzMiBiblioteca.dispose();
    if (intfzMiCuenta.isShowing())
      ;
    intfzMiCuenta.dispose();
    if (intfzRegistro.isShowing())
      ;
    intfzRegistro.dispose();
    if (intfzRegLibro.isShowing())
      ;
    intfzRegLibro.dispose();
  }

  public void crearComponentes(JPanel jpanel) {
    for (JPanel jPanelE : jPanelA) {
      jpanel.add(jPanelE);
      jPanelE.setVisible(false);
    }
    for (JLabel jLabel : jLabelA) {
      jpanel.add(jLabel);
    }
    for (JButton jButton : jButtonA) {
      panelMenuUsuario.add(jButton);
    }
  } // No Funciona

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, jLabelA, null, null, null, null, null);
  } // Esto no funciona
}
