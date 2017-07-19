/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockStockade;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class RenderStockade
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
/*     */   {
/*  23 */     drawPost(0.3D, 0.3D, 0.7D, 0.7D, renderer, block, metadata, false, false);
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/*  28 */     BlockStockade blockStockade = (BlockStockade)block;
/*  29 */     boolean flag = true;
/*  30 */     boolean drawLR = false;
/*  31 */     boolean drawUD = false;
/*     */     
/*  33 */     if ((blockStockade.canConnectFenceTo(world, x - 1, y, z)) || (blockStockade.canConnectFenceTo(world, x + 1, y, z))) {
/*  34 */       drawLR = true;
/*     */     }
/*     */     
/*  37 */     if ((blockStockade.canConnectFenceTo(world, x, y, z - 1)) || (blockStockade.canConnectFenceTo(world, x, y, z + 1))) {
/*  38 */       drawUD = true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  46 */     boolean oneAbove = blockStockade.canConnectFenceTo(world, x, y + 1, z);
/*     */     
/*  48 */     if ((!drawLR) && (!drawUD)) {
/*  49 */       drawPost(0.3D, 0.3D, 0.7D, 0.7D, x, y, z, renderer, blockStockade, oneAbove);
/*     */     }
/*     */     
/*  52 */     int diagonal = 0;
/*     */     
/*     */ 
/*  55 */     if ((drawLR) && (drawUD) && (diagonal > 1)) {
/*  56 */       drawPost(0.05D, 0.05D, 0.45D, 0.45D, x, y, z, renderer, blockStockade, oneAbove);
/*  57 */       drawPost(0.55D, 0.05D, 0.95D, 0.45D, x, y, z, renderer, blockStockade, oneAbove);
/*     */       
/*  59 */       drawPost(0.05D, 0.55D, 0.45D, 0.95D, x, y, z, renderer, blockStockade, oneAbove);
/*  60 */       drawPost(0.55D, 0.55D, 0.95D, 0.95D, x, y, z, renderer, blockStockade, oneAbove);
/*  61 */       flag = true;
/*     */     }
/*  63 */     else if ((drawLR) && (drawUD)) {
/*  64 */       drawPost(0.05D, 0.3D, 0.45D, 0.7D, x, y, z, renderer, blockStockade, oneAbove, true);
/*  65 */       drawPost(0.55D, 0.3D, 0.95D, 0.7D, x, y, z, renderer, blockStockade, oneAbove, false);
/*  66 */       drawPost(0.3D, 0.05D, 0.7D, 0.45D, x, y, z, renderer, blockStockade, oneAbove, true);
/*  67 */       drawPost(0.3D, 0.55D, 0.7D, 0.95D, x, y, z, renderer, blockStockade, oneAbove, false);
/*  68 */       flag = true;
/*     */     }
/*     */     else {
/*  71 */       if (drawLR) {
/*  72 */         drawPost(0.05D, 0.3D, 0.45D, 0.7D, x, y, z, renderer, blockStockade, oneAbove);
/*  73 */         drawPost(0.55D, 0.3D, 0.95D, 0.7D, x, y, z, renderer, blockStockade, oneAbove);
/*  74 */         flag = true;
/*     */       }
/*     */       
/*  77 */       if (drawUD) {
/*  78 */         drawPost(0.3D, 0.05D, 0.7D, 0.45D, x, y, z, renderer, blockStockade, oneAbove);
/*  79 */         drawPost(0.3D, 0.55D, 0.7D, 0.95D, x, y, z, renderer, blockStockade, oneAbove);
/*  80 */         flag = true;
/*     */       }
/*     */     }
/*     */     
/*  84 */     blockStockade.func_149719_a(world, x, y, z);
/*  85 */     return flag;
/*     */   }
/*     */   
/*     */   private void drawPost(double xMin, double zMin, double xMax, double zMax, int x, int y, int z, RenderBlocks renderer, Block block, boolean oneAbove)
/*     */   {
/*  90 */     drawPost(xMin, zMin, xMax, zMax, x, y, z, renderer, block, oneAbove, false);
/*     */   }
/*     */   
/*     */   private void drawPost(double xMin, double zMin, double xMax, double zMax, int x, int y, int z, RenderBlocks renderer, Block block, boolean oneAbove, boolean extra)
/*     */   {
/*  95 */     if (!oneAbove) {
/*  96 */       double startY = extra ? 0.6D : 0.5D;
/*     */       
/*  98 */       renderer.func_147782_a(xMin, 0.0D, zMin, xMax, startY, zMax);
/*  99 */       renderer.func_147784_q(block, x, y, z);
/*     */       try
/*     */       {
/* 102 */         ((BlockStockade)block).setTipTexture(true);
/* 103 */         double dx = 0.04D;
/* 104 */         double dy = 0.084D;
/*     */         
/* 106 */         for (int i = 0; i < 4; i++)
/*     */         {
/*     */ 
/* 109 */           double reduce = (i + 1) * dx;
/*     */           
/* 111 */           renderer.func_147782_a(xMin + reduce, startY + i * dy, zMin + reduce, xMax - reduce, startY + (i + 1) * dy, zMax - reduce);
/* 112 */           renderer.func_147784_q(block, x, y, z);
/*     */         }
/*     */       } finally {
/* 115 */         ((BlockStockade)block).setTipTexture(false);
/*     */       }
/*     */     } else {
/* 118 */       renderer.func_147782_a(xMin, 0.0D, zMin, xMax, 1.0D, zMax);
/* 119 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawPost(double xMin, double zMin, double xMax, double zMax, RenderBlocks renderer, Block block, int meta, boolean oneAbove, boolean extra) {
/* 124 */     if (!oneAbove) {
/* 125 */       double startY = extra ? 0.6D : 0.5D;
/*     */       
/* 127 */       renderer.func_147782_a(xMin, 0.0D, zMin, xMax, startY, zMax);
/* 128 */       renderStandardInvBlock(renderer, block, meta);
/*     */       try
/*     */       {
/* 131 */         ((BlockStockade)block).setTipTexture(true);
/* 132 */         double dx = 0.04D;
/* 133 */         double dy = 0.084D;
/*     */         
/* 135 */         for (int i = 0; i < 4; i++)
/*     */         {
/*     */ 
/* 138 */           double reduce = (i + 1) * dx;
/*     */           
/* 140 */           renderer.func_147782_a(xMin + reduce, startY + i * dy, zMin + reduce, xMax - reduce, startY + (i + 1) * dy, zMax - reduce);
/* 141 */           renderStandardInvBlock(renderer, block, meta);
/*     */         }
/*     */       } finally {
/* 144 */         ((BlockStockade)block).setTipTexture(false);
/*     */       }
/*     */     } else {
/* 147 */       renderer.func_147782_a(xMin, 0.0D, zMin, xMax, 1.0D, zMax);
/* 148 */       renderStandardInvBlock(renderer, block, meta);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
/*     */   {
/* 154 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 155 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 156 */     tessellator.func_78382_b();
/* 157 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/* 158 */     renderblocks.func_147768_a(block, 0.0D, 0.0D, 0.0D, getIcon(block.func_149691_a(0, meta)));
/* 159 */     tessellator.func_78381_a();
/* 160 */     tessellator.func_78382_b();
/* 161 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 162 */     renderblocks.func_147806_b(block, 0.0D, 0.0D, 0.0D, getIcon(block.func_149691_a(1, meta)));
/* 163 */     tessellator.func_78381_a();
/* 164 */     tessellator.func_78382_b();
/* 165 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/* 166 */     renderblocks.func_147761_c(block, 0.0D, 0.0D, 0.0D, getIcon(block.func_149691_a(2, meta)));
/* 167 */     tessellator.func_78381_a();
/* 168 */     tessellator.func_78382_b();
/* 169 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 170 */     renderblocks.func_147734_d(block, 0.0D, 0.0D, 0.0D, getIcon(block.func_149691_a(3, meta)));
/* 171 */     tessellator.func_78381_a();
/* 172 */     tessellator.func_78382_b();
/* 173 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 174 */     renderblocks.func_147798_e(block, 0.0D, 0.0D, 0.0D, getIcon(block.func_149691_a(4, meta)));
/* 175 */     tessellator.func_78381_a();
/* 176 */     tessellator.func_78382_b();
/* 177 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/* 178 */     renderblocks.func_147764_f(block, 0.0D, 0.0D, 0.0D, getIcon(block.func_149691_a(5, meta)));
/* 179 */     tessellator.func_78381_a();
/* 180 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   private static IIcon getIcon(IIcon icon)
/*     */   {
/* 185 */     if (icon != null)
/* 186 */       return icon;
/* 187 */     return ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110575_b)).func_110572_b("missingno");
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 192 */     return true;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 197 */     return Witchery.proxy.getStockageRenderId();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderStockade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */