package com.emoniph.witchery.worldgen;

import java.util.Random;
import net.minecraft.world.World;

public abstract interface IWorldGenHandler
{
  public abstract boolean generate(World paramWorld, Random paramRandom, int paramInt1, int paramInt2);
  
  public abstract void initiate();
  
  public abstract int getExtentX();
  
  public abstract int getExtentZ();
  
  public abstract int getRange();
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/IWorldGenHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */