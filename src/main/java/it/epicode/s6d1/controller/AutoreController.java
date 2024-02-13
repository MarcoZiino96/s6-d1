package it.epicode.s6d1.controller;

import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class AutoreController {
    @Autowired
    AutoreService autoreService;

    @GetMapping("/autore")
    public List<Autore> getAllAuthors() {
        return autoreService.searchAllAuthor();
    }

    @GetMapping("/autore/{id}")
    public ResponseEntity<Autore>getAuthor(@PathVariable int id) {
        try{
            Autore a = autoreService.searchAuthorById(id);
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


@PostMapping("/autore")
public void saveAthor(@RequestBody Autore a){
        autoreService.salvaAutore(a);
}

@PutMapping("/autore/{id}")
    public  Autore updateAthor(@PathVariable int id, @RequestBody Autore autore){
       return autoreService.updateAutore(id,autore);
}

@DeleteMapping("/autore/{id}")
    public void deleteAuthor(@PathVariable int id){
        autoreService.deleteAuthor(id);
}

}