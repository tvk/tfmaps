package com.senselessweb.tfmaps.domain;

public enum MapSize {

  SIZE_4(1025, "4 km"),
  SIZE_8(2049, "8 km"),
  SIZE_12(3073, "12 km"),
  SIZE_16(4097, "16 km"),
  SIZE_20(5121, "20 km"),
  SIZE_24(6145, "24 km");
  
  public static class Wrapper {
    
    private final MapSize delegate;
    
    public Wrapper(final MapSize delegate) {
      this.delegate = delegate;
    }

    public int getPixel() {
      return delegate.pixel;
    }
    
    public String getDisplayName() {
      return delegate.displayName;
    }
    
    public String getName() {
      return delegate.name();
    }
  }
  
  private final String displayName;
  private final int pixel;
  
  private MapSize(final int pixel, final String displayName) {
    this.pixel = pixel;
    this.displayName = displayName;
  }

  public int getPixel() {
    return pixel;
  }
  
  public String getDisplayName() {
    return displayName;
  }

  public Wrapper wrapped() {
    return new Wrapper(this);
  }

}
