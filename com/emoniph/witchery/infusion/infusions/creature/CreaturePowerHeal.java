/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerHeal
/*    */   extends CreaturePower
/*    */ {
/*    */   public static final int DEFAULT_CHARGES_PER_SACRIFICE = 1;
/*    */   private final int charges;
/*    */   
/*    */   public CreaturePowerHeal(int powerID, Class<? extends EntityLiving> creatureType, int charges)
/*    */   {
/* 29 */     super(powerID, creatureType);
/* 30 */     this.charges = charges;
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 35 */     player.func_70690_d(new PotionEffect(Potion.field_76432_h.field_76415_H, 10, 0));
/* 36 */     SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*    */   }
/*    */   
/*    */   public int getChargesPerSacrifice()
/*    */   {
/* 41 */     return this.charges;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerHeal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */