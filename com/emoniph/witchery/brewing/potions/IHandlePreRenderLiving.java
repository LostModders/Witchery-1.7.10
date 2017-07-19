package com.emoniph.witchery.brewing.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;

public abstract interface IHandlePreRenderLiving
{
  public abstract PotionBase getPotion();
  
  @SideOnly(Side.CLIENT)
  public abstract void onLivingRender(World paramWorld, EntityLivingBase paramEntityLivingBase, RenderLivingEvent.Pre paramPre, int paramInt);
}


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/IHandlePreRenderLiving.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */