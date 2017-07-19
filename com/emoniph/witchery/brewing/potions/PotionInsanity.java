/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.entity.EntityIllusionSpider;
/*    */ import com.emoniph.witchery.entity.EntityIllusionZombie;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionInsanity extends PotionBase
/*    */ {
/*    */   public PotionInsanity(int id, int color)
/*    */   {
/* 22 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void postContructInitialize()
/*    */   {
/* 27 */     setIncurable();
/* 28 */     setPermenant();
/* 29 */     hideInventoryText();
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 34 */     return duration % 20 == 13;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 39 */     if ((entity instanceof EntityPlayer)) {
/* 40 */       EntityPlayer player = (EntityPlayer)entity;
/* 41 */       World world = entity.field_70170_p;
/* 42 */       int level = Math.max(0, amplifier) + 1;
/*    */       
/* 44 */       int x = MathHelper.func_76128_c(entity.field_70165_t);
/* 45 */       int y = MathHelper.func_76128_c(entity.field_70163_u);
/* 46 */       int z = MathHelper.func_76128_c(entity.field_70161_v);
/* 47 */       if (world.field_73012_v.nextInt(level > 1 ? 30 : level > 2 ? 25 : 35) == 0) {
/* 48 */         Class<? extends EntityCreature> creatureType = null;
/* 49 */         switch (world.field_73012_v.nextInt(3)) {
/*    */         case 0: 
/*    */         default: 
/* 52 */           creatureType = com.emoniph.witchery.entity.EntityIllusionCreeper.class;
/* 53 */           break;
/*    */         case 1: 
/* 55 */           creatureType = EntityIllusionSpider.class;
/* 56 */           break;
/*    */         case 2: 
/* 58 */           creatureType = EntityIllusionZombie.class;
/*    */         }
/*    */         
/* 61 */         int MAX_DISTANCE = 9;
/* 62 */         int MIN_DISTANCE = 4;
/* 63 */         com.emoniph.witchery.infusion.Infusion.spawnCreature(world, creatureType, x, y, z, player, 4, 9);
/* 64 */       } else if ((level >= 4) && (world.field_73012_v.nextInt(20) == 0)) {
/* 65 */         SoundEffect sound = SoundEffect.NONE;
/* 66 */         switch (world.field_73012_v.nextInt(3)) {
/*    */         case 0: case 2: 
/*    */         case 3: 
/*    */         default: 
/* 70 */           sound = SoundEffect.RANDOM_EXPLODE;
/* 71 */           break;
/*    */         case 1: 
/* 73 */           sound = SoundEffect.MOB_ENDERMAN_IDLE;
/*    */         }
/*    */         
/*    */         
/* 77 */         sound.playOnlyTo((EntityPlayer)entity, 1.0F, 1.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
/*    */   {
/* 84 */     int factor = effect.func_76459_b() / 60 % 7;
/* 85 */     String s1 = I18n.func_135052_a(Witchery.resource("witchery:potion.insanity." + factor), new Object[0]);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 95 */     mc.field_71466_p.func_78261_a(s1, x + 10 + 18, y + 6, 16777215);
/* 96 */     String s = net.minecraft.potion.Potion.func_76389_a(effect);
/* 97 */     mc.field_71466_p.func_78261_a(s, x + 10 + 18, y + 6 + 10, 8355711);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionInsanity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */