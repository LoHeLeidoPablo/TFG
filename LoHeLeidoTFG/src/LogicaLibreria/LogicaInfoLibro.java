package LogicaLibreria;

import ArchivosLibreria.MongoDBConexion;
import IntfzLibreria.IntfzInfoLibro;

public class LogicaInfoLibro {

  MongoDBConexion mongoDBConexion = new MongoDBConexion();
  IntfzInfoLibro intfzInfoLibro = new IntfzInfoLibro();

  public LogicaInfoLibro() {
    mongoDBConexion.conexion();

    /*public void mostrarInfoLibro(Document libro) {
    intfzInfoLibro.lblISBN.setText("ISBN: " + libro.getString("ISBN"));
    intfzInfoLibro.lblTitlo.setText(libro.getString("Titulo"));

    if (lblTitlo == null) {
      setTitle("Info Libro");
    } else {
      setTitle(lblTitlo.getText());
    }

    lblAutor.setText(libro.getString("Autor"));
    txtASinopsis.setText(libro.getString("Sinopsis"));
    lblColeccion.setText("Saga: " + libro.getString("Saga") + "  " + libro.getInteger("Tomo"));
    lblCapitulos.setText("Capitulos: " + libro.getInteger("Capitulos"));
    SimpleDateFormat sdf = new SimpleDateFormat("dd - MMMM - yyyy");
    lblPublicacion.setText("Fecha de Publicación: " + sdf.format(libro.getDate("f_publicacion")));
    valMaximio = libro.getInteger("Capitulos");
    spCapL.setModel(new SpinnerNumberModel(0, 0, valMaximio, 1));
    lblCapTotales.setText("/" + valMaximio);
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
    listasecuelas.setModel(dlm);*/

    // TODO Cuanto más lo usas más tarda el proceso en ejecutarse

    /*
      listasecuelas.addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              listasecuelas = (JList) evt.getSource();
              if (evt.getClickCount() == 2) {
                int index = listasecuelas.locationToIndex(evt.getPoint());
                int i = 0;
                MongoCursor<Document> otroLibro =
                    collecLibro
                        .find(eq("Saga", libro.getString("Saga")))
                        .sort(ascending("Tomo"))
                        .iterator();
                while (otroLibro.hasNext()) {
                  Document libroColec = otroLibro.next();
                  if (i == index) {
                    mostrarInfoLibro(libroColec);
                    System.out.println("I = " + i);
                    System.out.println("Index = " + index);
                    break;
                  }
                  i++;
                }
              }
              return;
            }
          });

      listasecuelas.addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              listasecuelas = (JList) evt.getSource();
              if (evt.getClickCount() == 2) {
                int index = listasecuelas.locationToIndex(evt.getPoint());
                int i = 0;
                MongoCursor<Document> otroLibro =
                    collecLibro
                        .find(eq("Saga", libro.getString("Saga")))
                        .sort(ascending("Tomo"))
                        .iterator();
                while (otroLibro.hasNext()) {
                  Document libroColec = otroLibro.next();
                  if (i == index) {
                    dispose();
                    iniciar(libroColec);
                  }
                  i++;
                }
              }
            }
          });
    }*/
  }
}
