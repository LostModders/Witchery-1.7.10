/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityOwl;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelOwl extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer leftleg;
/*     */   
/*     */   public ModelOwl()
/*     */   {
/*  27 */     this.field_78090_t = 64;
/*  28 */     this.field_78089_u = 32;
/*     */     
/*  30 */     func_78085_a("head.beak", 30, 0);
/*  31 */     func_78085_a("head.hornRight", 37, 0);
/*  32 */     func_78085_a("head.hornLeft", 37, 0);
/*  33 */     func_78085_a("head.head1", 0, 0);
/*     */     
/*  35 */     this.head = new ModelRenderer(this, "head");
/*  36 */     this.head.func_78793_a(0.0F, 15.0F, 0.0F);
/*  37 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  38 */     this.head.field_78809_i = true;
/*  39 */     this.head.func_78786_a("hornRight", -5.0F, -7.0F, -1.0F, 1, 3, 2);
/*  40 */     this.head.func_78786_a("hornLeft", 4.0F, -7.0F, -1.0F, 1, 3, 2);
/*  41 */     this.head.func_78786_a("beak", -1.0F, -3.0F, -4.0F, 2, 3, 1);
/*  42 */     this.head.func_78786_a("head1", -4.0F, -6.0F, -3.0F, 8, 6, 6);
/*  43 */     this.body = new ModelRenderer(this, 16, 16);
/*  44 */     this.body.func_78789_a(-3.0F, 0.0F, -2.0F, 6, 8, 4);
/*  45 */     this.body.func_78793_a(0.0F, 15.0F, 0.0F);
/*  46 */     this.body.func_78787_b(64, 32);
/*  47 */     this.body.field_78809_i = true;
/*  48 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  49 */     this.rightarm = new ModelRenderer(this, 40, 16);
/*  50 */     this.rightarm.func_78789_a(-1.0F, -1.0F, -2.0F, 1, 8, 4);
/*  51 */     this.rightarm.func_78793_a(-3.0F, 16.0F, 0.0F);
/*  52 */     this.rightarm.func_78787_b(64, 32);
/*  53 */     this.rightarm.field_78809_i = true;
/*  54 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/*  55 */     this.leftarm = new ModelRenderer(this, 40, 16);
/*  56 */     this.leftarm.func_78789_a(0.0F, -1.0F, -2.0F, 1, 8, 4);
/*  57 */     this.leftarm.func_78793_a(3.0F, 16.0F, 0.0F);
/*  58 */     this.leftarm.func_78787_b(64, 32);
/*  59 */     this.leftarm.field_78809_i = true;
/*  60 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/*  61 */     this.rightleg = new ModelRenderer(this, 0, 16);
/*  62 */     this.rightleg.func_78789_a(-1.0F, 0.0F, -2.0F, 2, 1, 4);
/*  63 */     this.rightleg.func_78793_a(-2.0F, 23.0F, -1.0F);
/*  64 */     this.rightleg.func_78787_b(64, 32);
/*  65 */     this.rightleg.field_78809_i = true;
/*  66 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/*  67 */     this.leftleg = new ModelRenderer(this, 0, 16);
/*  68 */     this.leftleg.func_78789_a(-1.0F, 0.0F, -2.0F, 2, 1, 4);
/*  69 */     this.leftleg.func_78793_a(2.0F, 23.0F, -1.0F);
/*  70 */     this.leftleg.func_78787_b(64, 32);
/*  71 */     this.leftleg.field_78809_i = true;
/*  72 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/*     */     
/*  74 */     this.rightleg.func_78793_a(-2.0F, 8.0F, -1.0F);
/*  75 */     this.leftleg.func_78793_a(2.0F, 8.0F, -1.0F);
/*     */     
/*  77 */     this.body.func_78792_a(this.leftleg);
/*  78 */     this.body.func_78792_a(this.rightleg);
/*     */   }
/*     */   
/*     */   public static boolean isLanded(Entity entity) {
/*  82 */     Block block = entity.field_70170_p.func_147439_a(MathHelper.func_76128_c(entity.field_70165_t), (int)(entity.field_70163_u - 0.01D), MathHelper.func_76128_c(entity.field_70161_v));
/*  83 */     Material material = block.func_149688_o();
/*  84 */     if ((material == Material.field_151584_j) || (material.func_76220_a())) {
/*  85 */       return true;
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  93 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/*  95 */     EntityOwl entitybat = (EntityOwl)entity;
/*     */     
/*     */ 
/*  98 */     if ((entity.field_70181_x == 0.0D) && (entity.field_70159_w == 0.0D) && (entity.field_70179_y == 0.0D) && (isLanded(entity)))
/*     */     {
/* 100 */       this.body.field_78795_f = 0.0F;
/* 101 */       this.leftarm.field_78808_h = 0.0F;
/* 102 */       this.rightarm.field_78808_h = 0.0F;
/* 103 */       this.rightleg.field_78795_f = 0.0F;
/* 104 */       this.leftleg.field_78795_f = 0.0F;
/*     */     }
/*     */     else
/*     */     {
/* 108 */       float f6 = 57.295776F;
/* 109 */       this.body.field_78795_f = (0.7853982F + MathHelper.func_76134_b(f2 * 0.1F) * 0.15F);
/* 110 */       this.body.field_78796_g = 0.0F;
/*     */       
/* 112 */       this.rightleg.field_78795_f = (0.7853982F + MathHelper.func_76134_b(f2 * 0.1F) * 0.15F);
/* 113 */       this.leftleg.field_78795_f = (0.7853982F + MathHelper.func_76134_b(f2 * 0.1F) * 0.15F);
/*     */       
/* 115 */       this.rightarm.field_78808_h = (MathHelper.func_76134_b(f2 * 0.5F) * 3.1415927F * 0.2F * 2.0F + 1.4F);
/* 116 */       this.leftarm.field_78808_h = (-this.rightarm.field_78808_h);
/*     */     }
/*     */     
/* 119 */     if (entitybat.func_70906_o()) {
/* 120 */       this.rightleg.field_78796_g = 0.5F;
/* 121 */       this.leftleg.field_78796_g = (-this.rightleg.field_78796_g);
/*     */     } else {
/* 123 */       this.rightleg.field_78796_g = 0.1F;
/* 124 */       this.leftleg.field_78796_g = (-this.rightleg.field_78796_g);
/*     */     }
/*     */     
/* 127 */     this.head.field_78796_g = (f3 / 57.295776F);
/* 128 */     this.head.field_78795_f = (f4 / 57.295776F);
/*     */     
/*     */ 
/*     */ 
/* 132 */     if (this.field_78091_s)
/*     */     {
/* 134 */       float p6 = 2.0F;
/* 135 */       GL11.glPushMatrix();
/* 136 */       GL11.glScalef(1.5F / p6, 1.5F / p6, 1.5F / p6);
/* 137 */       GL11.glTranslatef(0.0F, 11.0F * f5, 0.0F);
/* 138 */       this.head.func_78785_a(f5);
/* 139 */       GL11.glPopMatrix();
/* 140 */       GL11.glPushMatrix();
/* 141 */       GL11.glScalef(1.0F / p6, 1.0F / p6, 1.0F / p6);
/* 142 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 143 */       this.body.func_78785_a(f5);
/* 144 */       this.rightarm.func_78785_a(f5);
/* 145 */       this.leftarm.func_78785_a(f5);
/* 146 */       GL11.glPopMatrix();
/*     */     }
/*     */     else
/*     */     {
/* 150 */       this.head.func_78785_a(f5);
/* 151 */       this.body.func_78785_a(f5);
/* 152 */       this.rightarm.func_78785_a(f5);
/* 153 */       this.leftarm.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 158 */     model.field_78795_f = x;
/* 159 */     model.field_78796_g = y;
/* 160 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 164 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelOwl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */