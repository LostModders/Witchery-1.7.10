/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.EntityThrowableBase;
/*     */ import com.emoniph.witchery.client.particle.NaturePowerFX;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.item.ItemSunGrenade;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGrenade extends EntityThrowableBase
/*     */ {
/*     */   String owner;
/*     */   boolean blockPlaced;
/*     */   int blockX;
/*     */   int blockY;
/*     */   int blockZ;
/*     */   
/*     */   public EntityGrenade(World world)
/*     */   {
/*  32 */     super(world);
/*  33 */     func_70105_a(0.25F, 0.25F);
/*  34 */     this.field_70145_X = false;
/*     */   }
/*     */   
/*     */   public EntityGrenade(World world, EntityLivingBase thrower, ItemStack stack) {
/*  38 */     super(world, thrower, -20.0F);
/*  39 */     func_70105_a(0.25F, 0.25F);
/*  40 */     this.field_70145_X = false;
/*  41 */     if ((stack != null) && (stack.func_77973_b() == Witchery.Items.DUP_GRENADE)) {
/*  42 */       setMode(1);
/*  43 */       setOwner(ItemSunGrenade.getOwnerName(stack));
/*     */     } else {
/*  45 */       setMode(0);
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityGrenade(World world, double x, double y, double z, ItemStack stack) {
/*  50 */     super(world, x, y, z, -20.0F);
/*  51 */     func_70105_a(0.25F, 0.25F);
/*  52 */     this.field_70145_X = false;
/*     */     
/*  54 */     if ((stack != null) && (stack.func_77973_b() == Witchery.Items.DUP_GRENADE)) {
/*  55 */       setMode(1);
/*  56 */       setOwner(ItemSunGrenade.getOwnerName(stack));
/*     */     } else {
/*  58 */       setMode(0);
/*     */     }
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity()
/*     */   {
/*  64 */     return getImpact() ? 0.0F : 0.05F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  69 */     return 0.75F;
/*     */   }
/*     */   
/*     */   protected float func_70183_g()
/*     */   {
/*  74 */     return -20.0F;
/*     */   }
/*     */   
/*     */   public void setOwner(String owner) {
/*  78 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  83 */     super.func_70088_a();
/*  84 */     this.field_70180_af.func_75682_a(6, Byte.valueOf((byte)0));
/*  85 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public int getMode() {
/*  89 */     return this.field_70180_af.func_75683_a(16);
/*     */   }
/*     */   
/*     */   public void setMode(int mode) {
/*  93 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)mode));
/*     */   }
/*     */   
/*     */   protected void setImpact(boolean impact) {
/*  97 */     func_70096_w().func_75692_b(6, Byte.valueOf((byte)(impact ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public boolean getImpact() {
/* 101 */     return func_70096_w().func_75683_a(6) == 1;
/*     */   }
/*     */   
/*     */   protected int getMaxGroundTicks()
/*     */   {
/* 106 */     return super.getMaxGroundTicks();
/*     */   }
/*     */   
/*     */   protected int getMaxAirTicks()
/*     */   {
/* 111 */     return super.getMaxAirTicks();
/*     */   }
/*     */   
/*     */   protected void onImpact(net.minecraft.util.MovingObjectPosition mop)
/*     */   {
/* 116 */     if (!this.field_70170_p.field_72995_K) {
/* 117 */       if (getMode() == 0) {
/* 118 */         setImpact(true);
/*     */       } else {
/* 120 */         if (!this.field_70170_p.field_72995_K) {
/* 121 */           onSetDead();
/*     */         } else {
/* 123 */           onClientSetDead();
/*     */         }
/*     */         
/* 126 */         func_70106_y();
/*     */       }
/*     */     }
/* 129 */     this.field_70159_w = 0.0D;
/* 130 */     this.field_70181_x = 0.0D;
/* 131 */     this.field_70179_y = 0.0D;
/*     */   }
/*     */   
/*     */   public void func_70030_z()
/*     */   {
/* 136 */     super.func_70030_z();
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 141 */     super.func_70071_h_();
/* 142 */     if (getMode() == 0) {
/* 143 */       if ((this.field_70170_p.field_72995_K) && (getImpact()) && (this.field_70170_p.field_73012_v.nextInt(4) == 0)) {
/* 144 */         float red = 1.0F;
/* 145 */         float green = 1.0F;
/* 146 */         float blue = 0.0F;
/* 147 */         Witchery.proxy.generateParticle(this.field_70170_p, this.field_70165_t - 0.1D + this.field_70170_p.field_73012_v.nextDouble() * 0.2D, this.field_70163_u + 0.3D * this.field_70131_O - 0.1D + this.field_70170_p.field_73012_v.nextDouble() * 0.2D, this.field_70161_v - 0.1D + this.field_70170_p.field_73012_v.nextDouble() * 0.2D, red, green, blue, 10, -0.3F);
/*     */ 
/*     */       }
/* 150 */       else if ((!this.field_70170_p.field_72995_K) && (!this.field_70128_L)) {
/* 151 */         if ((!this.blockPlaced) && (this.field_70173_aa % 5 == 4)) {
/* 152 */           this.blockPlaced = true;
/* 153 */           this.blockX = MathHelper.func_76128_c(this.field_70165_t);
/* 154 */           this.blockY = MathHelper.func_76128_c(this.field_70163_u);
/* 155 */           this.blockZ = MathHelper.func_76128_c(this.field_70161_v);
/* 156 */           if (this.field_70170_p.func_147437_c(this.blockX, this.blockY, this.blockZ)) {
/* 157 */             this.field_70170_p.func_147449_b(this.blockX, this.blockY, this.blockZ, Witchery.Blocks.LIGHT);
/*     */           } else {
/* 159 */             this.blockY += 1;
/* 160 */             if (this.field_70170_p.func_147437_c(this.blockX, this.blockY, this.blockZ)) {
/* 161 */               this.field_70170_p.func_147449_b(this.blockX, this.blockY, this.blockZ, Witchery.Blocks.LIGHT);
/*     */             }
/*     */           }
/* 164 */         } else if ((this.blockPlaced) && ((this.field_70173_aa % 5 == 2) || (getImpact()))) {
/* 165 */           int x = MathHelper.func_76128_c(this.field_70165_t);
/* 166 */           int y = MathHelper.func_76128_c(this.field_70163_u);
/* 167 */           int z = MathHelper.func_76128_c(this.field_70161_v);
/* 168 */           if ((this.blockX != x) || (this.blockY != y) || (this.blockZ != z) || ((this.field_70173_aa % 30 == 4) && (this.field_70170_p.func_147437_c(x, y, z))))
/*     */           {
/* 170 */             if (this.field_70170_p.func_147439_a(this.blockX, this.blockY, this.blockZ) == Witchery.Blocks.LIGHT) {
/* 171 */               this.field_70170_p.func_147468_f(this.blockX, this.blockY, this.blockZ);
/*     */             }
/* 173 */             this.blockX = x;
/* 174 */             this.blockY = y;
/* 175 */             this.blockZ = z;
/*     */             
/* 177 */             if (this.field_70170_p.func_147437_c(this.blockX, this.blockY, this.blockZ)) {
/* 178 */               this.field_70170_p.func_147449_b(this.blockX, this.blockY, this.blockZ, Witchery.Blocks.LIGHT);
/*     */             } else {
/* 180 */               this.blockY += 1;
/* 181 */               if (this.field_70170_p.func_147437_c(this.blockX, this.blockY, this.blockZ)) {
/* 182 */                 this.field_70170_p.func_147449_b(this.blockX, this.blockY, this.blockZ, Witchery.Blocks.LIGHT);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 187 */         if (getImpact()) {
/* 188 */           net.minecraft.entity.Entity entity = null;
/* 189 */           List<EntityLivingBase> list = this.field_70170_p.func_72872_a(EntityLivingBase.class, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */           
/*     */ 
/*     */ 
/* 193 */           double d0 = 0.0D;
/* 194 */           for (int j = 0; j < list.size(); j++) {
/* 195 */             EntityLivingBase entity1 = (EntityLivingBase)list.get(j);
/*     */             
/* 197 */             if ((entity1.func_70067_L()) && 
/* 198 */               (CreatureUtil.isUndead(entity1))) {
/* 199 */               entity1.func_70015_d(3);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void onSetDead()
/*     */   {
/* 212 */     if (!this.field_70170_p.field_72995_K) {
/* 213 */       func_70099_a(Witchery.Items.GENERIC.itemQuartzSphere.createStack(), 0.5F);
/*     */       
/* 215 */       int mode = getMode();
/* 216 */       if (mode == 0) {
/* 217 */         if (this.blockPlaced) {
/* 218 */           this.blockPlaced = false;
/* 219 */           if (this.field_70170_p.func_147439_a(this.blockX, this.blockY, this.blockZ) == Witchery.Blocks.LIGHT) {
/* 220 */             this.field_70170_p.func_147468_f(this.blockX, this.blockY, this.blockZ);
/*     */           }
/*     */         }
/*     */         
/* 224 */         net.minecraft.entity.Entity entity = null;
/* 225 */         List<EntityLivingBase> list = this.field_70170_p.func_72872_a(EntityLivingBase.class, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(3.0D, 2.0D, 3.0D));
/*     */         
/* 227 */         double d0 = 0.0D;
/* 228 */         for (int j = 0; j < list.size(); j++) {
/* 229 */           EntityLivingBase entity1 = (EntityLivingBase)list.get(j);
/*     */           
/* 231 */           if (entity1.func_70067_L()) {
/* 232 */             if (CreatureUtil.isUndead(entity1)) {
/* 233 */               entity1.func_70015_d(5);
/*     */               
/*     */ 
/* 236 */               if ((entity1 instanceof EntityPlayer)) {
/* 237 */                 EntityPlayer player = (EntityPlayer)entity1;
/* 238 */                 ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 239 */                 if ((playerEx.getVampireLevel() == 4) && (playerEx.canIncreaseVampireLevel())) {
/* 240 */                   if (playerEx.getVampireQuestCounter() >= 9) {
/* 241 */                     playerEx.increaseVampireLevel();
/*     */                   } else {
/* 243 */                     playerEx.increaseVampireQuestCounter();
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 249 */             entity1.func_70690_d(new net.minecraft.potion.PotionEffect(net.minecraft.potion.Potion.field_76440_q.field_76415_H, com.emoniph.witchery.util.TimeUtil.secsToTicks(this.field_70170_p.field_73012_v.nextInt(3) + 10), 0, true));
/*     */           }
/*     */         }
/*     */       }
/* 253 */       else if (mode == 1) {
/* 254 */         EntityFollower entity = new EntityFollower(this.field_70170_p);
/* 255 */         entity.setFollowerType(5);
/* 256 */         entity.setSkin(this.owner != null ? this.owner : "");
/* 257 */         entity.func_94058_c(this.owner != null ? this.owner : "Steve");
/* 258 */         entity.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/* 259 */         entity.setTTL(com.emoniph.witchery.util.TimeUtil.secsToTicks(10));
/* 260 */         this.field_70170_p.func_72838_d(entity);
/* 261 */         entity.attractAttention();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   protected void onClientSetDead()
/*     */   {
/* 269 */     if (getMode() == 0) {
/* 270 */       for (int i = 0; i < 20; i++) {
/* 271 */         double width = 0.4D;
/* 272 */         double xPos = 0.3D + this.field_70146_Z.nextDouble() * 0.4D;
/* 273 */         double zPos = 0.3D + this.field_70146_Z.nextDouble() * 0.4D;
/* 274 */         double d0 = this.field_70165_t;
/* 275 */         double d1 = this.field_70163_u;
/* 276 */         double d2 = this.field_70161_v;
/*     */         
/* 278 */         NaturePowerFX sparkle = new NaturePowerFX(this.field_70170_p, d0, d1, d2);
/* 279 */         sparkle.setScale(1.0F);
/* 280 */         sparkle.setGravity(0.2F);
/* 281 */         sparkle.setCanMove(true);
/* 282 */         sparkle.field_70145_X = true;
/* 283 */         double maxSpeed = 0.08D;
/* 284 */         double doubleSpeed = 0.16D;
/* 285 */         sparkle.func_70016_h(this.field_70146_Z.nextDouble() * 0.16D - 0.08D, this.field_70146_Z.nextDouble() * 0.05D + 0.12D, this.field_70146_Z.nextDouble() * 0.16D - 0.08D);
/*     */         
/* 287 */         sparkle.setMaxAge(25 + this.field_70146_Z.nextInt(10));
/* 288 */         float red = 1.0F;
/* 289 */         float green = 1.0F;
/* 290 */         float blue = 0.0F;
/* 291 */         float maxColorShift = 0.2F;
/* 292 */         float doubleColorShift = maxColorShift * 2.0F;
/* 293 */         float colorshiftR = this.field_70146_Z.nextFloat() * doubleColorShift - maxColorShift;
/* 294 */         float colorshiftG = this.field_70146_Z.nextFloat() * doubleColorShift - maxColorShift;
/* 295 */         float colorshiftB = this.field_70146_Z.nextFloat() * doubleColorShift - maxColorShift;
/* 296 */         sparkle.func_70538_b(red + colorshiftR, green + colorshiftG, blue + colorshiftB);
/* 297 */         sparkle.func_82338_g(0.1F);
/*     */         
/* 299 */         net.minecraft.client.Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 311 */     super.func_70037_a(nbtRoot);
/* 312 */     setImpact(nbtRoot.func_74767_n("Impacted"));
/* 313 */     this.blockPlaced = nbtRoot.func_74767_n("BlockPlaced");
/* 314 */     if (this.blockPlaced) {
/* 315 */       this.blockX = nbtRoot.func_74762_e("BlockPlacedX");
/* 316 */       this.blockY = nbtRoot.func_74762_e("BlockPlacedY");
/* 317 */       this.blockZ = nbtRoot.func_74762_e("BlockPlacedZ");
/*     */     }
/* 319 */     if (nbtRoot.func_74764_b("Mode")) {
/* 320 */       setMode(nbtRoot.func_74762_e("Mode"));
/*     */     }
/*     */     
/* 323 */     if (nbtRoot.func_74764_b("Owner")) {
/* 324 */       this.owner = nbtRoot.func_74779_i("Owner");
/*     */     } else {
/* 326 */       this.owner = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 332 */     super.func_70014_b(nbtRoot);
/* 333 */     nbtRoot.func_74757_a("Impacted", getImpact());
/* 334 */     if (this.blockPlaced) {
/* 335 */       nbtRoot.func_74757_a("BlockPlaced", this.blockPlaced);
/* 336 */       nbtRoot.func_74768_a("BlockPlacedX", this.blockX);
/* 337 */       nbtRoot.func_74768_a("BlockPlacedY", this.blockY);
/* 338 */       nbtRoot.func_74768_a("BlockPlacedZ", this.blockZ);
/*     */     }
/* 340 */     nbtRoot.func_74768_a("Mode", getMode());
/* 341 */     if (this.owner != null) {
/* 342 */       nbtRoot.func_74778_a("Owner", this.owner);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityGrenade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */