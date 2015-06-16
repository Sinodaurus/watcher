package org.singular.entities.dto.movie;

public class SeenMovieDTO {
    private long seenMovieId;
    private String imdbMovieId;

    public SeenMovieDTO() {}

    public SeenMovieDTO(String imdbMovieId) {
        this.imdbMovieId = imdbMovieId;
    }

    public long getSeenMovieId() {
        return seenMovieId;
    }

    public void setSeenMovieId(long seenMovieId) {
        this.seenMovieId = seenMovieId;
    }

    public String getImdbMovieId() {
        return imdbMovieId;
    }

    public void setImdbMovieId(String imdbMovieId) {
        this.imdbMovieId = imdbMovieId;
    }
}
