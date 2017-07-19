/*    */ package com.emoniph.witchery.entity;
/*    */ 
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ 
/*    */ public class EntityIllusionSpider extends EntityIllusion
/*    */ {
/*    */   public EntityIllusionSpider(net.minecraft.world.World world)
/*    */   {
/*  9 */     super(world);
/*    */   }
/*    */   
/*    */   protected SoundEffect getFakeLivingSound()
/*    */   {
/* 14 */     return SoundEffect.MOB_SPIDER_SAY;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityIllusionSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */