/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySpellEffect extends Entity
/*     */ {
/*  29 */   private int xTile = -1;
/*  30 */   private int yTile = -1;
/*  31 */   private int zTile = -1;
/*     */   private Block inTile;
/*     */   private boolean inGround;
/*     */   public EntityLivingBase shootingEntity;
/*     */   private int ticksAlive;
/*     */   private int ticksInAir;
/*  37 */   private int lifetime = -1;
/*     */   private int effectLevel;
/*     */   public double accelerationX;
/*     */   public double accelerationY;
/*     */   public double accelerationZ;
/*     */   private int effectID;
/*     */   
/*  44 */   public EntitySpellEffect(World par1World) { super(par1World);
/*  45 */     func_70105_a(0.5F, 0.5F);
/*  46 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */   public EntitySpellEffect setLifeTime(int ticks) {
/*  50 */     this.lifetime = ticks;
/*  51 */     return this;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  56 */     this.field_70180_af.func_75682_a(6, Integer.valueOf(0));
/*  57 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void setShooter(EntityLivingBase entity) {
/*  61 */     if (!this.field_70170_p.field_72995_K) {
/*  62 */       this.field_70180_af.func_75692_b(15, Integer.valueOf(entity.func_145782_y()));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getShooterID() {
/*  67 */     int id = this.field_70180_af.func_75679_c(15);
/*  68 */     return id;
/*     */   }
/*     */   
/*     */   public boolean isShooter(Entity entity) {
/*  72 */     int idOther = entity.func_145782_y();
/*  73 */     int us = getShooterID();
/*  74 */     return idOther == us;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setEffectID(int effectID)
/*     */   {
/*  80 */     this.effectID = effectID;
/*  81 */     func_70096_w().func_75692_b(6, Integer.valueOf(effectID));
/*     */   }
/*     */   
/*     */   public int getEffectID() {
/*  85 */     return func_70096_w().func_75679_c(6);
/*     */   }
/*     */   
/*     */   public int getEffectLevel() {
/*  89 */     return this.effectLevel;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/*  95 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  96 */     d1 *= 64.0D;
/*  97 */     return par1 < d1 * d1;
/*     */   }
/*     */   
/*     */   public EntitySpellEffect(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, SymbolEffect effect, int effectLevel)
/*     */   {
/* 102 */     super(par1World);
/* 103 */     this.effectLevel = effectLevel;
/* 104 */     func_70105_a(1.0F, 1.0F);
/* 105 */     func_70012_b(par2, par4, par6, this.field_70177_z, this.field_70125_A);
/* 106 */     func_70107_b(par2, par4, par6);
/* 107 */     double d6 = MathHelper.func_76133_a(par8 * par8 + par10 * par10 + par12 * par12);
/* 108 */     this.accelerationX = (par8 / d6 * 0.1D);
/* 109 */     this.accelerationY = (par10 / d6 * 0.1D);
/* 110 */     this.accelerationZ = (par12 / d6 * 0.1D);
/* 111 */     setEffectID(effect.getEffectID());
/*     */   }
/*     */   
/*     */   public EntitySpellEffect(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7, SymbolEffect effect, int effectLevel)
/*     */   {
/* 116 */     super(par1World);
/* 117 */     this.shootingEntity = par2EntityLivingBase;
/* 118 */     this.effectLevel = effectLevel;
/* 119 */     func_70105_a(1.0F, 1.0F);
/* 120 */     func_70012_b(par2EntityLivingBase.field_70165_t, par2EntityLivingBase.field_70163_u, par2EntityLivingBase.field_70161_v, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
/*     */     
/* 122 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 123 */     this.field_70129_M = 0.0F;
/* 124 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 125 */     par3 += this.field_70146_Z.nextGaussian() * 0.4D;
/* 126 */     par5 += this.field_70146_Z.nextGaussian() * 0.4D;
/* 127 */     par7 += this.field_70146_Z.nextGaussian() * 0.4D;
/* 128 */     double d3 = MathHelper.func_76133_a(par3 * par3 + par5 * par5 + par7 * par7);
/* 129 */     this.accelerationX = (par3 / d3 * 0.1D);
/* 130 */     this.accelerationY = (par5 / d3 * 0.1D);
/* 131 */     this.accelerationZ = (par7 / d3 * 0.1D);
/* 132 */     setEffectID(effect.getEffectID());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 140 */     if ((!this.field_70170_p.field_72995_K) && (((this.shootingEntity != null) && (this.shootingEntity.field_70128_L)) || (!this.field_70170_p.func_72899_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v))))
/*     */     {
/*     */ 
/* 143 */       func_70106_y();
/*     */     } else {
/* 145 */       super.func_70071_h_();
/*     */       
/* 147 */       if (this.inGround) {
/* 148 */         Block i = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/*     */         
/* 150 */         if (i == this.inTile) {
/* 151 */           this.ticksAlive += 1;
/*     */           
/* 153 */           if (this.ticksAlive == 600) {
/* 154 */             func_70106_y();
/*     */           }
/*     */           
/* 157 */           return;
/*     */         }
/*     */         
/* 160 */         this.inGround = false;
/* 161 */         this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 162 */         this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 163 */         this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 164 */         this.ticksAlive = 0;
/* 165 */         this.ticksInAir = 0;
/*     */       } else {
/* 167 */         this.ticksInAir += 1;
/*     */         
/* 169 */         if (this.ticksInAir == 200) {
/* 170 */           func_70106_y();
/*     */         }
/*     */       }
/*     */       
/* 174 */       Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 175 */       Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 177 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
/* 178 */       vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 179 */       vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/*     */ 
/* 182 */       if (movingobjectposition != null) {
/* 183 */         vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       }
/*     */       
/*     */ 
/* 187 */       Entity entity = null;
/* 188 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */       
/* 190 */       double d0 = 0.0D;
/*     */       
/* 192 */       boolean remote = this.field_70170_p.field_72995_K;
/* 193 */       for (int j = 0; j < list.size(); j++) {
/* 194 */         Entity entity1 = (Entity)list.get(j);
/*     */         
/* 196 */         if ((entity1.func_70067_L()) && ((!isShooter(entity1)) || (this.ticksInAir >= 25))) {
/* 197 */           float f = 0.3F;
/* 198 */           AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/* 199 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
/*     */           
/* 201 */           if (movingobjectposition1 != null) {
/* 202 */             double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 204 */             if ((d1 < d0) || (d0 == 0.0D)) {
/* 205 */               entity = entity1;
/* 206 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 212 */       if (entity != null) {
/* 213 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */       
/* 216 */       if ((movingobjectposition != null) || ((this.lifetime != -1) && (Math.max(--this.lifetime, 0) == 0))) {
/* 217 */         onImpact(movingobjectposition);
/*     */       }
/*     */       
/* 220 */       this.field_70165_t += this.field_70159_w;
/* 221 */       this.field_70163_u += this.field_70181_x;
/* 222 */       this.field_70161_v += this.field_70179_y;
/* 223 */       float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 224 */       this.field_70177_z = ((float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) + 90.0F);
/*     */       
/* 226 */       this.field_70125_A = ((float)(Math.atan2(f1, this.field_70181_x) * 180.0D / 3.141592653589793D) - 90.0F);
/* 227 */       while (this.field_70125_A - this.field_70127_C < -180.0F) { this.field_70127_C -= 360.0F;
/*     */       }
/*     */       
/*     */ 
/* 231 */       while (this.field_70125_A - this.field_70127_C >= 180.0F) {
/* 232 */         this.field_70127_C += 360.0F;
/*     */       }
/*     */       
/* 235 */       while (this.field_70177_z - this.field_70126_B < -180.0F) {
/* 236 */         this.field_70126_B -= 360.0F;
/*     */       }
/*     */       
/* 239 */       while (this.field_70177_z - this.field_70126_B >= 180.0F) {
/* 240 */         this.field_70126_B += 360.0F;
/*     */       }
/*     */       
/* 243 */       this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 244 */       this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 245 */       float f2 = getMotionFactor();
/*     */       
/* 247 */       if (func_70090_H()) {
/* 248 */         for (int k = 0; k < 4; k++) {
/* 249 */           float f3 = 0.25F;
/* 250 */           this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f3, this.field_70163_u - this.field_70181_x * f3, this.field_70161_v - this.field_70179_y * f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */         }
/*     */         
/*     */ 
/* 254 */         f2 = 0.8F;
/*     */       }
/*     */       
/* 257 */       SymbolEffect effect = EffectRegistry.instance().getEffect(getEffectID());
/* 258 */       if (effect == null) {
/* 259 */         func_70106_y();
/*     */       }
/*     */       else {
/* 262 */         if ((effect.fallsToEarth()) && (getEffectLevel() == 1)) {
/* 263 */           this.accelerationX *= 0.8D;
/* 264 */           this.accelerationY *= 0.8D;
/* 265 */           this.accelerationZ *= 0.8D;
/* 266 */           this.field_70159_w += this.accelerationX;
/* 267 */           this.field_70181_x += this.accelerationY;
/* 268 */           this.field_70179_y += this.accelerationZ;
/* 269 */           this.field_70181_x -= 0.05D;
/*     */         } else {
/* 271 */           this.field_70159_w += this.accelerationX;
/* 272 */           this.field_70181_x += this.accelerationY;
/* 273 */           this.field_70179_y += this.accelerationZ;
/* 274 */           this.field_70159_w *= f2;
/* 275 */           this.field_70181_x *= f2;
/* 276 */           this.field_70179_y *= f2;
/*     */         }
/*     */         
/* 279 */         this.field_70170_p.func_72869_a(effect.isCurse() ? ParticleEffect.MOB_SPELL.toString() : ParticleEffect.SLIME.toString(), this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, 0.0D, 0.0D, 0.0D);
/*     */         
/* 281 */         if (effect.isCurse()) {
/* 282 */           this.field_70170_p.func_72869_a(effect.isCurse() ? ParticleEffect.FLAME.toString() : ParticleEffect.SLIME.toString(), this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */         
/*     */ 
/* 286 */         func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float getMotionFactor()
/*     */   {
/* 296 */     return 0.95F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void onImpact(MovingObjectPosition mop)
/*     */   {
/* 303 */     if (!this.field_70170_p.field_72995_K) {
/* 304 */       SymbolEffect effect = EffectRegistry.instance().getEffect(getEffectID());
/* 305 */       if ((effect != null) && ((effect instanceof SymbolEffectProjectile))) {
/* 306 */         if (effect.isCurse()) {
/* 307 */           ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_HIT, this, 1.0D, 1.0D, 16);
/*     */         } else {
/* 309 */           ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_SMALL, this, 1.0D, 1.0D, 16);
/*     */         }
/* 311 */         ((SymbolEffectProjectile)effect).onCollision(this.field_70170_p, this.shootingEntity, mop, this);
/*     */       }
/*     */     }
/* 314 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 319 */     nbtRoot.func_74777_a("xTile", (short)this.xTile);
/* 320 */     nbtRoot.func_74777_a("yTile", (short)this.yTile);
/* 321 */     nbtRoot.func_74777_a("zTile", (short)this.zTile);
/* 322 */     nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
/* 323 */     nbtRoot.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/* 324 */     nbtRoot.func_74782_a("direction", func_70087_a(new double[] { this.field_70159_w, this.field_70181_x, this.field_70179_y }));
/* 325 */     nbtRoot.func_74768_a("EffectID", this.effectID);
/* 326 */     nbtRoot.func_74768_a("lifetime", this.lifetime);
/* 327 */     nbtRoot.func_74768_a("effectLevel", this.effectLevel);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 332 */     this.xTile = nbtRoot.func_74765_d("xTile");
/* 333 */     this.yTile = nbtRoot.func_74765_d("yTile");
/* 334 */     this.zTile = nbtRoot.func_74765_d("zTile");
/* 335 */     this.inTile = Block.func_149729_e(nbtRoot.func_74771_c("inTile") & 0xFF);
/* 336 */     this.inGround = (nbtRoot.func_74771_c("inGround") == 1);
/* 337 */     if (nbtRoot.func_74764_b("lifetime")) {
/* 338 */       this.lifetime = nbtRoot.func_74762_e("lifetime");
/*     */     } else {
/* 340 */       this.lifetime = -1;
/*     */     }
/*     */     
/* 343 */     if ((nbtRoot.func_74764_b("direction")) && (nbtRoot.func_74764_b("EffectID"))) {
/* 344 */       this.effectID = nbtRoot.func_74762_e("EffectID");
/* 345 */       setEffectID(this.effectID);
/*     */       
/* 347 */       NBTTagList nbttaglist = nbtRoot.func_150295_c("direction", 6);
/* 348 */       this.field_70159_w = nbttaglist.func_150309_d(0);
/* 349 */       this.field_70181_x = nbttaglist.func_150309_d(1);
/* 350 */       this.field_70179_y = nbttaglist.func_150309_d(2);
/*     */     } else {
/* 352 */       func_70106_y();
/*     */     }
/* 354 */     this.effectLevel = Math.max(nbtRoot.func_74762_e("effectLevel"), 1);
/*     */   }
/*     */   
/*     */   public boolean func_70067_L()
/*     */   {
/* 359 */     return true;
/*     */   }
/*     */   
/*     */   public float func_70111_Y()
/*     */   {
/* 364 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 369 */     if (func_85032_ar()) {
/* 370 */       return false;
/*     */     }
/* 372 */     func_70018_K();
/*     */     
/* 374 */     Entity entity = par1DamageSource.func_76346_g();
/* 375 */     boolean canDeflect = (entity != null) && (getEffectID() != 5) && ((entity instanceof EntityPlayer)) && (((EntityPlayer)entity).func_70694_bm() != null) && (((EntityPlayer)entity).func_70694_bm().func_77973_b() == com.emoniph.witchery.Witchery.Items.MYSTIC_BRANCH);
/*     */     
/* 377 */     if (canDeflect) {
/* 378 */       Vec3 vec3 = par1DamageSource.func_76346_g().func_70040_Z();
/*     */       
/* 380 */       if (vec3 != null) {
/* 381 */         this.field_70159_w = vec3.field_72450_a;
/* 382 */         this.field_70181_x = vec3.field_72448_b;
/* 383 */         this.field_70179_y = vec3.field_72449_c;
/* 384 */         this.accelerationX = (this.field_70159_w * 0.1D);
/* 385 */         this.accelerationY = (this.field_70181_x * 0.1D);
/* 386 */         this.accelerationZ = (this.field_70179_y * 0.1D);
/*     */       }
/*     */       
/* 389 */       if ((par1DamageSource.func_76346_g() instanceof EntityLivingBase)) {
/* 390 */         this.shootingEntity = ((EntityLivingBase)par1DamageSource.func_76346_g());
/*     */       }
/*     */       
/* 393 */       return true;
/*     */     }
/* 395 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 405 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 410 */     return 1.0F;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/* 416 */     return 15728880;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntitySpellEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */