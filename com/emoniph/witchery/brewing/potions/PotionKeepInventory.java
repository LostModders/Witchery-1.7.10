/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerDropsEvent;
/*    */ 
/*    */ public class PotionKeepInventory extends PotionBase implements IHandlePlayerDrops, IHandleLivingDeath
/*    */ {
/*    */   public PotionKeepInventory(int id, int color)
/*    */   {
/* 14 */     super(id, color);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void postContructInitialize() {}
/*    */   
/*    */ 
/*    */   public void onPlayerDrops(World world, EntityPlayer player, PlayerDropsEvent event, int amplifier)
/*    */   {
/* 24 */     if (!event.entityPlayer.field_70170_p.field_72995_K) {
/* 25 */       if (event.entityPlayer.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
/* 26 */         return;
/*    */       }
/* 28 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 29 */       if (playerEx != null) {
/* 30 */         playerEx.cachePlayerInventory();
/*    */       }
/* 32 */       event.setCanceled(true);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onLivingDeath(World world, EntityLivingBase entity, LivingDeathEvent event, int amplifier)
/*    */   {
/* 38 */     if ((!event.entityLiving.field_70170_p.field_72995_K) && ((event.entityLiving instanceof EntityPlayer))) {
/* 39 */       EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 40 */       if (player.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
/* 41 */         return;
/*    */       }
/* 43 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 44 */       if (playerEx != null) {
/* 45 */         playerEx.backupPlayerInventory();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionKeepInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */