package com.senselessweb.tfmaps.domain;

public enum IndustryType {
  
  Chemical_Plant("industry/chemical_plant.con", "Chemical Plant"),
  Coal_Mine("industry/coal_mine.con", "Coal Mine"),
  Construction_Material("industry/construction_material.con", "Construction Material Plant"),
  Farm("industry/farm.con", "Farm"),
  Food_Processing_Plant("industry/food_processing_plant.con", "Food Plant"),
  Forest("industry/forest.con", "Forest"),
  Goods_Factory("industry/goods_factory.con", "Goods Factory"),
  Iron_Ore_Mine("industry/iron_ore_mine.con", "Iron Ore Mine"),
  Machines_Factory("industry/machines_factory.con", "Machines Factory"),
  Oil_Refinery("industry/oil_refinery.con", "Oil Refinery"),
  Oil_Well("industry/oil_well.con", "Oil Well"),
  Quarry("industry/quarry.con", "Quarry"),
  Saw_Mill("industry/saw_mill.con", "Saw Mill"),
  Steel_Mill("industry/steel_mill.con", "Steel Mill");

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
    
    public String getDisplayName() {
      return delegate.displayName;
    }
    
  }
  
  private final String displayName;
  private final String filename;
  
  private IndustryType(final String filename, final String displayName) {
    this.filename = filename;
    this.displayName = displayName;
  }
  
  public String getFilename() {
    return filename;
  }
  
  public String getDisplayName() {
    return displayName;
  }

  public Wrapper wrapped() {
    return new Wrapper(this);
  }


}
