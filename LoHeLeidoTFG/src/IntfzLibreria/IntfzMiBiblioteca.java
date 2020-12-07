package IntfzLibreria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class IntfzMiBiblioteca extends JFrame implements Interfaz {

  JPanel panel = new JPanel();
  JPanel[] jPanelA = {panel};

  JTable myBibiblioTable;
  DefaultTableModel modelT;
  JScrollPane scrollPane;

  public IntfzMiBiblioteca() {
    this.setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void iniciar() {
    setTitle("¿Lo he leído? - Mi IntfzLibreria");
    getContentPane().setLayout(new GridLayout(1, 10));
    MenuUsuario menuUsuario = new MenuUsuario(panel, this);
    PanelBusqueda panelBusqueda = new PanelBusqueda(panel);

    panel.setLayout(null);

    // JTable MyBibilio
    String[] cabecera = {"Estado", "Conteo", "Titulo", "Capitulos"};
    modelT = new DefaultTableModel(cabecera, 0);
    myBibiblioTable = new JTable(modelT);
    myBibiblioTable.setBounds(200, 100, 1200, 825);
    myBibiblioTable.getColumnModel().getColumn(0).setMaxWidth(10);
    for (int i = 1; i >= cabecera.length; i++) {

      myBibiblioTable
          .getColumnModel()
          .getColumn(i)
          .setMaxWidth(myBibiblioTable.getWidth() / cabecera.length);
    }

    for (int i = 0; i < 120; i++) {
      Object[] aux = {"Leyendo", i, "Titulo", "Numero/300"};
      modelT.addRow(aux);
    }

    // Scroll Pane
    scrollPane = new JScrollPane(myBibiblioTable);
    scrollPane.setBounds(200, 100, 1200, 825);
    panel.add(scrollPane);

    getContentPane().add(panel);

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(1600, 1000);
    setVisible(true);
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, null, null, null, myBibiblioTable, null, null);
  }

  public void crearComponentes(){}
}
