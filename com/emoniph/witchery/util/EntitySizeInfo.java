/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.entity.passive.EntityHorse;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class EntitySizeInfo
/*    */ {
/*    */   public final float defaultWidth;
/*    */   public final float defaultHeight;
/*    */   public final float eyeHeight;
/*    */   public final float stepSize;
/*    */   public final boolean isDefault;
/*    */   public final TransformCreature creature;
/*    */   
/*    */   public EntitySizeInfo(EntityLivingBase entity)
/*    */   {
/* 21 */     if ((entity instanceof EntityPlayer)) {
/* 22 */       EntityPlayer player = (EntityPlayer)entity;
/* 23 */       this.creature = ExtendedPlayer.get(player).getCreatureType();
/* 24 */       NBTTagCompound nbtEntity = entity.getEntityData();
/* 25 */       switch (this.creature) {
/*    */       case NONE: 
/*    */       case PLAYER: 
/*    */       default: 
/* 29 */         this.isDefault = true;
/* 30 */         this.defaultWidth = 0.6F;
/* 31 */         this.defaultHeight = 1.8F;
/* 32 */         this.stepSize = 0.5F;
/* 33 */         this.eyeHeight = player.getDefaultEyeHeight();
/* 34 */         break;
/*    */       case WOLF: 
/* 36 */         this.isDefault = false;
/* 37 */         this.defaultWidth = 0.6F;
/* 38 */         this.defaultHeight = 0.8F;
/* 39 */         this.eyeHeight = (this.defaultHeight * 0.92F);
/* 40 */         this.stepSize = 1.0F;
/* 41 */         break;
/*    */       case WOLFMAN: 
/* 43 */         this.isDefault = true;
/* 44 */         this.defaultWidth = 0.6F;
/* 45 */         this.defaultHeight = 1.8F;
/* 46 */         this.eyeHeight = player.getDefaultEyeHeight();
/* 47 */         this.stepSize = 1.0F;
/* 48 */         break;
/*    */       case BAT: 
/* 50 */         this.isDefault = false;
/* 51 */         this.defaultWidth = 0.3F;
/* 52 */         this.defaultHeight = 0.6F;
/* 53 */         this.eyeHeight = (this.defaultHeight * 0.8F);
/* 54 */         this.stepSize = 0.5F;
/* 55 */         break;
/*    */       case TOAD: 
/* 57 */         this.isDefault = false;
/* 58 */         this.defaultWidth = 0.3F;
/* 59 */         this.defaultHeight = 0.5F;
/* 60 */         this.eyeHeight = (this.defaultHeight * 0.92F);
/* 61 */         this.stepSize = 0.5F;
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 66 */       NBTTagCompound nbtEntity = entity.getEntityData();
/* 67 */       this.defaultWidth = nbtEntity.func_74760_g("WITCInitialWidth");
/* 68 */       this.defaultHeight = nbtEntity.func_74760_g("WITCInitialHeight");
/* 69 */       this.stepSize = (((entity instanceof EntityHorse)) || ((entity instanceof EntityEnderman)) ? 1.0F : 0.5F);
/* 70 */       this.eyeHeight = 0.12F;
/* 71 */       this.isDefault = true;
/* 72 */       this.creature = TransformCreature.NONE;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/EntitySizeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */