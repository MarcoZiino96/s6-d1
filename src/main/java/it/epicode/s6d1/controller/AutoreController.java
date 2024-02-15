package it.epicode.s6d1.controller;

import com.cloudinary.Cloudinary;
import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.AutoreRequest;
import it.epicode.s6d1.model.CustomResponse;
import it.epicode.s6d1.service.AutoreService;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;


@RestController
public class AutoreController {
    @Autowired
    AutoreService autoreService;

    @Autowired
    Cloudinary cloudinary;

    @GetMapping("/autore")
    public ResponseEntity<CustomResponse> getAllAuthors(Pageable pageable) {
        return CustomResponse.success(HttpStatus.OK.toString(),autoreService.searchAllAuthor(pageable), HttpStatus.OK);
    }

    @GetMapping("/autore/{id}")
    public ResponseEntity<CustomResponse>getAuthor(@PathVariable int id){
            return  CustomResponse.success(HttpStatus.OK.toString(), autoreService.searchAuthorById(id), HttpStatus.OK);
    }


@PostMapping("/autore")
public ResponseEntity<CustomResponse> saveAuthor(@RequestBody @Validated AutoreRequest a, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString(), HttpStatus.BAD_REQUEST);
        }
            return CustomResponse.success(HttpStatus.OK.toString(),autoreService.salvaAutore(a), HttpStatus.OK);
}

@PutMapping("/autore/{id}")
    public  ResponseEntity<CustomResponse>updateAuthor(@PathVariable int id, @RequestBody @Validated AutoreRequest autore, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString(), HttpStatus.BAD_REQUEST);
        }
        return  CustomResponse.success(HttpStatus.OK.toString(), autoreService.updateAutore(id, autore), HttpStatus.OK);
}

@DeleteMapping("/autore/{id}")
    public ResponseEntity<CustomResponse> deleteAuthor(@PathVariable int id){
             autoreService.deleteAuthor(id);
            return CustomResponse.emptyResponse("Autore con id "+id+" è stata cancellata", HttpStatus.OK);
}


@PatchMapping("/autore/{id}/upload")
public ResponseEntity<CustomResponse> uploadAvatar(@PathVariable int id, @RequestParam("uploadAutore") MultipartFile file){
try{
    Autore autore = autoreService.uploadAvatar(id, (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));

    return  CustomResponse.success(HttpStatus.OK.toString(), autore, HttpStatus.OK);
}
catch (IOException e){
     return  CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
}
}
}