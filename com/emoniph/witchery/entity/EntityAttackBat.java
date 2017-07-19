/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAttackBat extends EntityBat
/*     */ {
/*     */   private EntityPlayer ownerPlayer;
/*     */   private GameProfile owner;
/*     */   
/*     */   public EntityAttackBat(World world)
/*     */   {
/*  25 */     super(world);
/*     */   }
/*     */   
/*     */   protected void func_85033_bc() {
/*  29 */     List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*     */     
/*     */ 
/*  32 */     if ((list != null) && (!list.isEmpty())) {
/*  33 */       for (int i = 0; i < list.size(); i++) {
/*  34 */         Entity entity = (Entity)list.get(i);
/*     */         
/*  36 */         if (entity.func_70104_M()) {
/*  37 */           func_82167_n(entity);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82167_n(Entity entity) {
/*  44 */     if ((!this.field_70170_p.field_72995_K) && ((entity instanceof EntityLivingBase))) {
/*  45 */       EntityLivingBase target = (EntityLivingBase)entity;
/*  46 */       if (this.ownerPlayer == null) {
/*  47 */         this.ownerPlayer = getOwner();
/*     */       }
/*  49 */       if ((target != this.ownerPlayer) && (!(target instanceof EntityBat)) && (!target.field_70128_L)) {
/*  50 */         target.func_70097_a(net.minecraft.util.DamageSource.func_76354_b(this, this.ownerPlayer), 4.0F);
/*  51 */         ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, entity, entity.field_70130_N, entity.field_70131_O, 16);
/*     */         
/*  53 */         func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/*  63 */     if (!this.field_70170_p.field_72995_K) {
/*  64 */       boolean done = false;
/*  65 */       if (this.field_70173_aa > 300) {
/*  66 */         ParticleEffect.SMOKE.send(SoundEffect.NONE, this, this.field_70130_N, this.field_70131_O, 16);
/*  67 */         func_70106_y();
/*     */       }
/*     */       else {
/*  70 */         if (this.ownerPlayer == null) {
/*  71 */           this.ownerPlayer = getOwner();
/*     */         }
/*     */         
/*  74 */         if ((this.ownerPlayer != null) && (this.ownerPlayer.field_71093_bK == this.field_71093_bK)) {
/*  75 */           MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.raytraceEntities(this.field_70170_p, this.ownerPlayer, true, 32.0D);
/*     */           
/*  77 */           if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase)) && (!(mop.field_72308_g instanceof EntityBat)))
/*     */           {
/*  79 */             double d0 = mop.field_72308_g.field_70165_t - this.field_70165_t;
/*  80 */             double d1 = mop.field_72308_g.field_70163_u - this.field_70163_u;
/*  81 */             double d2 = mop.field_72308_g.field_70161_v - this.field_70161_v;
/*  82 */             double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*  83 */             d3 = MathHelper.func_76133_a(d3);
/*  84 */             if (isCourseTraversable(mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, d3))
/*     */             {
/*  86 */               this.field_70159_w += d0 / d3 * 0.1D;
/*  87 */               this.field_70181_x += d1 / d3 * 0.1D;
/*  88 */               this.field_70179_y += d2 / d3 * 0.1D;
/*  89 */               float f = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/*  90 */               float f1 = MathHelper.func_76142_g(f - this.field_70177_z);
/*  91 */               this.field_70701_bs = 0.5F;
/*  92 */               this.field_70177_z += f1;
/*  93 */               done = true;
/*     */             }
/*     */             
/*  96 */             this.field_70761_aq = (this.field_70177_z = -(float)Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0F / 3.1415927F);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 103 */       if (!done) {
/* 104 */         super.func_70619_bc();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/* 110 */     double d4 = (par1 - this.field_70165_t) / par7;
/* 111 */     double d5 = (par3 - this.field_70163_u) / par7;
/* 112 */     double d6 = (par5 - this.field_70161_v) / par7;
/*     */     
/* 114 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/*     */     
/* 116 */     for (int i = 1; i < par7; i++) {
/* 117 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 119 */       if (!this.field_70170_p.func_72945_a(this, axisalignedbb).isEmpty()) {
/* 120 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 124 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setOwner(EntityPlayer player)
/*     */   {
/* 130 */     this.owner = player.func_146103_bH();
/*     */   }
/*     */   
/*     */   public EntityPlayer getOwner() {
/* 134 */     return this.owner != null ? this.field_70170_p.func_152378_a(this.owner.getId()) : null;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 139 */     super.func_70014_b(nbtRoot);
/* 140 */     if (this.owner != null) {
/* 141 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 142 */       NBTUtil.func_152460_a(nbttagcompound1, this.owner);
/* 143 */       nbtRoot.func_74782_a("Owner", nbttagcompound1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70020_e(NBTTagCompound nbtRoot)
/*     */   {
/* 149 */     super.func_70020_e(nbtRoot);
/* 150 */     if (nbtRoot.func_150297_b("Owner", 10)) {
/* 151 */       this.owner = NBTUtil.func_152459_a(nbtRoot.func_74775_l("Owner"));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityAttackBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */