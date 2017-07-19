/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMindrake extends EntityTameable implements IEntitySelector
/*     */ {
/*     */   public EntityMindrake(World world)
/*     */   {
/*  33 */     super(world);
/*  34 */     func_70661_as().func_75491_a(true);
/*  35 */     func_70661_as().func_75495_e(true);
/*  36 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  37 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, false));
/*  38 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 0.8D));
/*  39 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  40 */     this.field_70714_bg.func_75776_a(5, new EntityAILookIdle(this));
/*  41 */     this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtTarget(this));
/*  42 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  43 */     this.field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
/*  44 */     this.field_70728_aV = 0;
/*  45 */     func_70105_a(0.6F, 0.8F);
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  50 */     if (func_94056_bM()) {
/*  51 */       return func_94057_bL();
/*     */     }
/*  53 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.mindrake.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  59 */     super.func_110147_ax();
/*  60 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
/*  61 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/*  66 */     return true;
/*     */   }
/*     */   
/*     */   public int func_82143_as()
/*     */   {
/*  71 */     return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  76 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  81 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  86 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  91 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 101 */     return "mob.ghast.scream";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 106 */     return "mob.ghast.death";
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity target)
/*     */   {
/* 111 */     if (!func_70909_n()) {
/* 112 */       return target instanceof EntityPlayer;
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity entity)
/*     */   {
/* 120 */     if (!this.field_70170_p.field_72995_K) {
/* 121 */       this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F, false);
/* 122 */       func_70106_y();
/* 123 */       Block block = BlockUtil.getBlock(this.field_70170_p, this.field_70165_t, this.field_70121_D.field_72338_b - 1.0D, this.field_70161_v);
/* 124 */       if ((block != null) && ((block.func_149688_o() == Material.field_151577_b) || (block.func_149688_o() == Material.field_151578_c))) {
/* 125 */         BlockUtil.setBlock(this.field_70170_p, this.field_70165_t, this.field_70121_D.field_72338_b, this.field_70161_v, this.field_70146_Z.nextInt(2) == 0 ? Blocks.field_150328_O : Blocks.field_150327_N);
/*     */       }
/*     */     }
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 133 */     super.func_70645_a(par1DamageSource);
/* 134 */     if (!this.field_70170_p.field_72995_K) {
/* 135 */       this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, false);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 141 */     func_70099_a(new ItemStack(com.emoniph.witchery.Witchery.Items.SEEDS_MANDRAKE, this.field_70170_p.field_73012_v.nextDouble() <= 0.25D ? 2 : 1), 0.0F);
/*     */   }
/*     */   
/*     */   public EntityAgeable func_90011_a(EntityAgeable var1)
/*     */   {
/* 146 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityMindrake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */