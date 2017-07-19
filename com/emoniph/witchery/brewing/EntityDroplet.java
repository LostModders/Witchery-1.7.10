/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class EntityDroplet
/*     */   extends Entity
/*     */ {
/*  27 */   private int field_145788_c = -1;
/*  28 */   private int field_145786_d = -1;
/*  29 */   private int field_145787_e = -1;
/*     */   private Block field_145785_f;
/*     */   protected boolean inGround;
/*     */   public int throwableShake;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   private NBTTagCompound nbtBrew;
/*     */   private int color;
/*     */   
/*     */   public EntityDroplet(World world)
/*     */   {
/*  40 */     super(world);
/*  41 */     func_70105_a(0.25F, 0.25F);
/*     */   }
/*     */   
/*     */   public EntityDroplet(World world, double x, double y, double z, NBTTagCompound nbtBrew) {
/*  45 */     super(world);
/*  46 */     this.ticksInGround = 0;
/*  47 */     func_70105_a(0.25F, 0.25F);
/*  48 */     func_70107_b(x, y, z);
/*  49 */     this.field_70129_M = 0.0F;
/*  50 */     this.nbtBrew = nbtBrew;
/*  51 */     setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtBrew));
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  56 */     this.field_70180_af.func_75682_a(6, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   protected void setColor(int color) {
/*  60 */     func_70096_w().func_75692_b(6, Integer.valueOf(color));
/*     */   }
/*     */   
/*     */   public int getColor() {
/*  64 */     return func_70096_w().func_75679_c(6);
/*     */   }
/*     */   
/*     */   public NBTTagCompound getBrew() {
/*  68 */     return this.nbtBrew;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_70112_a(double distSq)
/*     */   {
/*  74 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  75 */     d1 *= 64.0D;
/*  76 */     return distSq < d1 * d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
/*     */   {
/*  89 */     float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
/*     */     
/*  91 */     p_70186_1_ /= f2;
/*  92 */     p_70186_3_ /= f2;
/*  93 */     p_70186_5_ /= f2;
/*  94 */     p_70186_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/*  95 */     p_70186_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/*  96 */     p_70186_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/*  97 */     p_70186_1_ *= p_70186_7_;
/*  98 */     p_70186_3_ *= p_70186_7_;
/*  99 */     p_70186_5_ *= p_70186_7_;
/* 100 */     this.field_70159_w = p_70186_1_;
/* 101 */     this.field_70181_x = p_70186_3_;
/* 102 */     this.field_70179_y = p_70186_5_;
/* 103 */     float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
/* 104 */     this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / 3.141592653589793D));
/* 105 */     this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0D / 3.141592653589793D));
/* 106 */     this.ticksInGround = 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double motionX, double motionY, double motionZ)
/*     */   {
/* 112 */     this.field_70159_w = motionX;
/* 113 */     this.field_70181_x = motionY;
/* 114 */     this.field_70179_y = motionZ;
/*     */     
/* 116 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F)) {
/* 117 */       float f = MathHelper.func_76133_a(motionX * motionX + motionZ * motionZ);
/* 118 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(motionX, motionZ) * 180.0D / 3.141592653589793D));
/* 119 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(motionY, f) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 125 */     this.field_70142_S = this.field_70165_t;
/* 126 */     this.field_70137_T = this.field_70163_u;
/* 127 */     this.field_70136_U = this.field_70161_v;
/* 128 */     super.func_70071_h_();
/*     */     
/* 130 */     if (this.throwableShake > 0) {
/* 131 */       this.throwableShake -= 1;
/*     */     }
/*     */     
/* 134 */     if (this.inGround) {
/* 135 */       if (this.field_70170_p.func_147439_a(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
/* 136 */         this.ticksInGround += 1;
/*     */         
/* 138 */         if (this.ticksInGround == 1200) {
/* 139 */           func_70106_y();
/*     */         }
/*     */         
/* 142 */         return;
/*     */       }
/*     */       
/* 145 */       this.inGround = false;
/* 146 */       this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 147 */       this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 148 */       this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 149 */       this.ticksInGround = 0;
/* 150 */       this.ticksInAir = 0;
/*     */     } else {
/* 152 */       this.ticksInAir += 1;
/*     */     }
/*     */     
/* 155 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 156 */     Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/* 158 */     MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
/* 159 */     vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 160 */     vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/*     */ 
/* 163 */     if (movingobjectposition != null) {
/* 164 */       vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */     }
/*     */     
/*     */ 
/* 168 */     if (!this.field_70170_p.field_72995_K) {
/* 169 */       Entity entity = null;
/* 170 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */       
/* 172 */       double d0 = 0.0D;
/*     */       
/* 174 */       for (int j = 0; j < list.size(); j++) {
/* 175 */         Entity entity1 = (Entity)list.get(j);
/*     */         
/* 177 */         if ((entity1.func_70067_L()) && (this.ticksInAir >= 5)) {
/* 178 */           float f = 0.3F;
/* 179 */           AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/* 180 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
/*     */           
/* 182 */           if (movingobjectposition1 != null) {
/* 183 */             double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 185 */             if ((d1 < d0) || (d0 == 0.0D)) {
/* 186 */               entity = entity1;
/* 187 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 193 */       if (entity != null) {
/* 194 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */     }
/*     */     
/* 198 */     if (movingobjectposition != null) {
/* 199 */       if ((movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) == Blocks.field_150427_aO))
/*     */       {
/*     */ 
/* 202 */         func_70063_aa();
/*     */       } else {
/* 204 */         onImpact(movingobjectposition);
/*     */       }
/*     */     }
/*     */     
/* 208 */     this.field_70165_t += this.field_70159_w;
/* 209 */     this.field_70163_u += this.field_70181_x;
/* 210 */     this.field_70161_v += this.field_70179_y;
/* 211 */     float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 212 */     this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */     
/* 214 */     this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f1) * 180.0D / 3.141592653589793D));
/* 215 */     while (this.field_70125_A - this.field_70127_C < -180.0F) { this.field_70127_C -= 360.0F;
/*     */     }
/*     */     
/*     */ 
/* 219 */     while (this.field_70125_A - this.field_70127_C >= 180.0F) {
/* 220 */       this.field_70127_C += 360.0F;
/*     */     }
/*     */     
/* 223 */     while (this.field_70177_z - this.field_70126_B < -180.0F) {
/* 224 */       this.field_70126_B -= 360.0F;
/*     */     }
/*     */     
/* 227 */     while (this.field_70177_z - this.field_70126_B >= 180.0F) {
/* 228 */       this.field_70126_B += 360.0F;
/*     */     }
/*     */     
/* 231 */     this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 232 */     this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 233 */     float f2 = 0.99F;
/* 234 */     float f3 = getGravityVelocity();
/*     */     
/* 236 */     if (func_70090_H()) {
/* 237 */       for (int i = 0; i < 4; i++) {
/* 238 */         float f4 = 0.25F;
/* 239 */         this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 244 */       f2 = 0.8F;
/*     */     }
/*     */     
/* 247 */     this.field_70159_w *= f2;
/* 248 */     this.field_70181_x *= f2;
/* 249 */     this.field_70179_y *= f2;
/* 250 */     this.field_70181_x -= f3;
/* 251 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/* 255 */     return 0.03F;
/*     */   }
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/* 259 */     if (!this.field_70170_p.field_72995_K) {
/* 260 */       this.field_70170_p.func_72926_e(1016, MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), getColor());
/*     */       
/* 262 */       if (mop != null) {
/* 263 */         ModifiersEffect modifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(this), true, 1, EntityUtil.playerOrFake(this.field_70170_p, (EntityPlayer)null));
/*     */         
/* 265 */         switch (mop.field_72313_a)
/*     */         {
/*     */         case BLOCK: 
/* 268 */           WitcheryBrewRegistry.INSTANCE.applyToBlock(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ForgeDirection.getOrientation(mop.field_72310_e), 1, this.nbtBrew, modifiers);
/*     */           
/* 270 */           break;
/*     */         case ENTITY: 
/* 272 */           if ((mop.field_72308_g instanceof EntityLivingBase)) {
/* 273 */             WitcheryBrewRegistry.INSTANCE.applyToEntity(this.field_70170_p, (EntityLivingBase)mop.field_72308_g, this.nbtBrew, modifiers);
/*     */           }
/*     */           
/*     */ 
/*     */           break;
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     
/* 283 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 288 */     nbtRoot.func_74777_a("xTile", (short)this.field_145788_c);
/* 289 */     nbtRoot.func_74777_a("yTile", (short)this.field_145786_d);
/* 290 */     nbtRoot.func_74777_a("zTile", (short)this.field_145787_e);
/* 291 */     nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b(this.field_145785_f));
/* 292 */     nbtRoot.func_74774_a("shake", (byte)this.throwableShake);
/* 293 */     nbtRoot.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/*     */     
/* 295 */     if (this.nbtBrew != null) {
/* 296 */       nbtRoot.func_74782_a("Brew", this.nbtBrew.func_74737_b());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 302 */     this.field_145788_c = nbtRoot.func_74765_d("xTile");
/* 303 */     this.field_145786_d = nbtRoot.func_74765_d("yTile");
/* 304 */     this.field_145787_e = nbtRoot.func_74765_d("zTile");
/* 305 */     this.field_145785_f = Block.func_149729_e(nbtRoot.func_74771_c("inTile") & 0xFF);
/* 306 */     this.throwableShake = (nbtRoot.func_74771_c("shake") & 0xFF);
/* 307 */     this.inGround = (nbtRoot.func_74771_c("inGround") == 1);
/*     */     
/* 309 */     if (nbtRoot.func_150297_b("Brew", 10)) {
/* 310 */       this.nbtBrew = ((NBTTagCompound)nbtRoot.func_74775_l("Brew").func_74737_b());
/* 311 */       setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtRoot));
/*     */     }
/*     */     
/* 314 */     if (this.nbtBrew == null) {
/* 315 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 322 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/EntityDroplet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */