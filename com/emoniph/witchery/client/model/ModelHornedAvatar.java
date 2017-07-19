/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelHornedAvatar extends ModelBase
/*     */ {
/*     */   ModelRenderer horns;
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer chest;
/*     */   ModelRenderer rightshin;
/*     */   ModelRenderer rightfoot;
/*     */   ModelRenderer rightforearm;
/*     */   ModelRenderer hips;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer leftforearm;
/*     */   ModelRenderer leftleg;
/*     */   ModelRenderer leftshin;
/*     */   ModelRenderer leftfoot;
/*     */   ModelRenderer spear;
/*     */   
/*     */   public ModelHornedAvatar()
/*     */   {
/*  39 */     this.field_78090_t = 128;
/*  40 */     this.field_78089_u = 128;
/*  41 */     func_78085_a("spear.shaft", 61, 14);
/*  42 */     func_78085_a("spear.tip1", 60, 8);
/*  43 */     func_78085_a("spear.tip2", 60, 5);
/*     */     
/*  45 */     this.horns = new ModelRenderer(this, 0, 88);
/*  46 */     this.horns.func_78789_a(-10.0F, -24.0F, 0.0F, 20, 17, 0);
/*  47 */     this.horns.func_78793_a(0.0F, -16.0F, 0.0F);
/*  48 */     this.horns.func_78787_b(128, 128);
/*  49 */     this.horns.field_78809_i = true;
/*  50 */     setRotation(this.horns, 0.0F, 0.0F, 0.0F);
/*  51 */     this.head = new ModelRenderer(this, 4, 112);
/*  52 */     this.head.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  53 */     this.head.func_78793_a(0.0F, -16.0F, 0.0F);
/*  54 */     this.head.func_78787_b(128, 128);
/*  55 */     this.head.field_78809_i = true;
/*  56 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  57 */     this.body = new ModelRenderer(this, 12, 61);
/*  58 */     this.body.func_78789_a(-6.0F, 0.0F, -3.0F, 12, 8, 6);
/*  59 */     this.body.func_78793_a(0.0F, -8.0F, 0.0F);
/*  60 */     this.body.func_78787_b(128, 128);
/*  61 */     this.body.field_78809_i = true;
/*  62 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  63 */     this.rightarm = new ModelRenderer(this, 72, 50);
/*  64 */     this.rightarm.func_78789_a(-4.0F, -2.0F, -2.0F, 4, 13, 4);
/*  65 */     this.rightarm.func_78793_a(-10.0F, -13.0F, 0.0F);
/*  66 */     this.rightarm.func_78787_b(128, 128);
/*  67 */     this.rightarm.field_78809_i = true;
/*  68 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/*  69 */     this.rightleg = new ModelRenderer(this, 72, 0);
/*  70 */     this.rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 10, 4);
/*  71 */     this.rightleg.func_78793_a(-4.0F, 3.0F, -1.0F);
/*  72 */     this.rightleg.func_78787_b(128, 128);
/*  73 */     this.rightleg.field_78809_i = true;
/*  74 */     setRotation(this.rightleg, 0.5235988F, 0.0F, 0.0F);
/*  75 */     this.chest = new ModelRenderer(this, 0, 43);
/*  76 */     this.chest.func_78789_a(0.0F, 0.0F, 0.0F, 20, 8, 10);
/*  77 */     this.chest.func_78793_a(-10.0F, -16.0F, -5.0F);
/*  78 */     this.chest.func_78787_b(128, 128);
/*  79 */     this.chest.field_78809_i = true;
/*  80 */     setRotation(this.chest, 0.0F, 0.0F, 0.0F);
/*  81 */     this.rightshin = new ModelRenderer(this, 68, 14);
/*  82 */     this.rightshin.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 14, 6);
/*  83 */     this.rightshin.func_78793_a(-4.0F, 12.0F, 5.0F);
/*  84 */     this.rightshin.func_78787_b(128, 128);
/*  85 */     this.rightshin.field_78809_i = true;
/*  86 */     setRotation(this.rightshin, -0.5235988F, 0.0F, 0.0F);
/*  87 */     this.rightfoot = new ModelRenderer(this, 69, 34);
/*  88 */     this.rightfoot.func_78789_a(-2.0F, 0.0F, -5.0F, 4, 3, 7);
/*  89 */     this.rightfoot.func_78793_a(-4.0F, 21.0F, 0.0F);
/*  90 */     this.rightfoot.func_78787_b(128, 128);
/*  91 */     this.rightfoot.field_78809_i = true;
/*  92 */     setRotation(this.rightfoot, 0.0F, 0.0F, 0.0F);
/*  93 */     this.rightforearm = new ModelRenderer(this, 68, 67);
/*  94 */     this.rightforearm.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 14, 6);
/*  95 */     this.rightforearm.func_78793_a(-12.0F, -3.0F, 0.0F);
/*  96 */     this.rightforearm.func_78787_b(128, 128);
/*  97 */     this.rightforearm.field_78809_i = true;
/*  98 */     setRotation(this.rightforearm, -0.5235988F, 0.0F, 0.0F);
/*  99 */     this.hips = new ModelRenderer(this, 8, 75);
/* 100 */     this.hips.func_78789_a(-7.0F, 0.0F, -4.0F, 14, 4, 8);
/* 101 */     this.hips.func_78793_a(0.0F, 0.0F, 0.0F);
/* 102 */     this.hips.func_78787_b(128, 128);
/* 103 */     this.hips.field_78809_i = true;
/* 104 */     setRotation(this.hips, 0.0F, 0.0F, 0.0F);
/* 105 */     this.leftarm = new ModelRenderer(this, 72, 50);
/* 106 */     this.leftarm.func_78789_a(0.0F, -2.0F, -2.0F, 4, 13, 4);
/* 107 */     this.leftarm.func_78793_a(10.0F, -13.0F, 0.0F);
/* 108 */     this.leftarm.func_78787_b(128, 128);
/* 109 */     this.leftarm.field_78809_i = true;
/* 110 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/* 111 */     this.leftforearm = new ModelRenderer(this, 68, 67);
/* 112 */     this.leftforearm.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 14, 6);
/* 113 */     this.leftforearm.func_78793_a(12.0F, -3.0F, 0.0F);
/* 114 */     this.leftforearm.func_78787_b(128, 128);
/* 115 */     this.leftforearm.field_78809_i = true;
/* 116 */     setRotation(this.leftforearm, -0.5235988F, 0.0F, 0.0F);
/* 117 */     this.leftleg = new ModelRenderer(this, 72, 0);
/* 118 */     this.leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 10, 4);
/* 119 */     this.leftleg.func_78793_a(4.0F, 3.0F, -1.0F);
/* 120 */     this.leftleg.func_78787_b(128, 128);
/* 121 */     this.leftleg.field_78809_i = true;
/* 122 */     setRotation(this.leftleg, 0.5235988F, 0.0F, 0.0F);
/* 123 */     this.leftshin = new ModelRenderer(this, 68, 14);
/* 124 */     this.leftshin.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 14, 6);
/* 125 */     this.leftshin.func_78793_a(4.0F, 12.0F, 5.0F);
/* 126 */     this.leftshin.func_78787_b(128, 128);
/* 127 */     this.leftshin.field_78809_i = true;
/* 128 */     setRotation(this.leftshin, -0.5235988F, 0.0F, 0.0F);
/* 129 */     this.leftfoot = new ModelRenderer(this, 69, 34);
/* 130 */     this.leftfoot.func_78789_a(-2.0F, 0.0F, -5.0F, 4, 3, 7);
/* 131 */     this.leftfoot.func_78793_a(4.0F, 21.0F, 0.0F);
/* 132 */     this.leftfoot.func_78787_b(128, 128);
/* 133 */     this.leftfoot.field_78809_i = true;
/* 134 */     setRotation(this.leftfoot, 0.0F, 0.0F, 0.0F);
/*     */     
/* 136 */     this.head.func_78792_a(this.horns);
/*     */     
/* 138 */     this.leftleg.func_78792_a(this.leftshin);
/* 139 */     this.leftshin.func_78792_a(this.leftfoot);
/* 140 */     this.rightleg.func_78792_a(this.rightshin);
/* 141 */     this.rightshin.func_78792_a(this.rightfoot);
/*     */     
/* 143 */     this.rightarm.func_78792_a(this.rightforearm);
/* 144 */     this.leftarm.func_78792_a(this.leftforearm);
/*     */     
/* 146 */     this.spear = new ModelRenderer(this, "spear");
/* 147 */     this.spear.func_78793_a(-12.0F, 4.0F, -10.0F);
/* 148 */     setRotation(this.spear, 0.0F, 0.0F, 0.0F);
/* 149 */     this.spear.field_78809_i = true;
/* 150 */     this.spear.func_78786_a("shaft", -0.5F, -30.0F, -0.5F, 1, 50, 1);
/* 151 */     this.spear.func_78786_a("tip1", -1.5F, -35.0F, 0.0F, 3, 6, 0);
/* 152 */     this.spear.func_78786_a("tip2", 0.0F, -35.0F, -1.5F, 0, 6, 3);
/*     */     
/* 154 */     this.rightforearm.func_78792_a(this.spear);
/*     */     
/* 156 */     this.horns.func_78793_a(0.0F, 0.0F, 0.0F);
/* 157 */     this.leftforearm.func_78793_a(2.0F, 10.0F, 0.0F);
/* 158 */     this.rightforearm.func_78793_a(-2.0F, 10.0F, 0.0F);
/*     */     
/* 160 */     this.leftshin.func_78793_a(0.0F, 10.0F, 0.0F);
/* 161 */     this.leftshin.field_78795_f = -0.9F;
/*     */     
/* 163 */     this.leftfoot.func_78793_a(0.0F, 10.0F, 0.0F);
/* 164 */     this.leftfoot.field_78795_f = 0.5F;
/*     */     
/* 166 */     this.rightshin.func_78793_a(0.0F, 10.0F, 0.0F);
/* 167 */     this.rightshin.field_78795_f = -0.9F;
/*     */     
/* 169 */     this.rightfoot.func_78793_a(0.0F, 10.0F, 0.0F);
/* 170 */     this.rightfoot.field_78795_f = 0.5F;
/*     */     
/* 172 */     this.spear.func_78793_a(0.0F, 12.0F, 0.0F);
/* 173 */     this.spear.field_78795_f = 1.5F;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 178 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 179 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 180 */     this.head.func_78785_a(f5);
/* 181 */     this.body.func_78785_a(f5);
/* 182 */     this.rightarm.func_78785_a(f5);
/* 183 */     this.rightleg.func_78785_a(f5);
/* 184 */     this.chest.func_78785_a(f5);
/* 185 */     this.hips.func_78785_a(f5);
/* 186 */     this.leftarm.func_78785_a(f5);
/* 187 */     this.leftleg.func_78785_a(f5);
/*     */     
/* 189 */     Minecraft mc = Minecraft.func_71410_x();
/* 190 */     if ((mc.field_71474_y.field_74347_j) && (Config.instance().renderHuntsmanGlintEffect)) {
/* 191 */       float f9 = entity.field_70173_aa;
/* 192 */       mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
/* 193 */       GL11.glEnable(3042);
/* 194 */       float f10 = 0.5F;
/* 195 */       GL11.glColor4f(f10, f10, f10, 1.0F);
/* 196 */       GL11.glDepthFunc(514);
/* 197 */       GL11.glDepthMask(false);
/*     */       
/* 199 */       for (int k = 0; k < 2; k++)
/*     */       {
/* 201 */         GL11.glDisable(2896);
/* 202 */         float f11 = 0.76F;
/* 203 */         GL11.glColor4f(0.0F * f11, 0.5F * f11, 0.0F * f11, 1.0F);
/* 204 */         GL11.glBlendFunc(768, 1);
/* 205 */         GL11.glMatrixMode(5890);
/* 206 */         GL11.glLoadIdentity();
/* 207 */         float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
/* 208 */         float f13 = 0.33333334F;
/* 209 */         GL11.glScalef(f13, f13, f13);
/* 210 */         GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
/* 211 */         GL11.glTranslatef(0.0F, f12, 0.0F);
/* 212 */         GL11.glMatrixMode(5888);
/*     */         
/* 214 */         this.head.func_78785_a(f5);
/* 215 */         this.body.func_78785_a(f5);
/* 216 */         this.rightarm.func_78785_a(f5);
/* 217 */         this.rightleg.func_78785_a(f5);
/* 218 */         this.chest.func_78785_a(f5);
/* 219 */         this.hips.func_78785_a(f5);
/* 220 */         this.leftarm.func_78785_a(f5);
/* 221 */         this.leftleg.func_78785_a(f5);
/*     */       }
/*     */       
/* 224 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 225 */       GL11.glMatrixMode(5890);
/* 226 */       GL11.glDepthMask(true);
/* 227 */       GL11.glLoadIdentity();
/* 228 */       GL11.glMatrixMode(5888);
/* 229 */       GL11.glEnable(2896);
/* 230 */       GL11.glDisable(3042);
/* 231 */       GL11.glDepthFunc(515);
/*     */     }
/*     */   }
/*     */   
/* 235 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 238 */     model.field_78795_f = x;
/* 239 */     model.field_78796_g = y;
/* 240 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 248 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 249 */     this.head.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 251 */     this.leftleg.field_78795_f = (-1.3F * func_78172_a(par1, 13.0F) * par2 + 0.5F);
/* 252 */     this.rightleg.field_78795_f = (1.3F * func_78172_a(par1, 13.0F) * par2 + 0.5F);
/*     */     
/* 254 */     float angle = -1.5F * func_78172_a(par1, 13.0F) * par2 - 1.0F;
/*     */     
/* 256 */     this.leftshin.field_78795_f = (0.8F * (this.rightleg.field_78795_f - 0.5F) - 1.0F);
/* 257 */     this.rightshin.field_78795_f = (0.8F * (this.leftleg.field_78795_f - 0.5F) - 1.0F);
/*     */     
/* 259 */     this.leftfoot.field_78795_f = Math.max(1.4F * (this.leftleg.field_78795_f - 0.5F) + 0.5F, 0.2F);
/* 260 */     this.rightfoot.field_78795_f = Math.max(1.4F * (this.rightleg.field_78795_f - 0.5F) + 0.5F, 0.2F);
/*     */     
/* 262 */     this.leftleg.field_78796_g = 0.0F;
/* 263 */     this.rightleg.field_78796_g = 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 273 */     EntityHornedHuntsman entityDemon = (EntityHornedHuntsman)par1EntityLiving;
/* 274 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 276 */     if (i > 0) {
/* 277 */       this.rightarm.field_78795_f = (-2.0F + 1.5F * func_78172_a(i - par4, 10.0F));
/*     */     }
/*     */     else
/*     */     {
/* 281 */       this.rightarm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3 * 0.2F);
/* 282 */       this.leftarm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3 * 0.2F);
/*     */     }
/*     */   }
/*     */   
/*     */   private float func_78172_a(float par1, float par2) {
/* 287 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelHornedAvatar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */