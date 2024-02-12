package org.prueba.back.controller;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.prueba.back.models.FavoriteNews;
import org.prueba.back.service.FavoriteNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.prueba.back.configuration.TestConfig;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@WebMvcTest(FavoriteNewsController.class)
@Import(TestConfig.class)
class FavoriteNewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteNewsService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;



    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getAllFavorites_ShouldReturnAllFavorites() throws Exception {
        List<FavoriteNews> favorites = Arrays.asList(new FavoriteNews(), new FavoriteNews());
        given(service.getAllFavorites()).willReturn(favorites);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/favorites"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(favorites.size())));
    }

    @Test
    public void createFavorite_ShouldReturn201_WhenValidRequest() throws Exception {
        // Given
        FavoriteNews favoriteNews = new FavoriteNews();
        favoriteNews.setId(0L);
        favoriteNews.setTitle("strinasasasgasdf");
        favoriteNews.setContent("striasasngasdf");
        favoriteNews.setSummary("striasasngasdf");
        favoriteNews.setImageUrl("striasasngasdf");
        favoriteNews.setNews_site("striasasngasdf");
        favoriteNews.setSavedAt("striasasngasdf");
        favoriteNews.setUrl("striasasasngasdf");

        when(service.createOrUpdateFavorite(any(FavoriteNews.class))).thenReturn(favoriteNews);

        mockMvc.perform(post("/api/favorites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(favoriteNews)))
                .andExpect(status().isCreated());
    }


    @Test
    void updateFavorite_ShouldReturnOk() throws Exception {
        Long id = 1L;
        FavoriteNews favoriteNews = new FavoriteNews();
        favoriteNews.setId(id);
        favoriteNews.setTitle("Updated News");

        when(service.getFavoriteById(id)).thenReturn(Optional.of(favoriteNews));
        when(service.createOrUpdateFavorite(any(FavoriteNews.class))).thenReturn(favoriteNews);

        mockMvc.perform(put("/api/favorites/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(favoriteNews)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFavorite_ShouldReturnNoContent() throws Exception {
        Long id = 1L;
        when(service.getFavoriteById(id)).thenReturn(Optional.of(new FavoriteNews()));

        mockMvc.perform(delete("/api/favorites/{id}", id))
                .andExpect(status().isNoContent());
    }
}
