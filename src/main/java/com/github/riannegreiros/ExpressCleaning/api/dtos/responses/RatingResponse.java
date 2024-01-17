package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RatingResponse {
    private String description;

    private Double grade;

    private String reviewerName;

    private String reviewerPhoto;

    public RatingResponse() {
    }

    public RatingResponse(String description, Double grade, String reviewerName, String reviewerPhoto) {
        this.description = description;
        this.grade = grade;
        this.reviewerName = reviewerName;
        this.reviewerPhoto = reviewerPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerPhoto() {
        return reviewerPhoto;
    }

    public void setReviewerPhoto(String reviewerPhoto) {
        this.reviewerPhoto = reviewerPhoto;
    }

    public static final class RatingResponseBuilder {
        private String description;
        private Double grade;
        private String reviewerName;
        private String reviewerPhoto;

        private RatingResponseBuilder() {
        }

        public static RatingResponseBuilder aRatingResponse() {
            return new RatingResponseBuilder();
        }

        public RatingResponseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RatingResponseBuilder withGrade(Double grade) {
            this.grade = grade;
            return this;
        }

        public RatingResponseBuilder withReviewerName(String reviewerName) {
            this.reviewerName = reviewerName;
            return this;
        }

        public RatingResponseBuilder withReviewerPhoto(String reviewerPhoto) {
            this.reviewerPhoto = reviewerPhoto;
            return this;
        }

        public RatingResponse build() {
            RatingResponse ratingResponse = new RatingResponse();
            ratingResponse.setDescription(description);
            ratingResponse.setGrade(grade);
            ratingResponse.setReviewerName(reviewerName);
            ratingResponse.setReviewerPhoto(reviewerPhoto);
            return ratingResponse;
        }
    }
}
