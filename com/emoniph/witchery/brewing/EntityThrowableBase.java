/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityThrowableBase extends Entity
/*     */ {
/*  20 */   private int field_145788_c = -1;
/*  21 */   private int field_145786_d = -1;
/*  22 */   private int field_145787_e = -1;
/*     */   private Block field_145785_f;
/*     */   protected boolean inGround;
/*     */   public int throwableShake;
/*     */   private EntityLivingBase thrower;
/*     */   private String throwerName;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   
/*     */   public EntityThrowableBase(World world) {
/*  32 */     super(world);
/*  33 */     func_70105_a(0.25F, 0.25F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a() {}
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_70112_a(double p_70112_1_)
/*     */   {
/*  43 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  44 */     d1 *= 64.0D;
/*  45 */     return p_70112_1_ < d1 * d1;
/*     */   }
/*     */   
/*     */   public EntityThrowableBase(World world, EntityLivingBase thrower, float pitchShift) {
/*  49 */     super(world);
/*  50 */     this.thrower = thrower;
/*  51 */     func_70105_a(0.25F, 0.25F);
/*  52 */     func_70012_b(thrower.field_70165_t, thrower.field_70163_u + thrower.func_70047_e(), thrower.field_70161_v, thrower.field_70177_z, thrower.field_70125_A);
/*     */     
/*  54 */     this.field_70165_t -= MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/*  55 */     this.field_70163_u -= 0.10000000149011612D;
/*  56 */     this.field_70161_v -= MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/*  57 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  58 */     this.field_70129_M = 0.0F;
/*  59 */     float f = 0.6F;
/*  60 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/*     */     
/*  62 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/*     */     
/*  64 */     this.field_70181_x = (-MathHelper.func_76126_a((this.field_70125_A + pitchShift) / 180.0F * 3.1415927F) * f);
/*  65 */     setThrowableHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), 1.0F);
/*     */   }
/*     */   
/*     */   public EntityThrowableBase(World world, double x, double y, double z, float pitchShift) {
/*  69 */     super(world);
/*  70 */     this.ticksInGround = 0;
/*  71 */     func_70105_a(0.25F, 0.25F);
/*  72 */     func_70107_b(x, y, z);
/*  73 */     this.field_70129_M = 0.0F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d() {
/*  77 */     return 1.5F;
/*     */   }
/*     */   
/*     */   protected float func_70183_g() {
/*  81 */     return 0.0F;
/*     */   }
/*     */   
/*     */   protected int getMaxAirTicks() {
/*  85 */     return 200;
/*     */   }
/*     */   
/*     */   protected int getMaxGroundTicks() {
/*  89 */     return 1200;
/*     */   }
/*     */   
/*     */   public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
/*     */   {
/*  94 */     float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
/*     */     
/*  96 */     p_70186_1_ /= f2;
/*  97 */     p_70186_3_ /= f2;
/*  98 */     p_70186_5_ /= f2;
/*  99 */     p_70186_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 100 */     p_70186_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 101 */     p_70186_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 102 */     p_70186_1_ *= p_70186_7_;
/* 103 */     p_70186_3_ *= p_70186_7_;
/* 104 */     p_70186_5_ *= p_70186_7_;
/* 105 */     this.field_70159_w = p_70186_1_;
/* 106 */     this.field_70181_x = p_70186_3_;
/* 107 */     this.field_70179_y = p_70186_5_;
/* 108 */     float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
/* 109 */     this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / 3.141592653589793D));
/* 110 */     this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0D / 3.141592653589793D));
/* 111 */     this.ticksInGround = 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_)
/*     */   {
/* 117 */     this.field_70159_w = p_70016_1_;
/* 118 */     this.field_70181_x = p_70016_3_;
/* 119 */     this.field_70179_y = p_70016_5_;
/*     */     
/* 121 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F)) {
/* 122 */       float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
/* 123 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / 3.141592653589793D));
/* 124 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(p_70016_3_, f) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 130 */     this.field_70142_S = this.field_70165_t;
/* 131 */     this.field_70137_T = this.field_70163_u;
/* 132 */     this.field_70136_U = this.field_70161_v;
/* 133 */     super.func_70071_h_();
/*     */     
/* 135 */     if (this.throwableShake > 0) {
/* 136 */       this.throwableShake -= 1;
/*     */     }
/*     */     
/* 139 */     if (this.inGround) {
/* 140 */       if (this.field_70170_p.func_147439_a(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
/* 141 */         this.ticksInGround += 1;
/*     */         
/* 143 */         if (this.ticksInGround >= getMaxGroundTicks()) {
/* 144 */           if (!this.field_70170_p.field_72995_K) {
/* 145 */             onSetDead();
/*     */           } else {
/* 147 */             onClientSetDead();
/*     */           }
/*     */           
/* 150 */           func_70106_y();
/*     */         }
/*     */         
/* 153 */         return;
/*     */       }
/*     */       
/* 156 */       this.inGround = false;
/* 157 */       this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 158 */       this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 159 */       this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 160 */       this.ticksInGround = 0;
/* 161 */       this.ticksInAir = 0;
/*     */     } else {
/* 163 */       this.ticksInAir += 1;
/*     */       
/* 165 */       if (this.ticksInAir >= getMaxAirTicks()) {
/* 166 */         if (!this.field_70170_p.field_72995_K) {
/* 167 */           onSetDead();
/*     */         } else {
/* 169 */           onClientSetDead();
/*     */         }
/* 171 */         func_70106_y();
/*     */       }
/*     */     }
/*     */     
/* 175 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 176 */     Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/* 178 */     MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
/* 179 */     vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 180 */     vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/*     */ 
/* 183 */     if (movingobjectposition != null) {
/* 184 */       vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */     }
/*     */     
/*     */ 
/* 188 */     if (!this.field_70170_p.field_72995_K) {
/* 189 */       Entity entity = null;
/* 190 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */       
/* 192 */       double d0 = 0.0D;
/* 193 */       EntityLivingBase entitylivingbase = getThrower();
/*     */       
/* 195 */       for (int j = 0; j < list.size(); j++) {
/* 196 */         Entity entity1 = (Entity)list.get(j);
/*     */         
/* 198 */         if ((entity1.func_70067_L()) && ((entity1 instanceof EntityLivingBase)) && (
/* 199 */           (entity1 != entitylivingbase) || (this.ticksInAir >= 5)))
/*     */         {
/* 201 */           float f = 0.3F;
/* 202 */           AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/* 203 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
/*     */           
/* 205 */           if (movingobjectposition1 != null) {
/* 206 */             double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 208 */             if ((d1 < d0) || (d0 == 0.0D)) {
/* 209 */               entity = entity1;
/* 210 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 217 */       if (entity != null) {
/* 218 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */     }
/*     */     
/* 222 */     if ((movingobjectposition != null) && (
/* 223 */       (movingobjectposition.field_72313_a != net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) || (this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) != net.minecraft.init.Blocks.field_150427_aO)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 228 */       onImpact(movingobjectposition);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 233 */     this.field_70165_t += this.field_70159_w;
/* 234 */     this.field_70163_u += this.field_70181_x;
/* 235 */     this.field_70161_v += this.field_70179_y;
/* 236 */     float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 237 */     this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */     
/* 239 */     this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f1) * 180.0D / 3.141592653589793D));
/* 240 */     while (this.field_70125_A - this.field_70127_C < -180.0F) { this.field_70127_C -= 360.0F;
/*     */     }
/*     */     
/*     */ 
/* 244 */     while (this.field_70125_A - this.field_70127_C >= 180.0F) {
/* 245 */       this.field_70127_C += 360.0F;
/*     */     }
/*     */     
/* 248 */     while (this.field_70177_z - this.field_70126_B < -180.0F) {
/* 249 */       this.field_70126_B -= 360.0F;
/*     */     }
/*     */     
/* 252 */     while (this.field_70177_z - this.field_70126_B >= 180.0F) {
/* 253 */       this.field_70126_B += 360.0F;
/*     */     }
/*     */     
/* 256 */     this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 257 */     this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 258 */     float f2 = 0.99F;
/* 259 */     float f3 = getGravityVelocity();
/*     */     
/* 261 */     if (func_70090_H()) {
/* 262 */       for (int i = 0; i < 4; i++) {
/* 263 */         float f4 = 0.25F;
/* 264 */         this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 269 */       f2 = 0.8F;
/*     */     }
/*     */     
/* 272 */     this.field_70159_w *= f2;
/* 273 */     this.field_70181_x *= f2;
/* 274 */     this.field_70179_y *= f2;
/* 275 */     this.field_70181_x -= f3;
/* 276 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void onSetDead() {}
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected void onClientSetDead() {}
/*     */   
/*     */ 
/*     */   protected float getGravityVelocity()
/*     */   {
/* 289 */     return 0.03F;
/*     */   }
/*     */   
/*     */   protected abstract void onImpact(MovingObjectPosition paramMovingObjectPosition);
/*     */   
/*     */   public void func_70014_b(NBTTagCompound p_70014_1_)
/*     */   {
/* 296 */     p_70014_1_.func_74777_a("xTile", (short)this.field_145788_c);
/* 297 */     p_70014_1_.func_74777_a("yTile", (short)this.field_145786_d);
/* 298 */     p_70014_1_.func_74777_a("zTile", (short)this.field_145787_e);
/* 299 */     p_70014_1_.func_74774_a("inTile", (byte)Block.func_149682_b(this.field_145785_f));
/* 300 */     p_70014_1_.func_74774_a("shake", (byte)this.throwableShake);
/* 301 */     p_70014_1_.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/*     */     
/* 303 */     if (((this.throwerName == null) || (this.throwerName.length() == 0)) && (this.thrower != null) && ((this.thrower instanceof EntityPlayer)))
/*     */     {
/* 305 */       this.throwerName = this.thrower.func_70005_c_();
/*     */     }
/*     */     
/* 308 */     p_70014_1_.func_74778_a("ownerName", this.throwerName == null ? "" : this.throwerName);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound p_70037_1_)
/*     */   {
/* 313 */     this.field_145788_c = p_70037_1_.func_74765_d("xTile");
/* 314 */     this.field_145786_d = p_70037_1_.func_74765_d("yTile");
/* 315 */     this.field_145787_e = p_70037_1_.func_74765_d("zTile");
/* 316 */     this.field_145785_f = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 0xFF);
/* 317 */     this.throwableShake = (p_70037_1_.func_74771_c("shake") & 0xFF);
/* 318 */     this.inGround = (p_70037_1_.func_74771_c("inGround") == 1);
/* 319 */     this.throwerName = p_70037_1_.func_74779_i("ownerName");
/*     */     
/* 321 */     if ((this.throwerName != null) && (this.throwerName.length() == 0)) {
/* 322 */       this.throwerName = null;
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 329 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public EntityLivingBase getThrower() {
/* 333 */     if ((this.thrower == null) && (this.throwerName != null) && (this.throwerName.length() > 0)) {
/* 334 */       this.thrower = this.field_70170_p.func_72924_a(this.throwerName);
/*     */     }
/*     */     
/* 337 */     return this.thrower;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/EntityThrowableBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */