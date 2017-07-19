package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public abstract interface IHandleLivingUpdate
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingUpdate(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingEvent.LivingUpdateEvent paramLivingUpdateEvent, int paramInt1, int paramInt2);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandleLivingUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */