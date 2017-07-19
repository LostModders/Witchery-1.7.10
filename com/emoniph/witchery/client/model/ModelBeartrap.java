/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBeartrap.TileEntityBeartrap;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelBeartrap
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer plate;
/*     */   public ModelRenderer base;
/*     */   public ModelRenderer armFront;
/*     */   public ModelRenderer armBack;
/*     */   public ModelRenderer diskLeft;
/*     */   public ModelRenderer diskRight;
/*     */   public ModelRenderer armRightFront;
/*     */   public ModelRenderer armLeftFront;
/*     */   public ModelRenderer armTooth1Front;
/*     */   public ModelRenderer armTooth2Front;
/*     */   public ModelRenderer armTooth3Front;
/*     */   public ModelRenderer armTooth4Front;
/*     */   public ModelRenderer armTooth5Front;
/*     */   public ModelRenderer armRightBack;
/*     */   public ModelRenderer armLeftBack;
/*     */   public ModelRenderer armTooth1Back;
/*     */   public ModelRenderer armTooth2Back;
/*     */   public ModelRenderer armTooth3Back;
/*     */   public ModelRenderer armTooth4Back;
/*     */   public ModelRenderer armTooth5Back;
/*     */   
/*     */   public ModelBeartrap()
/*     */   {
/*  40 */     this.field_78090_t = 32;
/*  41 */     this.field_78089_u = 32;
/*  42 */     this.armTooth4Back = new ModelRenderer(this, 0, 0);
/*  43 */     this.armTooth4Back.func_78793_a(0.0F, 0.0F, 0.0F);
/*  44 */     this.armTooth4Back.func_78790_a(1.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
/*  45 */     this.plate = new ModelRenderer(this, 1, 0);
/*  46 */     this.plate.func_78793_a(0.0F, 24.0F, 0.0F);
/*  47 */     this.plate.func_78790_a(-2.0F, -1.5F, -2.0F, 4, 1, 4, 0.0F);
/*  48 */     this.armFront = new ModelRenderer(this, 0, 9);
/*  49 */     this.armFront.func_78793_a(0.0F, 23.99F, 0.0F);
/*  50 */     this.armFront.func_78790_a(-4.5F, -1.0F, -7.0F, 9, 1, 1, 0.0F);
/*  51 */     this.armTooth2Front = new ModelRenderer(this, 0, 0);
/*  52 */     this.armTooth2Front.func_78793_a(0.0F, 0.0F, 0.0F);
/*  53 */     this.armTooth2Front.func_78790_a(-2.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
/*  54 */     this.armLeftFront = new ModelRenderer(this, 0, 12);
/*  55 */     this.armLeftFront.func_78793_a(0.0F, 0.0F, 0.0F);
/*  56 */     this.armLeftFront.func_78790_a(3.5F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
/*  57 */     this.armTooth4Front = new ModelRenderer(this, 0, 0);
/*  58 */     this.armTooth4Front.func_78793_a(0.0F, 0.0F, 0.0F);
/*  59 */     this.armTooth4Front.func_78790_a(1.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
/*  60 */     this.armLeftBack = new ModelRenderer(this, 0, 12);
/*  61 */     this.armLeftBack.func_78793_a(0.0F, 0.0F, 0.0F);
/*  62 */     this.armLeftBack.func_78790_a(3.5F, -1.0F, 0.0F, 1, 1, 6, 0.0F);
/*  63 */     this.armTooth3Front = new ModelRenderer(this, 0, 0);
/*  64 */     this.armTooth3Front.func_78793_a(0.0F, 0.0F, 0.0F);
/*  65 */     this.armTooth3Front.func_78790_a(-0.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
/*  66 */     this.armTooth3Back = new ModelRenderer(this, 0, 0);
/*  67 */     this.armTooth3Back.func_78793_a(0.0F, 0.0F, 0.0F);
/*  68 */     this.armTooth3Back.func_78790_a(-0.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
/*  69 */     this.armTooth5Front = new ModelRenderer(this, 0, 0);
/*  70 */     this.armTooth5Front.func_78793_a(0.0F, 0.0F, 0.0F);
/*  71 */     this.armTooth5Front.func_78790_a(3.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
/*  72 */     this.base = new ModelRenderer(this, 0, 20);
/*  73 */     this.base.func_78793_a(0.0F, 23.99F, 0.0F);
/*  74 */     this.base.func_78790_a(-5.0F, -1.0F, -0.5F, 10, 1, 1, 0.0F);
/*  75 */     this.armBack = new ModelRenderer(this, 0, 9);
/*  76 */     this.armBack.func_78793_a(0.0F, 23.99F, 0.0F);
/*  77 */     this.armBack.func_78790_a(-4.5F, -1.0F, 6.0F, 9, 1, 1, 0.0F);
/*  78 */     this.diskLeft = new ModelRenderer(this, 19, 3);
/*  79 */     this.diskLeft.func_78793_a(0.0F, 24.0F, 0.0F);
/*  80 */     this.diskLeft.func_78790_a(3.7F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
/*  81 */     this.armTooth2Back = new ModelRenderer(this, 0, 0);
/*  82 */     this.armTooth2Back.func_78793_a(0.0F, 0.0F, 0.0F);
/*  83 */     this.armTooth2Back.func_78790_a(-2.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
/*  84 */     this.armTooth1Back = new ModelRenderer(this, 0, 0);
/*  85 */     this.armTooth1Back.func_78793_a(0.0F, 0.0F, 0.0F);
/*  86 */     this.armTooth1Back.func_78790_a(-4.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
/*  87 */     this.armTooth5Back = new ModelRenderer(this, 0, 0);
/*  88 */     this.armTooth5Back.func_78793_a(0.0F, 0.0F, 0.0F);
/*  89 */     this.armTooth5Back.func_78790_a(3.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
/*  90 */     this.armRightBack = new ModelRenderer(this, 0, 12);
/*  91 */     this.armRightBack.func_78793_a(0.0F, 0.0F, 0.0F);
/*  92 */     this.armRightBack.func_78790_a(-4.5F, -1.0F, 0.0F, 1, 1, 6, 0.0F);
/*  93 */     this.armTooth1Front = new ModelRenderer(this, 0, 0);
/*  94 */     this.armTooth1Front.func_78793_a(0.0F, 0.0F, 0.0F);
/*  95 */     this.armTooth1Front.func_78790_a(-4.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
/*  96 */     this.armRightFront = new ModelRenderer(this, 0, 12);
/*  97 */     this.armRightFront.func_78793_a(0.0F, 0.0F, 0.0F);
/*  98 */     this.armRightFront.func_78790_a(-4.5F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
/*  99 */     this.diskRight = new ModelRenderer(this, 19, 3);
/* 100 */     this.diskRight.func_78793_a(0.0F, 24.0F, 0.0F);
/* 101 */     this.diskRight.func_78790_a(-4.7F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
/* 102 */     this.armBack.func_78792_a(this.armTooth4Back);
/* 103 */     this.armFront.func_78792_a(this.armTooth2Front);
/* 104 */     this.armFront.func_78792_a(this.armLeftFront);
/* 105 */     this.armFront.func_78792_a(this.armTooth4Front);
/* 106 */     this.armBack.func_78792_a(this.armLeftBack);
/* 107 */     this.armFront.func_78792_a(this.armTooth3Front);
/* 108 */     this.armBack.func_78792_a(this.armTooth3Back);
/* 109 */     this.armFront.func_78792_a(this.armTooth5Front);
/* 110 */     this.armBack.func_78792_a(this.armTooth2Back);
/* 111 */     this.armBack.func_78792_a(this.armTooth1Back);
/* 112 */     this.armBack.func_78792_a(this.armTooth5Back);
/* 113 */     this.armBack.func_78792_a(this.armRightBack);
/* 114 */     this.armFront.func_78792_a(this.armTooth1Front);
/* 115 */     this.armFront.func_78792_a(this.armRightFront);
/*     */   }
/*     */   
/*     */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 119 */     modelRenderer.field_78795_f = x;
/* 120 */     modelRenderer.field_78796_g = y;
/* 121 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockBeartrap.TileEntityBeartrap tile)
/*     */   {
/* 127 */     boolean inWorld = (tile != null) && (tile.func_145831_w() != null);
/*     */     
/* 129 */     if ((inWorld) && (!tile.isVisibleTo(Minecraft.func_71410_x().field_71439_g))) {
/* 130 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, Config.instance().mantrapAlpha);
/*     */     }
/*     */     
/* 133 */     this.base.func_78785_a(f5);
/* 134 */     this.diskLeft.func_78785_a(f5);
/* 135 */     this.diskRight.func_78785_a(f5);
/*     */     
/* 137 */     if ((inWorld) && (tile.isSprung())) {
/* 138 */       this.plate.field_78797_d = 23.8F;
/*     */     } else {
/* 140 */       this.plate.field_78797_d = 23.2F;
/*     */     }
/* 142 */     this.plate.func_78785_a(f5);
/*     */     
/* 144 */     if ((inWorld) && (tile.isSprung())) {
/* 145 */       this.armFront.field_78795_f = -1.2F;
/*     */     } else {
/* 147 */       this.armFront.field_78795_f = 0.0F;
/*     */     }
/* 149 */     this.armFront.func_78785_a(f5);
/*     */     
/* 151 */     if ((inWorld) && (tile.isSprung())) {
/* 152 */       this.armBack.field_78795_f = 1.2F;
/*     */     } else {
/* 154 */       this.armBack.field_78795_f = 0.0F;
/*     */     }
/* 156 */     this.armBack.func_78785_a(f5);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelBeartrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */