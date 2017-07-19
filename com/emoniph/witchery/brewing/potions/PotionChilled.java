/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionChilled extends PotionBase implements IHandleLivingHurt
/*    */ {
/*    */   public PotionChilled(int id, int color)
/*    */   {
/* 13 */     super(id, color);
/*    */   }
/*    */   
/*    */ 
/*    */   public void postContructInitialize()
/*    */   {
/* 19 */     func_111184_a(SharedMonsterAttributes.field_111263_d, "7A20B8CD-7A97-4800-A7DC-5B464E31A11A", -0.1D, 2);
/*    */   }
/*    */   
/*    */   public boolean handleAllHurtEvents() {
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 28 */     if ((!world.field_72995_K) && 
/* 29 */       (event.source.func_76347_k())) {
/* 30 */       event.ammount = Math.max(event.ammount - (1 + amplifier), Math.min(event.ammount, amplifier >= 2 ? 0.0F : 1.0F));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 38 */     int k = 25 >> amplifier;
/* 39 */     return duration % k == 0;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 44 */     if (((entity instanceof net.minecraft.entity.monster.EntityBlaze)) || (amplifier >= 2)) {
/* 45 */       entity.func_70097_a(DamageSource.field_76376_m, 1.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionChilled.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */