package Libreria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntfzPrincipal extends JFrame implements Interfaz {
  private static final long serialVersionUID = 1L;

  MenuUsuario menuUsuario = new MenuUsuario();
  InfoLibro infoLibro = new InfoLibro();

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
    MenuUsuario menuUsuario = new MenuUsuario(panel,  this);
    PanelBusqueda panelBusqueda = new PanelBusqueda(panel);
    crearComponentes();
    panel.setLayout(null);
    irLibro(lblportada1);
    irLibro(lblTitulo1);

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

  public void cerrarVentana() {
    jFramePrincipal.dispose();
    panel.repaint();
    panel.updateUI();
  }

  public void crearComponentes() {
    for (JLabel jLabel : jLabelA) {
      panel.add(jLabel);
      jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
  }

  public void irLibro(JComponent jComponent) {
    jComponent.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            infoLibro.iniciar();
          }
        });
  }
}
