package com.senselessweb.tfmaps.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.senselessweb.tfmaps.domain.IndustryType;
import com.senselessweb.tfmaps.domain.MapSize;

@Configuration
public class ExposedBeansConfigurer {
  
  public static final String BEAN_GOOGLE_MAPS_API_KEY = "googleMapsApiKey";
  private static final String BEAN_MAP_SIZES = "mapSizes";
  private static final String BEAN_BEAN_MAP_SIZES_BY_NAME = "mapSizesByName";
  private static final String BEAN_INDUSTRIES = "industries";
  private static final String BEAN_INDUSTRIES_BY_NAME = "industriesByName";

  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    final InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setExposedContextBeanNames(BEAN_GOOGLE_MAPS_API_KEY, BEAN_MAP_SIZES, BEAN_BEAN_MAP_SIZES_BY_NAME, BEAN_INDUSTRIES, BEAN_INDUSTRIES_BY_NAME);
    return bean;
  }
  
  @Bean(name = BEAN_GOOGLE_MAPS_API_KEY)
  public String googleMapsApiKey() {
    final String googleMapsApiKey = System.getenv(BEAN_GOOGLE_MAPS_API_KEY);
    if (StringUtils.isBlank(googleMapsApiKey)) {
      throw new IllegalStateException("googleMapsApiKey must not be null");
    }
    return googleMapsApiKey;
  }
  

  @Bean(name = BEAN_MAP_SIZES)
  public Collection<MapSize.Wrapper> getMapSizes() {
    return Arrays.asList(MapSize.values()).stream().map(s -> s.wrapped()).collect(Collectors.toList());
  }

  @Bean(name = BEAN_BEAN_MAP_SIZES_BY_NAME)
  public Map<String, MapSize.Wrapper> getMapSizesByName() {
    final Map<String, MapSize.Wrapper> result = new LinkedHashMap<>();
    getMapSizes().forEach(s -> result.put(s.getName(), s));
    return result;
  }
  
  @Bean(name = BEAN_INDUSTRIES)
  public Collection<IndustryType.Wrapper> getIndustries() {
    return Arrays.asList(IndustryType.values()).stream().map(i -> i.wrapped()).collect(Collectors.toList());
  }
  
  @Bean(name = BEAN_INDUSTRIES_BY_NAME)
  public Map<String, IndustryType.Wrapper> getIndustriesByName() {
    final Map<String, IndustryType.Wrapper> result = new LinkedHashMap<>();
    getIndustries().forEach(s -> result.put(s.getName(), s));
    return result;
  }

}
