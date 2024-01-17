package com.github.riannegreiros.ExpressCleaning.core.models;

import com.github.riannegreiros.ExpressCleaning.core.listeners.PhotoEntityListener;
import jakarta.persistence.*;

@EntityListeners(PhotoEntityListener.class)
@Entity
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String filename;

  @Column(name = "content_length", nullable = false)
  private Long contentLength;

  @Column(name = "content_type", nullable = false)
  private String contentType;

  @Column(nullable = false)
  private String url;

  public Photo() {
  }

  public Photo(Long id, String filename, Long contentLength, String contentType, String url) {
    this.id = id;
    this.filename = filename;
    this.contentLength = contentLength;
    this.contentType = contentType;
    this.url = url;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public Long getContentLength() {
    return contentLength;
  }

  public void setContentLength(Long contentLength) {
    this.contentLength = contentLength;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Photo other = (Photo) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Photo [id=" + id + "]";
  }
}
