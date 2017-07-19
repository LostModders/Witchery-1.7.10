/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelWitchesClothes
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer hat;
/*     */   ModelRenderer torso;
/*     */   ModelRenderer bottomBack;
/*     */   ModelRenderer bottomRight;
/*     */   ModelRenderer bottomLeft;
/*     */   ModelRenderer Shape1;
/*     */   ModelRenderer Shape2;
/*     */   ModelRenderer Shape3;
/*     */   ModelRenderer headRight1;
/*     */   ModelRenderer headLeft1;
/*     */   ModelRenderer legRightF;
/*     */   ModelRenderer legLeftF;
/*     */   ModelRenderer legRightB;
/*     */   ModelRenderer legLeftB;
/*     */   ModelRenderer bodyF;
/*     */   ModelRenderer bodyB;
/*     */   ModelRenderer armRightF;
/*     */   ModelRenderer armLeftF;
/*     */   ModelRenderer armRightB;
/*     */   ModelRenderer armLeftB;
/*     */   ModelRenderer armLeftOut;
/*     */   ModelRenderer armRightOut;
/*     */   ModelRenderer spikeLowerRight;
/*     */   ModelRenderer spikeLowerLeft;
/*     */   ModelRenderer spikeUpperLeft;
/*     */   ModelRenderer spikeUpperRight;
/*     */   ModelRenderer shoulderRight;
/*     */   ModelRenderer shoulderLeft;
/*     */   private ModelRenderer babasHat;
/*     */   
/*     */   public ModelWitchesClothes(float scale, boolean shoulders)
/*     */   {
/*  54 */     super(scale, 0.0F, 128, 64);
/*     */     
/*  56 */     func_78085_a("hat.hatBrim", 0, 49);
/*  57 */     func_78085_a("hat.hatCollar", 0, 36);
/*  58 */     func_78085_a("hat.hatBody", 31, 34);
/*  59 */     func_78085_a("hat.hatPoint", 50, 34);
/*     */     
/*     */ 
/*  62 */     this.hat = new ModelRenderer(this, "hat");
/*  63 */     this.hat.func_78793_a(0.0F, 0.0F, 0.0F);
/*  64 */     setRotation(this.hat, 0.0F, 0.0F, 0.0F);
/*  65 */     this.hat.field_78809_i = true;
/*  66 */     this.hat.func_78786_a("hatBrim", -7.0F, -7.0F, -7.0F, 14, 1, 14);
/*  67 */     this.hat.func_78786_a("hatCollar", -5.0F, -9.0F, -5.0F, 10, 2, 10);
/*  68 */     this.hat.func_78786_a("hatBody", -3.0F, -14.0F, -3.0F, 6, 5, 6);
/*  69 */     this.hat.func_78786_a("hatPoint", -1.0F, -17.0F, -1.0F, 2, 3, 2);
/*  70 */     this.field_78116_c.func_78792_a(this.hat);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */     this.babasHat = new ModelRenderer(this, 72, 48);
/*  84 */     this.babasHat.func_78793_a(-7.0F, -8.0F, -7.0F);
/*  85 */     this.babasHat.func_78790_a(0.0F, 0.0F, 0.0F, 14, 2, 14, 0.52F);
/*  86 */     setRotation(this.babasHat, 0.0F, 0.0F, 0.0F);
/*  87 */     this.babasHat.field_78809_i = true;
/*  88 */     this.field_78116_c.func_78792_a(this.babasHat);
/*     */     
/*  90 */     ModelRenderer modelrenderer = new ModelRenderer(this, 83, 29);
/*  91 */     modelrenderer.func_78793_a(3.75F, -4.0F, 4.0F);
/*  92 */     modelrenderer.func_78790_a(0.0F, 0.0F, 0.0F, 7, 4, 7, 0.4F);
/*  93 */     modelrenderer.field_78795_f = -0.05235988F;
/*  94 */     modelrenderer.field_78808_h = 0.02617994F;
/*  95 */     this.babasHat.func_78792_a(modelrenderer);
/*     */     
/*  97 */     ModelRenderer modelrenderer1 = new ModelRenderer(this, 83, 40);
/*  98 */     modelrenderer1.func_78793_a(1.75F, -4.0F, 2.0F);
/*  99 */     modelrenderer1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 100 */     modelrenderer1.field_78795_f = -0.10471976F;
/* 101 */     modelrenderer1.field_78808_h = 0.05235988F;
/* 102 */     modelrenderer.func_78792_a(modelrenderer1);
/*     */     
/* 104 */     ModelRenderer modelrenderer2 = new ModelRenderer(this, 81, 48);
/* 105 */     modelrenderer2.func_78793_a(1.75F, -2.0F, 2.0F);
/* 106 */     modelrenderer2.func_78790_a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
/* 107 */     modelrenderer2.field_78795_f = -0.20943952F;
/* 108 */     modelrenderer2.field_78808_h = 0.10471976F;
/* 109 */     modelrenderer1.func_78792_a(modelrenderer2);
/*     */     
/*     */ 
/* 112 */     this.torso = new ModelRenderer(this, 43, 46);
/* 113 */     this.torso.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 6, 4, scale);
/* 114 */     this.torso.func_78793_a(0.0F, 12.0F, 0.0F);
/* 115 */     this.torso.field_78809_i = true;
/* 116 */     setRotation(this.torso, 0.0F, 0.0F, 0.0F);
/* 117 */     this.field_78115_e.func_78792_a(this.torso);
/*     */     
/* 119 */     if (shoulders) {
/* 120 */       this.Shape1 = new ModelRenderer(this, 61, 32);
/* 121 */       this.Shape1.func_78790_a(0.0F, 0.0F, 0.0F, 5, 1, 6, scale + 0.1F);
/* 122 */       this.Shape1.func_78793_a(-9.0F, 0.0F, -3.0F);
/*     */       
/* 124 */       this.Shape1.field_78809_i = true;
/*     */       
/* 126 */       this.Shape2 = new ModelRenderer(this, 61, 39);
/* 127 */       this.Shape2.func_78790_a(0.0F, 0.0F, 0.0F, 5, 1, 6, scale + 0.1F);
/* 128 */       this.Shape2.func_78793_a(4.0F, 0.0F, -3.0F);
/*     */       
/* 130 */       this.Shape2.field_78809_i = true;
/*     */       
/*     */ 
/* 133 */       this.Shape2.func_78793_a(0.0F, -2.0F, -3.0F);
/* 134 */       this.Shape1.func_78793_a(-4.0F, -2.0F, -3.0F);
/*     */       
/* 136 */       this.field_78112_f.func_78792_a(this.Shape1);
/* 137 */       this.field_78113_g.func_78792_a(this.Shape2);
/*     */     }
/*     */     
/* 140 */     this.headRight1 = new ModelRenderer(this, 124, 0);
/* 141 */     this.headRight1.func_78789_a(-0.5F, -5.0F, -0.5F, 1, 5, 1);
/* 142 */     this.headRight1.func_78793_a(-4.0F, 0.0F, 0.0F);
/* 143 */     this.headRight1.func_78787_b(64, 128);
/* 144 */     this.headRight1.field_78809_i = true;
/* 145 */     setRotation(this.headRight1, -0.1487144F, 0.0F, -0.4089647F);
/*     */     
/* 147 */     this.headLeft1 = new ModelRenderer(this, 124, 0);
/* 148 */     this.headLeft1.func_78789_a(-0.5F, -5.0F, -0.5F, 1, 5, 1);
/* 149 */     this.headLeft1.func_78793_a(4.0F, 0.0F, 0.0F);
/* 150 */     this.headLeft1.func_78787_b(64, 128);
/* 151 */     this.headLeft1.field_78809_i = true;
/* 152 */     setRotation(this.headLeft1, -0.1487144F, 0.0F, 0.4089647F);
/*     */     
/* 154 */     this.legRightF = new ModelRenderer(this, 95, 0);
/* 155 */     this.legRightF.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
/* 156 */     this.legRightF.func_78793_a(-4.0F, 13.0F, -3.0F);
/* 157 */     this.legRightF.func_78787_b(64, 128);
/* 158 */     this.legRightF.field_78809_i = true;
/* 159 */     setRotation(this.legRightF, 0.0F, 0.0F, -0.2230717F);
/*     */     
/* 161 */     this.legLeftF = new ModelRenderer(this, 95, 0);
/* 162 */     this.legLeftF.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
/* 163 */     this.legLeftF.func_78793_a(1.0F, 13.0F, -3.0F);
/* 164 */     this.legLeftF.func_78787_b(64, 128);
/* 165 */     this.legLeftF.field_78809_i = true;
/* 166 */     setRotation(this.legLeftF, 0.0F, 0.0F, 0.1230717F);
/*     */     
/* 168 */     this.legRightB = new ModelRenderer(this, 95, 0);
/* 169 */     this.legRightB.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
/* 170 */     this.legRightB.func_78793_a(-4.0F, 13.0F, 2.0F);
/* 171 */     this.legRightB.func_78787_b(64, 128);
/* 172 */     this.legRightB.field_78809_i = true;
/* 173 */     setRotation(this.legRightB, 0.0F, 0.0F, -0.2230717F);
/*     */     
/* 175 */     this.legLeftB = new ModelRenderer(this, 95, 0);
/* 176 */     this.legLeftB.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
/* 177 */     this.legLeftB.func_78793_a(1.0F, 13.0F, 2.0F);
/* 178 */     this.legLeftB.func_78787_b(64, 128);
/* 179 */     this.legLeftB.field_78809_i = true;
/* 180 */     setRotation(this.legLeftB, 0.0F, 0.0F, 0.1230717F);
/*     */     
/* 182 */     this.bodyF = new ModelRenderer(this, 111, 0);
/* 183 */     this.bodyF.func_78789_a(0.0F, 0.0F, 0.0F, 6, 9, 1);
/* 184 */     this.bodyF.func_78793_a(-2.5F, 1.0F, -3.1F);
/* 185 */     this.bodyF.func_78787_b(64, 128);
/* 186 */     this.bodyF.field_78809_i = true;
/* 187 */     setRotation(this.bodyF, 0.0F, 0.0F, 0.1487144F);
/*     */     
/* 189 */     this.bodyB = new ModelRenderer(this, 111, 0);
/* 190 */     this.bodyB.func_78789_a(0.0F, 0.0F, 0.0F, 6, 9, 1);
/* 191 */     this.bodyB.func_78793_a(-2.5F, 1.0F, 2.1F);
/* 192 */     this.bodyB.func_78787_b(64, 128);
/* 193 */     this.bodyB.field_78809_i = true;
/* 194 */     setRotation(this.bodyB, 0.0F, 0.0F, 0.0887144F);
/*     */     
/* 196 */     this.armRightF = new ModelRenderer(this, 102, 0);
/* 197 */     this.armRightF.func_78789_a(0.0F, 0.0F, 0.0F, 3, 7, 1);
/* 198 */     this.armRightF.func_78793_a(-8.0F, 3.0F, -3.0F);
/* 199 */     this.armRightF.func_78787_b(64, 128);
/* 200 */     this.armRightF.field_78809_i = true;
/* 201 */     setRotation(this.armRightF, 0.0F, 0.0F, -0.1487144F);
/*     */     
/* 203 */     this.armLeftF = new ModelRenderer(this, 102, 0);
/* 204 */     this.armLeftF.func_78789_a(0.0F, 0.0F, 0.0F, 3, 6, 1);
/* 205 */     this.armLeftF.func_78793_a(5.0F, 2.0F, -3.0F);
/* 206 */     this.armLeftF.func_78787_b(64, 128);
/* 207 */     this.armLeftF.field_78809_i = true;
/* 208 */     setRotation(this.armLeftF, 0.0F, 0.0F, 0.0687144F);
/*     */     
/* 210 */     this.armRightB = new ModelRenderer(this, 102, 0);
/* 211 */     this.armRightB.func_78789_a(0.0F, 0.0F, 0.0F, 3, 7, 1);
/* 212 */     this.armRightB.func_78793_a(-8.0F, 3.0F, 2.0F);
/* 213 */     this.armRightB.func_78787_b(64, 128);
/* 214 */     this.armRightB.field_78809_i = true;
/* 215 */     setRotation(this.armRightB, 0.0F, 0.0F, -0.1487144F);
/*     */     
/* 217 */     this.armLeftB = new ModelRenderer(this, 102, 0);
/* 218 */     this.armLeftB.func_78789_a(0.0F, 0.0F, 0.0F, 3, 6, 1);
/* 219 */     this.armLeftB.func_78793_a(5.0F, 2.0F, 2.0F);
/* 220 */     this.armLeftB.func_78787_b(64, 128);
/* 221 */     this.armLeftB.field_78809_i = true;
/* 222 */     setRotation(this.armLeftB, 0.0F, 0.0F, 0.0687144F);
/*     */     
/* 224 */     this.armLeftOut = new ModelRenderer(this, 120, 0);
/* 225 */     this.armLeftOut.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 3);
/* 226 */     this.armLeftOut.func_78793_a(8.0F, 2.0F, -1.5F);
/* 227 */     this.armLeftOut.func_78787_b(128, 64);
/* 228 */     this.armLeftOut.field_78809_i = true;
/* 229 */     setRotation(this.armLeftOut, 0.0371786F, 0.0F, 0.0F);
/* 230 */     this.armRightOut = new ModelRenderer(this, 120, 0);
/* 231 */     this.armRightOut.func_78789_a(0.0F, 0.0F, 0.0F, 1, 6, 3);
/* 232 */     this.armRightOut.func_78793_a(-9.0F, 2.0F, -1.0F);
/* 233 */     this.armRightOut.func_78787_b(128, 64);
/* 234 */     this.armRightOut.field_78809_i = true;
/* 235 */     setRotation(this.armRightOut, -0.1858931F, 0.0F, 0.0F);
/* 236 */     this.spikeLowerRight = new ModelRenderer(this, 120, 0);
/* 237 */     this.spikeLowerRight.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
/* 238 */     this.spikeLowerRight.func_78793_a(-1.0F, 7.0F, 2.0F);
/* 239 */     this.spikeLowerRight.func_78787_b(128, 64);
/* 240 */     this.spikeLowerRight.field_78809_i = true;
/* 241 */     setRotation(this.spikeLowerRight, -0.7807508F, -0.1858931F, 0.0F);
/* 242 */     this.spikeLowerLeft = new ModelRenderer(this, 120, 0);
/* 243 */     this.spikeLowerLeft.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
/* 244 */     this.spikeLowerLeft.func_78793_a(1.0F, 7.0F, 2.0F);
/* 245 */     this.spikeLowerLeft.func_78787_b(128, 64);
/* 246 */     this.spikeLowerLeft.field_78809_i = true;
/* 247 */     setRotation(this.spikeLowerLeft, -0.7807508F, 0.1858931F, 0.0F);
/* 248 */     this.spikeUpperLeft = new ModelRenderer(this, 120, 0);
/* 249 */     this.spikeUpperLeft.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
/* 250 */     this.spikeUpperLeft.func_78793_a(2.0F, 3.0F, 2.0F);
/* 251 */     this.spikeUpperLeft.func_78787_b(128, 64);
/* 252 */     this.spikeUpperLeft.field_78809_i = true;
/* 253 */     setRotation(this.spikeUpperLeft, -0.7807508F, 0.1858931F, 0.0F);
/* 254 */     this.spikeUpperRight = new ModelRenderer(this, 120, 0);
/* 255 */     this.spikeUpperRight.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
/* 256 */     this.spikeUpperRight.func_78793_a(-2.0F, 3.0F, 2.0F);
/* 257 */     this.spikeUpperRight.func_78787_b(128, 64);
/* 258 */     this.spikeUpperRight.field_78809_i = true;
/* 259 */     setRotation(this.spikeUpperRight, -0.7807508F, -0.1858931F, 0.0F);
/* 260 */     this.shoulderRight = new ModelRenderer(this, 108, 0);
/* 261 */     this.shoulderRight.func_78789_a(0.0F, 0.0F, 0.0F, 5, 1, 5);
/* 262 */     this.shoulderRight.func_78793_a(-9.0F, -1.5F, -2.5F);
/* 263 */     this.shoulderRight.func_78787_b(128, 64);
/* 264 */     this.shoulderRight.field_78809_i = true;
/* 265 */     setRotation(this.shoulderRight, 0.0371786F, -0.1115358F, -0.1230717F);
/* 266 */     this.shoulderLeft = new ModelRenderer(this, 108, 0);
/* 267 */     this.shoulderLeft.func_78789_a(0.0F, 0.0F, 0.0F, 5, 1, 5);
/* 268 */     this.shoulderLeft.func_78793_a(4.0F, -2.5F, -1.5F);
/* 269 */     this.shoulderLeft.func_78787_b(128, 64);
/* 270 */     this.shoulderLeft.field_78809_i = true;
/* 271 */     setRotation(this.shoulderLeft, 0.0F, 0.2974289F, 0.1830717F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 276 */     if ((entity != null) && ((entity instanceof EntityLivingBase))) {
/* 277 */       EntityLivingBase living = (EntityLivingBase)entity;
/*     */       
/* 279 */       ItemStack hatStack = living.func_71124_b(4);
/* 280 */       if ((hatStack != null) && (this.field_78116_c.field_78806_j)) {
/* 281 */         boolean baba = hatStack.func_77973_b() == Witchery.Items.BABAS_HAT;
/* 282 */         this.hat.field_78806_j = (!baba);
/* 283 */         this.babasHat.field_78806_j = baba;
/*     */       }
/*     */     }
/*     */     
/* 287 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 288 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 289 */     if ((entity != null) && ((entity instanceof EntityLivingBase))) {
/* 290 */       EntityLivingBase living = (EntityLivingBase)entity;
/*     */       
/*     */ 
/*     */ 
/* 294 */       ItemStack belt = living.func_71124_b(2);
/* 295 */       if ((belt != null) && (belt.func_77973_b() == Witchery.Items.BARK_BELT) && (this.field_78115_e.field_78806_j)) {
/* 296 */         int charge = Math.min(Witchery.Items.BARK_BELT.getChargeLevel(belt), Witchery.Items.BARK_BELT.getMaxChargeLevel(living));
/*     */         
/* 298 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */         
/* 300 */         renderBark(f5, this.field_78115_e, this.bodyF, charge >= 1);
/* 301 */         renderBark(f5, this.field_78115_e, this.bodyB, charge >= 1);
/* 302 */         charge--;
/* 303 */         renderBark(f5, this.field_78115_e, this.spikeLowerLeft, charge >= 1);
/* 304 */         renderBark(f5, this.field_78115_e, this.spikeLowerRight, charge >= 1);
/* 305 */         renderBark(f5, this.field_78115_e, this.spikeUpperLeft, charge >= 1);
/* 306 */         renderBark(f5, this.field_78115_e, this.spikeUpperRight, charge >= 1);
/*     */         
/* 308 */         charge--;
/*     */         
/* 310 */         ItemStack shoes = living.func_71124_b(1);
/* 311 */         if ((shoes != null) && ((shoes.func_77973_b() instanceof ItemWitchesClothes))) {
/* 312 */           renderBark(f5, this.field_78123_h, this.legRightF, (charge >= 1) && (this.field_78115_e.field_78806_j), true);
/* 313 */           renderBark(f5, this.field_78123_h, this.legRightB, (charge >= 1) && (this.field_78115_e.field_78806_j), true);
/* 314 */           charge--;
/* 315 */           renderBark(f5, this.field_78124_i, this.legLeftF, (charge >= 1) && (this.field_78115_e.field_78806_j), true);
/* 316 */           renderBark(f5, this.field_78124_i, this.legLeftB, (charge >= 1) && (this.field_78115_e.field_78806_j), true);
/* 317 */           charge--;
/*     */         }
/*     */         
/* 320 */         ItemStack robes = living.func_71124_b(3);
/* 321 */         if ((robes != null) && ((robes.func_77973_b() instanceof ItemWitchesClothes))) {
/* 322 */           renderBark(f5, this.field_78112_f, this.armRightF, charge >= 1);
/* 323 */           renderBark(f5, this.field_78112_f, this.armRightOut, charge >= 1);
/* 324 */           renderBark(f5, this.field_78112_f, this.armRightB, charge >= 1);
/* 325 */           charge--;
/*     */           
/* 327 */           renderBark(f5, this.field_78113_g, this.armLeftB, charge >= 1);
/* 328 */           renderBark(f5, this.field_78113_g, this.armLeftF, charge >= 1);
/* 329 */           renderBark(f5, this.field_78113_g, this.armLeftOut, charge >= 1);
/* 330 */           charge--;
/*     */         }
/*     */         
/* 333 */         ItemStack hat = living.func_71124_b(4);
/* 334 */         if ((hat != null) && ((hat.func_77973_b() instanceof ItemWitchesClothes))) {
/* 335 */           renderBark(f5, this.field_78112_f, this.headRight1, charge >= 1);
/* 336 */           renderBark(f5, this.field_78112_f, this.shoulderRight, charge >= 1);
/* 337 */           charge--;
/*     */           
/* 339 */           renderBark(f5, this.field_78113_g, this.headLeft1, charge >= 1);
/* 340 */           renderBark(f5, this.field_78113_g, this.shoulderLeft, charge >= 1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderBark(float f5, ModelRenderer bodyPart, ModelRenderer barkPiece, boolean visible)
/*     */   {
/* 348 */     renderBark(f5, bodyPart, barkPiece, visible, false);
/*     */   }
/*     */   
/*     */   private void renderBark(float f5, ModelRenderer bodyPart, ModelRenderer barkPiece, boolean visible, boolean leg) {
/* 352 */     if (visible) {
/* 353 */       GL11.glTranslatef(bodyPart.field_82906_o, bodyPart.field_82908_p, bodyPart.field_82907_q);
/* 354 */       if ((bodyPart.field_78795_f == 0.0F) && (bodyPart.field_78796_g == 0.0F) && (bodyPart.field_78808_h == 0.0F) && (!leg)) {
/* 355 */         if ((bodyPart.field_78800_c == 0.0F) && (bodyPart.field_78797_d == 0.0F) && (bodyPart.field_78798_e == 0.0F)) {
/* 356 */           barkPiece.func_78785_a(f5);
/*     */         } else {
/* 358 */           GL11.glTranslatef(bodyPart.field_78800_c * f5, bodyPart.field_78797_d * f5, bodyPart.field_78798_e * f5);
/* 359 */           barkPiece.func_78785_a(f5);
/* 360 */           GL11.glTranslatef(-bodyPart.field_78800_c * f5, -bodyPart.field_78797_d * f5, -bodyPart.field_78798_e * f5);
/*     */         }
/*     */       } else {
/* 363 */         GL11.glPushMatrix();
/* 364 */         GL11.glTranslatef(bodyPart.field_78800_c * f5, bodyPart.field_78797_d * f5, bodyPart.field_78798_e * f5);
/*     */         
/* 366 */         if (bodyPart.field_78808_h != 0.0F) {
/* 367 */           GL11.glRotatef(bodyPart.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */         }
/*     */         
/* 370 */         if (bodyPart.field_78796_g != 0.0F) {
/* 371 */           GL11.glRotatef(bodyPart.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 374 */         if (bodyPart.field_78795_f != 0.0F) {
/* 375 */           GL11.glRotatef(bodyPart.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */         }
/* 377 */         GL11.glTranslatef(-bodyPart.field_78800_c * f5, -bodyPart.field_78797_d * f5, -bodyPart.field_78798_e * f5);
/* 378 */         if ((this.field_78117_n) && (leg)) {
/* 379 */           GL11.glTranslatef(0.0F, -3.0F * f5, 4.0F * f5);
/* 380 */           barkPiece.func_78785_a(f5);
/*     */         } else {
/* 382 */           barkPiece.func_78785_a(f5);
/*     */         }
/*     */         
/* 385 */         GL11.glPopMatrix();
/*     */       }
/* 387 */       GL11.glTranslatef(-bodyPart.field_82906_o, -bodyPart.field_82908_p, -bodyPart.field_82907_q);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 392 */     model.field_78795_f = x;
/* 393 */     model.field_78796_g = y;
/* 394 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWitchesClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */