/*    */ package com.emoniph.witchery.item.brew;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*    */ import com.emoniph.witchery.item.ItemGeneral.Drinkable;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class BrewSoul extends ItemGeneral.Drinkable
/*    */ {
/*    */   private final SymbolEffect effect;
/*    */   
/*    */   public BrewSoul(int damageValue, String unlocalizedName, SymbolEffect effect)
/*    */   {
/* 13 */     super(damageValue, unlocalizedName, 1, new net.minecraft.potion.PotionEffect[0]);
/* 14 */     this.effect = effect;
/* 15 */     setPotion(true);
/*    */   }
/*    */   
/*    */   public void onDrunk(net.minecraft.world.World world, EntityPlayer player, net.minecraft.item.ItemStack itemstack)
/*    */   {
/* 20 */     this.effect.acquireKnowledge(player);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/brew/BrewSoul.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */