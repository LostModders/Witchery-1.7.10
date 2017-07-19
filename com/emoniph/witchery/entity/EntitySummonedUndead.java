/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySummonedUndead extends EntityMob
/*     */ {
/*  15 */   private int timeToLive = -1;
/*     */   
/*  17 */   public EntitySummonedUndead(World world) { super(world); }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  22 */     super.func_70088_a();
/*  23 */     this.field_70180_af.func_75682_a(17, "");
/*  24 */     this.field_70180_af.func_75682_a(18, Integer.valueOf(Integer.valueOf(0).intValue()));
/*  25 */     this.field_70180_af.func_75682_a(19, Integer.valueOf(Integer.valueOf(0).intValue()));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/*  30 */     super.func_70014_b(nbtRoot);
/*  31 */     if (getSummonerName() == null) {
/*  32 */       nbtRoot.func_74778_a("Summoner", "");
/*     */     } else {
/*  34 */       nbtRoot.func_74778_a("Summoner", getSummonerName());
/*     */     }
/*     */     
/*  37 */     nbtRoot.func_74757_a("Obscured", isObscured());
/*  38 */     nbtRoot.func_74768_a("SuicideIn", this.timeToLive);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/*  43 */     super.func_70037_a(nbtRoot);
/*  44 */     String s = nbtRoot.func_74779_i("Summoner");
/*  45 */     if (s.length() > 0) {
/*  46 */       setSummoner(s);
/*     */     }
/*  48 */     setObscured(nbtRoot.func_74767_n("Obscured"));
/*  49 */     if (nbtRoot.func_74764_b("SuicideIn")) {
/*  50 */       this.timeToLive = nbtRoot.func_74762_e("SuicideIn");
/*     */     } else {
/*  52 */       this.timeToLive = -1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTimeToLive(int i) {
/*  57 */     this.timeToLive = i;
/*     */   }
/*     */   
/*     */   public boolean isTemp() {
/*  61 */     return this.timeToLive != -1;
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/*  66 */     return par1;
/*     */   }
/*     */   
/*     */   public String getSummonerName() {
/*  70 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setSummoner(String par1Str) {
/*  74 */     func_110163_bv();
/*  75 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public net.minecraft.entity.player.EntityPlayer getSummoner() {
/*  79 */     return this.field_70170_p.func_72924_a(getSummonerName());
/*     */   }
/*     */   
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/*  84 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/*  89 */     if (!isTemp()) {
/*  90 */       int chance = this.field_70146_Z.nextInt(Math.max(4 - par2, 2));
/*  91 */       int quantity = chance == 0 ? 1 : 0;
/*  92 */       if (quantity > 0) {
/*  93 */         func_70099_a(com.emoniph.witchery.Witchery.Items.GENERIC.itemSpectralDust.createStack(quantity), 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70629_bd() {
/*  99 */     super.func_70629_bd();
/* 100 */     if ((this.field_70170_p != null) && (!this.field_70128_L) && (!this.field_70170_p.field_72995_K) && (this.timeToLive != -1) && ((--this.timeToLive == 0) || (func_70638_az() == null) || (func_70638_az().field_70128_L))) {
/* 101 */       ParticleEffect.EXPLODE.send(com.emoniph.witchery.util.SoundEffect.NONE, this, 1.0D, 1.0D, 16);
/* 102 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 108 */     return super.func_70627_aG() * 3;
/*     */   }
/*     */   
/*     */   public boolean isScreaming() {
/* 112 */     return this.field_70180_af.func_75679_c(18) == 1;
/*     */   }
/*     */   
/*     */   protected void setScreaming(boolean screaming) {
/* 116 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(Integer.valueOf(screaming ? 1 : 0).intValue()));
/*     */   }
/*     */   
/*     */   public boolean isObscured() {
/* 120 */     return this.field_70180_af.func_75679_c(19) == 1;
/*     */   }
/*     */   
/*     */   public void setObscured(boolean obscured) {
/* 124 */     this.field_70180_af.func_75692_b(19, Integer.valueOf(Integer.valueOf(obscured ? 1 : 0).intValue()));
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damage)
/*     */   {
/* 129 */     return super.func_70097_a(damageSource, Math.min(damage, 15.0F));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntitySummonedUndead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */