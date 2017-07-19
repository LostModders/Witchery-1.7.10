/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Coord;
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
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class EntitySplatter extends Entity
/*     */ {
/*  29 */   private int field_145788_c = -1;
/*  30 */   private int field_145786_d = -1;
/*  31 */   private int field_145787_e = -1;
/*     */   private Block field_145785_f;
/*     */   protected boolean inGround;
/*     */   public int throwableShake;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   private int effectID;
/*     */   private int color;
/*     */   private int level;
/*     */   
/*     */   public EntitySplatter(World world)
/*     */   {
/*  43 */     super(world);
/*  44 */     func_70105_a(0.25F, 0.25F);
/*     */   }
/*     */   
/*     */   public EntitySplatter(World world, double x, double y, double z, int effectID, int color, int level) {
/*  48 */     super(world);
/*  49 */     this.ticksInGround = 0;
/*  50 */     func_70105_a(0.25F, 0.25F);
/*  51 */     func_70107_b(x, y, z);
/*  52 */     this.field_70129_M = 0.0F;
/*  53 */     this.effectID = effectID;
/*  54 */     this.level = level;
/*  55 */     setColor(color);
/*  56 */     if (effectID == 1) {
/*  57 */       func_70015_d(1000);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  63 */     this.field_70180_af.func_75682_a(6, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   protected void setColor(int color) {
/*  67 */     func_70096_w().func_75692_b(6, Integer.valueOf(color));
/*     */   }
/*     */   
/*     */   public int getColor() {
/*  71 */     return func_70096_w().func_75679_c(6);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_70112_a(double distSq)
/*     */   {
/*  77 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  78 */     d1 *= 64.0D;
/*  79 */     return distSq < d1 * d1;
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
/*  92 */     float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
/*     */     
/*  94 */     p_70186_1_ /= f2;
/*  95 */     p_70186_3_ /= f2;
/*  96 */     p_70186_5_ /= f2;
/*  97 */     p_70186_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/*  98 */     p_70186_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/*  99 */     p_70186_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 100 */     p_70186_1_ *= p_70186_7_;
/* 101 */     p_70186_3_ *= p_70186_7_;
/* 102 */     p_70186_5_ *= p_70186_7_;
/* 103 */     this.field_70159_w = p_70186_1_;
/* 104 */     this.field_70181_x = p_70186_3_;
/* 105 */     this.field_70179_y = p_70186_5_;
/* 106 */     float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
/* 107 */     this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / 3.141592653589793D));
/* 108 */     this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0D / 3.141592653589793D));
/* 109 */     this.ticksInGround = 0;
/*     */   }
/*     */   
/*     */   public void func_70016_h(double motionX, double motionY, double motionZ)
/*     */   {
/* 114 */     this.field_70159_w = motionX;
/* 115 */     this.field_70181_x = motionY;
/* 116 */     this.field_70179_y = motionZ;
/*     */     
/* 118 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F)) {
/* 119 */       float f = MathHelper.func_76133_a(motionX * motionX + motionZ * motionZ);
/* 120 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(motionX, motionZ) * 180.0D / 3.141592653589793D));
/* 121 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(motionY, f) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 127 */     this.field_70142_S = this.field_70165_t;
/* 128 */     this.field_70137_T = this.field_70163_u;
/* 129 */     this.field_70136_U = this.field_70161_v;
/* 130 */     super.func_70071_h_();
/*     */     
/* 132 */     if (this.throwableShake > 0) {
/* 133 */       this.throwableShake -= 1;
/*     */     }
/*     */     
/* 136 */     if (this.inGround) {
/* 137 */       if (this.field_70170_p.func_147439_a(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
/* 138 */         this.ticksInGround += 1;
/*     */         
/* 140 */         if (this.ticksInGround == 1200) {
/* 141 */           func_70106_y();
/*     */         }
/*     */         
/* 144 */         return;
/*     */       }
/*     */       
/* 147 */       this.inGround = false;
/* 148 */       this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 149 */       this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 150 */       this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 151 */       this.ticksInGround = 0;
/* 152 */       this.ticksInAir = 0;
/*     */     } else {
/* 154 */       this.ticksInAir += 1;
/*     */     }
/*     */     
/* 157 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 158 */     Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/* 160 */     MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
/* 161 */     vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 162 */     vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/*     */ 
/* 165 */     if (movingobjectposition != null) {
/* 166 */       vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */     }
/*     */     
/*     */ 
/* 170 */     if (!this.field_70170_p.field_72995_K) {
/* 171 */       Entity entity = null;
/* 172 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */       
/* 174 */       double d0 = 0.0D;
/*     */       
/* 176 */       for (int j = 0; j < list.size(); j++) {
/* 177 */         Entity entity1 = (Entity)list.get(j);
/*     */         
/* 179 */         if ((entity1.func_70067_L()) && (this.ticksInAir >= 5) && (!(entity instanceof EntitySplatter))) {
/* 180 */           float f = 0.3F;
/* 181 */           AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/* 182 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
/*     */           
/* 184 */           if (movingobjectposition1 != null) {
/* 185 */             double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 187 */             if ((d1 < d0) || (d0 == 0.0D)) {
/* 188 */               entity = entity1;
/* 189 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 195 */       if (entity != null) {
/* 196 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */     }
/*     */     
/* 200 */     if (movingobjectposition != null) {
/* 201 */       if ((movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) == Blocks.field_150427_aO))
/*     */       {
/*     */ 
/* 204 */         func_70063_aa();
/*     */       } else {
/* 206 */         onImpact(movingobjectposition);
/*     */       }
/*     */     }
/*     */     
/* 210 */     this.field_70165_t += this.field_70159_w;
/* 211 */     this.field_70163_u += this.field_70181_x;
/* 212 */     this.field_70161_v += this.field_70179_y;
/* 213 */     float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 214 */     this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */     
/* 216 */     this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f1) * 180.0D / 3.141592653589793D));
/* 217 */     while (this.field_70125_A - this.field_70127_C < -180.0F) { this.field_70127_C -= 360.0F;
/*     */     }
/*     */     
/*     */ 
/* 221 */     while (this.field_70125_A - this.field_70127_C >= 180.0F) {
/* 222 */       this.field_70127_C += 360.0F;
/*     */     }
/*     */     
/* 225 */     while (this.field_70177_z - this.field_70126_B < -180.0F) {
/* 226 */       this.field_70126_B -= 360.0F;
/*     */     }
/*     */     
/* 229 */     while (this.field_70177_z - this.field_70126_B >= 180.0F) {
/* 230 */       this.field_70126_B += 360.0F;
/*     */     }
/*     */     
/* 233 */     this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 234 */     this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 235 */     float f2 = 0.99F;
/* 236 */     float f3 = getGravityVelocity();
/*     */     
/* 238 */     if (func_70090_H()) {
/* 239 */       for (int i = 0; i < 4; i++) {
/* 240 */         float f4 = 0.25F;
/* 241 */         this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 246 */       f2 = 0.8F;
/*     */     }
/*     */     
/* 249 */     this.field_70159_w *= f2;
/* 250 */     this.field_70181_x *= f2;
/* 251 */     this.field_70179_y *= f2;
/* 252 */     this.field_70181_x -= f3;
/* 253 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/* 257 */     return 0.03F;
/*     */   }
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop) {
/* 261 */     if ((!this.field_70170_p.field_72995_K) && 
/* 262 */       (mop != null)) {
/* 263 */       Coord coord = new Coord(mop, new EntityPosition(this), true);
/* 264 */       switch (mop.field_72313_a) {
/*     */       case BLOCK: 
/* 266 */         ForgeDirection side = ForgeDirection.getOrientation(mop.field_72310_e);
/* 267 */         int x = mop.field_72311_b + side.offsetX;
/* 268 */         int y = mop.field_72312_c + side.offsetY;
/* 269 */         int z = mop.field_72309_d + side.offsetZ;
/* 270 */         if (BlockUtil.isReplaceableBlock(this.field_70170_p, x, y, z, FakePlayerFactory.getMinecraft((WorldServer)this.field_70170_p)))
/*     */         {
/* 272 */           this.field_70170_p.func_147449_b(x, y, z, Blocks.field_150480_ab);
/*     */         }
/* 274 */         if (this.level - 1 > 0) {
/* 275 */           splatter(this.field_70170_p, coord, this.level - 1);
/*     */         }
/* 277 */         func_70106_y();
/* 278 */         break;
/*     */       
/*     */       case ENTITY: 
/* 281 */         if ((mop.field_72308_g instanceof EntityLivingBase)) {
/* 282 */           mop.field_72308_g.func_70015_d(5);
/*     */         }
/*     */         break;
/*     */       case MISS: 
/* 286 */         func_70106_y();
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */   public static void splatter(World world, Coord coord, int level)
/*     */   {
/* 294 */     if (!world.field_72995_K) {
/* 295 */       for (int i = 0; i < 3 + level; i++) {
/* 296 */         EntitySplatter splatter = new EntitySplatter(world, 0.5D + coord.x, 0.5D + coord.y, 0.5D + coord.z, 1, 10027008, level);
/*     */         
/* 298 */         double maxSpeed = 0.1D;
/* 299 */         double doubleSpeed = 0.2D;
/* 300 */         splatter.func_70016_h(world.field_73012_v.nextDouble() * 0.2D - 0.1D, world.field_73012_v.nextDouble() * 0.05D + 0.3D, world.field_73012_v.nextDouble() * 0.2D - 0.1D);
/*     */         
/*     */ 
/* 303 */         EntityUtil.spawnEntityInWorld(world, splatter);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 310 */     nbtRoot.func_74777_a("xTile", (short)this.field_145788_c);
/* 311 */     nbtRoot.func_74777_a("yTile", (short)this.field_145786_d);
/* 312 */     nbtRoot.func_74777_a("zTile", (short)this.field_145787_e);
/* 313 */     nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b(this.field_145785_f));
/* 314 */     nbtRoot.func_74774_a("shake", (byte)this.throwableShake);
/* 315 */     nbtRoot.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/* 316 */     nbtRoot.func_74768_a("Color", this.color);
/* 317 */     nbtRoot.func_74768_a("Level", this.level);
/* 318 */     nbtRoot.func_74768_a("Effect", this.effectID);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 323 */     this.field_145788_c = nbtRoot.func_74765_d("xTile");
/* 324 */     this.field_145786_d = nbtRoot.func_74765_d("yTile");
/* 325 */     this.field_145787_e = nbtRoot.func_74765_d("zTile");
/* 326 */     this.field_145785_f = Block.func_149729_e(nbtRoot.func_74771_c("inTile") & 0xFF);
/* 327 */     this.throwableShake = (nbtRoot.func_74771_c("shake") & 0xFF);
/* 328 */     this.inGround = (nbtRoot.func_74771_c("inGround") == 1);
/* 329 */     this.effectID = nbtRoot.func_74762_e("Effect");
/* 330 */     this.level = nbtRoot.func_74762_e("Level");
/* 331 */     setColor(nbtRoot.func_74762_e("Color"));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 337 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/EntitySplatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */