/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.entity.EntityWitchProjectile;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CreaturePowerSpider extends CreaturePower
/*    */ {
/*    */   public CreaturePowerSpider(int powerID, Class<? extends EntitySpider> creatureType)
/*    */   {
/* 19 */     super(powerID, creatureType);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 24 */     if (!world.field_72995_K) {
/* 25 */       world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (world.field_73012_v.nextFloat() * 0.4F + 0.8F));
/* 26 */       world.func_72838_d(new EntityWitchProjectile(world, player, Witchery.Items.GENERIC.itemWeb));
/*    */     }
/*    */   }
/*    */   
/*    */   public void onUpdate(World world, EntityPlayer player)
/*    */   {
/* 32 */     int blockX = MathHelper.func_76128_c(player.field_70165_t);
/* 33 */     int blockY = MathHelper.func_76128_c(player.field_70163_u + 1.0D);
/* 34 */     int blockZ = MathHelper.func_76128_c(player.field_70161_v);
/*    */     
/* 36 */     if (world.func_147439_a(blockX, blockY, blockZ).func_149688_o().func_76220_a()) {
/* 37 */       player.field_70181_x *= 0.6D;
/*    */     }
/*    */     
/* 40 */     if (player.field_70123_F) {
/* 41 */       player.field_70181_x = 0.3D;
/*    */     }
/*    */     
/* 44 */     if ((player.func_70093_af()) && ((player instanceof EntityPlayer)) && (player.field_70123_F)) {
/* 45 */       player.field_70181_x = 0.0D;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */