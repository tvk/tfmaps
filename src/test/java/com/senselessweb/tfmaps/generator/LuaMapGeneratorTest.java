package com.senselessweb.tfmaps.generator;

import java.io.File;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.Test;

import com.senselessweb.tfmaps.domain.CityModel;
import com.senselessweb.tfmaps.domain.IndustryModel;
import com.senselessweb.tfmaps.domain.IndustryType;
import com.senselessweb.tfmaps.domain.LatLng;
import com.senselessweb.tfmaps.domain.MapModel;
import com.senselessweb.tfmaps.domain.MapSize;

public class LuaMapGeneratorTest {

  @Test
  public void test() throws Exception {
    
    final LuaMapGenerator testee = new LuaMapGenerator();
    
    final LatLng northEast = new LatLng(-32.717693263375594, 152.544634765625);
    final LatLng southWest = new LatLng(-34.28871313587644, 148.699419921875);
    final LatLng center = new LatLng(-33.584026212500525, 150.644);
    
    final CityModel bathurst = new CityModel("Bathurst", new LatLng(-33.41367536990064, 149.58984375), 0.2);
    final CityModel centralCoast = new CityModel("Central Coast", new LatLng(-33.35404687281852, 151.4520263671875), 0.5);
    final CityModel sydney = new CityModel("Sydney", new LatLng(-33.861863294411116, 151.2213134765625), 2.0);
    final Collection<CityModel> cities = Lists.newArrayList(bathurst, centralCoast, sydney);
    
    final IndustryModel coalMine = new IndustryModel(IndustryType.Coal_Mine, null, new LatLng(-33.43201437071108, 150.391845703125)); 
    final IndustryModel farm = new IndustryModel(IndustryType.Farm, "Blue Mountains Ranch", new LatLng(-33.724910755513115, 149.5843505859375)); 
    final Collection<IndustryModel> industries = Lists.newArrayList(coalMine, farm);
    
    final MapModel model = new MapModel("Australia", "A map of australia",
        8, center, northEast, southWest,  cities, industries, MapSize.SIZE_8, MapSize.SIZE_4);
    
    testee.generate(model, new File("/home/thomas/.steam/steam/steamapps/common/Transport Fever/maps/Schleswig-Holstein/map.lua"));
    
    final HeightMapGenerator mapGenerator = new HeightMapGenerator(System.getenv("googleMapsApiKey"));
    mapGenerator.generateMap(model, new File("/home/thomas/.steam/steam/steamapps/common/Transport Fever/maps/Schleswig-Holstein/heightmap.png"));
    
    
  }

}
