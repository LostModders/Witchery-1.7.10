/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.world.BlockEvent.BreakEvent;
/*    */ 
/*    */ 
/*    */ public class BlockProtect
/*    */ {
/*    */   public static boolean canBreak(Block block, World world)
/*    */   {
/* 19 */     return canBreak(block, world, true);
/*    */   }
/*    */   
/*    */   public static boolean canBreak(Block block, World world, boolean denyContainers) {
/* 23 */     if ((block != null) && (block.hasTileEntity(0))) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     return (block != Blocks.field_150380_bt) && (block != Blocks.field_150357_h) && (block != Witchery.Blocks.FORCE) && (block != Witchery.Blocks.BARRIER);
/*    */   }
/*    */   
/*    */   public static boolean canBreak(int x, int y, int z, World world) {
/* 31 */     return canBreak(x, y, z, world, true);
/*    */   }
/*    */   
/*    */   public static boolean canBreak(int x, int y, int z, World world, boolean denyContainers) {
/* 35 */     Block block = world.func_147439_a(x, y, z);
/* 36 */     return canBreak(block, world, denyContainers);
/*    */   }
/*    */   
/*    */   public static boolean checkModsForBreakOK(World world, int x, int y, int z, EntityLivingBase entity) {
/* 40 */     return checkModsForBreakOK(world, x, y, z, world.func_147439_a(x, y, z), world.func_72805_g(x, y, z), entity);
/*    */   }
/*    */   
/*    */   public static boolean checkModsForBreakOK(World world, int x, int y, int z, Block block, int meta, EntityLivingBase entity) {
/* 44 */     boolean allowBreak = block.func_149712_f(world, x, y, z) != -1.0F;
/* 45 */     if ((allowBreak) && (entity != null) && ((entity instanceof EntityPlayer)) && (Config.instance().allowBlockBreakEvents)) {
/* 46 */       BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, world, block, meta, (EntityPlayer)entity);
/* 47 */       event.setCanceled(false);
/* 48 */       MinecraftForge.EVENT_BUS.post(event);
/* 49 */       allowBreak = !event.isCanceled();
/*    */     }
/*    */     
/* 52 */     return allowBreak;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockProtect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */