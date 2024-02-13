package it.epicode.s6d1.controller;


import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.Blog;
import it.epicode.s6d1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Blog> getAllblogs(){
       return blogService.searchAllBlogs();
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable int id){
        try{
            Blog b = blogService.searchBlogForId(id);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/blog")
    public void saveBlog(@RequestBody Blog blog){
        blogService.saveBlog(blog);
    }

    @PutMapping("/blog/{id}")
    public Blog updateBlog(@PathVariable int id, Blog blog){
        return blogService.updateBlog(id,blog);
    }

    @DeleteMapping("/blog/{id}")
    public void deleteBlog(int id){
        blogService.deleteBlog(id);
    }
}
