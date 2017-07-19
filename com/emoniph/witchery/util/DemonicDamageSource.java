/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.DamageSource;
/*    */ 
/*    */ public class DemonicDamageSource extends DamageSource
/*    */ {
/*    */   protected Entity damageSourceEntity;
/*    */   
/*    */   public DemonicDamageSource(Entity par2Entity)
/*    */   {
/* 16 */     super("magic");
/* 17 */     this.damageSourceEntity = par2Entity;
/* 18 */     func_76348_h();
/* 19 */     func_82726_p();
/* 20 */     func_151518_m();
/*    */   }
/*    */   
/*    */   public Entity func_76346_g()
/*    */   {
/* 25 */     return this.damageSourceEntity;
/*    */   }
/*    */   
/*    */   public net.minecraft.util.IChatComponent func_151519_b(EntityLivingBase p_151519_1_)
/*    */   {
/* 30 */     ItemStack itemstack = (this.damageSourceEntity instanceof EntityLivingBase) ? ((EntityLivingBase)this.damageSourceEntity).func_70694_bm() : null;
/*    */     
/* 32 */     String s = "death.attack." + this.field_76373_n;
/* 33 */     String s1 = s + ".item";
/* 34 */     return (itemstack != null) && (itemstack.func_82837_s()) && (net.minecraft.util.StatCollector.func_94522_b(s1)) ? new ChatComponentTranslation(s1, new Object[] { p_151519_1_.func_145748_c_(), this.damageSourceEntity.func_145748_c_(), itemstack.func_151000_E() }) : new ChatComponentTranslation(s, new Object[] { p_151519_1_.func_145748_c_(), this.damageSourceEntity.func_145748_c_() });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_76350_n()
/*    */   {
/* 41 */     return (this.damageSourceEntity != null) && ((this.damageSourceEntity instanceof EntityLivingBase)) && (!(this.damageSourceEntity instanceof EntityPlayer));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/DemonicDamageSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */