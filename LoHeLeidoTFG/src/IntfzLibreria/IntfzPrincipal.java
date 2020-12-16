package IntfzLibreria;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class IntfzPrincipal extends JFrame implements Interfaz {

  MenuUsuario menuUsuario;
  PanelBusqueda panelBusqueda;
  IntfzInfoLibro intfzInfoLibro = new IntfzInfoLibro();

  MongoClientURI uri =
      new MongoClientURI(
          "mongodb+srv://AdminUser:iReadIt@loheleido.idhnu.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

  MongoClient mongoClient = new MongoClient(uri);
  MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
  MongoCollection<Document> collecLibro = DDBB.getCollection("Libro");

  JLabel lblportada1 = new JLabel("Portada1: 164 * 256");
  JLabel lblportada2 = new JLabel("Portada2: 164 * 256");
  JLabel lblportada3 = new JLabel("Portada3: 164 * 256");
  JLabel lblportada4 = new JLabel("Portada4: 164 * 256");
  JLabel lblportada5 = new JLabel("Portada5: 164 * 256");
  JLabel lblportada6 = new JLabel("Portada6: 164 * 256");
  JLabel lblportada7 = new JLabel("Portada7: 164 * 256");
  JLabel lblportada8 = new JLabel("Portada8: 164 * 256");
  JLabel lblportada9 = new JLabel("Portada9: 164 * 256");
  JLabel lblportada10 = new JLabel("Portada10: 164 * 256");
  JLabel lblportada11 = new JLabel("Portada11: 164 * 256");
  JLabel lblportada12 = new JLabel("Portada12: 164 * 256");

  JLabel lblTitulo1 = new JLabel("Titulo Portada1");
  JLabel lblTitulo2 = new JLabel("Titulo Portada2");
  JLabel lblTitulo3 = new JLabel("Titulo Portada3");
  JLabel lblTitulo4 = new JLabel("Titulo Portada4");
  JLabel lblTitulo5 = new JLabel("Titulo Portada5");
  JLabel lblTitulo6 = new JLabel("Titulo Portada6");
  JLabel lblTitulo7 = new JLabel("Titulo Portada7");
  JLabel lblTitulo8 = new JLabel("Titulo Portada8");
  JLabel lblTitulo9 = new JLabel("Titulo Portada9");
  JLabel lblTitulo10 = new JLabel("Titulo Portada10");
  JLabel lblTitulo11 = new JLabel("Titulo Portada11");
  JLabel lblTitulo12 = new JLabel("Titulo Portada12");


  Document libro;

  JPanel panel = new JPanel();
  JPanel[] jPanelA = {panel};
  JLabel[] jLabelA = {
      lblportada1,
      lblportada2,
      lblportada3,
      lblportada4,
      lblportada5,
      lblportada6,
      lblportada7,
      lblportada8,
      lblportada9,
      lblportada10,
      lblportada11,
      lblportada12,
      lblTitulo1,
      lblTitulo2,
      lblTitulo3,
      lblTitulo4,
      lblTitulo5,
      lblTitulo6,
      lblTitulo7,
      lblTitulo8,
      lblTitulo9,
      lblTitulo10,
      lblTitulo11,
      lblTitulo12
  };

  public IntfzPrincipal() {
    this.setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void iniciar() {
    setTitle("¿Lo he leído?");
    getContentPane().setLayout(new GridLayout(1, 10));
    menuUsuario = new MenuUsuario(panel, this);
    panelBusqueda = new PanelBusqueda(panel);
    crearComponentes();
    panel.setLayout(null);
    for (JLabel jLabel : jLabelA) {
      irLibro(jLabel);
    }
    ultimosAgregados();

    lblportada1.setBounds(37, 100, 210, 332);
    lblTitulo1.setBounds(lblportada1.getX(), lblportada1.getY() + lblportada1.getHeight() + 25, lblportada1.getWidth(), 25);
    lblportada2.setBounds(lblportada1.getX() + lblportada1.getWidth() + 50, lblportada1.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada3.setBounds(lblportada2.getX() + lblportada1.getWidth() + 50, lblportada1.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada4.setBounds(lblportada3.getX() + lblportada1.getWidth() + 50, lblportada1.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada5.setBounds(lblportada4.getX() + lblportada1.getWidth() + 50, lblportada1.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada6.setBounds(lblportada5.getX() + lblportada1.getWidth() + 50, lblportada1.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada7.setBounds(lblportada1.getX(), lblTitulo1.getY() + lblTitulo1.getHeight() + 50, lblportada1.getWidth(), lblportada1.getHeight());
    lblportada8.setBounds(lblportada2.getX(), lblportada7.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada9.setBounds(lblportada3.getX(), lblportada7.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada10.setBounds(lblportada4.getX(), lblportada7.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada11.setBounds(lblportada5.getX(), lblportada7.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblportada12.setBounds(lblportada6.getX(), lblportada7.getY(), lblportada1.getWidth(), lblportada1.getHeight());
    lblTitulo2.setBounds(lblportada2.getX(), lblTitulo1.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo3.setBounds(lblportada3.getX(), lblTitulo1.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo4.setBounds(lblportada4.getX(), lblTitulo1.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo5.setBounds(lblportada5.getX(), lblTitulo1.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo6.setBounds(lblportada6.getX(), lblTitulo1.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo7.setBounds(lblportada7.getX(), lblportada7.getY() + lblportada7.getHeight() + 25, lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo8.setBounds(lblportada8.getX(), lblTitulo7.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo9.setBounds(lblportada9.getX(), lblTitulo7.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo10.setBounds(lblportada10.getX(), lblTitulo7.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo11.setBounds(lblportada11.getX(), lblTitulo7.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());
    lblTitulo12.setBounds(lblportada12.getX(), lblTitulo7.getY(), lblportada1.getWidth(), lblTitulo1.getHeight());

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(1600, 1000);
    setVisible(true);
  }

  public void ultimosAgregados() {
    MongoCursor<Document> ultimoAgregados =
        collecLibro
            .find()
            .sort(descending("f_registro"))
            .projection(include("Titulo"))
            .limit(12)
            .iterator();
    int pos = -1;
    while (ultimoAgregados.hasNext()) {
      pos++;
      Document titulos = ultimoAgregados.next();
      jLabelA[pos].setText(titulos.get("Titulo").toString());
      jLabelA[pos + 12].setText(titulos.get("Titulo").toString());
    }
  }

  public void irLibro(JLabel jLabel) {
    jLabel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            if (panelBusqueda.panelBusqueda.isVisible() == false) {
            libro = collecLibro.find(eq("Titulo", jLabel.getText())).first();
            intfzInfoLibro.dispose();
            intfzInfoLibro.iniciar(libro);
            }
          }
        });
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
}