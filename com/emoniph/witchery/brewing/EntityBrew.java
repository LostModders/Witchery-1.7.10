/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityBrew
/*     */   extends EntityThrowableBase
/*     */ {
/*     */   private ItemStack brewStack;
/*     */   private int color;
/*     */   private boolean isSpell;
/*     */   
/*     */   public EntityBrew(World world)
/*     */   {
/*  22 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityBrew(World world, EntityLivingBase thrower, ItemStack brewStack, boolean isSpell) {
/*  26 */     super(world, thrower, isSpell ? 0.0F : -20.0F);
/*  27 */     this.brewStack = brewStack;
/*  28 */     setIsSpell(isSpell);
/*  29 */     setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
/*     */   }
/*     */   
/*     */   public EntityBrew(World world, double x, double y, double z, ItemStack brewStack, boolean isSpell) {
/*  33 */     super(world, x, y, z, isSpell ? 0.0F : -20.0F);
/*  34 */     this.brewStack = brewStack;
/*  35 */     setIsSpell(isSpell);
/*  36 */     setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  41 */     this.field_70180_af.func_75682_a(6, Integer.valueOf(0));
/*  42 */     this.field_70180_af.func_75682_a(12, Byte.valueOf((byte)0));
/*  43 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   protected void setColor(int color) {
/*  47 */     func_70096_w().func_75692_b(6, Integer.valueOf(color));
/*     */   }
/*     */   
/*     */   public int getColor() {
/*  51 */     return func_70096_w().func_75679_c(6);
/*     */   }
/*     */   
/*     */   protected void setIsSpell(boolean spell) {
/*  55 */     func_70096_w().func_75692_b(12, Byte.valueOf((byte)(spell ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public boolean getIsSpell() {
/*  59 */     return func_70096_w().func_75683_a(12) == 1;
/*     */   }
/*     */   
/*     */   public ItemStack getBrew() {
/*  63 */     return this.brewStack;
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity()
/*     */   {
/*  68 */     return getIsSpell() ? 0.0F : 0.05F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  73 */     return getIsSpell() ? 4.0F : 0.75F;
/*     */   }
/*     */   
/*     */   protected float func_70183_g()
/*     */   {
/*  78 */     return getIsSpell() ? 0.0F : -20.0F;
/*     */   }
/*     */   
/*     */   protected void onImpact(MovingObjectPosition mop)
/*     */   {
/*  83 */     if ((!this.field_70170_p.field_72995_K) && 
/*  84 */       (mop != null) && 
/*  85 */       (WitcheryBrewRegistry.INSTANCE.impactSplashPotion(this.field_70170_p, this.brewStack, mop, new ModifiersImpact(new EntityPosition(this), false, 0, EntityUtil.playerOrFake(this.field_70170_p, getThrower())))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  90 */       this.field_70170_p.func_72926_e(2002, MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), getColor());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  97 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 102 */     super.func_70037_a(nbtRoot);
/*     */     
/* 104 */     if (nbtRoot.func_150297_b("Brew", 10)) {
/* 105 */       this.brewStack = ItemStack.func_77949_a(nbtRoot.func_74775_l("Brew"));
/* 106 */       setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(this.brewStack.func_77978_p()));
/* 107 */       if (nbtRoot.func_74764_b("Spell")) {
/* 108 */         setIsSpell(nbtRoot.func_74767_n("Spell"));
/*     */       }
/*     */     }
/*     */     
/* 112 */     if (this.brewStack == null) {
/* 113 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 119 */     super.func_70014_b(nbtRoot);
/*     */     
/* 121 */     if (this.brewStack != null) {
/* 122 */       nbtRoot.func_74782_a("Brew", this.brewStack.func_77955_b(new NBTTagCompound()));
/* 123 */       nbtRoot.func_74757_a("Spell", getIsSpell());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/EntityBrew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */