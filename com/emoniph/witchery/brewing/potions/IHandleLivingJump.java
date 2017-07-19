package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public abstract interface IHandleLivingJump
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingJump(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingEvent.LivingJumpEvent paramLivingJumpEvent, int paramInt);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandleLivingJump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */