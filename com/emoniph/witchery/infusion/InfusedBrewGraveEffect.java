/*    */ package com.emoniph.witchery.infusion;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.potions.PotionEnslaved;
/*    */ import com.emoniph.witchery.util.CreatureUtil;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class InfusedBrewGraveEffect extends InfusedBrewEffect
/*    */ {
/*    */   private static final String LAST_USE_TIME_KEY = "WITCBrewGraveTime";
/*    */   private static final long COOLDOWN_TICKS = 200L;
/*    */   
/*    */   public InfusedBrewGraveEffect(int id, long durationMS)
/*    */   {
/* 20 */     super(id, durationMS, 16, 16);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void immediateEffect(World world, EntityPlayer player, ItemStack stack) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void regularEffect(World world, EntityPlayer player) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean tryUseEffect(EntityPlayer player, MovingObjectPosition mop)
/*    */   {
/* 36 */     if (isActive(player)) {
/* 37 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 38 */       long lastUseTicks = nbtPlayer.func_74763_f("WITCBrewGraveTime");
/* 39 */       long currentServerTime = TimeUtil.getServerTimeInTicks();
/* 40 */       if ((currentServerTime - lastUseTicks > 200L) && 
/* 41 */         (mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) && (CreatureUtil.isUndead(mop.field_72308_g))) {
/* 42 */         EntityLiving living = (EntityLiving)mop.field_72308_g;
/* 43 */         if (!PotionEnslaved.isMobEnslavedBy(living, player)) {
/* 44 */           PotionEnslaved.setEnslaverForMob(living, player);
/* 45 */           nbtPlayer.func_74772_a("WITCBrewGraveTime", currentServerTime);
/* 46 */           return true;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/InfusedBrewGraveEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */