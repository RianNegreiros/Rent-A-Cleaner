package com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.dtos;

public class DistanceResponse {

    private String origin;

    private String destination;

    private Double kmDistance;

    public DistanceResponse() {
    }

    public DistanceResponse(String origin, String destination, Double kmDistance) {
        this.origin = origin;
        this.destination = destination;
        this.kmDistance = kmDistance;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getKmDistance() {
        return kmDistance;
    }

    public void setKmDistance(Double kmDistance) {
        this.kmDistance = kmDistance;
    }

    public static class Builder {
        private String origin;
        private String destination;
        private Double kmDistance;

        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder kmDistance(Double kmDistance) {
            this.kmDistance = kmDistance;
            return this;
        }

        public DistanceResponse build() {
            return new DistanceResponse(origin, destination, kmDistance);
        }
    }
}
