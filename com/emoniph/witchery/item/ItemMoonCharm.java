/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.Shapeshift;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import com.emoniph.witchery.util.TransformCreature;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemMoonCharm extends ItemBase
/*     */ {
/*     */   public ItemMoonCharm()
/*     */   {
/*  24 */     this.autoGenerateTooltip = true;
/*  25 */     func_77625_d(1);
/*  26 */     func_77656_e(49);
/*     */   }
/*     */   
/*     */   public boolean func_82789_a(ItemStack item, ItemStack otherMaterial)
/*     */   {
/*  31 */     return otherMaterial.func_77969_a(new ItemStack(Items.field_151043_k));
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  37 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack itemstack)
/*     */   {
/*  42 */     return EnumAction.bow;
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack itemstack)
/*     */   {
/*  47 */     return TimeUtil.secsToTicks(3);
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*     */   {
/*  52 */     if (!player.field_70170_p.field_72995_K)
/*     */     {
/*     */ 
/*  55 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  56 */       int level = playerEx.getWerewolfLevel();
/*  57 */       if (countdown == Math.max((level - 1) * 4, 1)) {
/*  58 */         if ((!isWolfsbaneActive(player, playerEx)) && (Shapeshift.INSTANCE.canControlTransform(playerEx))) {}
/*  59 */         switch (playerEx.getCreatureType()) {
/*     */         case NONE: 
/*  61 */           if ((player.func_70093_af()) && (Shapeshift.INSTANCE.isWolfmanAllowed(playerEx))) {
/*  62 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLFMAN);
/*     */           } else {
/*  64 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
/*     */           }
/*  66 */           ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
/*     */           
/*  68 */           break;
/*     */         case WOLF: 
/*  70 */           if ((player.func_70093_af()) && (Shapeshift.INSTANCE.isWolfmanAllowed(playerEx))) {
/*  71 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLFMAN);
/*     */           } else {
/*  73 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/*     */           }
/*  75 */           ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
/*  76 */           break;
/*     */         case WOLFMAN: 
/*  78 */           if (player.func_70093_af()) {
/*  79 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/*     */           } else {
/*  81 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
/*     */           }
/*  83 */           ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
/*  84 */           break;
/*     */         default: 
/*  86 */           ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 0.5D, 8);
/*  87 */           break;
/*     */           
/*     */ 
/*  90 */           ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, player, 0.5D, 0.5D, 8);
/*     */         }
/*  92 */         stack.func_77972_a(1, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 100 */     player.func_71008_a(stack, func_77626_a(stack));
/* 101 */     return stack;
/*     */   }
/*     */   
/*     */   public static boolean isWolfsbaneActive(EntityPlayer player, ExtendedPlayer playerEx) {
/* 105 */     PotionEffect potion = player.func_70660_b(Witchery.Potions.WOLFSBANE);
/* 106 */     if (potion == null) {
/* 107 */       return false;
/*     */     }
/*     */     
/* 110 */     int amplifier = 1 + Math.max(0, potion.func_76458_c() * 3 - 1);
/*     */     
/* 112 */     return amplifier >= playerEx.getWerewolfLevel();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemMoonCharm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */