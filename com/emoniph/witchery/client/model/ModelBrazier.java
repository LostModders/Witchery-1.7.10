/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelBrazier extends ModelBase
/*     */ {
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer leg2;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer foot3;
/*     */   ModelRenderer foot2;
/*     */   ModelRenderer foot1;
/*     */   ModelRenderer foot4;
/*     */   ModelRenderer ash;
/*     */   ModelRenderer panSide1;
/*     */   ModelRenderer panSide2;
/*     */   ModelRenderer panSide3;
/*     */   ModelRenderer panSide4;
/*     */   ModelRenderer footBase;
/*     */   ModelRenderer panBase;
/*     */   
/*     */   public ModelBrazier()
/*     */   {
/*  31 */     this.field_78090_t = 64;
/*  32 */     this.field_78089_u = 64;
/*     */     
/*  34 */     this.leg1 = new ModelRenderer(this, 0, 0);
/*  35 */     this.leg1.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
/*  36 */     this.leg1.func_78793_a(0.7F, 10.0F, -0.74F);
/*  37 */     this.leg1.func_78787_b(64, 64);
/*  38 */     this.leg1.field_78809_i = true;
/*  39 */     setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
/*  40 */     this.leg2 = new ModelRenderer(this, 0, 0);
/*  41 */     this.leg2.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
/*  42 */     this.leg2.func_78793_a(-0.7F, 10.0F, -0.7F);
/*  43 */     this.leg2.func_78787_b(64, 64);
/*  44 */     this.leg2.field_78809_i = true;
/*  45 */     setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
/*  46 */     this.leg3 = new ModelRenderer(this, 0, 0);
/*  47 */     this.leg3.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
/*  48 */     this.leg3.func_78793_a(-0.7F, 10.0F, 0.7F);
/*  49 */     this.leg3.func_78787_b(64, 64);
/*  50 */     this.leg3.field_78809_i = true;
/*  51 */     setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
/*  52 */     this.leg4 = new ModelRenderer(this, 0, 0);
/*  53 */     this.leg4.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
/*  54 */     this.leg4.func_78793_a(0.7F, 10.0F, 0.7F);
/*  55 */     this.leg4.func_78787_b(64, 64);
/*  56 */     this.leg4.field_78809_i = true;
/*  57 */     setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
/*  58 */     this.foot3 = new ModelRenderer(this, 0, 13);
/*  59 */     this.foot3.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  60 */     this.foot3.func_78793_a(-0.7F, 21.0F, 0.7F);
/*  61 */     this.foot3.func_78787_b(64, 64);
/*  62 */     this.foot3.field_78809_i = true;
/*  63 */     setRotation(this.foot3, 0.7853982F, 0.0F, 0.7853982F);
/*  64 */     this.foot2 = new ModelRenderer(this, 0, 13);
/*  65 */     this.foot2.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  66 */     this.foot2.func_78793_a(-0.7F, 21.0F, -0.7F);
/*  67 */     this.foot2.func_78787_b(64, 64);
/*  68 */     this.foot2.field_78809_i = true;
/*  69 */     setRotation(this.foot2, -0.7853982F, 0.0F, 0.7853982F);
/*  70 */     this.foot1 = new ModelRenderer(this, 0, 13);
/*  71 */     this.foot1.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  72 */     this.foot1.func_78793_a(0.7F, 21.0F, -0.7F);
/*  73 */     this.foot1.func_78787_b(64, 64);
/*  74 */     this.foot1.field_78809_i = true;
/*  75 */     setRotation(this.foot1, -0.7853982F, 0.0F, -0.7853982F);
/*  76 */     this.foot4 = new ModelRenderer(this, 0, 13);
/*  77 */     this.foot4.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  78 */     this.foot4.func_78793_a(0.7F, 21.0F, 0.7F);
/*  79 */     this.foot4.func_78787_b(64, 64);
/*  80 */     this.foot4.field_78809_i = true;
/*  81 */     setRotation(this.foot4, 0.7853982F, 0.0F, -0.7853982F);
/*  82 */     this.ash = new ModelRenderer(this, 0, 20);
/*  83 */     this.ash.func_78789_a(-2.5F, 0.0F, -2.5F, 5, 0, 5);
/*  84 */     this.ash.func_78793_a(0.0F, 9.7F, 0.0F);
/*  85 */     this.ash.func_78787_b(64, 64);
/*  86 */     this.ash.field_78809_i = true;
/*  87 */     setRotation(this.ash, 0.0F, 0.0F, 0.0F);
/*  88 */     this.panSide1 = new ModelRenderer(this, 5, 12);
/*  89 */     this.panSide1.func_78789_a(-0.5F, -0.5F, -3.0F, 1, 1, 6);
/*  90 */     this.panSide1.func_78793_a(3.0F, 9.5F, 0.0F);
/*  91 */     this.panSide1.func_78787_b(64, 64);
/*  92 */     this.panSide1.field_78809_i = true;
/*  93 */     setRotation(this.panSide1, 0.0F, 0.0F, 0.0F);
/*     */     
/*  95 */     this.panSide2 = new ModelRenderer(this, 4, 26);
/*  96 */     this.panSide2.func_78789_a(-3.0F, -0.5F, -0.5F, 6, 1, 1);
/*  97 */     this.panSide2.func_78793_a(0.0F, 9.5F, 3.0F);
/*  98 */     this.panSide2.func_78787_b(64, 64);
/*  99 */     this.panSide2.field_78809_i = true;
/* 100 */     setRotation(this.panSide2, 0.0F, 0.0F, 0.0F);
/* 101 */     this.panSide4 = new ModelRenderer(this, 4, 26);
/* 102 */     this.panSide4.func_78789_a(-3.0F, -0.5F, -0.5F, 6, 1, 1);
/* 103 */     this.panSide4.func_78793_a(0.0F, 9.5F, -3.0F);
/* 104 */     this.panSide4.func_78787_b(64, 64);
/* 105 */     this.panSide4.field_78809_i = true;
/* 106 */     setRotation(this.panSide4, 0.0F, 0.0F, 0.0F);
/*     */     
/* 108 */     this.panSide3 = new ModelRenderer(this, 5, 12);
/* 109 */     this.panSide3.func_78789_a(-0.5F, -0.5F, -3.0F, 1, 1, 6);
/* 110 */     this.panSide3.func_78793_a(-3.0F, 9.5F, 0.0F);
/* 111 */     this.panSide3.func_78787_b(64, 64);
/* 112 */     this.panSide3.field_78809_i = true;
/* 113 */     setRotation(this.panSide3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 115 */     this.footBase = new ModelRenderer(this, 6, 0);
/* 116 */     this.footBase.func_78789_a(-1.5F, -0.5F, -1.5F, 3, 1, 3);
/* 117 */     this.footBase.func_78793_a(0.0F, 21.0F, 0.0F);
/* 118 */     this.footBase.func_78787_b(64, 64);
/* 119 */     this.footBase.field_78809_i = true;
/* 120 */     setRotation(this.footBase, 0.0F, 0.0F, 0.0F);
/* 121 */     this.panBase = new ModelRenderer(this, 6, 5);
/* 122 */     this.panBase.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 1, 6);
/* 123 */     this.panBase.func_78793_a(0.0F, 9.95F, 0.0F);
/* 124 */     this.panBase.func_78787_b(64, 64);
/* 125 */     this.panBase.field_78809_i = true;
/* 126 */     setRotation(this.panBase, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockBrazier.TileEntityBrazier tile)
/*     */   {
/* 132 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 133 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 134 */     this.leg1.func_78785_a(f5);
/* 135 */     this.leg2.func_78785_a(f5);
/* 136 */     this.leg3.func_78785_a(f5);
/* 137 */     this.leg4.func_78785_a(f5);
/* 138 */     this.foot3.func_78785_a(f5);
/* 139 */     this.foot2.func_78785_a(f5);
/* 140 */     this.foot1.func_78785_a(f5);
/* 141 */     this.foot4.func_78785_a(f5);
/*     */     
/* 143 */     this.panSide1.func_78785_a(f5);
/* 144 */     this.panSide2.func_78785_a(f5);
/* 145 */     this.panSide3.func_78785_a(f5);
/* 146 */     this.panSide4.func_78785_a(f5);
/* 147 */     this.footBase.func_78785_a(f5);
/* 148 */     this.panBase.func_78785_a(f5);
/* 149 */     this.panSide4.field_78795_f = 0.0F;
/* 150 */     this.panSide2.field_78795_f = 0.0F;
/* 151 */     this.panSide1.field_78808_h = 0.0F;
/* 152 */     this.panSide3.field_78808_h = 0.0F;
/* 153 */     if (tile != null) {
/* 154 */       int ingredientCount = tile.getIngredientCount();
/* 155 */       if (ingredientCount > 0) {
/* 156 */         this.ash.field_78797_d = (9.7F - (ingredientCount - 1) * 0.1F);
/* 157 */         this.ash.func_78785_a(f5);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 163 */     model.field_78795_f = x;
/* 164 */     model.field_78796_g = y;
/* 165 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 169 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelBrazier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */