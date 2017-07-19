/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelGoblin extends ModelBase
/*     */ {
/*     */   public ModelRenderer bipedHead;
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public int heldItemLeft;
/*     */   public int heldItemRight;
/*     */   public boolean isSneak;
/*     */   public boolean aimedBow;
/*     */   
/*     */   public ModelGoblin()
/*     */   {
/*  31 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public ModelGoblin(float scale) {
/*  35 */     this.field_78090_t = 64;
/*  36 */     this.field_78089_u = 32;
/*     */     
/*  38 */     func_78085_a("head.face", 0, 0);
/*  39 */     func_78085_a("head.nose1", 34, 3);
/*  40 */     func_78085_a("head.nose2", 34, 0);
/*  41 */     func_78085_a("head.nose3", 33, 9);
/*  42 */     func_78085_a("head.earTipLeft", 46, 0);
/*  43 */     func_78085_a("head.earInnerLeft", 39, 0);
/*  44 */     func_78085_a("head.earInnerRight", 39, 0);
/*  45 */     func_78085_a("head.earTipRight", 46, 0);
/*     */     
/*  47 */     this.bipedHead = new ModelRenderer(this, "head");
/*  48 */     this.bipedHead.func_78793_a(0.0F, 11.0F, 0.0F);
/*  49 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  50 */     this.bipedHead.field_78809_i = true;
/*  51 */     this.bipedHead.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  52 */     this.bipedHead.func_78786_a("nose1", -0.5F, -6.0F, -5.0F, 1, 3, 1);
/*  53 */     this.bipedHead.func_78786_a("nose2", -0.5F, -5.0F, -6.0F, 1, 1, 1);
/*  54 */     this.bipedHead.func_78786_a("nose3", -0.5F, -4.0F, -7.0F, 1, 2, 2);
/*  55 */     this.bipedHead.func_78786_a("earTipLeft", 6.0F, -7.0F, 0.0F, 2, 2, 1);
/*  56 */     this.bipedHead.func_78786_a("earInnerLeft", 4.0F, -7.0F, 0.0F, 2, 3, 1);
/*  57 */     this.bipedHead.func_78786_a("earInnerRight", -6.0F, -7.0F, 0.0F, 2, 3, 1);
/*  58 */     this.bipedHead.func_78786_a("earTipRight", -8.0F, -7.0F, 0.0F, 2, 2, 1);
/*  59 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  60 */     this.bipedBody.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 7, 4, scale);
/*  61 */     this.bipedBody.func_78793_a(0.0F, 11.0F, 0.0F);
/*  62 */     this.bipedBody.func_78787_b(64, 32);
/*  63 */     this.bipedBody.field_78809_i = true;
/*  64 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  65 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  66 */     this.bipedRightArm.func_78790_a(-3.0F, -3.0F, -2.0F, 4, 12, 4, scale);
/*  67 */     this.bipedRightArm.func_78793_a(-5.0F, 12.0F, 0.0F);
/*  68 */     this.bipedRightArm.func_78787_b(64, 32);
/*  69 */     this.bipedRightArm.field_78809_i = true;
/*  70 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  71 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  72 */     this.bipedLeftArm.func_78790_a(-1.0F, -3.0F, -2.0F, 4, 12, 4, scale);
/*  73 */     this.bipedLeftArm.func_78793_a(5.0F, 12.0F, 0.0F);
/*  74 */     this.bipedLeftArm.func_78787_b(64, 32);
/*  75 */     this.bipedLeftArm.field_78809_i = true;
/*  76 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  77 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  78 */     this.bipedRightLeg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
/*  79 */     this.bipedRightLeg.func_78793_a(-2.0F, 18.0F, 0.0F);
/*  80 */     this.bipedRightLeg.func_78787_b(64, 32);
/*  81 */     this.bipedRightLeg.field_78809_i = true;
/*  82 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  83 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  84 */     this.bipedLeftLeg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
/*  85 */     this.bipedLeftLeg.func_78793_a(2.0F, 18.0F, 0.0F);
/*  86 */     this.bipedLeftLeg.func_78787_b(64, 32);
/*  87 */     this.bipedLeftLeg.field_78809_i = true;
/*  88 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  92 */     model.field_78795_f = x;
/*  93 */     model.field_78796_g = y;
/*  94 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  98 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/*     */     
/* 100 */     if (this.field_78091_s) {
/* 101 */       float f6 = 2.0F;
/* 102 */       GL11.glPushMatrix();
/* 103 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 104 */       GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
/* 105 */       this.bipedHead.func_78785_a(par7);
/* 106 */       GL11.glPopMatrix();
/* 107 */       GL11.glPushMatrix();
/* 108 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 109 */       GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
/* 110 */       this.bipedBody.func_78785_a(par7);
/* 111 */       this.bipedRightArm.func_78785_a(par7);
/* 112 */       this.bipedLeftArm.func_78785_a(par7);
/* 113 */       this.bipedRightLeg.func_78785_a(par7);
/* 114 */       this.bipedLeftLeg.func_78785_a(par7);
/* 115 */       GL11.glPopMatrix();
/*     */     } else {
/* 117 */       this.bipedHead.func_78785_a(par7);
/* 118 */       this.bipedBody.func_78785_a(par7);
/* 119 */       this.bipedRightArm.func_78785_a(par7);
/* 120 */       this.bipedLeftArm.func_78785_a(par7);
/* 121 */       this.bipedRightLeg.func_78785_a(par7);
/* 122 */       this.bipedLeftLeg.func_78785_a(par7);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 133 */     this.bipedHead.field_78796_g = (par4 / 57.295776F);
/* 134 */     this.bipedHead.field_78795_f = (par5 / 57.295776F);
/* 135 */     this.bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 136 */     this.bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 137 */     this.bipedRightArm.field_78808_h = 0.0F;
/* 138 */     this.bipedLeftArm.field_78808_h = 0.0F;
/* 139 */     this.bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 140 */     this.bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 141 */     this.bipedRightLeg.field_78796_g = 0.0F;
/* 142 */     this.bipedLeftLeg.field_78796_g = 0.0F;
/*     */     
/* 144 */     if (this.field_78093_q) {
/* 145 */       this.bipedRightArm.field_78795_f += -0.62831855F;
/* 146 */       this.bipedLeftArm.field_78795_f += -0.62831855F;
/* 147 */       this.bipedRightLeg.field_78795_f = -1.2566371F;
/* 148 */       this.bipedLeftLeg.field_78795_f = -1.2566371F;
/* 149 */       this.bipedRightLeg.field_78796_g = 0.31415927F;
/* 150 */       this.bipedLeftLeg.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 153 */     if (this.heldItemLeft != 0) {
/* 154 */       this.bipedLeftArm.field_78795_f = (this.bipedLeftArm.field_78795_f * 0.5F - 0.31415927F * this.heldItemLeft);
/*     */     }
/*     */     
/* 157 */     if (this.heldItemRight != 0) {
/* 158 */       this.bipedRightArm.field_78795_f = (this.bipedRightArm.field_78795_f * 0.5F - 0.31415927F * this.heldItemRight);
/* 159 */       if ((par7Entity != null) && ((par7Entity instanceof EntityGoblin))) {
/* 160 */         EntityGoblin goblin = (EntityGoblin)par7Entity;
/* 161 */         if (goblin.isWorking()) {
/* 162 */           if ((goblin.func_70694_bm() != null) && (goblin.func_70694_bm().func_77973_b() == Witchery.Items.KOBOLDITE_PICKAXE)) {
/* 163 */             ModelRenderer tmp336_333 = this.bipedRightArm;tmp336_333.field_78795_f = ((float)(tmp336_333.field_78795_f - par7Entity.field_70173_aa % 6 * 0.3D));
/*     */           } else {
/* 165 */             ModelRenderer tmp366_363 = this.bipedRightArm;tmp366_363.field_78795_f = ((float)(tmp366_363.field_78795_f - par7Entity.field_70173_aa % 20 * 0.1D));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 171 */     this.bipedRightArm.field_78796_g = 0.0F;
/* 172 */     this.bipedLeftArm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 176 */     if (this.field_78095_p > -9990.0F) {
/* 177 */       float f6 = this.field_78095_p;
/* 178 */       this.bipedBody.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 179 */       this.bipedRightArm.field_78798_e = (MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 180 */       this.bipedRightArm.field_78800_c = (-MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 181 */       this.bipedLeftArm.field_78798_e = (-MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 182 */       this.bipedLeftArm.field_78800_c = (MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 183 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g;
/* 184 */       this.bipedLeftArm.field_78796_g += this.bipedBody.field_78796_g;
/* 185 */       this.bipedLeftArm.field_78795_f += this.bipedBody.field_78796_g;
/* 186 */       f6 = 1.0F - this.field_78095_p;
/* 187 */       f6 *= f6;
/* 188 */       f6 *= f6;
/* 189 */       f6 = 1.0F - f6;
/* 190 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 191 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.bipedHead.field_78795_f - 0.7F) * 0.75F;
/* 192 */       this.bipedRightArm.field_78795_f = ((float)(this.bipedRightArm.field_78795_f - (f7 * 1.2D + f8)));
/* 193 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0F;
/* 194 */       this.bipedRightArm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 197 */     boolean isWorshipping = (par7Entity != null) && ((par7Entity instanceof EntityGoblin)) && (((EntityGoblin)par7Entity).isWorshipping());
/*     */     
/* 199 */     if ((this.isSneak) || (isWorshipping)) {
/* 200 */       this.bipedBody.field_78795_f = 0.5F;
/* 201 */       this.bipedRightArm.field_78795_f -= 2.2F;
/* 202 */       this.bipedLeftArm.field_78795_f -= 2.2F;
/* 203 */       this.bipedRightLeg.field_78798_e = 3.0F;
/* 204 */       this.bipedLeftLeg.field_78798_e = 3.0F;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 210 */       this.bipedHead.field_78795_f = 0.5F;
/* 211 */       this.bipedRightLeg.field_78797_d = 18.0F;
/* 212 */       this.bipedLeftLeg.field_78797_d = 18.0F;
/* 213 */       this.bipedHead.field_78797_d = 13.0F;
/* 214 */       this.bipedBody.field_78797_d = 13.0F;
/*     */     } else {
/* 216 */       this.bipedBody.field_78795_f = 0.0F;
/* 217 */       this.bipedRightLeg.field_78798_e = 0.1F;
/* 218 */       this.bipedLeftLeg.field_78798_e = 0.1F;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 223 */       this.bipedRightLeg.field_78797_d = 18.0F;
/* 224 */       this.bipedLeftLeg.field_78797_d = 18.0F;
/* 225 */       this.bipedHead.field_78797_d = 11.0F;
/* 226 */       this.bipedBody.field_78797_d = 11.0F;
/*     */     }
/*     */     
/* 229 */     this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 230 */     this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 231 */     this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 232 */     this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     
/* 234 */     if (this.aimedBow) {
/* 235 */       float f6 = 0.0F;
/* 236 */       float f7 = 0.0F;
/* 237 */       this.bipedRightArm.field_78808_h = 0.0F;
/* 238 */       this.bipedLeftArm.field_78808_h = 0.0F;
/* 239 */       this.bipedRightArm.field_78796_g = (-(0.1F - f6 * 0.6F) + this.bipedHead.field_78796_g);
/* 240 */       this.bipedLeftArm.field_78796_g = (0.1F - f6 * 0.6F + this.bipedHead.field_78796_g + 0.4F);
/* 241 */       this.bipedRightArm.field_78795_f = (-1.5707964F + this.bipedHead.field_78795_f);
/* 242 */       this.bipedLeftArm.field_78795_f = (-1.5707964F + this.bipedHead.field_78795_f);
/* 243 */       this.bipedRightArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 244 */       this.bipedLeftArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 245 */       this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 246 */       this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 247 */       this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 248 */       this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGoblin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */