/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.util.Dye;
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.IEntityLivingData;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.ItemDye;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteFertility
/*    */   extends RiteExpandingEffect
/*    */ {
/*    */   public RiteFertility(int radius, int height)
/*    */   {
/* 23 */     super(radius, height, false);
/*    */   }
/*    */   
/*    */   public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced)
/*    */   {
/* 28 */     Block blockID = world.func_147439_a(posX, posY, posZ);
/* 29 */     if ((blockID != Blocks.field_150346_d) || (blockID != Blocks.field_150349_c) || (blockID != Blocks.field_150391_bh) || (blockID != Blocks.field_150458_ak) || (world.field_73012_v.nextInt(5) == 0))
/*    */     {
/* 31 */       if (player != null) {
/* 32 */         ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, posX, posY, posZ, player);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced)
/*    */   {
/* 39 */     double radiusSq = radius * radius;
/* 40 */     double minSq = Math.max(0, (radius - 1) * (radius - 1));
/* 41 */     for (Object obj : world.field_73010_i) {
/* 42 */       EntityPlayer victim = (EntityPlayer)obj;
/* 43 */       double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/* 44 */       if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
/* 45 */         if (victim.func_70644_a(Potion.field_76431_k)) {
/* 46 */           victim.func_82170_o(Potion.field_76431_k.field_76415_H);
/*    */         }
/*    */         
/* 49 */         if (victim.func_70644_a(Potion.field_76440_q)) {
/* 50 */           victim.func_82170_o(Potion.field_76440_q.field_76415_H);
/*    */         }
/*    */         
/* 53 */         if (victim.func_70644_a(Potion.field_76436_u)) {
/* 54 */           victim.func_82170_o(Potion.field_76436_u.field_76415_H);
/*    */         }
/*    */         
/* 57 */         if (enhanced) {
/* 58 */           victim.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 300, 1));
/* 59 */           victim.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 2400));
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 64 */     ArrayList<EntityZombie> villagersToZombify = new ArrayList();
/*    */     
/* 66 */     for (Object obj : world.field_72996_f) {
/* 67 */       if ((obj instanceof EntityZombie)) {
/* 68 */         EntityZombie victim = (EntityZombie)obj;
/* 69 */         if (victim.func_82231_m()) {
/* 70 */           double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/* 71 */           if ((distanceSq > minSq) && (distanceSq <= radiusSq))
/*    */           {
/* 73 */             Log.instance().debug(String.format("Try curing zombie %f %f %f", new Object[] { Double.valueOf(distanceSq), Double.valueOf(minSq), Double.valueOf(radiusSq) }));
/* 74 */             villagersToZombify.add(victim);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 81 */     for (EntityZombie victim : villagersToZombify) {
/* 82 */       EntityVillager entityvillager = new EntityVillager(world);
/* 83 */       entityvillager.func_82149_j(victim);
/* 84 */       entityvillager.func_110161_a((IEntityLivingData)null);
/* 85 */       entityvillager.func_82187_q();
/*    */       
/* 87 */       if (victim.func_70631_g_())
/*    */       {
/* 89 */         entityvillager.func_70873_a(41536);
/*    */       }
/*    */       
/* 92 */       world.func_72900_e(victim);
/* 93 */       world.func_72838_d(entityvillager);
/* 94 */       entityvillager.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
/* 95 */       world.func_72889_a((EntityPlayer)null, 1017, (int)entityvillager.field_70165_t, (int)entityvillager.field_70163_u, (int)entityvillager.field_70161_v, 0);
/*    */     }
/*    */     
/*    */ 
/* 99 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteFertility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */