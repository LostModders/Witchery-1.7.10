/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityVampire;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelVampireArmor
/*     */   extends ModelBiped
/*     */ {
/*     */   private ModelRenderer skirtFront;
/*     */   private ModelRenderer skirtMiddle;
/*     */   private ModelRenderer skirtMiddle2;
/*     */   private ModelRenderer skirtMiddle3;
/*     */   private ModelRenderer skirtBack;
/*     */   private ModelRenderer cloakMain;
/*     */   private ModelRenderer cloakLeft;
/*     */   private ModelRenderer cloakRight;
/*     */   public ModelRenderer hat;
/*     */   public ModelRenderer hatBrim;
/*     */   public ModelRenderer chest;
/*     */   private boolean legs;
/*     */   private boolean female;
/*     */   private boolean metal;
/*     */   
/*     */   public ModelVampireArmor(float scale, boolean legs, boolean female, boolean metal)
/*     */   {
/*  40 */     super(scale, 0.0F, 64, 96);
/*     */     
/*  42 */     this.legs = legs;
/*  43 */     this.female = female;
/*  44 */     this.metal = metal;
/*     */     
/*  46 */     this.skirtBack = new ModelRenderer(this, 26, 32);
/*  47 */     this.skirtBack.func_78793_a(0.0F, 11.0F, 0.0F);
/*  48 */     this.skirtBack.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
/*     */     
/*  50 */     this.skirtFront = new ModelRenderer(this, 26, 50);
/*  51 */     this.skirtFront.func_78793_a(0.0F, 11.0F, 0.0F);
/*  52 */     this.skirtFront.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
/*     */     
/*  54 */     this.skirtMiddle = new ModelRenderer(this, 26, 68);
/*  55 */     this.skirtMiddle.func_78793_a(0.0F, 11.0F, 0.0F);
/*  56 */     this.skirtMiddle.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
/*  57 */     this.skirtMiddle2 = new ModelRenderer(this, 26, 68);
/*  58 */     this.skirtMiddle2.func_78793_a(0.0F, 11.0F, 0.0F);
/*  59 */     this.skirtMiddle2.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
/*     */     
/*  61 */     this.skirtMiddle3 = new ModelRenderer(this, 26, 68);
/*  62 */     this.skirtMiddle3.func_78793_a(0.0F, 11.0F, 0.0F);
/*  63 */     this.skirtMiddle3.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
/*     */     
/*  65 */     this.cloakLeft = new ModelRenderer(this, 0, 56);
/*  66 */     this.cloakLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  67 */     this.cloakLeft.func_78790_a(-3.5F, -8.0F, 4.0F, 7, 7, 1, 0.0F);
/*  68 */     setRotateAngle(this.cloakLeft, -0.34906584F, 0.5108652F, 0.41086525F);
/*     */     
/*  70 */     this.cloakRight = new ModelRenderer(this, 0, 56);
/*  71 */     this.cloakRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  72 */     this.cloakRight.func_78790_a(-3.5F, -8.0F, 4.0F, 7, 7, 1, 0.0F);
/*  73 */     setRotateAngle(this.cloakRight, -0.34906584F, -0.5108652F, -0.41086525F);
/*     */     
/*  75 */     this.cloakMain = new ModelRenderer(this, 0, 33);
/*  76 */     this.cloakMain.func_78793_a(0.0F, 1.0F, 0.0F);
/*  77 */     this.cloakMain.func_78790_a(-6.0F, 0.0F, 2.5F, 12, 22, 1, 0.0F);
/*  78 */     setRotateAngle(this.cloakMain, 0.045553092F, 0.0F, 0.0F);
/*     */     
/*  80 */     float hatScale = 0.6F;
/*     */     
/*  82 */     this.hatBrim = new ModelRenderer(this, 0, 85);
/*  83 */     this.hatBrim.func_78793_a(0.0F, 0.0F, 0.0F);
/*  84 */     this.hatBrim.func_78790_a(-5.0F, -7.0F, -5.0F, 10, 1, 10, hatScale + 0.1F);
/*     */     
/*  86 */     this.hat = new ModelRenderer(this, 0, 67);
/*  87 */     this.hat.func_78793_a(0.0F, 0.0F, 0.0F);
/*  88 */     this.hat.func_78790_a(-4.0F, -15.0F, -4.0F, 8, 8, 8, hatScale);
/*     */     
/*  90 */     if (!metal) {
/*  91 */       this.field_78116_c.func_78792_a(this.hat);
/*  92 */       this.field_78116_c.func_78792_a(this.hatBrim);
/*     */     }
/*     */     
/*  95 */     this.chest = new ModelRenderer(this, 16, 67);
/*  96 */     this.chest.func_78790_a(-4.0F, -2.0F, -5.0F, 8, 4, 4, 0.0F);
/*  97 */     this.chest.func_78793_a(0.0F, 2.0F, 0.0F);
/*  98 */     setRotateAngle(this.chest, 0.7853982F, 0.0F, 0.0F);
/*     */   }
/*     */   
/* 101 */   ResourceLocation chain = new ResourceLocation("witchery", "textures/entities/vampirearmor_chain.png");
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 105 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*     */     
/* 107 */     if (!this.metal) {
/* 108 */       this.field_78114_d.field_78806_j = false;
/*     */     }
/*     */     
/* 111 */     if (this.legs) {
/* 112 */       if ((!this.field_78093_q) && (this.field_78123_h.field_78806_j) && (this.female)) {
/* 113 */         this.skirtBack.func_78785_a(f5);
/* 114 */         this.skirtFront.func_78785_a(f5);
/* 115 */         this.skirtMiddle.func_78785_a(f5);
/* 116 */         this.skirtMiddle2.func_78785_a(f5);
/* 117 */         this.skirtMiddle3.func_78785_a(f5);
/*     */       }
/* 119 */     } else if (this.field_78115_e.field_78806_j) {
/* 120 */       if (!(entity instanceof EntityVampire)) {
/* 121 */         this.cloakRight.func_78785_a(f5);
/* 122 */         this.cloakLeft.func_78785_a(f5);
/* 123 */         this.cloakMain.func_78785_a(f5);
/*     */       }
/* 125 */       if (this.female) {
/* 126 */         this.chest.func_78785_a(f5);
/*     */       }
/* 128 */       if (this.metal) {
/* 129 */         GL11.glPushMatrix();
/* 130 */         float scale = 1.06F;
/* 131 */         GL11.glScalef(scale, scale, scale);
/*     */         
/* 133 */         Minecraft.func_71410_x().func_110434_K().func_110577_a(this.chain);
/* 134 */         if (this.female) {
/* 135 */           this.chest.func_78785_a(f5);
/*     */         }
/*     */         
/* 138 */         this.field_78115_e.func_78785_a(f5);
/*     */         
/* 140 */         GL11.glScalef(scale, scale, scale); ModelRenderer 
/* 141 */           tmp243_240 = this.field_78112_f;tmp243_240.field_78797_d = ((float)(tmp243_240.field_78797_d - 0.05D)); ModelRenderer 
/* 142 */           tmp260_257 = this.field_78113_g;tmp260_257.field_78797_d = ((float)(tmp260_257.field_78797_d - 0.05D));
/* 143 */         this.field_78112_f.func_78785_a(f5);
/* 144 */         this.field_78113_g.func_78785_a(f5);
/* 145 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 151 */     modelRenderer.field_78795_f = x;
/* 152 */     modelRenderer.field_78796_g = y;
/* 153 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 159 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/* 161 */     this.field_78112_f.field_78797_d = 2.0F;
/* 162 */     this.field_78113_g.field_78797_d = 2.0F;
/*     */     
/* 164 */     this.hat.field_78797_d = -0.5F;
/*     */     
/* 166 */     this.skirtBack.field_78795_f = Math.max(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f);
/* 167 */     this.skirtMiddle.field_78795_f = (Math.max(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f) * 0.5F);
/*     */     
/* 169 */     this.skirtFront.field_78795_f = Math.min(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f);
/* 170 */     this.skirtMiddle2.field_78795_f = (Math.min(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f) * 0.5F);
/*     */     
/* 172 */     if (this.field_78117_n) {
/* 173 */       this.skirtBack.field_78798_e = (this.skirtFront.field_78798_e = this.skirtMiddle3.field_78798_e = this.skirtMiddle.field_78798_e = this.skirtMiddle2.field_78798_e = 4.0F);
/* 174 */       this.skirtBack.field_78797_d = (this.skirtFront.field_78797_d = this.skirtMiddle3.field_78797_d = this.skirtMiddle.field_78797_d = this.skirtMiddle2.field_78797_d = 8.0F);
/*     */       
/* 176 */       this.cloakMain.field_78795_f = 0.6F;
/*     */     } else {
/* 178 */       this.skirtBack.field_78798_e = (this.skirtFront.field_78798_e = this.skirtMiddle3.field_78798_e = this.skirtMiddle.field_78798_e = this.skirtMiddle2.field_78798_e = 0.0F);
/* 179 */       this.skirtBack.field_78797_d = (this.skirtFront.field_78797_d = this.skirtMiddle3.field_78797_d = this.skirtMiddle.field_78797_d = this.skirtMiddle2.field_78797_d = 11.0F);
/*     */       
/* 181 */       this.cloakMain.field_78795_f = 0.045553092F;
/* 182 */       if (p_78087_2_ > 0.1D) {
/* 183 */         ModelRenderer tmp346_343 = this.cloakMain;tmp346_343.field_78795_f = ((float)(tmp346_343.field_78795_f + (p_78087_2_ * 0.8D - 0.1D)));
/*     */       }
/*     */     }
/*     */     
/* 187 */     if (this.field_78116_c.field_78795_f < -0.15D) {
/* 188 */       this.cloakLeft.field_78795_f = (this.field_78116_c.field_78795_f - 0.15F);
/* 189 */       this.cloakRight.field_78795_f = (this.field_78116_c.field_78795_f - 0.15F);
/*     */     } else {
/* 191 */       this.cloakLeft.field_78795_f = (this.cloakRight.field_78795_f = -0.3F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelVampireArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */