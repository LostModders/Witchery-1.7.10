/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMirrorFace extends EntityLiving
/*     */ {
/*  15 */   private long ticksAlive = 0L;
/*     */   
/*     */   public EntityMirrorFace(World world) {
/*  18 */     super(world);
/*  19 */     this.field_70178_ae = true;
/*  20 */     func_70105_a(0.5F, 0.5F);
/*  21 */     this.field_70145_X = true;
/*  22 */     this.field_70714_bg.func_75776_a(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F, 0.4F));
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  27 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int air)
/*     */   {
/*  32 */     return air;
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/*  37 */     return 0.8F;
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/*  42 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/*  47 */     return 80;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/*  52 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/*  62 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_82167_n(Entity par1Entity) {}
/*     */   
/*     */ 
/*     */   protected void func_85033_bc() {}
/*     */   
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/*  80 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  85 */     super.func_70636_d();
/*     */     
/*  87 */     this.ticksAlive = Math.max(this.field_70173_aa, ++this.ticksAlive);
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
/*  99 */     if ((this.ticksAlive > TimeUtil.secsToTicks(10)) && 
/* 100 */       (!this.field_70170_p.field_72995_K)) {
/* 101 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 108 */     super.func_70071_h_();
/*     */     
/* 110 */     this.field_70181_x = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean par1, int par2) {}
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 120 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */   protected void func_70064_a(double par1, boolean par3) {}
/*     */   
/*     */ 
/*     */   public boolean func_145773_az()
/*     */   {
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 143 */     super.func_70037_a(nbtRoot);
/* 144 */     this.ticksAlive = nbtRoot.func_74763_f("WITCTicksAlive");
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 149 */     super.func_70014_b(nbtRoot);
/* 150 */     nbtRoot.func_74772_a("WITCTicksAlive", this.ticksAlive);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityMirrorFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */