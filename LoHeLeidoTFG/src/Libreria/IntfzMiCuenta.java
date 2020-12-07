package Libreria;

import javax.swing.*;
import java.awt.*;

public class IntfzMiCuenta extends JFrame implements Interfaz {
  private static final long serialVersionUID = 1L;

  // JFrame jFrameCuenta = new JFrame();
  JPanel panel = new JPanel();
  JPanel panelPrestamo = new JPanel();
  JPanel panelPrestamo1 = new JPanel();
  JPanel panelPrestamo2 = new JPanel();
  JPanel panelPrestamo3 = new JPanel();
  JPanel panelPrestamo4 = new JPanel();
  JPanel panelPrestamo5 = new JPanel();
  JPanel panelEstadisticas = new JPanel();

  JLabel lblPortada1 = new JLabel("Portada");
  JLabel lblTitulo1 = new JLabel("La Sombra del Zorro");
  JLabel lblDiasRestantes1 = new JLabel("Te Quedan " + 15 + " dias");
  // JLabel[] lblA1 = {lblPortada1, lblTitulo1, lblDiasRestantes1};

  JLabel lblPortada2 = new JLabel("Portada");
  JLabel lblTitulo2 = new JLabel("El Ladrón del Rayo");
  JLabel lblDiasRestantes2 = new JLabel("Te Quedan " + 3 + " dias");
  // JLabel[] lblA2 = {lblPortada2, lblTitulo2, lblDiasRestantes2};

  JLabel lblPortada3 = new JLabel("Portada");
  JLabel lblTitulo3 = new JLabel("El Hechicero");
  JLabel lblDiasRestantes3 = new JLabel("Te Quedan " + 10 + " dias");
  // JLabel[] lblA3 = {lblPortada3, lblTitulo3, lblDiasRestantes3};

  JLabel lblPortada4 = new JLabel("Portada");
  JLabel lblTitulo4 = new JLabel("Fablehaven");
  JLabel lblDiasRestantes4 = new JLabel("Te Quedan " + 30 + " dias");
  // JLabel[] lblA4 = {lblPortada4, lblTitulo4, lblDiasRestantes4};

  JLabel lblPortada5 = new JLabel("Portada");
  JLabel lblTitulo5 = new JLabel("Vida y Muerte");
  JLabel lblDiasRestantes5 = new JLabel("Te Quedan " + 24 + " dias");
  // JLabel[] lblA5 = {lblPortada5, lblTitulo5, lblDiasRestantes5};

  JLabel lblAntiguedad =
      new JLabel("Antiguedad en Lo he Leído: " + 10 + " años " + 12 + " meses " + 31 + " días ");

  int conteoLeyendo = 10;
  int conteoLeido = 242;
  int conteoAbandonado = 14;
  int conteoQuiero = 147;
  int conteoTotal = conteoLeyendo + conteoLeido + conteoAbandonado + conteoQuiero;

  JLabel lblVerde = new JLabel();
  JLabel lblAzul = new JLabel();
  JLabel lblRojo = new JLabel();
  JLabel lblGris = new JLabel();

  JLabel lblLeyendo = new JLabel("Leyendo: " + conteoLeyendo);
  JLabel lblLeidos = new JLabel("Leidos: " + conteoLeido);
  JLabel lblAbandonados = new JLabel("Abandonados: " + conteoAbandonado);
  JLabel lblQuieroLeer = new JLabel("Quiero Leer: " + conteoQuiero);
  JLabel lblTotalGuardados = new JLabel("Libros Guardados: " + conteoTotal);

  JLabel lblCapitulosTotales = new JLabel("Capitulos: " + 100.000);
  JLabel lblColecciones = new JLabel("Colecciones: " + 1.000);
  JLabel lblAutores = new JLabel("Autores: " + 100);
  JLabel lblNotaMedia = new JLabel("Nota Media: " + 7.84);

  int valorLeyendoVerde = 0;
  int valorLeidoAzul = 0;
  int valorAbandonadoRojo = 0;
  int valorQuieroGris = 0;

  JPanel[] jPanelA = {
    panel,
    panelPrestamo,
    panelPrestamo1,
    panelPrestamo2,
    panelPrestamo3,
    panelPrestamo4,
    panelPrestamo5,
    panelEstadisticas
  };

  JComponent[] jComponentA = {panelPrestamo, panelEstadisticas};

  public IntfzMiCuenta() {
    this.setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void iniciar() {
    setTitle("¿Lo he leído? - Mi Cuenta");
    getContentPane().setLayout(new GridLayout(1, 10));
    MenuUsuario menuUsuario = new MenuUsuario(panel, this);
    PanelBusqueda panelBusqueda = new PanelBusqueda(panel);
    panel.setLayout(null);
    panelPrestamo.setLayout(new GridLayout(5, 1));
    panelEstadisticas.setLayout(null);
    panelPrestamo1.setLayout(null);
    panelPrestamo2.setLayout(null);
    panelPrestamo3.setLayout(null);
    panelPrestamo4.setLayout(null);
    panelPrestamo5.setLayout(null);

    getContentPane().add(panel);

    crearComponentesPrestamo();
    crearComponentesEstadistica();

    // Empaquetado, tamaño y visualizazion
    pack();
    setSize(1600, 1000);
    setVisible(true);
  }

  public void crearComponentesPrestamo() {
    panelPrestamo.setBounds(10, 100, 515, 850);
    panel.add(panelPrestamo);

    lblPortada1.setBounds(10, 10, 100, 150);
    lblPortada1.setBorder(BorderFactory.createLineBorder(Color.black));
    panelPrestamo1.add(lblPortada1);
    lblTitulo1.setBounds(125, 10, 250, 20);
    panelPrestamo1.add(lblTitulo1);
    lblDiasRestantes1.setBounds(350, 145, 155, 20);
    lblDiasRestantes1.setHorizontalAlignment(SwingConstants.RIGHT);
    panelPrestamo1.add(lblDiasRestantes1);
    panelPrestamo1.setBorder(BorderFactory.createLineBorder(Color.black));
    panelPrestamo.add(panelPrestamo1);

    lblPortada2.setBounds(
        lblPortada1.getX(), lblPortada1.getY(), lblPortada1.getWidth(), lblPortada1.getHeight());
    lblPortada2.setBorder(BorderFactory.createLineBorder(Color.black));
    panelPrestamo2.add(lblPortada2);
    lblTitulo2.setBounds(
        lblTitulo1.getX(), lblTitulo1.getY(), lblTitulo1.getWidth(), lblTitulo1.getHeight());
    panelPrestamo2.add(lblTitulo2);
    lblDiasRestantes2.setBounds(
        lblDiasRestantes1.getX(),
        lblDiasRestantes1.getY(),
        lblDiasRestantes1.getWidth(),
        lblDiasRestantes1.getHeight());
    lblDiasRestantes2.setHorizontalAlignment(SwingConstants.RIGHT);
    panelPrestamo2.add(lblDiasRestantes2);
    panelPrestamo.add(panelPrestamo2);
    panelPrestamo2.setBorder(BorderFactory.createLineBorder(Color.black));

    lblPortada3.setBounds(
        lblPortada1.getX(), lblPortada1.getY(), lblPortada1.getWidth(), lblPortada1.getHeight());
    lblPortada3.setBorder(BorderFactory.createLineBorder(Color.black));
    panelPrestamo3.add(lblPortada3);
    lblTitulo3.setBounds(
        lblTitulo1.getX(), lblTitulo1.getY(), lblTitulo1.getWidth(), lblTitulo1.getHeight());
    panelPrestamo3.add(lblTitulo3);
    lblDiasRestantes3.setBounds(
        lblDiasRestantes1.getX(),
        lblDiasRestantes1.getY(),
        lblDiasRestantes1.getWidth(),
        lblDiasRestantes1.getHeight());
    lblDiasRestantes3.setHorizontalAlignment(SwingConstants.RIGHT);
    panelPrestamo3.add(lblDiasRestantes3);
    panelPrestamo.add(panelPrestamo3);
    panelPrestamo3.setBorder(BorderFactory.createLineBorder(Color.black));

    lblPortada4.setBounds(
        lblPortada1.getX(), lblPortada1.getY(), lblPortada1.getWidth(), lblPortada1.getHeight());
    lblPortada4.setBorder(BorderFactory.createLineBorder(Color.black));
    panelPrestamo4.add(lblPortada4);
    lblTitulo4.setBounds(
        lblTitulo1.getX(), lblTitulo1.getY(), lblTitulo1.getWidth(), lblTitulo1.getHeight());
    panelPrestamo4.add(lblTitulo4);
    lblDiasRestantes4.setBounds(
        lblDiasRestantes1.getX(),
        lblDiasRestantes1.getY(),
        lblDiasRestantes1.getWidth(),
        lblDiasRestantes1.getHeight());
    lblDiasRestantes4.setHorizontalAlignment(SwingConstants.RIGHT);
    panelPrestamo4.add(lblDiasRestantes4);
    panelPrestamo.add(panelPrestamo4);
    panelPrestamo4.setBorder(BorderFactory.createLineBorder(Color.black));

    lblPortada5.setBounds(
        lblPortada1.getX(), lblPortada1.getY(), lblPortada1.getWidth(), lblPortada1.getHeight());
    lblPortada5.setBorder(BorderFactory.createLineBorder(Color.black));
    panelPrestamo5.add(lblPortada5);
    lblTitulo5.setBounds(
        lblTitulo1.getX(), lblTitulo1.getY(), lblTitulo1.getWidth(), lblTitulo1.getHeight());
    panelPrestamo5.add(lblTitulo5);
    lblDiasRestantes5.setBounds(
        lblDiasRestantes1.getX(),
        lblDiasRestantes1.getY(),
        lblDiasRestantes1.getWidth(),
        lblDiasRestantes1.getHeight());
    lblDiasRestantes5.setHorizontalAlignment(SwingConstants.RIGHT);
    panelPrestamo5.add(lblDiasRestantes5);
    panelPrestamo.add(panelPrestamo5);
    panelPrestamo5.setBorder(BorderFactory.createLineBorder(Color.black));
  }

  private void crearComponentesEstadistica() {
    panelEstadisticas.setBounds(550, 100, 1000, 850);
    panelEstadisticas.setBorder(BorderFactory.createLineBorder(Color.black));

    panel.add(panelEstadisticas);
    coloresEstadistica();
  }

  private void coloresEstadistica() {

    lblVerde.setBackground(Color.GREEN);
    lblVerde.setOpaque(true);
    lblVerde.setBorder(BorderFactory.createLineBorder(lblVerde.getBackground(), 2));
    lblAzul.setBackground(Color.BLUE);
    lblAzul.setOpaque(true);
    lblAzul.setBorder(BorderFactory.createLineBorder(lblAzul.getBackground(), 2));
    lblRojo.setBackground(Color.RED);
    lblRojo.setOpaque(true);
    lblRojo.setBorder(BorderFactory.createLineBorder(lblRojo.getBackground(), 2));
    lblGris.setBackground(Color.GRAY);
    lblGris.setOpaque(true);
    lblGris.setBorder(BorderFactory.createLineBorder(lblGris.getBackground(), 2));

    if (conteoTotal == 0) {
    } else {
      double vLV = ((((double) conteoLeyendo * 100) / conteoTotal) * 500) / 100;
      double vLA = ((((double) conteoLeido * 100) / conteoTotal) * 500) / 100;
      double vAR = ((((double) conteoAbandonado * 100) / conteoTotal) * 500) / 100;
      double vQG = ((((double) conteoQuiero * 100) / conteoTotal) * 500) / 100;

      valorLeyendoVerde = (int) Math.round(vLV);
      valorLeidoAzul = (int) Math.round(vLA);
      valorAbandonadoRojo = (int) Math.round(vAR);
      valorQuieroGris = (int) Math.round(vQG);
    }

    lblVerde.setBounds(30, 30, valorLeyendoVerde, 30);
    lblAzul.setBounds(lblVerde.getX() + valorLeyendoVerde, 30, valorLeidoAzul, 30);
    lblRojo.setBounds(lblAzul.getX() + valorLeidoAzul, 30, valorAbandonadoRojo, 30);
    lblGris.setBounds(lblRojo.getX() + valorAbandonadoRojo, 30, valorQuieroGris, 30);

    panelEstadisticas.add(lblVerde);
    panelEstadisticas.add(lblAzul);
    panelEstadisticas.add(lblRojo);
    panelEstadisticas.add(lblGris);

    lblLeyendo.setBounds(50, 80, 200, 20);
    lblLeyendo.setForeground(lblVerde.getBackground());
    panelEstadisticas.add(lblLeyendo);
    lblLeidos.setBounds(50, 100, 200, 20);
    lblLeidos.setForeground(lblAzul.getBackground());
    panelEstadisticas.add(lblLeidos);
    lblAbandonados.setBounds(50, 120, 200, 20);
    lblAbandonados.setForeground(lblRojo.getBackground());
    panelEstadisticas.add(lblAbandonados);
    lblQuieroLeer.setBounds(50, 140, 200, 20);
    lblQuieroLeer.setForeground(lblGris.getBackground());
    panelEstadisticas.add(lblQuieroLeer);
  }

  public void cambioTema(String color) {
    Temas.cambioTema(color, jPanelA, null, null, null, null, null, null);
  }
}
