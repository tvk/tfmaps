package com.senselessweb.tfmaps.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MapModel {
 
  private final String name;
  private final String description;
  private final LatLng northEast;
  private final LatLng southWest;
  private final Collection<CityModel> cities;
  private final Collection<IndustryModel> industries;
  private final MapSize width;
  private final MapSize height;
  private final LatLng center;
  private final int zoom;
  
  @JsonCreator
  public MapModel(
      final @JsonProperty("name") String name,
      final @JsonProperty("description") String description,
      final @JsonProperty("zoom") int zoom, 
      final @JsonProperty("center") LatLng center, 
      final @JsonProperty("northEast") LatLng northEast, 
      final @JsonProperty("southWest") LatLng southWest, 
      final @JsonProperty("cities") Collection<CityModel> cities, 
      final @JsonProperty("industries") Collection<IndustryModel> industries, 
      final @JsonProperty("width") MapSize width,
      final @JsonProperty("height") MapSize height) {
    
    this.name = name;
    this.zoom = zoom;
    this.center = center;
    this.description = description;
    this.northEast = northEast;
    this.southWest = southWest;
    this.cities = cities;
    this.industries = industries;
    this.width = width;
    this.height = height;
  }
  
  public String getName() {
    return name;
  }
  
  public int getZoom() {
    return zoom;
  }
  
  public LatLng getCenter() {
    return center;
  }
  
  public String getDescription() {
    return description;
  }

  public LatLng getNorthEast() {
    return northEast;
  }

  public LatLng getSouthWest() {
    return southWest;
  }

  public Collection<CityModel> getCities() {
    return cities;
  }
  
  public Collection<IndustryModel> getIndustries() {
    return industries;
  }
  
  public MapSize getWidth() {
    return width;
  }
  
  public MapSize getHeight() {
    return height;
  }
}
