package com.github.riannegreiros.ExpressCleaning.core.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Rating extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private Boolean visibility;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Daily daily;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = true)
    private User reviewer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private User rated;

    public Rating() {
    }

    public Rating(Long id, String description, Double rate, Boolean visibility, Daily daily, User reviewer, User rated) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.visibility = visibility;
        this.daily = daily;
        this.reviewer = reviewer;
        this.rated = rated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getRated() {
        return rated;
    }

    public void setRated(User rated) {
        this.rated = rated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id, rating.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                '}';
    }

    public static class Builder {
        private Long id;
        private String description;
        private Double nota;
        private Boolean visibility;
        private Daily daily;
        private User reviewer;
        private User rated;

        public Builder() {
        }

        // Setters for each field, returning the builder instance for method chaining
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder nota(Double nota) {
            this.nota = nota;
            return this;
        }

        public Builder visibility(Boolean visibility) {
            this.visibility = visibility;
            return this;
        }

        public Builder daily(Daily daily) {
            this.daily = daily;
            return this;
        }

        public Builder reviewer(User reviewer) {
            this.reviewer = reviewer;
            return this;
        }

        public Builder rated(User rated) {
            this.rated = rated;
            return this;
        }

        public Rating build() {
            Rating rating = new Rating();
            rating.setId(this.id);
            rating.setDescription(this.description);
            rating.setRate(this.nota);
            rating.setVisibility(this.visibility);
            rating.setDaily(this.daily);
            rating.setReviewer(this.reviewer);
            rating.setRated(this.rated);
            return rating;
        }
    }
}
