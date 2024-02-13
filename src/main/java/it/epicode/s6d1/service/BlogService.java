package it.epicode.s6d1.service;


import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    AutoreService autoreService;
    List<Blog> blogs = new ArrayList<>();

    public List<Blog> searchAllBlogs(){
        return blogs;
    }

    public Blog searchBlogForId(int id) throws NoSuchElementException {
        Optional<Blog> b = blogs.stream().filter(auto -> auto.getId()==id).findAny();
        if (b.isPresent()){
            return b.get();
        }else{
             throw new NoSuchElementException("blog non trovato");
        }
    }

    public void saveBlog(Blog b) throws  RuntimeException{
        if (b.getAutore() != null){
            blogs.add(b);
        }else {
            throw new RuntimeException("Al blog non è associato nessun autore");
        }
    }

    public Blog updateBlog(int id, Blog blog){
        Blog b = searchBlogForId(id);

        Autore a = autoreService.searchAuthorById(blog.getAutore().getId());

        b.setAutore(a);

        b.setCategoria(blog.getCategoria());
        b.setContenuto(blog.getContenuto());
        b.setCover(blog.getCover());
        b.setTitolo(blog.getTitolo());
        b.setTempoDiLettura(blog.getTempoDiLettura());

        return  b;
    }

    public void deleteBlog(int id){
        Blog b = searchBlogForId(id);
        blogs.remove(b);
    }


}
