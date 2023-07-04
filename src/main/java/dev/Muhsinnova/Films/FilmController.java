package dev.Muhsinnova.Films;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/films")
public class FilmController
{
    @Autowired
    private FilmService filmService;

    @GetMapping
    private ResponseEntity<List<Film>> getAllFilms()
    {
        //return new ResponseEntity<String>("ALL MOVIES!", HttpStatus.OK);
        return new ResponseEntity<>(filmService.findAllFilms(),HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Film>>getSingleFilm(@PathVariable String imdbId)
    {
        return new ResponseEntity<Optional<Film>>(filmService.singleFilm(imdbId), HttpStatus.OK);

    }
}
