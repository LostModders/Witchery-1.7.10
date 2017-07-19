/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemPoppet;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteBlindness extends RiteExpandingEffect
/*    */ {
/*    */   public RiteBlindness(int radius, int height)
/*    */   {
/* 15 */     super(radius, height, true);
/*    */   }
/*    */   
/*    */   public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced)
/*    */   {
/* 20 */     double radiusSq = radius * radius;
/* 21 */     double minSq = Math.max(0, (radius - 1) * (radius - 1));
/* 22 */     for (Object obj : world.field_73010_i) {
/* 23 */       EntityPlayer victim = (EntityPlayer)obj;
/* 24 */       double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/* 25 */       if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
/* 26 */         if (com.emoniph.witchery.Witchery.Items.POPPET.voodooProtectionActivated(player, null, victim, 6))
/* 27 */           return false;
/* 28 */         if (!victim.func_70644_a(Potion.field_76440_q)) {
/* 29 */           victim.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, (enhanced ? 5 : 2) * 'Ұ', 0));
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 34 */     for (Object obj : world.field_72996_f) {
/* 35 */       if ((obj instanceof EntityLiving)) {
/* 36 */         EntityLiving victim = (EntityLiving)obj;
/* 37 */         double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/* 38 */         if ((distanceSq > minSq) && (distanceSq <= radiusSq) && 
/* 39 */           (!victim.func_70644_a(Potion.field_76440_q))) {
/* 40 */           victim.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, (enhanced ? 5 : 2) * 'Ұ', 0));
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteBlindness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */