package org.prueba.back.repository;
import org.prueba.back.models.FavoriteNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FavoriteNewsRepository extends JpaRepository<FavoriteNews, Long> {
    Page<FavoriteNews> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query(value = "SELECT f FROM FavoriteNews f ORDER BY f.savedAt DESC LIMIT 10")
    List<FavoriteNews> findLast10Favorites();

}

