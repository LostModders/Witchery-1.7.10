package com.emoniph.witchery.common;

import com.emoniph.witchery.util.Coord;
import net.minecraft.world.World;

public abstract interface IPowerSource
{
  public abstract World getWorld();
  
  public abstract Coord getLocation();
  
  public abstract boolean isLocationEqual(Coord paramCoord);
  
  public abstract boolean consumePower(float paramFloat);
  
  public abstract float getCurrentPower();
  
  public abstract float getRange();
  
  public abstract int getEnhancementLevel();
  
  public abstract boolean isPowerInvalid();
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/IPowerSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */