/*      */ package com.emoniph.witchery.entity;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockCircle;
/*      */ import com.emoniph.witchery.blocks.BlockCircleGlyph;
/*      */ import com.emoniph.witchery.brewing.potions.PotionEnslaved;
/*      */ import com.emoniph.witchery.familiar.Familiar;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.Brew;
/*      */ import com.emoniph.witchery.item.ItemGeneral.Brew.BrewResult;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketPushTarget;
/*      */ import com.emoniph.witchery.util.BlockProtect;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.EarthItems;
/*      */ import com.emoniph.witchery.util.EntityUtil;
/*      */ import com.emoniph.witchery.util.Log;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.DataWatcher;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.boss.EntityDragon;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityCreeper;
/*      */ import net.minecraft.entity.monster.EntityPigZombie;
/*      */ import net.minecraft.entity.monster.EntityZombie;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityBat;
/*      */ import net.minecraft.entity.passive.EntityVillager;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.projectile.EntityThrowable;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class EntityWitchProjectile extends EntityThrowable
/*      */ {
/*      */   private int damageValue;
/*      */   
/*      */   public EntityWitchProjectile(World world)
/*      */   {
/*   64 */     super(world);
/*      */   }
/*      */   
/*      */   public EntityWitchProjectile(World world, EntityLivingBase entityLiving, ItemGeneral.SubItem generalSubItem) {
/*   68 */     super(world, entityLiving);
/*   69 */     setDamageValue(generalSubItem.damageValue);
/*      */   }
/*      */   
/*      */   public EntityWitchProjectile(World world, double posX, double posY, double posZ, ItemGeneral.SubItem generalSubItem) {
/*   73 */     super(world, posX, posY, posZ);
/*   74 */     setDamageValue(generalSubItem.damageValue);
/*      */   }
/*      */   
/*      */   protected void func_70088_a()
/*      */   {
/*   79 */     this.field_70180_af.func_75682_a(6, Integer.valueOf(0));
/*   80 */     super.func_70088_a();
/*      */   }
/*      */   
/*      */   public void setDamageValue(int damageValue) {
/*   84 */     this.damageValue = damageValue;
/*   85 */     func_70096_w().func_75692_b(6, Integer.valueOf(damageValue));
/*      */   }
/*      */   
/*      */   public int getDamageValue() {
/*   89 */     return func_70096_w().func_75679_c(6);
/*      */   }
/*      */   
/*      */   public boolean isPotion() {
/*   93 */     return ((Witchery.Items.GENERIC.subItems.get(this.damageValue) instanceof ItemGeneral.Brew)) || (Witchery.Items.GENERIC.itemQuicklime.damageValue == this.damageValue);
/*      */   }
/*      */   
/*      */   protected float func_70185_h()
/*      */   {
/*   98 */     if (isPotion()) {
/*   99 */       return 0.05F;
/*      */     }
/*  101 */     return super.func_70185_h();
/*      */   }
/*      */   
/*      */ 
/*      */   protected float func_70182_d()
/*      */   {
/*  107 */     if (isPotion()) {
/*  108 */       return 0.5F;
/*      */     }
/*  110 */     return super.func_70182_d();
/*      */   }
/*      */   
/*      */ 
/*      */   protected float func_70183_g()
/*      */   {
/*  116 */     if (isPotion()) {
/*  117 */       return -20.0F;
/*      */     }
/*  119 */     return super.func_70183_g();
/*      */   }
/*      */   
/*      */ 
/*  123 */   private boolean skipFX = false;
/*      */   private static final String DAMAGE_VALUE_KEY = "damageValue";
/*      */   
/*      */   protected void func_70184_a(MovingObjectPosition mop) {
/*  127 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  129 */       if (mop != null) {
/*  130 */         boolean enhanced = false;
/*  131 */         EntityLivingBase thrower = func_85052_h();
/*      */         
/*  133 */         if ((thrower != null) && ((thrower instanceof EntityPlayer))) {
/*  134 */           enhanced = Familiar.hasActiveBrewMasteryFamiliar((EntityPlayer)thrower);
/*      */         }
/*      */         
/*  137 */         this.skipFX = false;
/*  138 */         if (Witchery.Items.GENERIC.itemBrewOfVines.damageValue == this.damageValue) {
/*  139 */           impactVines(mop, enhanced);
/*  140 */         } else if (Witchery.Items.GENERIC.itemBrewOfThorns.damageValue == this.damageValue) {
/*  141 */           impactThorns(mop, enhanced);
/*  142 */         } else if (Witchery.Items.GENERIC.itemBrewOfWebs.damageValue == this.damageValue) {
/*  143 */           impactWebBig(mop, enhanced);
/*  144 */         } else if (Witchery.Items.GENERIC.itemBrewOfInk.damageValue == this.damageValue) {
/*  145 */           impactInk(mop, enhanced);
/*  146 */         } else if (Witchery.Items.GENERIC.itemBrewOfWasting.damageValue == this.damageValue) {
/*  147 */           impactWasting(mop, enhanced);
/*  148 */         } else if (Witchery.Items.GENERIC.itemBrewOfSprouting.damageValue == this.damageValue) {
/*  149 */           impactSprout(mop, enhanced);
/*  150 */         } else if (Witchery.Items.GENERIC.itemBrewOfErosion.damageValue == this.damageValue) {
/*  151 */           impactErosion(mop, enhanced);
/*  152 */         } else if (Witchery.Items.GENERIC.itemBrewOfLove.damageValue == this.damageValue) {
/*  153 */           impactLove(mop, enhanced);
/*  154 */         } else if (Witchery.Items.GENERIC.itemWeb.damageValue == this.damageValue) {
/*  155 */           impactWebSmall(mop);
/*  156 */           this.skipFX = true;
/*  157 */         } else if (Witchery.Items.GENERIC.itemRock.damageValue == this.damageValue) {
/*  158 */           impactRock(mop);
/*  159 */           this.skipFX = true;
/*  160 */         } else if (Witchery.Items.GENERIC.itemBrewOfRaising.damageValue == this.damageValue) {
/*  161 */           impactRaising(mop);
/*  162 */         } else if (Witchery.Items.GENERIC.itemQuicklime.damageValue == this.damageValue) {
/*  163 */           impactQuicklime(mop);
/*  164 */         } else if (Witchery.Items.GENERIC.itemBrewOfIce.damageValue == this.damageValue) {
/*  165 */           impactIce(mop);
/*  166 */         } else if (Witchery.Items.GENERIC.itemBrewOfFrogsTongue.damageValue == this.damageValue) {
/*  167 */           impactFrogsTongue(mop, false);
/*  168 */         } else if (Witchery.Items.GENERIC.itemBrewOfCursedLeaping.damageValue == this.damageValue) {
/*  169 */           impactLeaping(mop, false);
/*  170 */         } else if (Witchery.Items.GENERIC.itemBrewOfHitchcock.damageValue == this.damageValue) {
/*  171 */           impactHitchcock(mop);
/*  172 */         } else if (Witchery.Items.GENERIC.itemBrewOfInfection.damageValue == this.damageValue) {
/*  173 */           impactInfection(mop, enhanced);
/*  174 */         } else if (Witchery.Items.GENERIC.itemBrewOfBats.damageValue == this.damageValue) {
/*  175 */           impactBats(mop, enhanced);
/*      */         } else {
/*  177 */           ItemGeneral.SubItem item = (ItemGeneral.SubItem)Witchery.Items.GENERIC.subItems.get(this.damageValue);
/*  178 */           if ((item instanceof ItemGeneral.Brew)) {
/*  179 */             ItemGeneral.Brew brew = (ItemGeneral.Brew)item;
/*  180 */             ItemGeneral.Brew.BrewResult result = brew.onImpact(this.field_70170_p, thrower, mop, enhanced, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70121_D);
/*      */             
/*  182 */             if (result == ItemGeneral.Brew.BrewResult.DROP_ITEM) {
/*  183 */               EntityItem itemEntity = null;
/*  184 */               if (mop != null) {
/*  185 */                 ItemStack newBrewStack = brew.createStack();
/*  186 */                 switch (mop.field_72313_a) {
/*      */                 case BLOCK: 
/*  188 */                   itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/*  189 */                   break;
/*      */                 case ENTITY: 
/*  191 */                   itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/*  192 */                   break;
/*      */                 }
/*      */                 
/*      */               }
/*      */               
/*  197 */               this.skipFX = true;
/*  198 */               if (itemEntity != null) {
/*  199 */                 this.field_70170_p.func_72838_d(itemEntity);
/*      */               }
/*      */             } else {
/*  202 */               this.skipFX = (result == ItemGeneral.Brew.BrewResult.HIDE_EFFECT);
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  207 */         if (!this.skipFX) {
/*  208 */           this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), 2);
/*      */         }
/*      */       }
/*      */     }
/*  212 */     func_70106_y();
/*      */   }
/*      */   
/*      */   private void impactBats(MovingObjectPosition mop, boolean enhanced) {
/*  216 */     switch (mop.field_72313_a) {
/*      */     case BLOCK: 
/*  218 */       explodeBats(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, enhanced);
/*  219 */       break;
/*      */     case ENTITY: 
/*  221 */       int x = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/*  222 */       int y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/*  223 */       int z = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/*  224 */       explodeBats(this.field_70170_p, x, y, z, -1, enhanced);
/*  225 */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  230 */     double RADIUS = enhanced ? 4.0D : 3.0D;
/*      */     
/*  232 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
/*  233 */     List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*      */     
/*  235 */     if ((list1 != null) && (!list1.isEmpty())) {
/*  236 */       Iterator iterator = list1.iterator();
/*      */       
/*  238 */       while (iterator.hasNext()) {
/*  239 */         EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/*  240 */         double d0 = entitylivingbase.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */         
/*  242 */         if (d0 < RADIUS * RADIUS) {
/*  243 */           double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
/*      */           
/*  245 */           if (entitylivingbase == mop.field_72308_g) {
/*  246 */             d1 = 1.0D;
/*      */           }
/*      */           
/*  249 */           int j = (int)(d1 * 100.0D + 0.5D);
/*      */           
/*  251 */           entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, j, 5));
/*  252 */           if ((entitylivingbase instanceof EntityLiving)) {
/*  253 */             EntityUtil.dropAttackTarget((EntityLiving)entitylivingbase);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void explodeBats(World world, int posX, int posY, int posZ, int side, boolean enhanced) {
/*  261 */     int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
/*  262 */     int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
/*  263 */     int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0);
/*  264 */     if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
/*  265 */       y--;
/*      */     }
/*  267 */     int NUM_BATS = enhanced ? 14 : 10;
/*  268 */     for (int i = 0; i < NUM_BATS; i++) {
/*  269 */       EntityBat bat = new EntityBat(world);
/*  270 */       NBTTagCompound nbtBat = bat.getEntityData();
/*  271 */       nbtBat.func_74757_a("WITCNoDrops", true);
/*  272 */       bat.func_70012_b(x, y, z, 0.0F, 0.0F);
/*  273 */       this.field_70170_p.func_72838_d(bat);
/*      */     }
/*  275 */     ParticleEffect.LARGE_EXPLODE.send(SoundEffect.MOB_ENDERMEN_PORTAL, world, 0.5D + x, 0.5D + y, 0.5D + z, 3.0D, 3.0D, 16);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void impactInfection(MovingObjectPosition mop, boolean enhanced)
/*      */   {
/*  303 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  304 */       Block blockID = this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  305 */       int blockMeta = this.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  306 */       if (((blockID == Blocks.field_150348_b) || (blockID == Blocks.field_150347_e) || ((blockID == Blocks.field_150417_aV) && (blockMeta == 0))) && (BlockProtect.canBreak(mop.field_72311_b, mop.field_72309_d, mop.field_72312_c, this.field_70170_p))) {
/*  307 */         if (blockID == Blocks.field_150348_b) {
/*  308 */           this.field_70170_p.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150418_aU, 0, 3);
/*  309 */         } else if (blockID == Blocks.field_150347_e) {
/*  310 */           this.field_70170_p.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150418_aU, 1, 3);
/*  311 */         } else if (blockID == Blocks.field_150417_aV) {
/*  312 */           this.field_70170_p.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150418_aU, 2, 3);
/*      */         }
/*  314 */         return;
/*      */       }
/*  316 */     } else if ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && 
/*  317 */       ((mop.field_72308_g instanceof EntityLivingBase))) {
/*  318 */       EntityLivingBase entity = (EntityLivingBase)mop.field_72308_g;
/*      */       
/*  320 */       if ((entity instanceof EntityVillager)) {
/*  321 */         EntityZombie entityzombie = new EntityZombie(this.field_70170_p);
/*  322 */         entityzombie.func_82149_j(entity);
/*  323 */         this.field_70170_p.func_72900_e(entity);
/*  324 */         entityzombie.func_110161_a((IEntityLivingData)null);
/*  325 */         entityzombie.func_82229_g(true);
/*  326 */         if (entity.func_70631_g_()) {
/*  327 */           entityzombie.func_82227_f(true);
/*      */         }
/*  329 */         this.field_70170_p.func_72838_d(entityzombie);
/*  330 */         this.field_70170_p.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
/*      */       } else {
/*  332 */         float WORM_DAMAGE = enhanced ? 4.0F : 1.0F;
/*      */         
/*  334 */         entity.func_70097_a(DamageSource.func_76356_a(entity, func_85052_h()), WORM_DAMAGE);
/*  335 */         entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 8));
/*      */       }
/*  337 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  342 */     EntityItem itemEntity = null;
/*  343 */     if (mop != null) {
/*  344 */       ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfInfection.createStack();
/*  345 */       switch (mop.field_72313_a) {
/*      */       case BLOCK: 
/*  347 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/*  348 */         break;
/*      */       case ENTITY: 
/*  350 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/*  351 */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*  356 */     this.skipFX = true;
/*  357 */     if (itemEntity != null) {
/*  358 */       this.field_70170_p.func_72838_d(itemEntity);
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactHitchcock(MovingObjectPosition mop) {
/*  363 */     if ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase))) {
/*  364 */       EntityLivingBase victim = (EntityLivingBase)mop.field_72308_g;
/*  365 */       int BIRDS = this.field_70170_p.field_73012_v.nextInt(2) + 3;
/*      */       
/*  367 */       for (int i = 0; i < BIRDS; i++) {
/*  368 */         EntityOwl owl = new EntityOwl(this.field_70170_p);
/*  369 */         owl.func_70012_b(victim.field_70165_t - 2.0D + this.field_70170_p.field_73012_v.nextInt(5), victim.field_70163_u + victim.field_70131_O + 1.0D + this.field_70170_p.field_73012_v.nextInt(2), victim.field_70161_v - 2.0D + this.field_70170_p.field_73012_v.nextInt(5), 0.0F, 0.0F);
/*  370 */         owl.func_70624_b(victim);
/*  371 */         owl.setTimeToLive(400);
/*  372 */         this.field_70170_p.func_72838_d(owl);
/*      */         
/*  374 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, owl, 1.0D, 1.0D, 16);
/*      */       }
/*      */     }
/*      */     else {
/*  378 */       EntityItem itemEntity = null;
/*  379 */       if (mop != null) {
/*  380 */         ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfHitchcock.createStack();
/*  381 */         switch (mop.field_72313_a) {
/*      */         case BLOCK: 
/*  383 */           itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/*  384 */           break;
/*      */         case ENTITY: 
/*  386 */           itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/*  387 */           break;
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  392 */       this.skipFX = true;
/*  393 */       if (itemEntity != null) {
/*  394 */         this.field_70170_p.func_72838_d(itemEntity);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactLeaping(MovingObjectPosition mop, boolean enhanced) {
/*  400 */     Entity livingEntity = mop.field_72308_g;
/*      */     
/*  402 */     double RADIUS = enhanced ? 6.0D : 5.0D;
/*      */     
/*  404 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
/*  405 */     List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*      */     
/*  407 */     if ((list1 != null) && (!list1.isEmpty())) {
/*  408 */       Iterator iterator = list1.iterator();
/*      */       
/*  410 */       while (iterator.hasNext()) {
/*  411 */         EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/*  412 */         double d0 = entitylivingbase.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */         
/*  414 */         if (d0 < RADIUS * RADIUS) {
/*  415 */           double d1 = 1.0D - 0.5D * (Math.sqrt(d0) / RADIUS / 2.0D);
/*      */           
/*  417 */           if (entitylivingbase == livingEntity) {
/*  418 */             d1 = 1.0D;
/*      */           }
/*      */           
/*  421 */           int j = (int)(d1 * 400.0D + 0.5D);
/*      */           
/*  423 */           double LEAP = d1 * 1.6D;
/*      */           
/*  425 */           entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 200, 3));
/*  426 */           if ((entitylivingbase instanceof EntityPlayer)) {
/*  427 */             Witchery.packetPipeline.sendTo(new PacketPushTarget(entitylivingbase.field_70159_w, entitylivingbase.field_70181_x + LEAP, entitylivingbase.field_70179_y), (EntityPlayer)entitylivingbase);
/*      */           } else {
/*  429 */             entitylivingbase.field_70181_x += LEAP;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactFrogsTongue(MovingObjectPosition mop, boolean enhanced) {
/*  437 */     if ((!this.field_70170_p.field_72995_K) && (func_85052_h() != null)) {
/*  438 */       double RADIUS = enhanced ? 5.0D : 4.0D;
/*  439 */       double RADIUS_SQ = RADIUS * RADIUS;
/*  440 */       EntityLivingBase thrower = func_85052_h();
/*      */       
/*  442 */       boolean pulled = false;
/*      */       
/*  444 */       AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
/*  445 */       List entityLivingList = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*  446 */       if ((entityLivingList != null) && (!entityLivingList.isEmpty())) {
/*  447 */         Iterator iterator = entityLivingList.iterator();
/*  448 */         while (iterator.hasNext()) {
/*  449 */           EntityLivingBase livingEntity = (EntityLivingBase)iterator.next();
/*  450 */           double distanceSq = livingEntity.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */           
/*  452 */           if ((distanceSq < RADIUS_SQ) && (livingEntity != func_85052_h())) {
/*  453 */             pull(this.field_70170_p, livingEntity, thrower.field_70165_t, thrower.field_70163_u, thrower.field_70161_v, 0.05D, 0.0D);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void pull(World world, Entity entity, double posX, double posY, double posZ, double dy, double yy)
/*      */   {
/*  505 */     if (((entity instanceof EntityDragon)) || ((entity instanceof EntityHornedHuntsman))) {
/*  506 */       return;
/*      */     }
/*      */     
/*  509 */     double d = posX - entity.field_70165_t;
/*  510 */     double d1 = posY - entity.field_70163_u;
/*  511 */     double d2 = posZ - entity.field_70161_v;
/*      */     
/*      */ 
/*  514 */     float distance = MathHelper.func_76133_a(d * d + d1 * d1 + d2 * d2);
/*  515 */     float f2 = 0.1F + (float)dy;
/*      */     
/*  517 */     double mx = d / distance * f2 * distance;
/*      */     
/*  519 */     double my = yy == 0.0D ? 0.4D : d1 / distance * distance * 0.2D + 0.2D + yy;
/*  520 */     double mz = d2 / distance * f2 * distance;
/*      */     
/*  522 */     if ((entity instanceof EntityLivingBase)) {
/*  523 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 20, 1));
/*      */     }
/*      */     
/*  526 */     if ((entity instanceof EntityPlayer)) {
/*  527 */       Witchery.packetPipeline.sendTo(new PacketPushTarget(mx, my, mz), (EntityPlayer)entity);
/*      */     } else {
/*  529 */       entity.field_70159_w = mx;
/*  530 */       entity.field_70181_x = my;
/*  531 */       entity.field_70179_y = mz;
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactIce(MovingObjectPosition mop) {
/*  536 */     switch (mop.field_72313_a) {
/*      */     case BLOCK: 
/*  538 */       int FREEZE_RANGE = 3;
/*  539 */       if ((this.field_70170_p.func_147439_a(mop.field_72311_b + 1, mop.field_72312_c, mop.field_72309_d).func_149688_o() == Material.field_151586_h) || (this.field_70170_p.func_147439_a(mop.field_72311_b - 1, mop.field_72312_c, mop.field_72309_d).func_149688_o() == Material.field_151586_h) || (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d).func_149688_o() == Material.field_151586_h) || (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c - 1, mop.field_72309_d).func_149688_o() == Material.field_151586_h) || (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d + 1).func_149688_o() == Material.field_151586_h) || (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d - 1).func_149688_o() == Material.field_151586_h))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  545 */         freezeSurroundingWater(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, 3);
/*  546 */         return;
/*      */       }
/*      */       
/*  549 */       int SHIELD_HEIGHT = 3;
/*  550 */       if (mop.field_72310_e == 1) {
/*  551 */         explodeIceShield(this.field_70170_p, func_85052_h(), mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, 3);
/*  552 */         return; }
/*  553 */       if (mop.field_72310_e != 0) {
/*  554 */         int b0 = 0;
/*      */         
/*  556 */         switch (mop.field_72310_e) {
/*      */         case 0: 
/*      */         case 1: 
/*  559 */           b0 = 0;
/*  560 */           break;
/*      */         case 2: 
/*      */         case 3: 
/*  563 */           b0 = 8;
/*  564 */           break;
/*      */         case 4: 
/*      */         case 5: 
/*  567 */           b0 = 4;
/*      */         }
/*      */         
/*  570 */         int dx = mop.field_72310_e == 4 ? -1 : mop.field_72310_e == 5 ? 1 : 0;
/*  571 */         int dy = mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0;
/*  572 */         int dz = mop.field_72310_e == 2 ? -1 : mop.field_72310_e == 3 ? 1 : 0;
/*      */         
/*  574 */         explodeIceShield(this.field_70170_p, func_85052_h(), mop.field_72311_b + dx, mop.field_72312_c + dy, mop.field_72309_d + dz, 3);
/*  575 */         return;
/*      */       }
/*      */       
/*  578 */       EntityItem itemEntity = null;
/*  579 */       if (mop != null) {
/*  580 */         ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfIce.createStack();
/*  581 */         switch (mop.field_72313_a) {
/*      */         case BLOCK: 
/*  583 */           itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/*  584 */           break;
/*      */         case ENTITY: 
/*  586 */           itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/*  587 */           break;
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  592 */       this.skipFX = true;
/*  593 */       if (itemEntity != null) {
/*  594 */         this.field_70170_p.func_72838_d(itemEntity);
/*      */       }
/*      */       break;
/*      */     case ENTITY: 
/*  598 */       int x = (int)Math.round(mop.field_72308_g.field_70165_t);
/*  599 */       int y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/*  600 */       int z = (int)Math.round(mop.field_72308_g.field_70161_v);
/*  601 */       explodeIceBlock(this.field_70170_p, x, y, z, -1, mop.field_72308_g);
/*  602 */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   private void freezeSurroundingWater(World world, int x, int y, int z, int x0, int y0, int z0, int range)
/*      */   {
/*  609 */     if ((Math.abs(x0 - x) >= range) || (Math.abs(y0 - y) >= range) || (Math.abs(z0 - z) >= range)) {
/*  610 */       return;
/*      */     }
/*  612 */     if (freezeWater(world, x + 1, y, z)) {
/*  613 */       freezeSurroundingWater(world, x + 1, y, z, x0, y0, z0, range);
/*      */     }
/*      */     
/*  616 */     if (freezeWater(world, x - 1, y, z)) {
/*  617 */       freezeSurroundingWater(world, x - 1, y, z, x0, y0, z0, range);
/*      */     }
/*      */     
/*  620 */     if (freezeWater(world, x, y, z + 1)) {
/*  621 */       freezeSurroundingWater(world, x, y, z + 1, x0, y0, z0, range);
/*      */     }
/*      */     
/*  624 */     if (freezeWater(world, x, y, z - 1)) {
/*  625 */       freezeSurroundingWater(world, x, y, z - 1, x0, y0, z0, range);
/*      */     }
/*      */     
/*  628 */     if (freezeWater(world, x, y + 1, z)) {
/*  629 */       freezeSurroundingWater(world, x, y + 1, z, x0, y0, z0, range);
/*      */     }
/*      */     
/*  632 */     if (freezeWater(world, x, y - 1, z)) {
/*  633 */       freezeSurroundingWater(world, x, y - 1, z, x0, y0, z0, range);
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean freezeWater(World world, int x, int y, int z) {
/*  638 */     if (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) {
/*  639 */       world.func_147449_b(x, y, z, Blocks.field_150432_aD);
/*  640 */       return true;
/*      */     }
/*  642 */     return false;
/*      */   }
/*      */   
/*      */   public static void explodeIceBlock(World world, int posX, int posY, int posZ, int side, Entity entity) {
/*  646 */     int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
/*  647 */     int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
/*  648 */     int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0) - 1;
/*  649 */     if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
/*  650 */       y--;
/*      */     }
/*      */     
/*  653 */     Block block = Blocks.field_150432_aD;
/*  654 */     boolean resistent = ((entity instanceof EntityDemon)) || ((entity instanceof net.minecraft.entity.monster.EntityBlaze)) || ((entity instanceof EntityDragon)) || ((entity instanceof EntityHornedHuntsman)) || ((entity instanceof EntityEnt)) || ((entity instanceof net.minecraft.entity.boss.EntityWither)) || ((entity instanceof net.minecraft.entity.monster.EntityIronGolem));
/*      */     
/*  656 */     if (resistent) {
/*  657 */       setBlockIfNotSolid(world, x, y + 1, z, Blocks.field_150358_i);
/*      */     } else {
/*  659 */       int HEIGHT = resistent ? 2 : 4;
/*  660 */       for (int i = 0; i < HEIGHT; i++) {
/*  661 */         setBlockIfNotSolid(world, x - 2, y + i, z - 1, block);
/*  662 */         setBlockIfNotSolid(world, x - 2, y + i, z, block);
/*  663 */         setBlockIfNotSolid(world, x - 1, y + i, z + 1, block);
/*  664 */         setBlockIfNotSolid(world, x, y + i, z + 1, block);
/*  665 */         setBlockIfNotSolid(world, x + 1, y + i, z, block);
/*  666 */         setBlockIfNotSolid(world, x + 1, y + i, z - 1, block);
/*      */         
/*  668 */         setBlockIfNotSolid(world, x, y + i, z - 2, block);
/*  669 */         setBlockIfNotSolid(world, x - 1, y + i, z - 2, block);
/*      */         
/*  671 */         setBlockIfNotSolid(world, x - 2, y + i, z - 2, block);
/*  672 */         setBlockIfNotSolid(world, x - 2, y + i, z + 1, block);
/*  673 */         setBlockIfNotSolid(world, x + 1, y + i, z + 1, block);
/*  674 */         setBlockIfNotSolid(world, x + 1, y + i, z - 2, block);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  686 */       setBlockIfNotSolid(world, x, y, z, block);
/*  687 */       if (!resistent) {
/*  688 */         setBlockIfNotSolid(world, x, y + HEIGHT - 1, z, block);
/*  689 */         setBlockIfNotSolid(world, x - 1, y + HEIGHT - 1, z - 1, block);
/*  690 */         setBlockIfNotSolid(world, x - 1, y + HEIGHT - 1, z, block);
/*  691 */         setBlockIfNotSolid(world, x, y + HEIGHT - 1, z - 1, block);
/*      */       }
/*      */       
/*  694 */       if ((entity instanceof EntityCreeper)) {
/*  695 */         EntityCreeper creeper = (EntityCreeper)entity;
/*  696 */         boolean flag = world.func_82736_K().func_82766_b("mobGriefing");
/*      */         
/*  698 */         if (creeper.func_70830_n())
/*      */         {
/*  700 */           world.func_72876_a(creeper, creeper.field_70165_t, creeper.field_70163_u, creeper.field_70161_v, 6.0F, flag);
/*      */         }
/*      */         else
/*      */         {
/*  704 */           world.func_72876_a(creeper, creeper.field_70165_t, creeper.field_70163_u, creeper.field_70161_v, 3.0F, flag);
/*      */         }
/*      */         
/*  707 */         creeper.func_70106_y();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static void explodeIceShield(World world, EntityLivingBase player, int posX, int posY, int posZ, int height)
/*      */   {
/*  714 */     double f1 = player != null ? MathHelper.func_76134_b(-player.field_70177_z * 0.017453292F - 3.1415927F) : 0.0D;
/*  715 */     double f2 = player != null ? MathHelper.func_76126_a(-player.field_70177_z * 0.017453292F - 3.1415927F) : 0.0D;
/*  716 */     Vec3 loc = Vec3.func_72443_a(f2, 0.0D, f1);
/*      */     
/*  718 */     if (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) {
/*  719 */       posY--;
/*      */     }
/*      */     
/*  722 */     explodeIceColumn(world, posX, posY + 1, posZ, height);
/*      */     
/*  724 */     loc.func_72442_b((float)Math.toRadians(90.0D));
/*  725 */     int newX = MathHelper.func_76128_c(posX + 0.5D + loc.field_72450_a * 1.0D);
/*  726 */     int newZ = MathHelper.func_76128_c(posZ + 0.5D + loc.field_72449_c * 1.0D);
/*  727 */     explodeIceColumn(world, newX, posY + 1, newZ, height);
/*      */     
/*  729 */     loc.func_72442_b((float)Math.toRadians(180.0D));
/*  730 */     newX = MathHelper.func_76128_c(posX + 0.5D + loc.field_72450_a * 1.0D);
/*  731 */     newZ = MathHelper.func_76128_c(posZ + 0.5D + loc.field_72449_c * 1.0D);
/*  732 */     explodeIceColumn(world, newX, posY + 1, newZ, height);
/*      */   }
/*      */   
/*      */   public static void explodeIceColumn(World world, int posX, int posY, int posZ, int height) {
/*  736 */     for (int offsetPosY = posY; offsetPosY < posY + height; offsetPosY++) {
/*  737 */       setBlockIfNotSolid(world, posX, offsetPosY, posZ, Blocks.field_150432_aD);
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactLove(MovingObjectPosition mop, boolean enhanced) {
/*  742 */     double RADIUS = enhanced ? 5.0D : 4.0D;
/*  743 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
/*  744 */     List list1 = this.field_70170_p.func_72872_a(EntityLiving.class, axisalignedbb);
/*      */     
/*  746 */     if ((list1 != null) && (!list1.isEmpty()) && (!this.field_70170_p.field_72995_K))
/*      */     {
/*  748 */       EntityLivingBase entityThrower = func_85052_h();
/*  749 */       EntityPlayer thrower = (entityThrower != null) && ((entityThrower instanceof EntityPlayer)) ? (EntityPlayer)entityThrower : null;
/*      */       
/*  751 */       Iterator iterator = list1.iterator();
/*      */       
/*  753 */       ArrayList<EntityVillager> villagers = new ArrayList();
/*  754 */       ArrayList<EntityZombie> zombies = new ArrayList();
/*  755 */       while (iterator.hasNext()) {
/*  756 */         EntityLiving entitylivingbase = (EntityLiving)iterator.next();
/*  757 */         double d0 = entitylivingbase.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */         
/*  759 */         if (d0 < RADIUS * RADIUS) {
/*  760 */           double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
/*      */           
/*  762 */           if (entitylivingbase == mop.field_72308_g) {
/*  763 */             d1 = 1.0D;
/*      */           }
/*      */           
/*  766 */           int j = (int)(d1 * 400.0D + 0.5D);
/*      */           
/*  768 */           if ((entitylivingbase instanceof EntityAnimal)) {
/*  769 */             EntityAnimal animal = (EntityAnimal)entitylivingbase;
/*  770 */             if (animal.func_70874_b() >= 0) {
/*  771 */               animal.func_70873_a(0);
/*  772 */               animal.func_146082_f(null);
/*      */             }
/*  774 */           } else if ((entitylivingbase instanceof EntityVillager)) {
/*  775 */             EntityVillager villager = (EntityVillager)entitylivingbase;
/*  776 */             if (villager.func_70874_b() >= 0) {
/*  777 */               villagers.add(villager);
/*      */             }
/*  779 */           } else if ((entitylivingbase instanceof EntityZombie))
/*      */           {
/*  781 */             EntityZombie zombie = (EntityZombie)entitylivingbase;
/*  782 */             if ((!zombie.func_70631_g_()) && (thrower != null)) {
/*  783 */               NBTTagCompound nbt = zombie.getEntityData();
/*  784 */               if (PotionEnslaved.isMobEnslavedBy(zombie, thrower)) {
/*  785 */                 zombies.add(zombie);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  792 */       int limit = 10;
/*  793 */       while ((villagers.size() > 1) && (limit-- > 0)) {
/*  794 */         EntityVillager villager = (EntityVillager)villagers.get(0);
/*  795 */         EntityVillager mate = (EntityVillager)villagers.get(1);
/*  796 */         villager.func_70107_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v);
/*  797 */         ParticleEffect.HEART.send(SoundEffect.NONE, mate, 1.0D, 2.0D, 8);
/*      */         
/*  799 */         giveBirth(villager, mate);
/*  800 */         villagers.remove(0);
/*  801 */         villagers.remove(0);
/*      */       }
/*      */       
/*  804 */       limit = 10;
/*  805 */       while ((zombies.size() > 1) && (limit-- > 0)) {
/*  806 */         EntityZombie zombie = (EntityZombie)zombies.get(0);
/*  807 */         EntityZombie mate = (EntityZombie)zombies.get(1);
/*  808 */         zombie.func_70107_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v);
/*  809 */         ParticleEffect.HEART.send(SoundEffect.NONE, mate, 1.0D, 2.0D, 8);
/*  810 */         zombie.func_82229_g(true);
/*  811 */         mate.func_82229_g(true);
/*  812 */         EntityZombie baby = new EntityZombie(this.field_70170_p);
/*  813 */         baby.func_70012_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v, 0.0F, 0.0F);
/*  814 */         baby.func_82227_f(true);
/*  815 */         this.field_70170_p.func_72838_d(baby);
/*      */         
/*  817 */         zombies.remove(0);
/*  818 */         zombies.remove(0);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void giveBirth(EntityVillager villagerObj, EntityVillager mate) {
/*  824 */     EntityVillager entityvillager = villagerObj.func_90011_a(mate);
/*  825 */     mate.func_70873_a(6000);
/*  826 */     villagerObj.func_70873_a(6000);
/*  827 */     entityvillager.func_70873_a(41536);
/*  828 */     entityvillager.func_70012_b(villagerObj.field_70165_t, villagerObj.field_70163_u, villagerObj.field_70161_v, 0.0F, 0.0F);
/*  829 */     this.field_70170_p.func_72838_d(entityvillager);
/*  830 */     this.field_70170_p.func_72960_a(entityvillager, (byte)12);
/*      */   }
/*      */   
/*      */   private void impactQuicklime(MovingObjectPosition mop) {
/*  834 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)
/*      */     {
/*  836 */       if ((mop.field_72308_g instanceof EntityLivingBase)) {
/*  837 */         EntityLivingBase livingEntity = (EntityLivingBase)mop.field_72308_g;
/*  838 */         if (!livingEntity.func_70644_a(Potion.field_76440_q)) {
/*  839 */           livingEntity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 60, 0));
/*      */         }
/*  841 */         float DAMAGE = livingEntity.func_110143_aJ() == livingEntity.func_110138_aP() ? 0.5F : (mop.field_72308_g instanceof net.minecraft.entity.monster.EntitySlime) ? 4.0F : 0.1F;
/*  842 */         livingEntity.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), DAMAGE);
/*      */       }
/*      */       
/*      */ 
/*  846 */       this.skipFX = true;
/*  847 */       return;
/*      */     }
/*      */     
/*      */ 
/*  851 */     EntityItem itemEntity = null;
/*  852 */     if (mop != null) {
/*  853 */       ItemStack newBrewStack = Witchery.Items.GENERIC.itemQuicklime.createStack();
/*  854 */       switch (mop.field_72313_a) {
/*      */       case BLOCK: 
/*  856 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/*  857 */         break;
/*      */       case ENTITY: 
/*  859 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/*  860 */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*  865 */     this.skipFX = true;
/*  866 */     if (itemEntity != null) {
/*  867 */       this.field_70170_p.func_72838_d(itemEntity);
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactRaising(MovingObjectPosition mop) {
/*  872 */     if ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (mop.field_72310_e == 1))
/*      */     {
/*  874 */       int posX = mop.field_72311_b;
/*  875 */       int posY = mop.field_72312_c;
/*  876 */       int posZ = mop.field_72309_d;
/*  877 */       World world = this.field_70170_p;
/*      */       
/*  879 */       raiseDead(posX, posY, posZ, world, func_85052_h());
/*      */       
/*  881 */       return;
/*      */     }
/*      */     
/*      */ 
/*  885 */     EntityItem itemEntity = null;
/*  886 */     if (mop != null) {
/*  887 */       ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfRaising.createStack();
/*  888 */       switch (mop.field_72313_a) {
/*      */       case BLOCK: 
/*  890 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/*  891 */         break;
/*      */       case ENTITY: 
/*  893 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/*  894 */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*  899 */     this.skipFX = true;
/*  900 */     if (itemEntity != null) {
/*  901 */       this.field_70170_p.func_72838_d(itemEntity);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void raiseDead(int posX, int posY, int posZ, World world, EntityLivingBase raiser) {
/*  906 */     int y0 = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a() ? posY : posY - 1;
/*      */     
/*  908 */     int MAX_SPAWNS = 3;
/*  909 */     int MAX_DISTANCE = 3;
/*  910 */     int MAX_DROP = 6;
/*      */     
/*  912 */     EntityPlayer playerThrower = (EntityPlayer)((raiser instanceof EntityPlayer) ? raiser : null);
/*      */     
/*  914 */     raiseUndead(world, posX, y0, posZ, playerThrower);
/*      */     
/*  916 */     int extraCount = 0;
/*  917 */     double chance = world.field_73012_v.nextDouble();
/*  918 */     if (chance < 0.1D) {
/*  919 */       extraCount = 2;
/*  920 */     } else if (chance < 0.4D) {
/*  921 */       extraCount = 1;
/*      */     }
/*      */     
/*  924 */     for (int i = 0; i < extraCount; i++) {
/*  925 */       int x = posX - 3 + world.field_73012_v.nextInt(6) + 1;
/*  926 */       int z = posZ - 3 + world.field_73012_v.nextInt(6) + 1;
/*  927 */       int y = -1;
/*  928 */       for (int dy = -6; dy < 6; dy++) {
/*  929 */         if (world.func_147439_a(x, y0 - dy, z).func_149688_o().func_76220_a()) {
/*  930 */           y = y0 - dy;
/*  931 */           break;
/*      */         }
/*      */       }
/*  934 */       if (y != -1) {
/*  935 */         raiseUndead(world, x, y, z, playerThrower);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static void raiseUndead(World world, int posX, int posY, int posZ, EntityPlayer thrower) {
/*  941 */     if (!world.field_72995_K) {
/*  942 */       Block blockID = world.func_147439_a(posX, posY, posZ);
/*  943 */       if ((blockID != Blocks.field_150346_d) && (blockID != Blocks.field_150348_b) && (blockID != Blocks.field_150349_c) && (blockID != Blocks.field_150424_aL) && (blockID != Blocks.field_150391_bh) && (blockID != Blocks.field_150425_aM) && (blockID != Blocks.field_150347_e) && (blockID != Blocks.field_150351_n) && (blockID != Blocks.field_150354_m))
/*      */       {
/*      */ 
/*  946 */         posY++;
/*      */       }
/*  948 */       spawnParticles(world, ParticleEffect.SMOKE, 0.5D + posX, 0.5D + posY, 0.5D + posZ);
/*  949 */       world.func_147468_f(posX, posY, posZ);
/*  950 */       world.func_147468_f(posX, posY + 1, posZ);
/*  951 */       EntityLiving undeadEntity = createUndeadCreature(world);
/*  952 */       undeadEntity.func_70012_b(0.5D + posX, 0.5D + posY, 0.5D + posZ, 1.0F, 0.0F);
/*  953 */       IEntityLivingData entitylivingData = null;
/*  954 */       entitylivingData = undeadEntity.func_110161_a(entitylivingData);
/*  955 */       undeadEntity.func_110163_bv();
/*      */       
/*  957 */       if (thrower != null) {
/*      */         try {
/*  959 */           PotionEnslaved.setEnslaverForMob(undeadEntity, thrower);
/*      */         } catch (Exception e) {
/*  961 */           Log.instance().warning(e, "Unhandled exception occurred setting enslaver from raiseUnded potion.");
/*      */         }
/*      */       }
/*      */       
/*  965 */       world.func_72838_d(undeadEntity);
/*      */     }
/*      */   }
/*      */   
/*      */   private static EntityLiving createUndeadCreature(World world) {
/*  970 */     double value = world.field_73012_v.nextDouble();
/*  971 */     if (value < 0.6D)
/*  972 */       return new EntityZombie(world);
/*  973 */     if (value < 0.97D) {
/*  974 */       return new net.minecraft.entity.monster.EntitySkeleton(world);
/*      */     }
/*  976 */     return new EntityPigZombie(world);
/*      */   }
/*      */   
/*      */   private void impactErosion(MovingObjectPosition mop, boolean enhanced)
/*      */   {
/*  981 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  982 */       if (BlockProtect.checkModsForBreakOK(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c, func_85052_h())) {
/*  983 */         int RADIUS = 2;
/*  984 */         int obsidianMetled = 0;
/*  985 */         obsidianMetled += drawFilledCircle(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c, 2);
/*  986 */         for (int i = 0; i < 2; i++) {
/*  987 */           int dy = i + 1;
/*  988 */           obsidianMetled += drawFilledCircle(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c + dy, 2 - dy);
/*  989 */           obsidianMetled += drawFilledCircle(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c - dy, 2 - dy);
/*      */         }
/*  991 */         if (obsidianMetled > 0) {
/*  992 */           this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, 0.5D + mop.field_72311_b, 0.5D + mop.field_72312_c, 0.5D + mop.field_72309_d, new ItemStack(Blocks.field_150343_Z, obsidianMetled, 0)));
/*      */         }
/*      */       }
/*      */     }
/*  996 */     else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/*  997 */       if ((mop.field_72308_g instanceof EntityLivingBase)) {
/*  998 */         EntityLivingBase entity = (EntityLivingBase)mop.field_72308_g;
/*  999 */         float ACID_DAMAGE = enhanced ? 10.0F : 8.0F;
/*      */         
/* 1001 */         entity.func_70097_a(DamageSource.func_76356_a(entity, func_85052_h()), ACID_DAMAGE);
/* 1002 */         if ((entity instanceof EntityPlayer)) {
/* 1003 */           EntityPlayer player = (EntityPlayer)entity;
/* 1004 */           if (causeAcidDamage(entity.func_70694_bm())) {
/* 1005 */             player.func_71028_bD();
/*      */           }
/* 1007 */           for (int slot = 0; slot < player.field_71071_by.field_70460_b.length; slot++) {
/* 1008 */             if (causeAcidDamage(player.field_71071_by.field_70460_b[slot])) {
/* 1009 */               player.field_71071_by.field_70460_b[slot] = null;
/*      */             }
/*      */           }
/*      */         } else {
/* 1013 */           for (int slot = 0; slot < 5; slot++) {
/* 1014 */             if (causeAcidDamage(entity.func_71124_b(slot))) {
/* 1015 */               entity.func_70062_b(slot, null);
/*      */             }
/*      */           }
/*      */         }
/*      */       } else {
/* 1020 */         this.skipFX = true;
/* 1021 */         this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, Witchery.Items.GENERIC.itemBrewOfErosion.createStack()));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean causeAcidDamage(ItemStack itemstack)
/*      */   {
/* 1028 */     int ITEM_ACID_DAMAGE = 100;
/* 1029 */     if ((itemstack != null) && (itemstack.func_77984_f()) && (EarthItems.instance().isMatch(itemstack))) {
/* 1030 */       itemstack.func_77972_a(100, func_85052_h());
/* 1031 */       return itemstack.func_77960_j() <= 0;
/*      */     }
/* 1033 */     return false;
/*      */   }
/*      */   
/*      */   protected int drawFilledCircle(World world, int x0, int z0, int y, int radius) {
/* 1037 */     int x = radius;
/* 1038 */     int z = 0;
/* 1039 */     int radiusError = 1 - x;
/*      */     
/* 1041 */     int obsidianMelted = 0;
/* 1042 */     while (x >= z) {
/* 1043 */       obsidianMelted += drawLine(world, -x + x0, x + x0, z + z0, y);
/* 1044 */       obsidianMelted += drawLine(world, -z + x0, z + x0, x + z0, y);
/* 1045 */       obsidianMelted += drawLine(world, -x + x0, x + x0, -z + z0, y);
/* 1046 */       obsidianMelted += drawLine(world, -z + x0, z + x0, -x + z0, y);
/*      */       
/* 1048 */       z++;
/*      */       
/* 1050 */       if (radiusError < 0) {
/* 1051 */         radiusError += 2 * z + 1;
/*      */       } else {
/* 1053 */         x--;
/* 1054 */         radiusError += 2 * (z - x + 1);
/*      */       }
/*      */     }
/* 1057 */     return obsidianMelted;
/*      */   }
/*      */   
/*      */   protected int drawLine(World world, int x1, int x2, int z, int y) {
/* 1061 */     int obsidianMelted = 0;
/* 1062 */     for (int x = x1; x <= x2; x++)
/*      */     {
/* 1064 */       Block blockID = world.func_147439_a(x, y, z);
/* 1065 */       if (blockID == Blocks.field_150343_Z) {
/* 1066 */         obsidianMelted++;
/*      */       }
/* 1068 */       if ((blockID != Blocks.field_150350_a) && (blockID != Blocks.field_150353_l) && (blockID != Blocks.field_150356_k) && (blockID != Blocks.field_150480_ab) && (blockID != Blocks.field_150358_i) && (blockID != Blocks.field_150355_j) && (BlockProtect.canBreak(blockID, world)))
/*      */       {
/*      */ 
/* 1071 */         world.func_147468_f(x, y, z);
/* 1072 */         spawnParticles(this.field_70170_p, ParticleEffect.SPLASH, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */       }
/*      */     }
/* 1075 */     return obsidianMelted;
/*      */   }
/*      */   
/*      */   private void impactSprout(MovingObjectPosition mop, boolean enhanced) {
/* 1079 */     if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK))
/*      */     {
/* 1081 */       int posX = mop.field_72311_b;
/* 1082 */       int posY = mop.field_72312_c;
/* 1083 */       int posZ = mop.field_72309_d;
/* 1084 */       World world = this.field_70170_p;
/* 1085 */       int sideHit = mop.field_72310_e;
/*      */       
/* 1087 */       growBranch(posX, posY, posZ, world, sideHit, enhanced ? 20 : 15, this.field_70121_D);
/*      */     } else {
/* 1089 */       EntityItem itemEntity = null;
/* 1090 */       if (mop != null) {
/* 1091 */         ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfSprouting.createStack();
/* 1092 */         switch (mop.field_72313_a) {
/*      */         case BLOCK: 
/* 1094 */           itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/* 1095 */           break;
/*      */         case ENTITY: 
/* 1097 */           itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/* 1098 */           break;
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1103 */       this.skipFX = true;
/* 1104 */       if (itemEntity != null) {
/* 1105 */         this.field_70170_p.func_72838_d(itemEntity);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static void growBranch(int posX, int posY, int posZ, World world, int sideHit, int extent, AxisAlignedBB boundingBox)
/*      */   {
/* 1112 */     Block blockID = world.func_147439_a(posX, posY, posZ);
/* 1113 */     int j1 = world.func_72805_g(posX, posY, posZ);
/* 1114 */     Block logBlock; Block logBlock; if ((blockID == Blocks.field_150364_r) || (blockID == Blocks.field_150344_f) || (blockID == Blocks.field_150345_g) || (blockID == Blocks.field_150362_t)) {
/* 1115 */       logBlock = Blocks.field_150364_r; } else { Block logBlock;
/* 1116 */       if ((blockID == Witchery.Blocks.LOG) || (blockID == Witchery.Blocks.PLANKS) || (blockID == Witchery.Blocks.SAPLING) || (blockID == Witchery.Blocks.LEAVES))
/*      */       {
/* 1118 */         logBlock = Witchery.Blocks.LOG;
/*      */       } else {
/* 1120 */         logBlock = world.field_73012_v.nextInt(2) == 0 ? Blocks.field_150364_r : Witchery.Blocks.LOG;
/* 1121 */         j1 = world.field_73012_v.nextInt(Blocks.field_150364_r == logBlock ? 4 : 3);
/*      */       } }
/* 1123 */     Block leavesBlock = Blocks.field_150364_r == logBlock ? Blocks.field_150362_t : Witchery.Blocks.LEAVES;
/* 1124 */     int b0 = 0;
/* 1125 */     j1 &= 0x3;
/*      */     
/* 1127 */     switch (sideHit) {
/*      */     case 0: 
/*      */     case 1: 
/* 1130 */       b0 = 0;
/* 1131 */       break;
/*      */     case 2: 
/*      */     case 3: 
/* 1134 */       b0 = 8;
/* 1135 */       break;
/*      */     case 4: 
/*      */     case 5: 
/* 1138 */       b0 = 4;
/*      */     }
/*      */     
/* 1141 */     int meta = j1 | b0;
/*      */     
/* 1143 */     ParticleEffect particleEffect = ParticleEffect.EXPLODE;
/*      */     
/* 1145 */     int dx = sideHit == 4 ? -1 : sideHit == 5 ? 1 : 0;
/* 1146 */     int dy = sideHit == 1 ? 1 : sideHit == 0 ? -1 : 0;
/* 1147 */     int dz = sideHit == 2 ? -1 : sideHit == 3 ? 1 : 0;
/*      */     
/* 1149 */     int sproutExtent = extent;
/*      */     
/* 1151 */     boolean isInitialBlockSolid = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a();
/*      */     
/*      */ 
/* 1154 */     for (int i = (sideHit == 1) && (!isInitialBlockSolid) ? 0 : 1; i < sproutExtent; i++) {
/* 1155 */       int x = posX + i * dx;
/* 1156 */       int y = posY + i * dy;
/* 1157 */       int z = posZ + i * dz;
/* 1158 */       if (y >= 255) {
/*      */         break;
/*      */       }
/* 1161 */       if (!setBlockIfNotSolid(world, x, y, z, logBlock, meta)) {
/*      */         break;
/*      */       }
/* 1164 */       int lx = (dx == 0) && (world.field_73012_v.nextInt(4) == 0) ? world.field_73012_v.nextInt(3) - 1 : 0;
/* 1165 */       int ly = (dy == 0) && (lx == 0) && (world.field_73012_v.nextInt(4) == 0) ? world.field_73012_v.nextInt(3) - 1 : 0;
/* 1166 */       int lz = (dz == 0) && (lx == 0) && (ly == 0) && (world.field_73012_v.nextInt(4) == 0) ? world.field_73012_v.nextInt(3) - 1 : 0;
/* 1167 */       if ((lx != 0) || (ly != 0) || (lz != 0)) {
/* 1168 */         setBlockIfNotSolid(world, x + lx, y + ly, z + lz, leavesBlock, meta);
/*      */       }
/*      */     }
/*      */     
/* 1172 */     if (sideHit == 1) {
/* 1173 */       AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(0.0D, 2.0D, 0.0D);
/* 1174 */       List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
/* 1175 */       if ((list1 != null) && (!list1.isEmpty())) {
/* 1176 */         Iterator iterator = list1.iterator();
/* 1177 */         int x = posX + i * dx;
/* 1178 */         int y = Math.min(posY + i * dy, 255);
/* 1179 */         int z = posZ + i * dz;
/* 1180 */         while (iterator.hasNext()) {
/* 1181 */           EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/* 1182 */           if ((!world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a()) && (!world.func_147439_a(x, y + 2, z).func_149688_o().func_76220_a())) {
/* 1183 */             entitylivingbase.func_70107_b(0.5D + x, y + 1, 0.5D + z);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactWasting(MovingObjectPosition mop, boolean enhanced) {
/* 1191 */     Entity livingEntity = mop.field_72308_g;
/*      */     double z;
/*      */     double x;
/* 1194 */     double y; double z; if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/* 1195 */       double x = livingEntity.field_70165_t;
/* 1196 */       double y = livingEntity.field_70163_u;
/* 1197 */       z = livingEntity.field_70161_v;
/*      */     } else {
/* 1199 */       x = mop.field_72311_b;
/* 1200 */       y = mop.field_72312_c;
/* 1201 */       z = mop.field_72309_d;
/*      */     }
/* 1203 */     explodeWasting(this.field_70170_p, x, y, z, livingEntity, this.field_70121_D, enhanced);
/*      */   }
/*      */   
/*      */   public static void explodeWasting(World world, double posX, double posY, double posZ, Entity livingEntity, AxisAlignedBB boundingBox, boolean enhanced) {
/* 1207 */     double RADIUS = enhanced ? 5.0D : 4.0D;
/*      */     
/* 1209 */     AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(RADIUS, 2.0D, RADIUS);
/* 1210 */     List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*      */     
/* 1212 */     if ((list1 != null) && (!list1.isEmpty())) {
/* 1213 */       Iterator iterator = list1.iterator();
/*      */       
/* 1215 */       while (iterator.hasNext()) {
/* 1216 */         EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/* 1217 */         double d0 = entitylivingbase.func_70092_e(posX, posY, posZ);
/*      */         
/* 1219 */         if (d0 < RADIUS * RADIUS) {
/* 1220 */           double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
/*      */           
/* 1222 */           if (entitylivingbase == livingEntity) {
/* 1223 */             d1 = 1.0D;
/*      */           }
/*      */           
/* 1226 */           int j = (int)(d1 * 400.0D + 0.5D);
/*      */           
/* 1228 */           if ((entitylivingbase instanceof EntityPlayer)) {
/* 1229 */             EntityPlayer victim = (EntityPlayer)entitylivingbase;
/* 1230 */             int minLevel = enhanced ? 6 : 10;
/* 1231 */             if (victim.func_71024_bL().func_75116_a() > minLevel) {
/* 1232 */               victim.func_71024_bL().func_75122_a(-minLevel, 0.0F);
/*      */             }
/* 1234 */             victim.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, j * 2, enhanced ? 2 : 1));
/* 1235 */             victim.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, Math.max(j / 3, 40), 0));
/*      */           } else {
/* 1237 */             entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, j * 2, enhanced ? 1 : 0));
/* 1238 */             entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, Math.max(j / 3, 40), 0));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1244 */     int BLOCK_RADIUS = (int)RADIUS - 1;
/* 1245 */     int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
/* 1246 */     int blockX = MathHelper.func_76128_c(posX);
/* 1247 */     int blockY = MathHelper.func_76128_c(posY);
/* 1248 */     int blockZ = MathHelper.func_76128_c(posZ);
/* 1249 */     for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; y++) {
/* 1250 */       for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; x++) {
/* 1251 */         for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; z++) {
/* 1252 */           if (Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= BLOCK_RADIUS_SQ) {
/* 1253 */             Material material = world.func_147439_a(x, y, z).func_149688_o();
/* 1254 */             if ((material != null) && ((material == Material.field_151584_j) || (((material == Material.field_151585_k) || (material == Material.field_151582_l)) && (material.func_76222_j())))) {
/* 1255 */               Block blockID = world.func_147439_a(x, y, z);
/* 1256 */               if ((!(blockID instanceof BlockCircle)) && (!(blockID instanceof BlockCircleGlyph))) {
/* 1257 */                 blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 1258 */                 world.func_147468_f(x, y, z);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactInk(MovingObjectPosition mop, boolean enhanced) {
/* 1268 */     Entity livingEntity = mop.field_72308_g;
/*      */     
/* 1270 */     explodeInk(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, livingEntity, this.field_70121_D, enhanced);
/*      */   }
/*      */   
/*      */   public static void explodeInk(World world, double posX, double posY, double posZ, Entity livingEntity, AxisAlignedBB boundingBox, boolean enhanced) {
/* 1274 */     double RADIUS = enhanced ? 5.0D : 4.0D;
/*      */     
/* 1276 */     AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(RADIUS, 2.0D, RADIUS);
/* 1277 */     List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*      */     
/* 1279 */     if ((list1 != null) && (!list1.isEmpty())) {
/* 1280 */       Iterator iterator = list1.iterator();
/*      */       
/* 1282 */       while (iterator.hasNext()) {
/* 1283 */         EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/* 1284 */         double d0 = entitylivingbase.func_70092_e(posX, posY, posZ);
/*      */         
/* 1286 */         if (d0 < RADIUS * RADIUS) {
/* 1287 */           double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
/*      */           
/* 1289 */           if (entitylivingbase == livingEntity) {
/* 1290 */             d1 = 1.0D;
/*      */           }
/*      */           
/* 1293 */           int j = (int)(d1 * 400.0D + 0.5D);
/*      */           
/* 1295 */           entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, j, 0));
/* 1296 */           if ((entitylivingbase instanceof EntityLiving)) {
/* 1297 */             EntityUtil.dropAttackTarget((EntityLiving)entitylivingbase);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void impactRock(MovingObjectPosition mop) {
/* 1305 */     if (mop.field_72308_g != null) {
/* 1306 */       float DAMAGE = 6.0F;
/* 1307 */       mop.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), 6.0F);
/*      */     }
/*      */     
/* 1310 */     spawnParticles(this.field_70170_p, ParticleEffect.EXPLODE, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */   
/*      */   private static void spawnParticles(World world, ParticleEffect effect, double posX, double posY, double posZ) {
/* 1314 */     effect.send(SoundEffect.NONE, world, posX, posY, posZ, 1.0D, 1.0D, 8);
/*      */   }
/*      */   
/*      */   private void impactWebSmall(MovingObjectPosition mop) {
/* 1318 */     switch (mop.field_72313_a) {
/*      */     case ENTITY: 
/* 1320 */       this.field_70170_p.func_147449_b((int)mop.field_72308_g.field_70165_t, (int)mop.field_72308_g.field_70163_u, (int)mop.field_72308_g.field_70161_v, Blocks.field_150321_G);
/* 1321 */       break;
/*      */     case BLOCK: 
/* 1323 */       if (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Blocks.field_150433_aE) {
/* 1324 */         mop.field_72312_c -= 1;
/* 1325 */         mop.field_72310_e = 1;
/*      */       }
/* 1327 */       switch (mop.field_72310_e) {
/*      */       case 0: 
/* 1329 */         setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c - 1, mop.field_72309_d, Blocks.field_150321_G);
/* 1330 */         break;
/*      */       case 1: 
/* 1332 */         setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, Blocks.field_150321_G);
/* 1333 */         break;
/*      */       case 2: 
/* 1335 */         setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b - 1, mop.field_72312_c, mop.field_72309_d, Blocks.field_150321_G);
/* 1336 */         break;
/*      */       case 3: 
/* 1338 */         setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b + 1, mop.field_72312_c, mop.field_72309_d, Blocks.field_150321_G);
/* 1339 */         break;
/*      */       case 4: 
/* 1341 */         setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d - 1, Blocks.field_150321_G);
/* 1342 */         break;
/*      */       case 5: 
/* 1344 */         setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d + 1, Blocks.field_150321_G);
/*      */       }
/*      */       
/* 1347 */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   private void impactWebBig(MovingObjectPosition mop, boolean enhanced)
/*      */   {
/* 1354 */     switch (mop.field_72313_a) {
/*      */     case BLOCK: 
/* 1356 */       explodeWeb(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, enhanced);
/* 1357 */       break;
/*      */     case ENTITY: 
/* 1359 */       int x = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/* 1360 */       int y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/* 1361 */       int z = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/* 1362 */       explodeWeb(this.field_70170_p, x, y, z, -1, enhanced);
/* 1363 */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   public static void explodeWeb(World world, int posX, int posY, int posZ, int side, boolean enhanced)
/*      */   {
/* 1370 */     int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
/* 1371 */     int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
/* 1372 */     int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0);
/* 1373 */     if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
/* 1374 */       y--;
/*      */     }
/* 1376 */     setBlockIfNotSolid(world, x, y, z, Blocks.field_150321_G);
/* 1377 */     setBlockIfNotSolid(world, x + 1, y, z, Blocks.field_150321_G);
/* 1378 */     setBlockIfNotSolid(world, x - 1, y, z, Blocks.field_150321_G);
/* 1379 */     setBlockIfNotSolid(world, x, y, z + 1, Blocks.field_150321_G);
/* 1380 */     setBlockIfNotSolid(world, x, y, z - 1, Blocks.field_150321_G);
/*      */     
/* 1382 */     if (enhanced) {
/* 1383 */       setBlockIfNotSolid(world, x + 1, y, z + 1, Blocks.field_150321_G);
/* 1384 */       setBlockIfNotSolid(world, x - 1, y, z - 1, Blocks.field_150321_G);
/* 1385 */       setBlockIfNotSolid(world, x - 1, y, z + 1, Blocks.field_150321_G);
/* 1386 */       setBlockIfNotSolid(world, x + 1, y, z - 1, Blocks.field_150321_G);
/*      */     }
/*      */     
/* 1389 */     setBlockIfNotSolid(world, x, y + 1, z, Blocks.field_150321_G);
/* 1390 */     setBlockIfNotSolid(world, x, y - 1, z, Blocks.field_150321_G); }
/*      */   
/*      */   private void impactThorns(MovingObjectPosition mop, boolean enhanced) { int x;
/*      */     int z;
/*      */     int y;
/* 1395 */     switch (mop.field_72313_a) {
/*      */     case BLOCK: 
/* 1397 */       if ((mop.field_72310_e == 1) || (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Blocks.field_150434_aF)) {
/* 1398 */         int y = mop.field_72312_c;
/* 1399 */         int x = mop.field_72311_b;
/* 1400 */         int z = mop.field_72309_d;
/* 1401 */         int CACTUS_HEIGHT = enhanced ? 4 : 3;
/*      */         
/* 1403 */         if (plantCactus(this.field_70170_p, x, y, z, CACTUS_HEIGHT)) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       else {
/* 1408 */         ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfThorns.createStack();
/* 1409 */         x = mop.field_72311_b + (mop.field_72310_e == 5 ? 1 : mop.field_72310_e == 4 ? -1 : 0);
/* 1410 */         z = mop.field_72309_d + (mop.field_72310_e == 3 ? 1 : mop.field_72310_e == 2 ? -1 : 0);
/* 1411 */         y = mop.field_72312_c + (mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0);
/* 1412 */         this.skipFX = true;
/* 1413 */         this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, x + 0.5D, y + 0.5D, z + 0.5D, newBrewStack)); }
/* 1414 */       break;
/*      */     case ENTITY: 
/* 1416 */       int CACTUS_HEIGHT = enhanced ? 2 : 1;
/* 1417 */       x = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/* 1418 */       y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/* 1419 */       z = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/*      */       
/* 1421 */       boolean success = plantCactus(this.field_70170_p, x + 1, y, z, CACTUS_HEIGHT);
/* 1422 */       success = (success) && (plantCactus(this.field_70170_p, x - 1, y, z, CACTUS_HEIGHT));
/* 1423 */       success = (success) && (plantCactus(this.field_70170_p, x, y, z + 1, CACTUS_HEIGHT));
/* 1424 */       success = (success) && (plantCactus(this.field_70170_p, x, y, z - 1, CACTUS_HEIGHT));
/*      */       
/* 1426 */       if (!success) {
/* 1427 */         this.skipFX = true;
/* 1428 */         this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, Witchery.Items.GENERIC.itemBrewOfThorns.createStack()));
/*      */       }
/*      */       
/*      */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   public static boolean plantCactus(World world, int x, int y, int z, int CACTUS_HEIGHT)
/*      */   {
/* 1438 */     if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
/* 1439 */       y--;
/*      */     }
/*      */     
/* 1442 */     Material material = world.func_147439_a(x, y, z).func_149688_o();
/* 1443 */     if ((material != Material.field_151571_B) && (material != Material.field_151596_z) && (material != Material.field_151577_b) && (material != Material.field_151578_c) && (material != Material.field_151576_e) && (material != Material.field_151595_p) && (material != Material.field_151597_y) && (material != Material.field_151583_m) && (material != Material.field_151570_A))
/*      */     {
/* 1445 */       return false;
/*      */     }
/* 1447 */     Block blockID = world.func_147439_a(x, y, z);
/* 1448 */     if (!BlockProtect.canBreak(blockID, world)) {
/* 1449 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1453 */     if (material != Material.field_151570_A) {
/* 1454 */       world.func_147449_b(x, y, z, Blocks.field_150354_m);
/*      */     } else {
/* 1456 */       while (world.func_147439_a(x, y, z) == Blocks.field_150434_aF) {
/* 1457 */         y++;
/*      */       }
/* 1459 */       y--;
/*      */     }
/* 1461 */     for (int i = 1; i <= CACTUS_HEIGHT; i++) {
/* 1462 */       if (y + i >= 256) {
/*      */         break;
/*      */       }
/*      */       
/* 1466 */       if (!setBlockIfNotSolid(world, x, y + i, z, Blocks.field_150434_aF)) {
/*      */         break;
/*      */       }
/*      */     }
/*      */     
/* 1471 */     return true;
/*      */   }
/*      */   
/*      */   private void impactVines(MovingObjectPosition mop, boolean enhanced) {
/* 1475 */     if ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (mop.field_72310_e != 0) && (mop.field_72310_e != 1)) {
/* 1476 */       int dx = mop.field_72310_e == 5 ? 1 : mop.field_72310_e == 4 ? -1 : 0;
/* 1477 */       int dz = mop.field_72310_e == 3 ? 1 : mop.field_72310_e == 2 ? -1 : 0;
/* 1478 */       int y0 = mop.field_72312_c;
/*      */       
/* 1480 */       int meta = 0;
/* 1481 */       switch (mop.field_72310_e) {
/*      */       case 2: 
/* 1483 */         meta = 1;
/* 1484 */         break;
/*      */       case 3: 
/* 1486 */         meta = 4;
/* 1487 */         break;
/*      */       case 4: 
/* 1489 */         meta = 8;
/* 1490 */         break;
/*      */       case 5: 
/* 1492 */         meta = 2;
/*      */       }
/*      */       
/*      */       
/* 1496 */       ParticleEffect EFFECT = ParticleEffect.EXPLODE;
/* 1497 */       int y = y0;
/* 1498 */       int x = mop.field_72311_b;
/* 1499 */       int z = mop.field_72309_d;
/* 1500 */       if ((!isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) || (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
/* 1501 */         x += dx;
/* 1502 */         z += dz;
/*      */       }
/* 1504 */       while ((isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) && (this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (y > 0)) {
/* 1505 */         this.field_70170_p.func_147465_d(x + dx, y, z + dz, Blocks.field_150395_bd, meta, 3);
/* 1506 */         spawnParticles(this.field_70170_p, EFFECT, 0.5D + x + dx, 0.5D + y, 0.5D + z + dz);
/* 1507 */         y--;
/* 1508 */         if ((!isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) || (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
/* 1509 */           x += dx;
/* 1510 */           z += dz;
/* 1511 */           if ((enhanced) && ((!isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) || (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()))) {
/* 1512 */             x += dx;
/* 1513 */             z += dz;
/*      */           }
/*      */         }
/*      */       }
/* 1517 */       y = y0 + 1;
/* 1518 */       x = mop.field_72311_b;
/* 1519 */       z = mop.field_72309_d;
/*      */       
/* 1521 */       if (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
/* 1522 */         x -= dx;
/* 1523 */         z -= dz;
/* 1524 */         if ((enhanced) && (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
/* 1525 */           x -= dx;
/* 1526 */           z -= dz;
/*      */         }
/*      */       }
/*      */       
/* 1530 */       while ((isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) && (this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (y < 256)) {
/* 1531 */         this.field_70170_p.func_147465_d(x + dx, y, z + dz, Blocks.field_150395_bd, meta, 3);
/* 1532 */         spawnParticles(this.field_70170_p, EFFECT, 0.5D + x + dx, 0.5D + y, 0.5D + z + dz);
/* 1533 */         y++;
/* 1534 */         if (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
/* 1535 */           x -= dx;
/* 1536 */           z -= dz;
/* 1537 */           if ((enhanced) && (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
/* 1538 */             x -= dx;
/* 1539 */             z -= dz;
/*      */           }
/*      */         }
/*      */       }
/*      */     } else {
/* 1544 */       EntityItem itemEntity = null;
/* 1545 */       ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfVines.createStack();
/* 1546 */       switch (mop.field_72313_a) {
/*      */       case BLOCK: 
/* 1548 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72311_b + 0.5D, mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1) + 0.5D, mop.field_72309_d + 0.5D, newBrewStack);
/* 1549 */         break;
/*      */       case ENTITY: 
/* 1551 */         itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
/* 1552 */         break;
/*      */       }
/*      */       
/*      */       
/* 1556 */       this.skipFX = true;
/* 1557 */       this.field_70170_p.func_72838_d(itemEntity);
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean isNotSolidOrLeaves(Material material) {
/* 1562 */     return (material == null) || (!material.func_76220_a()) || (material == Material.field_151584_j);
/*      */   }
/*      */   
/*      */   private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
/* 1566 */     return setBlockIfNotSolid(world, x, y, z, block, 0);
/*      */   }
/*      */   
/*      */   private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
/* 1570 */     if ((!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) || ((block == Blocks.field_150321_G) && (world.func_147439_a(x, y, z) == Blocks.field_150433_aE))) {
/* 1571 */       world.func_147465_d(x, y, z, block, metadata, 3);
/* 1572 */       spawnParticles(world, ParticleEffect.EXPLODE, 0.5D + x, 0.5D + y, 0.5D + z);
/* 1573 */       return true;
/*      */     }
/* 1575 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_70037_a(NBTTagCompound nbtTag)
/*      */   {
/* 1583 */     super.func_70037_a(nbtTag);
/*      */     
/* 1585 */     if (nbtTag.func_74764_b("damageValue")) {
/* 1586 */       this.damageValue = nbtTag.func_74762_e("damageValue");
/* 1587 */       setDamageValue(this.damageValue);
/*      */     } else {
/* 1589 */       func_70106_y();
/*      */     }
/*      */   }
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbtTag)
/*      */   {
/* 1595 */     super.func_70014_b(nbtTag);
/*      */     
/* 1597 */     nbtTag.func_74768_a("damageValue", this.damageValue);
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityWitchProjectile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */