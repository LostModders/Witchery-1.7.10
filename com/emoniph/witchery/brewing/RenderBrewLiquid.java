/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ public class RenderBrewLiquid implements ISimpleBlockRenderingHandler
/*     */ {
/*  17 */   public static final RenderBrewLiquid instance = new RenderBrewLiquid();
/*     */   static final float LIGHT_Y_NEG = 0.5F;
/*     */   static final float LIGHT_Y_POS = 1.0F;
/*     */   static final float LIGHT_XZ_NEG = 0.8F;
/*     */   static final float LIGHT_XZ_POS = 0.6F;
/*     */   static final double RENDER_OFFSET = 0.0010000000474974513D;
/*     */   
/*     */   public float getFluidHeightAverage(float[] flow)
/*     */   {
/*  26 */     float total = 0.0F;
/*  27 */     int count = 0;
/*     */     
/*  29 */     float end = 0.0F;
/*     */     
/*  31 */     for (int i = 0; i < flow.length; i++) {
/*  32 */       if ((flow[i] >= 0.875F) && (end != 1.0F)) {
/*  33 */         end = flow[i];
/*     */       }
/*     */       
/*  36 */       if (flow[i] >= 0.0F) {
/*  37 */         total += flow[i];
/*  38 */         count++;
/*     */       }
/*     */     }
/*     */     
/*  42 */     if (end == 0.0F) {
/*  43 */       end = total / count;
/*     */     }
/*  45 */     return end;
/*     */   }
/*     */   
/*     */   public float getFluidHeightForRender(IBlockAccess world, int x, int y, int z, BlockBrewLiquidEffect block) {
/*  49 */     if (world.func_147439_a(x, y, z) == block) {
/*  50 */       if (world.func_147439_a(x, y - block.densityDir, z).func_149688_o().func_76224_d()) {
/*  51 */         return 1.0F;
/*     */       }
/*     */       
/*  54 */       if (world.func_72805_g(x, y, z) == block.getMaxRenderHeightMeta()) {
/*  55 */         return 0.875F;
/*     */       }
/*     */     }
/*  58 */     return (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (world.func_147439_a(x, y - block.densityDir, z) == block) ? 1.0F : block.getQuantaPercentage(world, x, y, z) * 0.875F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*     */ 
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/*  68 */     if (!(block instanceof BlockBrewLiquidEffect)) {
/*  69 */       return false;
/*     */     }
/*     */     
/*  72 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  73 */     int color = block.func_149720_d(world, x, y, z);
/*  74 */     float red = (color >> 16 & 0xFF) / 255.0F;
/*  75 */     float green = (color >> 8 & 0xFF) / 255.0F;
/*  76 */     float blue = (color & 0xFF) / 255.0F;
/*     */     
/*  78 */     BlockBrewLiquidEffect theFluid = (BlockBrewLiquidEffect)block;
/*  79 */     int bMeta = world.func_72805_g(x, y, z);
/*     */     
/*  81 */     boolean renderTop = world.func_147439_a(x, y - theFluid.densityDir, z) != theFluid;
/*     */     
/*  83 */     boolean renderBottom = (block.func_149646_a(world, x, y + theFluid.densityDir, z, 0)) && (world.func_147439_a(x, y + theFluid.densityDir, z) != theFluid);
/*     */     
/*     */ 
/*  86 */     boolean[] renderSides = { block.func_149646_a(world, x, y, z - 1, 2), block.func_149646_a(world, x, y, z + 1, 3), block.func_149646_a(world, x - 1, y, z, 4), block.func_149646_a(world, x + 1, y, z, 5) };
/*     */     
/*     */ 
/*     */ 
/*  90 */     if ((!renderTop) && (!renderBottom) && (renderSides[0] == 0) && (renderSides[1] == 0) && (renderSides[2] == 0) && (renderSides[3] == 0)) {
/*  91 */       return false;
/*     */     }
/*  93 */     boolean rendered = false;
/*     */     
/*  95 */     float flow11 = getFluidHeightForRender(world, x, y, z, theFluid);
/*     */     double heightNE;
/*  97 */     double heightNW; double heightSW; double heightSE; double heightNE; if (flow11 != 1.0F) {
/*  98 */       float flow00 = getFluidHeightForRender(world, x - 1, y, z - 1, theFluid);
/*  99 */       float flow01 = getFluidHeightForRender(world, x - 1, y, z, theFluid);
/* 100 */       float flow02 = getFluidHeightForRender(world, x - 1, y, z + 1, theFluid);
/* 101 */       float flow10 = getFluidHeightForRender(world, x, y, z - 1, theFluid);
/* 102 */       float flow12 = getFluidHeightForRender(world, x, y, z + 1, theFluid);
/* 103 */       float flow20 = getFluidHeightForRender(world, x + 1, y, z - 1, theFluid);
/* 104 */       float flow21 = getFluidHeightForRender(world, x + 1, y, z, theFluid);
/* 105 */       float flow22 = getFluidHeightForRender(world, x + 1, y, z + 1, theFluid);
/*     */       
/* 107 */       double heightNW = getFluidHeightAverage(new float[] { flow00, flow01, flow10, flow11 });
/* 108 */       double heightSW = getFluidHeightAverage(new float[] { flow01, flow02, flow12, flow11 });
/* 109 */       double heightSE = getFluidHeightAverage(new float[] { flow12, flow21, flow22, flow11 });
/* 110 */       heightNE = getFluidHeightAverage(new float[] { flow10, flow20, flow21, flow11 });
/*     */     } else {
/* 112 */       heightNW = flow11;
/* 113 */       heightSW = flow11;
/* 114 */       heightSE = flow11;
/* 115 */       heightNE = flow11;
/*     */     }
/*     */     
/* 118 */     boolean rises = theFluid.densityDir == 1;
/* 119 */     if ((renderer.field_147837_f) || (renderTop)) {
/* 120 */       rendered = true;
/* 121 */       IIcon iconStill = getIcon(block.func_149691_a(1, bMeta));
/* 122 */       float flowDir = (float)BlockBrewLiquidEffect.getFlowDirection(world, x, y, z);
/*     */       
/* 124 */       if (flowDir > -999.0F) {
/* 125 */         iconStill = getIcon(block.func_149691_a(2, bMeta));
/*     */       }
/*     */       
/* 128 */       heightNW -= 0.0010000000474974513D;
/* 129 */       heightSW -= 0.0010000000474974513D;
/* 130 */       heightSE -= 0.0010000000474974513D;
/* 131 */       heightNE -= 0.0010000000474974513D;
/*     */       double v3;
/*     */       double u2;
/*     */       double v2;
/* 135 */       double u1; double v1; double u4; double v4; double u3; double v3; if (flowDir < -999.0F) {
/* 136 */         double u2 = iconStill.func_94214_a(0.0D);
/* 137 */         double v2 = iconStill.func_94207_b(0.0D);
/* 138 */         double u1 = u2;
/* 139 */         double v1 = iconStill.func_94207_b(16.0D);
/* 140 */         double u4 = iconStill.func_94214_a(16.0D);
/* 141 */         double v4 = v1;
/* 142 */         double u3 = u4;
/* 143 */         v3 = v2;
/*     */       } else {
/* 145 */         float xFlow = MathHelper.func_76126_a(flowDir) * 0.25F;
/* 146 */         float zFlow = MathHelper.func_76134_b(flowDir) * 0.25F;
/* 147 */         u2 = iconStill.func_94214_a(8.0F + (-zFlow - xFlow) * 16.0F);
/* 148 */         v2 = iconStill.func_94207_b(8.0F + (-zFlow + xFlow) * 16.0F);
/* 149 */         u1 = iconStill.func_94214_a(8.0F + (-zFlow + xFlow) * 16.0F);
/* 150 */         v1 = iconStill.func_94207_b(8.0F + (zFlow + xFlow) * 16.0F);
/* 151 */         u4 = iconStill.func_94214_a(8.0F + (zFlow + xFlow) * 16.0F);
/* 152 */         v4 = iconStill.func_94207_b(8.0F + (zFlow - xFlow) * 16.0F);
/* 153 */         u3 = iconStill.func_94214_a(8.0F + (zFlow - xFlow) * 16.0F);
/* 154 */         v3 = iconStill.func_94207_b(8.0F + (-zFlow - xFlow) * 16.0F);
/*     */       }
/*     */       
/* 157 */       tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
/* 158 */       tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
/*     */       
/* 160 */       if (!rises) {
/* 161 */         tessellator.func_78374_a(x + 0, y + heightNW, z + 0, u2, v2);
/* 162 */         tessellator.func_78374_a(x + 0, y + heightSW, z + 1, u1, v1);
/* 163 */         tessellator.func_78374_a(x + 1, y + heightSE, z + 1, u4, v4);
/* 164 */         tessellator.func_78374_a(x + 1, y + heightNE, z + 0, u3, v3);
/*     */       } else {
/* 166 */         tessellator.func_78374_a(x + 1, y + 1 - heightNE, z + 0, u3, v3);
/* 167 */         tessellator.func_78374_a(x + 1, y + 1 - heightSE, z + 1, u4, v4);
/* 168 */         tessellator.func_78374_a(x + 0, y + 1 - heightSW, z + 1, u1, v1);
/* 169 */         tessellator.func_78374_a(x + 0, y + 1 - heightNW, z + 0, u2, v2);
/*     */       }
/*     */     }
/*     */     
/* 173 */     if ((renderer.field_147837_f) || (renderBottom)) {
/* 174 */       rendered = true;
/* 175 */       tessellator.func_78380_c(block.func_149677_c(world, x, y - 1, z));
/* 176 */       if (!rises) {
/* 177 */         tessellator.func_78386_a(0.5F * red, 0.5F * green, 0.5F * blue);
/* 178 */         renderer.func_147768_a(block, x, y + 0.0010000000474974513D, z, getIcon(block.func_149691_a(0, bMeta)));
/*     */       } else {
/* 180 */         tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
/* 181 */         renderer.func_147806_b(block, x, y + 0.0010000000474974513D, z, getIcon(block.func_149691_a(1, bMeta)));
/*     */       }
/*     */     }
/*     */     
/* 185 */     for (int side = 0; side < 4; side++) {
/* 186 */       int x2 = x;
/* 187 */       int z2 = z;
/*     */       
/* 189 */       switch (side) {
/*     */       case 0: 
/* 191 */         z2--;
/* 192 */         break;
/*     */       case 1: 
/* 194 */         z2++;
/* 195 */         break;
/*     */       case 2: 
/* 197 */         x2--;
/* 198 */         break;
/*     */       case 3: 
/* 200 */         x2++;
/*     */       }
/*     */       
/*     */       
/* 204 */       IIcon iconFlow = getIcon(block.func_149691_a(side + 2, bMeta));
/* 205 */       if ((renderer.field_147837_f) || (renderSides[side] != 0)) {
/* 206 */         rendered = true;
/*     */         
/*     */         double tz2;
/*     */         double ty1;
/*     */         double ty2;
/*     */         double tx1;
/*     */         double tx2;
/*     */         double tz1;
/*     */         double tz2;
/* 215 */         if (side == 0) {
/* 216 */           double ty1 = heightNW;
/* 217 */           double ty2 = heightNE;
/* 218 */           double tx1 = x;
/* 219 */           double tx2 = x + 1;
/* 220 */           double tz1 = z + 0.0010000000474974513D;
/* 221 */           tz2 = z + 0.0010000000474974513D; } else { double tz2;
/* 222 */           if (side == 1) {
/* 223 */             double ty1 = heightSE;
/* 224 */             double ty2 = heightSW;
/* 225 */             double tx1 = x + 1;
/* 226 */             double tx2 = x;
/* 227 */             double tz1 = z + 1 - 0.0010000000474974513D;
/* 228 */             tz2 = z + 1 - 0.0010000000474974513D; } else { double tz2;
/* 229 */             if (side == 2) {
/* 230 */               double ty1 = heightSW;
/* 231 */               double ty2 = heightNW;
/* 232 */               double tx1 = x + 0.0010000000474974513D;
/* 233 */               double tx2 = x + 0.0010000000474974513D;
/* 234 */               double tz1 = z + 1;
/* 235 */               tz2 = z;
/*     */             } else {
/* 237 */               ty1 = heightNE;
/* 238 */               ty2 = heightSE;
/* 239 */               tx1 = x + 1 - 0.0010000000474974513D;
/* 240 */               tx2 = x + 1 - 0.0010000000474974513D;
/* 241 */               tz1 = z;
/* 242 */               tz2 = z + 1;
/*     */             }
/*     */           } }
/* 245 */         float u1Flow = iconFlow.func_94214_a(0.0D);
/* 246 */         float u2Flow = iconFlow.func_94214_a(8.0D);
/* 247 */         float v1Flow = iconFlow.func_94207_b((1.0D - ty1) * 16.0D * 0.5D);
/* 248 */         float v2Flow = iconFlow.func_94207_b((1.0D - ty2) * 16.0D * 0.5D);
/* 249 */         float v3Flow = iconFlow.func_94207_b(8.0D);
/* 250 */         tessellator.func_78380_c(block.func_149677_c(world, x2, y, z2));
/* 251 */         float sideLighting = 1.0F;
/*     */         
/* 253 */         if (side < 2) {
/* 254 */           sideLighting = 0.8F;
/*     */         } else {
/* 256 */           sideLighting = 0.6F;
/*     */         }
/*     */         
/* 259 */         tessellator.func_78386_a(1.0F * sideLighting * red, 1.0F * sideLighting * green, 1.0F * sideLighting * blue);
/*     */         
/*     */ 
/* 262 */         if (!rises) {
/* 263 */           tessellator.func_78374_a(tx1, y + ty1, tz1, u1Flow, v1Flow);
/* 264 */           tessellator.func_78374_a(tx2, y + ty2, tz2, u2Flow, v2Flow);
/* 265 */           tessellator.func_78374_a(tx2, y + 0, tz2, u2Flow, v3Flow);
/* 266 */           tessellator.func_78374_a(tx1, y + 0, tz1, u1Flow, v3Flow);
/*     */         } else {
/* 268 */           tessellator.func_78374_a(tx1, y + 1 - 0, tz1, u1Flow, v3Flow);
/* 269 */           tessellator.func_78374_a(tx2, y + 1 - 0, tz2, u2Flow, v3Flow);
/* 270 */           tessellator.func_78374_a(tx2, y + 1 - ty2, tz2, u2Flow, v2Flow);
/* 271 */           tessellator.func_78374_a(tx1, y + 1 - ty1, tz1, u1Flow, v1Flow);
/*     */         }
/*     */       }
/*     */     }
/* 275 */     renderer.field_147855_j = 0.0D;
/* 276 */     renderer.field_147857_k = 1.0D;
/* 277 */     return rendered;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 288 */     return Witchery.proxy.getBrewLiquidRenderId();
/*     */   }
/*     */   
/*     */   private IIcon getIcon(IIcon icon) {
/* 292 */     if (icon != null)
/* 293 */       return icon;
/* 294 */     return ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110575_b)).func_110572_b("missingno");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/RenderBrewLiquid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */