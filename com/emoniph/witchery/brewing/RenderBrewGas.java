/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderBrewGas
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/*  24 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  25 */     int l = block.func_149720_d(world, x, y, z);
/*  26 */     float red = (l >> 16 & 0xFF) / 255.0F;
/*  27 */     float green = (l >> 8 & 0xFF) / 255.0F;
/*  28 */     float blue = (l & 0xFF) / 255.0F;
/*  29 */     boolean flag = block.func_149646_a(world, x, y + 1, z, 1);
/*  30 */     boolean flag1 = block.func_149646_a(world, x, y - 1, z, 0);
/*  31 */     boolean[] aboolean = { block.func_149646_a(world, x, y, z - 1, 2), block.func_149646_a(world, x, y, z + 1, 3), block.func_149646_a(world, x - 1, y, z, 4), block.func_149646_a(world, x + 1, y, z, 5) };
/*     */     
/*  33 */     float opacityInner = 0.2F;
/*  34 */     float opacityOuter = 0.4F;
/*  35 */     renderer.field_147837_f = true;
/*     */     
/*  37 */     if ((!renderer.field_147837_f) && (!flag) && (!flag1) && (aboolean[0] == 0) && (aboolean[1] == 0) && (aboolean[2] == 0) && (aboolean[3] == 0))
/*     */     {
/*  39 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  43 */     boolean flag2 = false;
/*  44 */     float f3 = 0.5F;
/*  45 */     float f4 = 1.0F;
/*  46 */     float f5 = 0.8F;
/*  47 */     float f6 = 0.6F;
/*  48 */     double d0 = 0.0D;
/*  49 */     double d1 = 1.0D;
/*  50 */     Material material = block.func_149688_o();
/*  51 */     int i1 = world.func_72805_g(x, y, z);
/*  52 */     double d2 = 1.0D;
/*  53 */     double d3 = 1.0D;
/*  54 */     double d4 = 1.0D;
/*  55 */     double d5 = 1.0D;
/*  56 */     double d6 = 0.0010000000474974513D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  61 */     if ((renderer.field_147837_f) || (flag))
/*     */     {
/*  63 */       flag2 = true;
/*  64 */       IIcon iicon = renderer.func_147787_a(block, 1, i1);
/*  65 */       float f7 = 0.0F;
/*     */       
/*  67 */       if (f7 > -999.0F)
/*     */       {
/*  69 */         iicon = renderer.func_147787_a(block, 2, i1);
/*     */       }
/*     */       
/*  72 */       d2 -= d6;
/*  73 */       d3 -= d6;
/*  74 */       d4 -= d6;
/*  75 */       d5 -= d6;
/*     */       double d20;
/*     */       double d7;
/*     */       double d14;
/*     */       double d8;
/*     */       double d16;
/*     */       double d10;
/*     */       double d18;
/*     */       double d12;
/*     */       double d20;
/*  85 */       if (f7 < -999.0F)
/*     */       {
/*  87 */         double d7 = iicon.func_94214_a(0.0D);
/*  88 */         double d14 = iicon.func_94207_b(0.0D);
/*  89 */         double d8 = d7;
/*  90 */         double d16 = iicon.func_94207_b(16.0D);
/*  91 */         double d10 = iicon.func_94214_a(16.0D);
/*  92 */         double d18 = d16;
/*  93 */         double d12 = d10;
/*  94 */         d20 = d14;
/*     */       }
/*     */       else
/*     */       {
/*  98 */         float f9 = MathHelper.func_76126_a(f7) * 0.25F;
/*  99 */         float f10 = MathHelper.func_76134_b(f7) * 0.25F;
/* 100 */         float f11 = 8.0F;
/* 101 */         d7 = iicon.func_94214_a(8.0F + (-f10 - f9) * 16.0F);
/* 102 */         d14 = iicon.func_94207_b(8.0F + (-f10 + f9) * 16.0F);
/* 103 */         d8 = iicon.func_94214_a(8.0F + (-f10 + f9) * 16.0F);
/* 104 */         d16 = iicon.func_94207_b(8.0F + (f10 + f9) * 16.0F);
/* 105 */         d10 = iicon.func_94214_a(8.0F + (f10 + f9) * 16.0F);
/* 106 */         d18 = iicon.func_94207_b(8.0F + (f10 - f9) * 16.0F);
/* 107 */         d12 = iicon.func_94214_a(8.0F + (f10 - f9) * 16.0F);
/* 108 */         d20 = iicon.func_94207_b(8.0F + (-f10 - f9) * 16.0F);
/*     */       }
/*     */       
/* 111 */       tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
/*     */       
/* 113 */       tessellator.func_78369_a(f4 * red, f4 * green, f4 * blue, flag ? opacityOuter : opacityInner);
/*     */       
/* 115 */       tessellator.func_78374_a(x + 0, y + d2, z + 0, d7, d14);
/* 116 */       tessellator.func_78374_a(x + 0, y + d3, z + 1, d8, d16);
/* 117 */       tessellator.func_78374_a(x + 1, y + d4, z + 1, d10, d18);
/* 118 */       tessellator.func_78374_a(x + 1, y + d5, z + 0, d12, d20);
/* 119 */       tessellator.func_78374_a(x + 0, y + d2, z + 0, d7, d14);
/* 120 */       tessellator.func_78374_a(x + 1, y + d5, z + 0, d12, d20);
/* 121 */       tessellator.func_78374_a(x + 1, y + d4, z + 1, d10, d18);
/* 122 */       tessellator.func_78374_a(x + 0, y + d3, z + 1, d8, d16);
/*     */     }
/*     */     
/* 125 */     if ((renderer.field_147837_f) || (flag1))
/*     */     {
/* 127 */       tessellator.func_78380_c(block.func_149677_c(world, x, y - 1, z));
/*     */       
/* 129 */       tessellator.func_78369_a(f4 * red, f4 * green, f4 * blue, flag1 ? opacityOuter : opacityInner);
/* 130 */       renderer.func_147768_a(block, x, y + d6, z, renderer.func_147777_a(block, 0));
/* 131 */       flag2 = true;
/*     */     }
/*     */     
/* 134 */     for (int k1 = 0; k1 < 4; k1++)
/*     */     {
/* 136 */       int l1 = x;
/* 137 */       int j1 = z;
/*     */       
/* 139 */       if (k1 == 0)
/*     */       {
/* 141 */         j1 = z - 1;
/*     */       }
/*     */       
/* 144 */       if (k1 == 1)
/*     */       {
/* 146 */         j1++;
/*     */       }
/*     */       
/* 149 */       if (k1 == 2)
/*     */       {
/* 151 */         l1 = x - 1;
/*     */       }
/*     */       
/* 154 */       if (k1 == 3)
/*     */       {
/* 156 */         l1++;
/*     */       }
/*     */       
/* 159 */       IIcon iicon1 = renderer.func_147787_a(block, k1 + 2, i1);
/*     */       
/* 161 */       if ((renderer.field_147837_f) || (aboolean[k1] != 0))
/*     */       {
/*     */         double d19;
/*     */         double d9;
/*     */         double d11;
/*     */         double d13;
/*     */         double d17;
/*     */         double d15;
/*     */         double d19;
/* 170 */         if (k1 == 0)
/*     */         {
/* 172 */           double d9 = d2;
/* 173 */           double d11 = d5;
/* 174 */           double d13 = x;
/* 175 */           double d17 = x + 1;
/* 176 */           double d15 = z + d6;
/* 177 */           d19 = z + d6;
/*     */         } else { double d19;
/* 179 */           if (k1 == 1)
/*     */           {
/* 181 */             double d9 = d4;
/* 182 */             double d11 = d3;
/* 183 */             double d13 = x + 1;
/* 184 */             double d17 = x;
/* 185 */             double d15 = z + 1 - d6;
/* 186 */             d19 = z + 1 - d6;
/*     */           } else { double d19;
/* 188 */             if (k1 == 2)
/*     */             {
/* 190 */               double d9 = d3;
/* 191 */               double d11 = d2;
/* 192 */               double d13 = x + d6;
/* 193 */               double d17 = x + d6;
/* 194 */               double d15 = z + 1;
/* 195 */               d19 = z;
/*     */             }
/*     */             else
/*     */             {
/* 199 */               d9 = d5;
/* 200 */               d11 = d4;
/* 201 */               d13 = x + 1 - d6;
/* 202 */               d17 = x + 1 - d6;
/* 203 */               d15 = z;
/* 204 */               d19 = z + 1;
/*     */             }
/*     */           } }
/* 207 */         flag2 = true;
/* 208 */         float f8 = iicon1.func_94214_a(0.0D);
/* 209 */         float f9 = iicon1.func_94214_a(8.0D);
/* 210 */         float f10 = iicon1.func_94207_b((1.0D - d9) * 16.0D * 0.5D);
/* 211 */         float f11 = iicon1.func_94207_b((1.0D - d11) * 16.0D * 0.5D);
/* 212 */         float f12 = iicon1.func_94207_b(8.0D);
/* 213 */         tessellator.func_78380_c(block.func_149677_c(world, l1, y, j1));
/* 214 */         float f13 = 1.0F;
/* 215 */         f13 *= (k1 < 2 ? f5 : f6);
/*     */         
/*     */ 
/* 218 */         tessellator.func_78369_a(f4 * red, f4 * green, f4 * blue, aboolean[k1] != 0 ? opacityOuter : opacityInner);
/*     */         
/* 220 */         tessellator.func_78374_a(d13, y + d9, d15, f8, f10);
/* 221 */         tessellator.func_78374_a(d17, y + d11, d19, f9, f11);
/* 222 */         tessellator.func_78374_a(d17, y + 0, d19, f9, f12);
/* 223 */         tessellator.func_78374_a(d13, y + 0, d15, f8, f12);
/* 224 */         tessellator.func_78374_a(d13, y + 0, d15, f8, f12);
/* 225 */         tessellator.func_78374_a(d17, y + 0, d19, f9, f12);
/* 226 */         tessellator.func_78374_a(d17, y + d11, d19, f9, f11);
/* 227 */         tessellator.func_78374_a(d13, y + d9, d15, f8, f10);
/*     */       }
/*     */     }
/*     */     
/* 231 */     renderer.field_147855_j = d0;
/* 232 */     renderer.field_147857_k = d1;
/* 233 */     return flag2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 240 */     return false;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 245 */     return Witchery.proxy.getGasRenderId();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/RenderBrewGas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */