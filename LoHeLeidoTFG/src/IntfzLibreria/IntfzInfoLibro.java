package IntfzLibreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static IntfzLibreria.IntfzLogin.id_Usuario;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IntfzInfoLibro extends JFrame implements Interfaz {

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://AdminUser:iReadIt@loheleido.idhnu.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecLibro = DDBB.getCollection("Libro");
  MongoCollection<Document> collecDetLibro = DDBB.getCollection("DetallesPrestamo");
  MongoCollection<Document> collecUsuario = DDBB.getCollection("Usuario");

  MongoCursor<Document> coleccion;

  IntfzActualizarLibro intfzActualizarLibro = new IntfzActualizarLibro();

  JPanel panel = new JPanel();
  JPanel panelGenero = new JPanel();
  JPanel panelTecnico = new JPanel();
  JPanel panelEstado = new JPanel();
  JPanel panelEntregas = new JPanel();
  JTabbedPane tabbed = new JTabbedPane();

  JLabel lblPortada = new JLabel("Portada");
  JLabel lblTitlo = new JLabel("Titulo");
  JLabel lblAutor = new JLabel("Autor");
  JLabel lblGeneros = new JLabel("Genero");
  JLabel lblResumen = new JLabel("Resumen");
  JTextArea txtASinopsis = new JTextArea(loreIpsum());

  JCheckBox ch1 = new JCheckBox("Aventuras");
  JCheckBox ch2 = new JCheckBox("Autobiografía");
  JCheckBox ch3 = new JCheckBox("Ciencia Ficcion");
  JCheckBox ch4 = new JCheckBox("Fantasia");
  JCheckBox ch5 = new JCheckBox("Historica");
  JCheckBox ch6 = new JCheckBox("Infantil");
  JCheckBox ch7 = new JCheckBox("Romantica");
  JCheckBox ch8 = new JCheckBox("Misterio");
  JCheckBox ch9 = new JCheckBox("Negra");
  JCheckBox ch10 = new JCheckBox("Policiaca");
  JCheckBox ch11 = new JCheckBox("Comic/Manga");
  JCheckBox ch12 = new JCheckBox("Otros");
  List<Document> lstGeneros;

  JLabel lblISBN = new JLabel("ISBN: ");
  JLabel lblCapitulos = new JLabel("Capitulos:");
  JLabel lblColeccion = new JLabel("Saga: ");
  JLabel lblPublicacion = new JLabel("Fecha Publicacion:");

  JLabel lblEstado = new JLabel("Estado:");
  JLabel lblVistos = new JLabel("Caps leídos:");
  JLabel lblNota = new JLabel("Nota:");

  String[] estados = {"Sin Añadir", "Leyendo", "Leído", "Abandonado", "Quiero Leer"};
  JComboBox<String> cbEstados = new JComboBox<String>(estados);
  int valMaximio = 50;
  JSpinner spCapL = new JSpinner(new SpinnerNumberModel(0, 0, valMaximio, 1));
  JLabel lblCapTotales = new JLabel("/" + valMaximio);
  JSpinner spNota = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
  JButton btnUpdate = new JButton("Actualizar");

  Font fTitulo = new Font("Console", Font.BOLD, 40);
  Font fAutor = new Font("Console", Font.ITALIC, 30);
  Font fResumen = new Font("Console", Font.PLAIN, 20);
  Font fTResumen = new Font("Console", Font.PLAIN, 14);

  JButton btnPrestamo = new JButton("Prestar");
  JButton btnUpdateLibro = new JButton("Actualizar Libro");

  JList<String> listasecuelas = new JList<String>();
  DefaultListModel dlm = new DefaultListModel();
  JScrollPane scrollPane = new JScrollPane(listasecuelas);

  String colecc = new String();
  String isbn = new String();

  JPanel[] jPanelA = {panel, panelGenero, panelTecnico, panelEstado, panelEntregas};
  JLabel[] jLabelA = {
      lblPortada,
      lblTitlo,
      lblAutor,
      lblResumen,
      lblGeneros,
      lblPublicacion,
      lblISBN,
      lblCapitulos,
      lblColeccion,
      lblPublicacion,
      lblEstado,
      lblVistos,
      lblNota,
      lblCapTotales
  };
  JCheckBox[] jCheckBoxA = {ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12};
  JButton[] jButtonA = {btnPrestamo, btnUpdateLibro};
  JComponent[] jCompPprincipalA = {
      lblPortada, lblTitlo, lblAutor, panelGenero, lblResumen, txtASinopsis, tabbed
  };
  JComponent[] jCompPtecnicoA = {lblISBN, lblCapitulos, lblColeccion, lblPublicacion};
  JComponent[] jCompPestadoA = {
      lblEstado, lblVistos, lblNota, cbEstados, spCapL, lblCapTotales, spNota, btnUpdate
  };

  public IntfzInfoLibro() {
    this.setResizable(false);
    cambiarTomo();
    prestarLibro();
  }

  public void iniciar(Document libro) {
    if (libro != null) {
      colecc = libro.getString("Saga");
      isbn = libro.getString("ISBN");
      mostrarInfoLibro(libro);
    }

    getContentPane().setLayout(new GridLayout(1, 15));
    crearComponentes();
    panel.setLayout(null);
    panelGenero.setLayout(null);
    panelTecnico.setLayout(null);
    panelEstado.setLayout(null);
    panelEntregas.setLayout(null);

    lblPortada.setBounds(10, 30, 329, 512);
    lblTitlo.setBounds(350, 55, 950, 45);
    lblAutor.setBounds(350, 115, 575, 35);
    lblResumen.setBounds(350, 260, 100, 20);
    btnPrestamo.setBounds(10, 550, 329, 30);
    if (id_Usuario.equals("Admin")) {
      btnPrestamo.setBounds(10, 550, 150, 30);
      btnUpdateLibro.setBounds(190, 550, 150, 30);
      panel.add(btnUpdateLibro);
    }

    btnUpdateLibro.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            intfzActualizarLibro.iniciar(libro);
          }
        });

    lblTitlo.setFont(fTitulo);
    lblAutor.setFont(fAutor);
    lblResumen.setFont(fResumen);

    panelGenero.setBounds(350, 160, 575, 85);
    lblGeneros.setBounds(256, 4, 52, 15);

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

    txtASinopsis.setBounds(350, 285, 950, 257);
    txtASinopsis.setLineWrap(true);
    txtASinopsis.setWrapStyleWord(true);
    txtASinopsis.setEditable(false);
    txtASinopsis.setBackground(panel.getBackground());
    txtASinopsis.setFont(fTResumen);

    lblPortada.setBorder(BorderFactory.createLineBorder(Color.black));
    panelGenero.setBorder(BorderFactory.createLineBorder(Color.darkGray));

    tabbed.setBounds(950, 135, 350, 125);
    tabbed.addTab("Ficha Tecnica", panelTecnico);
    if (id_Usuario.equals("Invitado")) {
    } else {
      tabbed.addTab("Estado", panelEstado);
      panel.add(btnPrestamo);
    }
    tabbed.addTab("Misma Coleccion", panelEntregas);

    panelTecnico.setBounds(0, 0, tabbed.getWidth(), tabbed.getHeight());
    lblISBN.setBounds(10, 10, 420, 15);
    lblCapitulos.setBounds(10, 30, 420, 15);
    lblColeccion.setBounds(10, 50, 420, 15);
    lblPublicacion.setBounds(10, 70, 420, 15);

    panelEstado.setBounds(panelTecnico.getBounds());
    lblEstado.setBounds(10, 10, 55, 20);
    cbEstados.setBounds(70, 10, 130, 20);
    lblVistos.setBounds(10, 40, 95, 20);
    spCapL.setBounds(110, 40, 50, 20);
    lblCapTotales.setBounds(160, 40, 50, 20);
    lblNota.setBounds(10, 70, 50, 20);
    spNota.setBounds(60, 70, 50, 20);
    btnUpdate.setBounds(210, 50, 125, 40);

    panelEntregas.setBounds(panelTecnico.getBounds());

    scrollPane.setBounds(0, 0, panelEntregas.getWidth(), panelEntregas.getHeight() - 25);
    listasecuelas.setBounds(0, 0, scrollPane.getWidth(), scrollPane.getHeight());

    scrollPane.setBackground(panel.getBackground());
    listasecuelas.setBackground(panel.getBackground());

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(1350, 650);
    setVisible(true);
  }

  public void mostrarInfoLibro(Document libro) {
    lblISBN.setText("ISBN: " + libro.getString("ISBN"));
    lblTitlo.setText(libro.getString("Titulo"));

    if (lblTitlo == null) {
      setTitle("Info Libro");
    } else {
      setTitle(lblTitlo.getText());
    }

    lblAutor.setText(libro.getString("Autor"));
    lstGeneros = (List<Document>) libro.get("Generos");
    for (int k = 0, i = 0; k < jCheckBoxA.length; k++) {
      if (lstGeneros.contains(jCheckBoxA[k].getText())) {
        jCheckBoxA[k].setSelected(true);
        i++;
        if (i == lstGeneros.size()) break;
      }
    }
    txtASinopsis.setText(libro.getString("Sinopsis"));
    lblColeccion.setText("Saga: " + libro.getString("Saga") + "  " + libro.getInteger("Tomo"));
    lblCapitulos.setText("Capitulos: " + libro.getInteger("Capitulos"));
    if (libro.getDate("f_publicacion") != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("dd - MMMM - yyyy");
      lblPublicacion.setText("Fecha de Publicación: " + sdf.format(libro.getDate("f_publicacion")));
    }
    if (libro.getInteger("Capitulos") != null) {
    valMaximio = libro.getInteger("Capitulos");
    spCapL.setModel(new SpinnerNumberModel(0, 0, valMaximio, 1));
    lblCapTotales.setText("/" + valMaximio);
  }else{
      spCapL.setModel(new SpinnerNumberModel(0, 0, 999, 1));
      lblCapTotales.setText("/???");
    }
    dlm.clear();
    coleccion =
        collecLibro
            .find(eq("Saga", libro.getString("Saga")))
            .sort(ascending("Tomo"))
            .projection(include("Titulo", "Tomo"))
            .iterator();
    while (coleccion.hasNext()) {
      Document libroColec = coleccion.next();
      String collecNume =
          "Libro " + libroColec.getInteger("Tomo") + ":  " + libroColec.getString("Titulo");
      dlm.addElement(collecNume);
    }
    listasecuelas.setModel(dlm);
    isbn = libro.getString("ISBN");
  }

  public void cambiarTomo() {
    listasecuelas.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent evt) {
            listasecuelas = (JList) evt.getSource();
            if (evt.getClickCount() == 2) {
              int index = listasecuelas.locationToIndex(evt.getPoint());
              int i = 0;
              MongoCursor<Document> otroLibro =
                  collecLibro.find(eq("Saga", colecc)).sort(ascending("Tomo")).iterator();
              while (otroLibro.hasNext()) {
                Document libroColec = otroLibro.next();
                if (i == index) {
                  mostrarInfoLibro(libroColec);
                  break;
                }
                i++;
              }
            }
            return;
          }
        });
  }

  public void prestarLibro() {
    btnPrestamo.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Document usuario = collecUsuario.find(eq("Nombre", id_Usuario)).first();
            if (usuario.getInteger("NPrestados") < 5) {
              Calendar calc_fecha = Calendar.getInstance();
              calc_fecha.setTime(new Date());
              calc_fecha.set(Calendar.DAY_OF_YEAR, calc_fecha.get(Calendar.DAY_OF_YEAR) + 30);
              Date f_devolucion = calc_fecha.getTime();
              try {
                Document prestamo = new Document();
                prestamo.put("ISBN", isbn);
                prestamo.put("emailUsu", usuario.getString("Email"));
                prestamo.put("f_prestamo", new Date());
                prestamo.put("f_devolucion", f_devolucion);
                prestamo.put("Prestado", true);
                Document comproPrestamo =
                    collecDetLibro
                        .find(
                            and(
                                eq("emailUsu", prestamo.getString("emailUsu")),
                                eq("ISBN", prestamo.getString("ISBN")),
                                eq("Prestado", true)))
                        /*.sort(descending("f_prestamo"))*/
                        .first();
                if (comproPrestamo == null) {
                  collecDetLibro.insertOne(prestamo);
                  collecUsuario.updateOne(
                      eq("Email", usuario.getString("Email")),
                      set("NPrestados", usuario.getInteger("NPrestados") + 1));
                  mensajeEmergente(1);
                } else {
                  mensajeEmergente(4);
                }
              } catch (Exception exception) {
              }

            } else {
              mensajeEmergente(2);
            }
          }
        });
  }

  public void mensajeEmergente(int mensaje) {
    if (mensaje == 1) {
      JOptionPane.showMessageDialog(
          null, "Libro Prestado Correctamente", "Libro Prestado", JOptionPane.INFORMATION_MESSAGE);
    }
    if (mensaje == 2) {
      JOptionPane.showMessageDialog(
          null,
          "Este usuario ya ha tomado prestado el limite maximo de 5 libros simultaneos ",
          "Advertencia",
          JOptionPane.INFORMATION_MESSAGE);
    }
    if (mensaje == 3) {
      JOptionPane.showMessageDialog(
          null,
          "El Prestamo no ha podido realizarse correctamente, por favor vuelva a intentarlo",
          "Prestamo Fallido",
          JOptionPane.INFORMATION_MESSAGE);
    }
    if (mensaje == 4) {
      JOptionPane.showMessageDialog(
          null,
          "Este Usuario ya tiene este libro en prestamo",
          " Ya Prestado ",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public String loreIpsum() {
    return " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris lacinia porttitor libero in commodo. Integer erat metus, condimentum ut mattis quis, pretium sit amet orci. Aenean faucibus eu lacus eget iaculis. Aenean placerat ultrices suscipit. Etiam venenatis nulla ut pharetra scelerisque. Suspendisse scelerisque efficitur elit, id consequat ex tempus at. Nam odio erat, gravida at ante in, pellentesque porttitor ante. Maecenas semper a turpis et euismod.\n"
        + "\n"
        + "Integer sed ultricies sapien. Etiam scelerisque justo at dapibus gravida. Vestibulum sit amet nisi elit. Nam scelerisque magna nibh, feugiat dictum magna blandit at. Praesent dictum mi nec fermentum consequat. Maecenas molestie urna et lorem auctor mollis vitae mollis eros. Phasellus interdum tortor sed venenatis porta. Mauris hendrerit quis neque id aliquet. In a scelerisque urna, in fringilla tortor. Fusce in sodales enim, id iaculis leo. Nulla in laoreet urna, et sodales sem. ";
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, jLabelA, null, jButtonA, null, null, txtASinopsis);
  }

  public void crearComponentes() {
    for (JComponent jComponent : jCompPprincipalA) {
      panel.add(jComponent);
    }
    for (JCheckBox jCheckBox : jCheckBoxA) {
      panelGenero.add(jCheckBox);
      jCheckBox.setEnabled(false);
    }
    panelGenero.add(lblGeneros);
    for (JComponent jComponent : jCompPtecnicoA) {
      panelTecnico.add(jComponent);
    }
    for (JComponent jComponent : jCompPestadoA) {
      panelEstado.add(jComponent);
    }
    panelEntregas.add(scrollPane);
  }
}