package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public abstract interface IHandleLivingAttack
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingAttack(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingAttackEvent paramLivingAttackEvent, int paramInt);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandleLivingAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */