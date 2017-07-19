package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public abstract interface IHandlePlayerDrops
{
  public abstract PotionBase getPotion();
  
  public abstract void onPlayerDrops(World paramWorld, EntityPlayer paramEntityPlayer, PlayerDropsEvent paramPlayerDropsEvent, int paramInt);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandlePlayerDrops.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */