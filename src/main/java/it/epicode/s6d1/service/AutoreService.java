package it.epicode.s6d1.service;


import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    AutoreRepository autoreRepository;

    public Page<Autore> searchAllAuthor(Pageable pagebale){return autoreRepository.findAll(pagebale);}

    public Autore searchAuthorById(int id) throws NotFoundException {
    return autoreRepository.findById(id).orElseThrow(()-> new NotFoundException("Autore con id "+id+" non trovato"));
    }

    public Autore salvaAutore(Autore a){
        return autoreRepository.save(a);
    }

    public void deleteAuthor(int id) throws NotFoundException {
        Autore a = searchAuthorById(id);
        autoreRepository.delete(a);
    }

    public Autore updateAutore(int id, Autore a) throws NotFoundException {
        Autore autore = searchAuthorById(id);

        autore.setNome(a.getNome());
        autore.setCognome(a.getCognome());
        autore.setEmail(a.getEmail());
        autore.setDataDiNascita(a.getDataDiNascita());
        return autoreRepository.save(a);
    }

}
