package org.prueba.back.service;
import org.prueba.back.models.FavoriteNews;
import org.prueba.back.repository.FavoriteNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteNewsService {

    private final FavoriteNewsRepository repository;

    @Autowired
    public FavoriteNewsService(FavoriteNewsRepository repository) {
        this.repository = repository;
    }



    public List<FavoriteNews> getAllFavorites() {
        return repository.findAll();
    }

    public Page<FavoriteNews> searchFavoritesByTitle(String title, Pageable pageable) {
        return repository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public List<FavoriteNews> getLast10Favorites() {
        return repository.findLast10Favorites();
    }

    @Transactional
    public FavoriteNews createOrUpdateFavorite(FavoriteNews favoriteNews) {
        return repository.save(favoriteNews);
    }

    @Transactional(readOnly = true)
    public Optional<FavoriteNews> getFavoriteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteFavorite(Long id) {
        repository.deleteById(id);
    }
}

