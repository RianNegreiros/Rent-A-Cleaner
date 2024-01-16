package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import java.util.List;

public interface Assembler<R> {
    void addLinks(R resource);
    void addLinks(List<R> collectionResource);
}
