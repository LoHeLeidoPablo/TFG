package Libreria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class PanelBusqueda extends JFrame implements Interfaz {
  private JPanel panelBusqueda;
  private JComboBox<String> tipoBusqueda;
  private JTextField txtBusqueda;
  private JLabel lblnewBook =
      new JLabel(
          "No encunetra el libro que busca en nuestra Biblioteca, pulse aqui para añadirlo.");
  ;
  private boolean visible = true;

  private JPanel[] jPanelA = {panelBusqueda};
  private JTextField[] jTextFieldA = {txtBusqueda};
  Font font = lblnewBook.getFont();
  Libreria.RegistrarLibro registrarLibro = new Libreria.RegistrarLibro();

  public PanelBusqueda(JPanel jpanel) {

    panelBusqueda = new JPanel();
    panelBusqueda.setBounds(330, 60, 1000, 500);
    panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    panelBusqueda.setLayout(null);
    panelBusqueda.hide();
    jpanel.add(panelBusqueda);

    txtBusqueda = new JTextField("Buscar Titulo,Autor,Serie...");
    txtBusqueda.setBounds(330, 20, 1000, 30);
    jpanel.add(txtBusqueda);
    txtBusqueda.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panelBusqueda.show();
            panelBusqueda.setVisible(visible);
            visible = !visible;
          }
        });

    lblnewBook.setBounds(200, 475, 600, 20);
    lblnewBook.setForeground(Color.blue);
    panelBusqueda.add(lblnewBook);
    lblnewBook.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            panelBusqueda.setVisible(false);
            dispose();
            registrarLibro.iniciar();
          }

          @Override
          public void mouseEntered(MouseEvent e) {
            lblnewBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Font subrayado = lblnewBook.getFont();
            Map attributes = subrayado.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            lblnewBook.setFont(subrayado.deriveFont(attributes));
          }

          @Override
          public void mouseExited(MouseEvent e) {
            lblnewBook.setFont(font);
          }
        });
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, null, jTextFieldA, null, null, null, null);
  } // No Funciona
}
