package com.senselessweb.tfmaps.domain;

public enum MapSize {  

  SIZE_8X4(2049, 1025),

  SIZE_4X4(1025, 1025),
  SIZE_4X8(1025, 2049),
  SIZE_4X12(1025, 3073),
  SIZE_4X16(1025, 4097),
  SIZE_4X20(1025, 5121),
  SIZE_4X24(1025, 6145),
  SIZE_4X28(1025, 7169),
  SIZE_4X32(1025, 8193),

  SIZE_8X8(2049, 2049),
  SIZE_8X12(2049, 3073),
  SIZE_8X16(2049, 4097),
  SIZE_8X20(2049, 5121),
  SIZE_8X24(2049, 6145),
  SIZE_8X28(2049, 7169),
  SIZE_8X32(2049, 8193),

  SIZE_12X12(3073, 3073),
  SIZE_12X16(3073, 4097),
  SIZE_12X20(3073, 5121),
  SIZE_12X24(3073, 6145, true),
  SIZE_12X28(3073, 7169, true),
  SIZE_12X32(3073, 8193, true),

  SIZE_16X16(4097, 4097),
  SIZE_16X20(4097, 5121, true),
  SIZE_16X24(4097, 6145, true),
  SIZE_16X28(4097, 7169, true),
  SIZE_16X32(4097, 8193, true),

  SIZE_20X20(5121, 5121, true),
  SIZE_20X24(5121, 6145, true),
  SIZE_20X28(5121, 7169, true),

  SIZE_24X24(6145, 6145, true);

  /**
   * Looks like thymeleaf can't handle enums. So we need this wrapper class.
   */
  public static class Wrapper {
    
    private final MapSize delegate;
    

    private Wrapper(MapSize delegate) {
      this.delegate = delegate;
    }

    public int getWidth() {
      return delegate.getWidth();
    }
    
    public int getHeight() {
      return delegate.getHeight();
    }
    
    public String getName() {
      return delegate.getName();
    }    
  }

  private final int width;
  private final int height;
  private final boolean experimentalMapSize;

  private MapSize(int width, int height, boolean experimentalMapSize) {
    this.width = width;
    this.height = height;
    this.experimentalMapSize = experimentalMapSize;
  }

  private MapSize(int width, int height) {
    this(width, height, false);
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public String getName() {
    return name();
  }
  
  public Wrapper wrapped() {
    return new Wrapper(this);
  }

}
