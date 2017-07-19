/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntitySummonedUndead;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelSpectre extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer robeUpper;
/*     */   ModelRenderer robeLower;
/*     */   ModelRenderer mouth;
/*     */   private final boolean reachingArms;
/*     */   
/*     */   public ModelSpectre(boolean reachingArms)
/*     */   {
/*  25 */     this.reachingArms = reachingArms;
/*     */     
/*  27 */     this.field_78090_t = 64;
/*  28 */     this.field_78089_u = 32;
/*     */     
/*  30 */     this.head = new ModelRenderer(this, 0, 16);
/*  31 */     this.head.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  32 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/*  33 */     this.head.func_78787_b(64, 32);
/*  34 */     this.head.field_78809_i = true;
/*  35 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  36 */     this.mouth = new ModelRenderer(this, 56, 0);
/*     */     
/*  38 */     this.mouth.func_78789_a(0.0F, 0.0F, 0.0F, 4, 5, 0);
/*  39 */     this.mouth.func_78793_a(-2.0F, -4.0F, -4.02F);
/*  40 */     this.mouth.func_78787_b(64, 32);
/*  41 */     this.mouth.field_78809_i = true;
/*  42 */     setRotation(this.mouth, 0.0F, 0.0F, 0.0F);
/*     */     
/*  44 */     this.body = new ModelRenderer(this, 16, 0);
/*  45 */     this.body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 10, 4);
/*  46 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/*  47 */     this.body.func_78787_b(64, 32);
/*  48 */     this.body.field_78809_i = true;
/*  49 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  50 */     this.rightarm = new ModelRenderer(this, 0, 0);
/*  51 */     this.rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
/*  52 */     this.rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  53 */     this.rightarm.func_78787_b(64, 32);
/*  54 */     this.rightarm.field_78809_i = true;
/*  55 */     setRotation(this.rightarm, -1.396263F, 0.0F, 0.0F);
/*  56 */     this.leftarm = new ModelRenderer(this, 0, 0);
/*  57 */     this.leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
/*  58 */     this.leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
/*  59 */     this.leftarm.func_78787_b(64, 32);
/*  60 */     this.leftarm.field_78809_i = true;
/*  61 */     setRotation(this.leftarm, -1.396263F, 0.0F, 0.0F);
/*  62 */     this.robeUpper = new ModelRenderer(this, 38, 9);
/*  63 */     this.robeUpper.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 6, 5);
/*  64 */     this.robeUpper.func_78793_a(0.0F, 10.0F, 0.0F);
/*  65 */     this.robeUpper.func_78787_b(64, 32);
/*  66 */     this.robeUpper.field_78809_i = true;
/*  67 */     setRotation(this.robeUpper, 0.0F, 0.0F, 0.0F);
/*  68 */     this.robeLower = new ModelRenderer(this, 32, 20);
/*  69 */     this.robeLower.func_78789_a(-5.0F, 0.0F, -2.0F, 10, 6, 6);
/*  70 */     this.robeLower.func_78793_a(0.0F, 16.0F, 0.0F);
/*  71 */     this.robeLower.func_78787_b(64, 32);
/*  72 */     this.robeLower.field_78809_i = true;
/*  73 */     setRotation(this.robeLower, 0.0F, 0.0F, 0.0F);
/*     */     
/*  75 */     this.head.func_78792_a(this.mouth);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  79 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  80 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  81 */     boolean screaming = (entity != null) && ((entity instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)entity).isScreaming());
/*  82 */     this.mouth.field_78807_k = (!screaming);
/*  83 */     this.mouth.func_78793_a(-2.0F, -4.0F, -4.02F);
/*  84 */     this.head.func_78785_a(f5);
/*     */     
/*  86 */     this.body.func_78785_a(f5);
/*  87 */     this.rightarm.func_78785_a(f5);
/*  88 */     this.leftarm.func_78785_a(f5);
/*  89 */     this.robeUpper.func_78785_a(f5);
/*  90 */     this.robeLower.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  94 */     model.field_78795_f = x;
/*  95 */     model.field_78796_g = y;
/*  96 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
/* 100 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
/*     */     
/* 102 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 103 */     this.head.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 105 */     if (this.reachingArms) {
/* 106 */       this.rightarm.field_78795_f = -1.5F;
/* 107 */       this.leftarm.field_78795_f = -1.5F;
/*     */     } else {
/* 109 */       if ((entity != null) && ((entity instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)entity).isScreaming())) {
/* 110 */         this.rightarm.field_78808_h = 1.0F;
/* 111 */         this.leftarm.field_78808_h = -1.0F;
/*     */       } else {
/* 113 */         this.rightarm.field_78808_h = 0.0F;
/* 114 */         this.leftarm.field_78808_h = 0.0F;
/*     */       }
/* 116 */       this.rightarm.field_78795_f = -0.2F;
/* 117 */       this.leftarm.field_78795_f = -0.2F;
/*     */     }
/*     */     
/* 120 */     this.rightarm.field_78796_g = 0.0F;
/*     */     
/* 122 */     this.leftarm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 132 */     this.rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 133 */     this.leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelSpectre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */