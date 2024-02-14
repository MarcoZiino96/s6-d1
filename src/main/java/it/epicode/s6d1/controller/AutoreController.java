package it.epicode.s6d1.controller;

import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.CustomResponse;
import it.epicode.s6d1.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
public class AutoreController {
    @Autowired
    AutoreService autoreService;

    @GetMapping("/autore")
    public ResponseEntity<CustomResponse> getAllAuthors(Pageable pageable) {
        try{
        return CustomResponse.success(HttpStatus.OK.toString(),autoreService.searchAllAuthor(pageable), HttpStatus.OK);

        }catch (Exception e){
            return  CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/autore/{id}")
    public ResponseEntity<CustomResponse>getAuthor(@PathVariable int id) {
        try{
            return  CustomResponse.success(HttpStatus.OK.toString(), autoreService.searchAuthorById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return  CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return   CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


@PostMapping("/autore")
public ResponseEntity<CustomResponse> saveAuthor(@RequestBody Autore a){
    try{
        return CustomResponse.success(HttpStatus.OK.toString(),autoreService.salvaAutore(a), HttpStatus.OK);
    }catch (Exception e){
        return  CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PutMapping("/autore/{id}")
    public  ResponseEntity<CustomResponse>updateAuthor(@PathVariable int id, @RequestBody Autore autore){
    try{
        return  CustomResponse.success(HttpStatus.OK.toString(), autoreService.updateAutore(id, autore), HttpStatus.OK);
    }
    catch (NotFoundException e){
        return  CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    catch (Exception e){
        return  CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@DeleteMapping("/autore/{id}")
    public ResponseEntity<CustomResponse> deleteAuthor(@PathVariable int id){
        try{
             autoreService.deleteAuthor(id);
            return CustomResponse.emptyResponse("Autore con id "+id+" è stata cancellata", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return  CustomResponse.error(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return   CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}