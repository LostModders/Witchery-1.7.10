package com.emoniph.witchery.brewing;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class Dispersal
{
  public abstract void onImpactSplashPotion(World paramWorld, NBTTagCompound paramNBTTagCompound, MovingObjectPosition paramMovingObjectPosition, ModifiersImpact paramModifiersImpact);
  
  public abstract RitualStatus onUpdateRitual(World paramWorld, int paramInt1, int paramInt2, int paramInt3, NBTTagCompound paramNBTTagCompound, ModifiersRitual paramModifiersRitual, ModifiersImpact paramModifiersImpact);
  
  public abstract String getUnlocalizedName();
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/Dispersal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */