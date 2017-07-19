/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.BlockActionCircle;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class PotionSnowTrail extends PotionBase
/*    */ {
/*    */   public PotionSnowTrail(int id, int color)
/*    */   {
/* 19 */     super(id, color);
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 24 */     return duration % 10 == 0;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 29 */     if (!entity.field_70170_p.field_72995_K) {
/* 30 */       for (int l = 0; l < 4; l++) {
/* 31 */         int i = MathHelper.func_76128_c(entity.field_70165_t + (l % 2 * 2 - 1) * 0.25F);
/* 32 */         int j = MathHelper.func_76128_c(entity.field_70163_u);
/* 33 */         int k = MathHelper.func_76128_c(entity.field_70161_v + (l / 2 % 2 * 2 - 1) * 0.25F);
/*    */         
/* 35 */         if (entity.field_70170_p.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a) {
/* 36 */           float temp = entity.field_70170_p.func_72807_a(i, k).func_150564_a(i, j, k);
/* 37 */           if ((temp < 1.6F) && (Blocks.field_150431_aC.func_149742_c(entity.field_70170_p, i, j, k))) {
/* 38 */             entity.field_70170_p.func_147449_b(i, j, k, Blocks.field_150431_aC);
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 43 */       if (((entity instanceof net.minecraft.entity.monster.EntitySnowman)) && (entity.field_70170_p.field_73012_v.nextInt(20) == 0)) {
/* 44 */         entity.field_70170_p.func_72876_a(entity, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 3.0F, false);
/* 45 */         Coord coord = new Coord(entity);
/* 46 */         createSnowCovering(entity.field_70170_p, coord.x, coord.y, coord.z, 8, null);
/* 47 */         entity.func_70106_y();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void createSnowCovering(World world, int x, int y, int z, int radius, EntityPlayer source) {
/* 53 */     if (com.emoniph.witchery.util.BlockProtect.checkModsForBreakOK(world, x, y, z, source)) {
/* 54 */       new BlockActionCircle()
/*    */       {
/*    */         public void onBlock(World world, int x, int y, int z) {
/* 57 */           int maxSearch = 8;
/* 58 */           if (world.func_147437_c(x, y, z)) {
/* 59 */             for (int i = 1; i < 8; i++) {
/* 60 */               int dy = y - i;
/* 61 */               Block block = world.func_147439_a(x, dy, z);
/* 62 */               if (block.func_149688_o() != Material.field_151579_a) {
/* 63 */                 setBlockToSnow(world, x, dy + 1, z, block);
/* 64 */                 break;
/*    */               }
/*    */             }
/*    */           } else {
/* 68 */             for (int i = 1; i < 8; i++) {
/* 69 */               int dy = y + i;
/* 70 */               Block block = world.func_147439_a(x, dy, z);
/* 71 */               if (block.func_149688_o() == Material.field_151579_a) {
/* 72 */                 Block blockBelow = world.func_147439_a(x, dy - 1, z);
/* 73 */                 setBlockToSnow(world, x, dy, z, blockBelow);
/* 74 */                 break;
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */         
/*    */         private void setBlockToSnow(World world, int x, int y, int z, Block blockBelow) {
/* 81 */           if ((blockBelow.func_149662_c()) || (blockBelow.func_149688_o() == Material.field_151584_j))
/* 82 */             world.func_147449_b(x, y, z, Blocks.field_150431_aC); } }.processFilledCircle(world, x, y, z, radius);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionSnowTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */