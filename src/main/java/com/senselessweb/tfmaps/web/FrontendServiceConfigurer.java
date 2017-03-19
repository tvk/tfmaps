package com.senselessweb.tfmaps.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class FrontendServiceConfigurer {
  
  public static final String BEAN_GOOGLE_MAPS_API_KEY = "googleMapsApiKey";

  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    final InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setExposedContextBeanNames(FrontentMapService.BEAN_NAME, BEAN_GOOGLE_MAPS_API_KEY);
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

}
