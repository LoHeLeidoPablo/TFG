package Libreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntfzPrincipal extends JFrame implements Interfaz {
  private static final long serialVersionUID = 1L;

  MenuUsuario menuUsuario = new MenuUsuario();
  InfoLibro infoLibro = new InfoLibro();

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://PabloBibTFG:7Infantes@biblioteca.w5wrr.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecLibro = DDBB.getCollection("Libro");

  JLabel lblportada1 = new JLabel("Portada1: 164 * 256");
  JLabel lblportada2 = new JLabel("Portada2: 164 * 256");
  JLabel lblportada3 = new JLabel("Portada3: 164 * 256");
  JLabel lblportada4 = new JLabel("Portada4: 164 * 256");
  JLabel lblportada5 = new JLabel("Portada5: 164 * 256");
  JLabel lblTitulo1 = new JLabel("Titulo Portada1");
  JLabel lblTitulo2 = new JLabel("Titulo Portada2");
  JLabel lblTitulo3 = new JLabel("Titulo Portada3");
  JLabel lblTitulo4 = new JLabel("Titulo Portada4");
  JLabel lblTitulo5 = new JLabel("Titulo Portada5");

  Document libro;

  JFrame jFramePrincipal = new JFrame();
  JPanel panel = new JPanel();
  JPanel[] jPanelA = {panel};
  JLabel[] jLabelA = {
    lblportada1,
    lblportada2,
    lblportada3,
    lblportada4,
    lblportada5,
    lblTitulo1,
    lblTitulo2,
    lblTitulo3,
    lblTitulo4,
    lblTitulo5
  };

  public IntfzPrincipal() {
    this.setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void iniciar() {
    setTitle("¿Lo he leído?");
    getContentPane().setLayout(new GridLayout(1, 10));
    MenuUsuario menuUsuario = new MenuUsuario(panel, this);
    PanelBusqueda panelBusqueda = new PanelBusqueda(panel);
    crearComponentes();
    panel.setLayout(null);
    for (JLabel jLabel : jLabelA) {
      irLibro(jLabel);
    }
    ultimosAgregados();

    lblportada1.setBounds(75, 100, 250, 467);
    lblportada2.setBounds(375, 100, lblportada1.getWidth(), lblportada1.getHeight());
    lblportada3.setBounds(675, 100, lblportada1.getWidth(), lblportada1.getHeight());
    lblportada4.setBounds(975, 100, lblportada1.getWidth(), lblportada1.getHeight());
    lblportada5.setBounds(1275, 100, lblportada1.getWidth(), lblportada1.getHeight());
    lblTitulo1.setBounds(
        lblportada1.getX(),
        lblportada1.getY() + lblportada1.getHeight() + 20,
        lblportada1.getWidth(),
        20);
    lblTitulo2.setBounds(
        lblportada2.getX(),
        lblportada2.getY() + lblportada1.getHeight() + 20,
        lblportada2.getWidth(),
        20);
    lblTitulo3.setBounds(
        lblportada3.getX(),
        lblportada3.getY() + lblportada1.getHeight() + 20,
        lblportada3.getWidth(),
        20);
    lblTitulo4.setBounds(
        lblportada4.getX(),
        lblportada4.getY() + lblportada1.getHeight() + 20,
        lblportada4.getWidth(),
        20);
    lblTitulo5.setBounds(
        lblportada5.getX(),
        lblportada5.getY() + lblportada1.getHeight() + 20,
        lblportada5.getWidth(),
        20);

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(1600, 1000);
    setVisible(true);
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, jLabelA, null, null, null, null, null);
  }

  public void crearComponentes() {
    for (JLabel jLabel : jLabelA) {
      panel.add(jLabel);
      jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
  }

  public void ultimosAgregados() {
    MongoCursor<Document> ultimoAgregados =
        collecLibro.find().sort(descending("f_registro")).projection(include("Titulo")).iterator();

    for (int i = 0; i < 5; i++) {
      while (ultimoAgregados.hasNext()) {
        var titulos = ultimoAgregados.next();
        jLabelA[i].setText(titulos.get("Titulo").toString());
        jLabelA[i + 5].setText(titulos.get("Titulo").toString());
        break;
      }
    }
  }

  public void irLibro(JLabel jLabel) {
    jLabel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            libro = collecLibro.find(eq("Titulo", jLabel.getText())).first();
            infoLibro.dispose();
            infoLibro.iniciar(libro);
          }
        });
  }
}
