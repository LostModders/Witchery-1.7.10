/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteSummonItem extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final ItemStack itemToSummon;
/*     */   private final Binding binding;
/*     */   
/*     */   public static enum Binding
/*     */   {
/*  27 */     NONE,  LOCATION,  ENTITY,  COPY_LOCATION,  PLAYER;
/*     */     
/*     */     private Binding() {}
/*     */   }
/*     */   
/*     */   public RiteSummonItem(ItemStack itemToSummon, Binding binding)
/*     */   {
/*  34 */     this.itemToSummon = itemToSummon;
/*  35 */     this.binding = binding;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  40 */     steps.add(new StepSummonItem(this));
/*     */   }
/*     */   
/*     */   private static class StepSummonItem extends RitualStep
/*     */   {
/*     */     private final RiteSummonItem rite;
/*     */     
/*     */     public StepSummonItem(RiteSummonItem rite) {
/*  48 */       super();
/*  49 */       this.rite = rite;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  54 */       if (ticks % 20L != 0L) {
/*  55 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  58 */       if (!world.field_72995_K) {
/*  59 */         ItemStack itemstack = ItemStack.func_77944_b(this.rite.itemToSummon);
/*  60 */         if (this.rite.binding == RiteSummonItem.Binding.LOCATION) {
/*  61 */           Witchery.Items.GENERIC.bindToLocation(world, posX, posY, posZ, world.field_73011_w.field_76574_g, world.field_73011_w.func_80007_l(), itemstack);
/*  62 */         } else if (this.rite.binding == RiteSummonItem.Binding.ENTITY) {
/*  63 */           int r = 4;
/*  64 */           EntityLivingBase target = null;
/*  65 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - 4, posY, posZ - 4, posX + 4, posY + 1, posZ + 4);
/*  66 */           for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/*  67 */             EntityPlayer player = (EntityPlayer)obj;
/*  68 */             if (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= 4.0D) {
/*  69 */               target = player;
/*     */             }
/*     */           }
/*  72 */           if (target != null) {
/*  73 */             bounds = AxisAlignedBB.func_72330_a(posX - 4, posY, posZ - 4, posX + 4, posY + 1, posZ + 4);
/*  74 */             for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
/*  75 */               EntityLiving entity = (EntityLiving)obj;
/*  76 */               if (Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) <= 4.0D) {
/*  77 */                 target = entity;
/*     */               }
/*     */             }
/*     */           }
/*  81 */           if (target != null) {
/*  82 */             Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(itemstack, null, target, false, Integer.valueOf(1));
/*     */           } else {
/*  84 */             return RitualStep.Result.ABORTED_REFUND;
/*     */           }
/*  86 */         } else if (this.rite.binding == RiteSummonItem.Binding.PLAYER) {
/*  87 */           int r = 4;
/*  88 */           EntityLivingBase target = null;
/*  89 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - 4, posY, posZ - 4, posX + 4, posY + 1, posZ + 4);
/*  90 */           for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/*  91 */             EntityPlayer player = (EntityPlayer)obj;
/*  92 */             if (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= 4.0D) {
/*  93 */               target = player;
/*     */             }
/*     */           }
/*  96 */           if (target != null) {
/*  97 */             NBTTagCompound nbtRoot = new NBTTagCompound();
/*  98 */             nbtRoot.func_74778_a("WITCBoundPlayer", target.func_70005_c_());
/*  99 */             itemstack.func_77982_d(nbtRoot);
/*     */           } else {
/* 101 */             return RitualStep.Result.ABORTED_REFUND;
/*     */           }
/* 103 */         } else if (this.rite.binding == RiteSummonItem.Binding.COPY_LOCATION) {
/* 104 */           for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
/* 105 */             if (Witchery.Items.GENERIC.hasLocationBinding(item.itemstack)) {
/* 106 */               Witchery.Items.GENERIC.copyLocationBinding(item.itemstack, itemstack);
/* 107 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 112 */         if (itemstack.func_77973_b() == net.minecraft.item.Item.func_150898_a(Witchery.Blocks.CRYSTAL_BALL)) {
/* 113 */           EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 114 */           if (player != null) {
/* 115 */             com.emoniph.witchery.predictions.PredictionManager.instance().setFortuneTeller(player, true);
/*     */           }
/*     */         }
/*     */         
/* 119 */         net.minecraft.entity.item.EntityItem entity = new net.minecraft.entity.item.EntityItem(world, 0.5D + posX, posY + 1.5D, 0.5D + posZ, itemstack);
/* 120 */         entity.field_70159_w = 0.0D;
/* 121 */         entity.field_70181_x = 0.3D;
/* 122 */         entity.field_70179_y = 0.0D;
/* 123 */         world.func_72838_d(entity);
/*     */         
/* 125 */         com.emoniph.witchery.util.ParticleEffect.SPELL.send(com.emoniph.witchery.util.SoundEffect.RANDOM_FIZZ, entity, 0.5D, 0.5D, 16);
/*     */       }
/* 127 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteSummonItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */