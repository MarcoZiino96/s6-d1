package it.epicode.s6d1.controller;



import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Blog;
import it.epicode.s6d1.model.BlogRequest;
import it.epicode.s6d1.model.CustomResponse;
import it.epicode.s6d1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

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
    public ResponseEntity<CustomResponse> getBlog(@PathVariable int id){
        try{
           return CustomResponse.success(HttpStatus.OK.toString(), blogService.searchBlogForId(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return  CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception  e){
            return  CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/blog")
    public ResponseEntity<CustomResponse>saveBlog(@RequestBody BlogRequest blog) {
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.saveBlog(blog), HttpStatus.OK);
        }
        catch(Exception e){
            return  CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> updateBlog(@PathVariable int id, @RequestBody BlogRequest blog){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.updateBlog(id,blog),HttpStatus.OK);
        }
        catch(NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable int id){
        try{
            blogService.deleteBlog(id);
            return CustomResponse.emptyResponse("Blog con id"+id+"è stato casncellato", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
