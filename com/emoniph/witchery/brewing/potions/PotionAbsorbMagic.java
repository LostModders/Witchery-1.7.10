/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionAbsorbMagic extends PotionBase implements IHandleLivingHurt
/*    */ {
/*    */   public PotionAbsorbMagic(int id, int color)
/*    */   {
/* 13 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 18 */     if ((!world.field_72995_K) && 
/* 19 */       (event.source.func_82725_o())) {
/* 20 */       float damageAbsorbed = event.ammount * 0.2F * (amplifier + 1);
/* 21 */       event.ammount -= damageAbsorbed;
/* 22 */       if ((entity instanceof EntityPlayer)) {
/* 23 */         EntityPlayer player = (EntityPlayer)entity;
/* 24 */         int maxEnergy = Infusion.getMaxEnergy(player);
/* 25 */         if ((maxEnergy > 0) && (damageAbsorbed > 1.0F)) {
/* 26 */           int energy = Infusion.getCurrentEnergy(player);
/* 27 */           Infusion.setCurrentEnergy(player, Math.min(energy + (int)damageAbsorbed, maxEnergy));
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean handleAllHurtEvents()
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionAbsorbMagic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */