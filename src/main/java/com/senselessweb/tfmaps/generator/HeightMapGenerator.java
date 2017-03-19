package com.senselessweb.tfmaps.generator;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.tuple.Pair;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.senselessweb.tfmaps.domain.MapModel;
import com.senselessweb.tfmaps.web.ExposedBeansConfigurer;

@Service
public class HeightMapGenerator {
  
  private static final int maxBoundSize = 640;
  
  private static String urlTemplate = "https://maps.googleapis.com/maps/api/staticmap?"
      + "center=%s,%s&size=%sx%s&zoom=%s&scale=2&"
      + "style=feature:all|element:geometry|color:0x8E8E8E&"
      + "style=feature:all|element:geometry.stroke|visibility:off&"
      + "style=feature:all|element:labels|visibility:off&"
      + "style=feature:landscape.natural.terrain|element:geometry|color:0xBFBFBF&"
      + "style=feature:water|color:0x424242&"
      + "key=%s";
  
  private final String googleMapsApiKey;
  
  public HeightMapGenerator(final @Qualifier(ExposedBeansConfigurer.BEAN_GOOGLE_MAPS_API_KEY) String googleMapsApiKey) {
    this.googleMapsApiKey = googleMapsApiKey;
  }
  
  public void generateMap(final MapModel model, final File target) {
    
    try {
      
      final Pair<Integer, Integer> size = calcStaticImageSize(model);
      final URL url = new URL(String.format(urlTemplate, 
          model.getCenter().getLat(), model.getCenter().getLng(),
          size.getLeft(), size.getRight(), 
          model.getZoom() - 2, googleMapsApiKey));
      System.out.println(url);
      
      final InputStream is = new BufferedInputStream(url.openStream());
      final BufferedImage large = ImageIO.read(is);
      ImageIO.write(large, "png", new File(target.getParentFile(), "tmp.png"));
      
      final BufferedImage cropped = Scalr.crop(large,
          (int) (large.getWidth() / 4.0), 
          (int) (large.getHeight() / 4.0), 
          (int) (large.getWidth() - large.getWidth() / 2.0), 
          (int) (large.getHeight() - large.getHeight() / 2.0));
      
      final BufferedImage resized = Scalr.resize(cropped, Method.ULTRA_QUALITY, model.getWidth().getPixel(), model.getHeight().getPixel());
      final BufferedImage png = new BufferedImage(model.getWidth().getPixel(), model.getHeight().getPixel(), 
          BufferedImage.TYPE_BYTE_GRAY);
      
      png.getGraphics().drawImage(resized, 0, 0, null);
      
      ImageIO.write(png, "png", target);
      
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  private Pair<Integer, Integer> calcStaticImageSize(MapModel model) {
    final int width = model.getWidth().getPixel();
    final int height = model.getHeight().getPixel();
    
    if (width > height) {
      return Pair.of(maxBoundSize, maxBoundSize * height / width);
    } else {
      return Pair.of(maxBoundSize * width / height, maxBoundSize);
    }
  }

}
