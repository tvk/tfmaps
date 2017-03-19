package com.senselessweb.tfmaps.domain;

public enum IndustryType {
  
  Chemical_Plant("industry/chemical_plant.con"),
  Coal_Mine("industry/coal_mine.con"),
  Construction_Material("industry/construction_material.con"),
  Farm("industry/farm.con"),
  Food_Processing_Plant("industry/food_processing_plant.con"),
  Forest("industry/forest.con"),
  Goods_Factory("industry/goods_factory.con"),
  Iron_Ore_Mine("industry/iron_ore_mine.con"),
  Machines_Factory("industry/machines_factory.con"),
  Oil_Refinery("industry/oil_refinery.con"),
  Oil_Well("industry/oil_well.con"),
  Quarry("industry/quarry.con"),
  Saw_Mill("industry/saw_mill.con"),
  Steel_Mill("industry/steel_mill.con");
  

  /**
   * Looks like thymeleaf can't handle enums. So we need this wrapper class.
   */
  public static class Wrapper {
    
    private final IndustryType delegate;
    

    private Wrapper(IndustryType delegate) {
      this.delegate = delegate;
    }
    
    public String getName() {
      return delegate.name();
    }    
  }
  
  private final String filename;
  
  private IndustryType(final String filename) {
    this.filename = filename;
  }
  
  public String getFilename() {
    return filename;
  }

  public Wrapper wrapped() {
    return new Wrapper(this);
  }


}
