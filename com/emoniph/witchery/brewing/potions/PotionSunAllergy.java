/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionSunAllergy extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionSunAllergy(int id, int color)
/*    */   {
/* 13 */     super(id, true, color);
/* 14 */     setIncurable();
/*    */   }
/*    */   
/*    */ 
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 20 */     if ((!world.field_72995_K) && (world.func_72820_D() % 20L == 0L) && 
/* 21 */       (world.func_72935_r())) {
/* 22 */       float f = entity.func_70013_c(1.0F);
/* 23 */       if ((f > 0.5F) && (!entity.func_70090_H()) && (world.field_73012_v.nextFloat() < f - 0.45F) && (world.func_72937_j(MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v))))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/* 28 */         boolean burnEntity = true;
/* 29 */         ItemStack itemstack = entity.func_71124_b(4);
/*    */         
/* 31 */         if ((itemstack != null) && (!(entity instanceof EntityPlayer))) {
/* 32 */           if (itemstack.func_77984_f()) {
/* 33 */             itemstack.func_77964_b(itemstack.func_77952_i() + world.field_73012_v.nextInt(2));
/*    */             
/* 35 */             if (itemstack.func_77952_i() >= itemstack.func_77958_k()) {
/* 36 */               entity.func_70669_a(itemstack);
/* 37 */               entity.func_70062_b(4, (ItemStack)null);
/*    */             }
/*    */           }
/*    */           
/* 41 */           burnEntity = false;
/*    */         }
/*    */         
/* 44 */         if (burnEntity) {
/* 45 */           if ((entity instanceof EntityPlayer)) {
/* 46 */             entity.func_70097_a(net.minecraft.util.DamageSource.field_76380_i, amplifier >= 3 ? 2.0F : 1.0F);
/*    */           } else {
/* 48 */             entity.func_70015_d(8);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionSunAllergy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */