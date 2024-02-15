package it.epicode.s6d1.controller;



import com.cloudinary.Cloudinary;
import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.Blog;
import it.epicode.s6d1.model.BlogRequest;
import it.epicode.s6d1.model.CustomResponse;
import it.epicode.s6d1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    Cloudinary cloudinary;

    @GetMapping("/blog")
    public ResponseEntity<CustomResponse> getAllblogs(Pageable pageable){
       try{
           return CustomResponse.success(HttpStatus.OK.toString(),blogService.searchAllBlogs(pageable),HttpStatus.OK);
       }
       catch (Exception e){
           return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> getBlog(@PathVariable int id) throws NotFoundException, Exception{
           return CustomResponse.success(HttpStatus.OK.toString(), blogService.searchBlogForId(id), HttpStatus.OK);
    }

    @PostMapping("/blog")
    public ResponseEntity<CustomResponse>saveBlog(@RequestBody @Validated BlogRequest blog, BindingResult bindingResult) throws NotFoundException, Exception {

        if (bindingResult.hasErrors()){
            return  CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.saveBlog(blog), HttpStatus.OK);

    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> updateBlog(@PathVariable int id, @RequestBody @Validated BlogRequest blog, BindingResult bindingResult) throws NotFoundException, Exception {

        if (bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.updateBlog(id,blog),HttpStatus.OK);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable int id) throws NotFoundException, Exception{
            blogService.deleteBlog(id);
            return CustomResponse.emptyResponse("Blog con id"+id+"è stato casncellato", HttpStatus.OK);
    }
//    @PatchMapping("/autore/{id}/upload")
//    public ResponseEntity<CustomResponse> uploadCover(@PathVariable int id, @RequestParam("uploadBlog") MultipartFile file) throws NotFoundException{
//        try{
//            Blog blog = blogService.uploadCover(id, (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
//
//            return  CustomResponse.success(HttpStatus.OK.toString(), blog, HttpStatus.OK);
//        }
//        catch (IOException e){
//            return  CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
