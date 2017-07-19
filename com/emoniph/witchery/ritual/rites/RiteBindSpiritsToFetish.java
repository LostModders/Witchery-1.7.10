/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.entity.EntityBanshee;
/*     */ import com.emoniph.witchery.entity.EntityDeath;
/*     */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*     */ import com.emoniph.witchery.entity.EntitySpectre;
/*     */ import com.emoniph.witchery.entity.EntitySpirit;
/*     */ import com.emoniph.witchery.item.ItemDeathsClothes;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class RiteBindSpiritsToFetish extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int radius;
/*     */   
/*     */   public RiteBindSpiritsToFetish(int radius)
/*     */   {
/*  32 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  37 */     steps.add(new StepSpiritsToFetish(this));
/*     */   }
/*     */   
/*     */   private static class StepSpiritsToFetish extends RitualStep
/*     */   {
/*     */     private final RiteBindSpiritsToFetish rite;
/*     */     
/*     */     public StepSpiritsToFetish(RiteBindSpiritsToFetish rite) {
/*  45 */       super();
/*  46 */       this.rite = rite;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  51 */       if (ticks % 20L != 0L) {
/*  52 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  55 */       if (!world.field_72995_K) {
/*  56 */         int r = this.rite.radius;
/*  57 */         int r2 = r * r;
/*     */         
/*  59 */         AxisAlignedBB bb = AxisAlignedBB.func_72330_a(posX - r, posY - r, posZ - r, posX + r, posY + r, posZ + r);
/*  60 */         java.util.List entities = world.func_72872_a(net.minecraft.entity.EntityCreature.class, bb);
/*     */         
/*  62 */         ArrayList<EntitySpectre> spectreList = new ArrayList();
/*  63 */         ArrayList<EntitySpirit> spiritList = new ArrayList();
/*  64 */         ArrayList<EntityBanshee> bansheeList = new ArrayList();
/*  65 */         ArrayList<EntityPoltergeist> poltergeistList = new ArrayList();
/*     */         
/*  67 */         for (Object obj : entities) {
/*  68 */           if ((obj instanceof EntitySpectre)) {
/*  69 */             spectreList.add((EntitySpectre)obj);
/*  70 */           } else if ((obj instanceof EntityPoltergeist)) {
/*  71 */             poltergeistList.add((EntityPoltergeist)obj);
/*  72 */           } else if ((obj instanceof EntityBanshee)) {
/*  73 */             bansheeList.add((EntityBanshee)obj);
/*  74 */           } else if ((obj instanceof EntitySpirit)) {
/*  75 */             spiritList.add((EntitySpirit)obj);
/*     */           }
/*     */         }
/*     */         
/*  79 */         ItemStack stack = null;
/*  80 */         for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
/*  81 */           if (item.itemstack.func_77969_a(new ItemStack(Witchery.Blocks.FETISH_SCARECROW))) {
/*  82 */             stack = item.itemstack;
/*  83 */             break; }
/*  84 */           if (item.itemstack.func_77969_a(new ItemStack(Witchery.Blocks.FETISH_TREANT_IDOL))) {
/*  85 */             stack = item.itemstack;
/*  86 */             break; }
/*  87 */           if (item.itemstack.func_77969_a(new ItemStack(Witchery.Blocks.FETISH_WITCHS_LADDER))) {
/*  88 */             stack = item.itemstack;
/*  89 */             break;
/*     */           }
/*     */         }
/*     */         
/*  93 */         if (stack == null) {
/*  94 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/*  97 */         int result = com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect.tryBindFetish(world, stack, spiritList, spectreList, bansheeList, poltergeistList);
/*  98 */         if (result == 0) {
/*  99 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/* 102 */         if (result == 2) {
/* 103 */           EntityPlayer deathPlayer = findDeathPlayer(world);
/* 104 */           if (deathPlayer != null) {
/* 105 */             com.emoniph.witchery.item.ItemGeneral.teleportToLocation(world, posX, posY, posZ, world.field_73011_w.field_76574_g, deathPlayer, true);
/* 106 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_WITHER_SPAWN, deathPlayer, 0.5D, 1.5D, 16);
/*     */           } else {
/* 108 */             EntityDeath death = new EntityDeath(world);
/* 109 */             death.func_70012_b(0.5D + posX, posY + 0.1D, 0.5D + posZ, 0.0F, 0.0F);
/* 110 */             death.func_110163_bv();
/* 111 */             world.func_72838_d(death);
/* 112 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_WITHER_SPAWN, death, 0.5D, 1.5D, 16);
/*     */           }
/*     */         }
/*     */         else {
/* 116 */           EntityItem entity = new EntityItem(world, 0.5D + posX, posY + 1.5D, 0.5D + posZ, stack);
/* 117 */           entity.field_70159_w = 0.0D;
/* 118 */           entity.field_70181_x = 0.3D;
/* 119 */           entity.field_70179_y = 0.0D;
/* 120 */           world.func_72838_d(entity);
/* 121 */           ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 1.5D, 16);
/*     */         }
/*     */       }
/* 124 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     private EntityPlayer findDeathPlayer(World world) {
/* 128 */       for (Object obj : world.field_73010_i) {
/* 129 */         EntityPlayer player = (EntityPlayer)obj;
/* 130 */         if ((ItemDeathsClothes.isFullSetWorn(player)) && (player.func_71045_bC() != null) && (player.func_71045_bC().func_77973_b() == Witchery.Items.DEATH_HAND)) {
/* 131 */           return player;
/*     */         }
/*     */       }
/* 134 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteBindSpiritsToFetish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */