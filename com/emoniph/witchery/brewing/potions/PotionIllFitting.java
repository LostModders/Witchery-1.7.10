/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityFollower;
/*    */ import com.emoniph.witchery.entity.EntityReflection;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionIllFitting extends PotionBase
/*    */ {
/*    */   public PotionIllFitting(int id, int color)
/*    */   {
/* 14 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void postContructInitialize()
/*    */   {
/* 19 */     setPermenant();
/* 20 */     setIncurable();
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 25 */     if (duration % 15 == 0) {
/* 26 */       switch (amplifier) {
/*    */       case 3: 
/* 28 */         return duration <= 60;
/*    */       case 2: 
/* 30 */         return duration <= 45;
/*    */       case 1: 
/* 32 */         return duration <= 30;
/*    */       }
/* 34 */       return duration <= 15;
/*    */     }
/*    */     
/* 37 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 43 */     World world = entity.field_70170_p;
/* 44 */     if ((!world.field_72995_K) && (!isTargetBanned(entity))) {
/* 45 */       int slot = world.field_73012_v.nextInt(4) + 1;
/* 46 */       net.minecraft.item.ItemStack armorPiece = entity.func_71124_b(slot);
/* 47 */       if (armorPiece != null) {
/* 48 */         entity.func_70062_b(slot, null);
/* 49 */         EntityItem droppedItem = entity.func_70099_a(armorPiece, 0.0F);
/* 50 */         droppedItem.field_145804_b = (5 + 5 * amplifier);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean isTargetBanned(EntityLivingBase entity) {
/* 56 */     return ((entity instanceof EntityReflection)) || ((entity instanceof EntityFollower));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionIllFitting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */