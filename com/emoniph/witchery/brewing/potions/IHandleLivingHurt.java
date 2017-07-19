package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract interface IHandleLivingHurt
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingHurt(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingHurtEvent paramLivingHurtEvent, int paramInt);
  
  public abstract boolean handleAllHurtEvents();
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandleLivingHurt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */