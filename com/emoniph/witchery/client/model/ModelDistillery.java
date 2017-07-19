/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockDistillery.TileEntityDistillery;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelDistillery
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer stillBase;
/*     */   ModelRenderer stillMiddle;
/*     */   ModelRenderer stillTop;
/*     */   ModelRenderer stillBend;
/*     */   ModelRenderer stillTube;
/*     */   ModelRenderer frameTop;
/*     */   ModelRenderer frameLeft;
/*     */   ModelRenderer frameRight;
/*     */   ModelRenderer frameBase;
/*     */   ModelRenderer bottle1;
/*     */   ModelRenderer bottle2;
/*     */   ModelRenderer bottle3;
/*     */   ModelRenderer bottle4;
/*     */   
/*     */   public ModelDistillery()
/*     */   {
/*  32 */     this.field_78090_t = 64;
/*  33 */     this.field_78089_u = 64;
/*  34 */     func_78085_a("bottle1.bottle1Body", 52, 26);
/*  35 */     func_78085_a("bottle1.bottle1Neck", 60, 24);
/*  36 */     func_78085_a("bottle1.bottle1Top", 56, 21);
/*  37 */     func_78085_a("bottle2.bottle2Body", 52, 26);
/*  38 */     func_78085_a("bottle2.bottle2Neck", 60, 24);
/*  39 */     func_78085_a("bottle2.bottle2Top", 56, 21);
/*  40 */     func_78085_a("bottle3.bottle3Body", 52, 26);
/*  41 */     func_78085_a("bottle3.bottle3Neck", 60, 24);
/*  42 */     func_78085_a("bottle3.bottle3Top", 56, 21);
/*  43 */     func_78085_a("bottle4.bottle4Body", 52, 26);
/*  44 */     func_78085_a("bottle4.bottle4Neck", 60, 24);
/*  45 */     func_78085_a("bottle4.bottle4Top", 56, 21);
/*     */     
/*  47 */     this.stillBase = new ModelRenderer(this, 0, 16);
/*  48 */     this.stillBase.func_78789_a(0.0F, 0.0F, 0.0F, 10, 6, 10);
/*  49 */     this.stillBase.func_78793_a(-5.0F, 18.0F, -2.0F);
/*  50 */     this.stillBase.func_78787_b(64, 64);
/*  51 */     this.stillBase.field_78809_i = true;
/*  52 */     setRotation(this.stillBase, 0.0F, 0.0F, 0.0F);
/*  53 */     this.stillMiddle = new ModelRenderer(this, 0, 6);
/*  54 */     this.stillMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 6, 4, 6);
/*  55 */     this.stillMiddle.func_78793_a(-3.0F, 14.0F, 0.0F);
/*  56 */     this.stillMiddle.func_78787_b(64, 64);
/*  57 */     this.stillMiddle.field_78809_i = true;
/*  58 */     setRotation(this.stillMiddle, 0.0F, 0.0F, 0.0F);
/*  59 */     this.stillTop = new ModelRenderer(this, 25, 9);
/*  60 */     this.stillTop.func_78789_a(0.0F, 0.0F, 0.0F, 4, 3, 4);
/*  61 */     this.stillTop.func_78793_a(-2.0F, 11.0F, 1.0F);
/*  62 */     this.stillTop.func_78787_b(64, 64);
/*  63 */     this.stillTop.field_78809_i = true;
/*  64 */     setRotation(this.stillTop, 0.0F, 0.0F, 0.0F);
/*  65 */     this.stillBend = new ModelRenderer(this, 0, 0);
/*  66 */     this.stillBend.func_78789_a(0.0F, 0.0F, 0.0F, 2, 2, 4);
/*  67 */     this.stillBend.func_78793_a(-1.0F, 9.0F, -1.0F);
/*  68 */     this.stillBend.func_78787_b(64, 64);
/*  69 */     this.stillBend.field_78809_i = true;
/*  70 */     setRotation(this.stillBend, 0.0F, 0.0F, 0.0F);
/*  71 */     this.stillTube = new ModelRenderer(this, 46, 10);
/*  72 */     this.stillTube.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 8);
/*  73 */     this.stillTube.func_78793_a(0.0F, 10.0F, 0.0F);
/*  74 */     this.stillTube.func_78787_b(64, 64);
/*  75 */     this.stillTube.field_78809_i = true;
/*     */     
/*  77 */     setRotation(this.stillTube, -2.341978F, 0.0F, 0.0F);
/*  78 */     this.frameTop = new ModelRenderer(this, 30, 6);
/*  79 */     this.frameTop.func_78789_a(0.0F, 0.0F, 0.0F, 16, 1, 1);
/*  80 */     this.frameTop.func_78793_a(-8.0F, 15.0F, -6.0F);
/*  81 */     this.frameTop.func_78787_b(64, 64);
/*  82 */     this.frameTop.field_78809_i = true;
/*  83 */     setRotation(this.frameTop, 0.0F, 0.0F, 0.0F);
/*  84 */     this.frameLeft = new ModelRenderer(this, 47, 24);
/*  85 */     this.frameLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 1);
/*  86 */     this.frameLeft.func_78793_a(-8.0F, 16.0F, -6.0F);
/*  87 */     this.frameLeft.func_78787_b(64, 64);
/*  88 */     this.frameLeft.field_78809_i = true;
/*  89 */     setRotation(this.frameLeft, 0.0F, 0.0F, 0.0F);
/*  90 */     this.frameRight = new ModelRenderer(this, 47, 24);
/*  91 */     this.frameRight.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 1);
/*  92 */     this.frameRight.func_78793_a(7.0F, 16.0F, -6.0F);
/*  93 */     this.frameRight.func_78787_b(64, 64);
/*  94 */     this.frameRight.field_78809_i = true;
/*  95 */     setRotation(this.frameRight, 0.0F, 0.0F, 0.0F);
/*  96 */     this.frameBase = new ModelRenderer(this, 22, 0);
/*  97 */     this.frameBase.func_78789_a(0.0F, 0.0F, 0.0F, 16, 1, 5);
/*  98 */     this.frameBase.func_78793_a(-8.0F, 23.0F, -8.0F);
/*  99 */     this.frameBase.func_78787_b(64, 64);
/* 100 */     this.frameBase.field_78809_i = true;
/* 101 */     setRotation(this.frameBase, 0.0F, 0.0F, 0.0F);
/* 102 */     this.bottle1 = new ModelRenderer(this, "bottle1");
/* 103 */     this.bottle1.func_78793_a(-7.0F, 16.0F, -7.0F);
/* 104 */     setRotation(this.bottle1, 0.0F, 0.0F, 0.0F);
/* 105 */     this.bottle1.field_78809_i = true;
/* 106 */     this.bottle1.func_78786_a("bottle1Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
/* 107 */     this.bottle1.func_78786_a("bottle1Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
/* 108 */     this.bottle1.func_78786_a("bottle1Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
/* 109 */     this.bottle2 = new ModelRenderer(this, "bottle2");
/* 110 */     this.bottle2.func_78793_a(-3.3F, 16.0F, -7.0F);
/* 111 */     setRotation(this.bottle2, 0.0174533F, 0.0F, 0.0F);
/* 112 */     this.bottle2.field_78809_i = true;
/* 113 */     this.bottle2.func_78786_a("bottle2Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
/* 114 */     this.bottle2.func_78786_a("bottle2Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
/* 115 */     this.bottle2.func_78786_a("bottle2Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
/* 116 */     this.bottle3 = new ModelRenderer(this, "bottle3");
/* 117 */     this.bottle3.func_78793_a(0.4F, 16.0F, -7.0F);
/* 118 */     setRotation(this.bottle3, 0.0F, 0.0F, 0.0F);
/* 119 */     this.bottle3.field_78809_i = true;
/* 120 */     this.bottle3.func_78786_a("bottle3Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
/* 121 */     this.bottle3.func_78786_a("bottle3Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
/* 122 */     this.bottle3.func_78786_a("bottle3Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
/* 123 */     this.bottle4 = new ModelRenderer(this, "bottle4");
/* 124 */     this.bottle4.func_78793_a(4.0F, 16.0F, -7.0F);
/* 125 */     setRotation(this.bottle4, 0.0F, 0.0F, 0.0F);
/* 126 */     this.bottle4.field_78809_i = true;
/* 127 */     this.bottle4.func_78786_a("bottle4Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
/* 128 */     this.bottle4.func_78786_a("bottle4Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
/* 129 */     this.bottle4.func_78786_a("bottle4Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tileEntity) {
/* 133 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 134 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 135 */     this.stillBase.func_78785_a(f5);
/* 136 */     this.stillMiddle.func_78785_a(f5);
/* 137 */     this.stillTop.func_78785_a(f5);
/* 138 */     this.stillBend.func_78785_a(f5);
/* 139 */     this.stillTube.func_78785_a(f5);
/* 140 */     this.frameTop.func_78785_a(f5);
/* 141 */     this.frameLeft.func_78785_a(f5);
/* 142 */     this.frameRight.func_78785_a(f5);
/* 143 */     this.frameBase.func_78785_a(f5);
/*     */     
/* 145 */     if ((tileEntity != null) && (tileEntity.func_145831_w() != null)) {
/* 146 */       BlockDistillery.TileEntityDistillery te = (BlockDistillery.TileEntityDistillery)tileEntity;
/* 147 */       ModelRenderer[] bottles = { this.bottle1, this.bottle2, this.bottle3, this.bottle4 };
/* 148 */       ItemStack jars = te.func_70301_a(2);
/* 149 */       if (jars != null) {
/* 150 */         for (int i = 0; (i < jars.field_77994_a) && (i < bottles.length); i++) {
/* 151 */           bottles[i].func_78785_a(f5);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 158 */     model.field_78795_f = x;
/* 159 */     model.field_78796_g = y;
/* 160 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 165 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelDistillery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */