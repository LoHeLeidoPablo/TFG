package IntfzLibreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntfzRegistro extends JFrame {

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://PabloBibTFG:7Infantes@biblioteca.w5wrr.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecAuth = DDBB.getCollection("Auth");
  MongoCollection<Document> collecUsuario = DDBB.getCollection("usuario");

  IntfzLogin intfzLogin = new IntfzLogin();

  JPanel panel = new JPanel();

  JLabel lblTituloProyecto = new JLabel("Unete a Nosotros");
  JLabel lblUsuario = new JLabel("Usuario:");
  JLabel lblObUsuario = new JLabel("Obligatorio");
  JLabel lblEmail = new JLabel("Email:");
  JLabel lblObEmail = new JLabel("Obligatorio");
  JLabel lblPassword = new JLabel("Contraseña:");
  JLabel lblObPassword = new JLabel("La contraseña debe contener al menos 8 caracteres");
  JLabel lblPassword2 = new JLabel("Contraseña:");
  JLabel lblObPassword2 = new JLabel("La contraseña debe coincidir con la otra contraseña");

  JTextField txtUsuario = new JTextField("");
  JTextField txtEmail = new JTextField("");
  JPasswordField txtPassword = new JPasswordField("");
  JPasswordField txtRepPassword = new JPasswordField("");
  JButton btnRegistro = new JButton("Registrar");

  String contra1;
  String contra2;
  Boolean existe = false;

  JCheckBox cbVerPasswd = new JCheckBox("Mostrar Contraseñas");
  JPanel[] jPanelA = {panel};
  JLabel[] jLabelA = {
    lblTituloProyecto, lblUsuario, lblEmail, lblPassword, lblPassword, lblPassword2
  };
  JLabel[] jLabelObli = {lblObUsuario, lblObEmail, lblObPassword, lblObPassword2};
  JButton[] jButtonA = {btnRegistro};
  JTextField[] jTextFieldA = {txtUsuario, txtEmail, txtPassword, txtRepPassword};
  JComponent[] jComponentA = {
    lblTituloProyecto,
    lblUsuario,
    lblEmail,
    lblPassword,
    lblPassword,
    lblPassword2,
    lblObUsuario,
    lblObEmail,
    lblObPassword,
    lblObPassword2,
    btnRegistro,
    txtUsuario,
    txtEmail,
    txtPassword,
    txtRepPassword,
    cbVerPasswd
  };

  Font fuenteObligatoria = new Font(lblUsuario.getFont().getFamily(), Font.ITALIC, 9);

  public IntfzRegistro() {
    this.setResizable(false);
  }

  public void iniciar() {
    setTitle("Registrar - ¿Lo he leído?");
    getContentPane().setLayout(new GridLayout(1, 10));
    crearComponentes();
    mostrarContraseña(txtPassword);
    mostrarContraseña(txtRepPassword);
    rescribirCampo(txtUsuario, lblObUsuario);
    rescribirCampo(txtEmail, lblObEmail);
    rescribirCampo(txtPassword, lblObPassword);
    rescribirCampo(txtRepPassword, lblObPassword2);

    panel.setLayout(null);

    lblTituloProyecto.setBounds(15, 10, 250, 25);
    Font fuenteis = new Font("Consola", 3, 22);
    lblTituloProyecto.setFont(fuenteis);

    lblUsuario.setBounds(20, 50, 100, 15);
    txtUsuario.setBounds(20, 65, 235, 20);
    lblObUsuario.setBounds(20, 85, 160, 15);

    lblEmail.setBounds(20, 110, 100, 15);
    txtEmail.setBounds(20, 125, 235, 20);
    lblObEmail.setBounds(20, 145, 160, 15);

    lblPassword.setBounds(20, 170, 100, 15);
    txtPassword.setBounds(20, 185, 235, 20);
    lblObPassword.setBounds(10, 205, 255, 15);

    lblPassword2.setBounds(20, 230, 100, 15);
    txtRepPassword.setBounds(20, 245, 235, 20);
    lblObPassword2.setBounds(10, 265, 255, 15);

    for (JLabel jLabel : jLabelObli) {
      jLabel.setForeground(Color.red);
      jLabel.setVisible(false);
      jLabel.setFont(fuenteObligatoria);
    }

    cbVerPasswd.setBounds(20, 285, 230, 20);
    cbVerPasswd.setBackground(panel.getBackground());
    mostrarContraseña(txtPassword);
    mostrarContraseña(txtRepPassword);

    btnRegistro.setBounds(10, 315, 255, 25);
    btnRegistro.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contra1 = txtPassword.getText();
            contra2 = txtRepPassword.getText();
            if (!txtUsuario.getText().isEmpty()) {
              if (!txtEmail.getText().isEmpty()) {
                if (txtPassword.getText().length() >= 2) {
                  if (contra1.equals(contra2)) {
                    try {
                      creacionUsuario();
                    } catch (ParseException parseException) {
                      parseException.printStackTrace();
                    }
                  } else {
                    lblObPassword2.setVisible(true);
                  }
                } else {
                  lblObPassword.setVisible(true);
                }
              } else {
                lblObEmail.setVisible(true);
              }
            } else {
              lblObUsuario.setVisible(true);
            }
          }
        });

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(275, 385);
    setVisible(true);
  }

  public void creacionUsuario() throws ParseException {
    if (existeUsuario() == false) {
      String nameusu = txtUsuario.getText();
      String email = txtEmail.getText();
      String passwd = txtPassword.getText();

      Date fechaRegistro = new Date();

      Document auth = new Document();
      auth.put("Nombre", nameusu);
      auth.put("Email", email);
      auth.put("Contraseña", passwd);
      collecAuth.insertOne(auth);

      Document usuario = new Document();
      usuario.put("Nombre", nameusu);
      usuario.put("Email", email);
      usuario.put("fCreacionCuenta", fechaRegistro);
      usuario.put("NPrestados", 0);
      collecUsuario.insertOne(usuario);
      mensajeEmergente(1);
      panel.setVisible(false);
      dispose();

      intfzLogin.iniciar();

    } else {
      mensajeEmergente(2);
      existe = false;
    }
  }

  public boolean existeUsuario() {
    // Document doc = collecUsuario.find(eq("Email", txtEmail.getText())).first();
    List<Document> consulta = collecUsuario.find().into(new ArrayList<Document>());
    for (int i = 0; i < consulta.size(); i++) {
      Document usuario = consulta.get(i);
      String email = txtEmail.getText();
      if (email.equals(usuario.getString("Email"))) {
        existe = true;
        break;
      }
    }
    return existe;
  }

  public void rescribirCampo(JTextField jTextField, JLabel jLabel) {
    jTextField.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            jLabel.setVisible(false);
          }
        });
  }

  public void mostrarContraseña(JPasswordField jPasswordField) {
    cbVerPasswd.addItemListener(
        new ItemListener() {
          public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
              jPasswordField.setEchoChar((char) 0);
              cbVerPasswd.setText("Ocultar Contraseñas");
            } else {
              jPasswordField.setEchoChar('*');
              cbVerPasswd.setText("Mostrar Contraseñas");
            }
          }
        });
  }

  public void mensajeEmergente(int mensaje) {
    if (mensaje == 1) {
      JOptionPane.showMessageDialog(
          null,
          "Usuario Registrado Correctamente",
          "Registro Completado",
          JOptionPane.INFORMATION_MESSAGE);
    } else if (mensaje == 2) {
      JOptionPane.showMessageDialog(
          null, "Este Usuario ya existe ", "Registro Fallido", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void crearComponentes() {
    for (JComponent jComponent : jComponentA) {
      panel.add(jComponent);
    }
  }
}
