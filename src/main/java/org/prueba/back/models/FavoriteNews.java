package org.prueba.back.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    @Column(length = 1000)
    @NotBlank(message = "Content is required")
    private String content;

    @Column(length = 1000)
    @NotBlank(message = "Summary is required")
    private String summary;

    private String imageUrl;

    @NotBlank(message = "News site is required")
    private String news_site;


    private String savedAt;

    @NotBlank(message = "URL is required")
    private String url;


    // Default constructor
    public FavoriteNews() {
    }

    // Full constructor
    public FavoriteNews(Long id, String title, String content, String summary, String imageUrl, String news_site, String savedAt, String url ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.news_site = news_site;
        this.savedAt = savedAt;
        this.url = url;

    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSummary() {
        return summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNews_site() {
        return news_site;
    }

    public void setNews_site(String news_site) {
        this.news_site = news_site;
    }

    public String getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(String savedAt) {
        this.savedAt = savedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSummary(String summary) {

        this.summary = summary;
    }


}

