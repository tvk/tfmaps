package com.senselessweb.tfmaps.generator;

import java.io.File;

import org.springframework.stereotype.Service;

import com.senselessweb.tfmaps.domain.MapModel;

@Service
public class GeneratorService {
  
  private final HeightMapGenerator heightMapGenerator;
  private final LuaMapGenerator luaMapGenerator;
  
  public GeneratorService(
      final HeightMapGenerator heightMapGenerator,
      final LuaMapGenerator luaMapGenerator) {
    this.heightMapGenerator = heightMapGenerator;
    this.luaMapGenerator = luaMapGenerator;
  }
  
  public void generate(final MapModel model) {
    final File targetDir = new File("/home/thomas/.steam/steam/steamapps/common/Transport Fever/maps/Schleswig-Holstein/");
    heightMapGenerator.generateMap(model, new File(targetDir, "heightmap.png"));
    luaMapGenerator.generate(model, new File(targetDir, "map.lua"));
  }
}
