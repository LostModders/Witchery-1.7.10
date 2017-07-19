package com.emoniph.witchery.common;

import net.minecraft.world.World;

public abstract interface INullSource
{
  public abstract World getWorld();
  
  public abstract int getPosX();
  
  public abstract int getPosY();
  
  public abstract int getPosZ();
  
  public abstract float getRange();
  
  public abstract boolean isPowerInvalid();
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/INullSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */