package Libreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.toedter.calendar.JDateChooser;
import org.bson.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrarLibro extends JFrame {
  private static final long serialVersionUID = 1L;

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://PabloBibTFG:7Infantes@biblioteca.w5wrr.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecLibros = DDBB.getCollection("Libro");

  private JPanel panel = new JPanel();
  private JPanel panelGenero = new JPanel();

  private JLabel lblPortada = new JLabel("Portada: Tamaño Estandar --> 329 * 512");
  private JLabel lblISBN = new JLabel("ISBN:");
  private JLabel lblTitlo = new JLabel("Titulo");
  private JLabel lblAutor = new JLabel("Autor");
  private JLabel lblCapitulos = new JLabel("Capitulos");
  private JLabel lblColeccion = new JLabel("Saga:");
  private JLabel lblNColeccion = new JLabel("Tomo");
  private JLabel lblPublicacion = new JLabel("Fecha Publicacion:");
  private JLabel lblGeneros = new JLabel("Genero");
  private JLabel lblResumen = new JLabel("Resumen");

  private JTextField txtTitlo = new JTextField();
  private JTextField txtAutor = new JTextField();
  private JTextArea txtASinopsis = new JTextArea();
  private JTextField txtGeneros = new JTextField();
  private JTextField txtISBN = new JTextField();
  private JTextField txtColeccion = new JTextField();
  private JSpinner spCapitulos = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
  private JSpinner spNColeccion = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

  private JCheckBox ch1 = new JCheckBox("Aventuras");
  private JCheckBox ch2 = new JCheckBox("Autobiografica");
  private JCheckBox ch3 = new JCheckBox("Ciencia Ficcion");
  private JCheckBox ch4 = new JCheckBox("Fantasia");
  private JCheckBox ch5 = new JCheckBox("Historica");
  private JCheckBox ch6 = new JCheckBox("Infantil");
  private JCheckBox ch7 = new JCheckBox("Romantica");
  private JCheckBox ch8 = new JCheckBox("Misterio");
  private JCheckBox ch9 = new JCheckBox("Negra");
  private JCheckBox ch10 = new JCheckBox("Policiaca");
  private JCheckBox ch11 = new JCheckBox("Comic/Manga");
  private JCheckBox ch12 = new JCheckBox("Otros");

  private JButton btnAddPortada = new JButton("Añadir Portada");
  private JButton btnAddLibro = new JButton("Guardar Libro");
  private JFileChooser fcAddPortada = new JFileChooser();
  private JDateChooser datePublicacion = new JDateChooser();

  Font fuente = new Font(lblGeneros.getFont().getFamily(), Font.BOLD, 12);
  File archivo;
  JCheckBox[] jCheckBoxeA = {ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12};
  boolean existe = false;

  JComponent[] jComponentA = {
    panelGenero,
    lblPortada,
    lblISBN,
    lblTitlo,
    lblAutor,
    lblColeccion,
    lblResumen,
    lblPublicacion,
    lblISBN,
    lblCapitulos,
    lblNColeccion,
    txtTitlo,
    txtAutor,
    txtGeneros,
    txtISBN,
    txtColeccion,
    spCapitulos,
    spNColeccion,
    txtASinopsis,
    btnAddPortada,
    btnAddLibro,
    datePublicacion
  };

  public RegistrarLibro() {
    this.setResizable(false);
  }

  public void iniciar() {
    setTitle("Registrar libro - ¿Lo he leído?");
    getContentPane().setLayout(new GridLayout(1, 10));
    añadirComponentes(jComponentA);
    añadirPortada();

    panel.setLayout(null);
    panelGenero.setLayout(null);

    lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
    lblPortada.setBorder(BorderFactory.createLineBorder(Color.black));
    lblPortada.setBounds(10, 30, 329, 512);
    btnAddLibro.setBounds(10, 550, 160, 30);
    btnAddPortada.setBounds(179, 550, 160, 30);

    lblISBN.setBounds(350, 45, 75, 20);
    txtISBN.setBounds(425, 45, 500, 20);
    lblTitlo.setBounds(350, 80, 75, 20);
    txtTitlo.setBounds(425, 80, 500, 20);
    lblAutor.setBounds(350, 115, 75, 20);
    txtAutor.setBounds(425, 115, 500, 20);
    lblColeccion.setBounds(350, 150, 75, 20);
    txtColeccion.setBounds(425, 150, 500, 20);

    lblNColeccion.setBounds(350, 185, 75, 20);
    spNColeccion.setBounds(425, 185, 45, 20);
    lblCapitulos.setBounds(495, 185, 75, 20);
    spCapitulos.setBounds(570, 185, 45, 20);
    lblPublicacion.setBounds(640, 185, 150, 20);
    datePublicacion.setBounds(775, 185, 150, 20);

    panelGenero.setBounds(350, 215, 575, 85);
    panelGenero.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

    lblGeneros.setBounds(256, 4, 52, 15);
    panelGenero.add(lblGeneros);

    ch1.setBounds(5, 20, 134, 20);
    ch2.setBounds(5, 40, 134, 20);
    ch3.setBounds(5, 60, 134, 20);
    ch4.setBounds(140, 20, 134, 20);
    ch5.setBounds(140, 40, 134, 20);
    ch6.setBounds(140, 60, 134, 20);
    ch7.setBounds(275, 20, 134, 20);
    ch8.setBounds(275, 40, 134, 20);
    ch9.setBounds(275, 60, 134, 20);
    ch10.setBounds(410, 20, 134, 20);
    ch11.setBounds(410, 40, 134, 20);
    ch12.setBounds(410, 60, 134, 20);

    lblResumen.setBounds(350, 310, 100, 15);
    txtASinopsis.setBounds(350, 325, 575, 255);
    txtASinopsis.setLineWrap(true);
    txtASinopsis.setWrapStyleWord(true);
    txtASinopsis.setEditable(true);

    ImageIcon portadaIco;

    btnAddLibro.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            registroLibro();
          }
        });

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(950, 635);
    setVisible(true);
  }

  public void registroLibro() {
    if (existeLibro() == false) {
      String isbn = txtISBN.getText();
      String titulo = txtTitlo.getText();
      String autor = txtAutor.getText();
      String saga = txtColeccion.getText();
      Integer tomo = (Integer) spNColeccion.getValue();
      Integer capitulos = (Integer) spCapitulos.getValue();
      /* Date fpublicacion = new Date();
      Date f_publicacion =
          new Date(
              fpublicacion.getDate()
                  + "/"
                  + (fpublicacion.getMonth() + 1)
                  + "/"
                  + (fpublicacion.getYear() + 1900));*/
      Date f_publicacion = datePublicacion.getDate();
      Date f_registroLibro = new Date();
      ArrayList<String> valoresCB = new ArrayList<String>();
      for (JCheckBox jCheckBox : jCheckBoxeA) {
        if (jCheckBox.isSelected()) {
          valoresCB.add(jCheckBox.getText());
        }
      }
      String resumen = txtASinopsis.getText();

      Document libro = new Document();
      // libro.put("Portada", archivo);
      libro.put("ISBN", isbn);
      libro.put("Titulo", titulo);
      libro.put("Autor", autor);
      libro.put("Saga", saga);
      libro.put("Tomo", tomo);
      libro.put("Capitulos", capitulos);
      libro.put("f_publicacion", f_publicacion);
      libro.put("Generos", valoresCB);
      libro.put("Sinopsis", resumen);
      libro.put("f_registro", f_registroLibro);
      collecLibros.insertOne(libro);
      mensajeEmergente(1);
    } else {
      mensajeEmergente(2);
      existe = false;
    }
  }

  public boolean existeLibro() {
    // Document doc = collecLibros.find(equ("ISBN", txtISBN)).first();
    // TODO El metodo de abajo es ineficiente
    List<Document> consulta = collecLibros.find().into(new ArrayList<Document>());
    for (int i = 0; i < consulta.size(); i++) {
      Document usuario = consulta.get(i);
      String ISBN = txtISBN.getText();
      if (ISBN.equals(usuario.getString("ISBN"))) {
        existe = true;
        break;
      }
    }
    return existe;
  }

  public void añadirComponentes(JComponent[] jComponentA) {

    for (JComponent jComponent : jComponentA) {
      panel.add(jComponent);
    }
    for (JCheckBox jCheckBox : jCheckBoxeA) {
      panelGenero.add(jCheckBox);
    }
  }

  public void añadirPortada() {
    btnAddPortada.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            FileNameExtensionFilter formato =
                new FileNameExtensionFilter("JPG y PNG,", "jpg", "png");
            fcAddPortada.setFileFilter(formato);
            int resultado = fcAddPortada.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == resultado) {
              archivo = fcAddPortada.getSelectedFile();

              try {
                byte[] imagebytes = Files.readAllBytes(archivo.toPath());
                ImageIcon portadaIco = new ImageIcon(archivo.toString());
                Icon icono =
                    new ImageIcon(
                        portadaIco
                            .getImage()
                            .getScaledInstance(
                                lblPortada.getWidth(),
                                lblPortada.getHeight(),
                                Image.SCALE_DEFAULT));
                lblPortada.setIcon(icono);
                lblPortada.setBorder(null);
              } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir: " + ex);
              }
            }
          }
        });
  }

  public void mensajeEmergente(int mensaje) {

    if (mensaje == 1) {
      JOptionPane.showMessageDialog(
          null,
          "Libro añadido Correctamente",
          "Registro Completado",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null, "Este Libro ya esta registrado", "Registro Fallido", JOptionPane.ERROR_MESSAGE);
    }
  }
}
