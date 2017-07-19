/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityWitchCat extends EntityOcelot implements com.emoniph.witchery.familiar.IFamiliar
/*     */ {
/*     */   public EntityWitchCat(World par1World)
/*     */   {
/*  26 */     super(par1World);
/*  27 */     func_70661_as().func_75491_a(true);
/*  28 */     func_70661_as().func_75495_e(true);
/*     */     
/*  30 */     this.field_70714_bg.func_85156_a(((EntityAITasks.EntityAITaskEntry)this.field_70714_bg.field_75782_a.get(4)).field_75733_a);
/*  31 */     this.field_70714_bg.func_85156_a(((EntityAITasks.EntityAITaskEntry)this.field_70714_bg.field_75782_a.get(1)).field_75733_a);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  36 */     this.field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
/*  37 */     this.field_70714_bg.func_75776_a(5, new com.emoniph.witchery.entity.ai.EntityAIDimensionalFollowOwner(this, 1.0D, 10.0F, 5.0F));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  42 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/*  44 */     par1NBTTagCompound.func_74774_a("Familiar", (byte)(isFamiliar() ? 1 : 0));
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  49 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/*  51 */     if (par1NBTTagCompound.func_74764_b("Familiar")) {
/*  52 */       setFamiliar(par1NBTTagCompound.func_74771_c("Familiar") > 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  58 */     this.field_70178_ae = isFamiliar();
/*  59 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  64 */     return super.func_70658_aO() + (isFamiliar() ? 5 : 0);
/*     */   }
/*     */   
/*     */   public net.minecraft.entity.EntityLivingBase func_70902_q()
/*     */   {
/*  69 */     if ((isFamiliar()) && (!this.field_70170_p.field_72995_K)) {
/*  70 */       return TameableUtil.getOwnerAccrossDimensions(this);
/*     */     }
/*  72 */     return super.func_70902_q();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  78 */     super.func_70088_a();
/*  79 */     this.field_70180_af.func_75682_a(26, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/*  84 */     boolean sitting = func_70906_o();
/*  85 */     boolean result = super.func_70097_a(par1DamageSource, par2);
/*  86 */     if ((sitting) && (isFamiliar())) {
/*  87 */       func_70904_g(true);
/*     */     }
/*  89 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isFamiliar()
/*     */   {
/*  94 */     return this.field_70180_af.func_75683_a(26) > 0;
/*     */   }
/*     */   
/*     */   public void setFamiliar(boolean familiar) {
/*  98 */     this.field_70180_af.func_75692_b(26, Byte.valueOf((byte)(familiar ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 103 */     if (func_94056_bM()) {
/* 104 */       return func_94057_bL();
/*     */     }
/* 106 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.cat.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 112 */     return par1;
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 117 */     ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
/*     */     
/* 119 */     if (func_70909_n()) {
/* 120 */       if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (isFamiliar()) && (par1EntityPlayer.func_70093_af()) && (func_70906_o()))
/*     */       {
/* 122 */         if (!this.field_70170_p.field_72995_K) {
/* 123 */           com.emoniph.witchery.familiar.Familiar.dismissFamiliar(par1EntityPlayer, this);
/*     */         }
/* 125 */         return true;
/*     */       }
/*     */       
/* 128 */       if (TameableUtil.isOwner(this, par1EntityPlayer)) {
/* 129 */         if ((itemstack == null) || ((!func_70877_b(itemstack)) && (itemstack.func_77973_b() != Items.field_151057_cb) && (itemstack.func_77973_b() != com.emoniph.witchery.Witchery.Items.POLYNESIA_CHARM) && (itemstack.func_77973_b() != com.emoniph.witchery.Witchery.Items.DEVILS_TONGUE_CHARM)))
/*     */         {
/*     */ 
/* 132 */           if (!this.field_70170_p.field_72995_K) {
/* 133 */             func_70904_g(!func_70906_o());
/*     */           }
/* 135 */           return true; }
/* 136 */         if ((itemstack != null) && (func_70877_b(itemstack)) && (func_110143_aJ() < func_110138_aP())) {
/* 137 */           if (!this.field_70170_p.field_72995_K) {
/* 138 */             if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 139 */               itemstack.field_77994_a -= 1;
/*     */             }
/*     */             
/* 142 */             func_70691_i(10.0F);
/*     */             
/* 144 */             if (itemstack.field_77994_a <= 0) {
/* 145 */               par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */             }
/*     */           }
/* 148 */           return true;
/*     */         }
/*     */       }
/* 151 */     } else if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151115_aP) && (par1EntityPlayer.func_70068_e(this) < 9.0D)) {
/* 152 */       if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 153 */         itemstack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 156 */       if (itemstack.field_77994_a <= 0) {
/* 157 */         par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */       }
/*     */       
/* 160 */       if (!this.field_70170_p.field_72995_K) {
/* 161 */         if (this.field_70146_Z.nextInt(3) == 0) {
/* 162 */           func_70903_f(true);
/* 163 */           func_70912_b(1 + this.field_70170_p.field_73012_v.nextInt(3));
/* 164 */           TameableUtil.setOwner(this, par1EntityPlayer);
/* 165 */           func_70908_e(true);
/* 166 */           func_110163_bv();
/* 167 */           func_70904_g(true);
/* 168 */           this.field_70170_p.func_72960_a(this, (byte)7);
/*     */         } else {
/* 170 */           func_70908_e(false);
/* 171 */           this.field_70170_p.func_72960_a(this, (byte)6);
/*     */         }
/*     */       }
/*     */       
/* 175 */       return true;
/*     */     }
/*     */     
/* 178 */     return super.func_70085_c(par1EntityPlayer);
/*     */   }
/*     */   
/*     */   public void setMaxHealth(float maxHealth)
/*     */   {
/* 183 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
/* 184 */     func_70606_j(maxHealth);
/* 185 */     setFamiliar(true);
/*     */   }
/*     */   
/*     */   public void cloneOcelot(EntityOcelot oldCat) {
/* 189 */     if (oldCat.func_94056_bM()) {
/* 190 */       func_94058_c(oldCat.func_94057_bL());
/*     */     }
/*     */     
/* 193 */     func_70012_b(oldCat.field_70165_t, oldCat.field_70163_u, oldCat.field_70161_v, oldCat.field_70177_z, oldCat.field_70125_A);
/* 194 */     TameableUtil.cloneOwner(this, oldCat);
/* 195 */     func_70903_f(true);
/* 196 */     func_70904_g(oldCat.func_70906_o());
/* 197 */     double oldMaxHealth = oldCat.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/* 198 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(oldMaxHealth);
/* 199 */     func_70606_j(oldCat.func_110143_aJ());
/*     */   }
/*     */   
/*     */   public void clearFamiliar()
/*     */   {
/* 204 */     setFamiliar(false);
/* 205 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/* 206 */     func_70606_j(10.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityWitchCat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */