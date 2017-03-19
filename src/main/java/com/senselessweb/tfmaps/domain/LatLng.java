package com.senselessweb.tfmaps.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LatLng {

  private final double lat;
  private final double lng;
  
  @JsonCreator
  public LatLng(
      final @JsonProperty("lat") double lat, 
      final @JsonProperty("lng") double lng) {
    this.lat = lat;
    this.lng = lng;
  }
  
  public double getLat() {
    return lat;
  }
  
  public double getLng() {
    return lng;
  }
}
