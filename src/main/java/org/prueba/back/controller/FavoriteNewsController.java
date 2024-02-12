package org.prueba.back.controller;
import jakarta.validation.Valid;
import org.prueba.back.models.FavoriteNews;
import org.prueba.back.service.FavoriteNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteNewsController {

    private final FavoriteNewsService service;

    @Autowired
    public FavoriteNewsController(FavoriteNewsService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<FavoriteNews>> searchFavoritesByTitle(@RequestParam String title, Pageable pageable) {
        Page<FavoriteNews> page = service.searchFavoritesByTitle(title, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createFavorite(@Valid @RequestBody FavoriteNews favoriteNews, BindingResult bindingResult) {
        System.out.println(favoriteNews);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        FavoriteNews createdFavoriteNews = service.createOrUpdateFavorite(favoriteNews);
        return new ResponseEntity<>(createdFavoriteNews, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FavoriteNews>> getAllFavorites() {
        List<FavoriteNews> favorites = service.getAllFavorites();
        return ResponseEntity.ok(favorites);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FavoriteNews> updateFavorite(@PathVariable Long id, @RequestBody FavoriteNews favoriteNews) {
        Optional<FavoriteNews> existingFavoriteNews = service.getFavoriteById(id);
        if (existingFavoriteNews.isPresent()) {
            favoriteNews.setId(id);
            FavoriteNews updatedFavoriteNews = service.createOrUpdateFavorite(favoriteNews);
            return new ResponseEntity<>(updatedFavoriteNews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/last10")
    public ResponseEntity<List<FavoriteNews>> getLast10Favorites() {
        List<FavoriteNews> last10Favorites = service.getLast10Favorites();
        return ResponseEntity.ok(last10Favorites);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteNews> getFavoriteById(@PathVariable Long id) {
        Optional<FavoriteNews> favoriteNews = service.getFavoriteById(id);
        return favoriteNews
                .map(news -> new ResponseEntity<>(news, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        Optional<FavoriteNews> existingFavoriteNews = service.getFavoriteById(id);
        if (existingFavoriteNews.isPresent()) {
            service.deleteFavorite(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


