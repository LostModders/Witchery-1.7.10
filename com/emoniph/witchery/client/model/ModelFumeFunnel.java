/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockWitchesOven;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelFumeFunnel
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer chimney;
/*     */   ModelRenderer chimneyTop;
/*     */   ModelRenderer base;
/*     */   ModelRenderer body;
/*     */   ModelRenderer tubeLeft;
/*     */   ModelRenderer tubeRight;
/*     */   ModelRenderer pipeTop2;
/*     */   ModelRenderer pipeTop3;
/*     */   ModelRenderer pipeTop4;
/*     */   ModelRenderer pipeTop5;
/*     */   ModelRenderer pipeBottom1;
/*     */   ModelRenderer pipeBottom2;
/*     */   ModelRenderer pipeBottom3;
/*     */   ModelRenderer pipeBottom4;
/*     */   ModelRenderer pipeTop1;
/*     */   ModelRenderer top1;
/*     */   ModelRenderer pipeBottom5;
/*     */   ModelRenderer top2;
/*     */   ModelRenderer filterLeft;
/*     */   ModelRenderer filterRight;
/*     */   ModelRenderer filterMid;
/*     */   ModelRenderer filterCase;
/*     */   final boolean filtered;
/*     */   
/*     */   public ModelFumeFunnel(boolean filtered)
/*     */   {
/*  45 */     this.filtered = filtered;
/*  46 */     this.field_78090_t = 64;
/*  47 */     this.field_78089_u = 64;
/*     */     
/*  49 */     this.base = new ModelRenderer(this, 0, 51);
/*  50 */     this.base.func_78789_a(0.0F, 0.0F, 0.0F, 12, 1, 12);
/*  51 */     this.base.func_78793_a(-6.0F, 23.0F, -6.0F);
/*  52 */     this.base.func_78787_b(64, 64);
/*  53 */     this.base.field_78809_i = true;
/*  54 */     setRotation(this.base, 0.0F, 0.0F, 0.0F);
/*  55 */     this.body = new ModelRenderer(this, 4, 27);
/*  56 */     this.body.func_78789_a(0.0F, 0.0F, 0.0F, 10, 11, 10);
/*  57 */     this.body.func_78793_a(-5.0F, 12.0F, -5.0F);
/*  58 */     this.body.func_78787_b(64, 64);
/*  59 */     this.body.field_78809_i = true;
/*  60 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  61 */     this.tubeLeft = new ModelRenderer(this, 1, 18);
/*  62 */     this.tubeLeft.func_78789_a(0.0F, 0.0F, 0.0F, 5, 2, 2);
/*  63 */     this.tubeLeft.func_78793_a(-10.0F, 17.0F, -1.0F);
/*  64 */     this.tubeLeft.func_78787_b(64, 64);
/*  65 */     this.tubeLeft.field_78809_i = true;
/*  66 */     setRotation(this.tubeLeft, 0.0F, 0.0F, 0.0F);
/*  67 */     this.tubeRight = new ModelRenderer(this, 1, 18);
/*  68 */     this.tubeRight.func_78789_a(0.0F, 1.0F, 0.0F, 5, 2, 2);
/*  69 */     this.tubeRight.func_78793_a(5.0F, 18.0F, 1.0F);
/*  70 */     this.tubeRight.func_78787_b(64, 64);
/*  71 */     this.tubeRight.field_78809_i = true;
/*  72 */     setRotation(this.tubeRight, 0.0F, 0.0F, 0.0F);
/*  73 */     this.pipeTop2 = new ModelRenderer(this, 0, 0);
/*  74 */     this.pipeTop2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 6);
/*  75 */     this.pipeTop2.func_78793_a(-4.0F, 8.0F, -3.0F);
/*  76 */     this.pipeTop2.func_78787_b(64, 64);
/*  77 */     this.pipeTop2.field_78809_i = true;
/*  78 */     setRotation(this.pipeTop2, 0.0F, 0.0F, 0.0F);
/*  79 */     this.pipeTop3 = new ModelRenderer(this, 0, 0);
/*  80 */     this.pipeTop3.func_78789_a(0.0F, 0.0F, 0.0F, 11, 1, 1);
/*  81 */     this.pipeTop3.func_78793_a(-3.0F, 8.0F, -3.0F);
/*  82 */     this.pipeTop3.func_78787_b(64, 64);
/*  83 */     this.pipeTop3.field_78809_i = true;
/*  84 */     setRotation(this.pipeTop3, 0.0F, 0.0F, 0.0F);
/*  85 */     this.pipeTop4 = new ModelRenderer(this, 0, 0);
/*  86 */     this.pipeTop4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 11, 1);
/*  87 */     this.pipeTop4.func_78793_a(7.0F, 9.0F, -3.0F);
/*  88 */     this.pipeTop4.func_78787_b(64, 64);
/*  89 */     this.pipeTop4.field_78809_i = true;
/*  90 */     setRotation(this.pipeTop4, 0.0F, 0.0F, 0.0F);
/*  91 */     this.pipeTop5 = new ModelRenderer(this, 0, 0);
/*  92 */     this.pipeTop5.func_78789_a(0.0F, 0.0F, 0.0F, 2, 3, 3);
/*  93 */     this.pipeTop5.func_78793_a(5.0F, 18.0F, -4.0F);
/*  94 */     this.pipeTop5.func_78787_b(64, 64);
/*  95 */     this.pipeTop5.field_78809_i = true;
/*  96 */     setRotation(this.pipeTop5, 0.0F, 0.0F, 0.0F);
/*  97 */     this.pipeBottom1 = new ModelRenderer(this, 0, 0);
/*  98 */     this.pipeBottom1.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 1);
/*  99 */     this.pipeBottom1.func_78793_a(-7.0F, 13.0F, -3.0F);
/* 100 */     this.pipeBottom1.func_78787_b(64, 64);
/* 101 */     this.pipeBottom1.field_78809_i = true;
/* 102 */     setRotation(this.pipeBottom1, 0.0F, 0.0F, 0.0F);
/* 103 */     this.pipeBottom2 = new ModelRenderer(this, 0, 0);
/* 104 */     this.pipeBottom2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 4);
/* 105 */     this.pipeBottom2.func_78793_a(-7.0F, 20.0F, -7.0F);
/* 106 */     this.pipeBottom2.func_78787_b(64, 64);
/* 107 */     this.pipeBottom2.field_78809_i = true;
/* 108 */     setRotation(this.pipeBottom2, 0.0F, 0.0F, 0.0F);
/* 109 */     this.pipeBottom3 = new ModelRenderer(this, 0, 0);
/* 110 */     this.pipeBottom3.func_78789_a(0.0F, 0.0F, 0.0F, 5, 1, 1);
/* 111 */     this.pipeBottom3.func_78793_a(-6.0F, 20.0F, -7.0F);
/* 112 */     this.pipeBottom3.func_78787_b(64, 64);
/* 113 */     this.pipeBottom3.field_78809_i = true;
/* 114 */     setRotation(this.pipeBottom3, 0.0F, 0.0F, 0.0F);
/* 115 */     this.pipeBottom4 = new ModelRenderer(this, 0, 0);
/* 116 */     this.pipeBottom4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 3, 1);
/* 117 */     this.pipeBottom4.func_78793_a(-2.0F, 21.0F, -7.0F);
/* 118 */     this.pipeBottom4.func_78787_b(64, 64);
/* 119 */     this.pipeBottom4.field_78809_i = true;
/* 120 */     setRotation(this.pipeBottom4, 0.0F, 0.0F, 0.0F);
/* 121 */     this.pipeTop1 = new ModelRenderer(this, 0, 0);
/* 122 */     this.pipeTop1.func_78789_a(0.0F, 0.0F, 0.0F, 1, 3, 1);
/* 123 */     this.pipeTop1.func_78793_a(-4.0F, 8.0F, 3.0F);
/* 124 */     this.pipeTop1.func_78787_b(64, 64);
/* 125 */     this.pipeTop1.field_78809_i = true;
/* 126 */     setRotation(this.pipeTop1, 0.0F, 0.0F, 0.0F);
/* 127 */     this.top1 = new ModelRenderer(this, 0, 51);
/* 128 */     this.top1.func_78789_a(0.0F, 0.0F, 0.0F, 12, 1, 12);
/* 129 */     this.top1.func_78793_a(-6.0F, 11.0F, -6.0F);
/* 130 */     this.top1.func_78787_b(64, 64);
/* 131 */     this.top1.field_78809_i = true;
/* 132 */     setRotation(this.top1, 0.0F, 0.0F, 0.0F);
/* 133 */     this.pipeBottom5 = new ModelRenderer(this, 0, 0);
/* 134 */     this.pipeBottom5.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 1);
/* 135 */     this.pipeBottom5.func_78793_a(-7.0F, 14.0F, -3.0F);
/* 136 */     this.pipeBottom5.func_78787_b(64, 64);
/* 137 */     this.pipeBottom5.field_78809_i = true;
/* 138 */     setRotation(this.pipeBottom5, 0.0F, 0.0F, 0.0F);
/* 139 */     this.top2 = new ModelRenderer(this, 37, 55);
/* 140 */     this.top2.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 6);
/* 141 */     this.top2.func_78793_a(-3.0F, 10.0F, -3.0F);
/* 142 */     this.top2.func_78787_b(64, 64);
/* 143 */     this.top2.field_78809_i = true;
/* 144 */     setRotation(this.top2, 0.0F, 0.0F, 0.0F);
/* 145 */     this.filterLeft = new ModelRenderer(this, 0, 0);
/* 146 */     this.filterLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
/* 147 */     this.filterLeft.func_78793_a(-4.0F, 14.0F, -7.0F);
/* 148 */     this.filterLeft.func_78787_b(64, 64);
/* 149 */     this.filterLeft.field_78809_i = true;
/* 150 */     setRotation(this.filterLeft, 0.0F, 0.0F, 0.0F);
/* 151 */     this.filterRight = new ModelRenderer(this, 0, 0);
/* 152 */     this.filterRight.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
/* 153 */     this.filterRight.func_78793_a(3.0F, 14.0F, -7.0F);
/* 154 */     this.filterRight.func_78787_b(64, 64);
/* 155 */     this.filterRight.field_78809_i = true;
/* 156 */     setRotation(this.filterRight, 0.0F, 0.0F, 0.0F);
/* 157 */     this.filterMid = new ModelRenderer(this, 24, 0);
/* 158 */     this.filterMid.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 1);
/* 159 */     this.filterMid.func_78793_a(-3.0F, 14.0F, -7.0F);
/* 160 */     this.filterMid.func_78787_b(64, 64);
/* 161 */     this.filterMid.field_78809_i = true;
/* 162 */     setRotation(this.filterMid, 0.0F, 0.0F, 0.0F);
/* 163 */     this.filterCase = new ModelRenderer(this, 25, 3);
/* 164 */     this.filterCase.func_78789_a(0.0F, 0.0F, 0.0F, 4, 3, 2);
/* 165 */     this.filterCase.func_78793_a(-2.0F, 13.0F, -8.0F);
/* 166 */     this.filterCase.func_78787_b(64, 64);
/* 167 */     this.filterCase.field_78809_i = true;
/* 168 */     setRotation(this.filterCase, 0.0F, 0.0F, 0.0F);
/*     */     
/* 170 */     this.chimney = new ModelRenderer(this, 27, 13);
/* 171 */     this.chimney.func_78789_a(0.0F, 0.0F, 0.0F, 4, 10, 4);
/* 172 */     this.chimney.func_78793_a(-2.0F, 14.0F, 3.0F);
/* 173 */     this.chimney.func_78787_b(64, 128);
/* 174 */     this.chimney.field_78809_i = true;
/* 175 */     setRotation(this.chimney, 0.0F, 0.0F, 0.0F);
/* 176 */     this.chimneyTop = new ModelRenderer(this, 40, 7);
/* 177 */     this.chimneyTop.func_78789_a(0.0F, 0.0F, 0.0F, 6, 3, 6);
/* 178 */     this.chimneyTop.func_78793_a(-3.0F, 11.0F, 2.0F);
/* 179 */     this.chimneyTop.func_78787_b(64, 128);
/* 180 */     this.chimneyTop.field_78809_i = true;
/* 181 */     setRotation(this.chimneyTop, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile) {
/* 185 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 186 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 188 */     boolean validTileEntity = (tile != null) && (tile.func_145831_w() != null);
/*     */     
/* 190 */     boolean renderWideBody = true;
/*     */     
/* 192 */     if (validTileEntity) {
/* 193 */       int meta = tile.func_145832_p();
/* 194 */       switch (meta) {
/*     */       case 2: 
/* 196 */         renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c + 1, tile.field_145848_d, tile.field_145849_e, f5);
/* 197 */         renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c - 1, tile.field_145848_d, tile.field_145849_e, f5);
/* 198 */         break;
/*     */       case 5: 
/* 200 */         renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e + 1, f5);
/* 201 */         renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e - 1, f5);
/* 202 */         break;
/*     */       case 3: 
/* 204 */         renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c - 1, tile.field_145848_d, tile.field_145849_e, f5);
/* 205 */         renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c + 1, tile.field_145848_d, tile.field_145849_e, f5);
/* 206 */         break;
/*     */       case 4: 
/* 208 */         renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e - 1, f5);
/* 209 */         renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e + 1, f5);
/*     */       }
/*     */       
/*     */       
/* 213 */       Block block = tile.func_145831_w().func_147439_a(tile.field_145851_c, tile.field_145848_d - 1, tile.field_145849_e);
/* 214 */       if (BlockWitchesOven.isOven(block)) {
/* 215 */         this.chimney.func_78785_a(f5);
/* 216 */         this.chimneyTop.func_78785_a(f5);
/* 217 */         renderWideBody = false;
/*     */       }
/*     */     }
/*     */     
/* 221 */     if (renderWideBody) {
/* 222 */       this.base.func_78785_a(f5);
/* 223 */       this.body.func_78785_a(f5);
/* 224 */       this.top1.func_78785_a(f5);
/* 225 */       this.top2.func_78785_a(f5);
/*     */       
/* 227 */       if ((this.filtered) || ((validTileEntity) && (tile.func_145838_q() == Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED))) {
/* 228 */         this.filterLeft.func_78785_a(f5);
/* 229 */         this.filterRight.func_78785_a(f5);
/* 230 */         this.filterMid.func_78785_a(f5);
/* 231 */         this.filterCase.func_78785_a(f5);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderLeftGubbinsIfConnected(World world, int xCoord, int yCoord, int zCoord, float f5) {
/* 237 */     Block block = world.func_147439_a(xCoord, yCoord, zCoord);
/* 238 */     if (BlockWitchesOven.isOven(block)) {
/* 239 */       this.tubeLeft.func_78785_a(f5);
/* 240 */       this.pipeTop1.func_78785_a(f5);
/* 241 */       this.pipeTop2.func_78785_a(f5);
/* 242 */       this.pipeTop3.func_78785_a(f5);
/* 243 */       this.pipeTop4.func_78785_a(f5);
/* 244 */       this.pipeTop5.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderRightGubbinsIfConnected(World world, int xCoord, int yCoord, int zCoord, float f5) {
/* 249 */     Block block = world.func_147439_a(xCoord, yCoord, zCoord);
/* 250 */     if (BlockWitchesOven.isOven(block)) {
/* 251 */       this.tubeRight.func_78785_a(f5);
/* 252 */       this.pipeBottom1.func_78785_a(f5);
/* 253 */       this.pipeBottom2.func_78785_a(f5);
/* 254 */       this.pipeBottom3.func_78785_a(f5);
/* 255 */       this.pipeBottom4.func_78785_a(f5);
/* 256 */       this.pipeBottom5.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 261 */     model.field_78795_f = x;
/* 262 */     model.field_78796_g = y;
/* 263 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 267 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelFumeFunnel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */