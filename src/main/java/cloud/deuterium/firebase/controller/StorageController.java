package cloud.deuterium.firebase.controller;

import cloud.deuterium.firebase.model.Atom;
import cloud.deuterium.firebase.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Milan Stojkovic 01-Oct-2021
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/atoms")
public class StorageController {

    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<Atom>> getAllAtoms() throws ExecutionException, InterruptedException {
        List<Atom> atoms = storageService.getAll();
        return ResponseEntity.ok(atoms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atom> getAtom(@PathVariable String id) throws ExecutionException, InterruptedException {
        Atom atom = storageService.getById(id);
        return ResponseEntity.ok(atom);
    }

    @PostMapping
    public ResponseEntity<Atom> saveAtom(@RequestBody Atom atom) throws ExecutionException, InterruptedException {
        Atom savedAtom = storageService.save(atom);
        return ResponseEntity.ok(savedAtom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atom> updateAtom(@PathVariable String id, @RequestBody Atom atom) throws ExecutionException, InterruptedException {
        Atom updatedAtom = storageService.update(id, atom);
        return ResponseEntity.ok(updatedAtom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtom(@PathVariable String id) throws ExecutionException, InterruptedException {
        storageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
