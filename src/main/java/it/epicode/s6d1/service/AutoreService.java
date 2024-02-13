package it.epicode.s6d1.service;


import it.epicode.s6d1.model.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    private List<Autore> autori = new ArrayList<>();

    public List<Autore> searchAllAuthor(){return autori;};

    public Autore searchAuthorById(int id) throws NoSuchElementException{
        Optional<Autore> a = autori.stream().filter(autore -> autore.getId()== id).findAny();
        if(a.isPresent()){
            return a.get();
        }else{
            throw new NoSuchElementException("Autore non trovato");
        }
    }

    public void salvaAutore(Autore a){
        autori.add(a);
    }

    public void deleteAuthor(int id){
        Autore a = searchAuthorById(id);
        autori.remove(a);
    }

    public Autore updateAutore(int id, Autore a){
        Autore autore = searchAuthorById(id);
        autore.setNome(a.getNome());
        autore.setCognome(a.getCognome());
        autore.setEmail(a.getEmail());
        autore.setDataDiNascita(a.getDataDiNascita());
        return a;
    }

}
