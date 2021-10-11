package hu.bme.aut.retelab2.controller;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/ads")

public class AdController {

    @Autowired
    private AdRepository noteRepository;

    @GetMapping
    public List<Ad> getAll(@RequestParam(required = false, defaultValue = "") String keyword, @RequestParam(required = false, defaultValue = "0") Integer min, @RequestParam(required = false, defaultValue = "10000000") Integer max) {
        List<Ad> list = noteRepository.findByKeyword(keyword, min, max);
        for (Ad ad:list
             ) {
            ad.setPassword(null);
        }
        return list;
    }

    @GetMapping("{id}")
    public ResponseEntity<Ad> getById(@PathVariable long id) {
        Ad note = noteRepository.findById(id);
        if (note == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(note);
    }

    @PostMapping
    public String create(@RequestBody Ad note) {
        note.setId(null);
        note.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        note.setPassword(SecretGenerator.generate());
        return noteRepository.save(note).getPassword();

    }

    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad note) {
        Ad n = noteRepository.findByAd(note);
        if (n == null)
            return ResponseEntity.forbidden().build();
        n = noteRepository.save(note);
        return ResponseEntity.ok(n);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Ad note = noteRepository.findById(id);
        if (note == null)
            return ResponseEntity.notFound().build();
        else {
            noteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
class SecretGenerator {
    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RND = new Random();

    public static String generate() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 6; i++)
            sb.append(CHARS[RND.nextInt(CHARS.length)]);
        return sb.toString();
    }
}