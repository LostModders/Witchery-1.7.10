/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.integration.ModHookManager;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.BoltDamageSource;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityBolt extends Entity
/*     */ {
/*  35 */   private int xTile = -1;
/*  36 */   private int yTile = -1;
/*  37 */   private int zTile = -1;
/*     */   
/*     */   private Block inTile;
/*     */   
/*     */   private int inData;
/*     */   
/*     */   private boolean inGround;
/*     */   
/*     */   public int canBePickedUp;
/*     */   
/*     */   public int arrowShake;
/*     */   
/*     */   public Entity shootingEntity;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*  52 */   private double damage = 2.0D;
/*     */   
/*     */   private int knockbackStrength;
/*     */   
/*     */   public EntityBolt(World par1World)
/*     */   {
/*  58 */     super(par1World);
/*  59 */     this.field_70155_l = 10.0D;
/*  60 */     func_70105_a(0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public EntityBolt(World par1World, double par2, double par4, double par6) {
/*  64 */     super(par1World);
/*  65 */     this.field_70155_l = 10.0D;
/*  66 */     func_70105_a(0.5F, 0.5F);
/*  67 */     func_70107_b(par2, par4, par6);
/*  68 */     this.field_70129_M = 0.0F;
/*     */   }
/*     */   
/*     */   public EntityBolt(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
/*  72 */     super(par1World);
/*  73 */     this.field_70155_l = 10.0D;
/*  74 */     this.shootingEntity = par2EntityLivingBase;
/*     */     
/*  76 */     if ((par2EntityLivingBase instanceof EntityPlayer)) {
/*  77 */       this.canBePickedUp = 1;
/*     */     }
/*     */     
/*  80 */     this.field_70163_u = (par2EntityLivingBase.field_70163_u + par2EntityLivingBase.func_70047_e() - 0.10000000149011612D);
/*  81 */     double d0 = par3EntityLivingBase.field_70165_t - par2EntityLivingBase.field_70165_t;
/*  82 */     double d1 = par3EntityLivingBase.field_70121_D.field_72338_b + par3EntityLivingBase.field_70131_O / 3.0F - this.field_70163_u;
/*  83 */     double d2 = par3EntityLivingBase.field_70161_v - par2EntityLivingBase.field_70161_v;
/*  84 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/*     */     
/*  86 */     if (d3 >= 1.0E-7D) {
/*  87 */       float f2 = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/*  88 */       float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/*  89 */       double d4 = d0 / d3;
/*  90 */       double d5 = d2 / d3;
/*  91 */       func_70012_b(par2EntityLivingBase.field_70165_t + d4, this.field_70163_u, par2EntityLivingBase.field_70161_v + d5, f2, f3);
/*  92 */       this.field_70129_M = 0.0F;
/*  93 */       float f4 = (float)d3 * 0.2F;
/*  94 */       setThrowableHeading(d0, d1 + f4, d2, par4, par5);
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityBolt(World par1World, EntityLivingBase par2EntityLivingBase, float par3, float arcStart) {
/*  99 */     super(par1World);
/* 100 */     this.field_70155_l = 10.0D;
/* 101 */     this.shootingEntity = par2EntityLivingBase;
/*     */     
/* 103 */     if ((par2EntityLivingBase instanceof EntityPlayer)) {
/* 104 */       this.canBePickedUp = 1;
/*     */     }
/*     */     
/* 107 */     func_70105_a(0.5F, 0.5F);
/* 108 */     func_70012_b(par2EntityLivingBase.field_70165_t, par2EntityLivingBase.field_70163_u + par2EntityLivingBase.func_70047_e(), par2EntityLivingBase.field_70161_v, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
/*     */     
/*     */ 
/* 111 */     this.field_70177_z += arcStart;
/*     */     
/* 113 */     this.field_70165_t -= MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 114 */     this.field_70163_u -= 0.30000000149011613D;
/* 115 */     this.field_70161_v -= MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 116 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 117 */     this.field_70129_M = 0.0F;
/* 118 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/* 119 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/* 120 */     this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
/* 121 */     setThrowableHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, par3 * 1.5F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 126 */     this.field_70180_af.func_75682_a(15, "");
/* 127 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/* 128 */     this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void setShooter(EntityLivingBase entity) {
/* 132 */     if ((!this.field_70170_p.field_72995_K) && ((entity instanceof EntityPlayer))) {
/* 133 */       this.field_70180_af.func_75692_b(15, ((EntityPlayer)entity).func_70005_c_());
/*     */     }
/*     */   }
/*     */   
/*     */   public String getShooter() {
/* 138 */     String username = this.field_70180_af.func_75681_e(15);
/* 139 */     if (username == null) {
/* 140 */       return "";
/*     */     }
/* 142 */     return username;
/*     */   }
/*     */   
/*     */   public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/* 147 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/* 148 */     par1 /= f2;
/* 149 */     par3 /= f2;
/* 150 */     par5 /= f2;
/* 151 */     par1 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/* 152 */     par3 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/* 153 */     par5 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/* 154 */     par1 *= par7;
/* 155 */     par3 *= par7;
/* 156 */     par5 *= par7;
/* 157 */     this.field_70159_w = par1;
/* 158 */     this.field_70181_x = par3;
/* 159 */     this.field_70179_y = par5;
/* 160 */     float f3 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
/* 161 */     this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
/* 162 */     this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0D / 3.141592653589793D));
/* 163 */     this.ticksInGround = 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9)
/*     */   {
/* 169 */     func_70107_b(par1, par3, par5);
/* 170 */     func_70101_b(par7, par8);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double par1, double par3, double par5)
/*     */   {
/* 176 */     this.field_70159_w = par1;
/* 177 */     this.field_70181_x = par3;
/* 178 */     this.field_70179_y = par5;
/*     */     
/* 180 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F)) {
/* 181 */       float f = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
/* 182 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
/* 183 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(par3, f) * 180.0D / 3.141592653589793D));
/* 184 */       this.field_70127_C = this.field_70125_A;
/* 185 */       this.field_70126_B = this.field_70177_z;
/* 186 */       func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
/* 187 */       this.ticksInGround = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 193 */     func_70030_z();
/*     */     
/* 195 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F)) {
/* 196 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 197 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/* 198 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */     
/* 201 */     Block i = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/*     */     
/* 203 */     if (i != null) {
/* 204 */       i.func_149719_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
/* 205 */       AxisAlignedBB axisalignedbb = i.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
/*     */       
/* 207 */       if ((axisalignedbb != null) && (axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)))) {
/* 208 */         this.inGround = true;
/*     */       }
/*     */     }
/*     */     
/* 212 */     if (this.arrowShake > 0) {
/* 213 */       this.arrowShake -= 1;
/*     */     }
/*     */     
/* 216 */     if (this.inGround) {
/* 217 */       Block j = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/* 218 */       int k = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
/*     */       
/* 220 */       if ((j == this.inTile) && (k == this.inData)) {
/* 221 */         this.ticksInGround += 1;
/*     */         
/* 223 */         if (this.ticksInGround == 1200) {
/* 224 */           func_70106_y();
/*     */         }
/*     */       } else {
/* 227 */         this.inGround = false;
/* 228 */         this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 229 */         this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 230 */         this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 231 */         this.ticksInGround = 0;
/* 232 */         this.ticksInAir = 0;
/*     */       }
/*     */     } else {
/* 235 */       this.ticksInAir += 1;
/* 236 */       Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 237 */       Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 238 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3, vec31, false, true, false);
/* 239 */       vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 240 */       vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 242 */       if (movingobjectposition != null) {
/* 243 */         vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       }
/*     */       
/*     */ 
/* 247 */       Entity entity = null;
/* 248 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */       
/* 250 */       double d0 = 0.0D;
/*     */       
/*     */ 
/*     */ 
/* 254 */       String shooterPlayer = getShooter();
/* 255 */       for (int l = 0; l < list.size(); l++) {
/* 256 */         Entity entity1 = (Entity)list.get(l);
/*     */         
/* 258 */         if ((entity1.func_70067_L()) && ((this.ticksInAir >= 5) || ((entity1 != this.shootingEntity) && ((!(entity1 instanceof EntityPlayer)) || (!((EntityPlayer)entity1).func_70005_c_().equals(shooterPlayer))))))
/*     */         {
/*     */ 
/* 261 */           float f1 = 0.3F;
/* 262 */           AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(f1, f1, f1);
/* 263 */           MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec3, vec31);
/*     */           
/* 265 */           if (movingobjectposition1 != null) {
/* 266 */             double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 268 */             if ((d1 < d0) || (d0 == 0.0D)) {
/* 269 */               entity = entity1;
/* 270 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 276 */       if (entity != null) {
/* 277 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */       
/* 280 */       if ((movingobjectposition != null) && (movingobjectposition.field_72308_g != null) && ((movingobjectposition.field_72308_g instanceof EntityPlayer))) {
/* 281 */         EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
/*     */         
/* 283 */         if ((entityplayer.field_71075_bZ.field_75102_a) || (((this.shootingEntity instanceof EntityPlayer)) && (!((EntityPlayer)this.shootingEntity).func_96122_a(entityplayer))))
/*     */         {
/* 285 */           movingobjectposition = null;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 292 */       if (movingobjectposition != null) {
/* 293 */         if (movingobjectposition.field_72308_g != null) {
/* 294 */           float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 295 */           int i1 = MathHelper.func_76143_f(f2 * this.damage);
/*     */           
/* 297 */           if (getIsCritical()) {
/* 298 */             i1 += this.field_70146_Z.nextInt(i1 / 2 + 2);
/*     */           }
/*     */           
/* 301 */           BoltDamageSource damagesource = new BoltDamageSource(this, this.shootingEntity != null ? this.shootingEntity : null);
/*     */           
/* 303 */           if ((func_70027_ad()) && (!(movingobjectposition.field_72308_g instanceof EntityEnderman))) {
/* 304 */             movingobjectposition.field_72308_g.func_70015_d(5);
/*     */           }
/*     */           
/*     */ 
/* 308 */           if ((damagesource.isPoweredDraining) && ((movingobjectposition.field_72308_g instanceof EntityLivingBase))) {
/* 309 */             EntityLivingBase living = (EntityLivingBase)movingobjectposition.field_72308_g;
/*     */             
/*     */ 
/* 312 */             Collection activeEffects = living.func_70651_bq();
/* 313 */             ArrayList<Integer> removeIDs = new ArrayList();
/* 314 */             for (Object obj : activeEffects) {
/* 315 */               PotionEffect effect = (PotionEffect)obj;
/* 316 */               if ((effect.func_76456_a() != Potion.field_76436_u.field_76415_H) && (effect.func_76456_a() != Potion.field_82731_v.field_76415_H) && (effect.func_76456_a() != Potion.field_76431_k.field_76415_H))
/*     */               {
/* 318 */                 removeIDs.add(Integer.valueOf(effect.func_76456_a())); }
/*     */             }
/* 320 */             for (Integer id : removeIDs) {
/* 321 */               living.func_82170_o(id.intValue());
/*     */             }
/*     */             
/*     */ 
/* 325 */             Witchery.modHooks.reducePowerLevels(living, 0.5F);
/*     */           }
/*     */           
/*     */ 
/* 329 */           if ((damagesource.isHoly) && ((CreatureUtil.isUndead(entity)) || (CreatureUtil.isDemonic(entity)))) {
/* 330 */             i1 = (int)(i1 * 1.5D);
/*     */           }
/*     */           
/* 333 */           if (movingobjectposition.field_72308_g.func_70097_a(damagesource, i1)) {
/* 334 */             if ((movingobjectposition.field_72308_g instanceof EntityLivingBase)) {
/* 335 */               EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
/*     */               
/* 337 */               if (!this.field_70170_p.field_72995_K) {
/* 338 */                 entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1);
/*     */               }
/*     */               
/* 341 */               if (this.knockbackStrength > 0) {
/* 342 */                 float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */                 
/* 344 */                 if (f3 > 0.0F) {
/* 345 */                   movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * this.knockbackStrength * 0.6000000238418579D / f3, 0.1D, this.field_70179_y * this.knockbackStrength * 0.6000000238418579D / f3);
/*     */                 }
/*     */               }
/*     */               
/*     */ 
/* 350 */               if ((this.shootingEntity != null) && ((this.shootingEntity instanceof EntityLivingBase)))
/*     */               {
/* 352 */                 EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
/* 353 */                 EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, entitylivingbase);
/*     */               }
/*     */               
/* 356 */               if ((this.shootingEntity != null) && (movingobjectposition.field_72308_g != this.shootingEntity) && ((movingobjectposition.field_72308_g instanceof EntityPlayer)) && ((this.shootingEntity instanceof net.minecraft.entity.player.EntityPlayerMP)))
/*     */               {
/*     */ 
/* 359 */                 Witchery.packetPipeline.sendToAllAround(new net.minecraft.network.play.server.S2BPacketChangeGameState(6, 0.0F), this.shootingEntity.field_70170_p, com.emoniph.witchery.util.TargetPointUtil.from(this.shootingEntity, 128.0D));
/*     */               }
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 365 */               entitylivingbase.field_70172_ad = 0;
/*     */             }
/*     */             
/* 368 */             func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/*     */             
/* 370 */             if (!(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
/* 371 */               func_70106_y();
/*     */             }
/*     */           } else {
/* 374 */             this.field_70159_w *= -0.10000000149011612D;
/* 375 */             this.field_70181_x *= -0.10000000149011612D;
/* 376 */             this.field_70179_y *= -0.10000000149011612D;
/* 377 */             this.field_70177_z += 180.0F;
/* 378 */             this.field_70126_B += 180.0F;
/* 379 */             this.ticksInAir = 0;
/*     */           }
/*     */         } else {
/* 382 */           this.xTile = movingobjectposition.field_72311_b;
/* 383 */           this.yTile = movingobjectposition.field_72312_c;
/* 384 */           this.zTile = movingobjectposition.field_72309_d;
/* 385 */           this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/* 386 */           this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
/* 387 */           this.field_70159_w = ((float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t));
/* 388 */           this.field_70181_x = ((float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u));
/* 389 */           this.field_70179_y = ((float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v));
/* 390 */           float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 391 */           this.field_70165_t -= this.field_70159_w / f2 * 0.05000000074505806D;
/* 392 */           this.field_70163_u -= this.field_70181_x / f2 * 0.05000000074505806D;
/* 393 */           this.field_70161_v -= this.field_70179_y / f2 * 0.05000000074505806D;
/* 394 */           func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 395 */           this.inGround = true;
/* 396 */           this.arrowShake = 7;
/* 397 */           setIsCritical(false);
/*     */           
/* 399 */           if (this.inTile.func_149688_o() != net.minecraft.block.material.Material.field_151579_a) {
/* 400 */             this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, this);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 405 */       if (getIsCritical()) {
/* 406 */         for (l = 0; l < 4; l++) {
/* 407 */           this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * l / 4.0D, this.field_70163_u + this.field_70181_x * l / 4.0D, this.field_70161_v + this.field_70179_y * l / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 412 */       this.field_70165_t += this.field_70159_w;
/* 413 */       this.field_70163_u += this.field_70181_x;
/* 414 */       this.field_70161_v += this.field_70179_y;
/* 415 */       float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 416 */       this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */       
/* 418 */       for (this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f2) * 180.0D / 3.141592653589793D)); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {}
/*     */       
/*     */ 
/*     */ 
/* 422 */       while (this.field_70125_A - this.field_70127_C >= 180.0F) {
/* 423 */         this.field_70127_C += 360.0F;
/*     */       }
/*     */       
/* 426 */       while (this.field_70177_z - this.field_70126_B < -180.0F) {
/* 427 */         this.field_70126_B -= 360.0F;
/*     */       }
/*     */       
/* 430 */       while (this.field_70177_z - this.field_70126_B >= 180.0F) {
/* 431 */         this.field_70126_B += 360.0F;
/*     */       }
/*     */       
/* 434 */       this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 435 */       this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 436 */       float f4 = 0.99F;
/* 437 */       float f1 = 0.05F;
/*     */       
/* 439 */       if (func_70090_H()) {
/* 440 */         for (int j1 = 0; j1 < 4; j1++) {
/* 441 */           float f3 = 0.25F;
/* 442 */           this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f3, this.field_70163_u - this.field_70181_x * f3, this.field_70161_v - this.field_70179_y * f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */         }
/*     */         
/*     */ 
/* 446 */         f4 = 0.8F;
/*     */       }
/*     */       
/* 449 */       this.field_70159_w *= f4;
/* 450 */       this.field_70181_x *= f4;
/* 451 */       this.field_70179_y *= f4;
/* 452 */       this.field_70181_x -= f1;
/* 453 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 454 */       func_145775_I();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 460 */     par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
/* 461 */     par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
/* 462 */     par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
/* 463 */     par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
/* 464 */     par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
/* 465 */     par1NBTTagCompound.func_74774_a("shake", (byte)this.arrowShake);
/* 466 */     par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/* 467 */     par1NBTTagCompound.func_74774_a("pickup", (byte)this.canBePickedUp);
/* 468 */     par1NBTTagCompound.func_74780_a("damage", this.damage);
/*     */     
/* 470 */     par1NBTTagCompound.func_74768_a("boltType", getBoltType());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 476 */     this.xTile = par1NBTTagCompound.func_74765_d("xTile");
/* 477 */     this.yTile = par1NBTTagCompound.func_74765_d("yTile");
/* 478 */     this.zTile = par1NBTTagCompound.func_74765_d("zTile");
/* 479 */     this.inTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("inTile") & 0xFF);
/* 480 */     this.inData = (par1NBTTagCompound.func_74771_c("inData") & 0xFF);
/* 481 */     this.arrowShake = (par1NBTTagCompound.func_74771_c("shake") & 0xFF);
/* 482 */     this.inGround = (par1NBTTagCompound.func_74771_c("inGround") == 1);
/*     */     
/* 484 */     if (par1NBTTagCompound.func_74764_b("damage")) {
/* 485 */       this.damage = par1NBTTagCompound.func_74769_h("damage");
/*     */     }
/*     */     
/* 488 */     if (par1NBTTagCompound.func_74764_b("pickup")) {
/* 489 */       this.canBePickedUp = par1NBTTagCompound.func_74771_c("pickup");
/* 490 */     } else if (par1NBTTagCompound.func_74764_b("player")) {
/* 491 */       this.canBePickedUp = (par1NBTTagCompound.func_74767_n("player") ? 1 : 0);
/*     */     }
/*     */     
/* 494 */     setBoltType(par1NBTTagCompound.func_74762_e("boltType"));
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer)
/*     */   {
/* 499 */     if ((!this.field_70170_p.field_72995_K) && (this.inGround) && (this.arrowShake <= 0)) {
/* 500 */       boolean flag = (this.canBePickedUp == 1) || ((this.canBePickedUp == 2) && (par1EntityPlayer.field_71075_bZ.field_75098_d));
/*     */       
/* 502 */       if (this.canBePickedUp == 1) {
/* 503 */         net.minecraft.item.ItemStack stack = null;
/* 504 */         if (isDraining()) {
/* 505 */           stack = Witchery.Items.GENERIC.itemBoltAntiMagic.createStack();
/* 506 */         } else if (isHolyDamage()) {
/* 507 */           stack = Witchery.Items.GENERIC.itemBoltHoly.createStack();
/*     */         } else {
/* 509 */           stack = Witchery.Items.GENERIC.itemBoltStake.createStack();
/*     */         }
/*     */         
/* 512 */         if (!par1EntityPlayer.field_71071_by.func_70441_a(stack)) {
/* 513 */           flag = false;
/*     */         }
/*     */       }
/*     */       
/* 517 */       if (flag) {
/* 518 */         func_85030_a("random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 519 */         par1EntityPlayer.func_71001_a(this, 1);
/* 520 */         func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 527 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 533 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void setDamage(double par1) {
/* 537 */     this.damage = par1;
/*     */   }
/*     */   
/*     */   public double getDamage() {
/* 541 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setKnockbackStrength(int par1) {
/* 545 */     this.knockbackStrength = par1;
/*     */   }
/*     */   
/*     */   public void setBoltType(int type) {
/* 549 */     if (!this.field_70170_p.field_72995_K) {
/* 550 */       this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)type));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getBoltType() {
/* 555 */     byte b0 = this.field_70180_af.func_75683_a(17);
/* 556 */     return b0;
/*     */   }
/*     */   
/*     */   public boolean isDraining() {
/* 560 */     int b0 = getBoltType();
/* 561 */     return (b0 == 1) || (b0 == 2);
/*     */   }
/*     */   
/*     */   public boolean isPoweredDraining() {
/* 565 */     return getBoltType() == 2;
/*     */   }
/*     */   
/*     */   public boolean isHolyDamage() {
/* 569 */     return getBoltType() == 3;
/*     */   }
/*     */   
/*     */   public boolean isWoodenDamage() {
/* 573 */     int boltType = getBoltType();
/*     */     
/* 575 */     return (boltType == 0) || (boltType == 1) || (boltType == 2);
/*     */   }
/*     */   
/*     */   public boolean isSilverDamage() {
/* 579 */     int boltType = getBoltType();
/*     */     
/* 581 */     return boltType == 4;
/*     */   }
/*     */   
/*     */   public boolean func_70075_an()
/*     */   {
/* 586 */     return false;
/*     */   }
/*     */   
/*     */   public void setIsCritical(boolean par1) {
/* 590 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 592 */     if (par1) {
/* 593 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 595 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getIsCritical() {
/* 600 */     byte b0 = this.field_70180_af.func_75683_a(16);
/* 601 */     return (b0 & 0x1) != 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityBolt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */