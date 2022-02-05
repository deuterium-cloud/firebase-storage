package cloud.deuterium.firebase.service;

import cloud.deuterium.firebase.model.Atom;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by Milan Stojkovic 01-Oct-2021
 */

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final String COLLECTION_NAME = "atoms";
    private Firestore dbFirestore;

    @PostConstruct
    private void initialize(){
         this.dbFirestore = FirestoreClient.getFirestore();
    }

    @Override
    public List<Atom> getAll() throws ExecutionException, InterruptedException {
        log.info("Querying for all Atoms");
        ApiFuture<QuerySnapshot> apiFuture = dbFirestore.collection(COLLECTION_NAME).get();
        QuerySnapshot snapshots = apiFuture.get();
        return snapshots.toObjects(Atom.class);
    }

    @Override
    public Atom getById(String id) throws ExecutionException, InterruptedException {
        log.info("Querying for Atom with id={}", id);
        ApiFuture<DocumentSnapshot> apiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).get();
        DocumentSnapshot snapshot = apiFuture.get();
        if(snapshot.exists()){
            return snapshot.toObject(Atom.class);
        }
        return new Atom();
    }

    @Override
    public Atom save(Atom atom) throws ExecutionException, InterruptedException {
        log.info("Save new Atom into Firebase: {}", atom);
        String id = UUID.randomUUID().toString().replace("-", "");
        atom.setId(id);
        atom.setCreated(Timestamp.now());
        ApiFuture<WriteResult> apiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).set(atom);
        WriteResult writeResult = apiFuture.get();
        log.info("New Atom saved to Firebase at: {}", writeResult.getUpdateTime());
        return atom;
    }

    @Override
    public Atom update(String id, Atom atom) throws ExecutionException, InterruptedException {
        log.info("Update Atom {} in Firebase", atom);
        ApiFuture<WriteResult> apiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).set(atom);
        WriteResult writeResult = apiFuture.get();
        log.info("Atom successfully updated in Firebase at: {}", writeResult.getUpdateTime());
        return atom;
    }

    @Override
    public void delete(String id) throws ExecutionException, InterruptedException {
        log.info("Delete Atom with id={} from Firebase", id);
        ApiFuture<WriteResult> delete = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
        WriteResult writeResult = delete.get();
        log.info("Atom with id={} successfully deleted from Firebase at: {}", id, writeResult.getUpdateTime());
    }

}
