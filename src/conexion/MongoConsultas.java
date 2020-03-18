package conexion;

import clases.TablaConsultas;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import programa.Constantes;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoConsultas {

    private MongoCollection<Document> consultas;

    private TablaConsultas data;

    private Bson filter;

    private List<Document> listaConsultas;

    private Document oldConsulta;

    public MongoConsultas(MongoCollection<Document> consultas, TablaConsultas data) {
        this.consultas = consultas;
        this.data = data;
        searchConsultas();
        searchOldConsulta();
    }

    public void changeAsistido() {
        createDocument();
    }

    public void createDocument() {
        Document nuevaConsulta = new Document();
        nuevaConsulta.append(Constantes.MONGO_CONSULTA_HORA, data.getHora());
        nuevaConsulta.append(Constantes.MONGO_CONSULTA_DIA, data.getDia());
        nuevaConsulta.append(Constantes.MONGO_CONSULTA_ASISTENCIA, data.getAsistido().isSelected());
        nuevaConsulta.append(Constantes.MONGO_CONSULTA_NOTASPACIENTE, data.getNotasPaciente());
        nuevaConsulta.append(Constantes.MONGO_CONSULTA_NOTASDOCTOR, data.getNotasDoctor());
        nuevaConsulta.append(Constantes.MONGO_CONSULTA_ID, data.getNum_consulta());
        listaConsultas.remove(oldConsulta);
        listaConsultas.add(nuevaConsulta);
        updateArrayConsultas();
    }

    private void updateArrayConsultas() {
        listaConsultas.remove(oldConsulta); //BORRO LOS DATOS DESACTUALIZADOS
        Bson updateDocument = set(Constantes.MONGO_CONSULTAS_CONSULTASARRAY, listaConsultas);
        consultas.updateOne(filter, updateDocument); //ACTUALIZO LOS CAMBIOS
    }

    private void searchConsultas() {
        filter = eq(Constantes.MONGO_CONSULTAS_ID, data.getId_consulta()); //FILTRO PARA ENCONTRAR EL PACIENTE

        //BUSCO LA CONSULTA CON ESTE ID
        FindIterable<Document> consultasIterable = consultas.find(filter);
        Document consulta = consultasIterable.first();
        listaConsultas = (List<Document>) consulta.get(Constantes.MONGO_CONSULTAS_CONSULTASARRAY);
    }

    private void searchOldConsulta() {
        int num_consulta = data.getNum_consulta();
        int num_consulta_pos;

        //RECORRO LAS CONSULTAS DEL PACIENTE
        for(Document consulta : listaConsultas) {
            num_consulta_pos = consulta.getInteger(Constantes.MONGO_CONSULTA_ID);
            if (num_consulta == num_consulta_pos) { //BUSCO LA CONSULTA QUE EL MEDICO HA ACTUALIZADO
                oldConsulta = consulta;
                break;
            }
        }
    }

}
