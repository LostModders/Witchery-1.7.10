/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityParasyticLouse extends EntityMob
/*     */ {
/*     */   public EntityParasyticLouse(World par1World)
/*     */   {
/*  27 */     super(par1World);
/*  28 */     func_70105_a(0.3F, 0.7F);
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  33 */     super.func_110147_ax();
/*  34 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
/*  35 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6000000238418579D);
/*  36 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  41 */     super.func_70088_a();
/*  42 */     this.field_70180_af.func_75682_a(20, new Integer(0));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  47 */     super.func_70014_b(par1NBTTagCompound);
/*  48 */     par1NBTTagCompound.func_74768_a("BitePotionEffect", getBitePotionEffect());
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  53 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/*  55 */     if (par1NBTTagCompound.func_74764_b("BitePotionEffect")) {
/*  56 */       setBitePotionEffect(par1NBTTagCompound.func_74762_e("BitePotionEffect"));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getBitePotionEffect() {
/*  61 */     return this.field_70180_af.func_75679_c(20);
/*     */   }
/*     */   
/*     */   public void setBitePotionEffect(int par1) {
/*  65 */     this.field_70180_af.func_75692_b(20, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  70 */     if (func_94056_bM()) {
/*  71 */       return func_94057_bL();
/*     */     }
/*  73 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.louse.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   protected Entity func_70782_k()
/*     */   {
/*  84 */     double d0 = 8.0D;
/*  85 */     return this.field_70170_p.func_72856_b(this, d0);
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/*  90 */     return "mob.silverfish.say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/*  95 */     return "mob.silverfish.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 100 */     return "mob.silverfish.kill";
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 105 */     if (func_85032_ar()) {
/* 106 */       return false;
/*     */     }
/* 108 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70785_a(Entity par1Entity, float par2)
/*     */   {
/* 114 */     if ((this.field_70724_aR <= 0) && (par2 < 1.2F) && (par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e)) {
/* 115 */       this.field_70724_aR = 20;
/* 116 */       func_70652_k(par1Entity);
/* 117 */       if (((par1Entity instanceof EntityLivingBase)) && (!this.field_70170_p.field_72995_K)) {
/* 118 */         EntityLivingBase living = (EntityLivingBase)par1Entity;
/* 119 */         int potionEffect = getBitePotionEffect();
/* 120 */         if (potionEffect > 0) {
/* 121 */           List list = Items.field_151068_bn.func_77834_f(potionEffect);
/*     */           
/* 123 */           if ((list != null) && (!list.isEmpty())) {
/* 124 */             PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 125 */             living.func_70690_d(effect);
/*     */           }
/* 127 */           setBitePotionEffect(0);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 135 */     func_85030_a("mob.silverfish.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 145 */     this.field_70761_aq = this.field_70177_z;
/* 146 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   protected void func_70626_be()
/*     */   {
/* 151 */     super.func_70626_be();
/*     */     
/* 153 */     if ((!this.field_70170_p.field_72995_K) && 
/* 154 */       (this.field_70789_a != null) && (!func_70781_l())) {
/* 155 */       this.field_70789_a = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70783_a(int par1, int par2, int par3)
/*     */   {
/* 162 */     return this.field_70170_p.func_147439_a(par1, par2 - 1, par3) == net.minecraft.init.Blocks.field_150348_b ? 10.0F : super.func_70783_a(par1, par2, par3);
/*     */   }
/*     */   
/*     */   protected boolean func_70814_o()
/*     */   {
/* 167 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi()
/*     */   {
/* 172 */     if (super.func_70601_bi()) {
/* 173 */       EntityPlayer entityplayer = this.field_70170_p.func_72890_a(this, 5.0D);
/* 174 */       return entityplayer == null;
/*     */     }
/* 176 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/* 182 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */   
/*     */   protected boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 187 */     func_70106_y();
/* 188 */     if (!this.field_70170_p.field_72995_K) {
/* 189 */       ItemStack stack = new ItemStack(com.emoniph.witchery.Witchery.Items.PARASYTIC_LOUSE);
/* 190 */       EntityItem item = new EntityItem(this.field_70170_p, this.field_70165_t, 0.4D + this.field_70163_u, this.field_70161_v, stack);
/* 191 */       this.field_70170_p.func_72838_d(item);
/* 192 */       return true;
/*     */     }
/* 194 */     return super.func_70085_c(player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityParasyticLouse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */