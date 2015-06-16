package org.singular.entities.dto.person;

public class SeenMovieInfoWithoutPersonsDTO {
    private long seenMovieId;
    private String imdbMovieId;

    public SeenMovieInfoWithoutPersonsDTO() {}

    public SeenMovieInfoWithoutPersonsDTO(String imdbMovieId) {
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

    @Override
    public String toString() {
        return "SeenMovieInfoWithoutPersonsDTO{" +
                "seenMovieId=" + seenMovieId +
                ", imdbMovieId='" + imdbMovieId + '\'' +
                '}';
    }
}
