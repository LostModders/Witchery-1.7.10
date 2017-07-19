/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderWitchVine
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/*  25 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  26 */     IIcon iicon = renderer.func_147777_a(block, 0);
/*     */     
/*  28 */     if (renderer.func_147744_b()) {
/*  29 */       iicon = renderer.field_147840_d;
/*     */     }
/*     */     
/*  32 */     tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  33 */     int color = block.func_149720_d(renderer.field_147845_a, x, y, z);
/*  34 */     float f = (color >> 16 & 0xFF) / 255.0F;
/*  35 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/*  36 */     float f2 = (color & 0xFF) / 255.0F;
/*  37 */     tessellator.func_78386_a(f, f1, f2);
/*     */     
/*  39 */     double d0 = iicon.func_94209_e();
/*  40 */     double d1 = iicon.func_94206_g();
/*  41 */     double d2 = iicon.func_94212_f();
/*  42 */     double d3 = iicon.func_94210_h();
/*  43 */     int l = renderer.field_147845_a.func_72805_g(x, y, z);
/*  44 */     double d4 = 0.0D;
/*  45 */     double d5 = 0.05000000074505806D;
/*     */     
/*  47 */     if (l == 5) {
/*  48 */       tessellator.func_78374_a(x + d5, y + 1 + d4, z + 1 + d4, d0, d1);
/*  49 */       tessellator.func_78374_a(x + d5, y + 0 - d4, z + 1 + d4, d0, d3);
/*  50 */       tessellator.func_78374_a(x + d5, y + 0 - d4, z + 0 - d4, d2, d3);
/*  51 */       tessellator.func_78374_a(x + d5, y + 1 + d4, z + 0 - d4, d2, d1);
/*  52 */       tessellator.func_78381_a();
/*     */       
/*  54 */       tessellator.func_78382_b();
/*  55 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  56 */       tessellator.func_78386_a(f, f1, f2);
/*  57 */       tessellator.func_78374_a(x - d5, y + 0 - d4, z + 1 + d4, d2, d3);
/*  58 */       tessellator.func_78374_a(x - d5, y + 1 + d4, z + 1 + d4, d2, d1);
/*  59 */       tessellator.func_78374_a(x - d5, y + 1 + d4, z + 0 - d4, d0, d1);
/*  60 */       tessellator.func_78374_a(x - d5, y + 0 - d4, z + 0 - d4, d0, d3);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  65 */     if (l == 4) {
/*  66 */       tessellator.func_78374_a(x + 1 - d5, y + 0 - d4, z + 1 + d4, d2, d3);
/*  67 */       tessellator.func_78374_a(x + 1 - d5, y + 1 + d4, z + 1 + d4, d2, d1);
/*  68 */       tessellator.func_78374_a(x + 1 - d5, y + 1 + d4, z + 0 - d4, d0, d1);
/*  69 */       tessellator.func_78374_a(x + 1 - d5, y + 0 - d4, z + 0 - d4, d0, d3);
/*     */       
/*  71 */       tessellator.func_78381_a();
/*     */       
/*  73 */       tessellator.func_78382_b();
/*  74 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  75 */       tessellator.func_78386_a(f, f1, f2);
/*  76 */       tessellator.func_78374_a(x + 1.0D + d5, y + 1 + d4, z + 1 + d4, d0, d1);
/*  77 */       tessellator.func_78374_a(x + 1.0D + d5, y + 0 - d4, z + 1 + d4, d0, d3);
/*  78 */       tessellator.func_78374_a(x + 1.0D + d5, y + 0 - d4, z + 0 - d4, d2, d3);
/*  79 */       tessellator.func_78374_a(x + 1.0D + d5, y + 1 + d4, z + 0 - d4, d2, d1);
/*     */     }
/*     */     
/*  82 */     if (l == 3) {
/*  83 */       tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z + d5, d2, d3);
/*  84 */       tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z + d5, d2, d1);
/*  85 */       tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z + d5, d0, d1);
/*  86 */       tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z + d5, d0, d3);
/*  87 */       tessellator.func_78381_a();
/*     */       
/*  89 */       tessellator.func_78382_b();
/*  90 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  91 */       tessellator.func_78386_a(f, f1, f2);
/*  92 */       tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z - d5, d0, d1);
/*  93 */       tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z - d5, d0, d3);
/*  94 */       tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z - d5, d2, d3);
/*  95 */       tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z - d5, d2, d1);
/*     */     }
/*     */     
/*  98 */     if (l == 2) {
/*  99 */       tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z + 1 - d5, d0, d1);
/* 100 */       tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z + 1 - d5, d0, d3);
/* 101 */       tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z + 1 - d5, d2, d3);
/* 102 */       tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z + 1 - d5, d2, d1);
/* 103 */       tessellator.func_78381_a();
/*     */       
/* 105 */       tessellator.func_78382_b();
/* 106 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/* 107 */       tessellator.func_78386_a(f, f1, f2);
/* 108 */       tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z + 1.0D + d5, d2, d3);
/* 109 */       tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z + 1.0D + d5, d2, d1);
/* 110 */       tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z + 1.0D + d5, d0, d1);
/* 111 */       tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z + 1.0D + d5, d0, d3);
/*     */     }
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 123 */     return Witchery.proxy.getVineRenderId();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/RenderWitchVine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */