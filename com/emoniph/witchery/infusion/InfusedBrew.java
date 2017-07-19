/*    */ package com.emoniph.witchery.infusion;
/*    */ 
/*    */ import com.emoniph.witchery.item.ItemGeneral.Drinkable;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class InfusedBrew extends ItemGeneral.Drinkable
/*    */ {
/*    */   private final InfusedBrewEffect effect;
/*    */   
/*    */   public InfusedBrew(int damageValue, String unlocalizedName, InfusedBrewEffect effect)
/*    */   {
/* 13 */     super(damageValue, unlocalizedName, 2, new net.minecraft.potion.PotionEffect[0]);
/* 14 */     this.effect = effect;
/* 15 */     this.potion = true;
/*    */   }
/*    */   
/*    */   public void onDrunk(net.minecraft.world.World world, EntityPlayer player, ItemStack itemstack)
/*    */   {
/* 20 */     this.effect.drunk(world, player, itemstack);
/*    */   }
/*    */   
/*    */   public boolean isInfused()
/*    */   {
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/InfusedBrew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */