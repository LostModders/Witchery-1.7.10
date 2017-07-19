/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelFetishTrent
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer body;
/*     */   ModelRenderer armLeft;
/*     */   ModelRenderer armRight;
/*     */   ModelRenderer legLeftUpper;
/*     */   ModelRenderer legLeftLower;
/*     */   ModelRenderer legRightUpper;
/*     */   ModelRenderer legRightLower;
/*     */   ModelRenderer headdress1;
/*     */   ModelRenderer headdress2;
/*     */   ModelRenderer headdress3;
/*     */   ModelRenderer face;
/*     */   
/*     */   public ModelFetishTrent()
/*     */   {
/*  30 */     this.field_78090_t = 64;
/*  31 */     this.field_78089_u = 64;
/*     */     
/*  33 */     this.body = new ModelRenderer(this, 0, 14);
/*  34 */     this.body.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 9, 6);
/*  35 */     this.body.func_78793_a(0.0F, 12.0F, 0.0F);
/*  36 */     this.body.func_78787_b(64, 64);
/*  37 */     this.body.field_78809_i = true;
/*  38 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*     */     
/*  40 */     this.face = new ModelRenderer(this, 18, 1);
/*  41 */     this.face.func_78789_a(-3.0F, 1.0F, -2.9F, 6, 7, 0);
/*  42 */     this.face.func_78793_a(0.0F, 12.0F, 0.0F);
/*  43 */     this.face.func_78787_b(64, 64);
/*  44 */     this.face.field_78809_i = true;
/*  45 */     setRotation(this.face, 0.0F, 0.0F, 0.0F);
/*     */     
/*  47 */     this.armLeft = new ModelRenderer(this, 0, 0);
/*  48 */     this.armLeft.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  49 */     this.armLeft.func_78793_a(2.0F, 13.0F, 0.0F);
/*  50 */     this.armLeft.func_78787_b(64, 64);
/*  51 */     this.armLeft.field_78809_i = true;
/*  52 */     setRotation(this.armLeft, -0.1858931F, 0.0F, -0.7435722F);
/*  53 */     this.armRight = new ModelRenderer(this, 0, 0);
/*  54 */     this.armRight.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  55 */     this.armRight.func_78793_a(-2.0F, 13.0F, 0.0F);
/*  56 */     this.armRight.func_78787_b(64, 64);
/*  57 */     this.armRight.field_78809_i = true;
/*  58 */     setRotation(this.armRight, -0.1858931F, 0.0F, 0.8551081F);
/*  59 */     this.legLeftUpper = new ModelRenderer(this, 9, 0);
/*  60 */     this.legLeftUpper.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  61 */     this.legLeftUpper.func_78793_a(2.0F, 18.0F, 0.0F);
/*  62 */     this.legLeftUpper.func_78787_b(64, 64);
/*  63 */     this.legLeftUpper.field_78809_i = true;
/*  64 */     setRotation(this.legLeftUpper, -0.1487144F, 0.0F, -0.2602503F);
/*  65 */     this.legLeftLower = new ModelRenderer(this, 11, 8);
/*  66 */     this.legLeftLower.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 3, 1);
/*  67 */     this.legLeftLower.func_78793_a(3.0F, 21.0F, -0.5F);
/*  68 */     this.legLeftLower.func_78787_b(64, 64);
/*  69 */     this.legLeftLower.field_78809_i = true;
/*  70 */     setRotation(this.legLeftLower, 0.0743572F, 0.0F, -0.1115358F);
/*  71 */     this.legRightUpper = new ModelRenderer(this, 9, 0);
/*  72 */     this.legRightUpper.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  73 */     this.legRightUpper.func_78793_a(-2.0F, 18.0F, 0.0F);
/*  74 */     this.legRightUpper.func_78787_b(64, 64);
/*  75 */     this.legRightUpper.field_78809_i = true;
/*  76 */     setRotation(this.legRightUpper, 0.1858931F, 0.0F, 0.3346075F);
/*  77 */     this.legRightLower = new ModelRenderer(this, 11, 8);
/*  78 */     this.legRightLower.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 3, 1);
/*  79 */     this.legRightLower.func_78793_a(-3.0F, 21.0F, 0.5F);
/*  80 */     this.legRightLower.func_78787_b(64, 64);
/*  81 */     this.legRightLower.field_78809_i = true;
/*  82 */     setRotation(this.legRightLower, 0.1858931F, 0.0F, 0.2230717F);
/*  83 */     this.headdress1 = new ModelRenderer(this, 0, 30);
/*  84 */     this.headdress1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  85 */     this.headdress1.func_78793_a(0.0F, 13.0F, 1.0F);
/*  86 */     this.headdress1.func_78787_b(64, 64);
/*  87 */     this.headdress1.field_78809_i = true;
/*  88 */     setRotation(this.headdress1, 0.1115358F, 0.0F, -2.862753F);
/*  89 */     this.headdress2 = new ModelRenderer(this, 0, 30);
/*  90 */     this.headdress2.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  91 */     this.headdress2.func_78793_a(-1.0F, 13.0F, 0.0F);
/*  92 */     this.headdress2.func_78787_b(64, 64);
/*  93 */     this.headdress2.field_78809_i = true;
/*  94 */     setRotation(this.headdress2, 0.3717861F, 0.0F, 2.639681F);
/*  95 */     this.headdress3 = new ModelRenderer(this, 0, 30);
/*  96 */     this.headdress3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
/*  97 */     this.headdress3.func_78793_a(-1.0F, 13.0F, 0.0F);
/*  98 */     this.headdress3.func_78787_b(64, 64);
/*  99 */     this.headdress3.field_78809_i = true;
/* 100 */     setRotation(this.headdress3, -0.4461433F, 0.0F, 2.862753F);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockFetish.TileEntityFetish tile) {
/* 104 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 105 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 107 */     this.body.func_78785_a(f5);
/* 108 */     this.armLeft.func_78785_a(f5);
/* 109 */     this.armRight.func_78785_a(f5);
/* 110 */     this.legLeftUpper.func_78785_a(f5);
/* 111 */     this.legLeftLower.func_78785_a(f5);
/* 112 */     this.legRightUpper.func_78785_a(f5);
/* 113 */     this.legRightLower.func_78785_a(f5);
/* 114 */     this.headdress1.func_78785_a(f5);
/* 115 */     this.headdress2.func_78785_a(f5);
/* 116 */     this.headdress3.func_78785_a(f5);
/*     */     
/* 118 */     int colorIndex = 9;
/* 119 */     if (tile != null) {
/* 120 */       int color = tile.getColor();
/* 121 */       if ((color >= 0) && (color <= 15)) {
/* 122 */         colorIndex = color;
/*     */       }
/*     */     }
/*     */     
/* 126 */     GL11.glColor4f(ModelBroom.fleeceColorTable[colorIndex][0], ModelBroom.fleeceColorTable[colorIndex][1], ModelBroom.fleeceColorTable[colorIndex][2], 1.0F);
/*     */     
/* 128 */     this.face.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 132 */     model.field_78795_f = x;
/* 133 */     model.field_78796_g = y;
/* 134 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 139 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelFetishTrent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */