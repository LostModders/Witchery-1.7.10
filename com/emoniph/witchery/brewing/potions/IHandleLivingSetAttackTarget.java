package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public abstract interface IHandleLivingSetAttackTarget
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingSetAttackTarget(World paramWorld, EntityLiving paramEntityLiving, LivingSetAttackTargetEvent paramLivingSetAttackTargetEvent, int paramInt);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandleLivingSetAttackTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */