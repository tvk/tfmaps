package com.senselessweb.tfmaps.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityModel extends ObjectModel {

  private final double size;

  public CityModel(
      final @JsonProperty("name") String name, 
      final @JsonProperty("position") LatLng position, 
      final @JsonProperty("size") double size) {
    super(name, position);
    this.size = size;
  }
  
  public double getSize() {
    return size;
  }

}
