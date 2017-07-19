/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityFlyingTameable;
/*     */ import com.emoniph.witchery.entity.EntityWitchProjectile;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.Waypoint;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFlyerFlyToWaypoint extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityFlyingTameable flyer;
/*     */   private CarryRequirement carryRequirement;
/*     */   private static final double HIT_RADIUS = 1.0D;
/*     */   private static final double HIT_RADIUS_SQ = 1.0D;
/*     */   
/*     */   public static enum CarryRequirement
/*     */   {
/*  28 */     NONE,  HELD_ITEM,  ENTITY_LIVING;
/*     */     
/*     */     private CarryRequirement() {}
/*     */   }
/*     */   
/*     */   public EntityAIFlyerFlyToWaypoint(EntityFlyingTameable flyer, CarryRequirement carryRestrictions) {
/*  34 */     this.flyer = flyer;
/*  35 */     this.carryRequirement = carryRestrictions;
/*  36 */     func_75248_a(7);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a() {
/*  40 */     if ((this.flyer.waypoint != null) && ((this.flyer.func_70694_bm() != null) || (this.carryRequirement != CarryRequirement.HELD_ITEM))) {
/*  41 */       return true;
/*     */     }
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/*  48 */     boolean heldItem = this.flyer.func_70694_bm() != null;
/*  49 */     boolean awayFromHome = (this.flyer.func_70092_e(this.flyer.homeX, this.flyer.field_70163_u, this.flyer.homeZ) > 1.0D) || (Math.abs(this.flyer.field_70163_u - this.flyer.homeY) > 1.0D);
/*  50 */     return ((heldItem) && (this.carryRequirement == CarryRequirement.HELD_ITEM)) || (this.flyer.waypoint != null) || (awayFromHome);
/*     */   }
/*     */   
/*     */   public void func_75249_e() {}
/*     */   
/*     */   public void func_75251_c()
/*     */   {
/*  57 */     this.flyer.waypoint = null;
/*  58 */     this.flyer.func_70904_g(true);
/*  59 */     if (this.flyer.field_70153_n != null) {
/*  60 */       this.flyer.field_70153_n.func_70078_a(null);
/*     */     }
/*  62 */     this.courseTimer = 0;
/*     */   }
/*     */   
/*  65 */   int courseTimer = 0;
/*     */   
/*     */   public void func_75246_d() {
/*  68 */     if (!this.flyer.func_70906_o()) {
/*  69 */       Waypoint waypoint = this.flyer.getWaypoint();
/*  70 */       if (this.carryRequirement == CarryRequirement.ENTITY_LIVING) {
/*  71 */         if (this.flyer.func_70092_e(waypoint.X, waypoint.Y, waypoint.Z) <= 1.0D)
/*     */         {
/*  73 */           List<EntityLivingBase> entities = this.flyer.field_70170_p.func_72872_a(EntityLivingBase.class, this.flyer.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D));
/*  74 */           if ((entities != null) && (entities.size() > 1)) {
/*  75 */             if (!this.flyer.field_70170_p.field_72995_K) {
/*  76 */               for (EntityLivingBase entity : entities) {
/*  77 */                 if (entity != this.flyer) {
/*  78 */                   entity.func_70078_a(this.flyer);
/*     */                 }
/*     */               }
/*     */             }
/*  82 */             this.flyer.waypoint = null;
/*  83 */             waypoint = this.flyer.getWaypoint();
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */       }
/*  89 */       else if ((this.flyer.func_70694_bm() != null) && (this.flyer.func_70092_e(waypoint.X, waypoint.Y, waypoint.Z) <= 1.0D)) {
/*  90 */         if (!this.flyer.field_70170_p.field_72995_K) {
/*  91 */           ItemStack stack = this.flyer.func_70694_bm();
/*  92 */           this.flyer.func_70062_b(0, null);
/*     */           
/*  94 */           if (Witchery.Items.GENERIC.isBrew(stack)) {
/*  95 */             this.flyer.field_70170_p.func_72956_a(this.flyer, "random.bow", 0.5F, 0.4F / (this.flyer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F));
/*  96 */             EntityWitchProjectile projectile = new EntityWitchProjectile(this.flyer.field_70170_p, this.flyer, (com.emoniph.witchery.item.ItemGeneral.SubItem)Witchery.Items.GENERIC.subItems.get(stack.func_77960_j()));
/*  97 */             projectile.field_70159_w = 0.0D;
/*  98 */             projectile.field_70179_y = 0.0D;
/*  99 */             this.flyer.field_70170_p.func_72838_d(projectile);
/* 100 */           } else if ((Witchery.Items.BREW == stack.func_77973_b()) && (com.emoniph.witchery.brewing.WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p()))) {
/* 101 */             this.flyer.field_70170_p.func_72956_a(this.flyer, "random.bow", 0.5F, 0.4F / (this.flyer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F));
/* 102 */             com.emoniph.witchery.brewing.EntityBrew projectile = new com.emoniph.witchery.brewing.EntityBrew(this.flyer.field_70170_p, this.flyer, stack, false);
/* 103 */             projectile.field_70159_w = 0.0D;
/* 104 */             projectile.field_70179_y = 0.0D;
/* 105 */             this.flyer.field_70170_p.func_72838_d(projectile);
/* 106 */           } else if ((stack.func_77973_b() == net.minecraft.init.Items.field_151068_bn) && (net.minecraft.item.ItemPotion.func_77831_g(stack.func_77960_j()))) {
/* 107 */             this.flyer.field_70170_p.func_72956_a(this.flyer, "random.bow", 0.5F, 0.4F / (this.flyer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F));
/* 108 */             EntityPotion projectile = new EntityPotion(this.flyer.field_70170_p, this.flyer, stack);
/* 109 */             projectile.field_70159_w = 0.0D;
/* 110 */             projectile.field_70179_y = 0.0D;
/* 111 */             this.flyer.field_70170_p.func_72838_d(projectile);
/*     */           }
/*     */           else {
/* 114 */             EntityItem item = new EntityItem(this.flyer.field_70170_p, this.flyer.field_70165_t, this.flyer.field_70163_u, this.flyer.field_70161_v, stack);
/* 115 */             if (stack.func_77973_b() == Witchery.Items.SEEDS_MINDRAKE) {
/* 116 */               item.lifespan = com.emoniph.witchery.util.TimeUtil.secsToTicks(3);
/*     */             }
/* 118 */             this.flyer.field_70170_p.func_72838_d(item);
/*     */           }
/*     */         }
/* 121 */         this.flyer.waypoint = null;
/* 122 */         waypoint = this.flyer.getWaypoint();
/*     */       }
/*     */       
/*     */ 
/* 126 */       double dX = waypoint.X - this.flyer.field_70165_t;
/* 127 */       double dY = waypoint.Y - this.flyer.field_70163_u;
/* 128 */       double dZ = waypoint.Z - this.flyer.field_70161_v;
/* 129 */       double trajectory = dX * dX + dY * dY + dZ * dZ;
/* 130 */       trajectory = MathHelper.func_76133_a(trajectory);
/*     */       
/* 132 */       if ((trajectory >= 128.0D) && (this.carryRequirement == CarryRequirement.HELD_ITEM)) {
/* 133 */         com.emoniph.witchery.blocks.BlockVoidBramble.teleportRandomly(this.flyer.field_70170_p, (int)waypoint.X, (int)waypoint.Y, (int)waypoint.Z, this.flyer, 16);
/*     */       }
/*     */       
/* 136 */       if (--this.courseTimer < 0) {
/* 137 */         this.courseTimer = 0;
/*     */       }
/* 139 */       if (this.courseTimer == 0) {
/* 140 */         if (!isCourseTraversable(waypoint.X, waypoint.Y, waypoint.Z, trajectory))
/*     */         {
/* 142 */           double newX = this.flyer.field_70165_t + (this.flyer.field_70170_p.field_73012_v.nextDouble() * 4.0D - 2.0D) * 6.0D;
/* 143 */           double newY = this.flyer.field_70163_u + (this.flyer.field_70170_p.field_73012_v.nextDouble() * 2.0D - 1.0D) * 4.0D;
/* 144 */           double newZ = this.flyer.field_70161_v + (this.flyer.field_70170_p.field_73012_v.nextDouble() * 4.0D - 2.0D) * 6.0D;
/* 145 */           if (this.flyer.field_70170_p.field_73012_v.nextInt(2) != 0) {
/* 146 */             dX = newX - this.flyer.field_70165_t;
/* 147 */             dZ = newZ - this.flyer.field_70161_v;
/*     */           }
/* 149 */           if (this.flyer.func_70092_e(waypoint.X, waypoint.Y, waypoint.Z) <= 1.0D) {
/* 150 */             dY = ((this.flyer.field_70163_u > waypoint.Y) && (newY > 0.0D) ? -newY : newY) - this.flyer.field_70163_u;
/*     */           } else {
/* 152 */             dY = newY - this.flyer.field_70163_u;
/*     */           }
/*     */           
/* 155 */           trajectory = dX * dX + dY * dY + dZ * dZ;
/* 156 */           trajectory = MathHelper.func_76133_a(trajectory);
/*     */         }
/*     */         
/* 159 */         double ACCELERATION = 0.2D;
/* 160 */         this.flyer.field_70159_w += dX / trajectory * 0.2D;
/* 161 */         this.flyer.field_70179_y += dZ / trajectory * 0.2D;
/*     */         
/*     */ 
/*     */ 
/* 165 */         this.flyer.field_70181_x += dY / trajectory * 0.2D + (this.flyer.field_70163_u < Math.min(waypoint.Y + (this.carryRequirement == CarryRequirement.HELD_ITEM ? 32 : 32), 255.0D) ? 0.1D : 0.0D);
/*     */         
/* 167 */         this.courseTimer = 10;
/*     */       }
/*     */       
/* 170 */       this.flyer.field_70761_aq = (this.flyer.field_70177_z = -(float)Math.atan2(this.flyer.field_70159_w, this.flyer.field_70179_y) * 180.0F / 3.1415927F);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/* 175 */     double d4 = (par1 - this.flyer.field_70165_t) / par7;
/* 176 */     double d5 = (par3 - this.flyer.field_70163_u) / par7;
/* 177 */     double d6 = (par5 - this.flyer.field_70161_v) / par7;
/*     */     
/* 179 */     AxisAlignedBB axisalignedbb = this.flyer.field_70121_D.func_72329_c();
/*     */     
/* 181 */     for (int i = 1; i < par7; i++) {
/* 182 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 184 */       if (!this.flyer.field_70170_p.func_72945_a(this.flyer, axisalignedbb).isEmpty()) {
/* 185 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 189 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerFlyToWaypoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */