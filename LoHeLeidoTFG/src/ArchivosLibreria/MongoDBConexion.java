package ArchivosLibreria;

import IntfzLibreria.IntfzPntCarga;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConexion {

  IntfzPntCarga intfzPntCarga = new IntfzPntCarga();

  MongoCollection<Document> collecLibros;

  public void conexion() {
    try {
      MongoClientURI uri =
          new MongoClientURI(
              "mongodb+srv://PabloBibTFG:7Infantes@biblioteca.w5wrr.mongodb.net/LoHeLeidoDB?retryWrites=true&w=majority");

      MongoClient mongoClient = new MongoClient(uri);
      MongoDatabase DDBB = mongoClient.getDatabase("LoHeLeidoDB");
    } catch (Exception exception) {
      intfzPntCarga.iniciar();
      intfzPntCarga.pantallaError();
    }
  }
}
