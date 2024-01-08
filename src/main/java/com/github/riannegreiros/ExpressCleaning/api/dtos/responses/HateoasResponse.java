package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

public class HateoasResponse {
    private List<LinkResponse> links;

    public HateoasResponse(List<LinkResponse> links) {
        this.links = links;
    }

    public HateoasResponse() {
        links = new ArrayList<>();
    }

    public void addLinks(Link... links) {
        for (Link link : links) {
            var linkResponse = new LinkResponse();
            linkResponse.setUri(link.getHref());
            linkResponse.setType(link.getType());
            linkResponse.setRel(link.getRel().value());

            this.links.add(linkResponse);
        }
    }

    public List<LinkResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinkResponse> links) {
        this.links = links;
    }
}
