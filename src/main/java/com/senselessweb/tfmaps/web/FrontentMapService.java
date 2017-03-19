package com.senselessweb.tfmaps.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.senselessweb.tfmaps.domain.IndustryType;
import com.senselessweb.tfmaps.domain.MapSize;

@Service(FrontentMapService.BEAN_NAME)
public class FrontentMapService {
  
  public static final String BEAN_NAME = "frontendMap";

  public Collection<MapSize> getMapSizes() {
    return Arrays.asList(MapSize.values());
  }

  public Map<String, MapSize.Wrapper> getMapSizesByName() {
    final Map<String, MapSize.Wrapper> result = new LinkedHashMap<>();
    getMapSizes().forEach(s -> result.put(s.name(), s.wrapped()));
    return result;
  }
  
  public Collection<IndustryType.Wrapper> getIndustries() {
    return Arrays.asList(IndustryType.values()).stream().map(i -> i.wrapped()).collect(Collectors.toList());
  }
  
  public Map<String, IndustryType.Wrapper> getIndustriesByName() {
    final Map<String, IndustryType.Wrapper> result = new LinkedHashMap<>();
    getIndustries().forEach(s -> result.put(s.getName(), s));
    return result;
  }

}
