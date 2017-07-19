/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockKettle.TileEntityKettle;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelKettle extends ModelBase
/*     */ {
/*     */   ModelRenderer sideFront;
/*     */   ModelRenderer sideBack;
/*     */   ModelRenderer sideLeft;
/*     */   ModelRenderer sideRight;
/*     */   ModelRenderer sideBottom;
/*     */   ModelRenderer crossbar;
/*     */   ModelRenderer[] liquid;
/*     */   ModelRenderer chainLF;
/*     */   ModelRenderer chainLB;
/*     */   ModelRenderer chainRF;
/*     */   ModelRenderer chainRB;
/*     */   ModelRenderer bottle1;
/*     */   ModelRenderer bottle2;
/*     */   private int ticks;
/*     */   
/*     */   public ModelKettle()
/*     */   {
/*  32 */     this.field_78090_t = 64;
/*  33 */     this.field_78089_u = 64;
/*     */     
/*  35 */     func_78085_a("bottle1.bottle1Body", 52, 5);
/*  36 */     func_78085_a("bottle1.bottle1Neck", 60, 3);
/*  37 */     func_78085_a("bottle1.bottle1Top", 56, 0);
/*     */     
/*  39 */     func_78085_a("bottle2.bottle2Body", 52, 5);
/*  40 */     func_78085_a("bottle2.bottle2Neck", 60, 3);
/*  41 */     func_78085_a("bottle2.bottle2Top", 56, 0);
/*     */     
/*  43 */     this.sideFront = new ModelRenderer(this, 0, 0);
/*  44 */     this.sideFront.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
/*  45 */     this.sideFront.func_78793_a(-5.0F, 18.0F, -5.0F);
/*  46 */     this.sideFront.func_78787_b(64, 64);
/*  47 */     this.sideFront.field_78809_i = true;
/*  48 */     setRotation(this.sideFront, 0.0F, 0.0F, 0.0F);
/*  49 */     this.sideBack = new ModelRenderer(this, 0, 0);
/*  50 */     this.sideBack.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
/*  51 */     this.sideBack.func_78793_a(-4.0F, 18.0F, 4.0F);
/*  52 */     this.sideBack.func_78787_b(64, 64);
/*  53 */     this.sideBack.field_78809_i = true;
/*  54 */     setRotation(this.sideBack, 0.0F, 0.0F, 0.0F);
/*  55 */     this.sideLeft = new ModelRenderer(this, 0, 0);
/*  56 */     this.sideLeft.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
/*  57 */     this.sideLeft.func_78793_a(-5.0F, 18.0F, 5.0F);
/*  58 */     this.sideLeft.func_78787_b(64, 64);
/*  59 */     this.sideLeft.field_78809_i = true;
/*  60 */     setRotation(this.sideLeft, 0.0F, 1.570796F, 0.0F);
/*  61 */     this.sideRight = new ModelRenderer(this, 0, 0);
/*  62 */     this.sideRight.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
/*  63 */     this.sideRight.func_78793_a(4.0F, 18.0F, 4.0F);
/*  64 */     this.sideRight.func_78787_b(64, 64);
/*  65 */     this.sideRight.field_78809_i = true;
/*  66 */     setRotation(this.sideRight, 0.0F, 1.570796F, 0.0F);
/*  67 */     this.sideBottom = new ModelRenderer(this, 13, 0);
/*  68 */     this.sideBottom.func_78789_a(0.0F, 0.0F, 0.0F, 8, 1, 8);
/*  69 */     this.sideBottom.func_78793_a(-4.0F, 23.0F, -4.0F);
/*  70 */     this.sideBottom.func_78787_b(64, 64);
/*  71 */     this.sideBottom.field_78809_i = true;
/*  72 */     setRotation(this.sideBottom, 0.0F, 0.0F, 0.0F);
/*  73 */     this.crossbar = new ModelRenderer(this, 0, 10);
/*     */     
/*  75 */     this.crossbar.func_78789_a(-4.0F, 0.0F, 0.0F, 24, 2, 2);
/*  76 */     this.crossbar.func_78793_a(-8.0F, 8.05F, -1.0F);
/*  77 */     this.crossbar.func_78787_b(64, 64);
/*  78 */     this.crossbar.field_78809_i = true;
/*  79 */     setRotation(this.crossbar, 0.0F, 0.0F, 0.0F);
/*  80 */     this.liquid = new ModelRenderer[8];
/*  81 */     for (int i = 0; i < this.liquid.length; i++) {
/*  82 */       this.liquid[i] = new ModelRenderer(this, i < 4 ? i * 16 - 8 : (i - 4) * 16 - 8, i < 4 ? 16 : 32);
/*  83 */       this.liquid[i].func_78789_a(0.0F, 0.0F, 0.0F, 8, 0, 8);
/*  84 */       this.liquid[i].func_78793_a(-4.0F, 20.0F, -4.0F);
/*  85 */       this.liquid[i].func_78787_b(64, 64);
/*  86 */       this.liquid[i].field_78809_i = true;
/*  87 */       setRotation(this.liquid[i], 0.0F, 0.0F, 0.0F);
/*     */     }
/*  89 */     this.chainLF = new ModelRenderer(this, 0, 15);
/*  90 */     this.chainLF.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
/*  91 */     this.chainLF.func_78793_a(0.0F, 9.0F, 0.0F);
/*  92 */     this.chainLF.func_78787_b(64, 64);
/*  93 */     this.chainLF.field_78809_i = true;
/*     */     
/*  95 */     this.chainLB = new ModelRenderer(this, 0, 15);
/*  96 */     this.chainLB.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
/*  97 */     this.chainLB.func_78793_a(0.0F, 9.0F, 0.0F);
/*  98 */     this.chainLB.func_78787_b(64, 64);
/*  99 */     this.chainLB.field_78809_i = true;
/*     */     
/* 101 */     this.chainRF = new ModelRenderer(this, 0, 15);
/* 102 */     this.chainRF.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
/* 103 */     this.chainRF.func_78793_a(0.0F, 9.0F, 0.0F);
/* 104 */     this.chainRF.func_78787_b(64, 64);
/* 105 */     this.chainRF.field_78809_i = true;
/*     */     
/* 107 */     this.chainRB = new ModelRenderer(this, 0, 15);
/* 108 */     this.chainRB.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
/* 109 */     this.chainRB.func_78793_a(0.0F, 9.0F, 0.0F);
/* 110 */     this.chainRB.func_78787_b(64, 64);
/* 111 */     this.chainRB.field_78809_i = true;
/*     */     
/* 113 */     this.chainRB.field_78809_i = false;
/*     */     
/* 115 */     setRotation(this.chainRB, 0.0F, -0.4F, 1.1F);
/* 116 */     setRotation(this.chainLB, 0.0F, 0.4F, 1.1F);
/* 117 */     setRotation(this.chainRF, 0.0F, 0.4F, 2.05F);
/* 118 */     setRotation(this.chainLF, 0.0F, -2.75F, -1.1F);
/*     */     
/* 120 */     this.bottle1 = new ModelRenderer(this, "bottle1");
/* 121 */     this.bottle1.func_78793_a(-4.0F, 13.0F, -6.0F);
/* 122 */     setRotation(this.bottle1, 0.0F, 0.0F, 0.0F);
/* 123 */     this.bottle1.field_78809_i = true;
/* 124 */     this.bottle1.func_78786_a("bottle1Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
/* 125 */     this.bottle1.func_78786_a("bottle1Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
/* 126 */     this.bottle1.func_78786_a("bottle1Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
/*     */     
/* 128 */     this.bottle2 = new ModelRenderer(this, "bottle2");
/* 129 */     this.bottle2.func_78793_a(0.0F, 13.0F, -6.0F);
/* 130 */     setRotation(this.bottle2, 0.0F, 0.0F, 0.0F);
/* 131 */     this.bottle2.field_78809_i = true;
/* 132 */     this.bottle2.func_78786_a("bottle2Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
/* 133 */     this.bottle2.func_78786_a("bottle2Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
/* 134 */     this.bottle2.func_78786_a("bottle2Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockKettle.TileEntityKettle kettleTileEntity)
/*     */   {
/* 139 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 140 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 141 */     this.sideFront.func_78785_a(f5);
/* 142 */     this.sideBack.func_78785_a(f5);
/* 143 */     this.sideLeft.func_78785_a(f5);
/* 144 */     this.sideRight.func_78785_a(f5);
/* 145 */     this.sideBottom.func_78785_a(f5);
/*     */     
/* 147 */     if ((kettleTileEntity != null) && (kettleTileEntity.func_145831_w() != null)) {
/* 148 */       int posX = MathHelper.func_76128_c(kettleTileEntity.field_145851_c);
/* 149 */       int posY = MathHelper.func_76128_c(kettleTileEntity.field_145848_d);
/* 150 */       int posZ = MathHelper.func_76128_c(kettleTileEntity.field_145849_e);
/* 151 */       if (!kettleTileEntity.func_145831_w().func_147439_a(posX, posY + 1, posZ).func_149688_o().func_76220_a()) {
/* 152 */         this.crossbar.func_78785_a(f5);
/*     */       }
/*     */       
/* 155 */       this.chainLF.func_78785_a(f5);
/* 156 */       this.chainLB.func_78785_a(f5);
/* 157 */       this.chainRF.func_78785_a(f5);
/* 158 */       this.chainRB.func_78785_a(f5);
/*     */       
/* 160 */       int bottles = kettleTileEntity.bottleCount();
/* 161 */       if (bottles > 0) {
/* 162 */         this.bottle1.func_78785_a(f5);
/*     */         
/* 164 */         if (bottles > 1) {
/* 165 */           this.bottle2.func_78785_a(f5);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 170 */       setRotation(this.chainRB, 0.0F, -0.4F, 1.1F);
/* 171 */       setRotation(this.chainLB, 0.0F, 0.4F, 1.1F);
/* 172 */       setRotation(this.chainRF, 0.0F, 0.4F, 2.05F);
/* 173 */       setRotation(this.chainLF, 0.0F, -2.75F, -1.1F);
/*     */       
/* 175 */       if (kettleTileEntity.isFilled()) {
/* 176 */         if (this.ticks >= 79) {
/* 177 */           this.ticks = 0;
/*     */         }
/* 179 */         this.ticks += 1;
/*     */         
/* 181 */         int color = 0;
/* 182 */         float factor = 1.0F;
/* 183 */         if (kettleTileEntity.isRuined()) {
/* 184 */           color = -8429824;
/* 185 */           GL11.glColor4f(1.0F, 0.5F, 0.2F, 0.5F);
/* 186 */         } else if (kettleTileEntity.isReady()) {
/* 187 */           color = kettleTileEntity.getLiquidColor();
/* 188 */         } else if (kettleTileEntity.isBrewing()) {
/* 189 */           color = kettleTileEntity.getLiquidColor();
/* 190 */           factor = 0.5F;
/*     */         }
/* 192 */         if (color == 0) {
/* 193 */           color = -13148989;
/* 194 */           factor = 1.0F;
/*     */         }
/* 196 */         float red = (color >>> 16 & 0xFF) / 256.0F * factor;
/* 197 */         float green = (color >>> 8 & 0xFF) / 256.0F * factor;
/* 198 */         float blue = (color & 0xFF) / 256.0F * factor;
/* 199 */         GL11.glColor4f(red, green, blue, 1.0F);
/*     */         
/* 201 */         this.liquid[((int)Math.floor(this.ticks / 20))].func_78785_a(f5);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 207 */     model.field_78795_f = x;
/* 208 */     model.field_78796_g = y;
/* 209 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 214 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelKettle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */