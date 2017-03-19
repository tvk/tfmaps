package com.senselessweb.tfmaps.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import com.senselessweb.tfmaps.domain.CityModel;
import com.senselessweb.tfmaps.domain.IndustryModel;
import com.senselessweb.tfmaps.domain.LatLng;
import com.senselessweb.tfmaps.domain.MapModel;

@Service
public class LuaMapGenerator {
  
  private static final String city = "  {pos = { %s, %s }, name = _('%s'), sizeFactor = %s}";
  private static final String industry = "  {pos = { %s, %s }, angle = %s, fileName = '%s'}";
  
  private static final String template = 
      "local towns = {" + System.lineSeparator() + 
      "%s " + System.lineSeparator() +
      "}" + System.lineSeparator() +  
      "local industries = {" + System.lineSeparator() +
      "%s" + System.lineSeparator() +
      "}" + System.lineSeparator() +  
      "function data() return {" + System.lineSeparator() +
        "name  = _('%s')," + System.lineSeparator() +
        "  description = _('%s')," + System.lineSeparator() +
        "  minGameVersion = '10000'," + System.lineSeparator() +
        "  range = {80, 120 }," + System.lineSeparator() +
        "  seed = '%s'," + System.lineSeparator() +
        "  tags  = {'map'}," + System.lineSeparator() +
        "  towns = towns," + System.lineSeparator() +
        "  industries = industries" + System.lineSeparator() +
        "} end" + System.lineSeparator();
  
  public void generate(final MapModel model, final File target) {
    
    final String generated = String.format(template,
        generateCities(model), generateIndustries(model),
        model.getName(), model.getDescription(), model.getName());

    try {
      System.out.println("Writing to " + target);
      FileUtils.write(target, generated);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  private String generateCities(final MapModel model) {

    final StringBuilder result = new StringBuilder();
    boolean first = true;
    
    for (final CityModel object : model.getCities()) {
      final Pair<Double, Double> position = calculatePosition(model, object.getPosition());
      if (!first) {
        result.append("," + System.lineSeparator());
      }
      result.append(String.format(city, position.getLeft(), position.getRight(), object.getName(), object.getSize()));
      first = false;
    }
    return result.toString();
  }

  
  private String generateIndustries(final MapModel model) {

    final StringBuilder result = new StringBuilder();
    boolean first = true;
    
    for (final IndustryModel object : model.getIndustries()) {
      final Pair<Double, Double> position = calculatePosition(model, object.getPosition());
      if (!first) {
        result.append("," + System.lineSeparator());
      }
      
      final double angle = Math.random() * Math.PI * 2;
      result.append(String.format(industry, position.getLeft(), position.getRight(), angle, object.getIndustryFilename()));
      first = false;
    }
    return result.toString();
  }
  
  private Pair<Double, Double> calculatePosition(final MapModel model, final LatLng position) {
    
    final double minLng = model.getSouthWest().getLng();
    final double maxLng = model.getNorthEast().getLng();
    
    final double minLat = model.getSouthWest().getLat();
    final double maxLat = model.getNorthEast().getLat();
    
    final double relativeX = (position.getLng() - minLng) / (maxLng - minLng);
    final double relativeY = (position.getLat() - minLat) / (maxLat - minLat);
    
    final double tfX = - (2.0 * model.getWidth().getPixel()) + (relativeX * 4 * model.getWidth().getPixel());
    final double tfY = - (2.0 * model.getHeight().getPixel()) + (relativeY * 4 * model.getHeight().getPixel());
    
    return Pair.of(tfX, tfY);
  }

}
