package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public abstract interface IHandleHarvestDrops
{
  public abstract PotionBase getPotion();
  
  public abstract void onHarvestDrops(World paramWorld, EntityPlayer paramEntityPlayer, BlockEvent.HarvestDropsEvent paramHarvestDropsEvent, int paramInt);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandleHarvestDrops.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */