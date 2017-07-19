/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityParasyticLouse;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemParasyticLouse extends ItemBase
/*     */ {
/*     */   public ItemParasyticLouse()
/*     */   {
/*  28 */     func_77625_d(1);
/*  29 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
/*     */   {
/*  35 */     if (par3World.field_72995_K) {
/*  36 */       return true;
/*     */     }
/*  38 */     Block i1 = par3World.func_147439_a(par4, par5, par6);
/*  39 */     par4 += net.minecraft.util.Facing.field_71586_b[par7];
/*  40 */     par5 += net.minecraft.util.Facing.field_71587_c[par7];
/*  41 */     par6 += net.minecraft.util.Facing.field_71585_d[par7];
/*  42 */     double d0 = 0.0D;
/*     */     
/*  44 */     if ((par7 == 1) && (i1.func_149645_b() == 11)) {
/*  45 */       d0 = 0.5D;
/*     */     }
/*     */     
/*  48 */     Entity entity = spawnCreature(par1ItemStack, par3World, par4 + 0.5D, par5 + d0, par6 + 0.5D);
/*     */     
/*  50 */     if (entity != null) {
/*  51 */       if (((entity instanceof EntityLivingBase)) && (par1ItemStack.func_82837_s())) {
/*  52 */         ((EntityLiving)entity).func_94058_c(par1ItemStack.func_82833_r());
/*     */       }
/*     */       
/*  55 */       if (!par2EntityPlayer.field_71075_bZ.field_75098_d) {
/*  56 */         par1ItemStack.field_77994_a -= 1;
/*     */       }
/*     */     }
/*     */     
/*  60 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/*  66 */     return super.func_77653_i(stack);
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/*  71 */     List effects = Items.field_151068_bn.func_77834_f(stack.func_77960_j());
/*  72 */     if ((effects != null) && (!effects.isEmpty())) {
/*  73 */       PotionEffect effect = (PotionEffect)effects.get(0);
/*  74 */       String s1 = effect.func_76453_d();
/*  75 */       s1 = s1 + ".postfix";
/*  76 */       String s2 = "§6" + StatCollector.func_74838_a(s1).trim() + "§r";
/*     */       
/*  78 */       if (effect.func_76458_c() > 0) {
/*  79 */         s2 = s2 + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */       }
/*     */       
/*  82 */       if (effect.func_76459_b() > 20) {
/*  83 */         s2 = s2 + " [" + net.minecraft.potion.Potion.func_76389_a(effect) + "]";
/*     */       }
/*     */       
/*  86 */       list.add(s2);
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/*  92 */     if (par2World.field_72995_K) {
/*  93 */       return par1ItemStack;
/*     */     }
/*  95 */     MovingObjectPosition movingobjectposition = func_77621_a(par2World, par3EntityPlayer, true);
/*     */     
/*  97 */     if (movingobjectposition == null) {
/*  98 */       return par1ItemStack;
/*     */     }
/* 100 */     if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 101 */       int i = movingobjectposition.field_72311_b;
/* 102 */       int j = movingobjectposition.field_72312_c;
/* 103 */       int k = movingobjectposition.field_72309_d;
/*     */       
/* 105 */       if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
/* 106 */         return par1ItemStack;
/*     */       }
/*     */       
/* 109 */       if (!par3EntityPlayer.func_82247_a(i, j, k, movingobjectposition.field_72310_e, par1ItemStack)) {
/* 110 */         return par1ItemStack;
/*     */       }
/*     */       
/* 113 */       if (par2World.func_147439_a(i, j, k).func_149688_o() == Material.field_151586_h) {
/* 114 */         Entity entity = spawnCreature(par1ItemStack, par2World, i, j, k);
/*     */         
/* 116 */         if (entity != null) {
/* 117 */           if (((entity instanceof EntityLivingBase)) && (par1ItemStack.func_82837_s())) {
/* 118 */             ((EntityLiving)entity).func_94058_c(par1ItemStack.func_82833_r());
/*     */           }
/*     */           
/* 121 */           if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
/* 122 */             par1ItemStack.field_77994_a -= 1;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 128 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */   private Entity spawnCreature(ItemStack stack, World par0World, double par2, double par4, double par6)
/*     */   {
/* 134 */     EntityParasyticLouse entity = new EntityParasyticLouse(par0World);
/* 135 */     int damage = stack.func_77960_j();
/* 136 */     if (damage > 0) {
/* 137 */       entity.setBitePotionEffect(damage);
/*     */     }
/*     */     
/* 140 */     if ((entity != null) && ((entity instanceof EntityLivingBase))) {
/* 141 */       EntityLiving entityliving = entity;
/* 142 */       entity.func_70012_b(par2, par4, par6, MathHelper.func_76142_g(par0World.field_73012_v.nextFloat() * 360.0F), 0.0F);
/* 143 */       entity.func_110163_bv();
/* 144 */       entityliving.field_70759_as = entityliving.field_70177_z;
/* 145 */       entityliving.field_70761_aq = entityliving.field_70177_z;
/* 146 */       entityliving.func_110161_a((IEntityLivingData)null);
/* 147 */       par0World.func_72838_d(entity);
/* 148 */       entityliving.func_70642_aH();
/*     */     }
/*     */     
/* 151 */     return entity;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemParasyticLouse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */