package com.emoniph.witchery.util;

import net.minecraft.world.World;

public abstract interface ISpiralBlockAction
{
  public abstract void onSpiralActionStart(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract boolean onSpiralBlockAction(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void onSpiralActionStop(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/ISpiralBlockAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */