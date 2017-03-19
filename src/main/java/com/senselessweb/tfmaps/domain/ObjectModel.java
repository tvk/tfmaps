package com.senselessweb.tfmaps.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ObjectModel {

  private final String name;
  private final LatLng position;
  
  public ObjectModel(
      final @JsonProperty("name") String name, 
      final @JsonProperty("position") LatLng position) {
    this.name = name;
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public LatLng getPosition() {
    return position;
  }
}
