package cloud.deuterium.firebase.service;

import cloud.deuterium.firebase.model.Atom;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Milan Stojkovic 01-Oct-2021
 */
public interface StorageService {
    List<Atom> getAll() throws ExecutionException, InterruptedException;
    Atom getById(String id) throws ExecutionException, InterruptedException;
    Atom save(Atom atom) throws ExecutionException, InterruptedException;
    Atom update(String id, Atom atom) throws ExecutionException, InterruptedException;
    void delete(String id) throws ExecutionException, InterruptedException;
}
