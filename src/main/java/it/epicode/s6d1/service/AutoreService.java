package it.epicode.s6d1.service;


import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.AutoreRequest;
import it.epicode.s6d1.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    AutoreRepository autoreRepository;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<Autore> searchAllAuthor(Pageable pageabale){return autoreRepository.findAll(pageabale);}

    public Autore searchAuthorById(int id) throws NotFoundException {
    return autoreRepository.findById(id).orElseThrow(()-> new NotFoundException("Autore con id "+id+" non trovato"));
    }

    public Autore salvaAutore(AutoreRequest a){
        Autore autore = new Autore();

        autore.setNome(a.getNome());
        autore.setCognome(a.getCognome());
        autore.setEmail(a.getEmail());
        autore.setDataDiNascita(a.getDataDiNascita());
        sendMail(a.getEmail());


        return autoreRepository.save(autore);
    }

    public void deleteAuthor(int id) throws NotFoundException {
        Autore autore = searchAuthorById(id);
        autoreRepository.delete(autore);
    }
    public Autore updateAutore(int id, AutoreRequest a) throws NotFoundException {
        Autore autore = searchAuthorById(id);

        autore.setNome(a.getNome());
        autore.setCognome(a.getCognome());
        autore.setEmail(a.getEmail());
        autore.setDataDiNascita(a.getDataDiNascita());
       autore.setEmail(a.getEmail());

        return autoreRepository.save(autore);
    }
    public Autore uploadAvatar(int id, String url) throws NotFoundException {
        Autore autore = searchAuthorById(id);
        autore.setAvatar(url);
        return  autoreRepository.save(autore);
    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }

}
