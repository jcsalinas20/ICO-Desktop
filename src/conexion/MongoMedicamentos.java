package conexion;

import com.mongodb.client.*;
import org.bson.Document;
import programa.Constantes;

public class MongoMedicamentos {

    private MongoCollection<Document> pacientes, medicamentos;
    private MongoClient client;

    public MongoMedicamentos() {
        client = MongoClients.create(Constantes.MONGO_CONNECTION_STRING);
        this.pacientes = client.getDatabase(Constantes.MONGO_DATABASE_NAME).getCollection(Constantes.MONGO_PACIENTES_COLLECTION);
        this.medicamentos = client.getDatabase(Constantes.MONGO_DATABASE_NAME).getCollection(Constantes.MONGO_MEDICAMENTOS_COLLECTION);
    }

    public void eliminarMedicamento(int id_paciente, int id_medicamento) {

    }

    public void editarHorasMedicamento(int id_paciente, int id_medicamento, String horas) {

    }

}
