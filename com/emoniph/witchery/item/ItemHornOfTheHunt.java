/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*    */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemHornOfTheHunt extends ItemBase
/*    */ {
/*    */   public ItemHornOfTheHunt()
/*    */   {
/* 17 */     this.autoGenerateTooltip = true;
/* 18 */     func_77656_e(1);
/* 19 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */   public EnumAction func_77661_b(ItemStack itemstack)
/*    */   {
/* 24 */     return EnumAction.bow;
/*    */   }
/*    */   
/*    */   public int func_77626_a(ItemStack itemstack)
/*    */   {
/* 29 */     return TimeUtil.secsToTicks(2);
/*    */   }
/*    */   
/*    */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*    */   {
/* 34 */     if ((!player.field_70170_p.field_72995_K) && (countdown == 1)) {
/* 35 */       SoundEffect.WITCHERY_RANDOM_HORN.playAtPlayer(player.field_70170_p, player, 1.0F, 1.0F);
/* 36 */       EntityCreature creature = InfusionInfernal.spawnCreature(player.field_70170_p, EntityHornedHuntsman.class, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, player, 2, 8, com.emoniph.witchery.util.ParticleEffect.EXPLODE, SoundEffect.MOB_WITHER_SPAWN);
/*    */       
/*    */ 
/* 39 */       if (creature != null) {
/* 40 */         EntityHornedHuntsman huntsman = (EntityHornedHuntsman)creature;
/* 41 */         huntsman.causeExplosiveEntrance();
/* 42 */         huntsman.func_110163_bv();
/* 43 */         huntsman.func_82206_m();
/* 44 */         stack.func_77972_a(2, player);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 51 */     player.func_71008_a(stack, func_77626_a(stack));
/* 52 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemHornOfTheHunt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */