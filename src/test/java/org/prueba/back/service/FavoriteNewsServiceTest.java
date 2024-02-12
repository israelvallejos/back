package org.prueba.back.service;
import org.prueba.back.repository.FavoriteNewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prueba.back.models.FavoriteNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteNewsServiceTest {

    @Mock
    private FavoriteNewsRepository repository;

    @InjectMocks
    private FavoriteNewsService service;


    @Test
    void searchFavoritesByTitle_ShouldReturnFilteredPage() {
        String title = "testTitle";
        Page<FavoriteNews> expectedPage = new PageImpl<>(Collections.singletonList(new FavoriteNews()));
        when(repository.findByTitleContainingIgnoreCase(title, PageRequest.of(0, 10)))
                .thenReturn(expectedPage);

        Page<FavoriteNews> result = service.searchFavoritesByTitle(title, PageRequest.of(0, 10));

        assertThat(result).isEqualTo(expectedPage);
    }

    @Test
    void createFavorite_ShouldSaveAndReturnFavoriteNews() {
        FavoriteNews favoriteNewsToCreate = new FavoriteNews();
        favoriteNewsToCreate.setTitle("New Favorite News");

        FavoriteNews savedFavoriteNews = new FavoriteNews();
        savedFavoriteNews.setId(1L);
        savedFavoriteNews.setTitle(favoriteNewsToCreate.getTitle());


        when(repository.save(any(FavoriteNews.class))).thenReturn(savedFavoriteNews);

        FavoriteNews result = service.createOrUpdateFavorite(favoriteNewsToCreate);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("New Favorite News");
        verify(repository, times(1)).save(favoriteNewsToCreate);
    }

    @Test
    void getAllFavorites_ShouldReturnAllFavoriteNews() {
        List<FavoriteNews> expectedFavorites = Arrays.asList(
                new FavoriteNews(),
                new FavoriteNews()
        );

        when(repository.findAll()).thenReturn(expectedFavorites);

        List<FavoriteNews> result = service.getAllFavorites();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expectedFavorites.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getFavoriteById_ShouldReturnFavoriteNews() {
        Long id = 1L;
        FavoriteNews favoriteNews = new FavoriteNews();
        favoriteNews.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(favoriteNews));

        Optional<FavoriteNews> result = service.getFavoriteById(id);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(id);
    }

    @Test
    void deleteFavorite_ShouldCallRepositoryDelete() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.deleteFavorite(id);

        verify(repository, times(1)).deleteById(id);
    }
}

