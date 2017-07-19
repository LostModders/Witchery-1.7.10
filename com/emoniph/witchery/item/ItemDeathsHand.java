/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.FoodStats;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemDeathsHand
/*     */   extends ItemBase
/*     */ {
/*     */   public ItemDeathsHand()
/*     */   {
/*  30 */     func_77625_d(1);
/*  31 */     func_77664_n();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  37 */     return EnumRarity.epic;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/*  42 */     String localText = Witchery.resource(func_77658_a() + ".tip");
/*  43 */     if (localText != null) {
/*  44 */       for (String s : localText.split("\n")) {
/*  45 */         if (!s.isEmpty()) {
/*  46 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isHeldItem)
/*     */   {
/*  54 */     if (((entity instanceof EntityPlayer)) && (!world.field_72995_K)) {
/*  55 */       EntityPlayer player = (EntityPlayer)entity;
/*  56 */       if ((isDeployed(stack)) && 
/*  57 */         (TimeUtil.secondsElapsed(1, world.func_72820_D()))) {
/*  58 */         if (!ItemDeathsClothes.isFullSetWorn(player)) {
/*  59 */           setDeployed(player, stack, false);
/*     */         } else {
/*  61 */           int level = player.func_71024_bL().func_75116_a();
/*  62 */           if (level > 0) {
/*  63 */             player.func_71024_bL().func_75122_a(level == 1 ? -1 : -2, 0.0F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity otherEntity)
/*     */   {
/*     */     boolean flag;
/*  73 */     if ((!player.field_70170_p.field_72995_K) && ((otherEntity instanceof EntityLivingBase))) {
/*  74 */       EntityLivingBase victim = (EntityLivingBase)otherEntity;
/*     */       
/*  76 */       float MAX_DAMAGE = 15.0F;
/*     */       
/*  78 */       float DAMAGE_PERCENTAGE = 0.1F;
/*  79 */       boolean deployed = isDeployed(stack);
/*  80 */       float damagePct = 0.1F;
/*  81 */       float minDamage = 2.0F;
/*  82 */       int hungerRestore = 0;
/*  83 */       int healthRestore = 0;
/*  84 */       if (deployed) {
/*  85 */         int hunger = player.func_71024_bL().func_75116_a();
/*  86 */         if (hunger == 0) {
/*  87 */           damagePct = 0.5F;
/*  88 */           minDamage = 4.0F;
/*  89 */           hungerRestore = 10;
/*  90 */           healthRestore = 3;
/*  91 */         } else if (hunger <= 4) {
/*  92 */           damagePct = 0.25F;
/*  93 */           minDamage = 4.0F;
/*  94 */           hungerRestore = 3;
/*  95 */           healthRestore = 2;
/*  96 */         } else if (hunger <= 10) {
/*  97 */           damagePct = 0.2F;
/*  98 */           minDamage = 3.0F;
/*  99 */           hungerRestore = 2;
/* 100 */           healthRestore = 1;
/* 101 */         } else if (hunger <= 20) {
/* 102 */           damagePct = 0.15F;
/* 103 */           minDamage = 3.0F;
/* 104 */           hungerRestore = 1;
/*     */         } else {
/* 106 */           damagePct = 0.15F;
/* 107 */           minDamage = 3.0F;
/*     */         }
/*     */       }
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
/* 120 */       if (deployed) {
/* 121 */         double r = 1.5D;
/* 122 */         AxisAlignedBB bb = AxisAlignedBB.func_72330_a(victim.field_70165_t - 1.5D, victim.field_70121_D.field_72338_b, victim.field_70161_v - 1.5D, victim.field_70165_t + 1.5D, victim.field_70121_D.field_72337_e, victim.field_70161_v + 1.5D);
/*     */         
/* 124 */         List entities = player.field_70170_p.func_72872_a(EntityLivingBase.class, bb);
/* 125 */         for (Object obj : entities) {
/* 126 */           EntityLivingBase hitEntity = (EntityLivingBase)obj;
/* 127 */           if (hitEntity != player) {
/* 128 */             float maxHealth = Math.min(hitEntity.func_110138_aP(), 20.0F);
/* 129 */             float damage = Math.min(Math.max(maxHealth * damagePct, minDamage), 15.0F);
/* 130 */             boolean flag = EntityUtil.touchOfDeath(hitEntity, player, damage);
/*     */             
/* 132 */             if (flag) {
/* 133 */               if (hungerRestore > 0) {
/* 134 */                 player.func_71024_bL().func_75122_a(hungerRestore, 0.0F);
/*     */               }
/* 136 */               if (healthRestore > 0) {
/* 137 */                 player.func_70691_i(healthRestore);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       } else {
/* 143 */         float maxHealth = Math.min(victim.func_110138_aP(), 20.0F);
/* 144 */         float damage = Math.min(Math.max(maxHealth * damagePct, minDamage), 15.0F);
/* 145 */         flag = EntityUtil.touchOfDeath(victim, player, damage);
/*     */       }
/*     */     }
/*     */     
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 154 */     if ((!world.field_72995_K) && (ItemDeathsClothes.isFullSetWorn(player))) {
/* 155 */       if (!stack.func_77942_o()) {
/* 156 */         stack.func_77982_d(new NBTTagCompound());
/*     */       }
/* 158 */       NBTTagCompound nbtItem = stack.func_77978_p();
/* 159 */       boolean deployed = !isDeployed(nbtItem);
/* 160 */       setDeployed(player, stack, nbtItem, deployed);
/* 161 */       if (deployed) {
/* 162 */         ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_GROWL, player, 1.0D, 2.0D, 16);
/*     */       }
/*     */     }
/* 165 */     return stack;
/*     */   }
/*     */   
/*     */   private void setDeployed(EntityPlayer player, ItemStack stack, boolean deployed) {
/* 169 */     setDeployed(player, stack, stack.func_77978_p(), deployed);
/*     */   }
/*     */   
/*     */   private void setDeployed(EntityPlayer player, ItemStack stack, NBTTagCompound nbtItem, boolean deployed) {
/* 173 */     if ((player != null) && (!player.field_70170_p.field_72995_K) && (nbtItem != null)) {
/* 174 */       nbtItem.func_74757_a("WITCScytheDeployed", deployed);
/* 175 */       if ((player instanceof EntityPlayerMP)) {
/* 176 */         ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isDeployed(EntityLivingBase player) {
/* 182 */     ItemStack heldItem = player.func_70694_bm();
/* 183 */     if ((heldItem != null) && (heldItem.func_77973_b() == this)) {
/* 184 */       return isDeployed(heldItem);
/*     */     }
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isDeployed(ItemStack stack)
/*     */   {
/* 191 */     return isDeployed(stack.func_77978_p());
/*     */   }
/*     */   
/*     */   private boolean isDeployed(NBTTagCompound nbtItem) {
/* 195 */     boolean deployed = (nbtItem != null) && (nbtItem.func_74767_n("WITCScytheDeployed"));
/* 196 */     return deployed;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemDeathsHand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */