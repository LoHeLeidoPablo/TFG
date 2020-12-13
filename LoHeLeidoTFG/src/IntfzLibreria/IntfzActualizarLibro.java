package IntfzLibreria;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.toedter.calendar.JDateChooser;
import org.bson.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;

public class IntfzActualizarLibro extends JFrame {

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://AdminUser:iReadIt@loheleido.idhnu.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");
  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecLibros = DDBB.getCollection("Libro");

  private JPanel panel = new JPanel();
  private JPanel panelGenero = new JPanel();

  private JLabel lblPortada = new JLabel("Portada: Tamaño Estandar --> 329 * 512");
  private JLabel lblISBN = new JLabel("ISBN");
  private JLabel lblTitlo = new JLabel("Titulo");
  private JLabel lblAutor = new JLabel("Autor");
  private JLabel lblColeccion = new JLabel("Saga");
  private JLabel lblNColeccion = new JLabel("Tomo");
  private JLabel lblPaginas = new JLabel("Paginas");
  private JLabel lblCapitulos = new JLabel("Capitulos");
  private JLabel lblPublicacion = new JLabel("F. Publicación");
  private JLabel lblGeneros = new JLabel("Genero");
  private JLabel lblResumen = new JLabel("Resumen");

  private JTextField txtTitlo = new JTextField();
  private JTextField txtAutor = new JTextField();
  private JTextArea txtASinopsis = new JTextArea();
  private JTextField txtGeneros = new JTextField();
  private JTextField txtISBN = new JTextField();
  private JTextField txtColeccion = new JTextField();
  private JSpinner spCapitulos = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
  private JSpinner spPaginas = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
  private JSpinner spNColeccion = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

  private JCheckBox ch1 = new JCheckBox("Aventuras");
  private JCheckBox ch2 = new JCheckBox("Autobiografía");
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
  private JButton btnAddLibro = new JButton("Actualizar Libro");
  private JFileChooser fcAddPortada = new JFileChooser();
  private JDateChooser datePublicacion = new JDateChooser();

  Font fuente = new Font(lblGeneros.getFont().getFamily(), Font.BOLD, 12);
  File archivo;
  JCheckBox[] jCheckBoxA = {ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12};
  Boolean existe = false;
  String isbn = new String();

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

  public IntfzActualizarLibro() {
    this.setResizable(false);
  }

  public void iniciar(Document actualizarLibro) {
    setTitle("Registrar libro - ¿Lo he leído?");
    getContentPane().setLayout(new GridLayout(1, 10));
    crearComponentes();
    if (actualizarLibro != null)
      ;
    mostrarInfoLibro(actualizarLibro);

    panel.setLayout(null);
    panelGenero.setLayout(null);

    lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
    lblPortada.setBorder(BorderFactory.createLineBorder(Color.black));
    lblPortada.setBounds(10, 30, 329, 512);
    btnAddLibro.setBounds(10, 550, 160, 30);
    btnAddPortada.setBounds(179, 550, 160, 30);

    lblISBN.setBounds(350, 45, 50, 20);
    txtISBN.setBounds(400, 45, 525, 20);
    lblTitlo.setBounds(350, 80, 50, 20);
    txtTitlo.setBounds(400, 80, 525, 20);
    lblAutor.setBounds(350, 115, 50, 20);
    txtAutor.setBounds(400, 115, 525, 20);
    lblColeccion.setBounds(350, 150, 50, 20);
    txtColeccion.setBounds(400, 150, 525, 20);

    lblNColeccion.setBounds(350, 185, 50, 20);
    spNColeccion.setBounds(400, 185, 45, 20);
    lblPaginas.setBounds(460, 185, 50, 20);
    spPaginas.setBounds(520, 185, 50, 20);
    lblCapitulos.setBounds(585, 185, 53, 20);
    spCapitulos.setBounds(650, 185, 45, 20);
    lblPublicacion.setBounds(715, 185, 100, 20);
    datePublicacion.setBounds(810, 185, 115, 20);

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

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(950, 635);
    setVisible(true);
  }

  public void mostrarInfoLibro(Document libro) {
    if (lblTitlo == null) {
      setTitle("Interfaz de Actualización");
    } else {
      setTitle("Actualizar: " + lblTitlo.getText());
    }

    txtISBN.setText(libro.getString("ISBN"));
    txtTitlo.setText(libro.getString("Titulo"));
    txtAutor.setText(libro.getString("Autor"));
    txtColeccion.setText(libro.getString("Saga"));
    spNColeccion.setValue(libro.getInteger("Tomo"));
    spCapitulos.setValue(libro.getInteger("Capitulos"));
    datePublicacion.setDate(libro.getDate("f_publicacion"));
    List<Document> lstGeneros = (List<Document>) libro.get("Generos");
    for (int k = 0, i = 0; k < jCheckBoxA.length; k++) {
      if (lstGeneros.contains(jCheckBoxA[k].getText())) {
        jCheckBoxA[k].setSelected(true);
        i++;
        if (i == lstGeneros.size()) break;
      }
    }
    txtASinopsis.setText(libro.getString("Sinopsis"));
    isbn = libro.getString("ISBN");
  }

  public void actualizarLibro() {
    DeleteResult del = collecLibros.deleteOne(eq("ISBN", isbn));
    ArrayList<String> valoresCB = new ArrayList<String>();
    for (JCheckBox jCheckBox : jCheckBoxA) {
      if (jCheckBox.isSelected()) {
        valoresCB.add(jCheckBox.getText());
      }
    }
    String resumen = txtASinopsis.getText();
    try {
      Document libro = new Document();
      // guardarPortada();
      libro.put("ISBN", txtISBN.getText());
      libro.put("Titulo", txtTitlo.getText());
      libro.put("Autor", txtAutor.getText());
      libro.put("Saga", txtColeccion.getText());
      libro.put("Tomo", (Integer) spNColeccion.getValue());
      libro.put("Capitulos", (Integer) spCapitulos.getValue());
      libro.put("f_publicacion", datePublicacion.getDate());
      libro.put("Generos", valoresCB);
      libro.put("Sinopsis", txtASinopsis.getText());
      libro.put("f_registro", new Date());
      collecLibros.insertOne(libro);
      mensajeEmergente(1);
    } catch (Exception e) {
      mensajeEmergente(2);
    }
  }

/*
  public boolean obligatorios() {
    if (txtISBN.getText().isEmpty()) {}
    if (txtAutor.getText().isEmpty()) {}
    if (txtColeccion.getText().isEmpty()) {}

    // if(spNColeccion.getValue().equals(0));
    // if(spNColeccion.getValue().equals(0));
    return true;
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

  public void guardarPortada() {
    try {
      Mongo mongo =
          new Mongo(
               "mongodb+srv://AdminUser:iReadIt@loheleido.idhnu.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");
      DB db = mongo.getDB("LoHeLeidoDB");
      DBCollection collection = db.getCollection("Libros");
      String newFileName = txtTitlo.getText();

      // create a "photo" namespace
      GridFS gfsPhoto = new GridFS(db, "portada");

      // get image file from local drive
      GridFSInputFile gfsFile = gfsPhoto.createFile(archivo);

      // set a new filename for identify purpose
      gfsFile.setFilename(newFileName);

      // save the image file into mongoDB
      gfsFile.save();
    } catch (Exception ex) {
    }
  }*/

  public void crearComponentes() {

    for (JComponent jComponent : jComponentA) {
      panel.add(jComponent);
    }
    for (JCheckBox jCheckBox : jCheckBoxA) {
      panelGenero.add(jCheckBox);
    }
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