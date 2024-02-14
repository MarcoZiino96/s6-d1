package it.epicode.s6d1.service;


import it.epicode.s6d1.exception.NotFoundException;
import it.epicode.s6d1.model.Autore;
import it.epicode.s6d1.model.Blog;
import it.epicode.s6d1.model.BlogRequest;
import it.epicode.s6d1.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class BlogService {
    @Autowired
    AutoreService autoreService;
    @Autowired
    BlogRepository blogRepository;

    public Page<Blog> searchAllBlogs(Pageable pageable){
        return blogRepository.findAll(pageable);
    }

    public Blog searchBlogForId(int id) throws NotFoundException {
        return blogRepository.findById(id).orElseThrow(()->new NotFoundException("Blog con id "+id+" non trovato"));
    }

    public Blog saveBlog(BlogRequest b) throws  NotFoundException{
        Autore autore = autoreService.searchAuthorById(b.getIdAutore());

        Blog blog = new Blog();
        blog.setTempoDiLettura(b.getTempoDiLettura());
        blog.setTitolo(b.getTitolo());
        blog.setContenuto(b.getContenuto());
        blog.setCategoria(b.getCategoria());
        blog.setCover(b.getCover());
        blog.setAutore(autore);
        return blogRepository.save(blog);
    }

    public Blog updateBlog(int id, BlogRequest b) throws NotFoundException {

        Blog blog = searchBlogForId(id);

        Autore a = autoreService.searchAuthorById(b.getIdAutore());

        blog.setAutore(a);

        blog.setCategoria(b.getCategoria());
        blog.setContenuto(b.getContenuto());
        blog.setCover(b.getCover());
        blog.setTitolo(b.getTitolo());
        blog.setTempoDiLettura(b.getTempoDiLettura());

        return  blogRepository.save(blog);
    }

    public void deleteBlog(int id) throws NotFoundException {
        Blog blog = searchBlogForId(id);
        blogRepository.delete(blog);
    }


}
