package com.senselessweb.tfmaps.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IndustryModel extends ObjectModel {

  private final IndustryType type;
  
  @JsonCreator
  public IndustryModel(
      final @JsonProperty("type") IndustryType type, 
      final @JsonProperty("name") String name, 
      final @JsonProperty("position") LatLng position) {
    super(name, position);
    this.type = type;
  }

  public String getIndustryFilename() {
    return type.getFilename();
  }
  

}
