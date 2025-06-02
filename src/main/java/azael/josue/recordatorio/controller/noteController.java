package azael.josue.recordatorio.controller;

import azael.josue.recordatorio.model.note;
import azael.josue.recordatorio.model.user;
import azael.josue.recordatorio.service.noteService;
import azael.josue.recordatorio.service.userService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class noteController {

    private final noteService noteService;
    private final userService userService;

    public noteController(noteService noteService, userService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    // GET /notes
    // GET /notes?userId=1&order=desc
    @GetMapping
    public List<note> getNotas(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "asc") String order) 
    {
        List<note> notas;

        if (userId != null) {
            if (!userService.getById(userId).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
            }
            notas = noteService.getAll().stream()
                    .filter(n -> n.getUser().getId().equals(userId))
                    .toList();
        } else {
            notas = noteService.getAll();
        }

        Comparator<note> comparador = Comparator.comparing(note::getDateCreated);
        if (order.equalsIgnoreCase("desc")) {
            comparador = comparador.reversed();
        }

        return notas.stream().sorted(comparador).toList();
    }

    // GET /notes/{id}
    @GetMapping("/{id}")
    public note getNotaById(@PathVariable @Positive Long id) {
        return noteService.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota no encontrada"));
    }

    // POST /notes?userId={id}
    // POST /notes?userId={id}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public note crearNota(
            @RequestParam @Positive Long userId,
            @RequestBody @Valid note nota
    ) {
        user usuario = userService.getById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        nota.setUser(usuario);
        // Si quieres que la fecha se ponga automáticamente:
        if (nota.getDateCreated() == null) {
            nota.setDateCreated(java.time.LocalDateTime.now());
        }
        return noteService.save(nota);
    }

    // PUT /notes/{id}?userId={id}
    @PutMapping("/{id}")
    public note actualizarNota(
            @PathVariable @Positive Long id,
            @RequestParam @Positive Long userId,
            @RequestBody @Valid note nota
    ) {
        note existingNote = noteService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota no encontrada"));

        user usuario = userService.getById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        existingNote.setTitle(nota.getTitle());
        existingNote.setContent(nota.getContent());

        // Opcionalmente actualizamos la fecha
        existingNote.setDateCreated(nota.getDateCreated() != null ? nota.getDateCreated() : existingNote.getDateCreated());

        existingNote.setUser(usuario);

        return noteService.update(id, existingNote);
    }

    // DELETE /notes/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarNota(@PathVariable @Positive Long id) {
        if (!noteService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota no encontrada");
        }
        noteService.deleteById(id);
    }
}