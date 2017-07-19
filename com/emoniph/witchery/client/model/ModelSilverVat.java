/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockSilverVat.TileEntitySilverVat;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ModelSilverVat extends net.minecraft.client.model.ModelBase
/*     */ {
/*     */   public ModelRenderer base;
/*     */   public ModelRenderer sideBack;
/*     */   public ModelRenderer sideFront;
/*     */   public ModelRenderer sideLeft;
/*     */   public ModelRenderer sideRight;
/*     */   public ModelRenderer spoutLowerLeft;
/*     */   public ModelRenderer spoutUpperLeft;
/*     */   public ModelRenderer spoutUpperFront;
/*     */   public ModelRenderer spoutLowerFront;
/*     */   public ModelRenderer spoutUpperRight;
/*     */   public ModelRenderer spoutUpperBack;
/*     */   public ModelRenderer spoutLowerRight;
/*     */   public ModelRenderer silver1;
/*     */   public ModelRenderer spoutLowerBack;
/*     */   public ModelRenderer silver2;
/*     */   public ModelRenderer silver3;
/*     */   public ModelRenderer silver4;
/*     */   public ModelRenderer silver5;
/*     */   public ModelRenderer silver6;
/*     */   public ModelRenderer silver7;
/*     */   public ModelRenderer silver8;
/*     */   private final ModelRenderer[] silver;
/*     */   int capacity;
/*     */   
/*     */   public ModelSilverVat()
/*     */   {
/*  36 */     this.field_78090_t = 64;
/*  37 */     this.field_78089_u = 32;
/*  38 */     this.base = new ModelRenderer(this, 0, 19);
/*  39 */     this.base.func_78793_a(-6.0F, 23.0F, -6.0F);
/*  40 */     this.base.func_78790_a(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
/*  41 */     this.spoutLowerRight = new ModelRenderer(this, 15, 0);
/*  42 */     this.spoutLowerRight.func_78793_a(-5.2F, 16.0F, -0.5F);
/*  43 */     this.spoutLowerRight.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  44 */     this.spoutLowerFront = new ModelRenderer(this, 15, 0);
/*  45 */     this.spoutLowerFront.func_78793_a(-0.5F, 16.0F, -5.2F);
/*  46 */     this.spoutLowerFront.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  47 */     this.silver2 = new ModelRenderer(this, 0, 6);
/*  48 */     this.silver2.func_78793_a(1.6F, 19.0F, -2.1F);
/*  49 */     this.silver2.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
/*  50 */     this.sideRight = new ModelRenderer(this, 38, 10);
/*  51 */     this.sideRight.func_78793_a(-7.0F, 16.0F, -6.0F);
/*  52 */     this.sideRight.func_78790_a(0.0F, 0.0F, 0.0F, 1, 8, 12, 0.0F);
/*  53 */     this.spoutUpperLeft = new ModelRenderer(this, 15, 3);
/*  54 */     this.spoutUpperLeft.func_78793_a(4.0F, 14.0F, -1.5F);
/*  55 */     this.spoutUpperLeft.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
/*  56 */     this.sideFront = new ModelRenderer(this, 34, 0);
/*  57 */     this.sideFront.func_78793_a(-7.0F, 16.0F, -7.0F);
/*  58 */     this.sideFront.func_78790_a(0.0F, 0.0F, 0.0F, 14, 8, 1, 0.0F);
/*  59 */     this.silver7 = new ModelRenderer(this, 0, 22);
/*  60 */     this.silver7.func_78793_a(-0.5F, 19.0F, 2.0F);
/*  61 */     this.silver7.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  62 */     this.spoutUpperRight = new ModelRenderer(this, 15, 3);
/*  63 */     this.spoutUpperRight.func_78793_a(-8.0F, 14.0F, -1.5F);
/*  64 */     this.spoutUpperRight.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
/*  65 */     this.sideBack = new ModelRenderer(this, 34, 0);
/*  66 */     this.sideBack.func_78793_a(-7.0F, 16.0F, 6.0F);
/*  67 */     this.sideBack.func_78790_a(0.0F, 0.0F, 0.0F, 14, 8, 1, 0.0F);
/*  68 */     this.spoutUpperFront = new ModelRenderer(this, 15, 9);
/*  69 */     this.spoutUpperFront.func_78793_a(-1.5F, 14.0F, -8.0F);
/*  70 */     this.spoutUpperFront.func_78790_a(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
/*  71 */     this.spoutLowerLeft = new ModelRenderer(this, 15, 0);
/*  72 */     this.spoutLowerLeft.func_78793_a(4.2F, 16.0F, -0.5F);
/*  73 */     this.spoutLowerLeft.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  74 */     this.silver8 = new ModelRenderer(this, 0, 25);
/*  75 */     this.silver8.func_78793_a(-1.2F, 19.0F, -0.3F);
/*  76 */     this.silver8.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  77 */     this.silver3 = new ModelRenderer(this, 0, 9);
/*  78 */     this.silver3.func_78793_a(-3.8F, 19.1F, 3.1F);
/*  79 */     this.silver3.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
/*  80 */     this.silver6 = new ModelRenderer(this, 0, 19);
/*  81 */     this.silver6.func_78793_a(-4.6F, 19.1F, -1.6F);
/*  82 */     this.silver6.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
/*  83 */     this.spoutLowerBack = new ModelRenderer(this, 15, 0);
/*  84 */     this.spoutLowerBack.func_78793_a(-0.5F, 16.0F, 4.2F);
/*  85 */     this.spoutLowerBack.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  86 */     this.silver1 = new ModelRenderer(this, 0, 3);
/*  87 */     this.silver1.func_78793_a(-2.2F, 19.3F, -3.9F);
/*  88 */     this.silver1.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  89 */     this.silver4 = new ModelRenderer(this, 0, 13);
/*  90 */     this.silver4.func_78793_a(-3.4F, 18.8F, 0.9F);
/*  91 */     this.silver4.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*  92 */     this.spoutUpperBack = new ModelRenderer(this, 15, 9);
/*  93 */     this.spoutUpperBack.func_78793_a(-1.5F, 14.0F, 4.0F);
/*  94 */     this.spoutUpperBack.func_78790_a(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
/*  95 */     this.sideLeft = new ModelRenderer(this, 38, 10);
/*  96 */     this.sideLeft.func_78793_a(6.0F, 16.0F, -6.0F);
/*  97 */     this.sideLeft.func_78790_a(0.0F, 0.0F, 0.0F, 1, 8, 12, 0.0F);
/*  98 */     this.silver5 = new ModelRenderer(this, 0, 16);
/*  99 */     this.silver5.func_78793_a(1.6F, 19.0F, -0.1F);
/* 100 */     this.silver5.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*     */     
/* 102 */     this.silver = new ModelRenderer[] { this.silver1, this.silver2, this.silver3, this.silver4, this.silver5, this.silver6, this.silver7, this.silver8 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void render(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockSilverVat.TileEntitySilverVat te)
/*     */   {
/* 109 */     this.base.func_78785_a(f5);
/* 110 */     this.sideRight.func_78785_a(f5);
/* 111 */     this.sideFront.func_78785_a(f5);
/* 112 */     this.sideBack.func_78785_a(f5);
/* 113 */     this.sideLeft.func_78785_a(f5);
/*     */     
/* 115 */     boolean isWorld = (te != null) && (te.func_145831_w() != null);
/*     */     
/* 117 */     if ((!isWorld) || (te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e).func_149716_u())) {
/* 118 */       this.spoutUpperLeft.func_78785_a(f5);
/* 119 */       this.spoutLowerLeft.func_78785_a(f5);
/*     */     }
/*     */     
/* 122 */     if ((!isWorld) || (te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e).func_149716_u())) {
/* 123 */       this.spoutUpperRight.func_78785_a(f5);
/* 124 */       this.spoutLowerRight.func_78785_a(f5);
/*     */     }
/* 126 */     if ((!isWorld) || (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1).func_149716_u())) {
/* 127 */       this.spoutUpperFront.func_78785_a(f5);
/* 128 */       this.spoutLowerFront.func_78785_a(f5);
/*     */     }
/* 130 */     if ((!isWorld) || (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1).func_149716_u())) {
/* 131 */       this.spoutUpperBack.func_78785_a(f5);
/* 132 */       this.spoutLowerBack.func_78785_a(f5);
/*     */     }
/*     */     
/* 135 */     int capacity = isWorld ? 0 : (te.func_70301_a(0) != null) && (te.func_70301_a(0).field_77994_a > 0) ? Math.max(te.func_70301_a(0).field_77994_a / 8, 1) : 0;
/*     */     
/* 137 */     for (int i = 0; i < capacity; i++) {
/* 138 */       this.silver[i].func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 143 */     modelRenderer.field_78795_f = x;
/* 144 */     modelRenderer.field_78796_g = y;
/* 145 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCapactiy(int i)
/*     */   {
/* 151 */     this.capacity = i;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelSilverVat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */