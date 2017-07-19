/*    */ package com.emoniph.witchery.entity;
/*    */ 
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ 
/*    */ public class EntityIllusionZombie extends EntityIllusion
/*    */ {
/*    */   public EntityIllusionZombie(net.minecraft.world.World world)
/*    */   {
/*  9 */     super(world);
/*    */   }
/*    */   
/*    */   protected SoundEffect getFakeLivingSound()
/*    */   {
/* 14 */     return SoundEffect.MOB_ZOMBIE_SAY;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityIllusionZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */