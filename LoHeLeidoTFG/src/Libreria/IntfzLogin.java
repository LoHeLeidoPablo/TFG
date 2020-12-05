package Libreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntfzLogin extends JFrame implements Interfaz {
  private static final long serialVersionUID = 1L;
  public static String id_Usuario = "Invitado";

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://PabloBibTFG:7Infantes@biblioteca.w5wrr.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecAuth = DDBB.getCollection("Auth");
  MongoCollection<Document> collecUsuario = DDBB.getCollection("usuario");

  IntfzPrincipal intfzPrincipal = new IntfzPrincipal();
  MenuUsuario menuUsuario = new MenuUsuario();

  JPanel panel = new JPanel();

  JLabel lblTituloProyecto = new JLabel("¿Lo he leído?");
  JLabel lblUsuario = new JLabel("Usuario:");
  JLabel lblPassword = new JLabel("Contraseña:");
  JLabel lblRegistro = new JLabel("Crear Nueva Cuenta");

  JTextField txtUsuario = new JTextField();
  JPasswordField txtPassword = new JPasswordField();

  JButton btnLogIn = new JButton("Iniciar Sesión");

  JCheckBox cbVerPasswd = new JCheckBox("Mostrar Contraseña");

  boolean registrado = false;

  JComponent[] jComponentA = {
    lblTituloProyecto,
    lblUsuario,
    txtUsuario,
    lblPassword,
    txtPassword,
    cbVerPasswd,
    lblRegistro,
    btnLogIn
  };
  JPanel[] jPanelA = {panel};
  JLabel[] jLabelA = {lblUsuario, lblTituloProyecto, lblPassword, lblRegistro};
  JButton[] jButtonA = {btnLogIn};
  JTextField[] jTextFieldA = {txtUsuario, txtPassword};

  Font font = lblRegistro.getFont();

  public IntfzLogin() {}

  public void iniciar() {
    setTitle("Iniciar Sesión - ¿Lo he leído?");
    getContentPane().setLayout(new GridLayout(1, 10));
    crearComponents();
    existeUsuario();
    panel.setLayout(null);

    lblTituloProyecto.setBounds(65, 10, 170, 25);
    Font fuenteis = new Font("Consola", 3, 22);
    lblTituloProyecto.setFont(fuenteis);

    lblUsuario.setBounds(50, 50, 100, 15);
    txtUsuario.setBounds(50, 65, 200, 20);
    lblPassword.setBounds(50, 100, 100, 15);
    txtPassword.setBounds(50, 115, 200, 20);
    txtPassword.setEchoChar('*');

    cbVerPasswd.setBounds(50, 140, 175, 20);
    cbVerPasswd.setBackground(panel.getBackground());
    cbVerPasswd.addItemListener(
        new ItemListener() {
          public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
              txtPassword.setEchoChar((char) 0);
              cbVerPasswd.setText("Ocultar Contraseña");
            } else {
              txtPassword.setEchoChar('*');
              cbVerPasswd.setText("Mostrar Contraseña");
            }
          }
        });

    btnLogIn.setBounds(50, 170, 200, 25);
    panel.add(btnLogIn);

    lblRegistro.setBounds(75, 205, 150, 15);
    lblRegistro.setForeground(Color.blue);
    lblRegistro.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panel.setVisible(false);
            dispose();
            IntfzRegistro ventana = new IntfzRegistro();
            ventana.iniciar();
          }

          @Override
          public void mouseEntered(MouseEvent e) {
            lblRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Font subrayado = lblRegistro.getFont();
            Map attributes = subrayado.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            lblRegistro.setFont(subrayado.deriveFont(attributes));
          }

          @Override
          public void mouseExited(MouseEvent e) {
            lblRegistro.setFont(font);
          }
        });

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(300, 260);
    setVisible(true);
  }

  public void existeUsuario() {
    btnLogIn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            List<Document> consulta = collecAuth.find().into(new ArrayList<Document>());
            List<Document> etqUsuario = collecUsuario.find().into(new ArrayList<Document>());
            // TODO
            /* Document doc =
            collecAuth
                .find(
                    and(eq("Nombre", txtUsuario.getText()), eq("Nombre", txtUsuario.getText())))
                .first();*/

            for (int i = 0; i < consulta.size(); i++) {
              Document usuario = consulta.get(i);
              Document auth = consulta.get(i);
              String usu = txtUsuario.getText();
              String passwd = txtPassword.getText();
              if (usu.equals(auth.getString("Nombre"))
                  | usu.equals(auth.getString("Email"))
                      & passwd.equals(auth.getString("Contraseña"))) {
                registrado = true;
                break;
              }
            }
            if (registrado == true) {
              id_Usuario = txtUsuario.getText(); // Poco Eficiente
              panel.setVisible(false);
              dispose();
              intfzPrincipal.iniciar();
            } else {
              JOptionPane.showMessageDialog(
                  null,
                  "Credenciales Incorrectas, por favor vuelve a intentarlo",
                  "Advertencia",
                  JOptionPane.ERROR_MESSAGE);
            }
          }
        });
    /*
    menuUsuario = new Libreria.MenuUsuario(intfzPrincipal.panel,intfzPrincipal.jFramePrincipal,null);
       menuUsuario.repaint();
       intfzPrincipal.panel.repaint();
       intfzPrincipal.cerrarVentana();
       intfzPrincipal.jFramePrincipal.setVisible(false);
    intfzPrincipal.jFramePrincipal.disable();*/
  }

  public void crearComponents() {
    for (JComponent jComponent : jComponentA) {
      panel.add(jComponent);
    }
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, null, null, null, null, null, null, null);
  }
}
