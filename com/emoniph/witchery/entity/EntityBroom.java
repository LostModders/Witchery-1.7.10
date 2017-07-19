/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ 
/*     */ public class EntityBroom extends Entity
/*     */ {
/*     */   private boolean field_70279_a;
/*     */   private double speedMultiplier;
/*     */   private int broomPosRotationIncrements;
/*     */   private double broomX;
/*     */   private double broomY;
/*     */   private double broomZ;
/*     */   private double broomYaw;
/*     */   private double broomPitch;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   private double velocityX;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   private double velocityY;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   private double velocityZ;
/*     */   
/*     */   public static class EventHooks
/*     */   {
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onLivingFall(net.minecraftforge.event.entity.living.LivingFallEvent event)
/*     */     {
/*  30 */       if ((event.entityLiving instanceof EntityPlayer)) {
/*  31 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/*     */         
/*  33 */         if ((player.func_70115_ae()) && ((player.field_70154_o instanceof EntityBroom))) {
/*  34 */           event.distance = 0.0F;
/*     */         }
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityBroom(net.minecraft.world.World world)
/*     */   {
/*  56 */     super(world);
/*  57 */     this.field_70279_a = true;
/*  58 */     this.speedMultiplier = 0.07D;
/*  59 */     this.field_70156_m = true;
/*  60 */     func_70105_a(1.2F, 0.5F);
/*  61 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EntityBroom(net.minecraft.world.World world, double x, double y, double z)
/*     */   {
/*  68 */     this(world);
/*  69 */     func_70107_b(x, y + this.field_70129_M, z);
/*  70 */     this.field_70159_w = 0.0D;
/*  71 */     this.field_70181_x = 0.0D;
/*  72 */     this.field_70179_y = 0.0D;
/*  73 */     this.field_70169_q = x;
/*  74 */     this.field_70167_r = y;
/*  75 */     this.field_70166_s = z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  82 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  87 */     this.field_70180_af.func_75682_a(10, "");
/*  88 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)-1));
/*  89 */     this.field_70180_af.func_75682_a(17, new Integer(0));
/*  90 */     this.field_70180_af.func_75682_a(18, new Integer(1));
/*  91 */     this.field_70180_af.func_75682_a(19, new Float(0.0F));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70081_e(int par1) {}
/*     */   
/*     */   public void setBrushColor(int color)
/*     */   {
/*  99 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)color));
/*     */   }
/*     */   
/*     */   public int getBrushColor() {
/* 103 */     return this.field_70180_af.func_75683_a(16);
/*     */   }
/*     */   
/*     */   public void setCustomNameTag(String par1Str)
/*     */   {
/* 108 */     this.field_70180_af.func_75692_b(10, par1Str);
/*     */   }
/*     */   
/*     */   public String getCustomNameTag()
/*     */   {
/* 113 */     return this.field_70180_af.func_75681_e(10);
/*     */   }
/*     */   
/*     */   public boolean hasCustomNameTag()
/*     */   {
/* 118 */     return this.field_70180_af.func_75681_e(10).length() > 0;
/*     */   }
/*     */   
/*     */   public net.minecraft.util.AxisAlignedBB func_70114_g(Entity par1Entity)
/*     */   {
/* 123 */     return par1Entity.field_70121_D;
/*     */   }
/*     */   
/*     */   public net.minecraft.util.AxisAlignedBB func_70046_E()
/*     */   {
/* 128 */     return this.field_70121_D;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public double func_70042_X()
/*     */   {
/* 138 */     return this.field_70131_O * 0.55D;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(net.minecraft.util.DamageSource par1DamageSource, float par2)
/*     */   {
/* 143 */     if (func_85032_ar())
/* 144 */       return false;
/* 145 */     if ((!this.field_70170_p.field_72995_K) && (!this.field_70128_L)) {
/* 146 */       setForwardDirection(-getForwardDirection());
/* 147 */       setTimeSinceHit(10);
/* 148 */       setDamageTaken(getDamageTaken() + par2 * 10.0F);
/* 149 */       func_70018_K();
/* 150 */       boolean flag = ((par1DamageSource.func_76346_g() instanceof EntityPlayer)) && (((EntityPlayer)par1DamageSource.func_76346_g()).field_71075_bZ.field_75098_d);
/*     */       
/* 152 */       if ((flag) || (getDamageTaken() > 40.0F)) {
/* 153 */         if (this.field_70153_n != null) {
/* 154 */           this.field_70153_n.func_70078_a(this);
/*     */         }
/*     */         
/* 157 */         if (!flag) {
/* 158 */           net.minecraft.item.ItemStack broomStack = com.emoniph.witchery.Witchery.Items.GENERIC.itemBroomEnchanted.createStack();
/* 159 */           if (hasCustomNameTag()) {
/* 160 */             broomStack.func_151001_c(getCustomNameTag());
/*     */           }
/* 162 */           int brushColor = getBrushColor();
/* 163 */           if ((brushColor >= 0) && (brushColor <= 15)) {
/* 164 */             com.emoniph.witchery.Witchery.Items.GENERIC.setBroomItemColor(broomStack, brushColor);
/*     */           }
/* 166 */           func_70099_a(broomStack, 0.0F);
/*     */         }
/*     */         
/* 169 */         func_70106_y();
/*     */       }
/*     */       
/* 172 */       return true;
/*     */     }
/* 174 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70057_ab()
/*     */   {
/* 181 */     setForwardDirection(-getForwardDirection());
/* 182 */     setTimeSinceHit(10);
/* 183 */     setDamageTaken(getDamageTaken() * 11.0F);
/*     */   }
/*     */   
/*     */   public boolean func_70067_L()
/*     */   {
/* 188 */     return (!this.field_70128_L) && (this.field_70153_n == null);
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70056_a(double x, double y, double z, float yaw, float pitch, int par9)
/*     */   {
/* 194 */     if (this.field_70279_a) {
/* 195 */       this.broomPosRotationIncrements = (par9 + 5);
/*     */     } else {
/* 197 */       double d3 = x - this.field_70165_t;
/* 198 */       double d4 = y - this.field_70163_u;
/* 199 */       double d5 = z - this.field_70161_v;
/* 200 */       double d6 = d3 * d3 + d4 * d4 + d5 * d5;
/*     */       
/* 202 */       if (d6 <= 1.0D) {
/* 203 */         return;
/*     */       }
/*     */       
/* 206 */       this.broomPosRotationIncrements = 3;
/*     */     }
/*     */     
/* 209 */     this.broomX = x;
/* 210 */     this.broomY = y;
/* 211 */     this.broomZ = z;
/* 212 */     this.broomYaw = yaw;
/* 213 */     this.broomPitch = pitch;
/* 214 */     this.field_70159_w = this.velocityX;
/* 215 */     this.field_70181_x = this.velocityY;
/* 216 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70016_h(double x, double y, double z)
/*     */   {
/* 222 */     this.velocityX = (this.field_70159_w = x);
/* 223 */     this.velocityY = (this.field_70181_x = y);
/* 224 */     this.velocityZ = (this.field_70179_y = z);
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 229 */     super.func_70071_h_();
/*     */     
/* 231 */     if ((this.field_70173_aa % 100 == 0) && 
/* 232 */       (this.field_70153_n != null) && ((this.field_70153_n instanceof EntityPlayer))) {
/* 233 */       this.riderHasSoaringBrew = com.emoniph.witchery.infusion.InfusedBrewEffect.Soaring.isActive((EntityPlayer)this.field_70153_n);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 238 */     if (getTimeSinceHit() > 0) {
/* 239 */       setTimeSinceHit(getTimeSinceHit() - 1);
/*     */     }
/*     */     
/* 242 */     if (getDamageTaken() > 0.0F) {
/* 243 */       setDamageTaken(getDamageTaken() - 1.0F);
/*     */     }
/*     */     
/* 246 */     this.field_70169_q = this.field_70165_t;
/* 247 */     this.field_70167_r = this.field_70163_u;
/* 248 */     this.field_70166_s = this.field_70161_v;
/* 249 */     byte b0 = 5;
/* 250 */     double d0 = 0.0D;
/* 251 */     double initialHorzVelocity = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */     
/*     */ 
/*     */ 
/* 255 */     if (initialHorzVelocity > 0.26249999999999996D) {
/* 256 */       double newHorzVelocity = Math.cos(this.field_70177_z * 3.141592653589793D / 180.0D);
/* 257 */       double d1 = Math.sin(this.field_70177_z * 3.141592653589793D / 180.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 263 */     if ((this.field_70170_p.field_72995_K) && (this.field_70279_a)) {
/* 264 */       if (this.broomPosRotationIncrements > 0) {
/* 265 */         double newHorzVelocity = this.field_70165_t + (this.broomX - this.field_70165_t) / this.broomPosRotationIncrements;
/* 266 */         double d5 = this.field_70163_u + (this.broomY - this.field_70163_u) / this.broomPosRotationIncrements;
/* 267 */         double d11 = this.field_70161_v + (this.broomZ - this.field_70161_v) / this.broomPosRotationIncrements;
/* 268 */         double d10 = net.minecraft.util.MathHelper.func_76138_g(this.broomYaw - this.field_70177_z);
/* 269 */         this.field_70177_z = ((float)(this.field_70177_z + d10 / this.broomPosRotationIncrements));
/* 270 */         this.field_70125_A = ((float)(this.field_70125_A + (this.broomPitch - this.field_70125_A) / this.broomPosRotationIncrements));
/*     */         
/* 272 */         this.broomPosRotationIncrements -= 1;
/* 273 */         func_70107_b(newHorzVelocity, d5, d11);
/* 274 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       } else {
/* 276 */         double newHorzVelocity = this.field_70165_t + this.field_70159_w;
/* 277 */         double d5 = this.field_70163_u + this.field_70181_x;
/* 278 */         double d11 = this.field_70161_v + this.field_70179_y;
/* 279 */         func_70101_b((float)(this.field_70177_z + (this.broomYaw - this.field_70177_z)), (float)(this.field_70125_A + (this.broomPitch - this.field_70125_A)));
/*     */         
/* 281 */         func_70107_b(newHorzVelocity, d5, d11);
/*     */         
/* 283 */         this.field_70159_w *= 0.9900000095367432D;
/* 284 */         this.field_70179_y *= 0.9900000095367432D;
/*     */       }
/*     */     } else {
/* 287 */       if ((this.field_70153_n != null) && ((this.field_70153_n instanceof net.minecraft.entity.EntityLivingBase)))
/*     */       {
/* 289 */         double newHorzVelocity = ((net.minecraft.entity.EntityLivingBase)this.field_70153_n).field_70701_bs;
/*     */         
/* 291 */         if (newHorzVelocity > 0.0D) {
/* 292 */           double d5 = -Math.sin(this.field_70153_n.field_70177_z * 3.1415927F / 180.0F);
/* 293 */           double d11 = Math.cos(this.field_70153_n.field_70177_z * 3.1415927F / 180.0F);
/*     */           
/* 295 */           this.field_70159_w += d5 * this.speedMultiplier * (0.1D + (this.riderHasSoaringBrew ? 0.1D : 0.0D) + (this.riderHasOwlFamiliar ? 0.2D : 0.0D));
/* 296 */           this.field_70179_y += d11 * this.speedMultiplier * (0.1D + (this.riderHasSoaringBrew ? 0.1D : 0.0D) + (this.riderHasOwlFamiliar ? 0.2D : 0.0D));
/*     */           
/* 298 */           double pitch = -Math.sin(this.field_70153_n.field_70125_A * 3.1415927F / 180.0F);
/* 299 */           if ((pitch > -0.5D) && (pitch < 0.2D)) {
/* 300 */             pitch = 0.0D;
/* 301 */           } else if (pitch < 0.0D) {
/* 302 */             pitch *= 0.5D;
/*     */           }
/*     */           
/* 305 */           this.field_70181_x = (pitch * this.speedMultiplier * 2.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 314 */         else if ((newHorzVelocity == 0.0D) && ((this.riderHasOwlFamiliar) || (this.riderHasSoaringBrew))) {
/* 315 */           this.field_70159_w *= 0.9D;
/* 316 */           this.field_70179_y *= 0.9D;
/*     */         }
/* 318 */       } else if (this.field_70153_n == null) {
/* 319 */         this.riderHasOwlFamiliar = false;
/* 320 */         double moX = this.field_70159_w * 0.9D;
/* 321 */         double moZ = this.field_70179_y * 0.9D;
/* 322 */         this.field_70159_w = (Math.abs(moX) < 0.01D ? 0.0D : moX);
/* 323 */         this.field_70179_y = (Math.abs(moZ) < 0.01D ? 0.0D : moZ);
/* 324 */         if (!this.field_70122_E) {
/* 325 */           this.field_70181_x = -0.2D;
/*     */         }
/*     */       }
/*     */       
/* 329 */       double newHorzVelocity = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */       
/* 331 */       double SPEED_LIMIT = 0.9D + (this.riderHasOwlFamiliar ? 0.3D : 0.0D) + (this.riderHasSoaringBrew ? 0.3D : 0.0D);
/* 332 */       if (newHorzVelocity > SPEED_LIMIT) {
/* 333 */         double d5 = SPEED_LIMIT / newHorzVelocity;
/* 334 */         this.field_70159_w *= d5;
/* 335 */         this.field_70179_y *= d5;
/* 336 */         this.field_70181_x *= d5;
/* 337 */         newHorzVelocity = SPEED_LIMIT;
/*     */       }
/*     */       
/* 340 */       double MAX_ACCELERATION = (this.riderHasSoaringBrew) || (this.riderHasOwlFamiliar) ? 0.35D : 0.35D;
/* 341 */       double MAX_ACCELERATION_FACTOR = MAX_ACCELERATION * 100.0D;
/*     */       
/* 343 */       if ((newHorzVelocity > initialHorzVelocity) && (this.speedMultiplier < MAX_ACCELERATION)) {
/* 344 */         this.speedMultiplier += (MAX_ACCELERATION - this.speedMultiplier) / MAX_ACCELERATION_FACTOR;
/*     */         
/* 346 */         if (this.speedMultiplier > MAX_ACCELERATION) {
/* 347 */           this.speedMultiplier = MAX_ACCELERATION;
/*     */         }
/*     */       } else {
/* 350 */         this.speedMultiplier -= (this.speedMultiplier - 0.07D) / MAX_ACCELERATION_FACTOR;
/*     */         
/* 352 */         if (this.speedMultiplier < 0.07D) {
/* 353 */           this.speedMultiplier = 0.07D;
/*     */         }
/*     */       }
/*     */       
/* 357 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/* 359 */       this.field_70159_w *= 0.9900000095367432D;
/* 360 */       this.field_70181_x *= 0.9900000095367432D;
/* 361 */       this.field_70179_y *= 0.9900000095367432D;
/*     */       
/* 363 */       this.field_70125_A = 0.0F;
/* 364 */       double d5 = this.field_70177_z;
/* 365 */       double d11 = this.field_70169_q - this.field_70165_t;
/* 366 */       double d10 = this.field_70166_s - this.field_70161_v;
/*     */       
/* 368 */       if (d11 * d11 + d10 * d10 > 0.001D) {
/* 369 */         d5 = (float)(Math.atan2(d10, d11) * 180.0D / 3.141592653589793D);
/*     */       }
/*     */       
/* 372 */       double d12 = net.minecraft.util.MathHelper.func_76138_g(d5 - this.field_70177_z);
/*     */       
/* 374 */       this.field_70177_z = ((float)(this.field_70177_z + d12));
/* 375 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       
/* 377 */       if (!this.field_70170_p.field_72995_K) {
/* 378 */         java.util.List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*     */         
/*     */ 
/* 381 */         if ((list != null) && (!list.isEmpty())) {
/* 382 */           for (int l = 0; l < list.size(); l++) {
/* 383 */             Entity entity = (Entity)list.get(l);
/*     */             
/* 385 */             if ((entity != this.field_70153_n) && (entity.func_70104_M()) && ((entity instanceof EntityBroom))) {
/* 386 */               entity.func_70108_f(this);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 391 */         if ((this.field_70153_n != null) && 
/* 392 */           (this.field_70153_n.field_70128_L)) {
/* 393 */           this.field_70153_n = null;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70043_V()
/*     */   {
/* 405 */     super.func_70043_V();
/*     */   }
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 410 */     par1NBTTagCompound.func_74778_a("CustomName", getCustomNameTag());
/* 411 */     int brushColor = getBrushColor();
/* 412 */     if (brushColor >= 0) {
/* 413 */       par1NBTTagCompound.func_74774_a("BrushColor", Byte.valueOf((byte)brushColor).byteValue());
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 419 */     if ((par1NBTTagCompound.func_74764_b("CustomName")) && (par1NBTTagCompound.func_74779_i("CustomName").length() > 0))
/*     */     {
/* 421 */       setCustomNameTag(par1NBTTagCompound.func_74779_i("CustomName"));
/*     */     }
/*     */     
/* 424 */     if ((par1NBTTagCompound.func_74764_b("BrushColor")) && (par1NBTTagCompound.func_74771_c("BrushColor") >= 0)) {
/* 425 */       setBrushColor(par1NBTTagCompound.func_74771_c("BrushColor"));
/*     */     }
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 432 */     return 0.0F;
/*     */   }
/*     */   
/* 435 */   boolean riderHasOwlFamiliar = false;
/* 436 */   boolean riderHasSoaringBrew = false;
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player)
/*     */   {
/* 440 */     if ((this.field_70153_n != null) && ((this.field_70153_n instanceof EntityPlayer)) && (this.field_70153_n != player))
/* 441 */       return true;
/* 442 */     if ((!this.field_70170_p.field_72995_K) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == net.minecraft.init.Items.field_151100_aR)) {
/* 443 */       net.minecraft.item.ItemStack itemstack = player.func_70694_bm();
/* 444 */       int i = net.minecraft.block.BlockColored.func_150032_b(itemstack.func_77960_j());
/* 445 */       setBrushColor(i);
/*     */       
/* 447 */       if (!player.field_71075_bZ.field_75098_d) {
/* 448 */         itemstack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 451 */       if (itemstack.field_77994_a <= 0) {
/* 452 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (net.minecraft.item.ItemStack)null);
/*     */       }
/* 454 */       return true;
/*     */     }
/* 456 */     if (!this.field_70170_p.field_72995_K) {
/* 457 */       this.riderHasOwlFamiliar = com.emoniph.witchery.familiar.Familiar.hasActiveBroomMasteryFamiliar(player);
/* 458 */       this.riderHasSoaringBrew = com.emoniph.witchery.infusion.InfusedBrewEffect.Soaring.isActive(player);
/* 459 */       player.func_70078_a(this);
/*     */     }
/*     */     
/* 462 */     return true;
/*     */   }
/*     */   
/*     */   public void setDamageTaken(float par1)
/*     */   {
/* 467 */     this.field_70180_af.func_75692_b(19, Float.valueOf(par1));
/*     */   }
/*     */   
/*     */   public float getDamageTaken() {
/* 471 */     return this.field_70180_af.func_111145_d(19);
/*     */   }
/*     */   
/*     */   public void setTimeSinceHit(int par1) {
/* 475 */     this.field_70180_af.func_75692_b(17, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public int getTimeSinceHit() {
/* 479 */     return this.field_70180_af.func_75679_c(17);
/*     */   }
/*     */   
/*     */   public void setForwardDirection(int par1) {
/* 483 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public int getForwardDirection() {
/* 487 */     return this.field_70180_af.func_75679_c(18);
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70270_d(boolean par1) {
/* 492 */     this.field_70279_a = par1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityBroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */