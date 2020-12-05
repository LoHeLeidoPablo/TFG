
package Libreria;

import javax.swing.*;
import java.awt.*;

public class InfoLibro extends JFrame implements Interfaz {
    private static final long serialVersionUID = 1L;

    JFrame jFrameLibro = new JFrame();

    JPanel panel = new JPanel();
    JPanel panelGenero = new JPanel();
    JPanel panelTecnico = new JPanel();
    JPanel panelEstado = new JPanel();
    JPanel panelEntregas = new JPanel();
    JTabbedPane tabbed = new JTabbedPane();

    JLabel lblPortada = new JLabel("Portada");
    JLabel lblTitlo = new JLabel("La Sombra del Zorro");
    JLabel lblAutor = new JLabel(" Julie Kagawa");
    JLabel lblResumen = new JLabel("Resumen");
    JLabel lblGeneros = new JLabel("Genero");

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

    JLabel lblISBN = new JLabel("ISBN: 123456789012345");
    JLabel lblCapitulos = new JLabel("Capitulos: 36");
    JLabel lblColeccion = new JLabel("Saga: Guardianes de la Ciudadela");
    JLabel lblNColeccion = new JLabel(" Tomo II");
    JLabel lblPublicacion = new JLabel("Fecha Publicacion: 22-01-2020");

    JLabel lblEstado = new JLabel("Estado:");
    JLabel lblVistos = new JLabel("Caps leídos:");
    JLabel lblNota = new JLabel("Nota:");

    String[] estados = {"Sin Añadir", "Leyendo", "Leído", "Abandonado", "Quiero Leer"};
    JComboBox<String> cbEstados = new JComboBox<String>(estados);
    int valMaximio = 50;
    JSpinner spCapL = new JSpinner(new SpinnerNumberModel(0, 0, valMaximio, 1));
    JLabel lblCapTotales = new JLabel("/" + valMaximio);
    String[] notas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    JComboBox<String> cbNota = new JComboBox<String>(notas);
    JButton btnUpdate = new JButton("Actualizar");

    Font fTitulo = new Font("Console", Font.BOLD, 40);
    Font fAutor = new Font("Console", Font.ITALIC, 30);
    Font fResumen = new Font("Console", Font.PLAIN, 20);
    Font fTResumen = new Font("Console", Font.PLAIN, 14);

    JTextArea txtASinopsis =
            new JTextArea(
                    "Criada por monjes en en un templo escondido, Yumeko ha sido entrenada para ocultar su naturaleza. "
                            + "Mitad zorro kitsune, mitad humana, su habilidad para transformarse, solo es compatible con su inclinacion por las travesuras. "
                            + "HAsta el dia en que su hogar es arrastrado por demonios del averno y se ve obligada a huir con el mayor "
                            + "tesoro del templo, una parte del antiguo pergamino sagrado. Kage Tatsumi es un misterioso samurai del Clan de la Sombra,"
                            + " un guerrero que ha recibido la orden de recuperar el pergamino a cualquier precio. Pero el destino pronto une a Tatsumi y Yumeko."
                            + " Con la promesa de guiarlo hasta el anhelado tesoro, Yumeko establece una peligrosa alianza que le ofrece su mejor esperanza de supervivencia."
                            + "Pero él busca lo que ella ha escondido, ¿y  si su engaño es descubierto?");

    JButton btnPrestamo = new JButton("Prestar");

    String[] titulos = {
            lblColeccion.getText() + " - Tomo 1",
            lblColeccion.getText() + " - Tomo 2",
            lblColeccion.getText() + " - Tomo 3",
            lblColeccion.getText() + " - Tomo 4",
            lblColeccion.getText() + " - Tomo 5",
            lblColeccion.getText() + " - Tomo 1",
            lblColeccion.getText() + " - Tomo 2",
            lblColeccion.getText() + " - Tomo 3",
            lblColeccion.getText() + " - Tomo 4",
            lblColeccion.getText() + " - Tomo 5"
    };

    JList<String> listasecuelas = new JList<String>(titulos);
    JScrollPane scrollPane = new JScrollPane(listasecuelas);

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
    JButton[] jButtonA = {btnPrestamo};
    JComponent[] jCompPprincipalA = {
            lblPortada, btnPrestamo, lblTitlo, lblAutor, panelGenero, lblResumen, txtASinopsis, tabbed
    };
    JComponent[] jCompPtecnicoA = {lblISBN, lblCapitulos, lblColeccion, lblPublicacion};
    JComponent[] jCompPestadoA = {
            lblEstado, lblVistos, lblNota, cbEstados, spCapL, lblCapTotales, cbNota, btnUpdate
    };

    public InfoLibro() {
        this.setResizable(false);
    }

    public void iniciar() {
        setTitle(lblTitlo.getText());
        getContentPane().setLayout(new GridLayout(1, 15));
        crearComponents();

        panel.setLayout(null);
        panelGenero.setLayout(null);
        panelTecnico.setLayout(null);
        panelEstado.setLayout(null);
        panelEntregas.setLayout(null);

        btnPrestamo.setBounds(12, 550, 329, 30);
        lblPortada.setBounds(10, 30, 329, 512);
        lblTitlo.setBounds(350, 55, 500, 35);
        lblAutor.setBounds(350, 105, 500, 35);
        lblResumen.setBounds(350, 250, 100, 20);

        lblTitlo.setFont(fTitulo);
        lblAutor.setFont(fAutor);
        lblResumen.setFont(fResumen);

        panelGenero.setBounds(350, 150, 575, 85);
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

        txtASinopsis.setBounds(350, 275, 1050, 305);
        txtASinopsis.setLineWrap(true);
        txtASinopsis.setWrapStyleWord(true);
        txtASinopsis.setEditable(false);
        txtASinopsis.setBackground(panel.getBackground());
        txtASinopsis.setFont(fTResumen);

        lblPortada.setBorder(BorderFactory.createLineBorder(Color.black));
        panelGenero.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        tabbed.setBounds(1050, 130, 350, 125);
        tabbed.addTab("Ficha Tecnica", panelTecnico);
        if (IntfzLogin.id_Usuario.equals("Invitado")) {
        } else {
            tabbed.addTab("Estado", panelEstado);
        }
        tabbed.addTab("Misma Coleccion", panelEntregas);

        panelTecnico.setBounds(0, 0, tabbed.getWidth(), tabbed.getHeight());
        lblISBN.setBounds(10, 10, 420, 15);
        lblCapitulos.setBounds(10, 30, 420, 15);
        lblColeccion.setBounds(10, 50, 420, 15);
        lblColeccion.setText(lblColeccion.getText() + lblNColeccion.getText());
        lblPublicacion.setBounds(10, 70, 420, 15);

        panelEstado.setBounds(panelTecnico.getBounds());
        lblEstado.setBounds(10, 10, 55, 20);
        cbEstados.setBounds(70, 10, 130, 20);
        lblVistos.setBounds(10, 40, 95, 20);
        spCapL.setBounds(110, 40, 50, 20);
        lblCapTotales.setBounds(160, 40, 50, 20);
        lblNota.setBounds(10, 70, 50, 20);
        cbNota.setBounds(60, 70, 50, 20);
        btnUpdate.setBounds(210, 50, 125, 40);

        panelEntregas.setBounds(panelTecnico.getBounds());

        scrollPane.setBounds(0, 0, panelEntregas.getWidth(), panelEntregas.getHeight() - 25);
        listasecuelas.setBounds(0, 0, scrollPane.getWidth(), scrollPane.getHeight());

        scrollPane.setBackground(panel.getBackground());
        listasecuelas.setBackground(panel.getBackground());

        getContentPane().add(panel);

        // Empaquetado, tamaño y visualizazion
        pack();
        setSize(1440, 650);
        setVisible(true);
    }

    public void cambioTema(String color) {
        Temas.cambioTema(color, jPanelA, jLabelA, null, jButtonA, null, null, txtASinopsis);
    }

    public void crearComponents() {
        for (JComponent jComponent : jCompPprincipalA) {
            panel.add(jComponent);
        }
        for (JCheckBox jCheckBox : jCheckBoxA) {
            panelGenero.add(jCheckBox);
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
