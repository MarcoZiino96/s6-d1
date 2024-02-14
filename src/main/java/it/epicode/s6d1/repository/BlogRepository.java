package it.epicode.s6d1.repository;

import it.epicode.s6d1.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer>, PagingAndSortingRepository<Blog, Integer> {
}
