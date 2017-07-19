package com.emoniph.witchery.entity;

import net.minecraft.entity.player.EntityPlayer;

public abstract interface IOwnable
{
  public abstract String getOwnerName();
  
  public abstract void setOwner(String paramString);
  
  public abstract EntityPlayer getOwnerEntity();
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/IOwnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */