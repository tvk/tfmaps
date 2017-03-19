package com.senselessweb.tfmaps.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senselessweb.tfmaps.domain.MapModel;
import com.senselessweb.tfmaps.generator.GeneratorService;

@RestController
public class GenerateMapController {
  
  private final GeneratorService generator;
  
  public GenerateMapController(final GeneratorService generator) {
    this.generator = generator;
  }
  
  @RequestMapping(path="generator", method=RequestMethod.POST)
  public String generate(@RequestBody MapModel model) {
    generator.generate(model);
    return "ok";
  }
}
