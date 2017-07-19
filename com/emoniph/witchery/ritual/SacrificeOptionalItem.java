/*     */ package com.emoniph.witchery.ritual;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class SacrificeOptionalItem extends SacrificeItem
/*     */ {
/*     */   public SacrificeOptionalItem(ItemStack optionalSacrifice)
/*     */   {
/*  31 */     super(new ItemStack[] { optionalSacrifice });
/*     */   }
/*     */   
/*     */   public void addDescription(StringBuffer sb)
/*     */   {
/*  36 */     for (ItemStack itemstack : this.itemstacks) {
/*  37 */       sb.append("§8> ");
/*     */       
/*  39 */       if (itemstack.func_77973_b() == Items.field_151068_bn) {
/*  40 */         List list = Items.field_151068_bn.func_77832_l(itemstack);
/*  41 */         if ((list != null) && (!list.isEmpty())) {
/*  42 */           PotionEffect effect = (PotionEffect)list.get(0);
/*  43 */           String s = itemstack.func_82833_r();
/*  44 */           if (effect.func_76458_c() > 0) {
/*  45 */             s = s + " " + net.minecraft.util.StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */           }
/*     */           
/*  48 */           if (effect.func_76459_b() > 20) {
/*  49 */             s = s + " (" + net.minecraft.potion.Potion.func_76389_a(effect) + ")";
/*     */           }
/*  51 */           sb.append(s);
/*     */         } else {
/*  53 */           sb.append(itemstack.func_82833_r());
/*     */         }
/*     */       } else {
/*  56 */         sb.append(itemstack.func_82833_r());
/*     */       }
/*  58 */       sb.append(" ");
/*  59 */       sb.append(Witchery.resource("witchery.rite.optional"));
/*  60 */       sb.append("§0");
/*  61 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
/*     */   {
/*  67 */     boolean found = super.isMatch(world, posX, posY, posZ, maxDistance, entities, grassperStacks);
/*  68 */     return true;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
/*     */   {
/*  73 */     for (ItemStack itemstack : this.itemstacks) {
/*  74 */       steps.add(new StepSacrificeOptionalItem(itemstack, bounds, maxDistance));
/*     */     }
/*     */   }
/*     */   
/*     */   private static class StepSacrificeOptionalItem extends SacrificeItem.StepSacrificeItem
/*     */   {
/*     */     public StepSacrificeOptionalItem(ItemStack itemstack, AxisAlignedBB bounds, int maxDistance) {
/*  81 */       super(bounds, maxDistance);
/*  82 */       this.showMessages = false;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
/*  86 */       if (ticks % 20L != 0L) {
/*  87 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  90 */       if (!world.field_72995_K) {
/*  91 */         List itemEntities = world.func_72872_a(EntityItem.class, this.bounds);
/*     */         
/*  93 */         if (Config.instance().traceRites()) {
/*  94 */           for (Object obj : itemEntities) {
/*  95 */             EntityItem entity = (EntityItem)obj;
/*  96 */             ItemStack foundItemstack = entity.func_92059_d();
/*  97 */             Log.instance().traceRite(String.format(" * found: %s, distance: %f", new Object[] { foundItemstack.toString(), Double.valueOf(Sacrifice.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ)) }));
/*     */           }
/*     */         }
/*     */         
/* 101 */         for (Object obj : itemEntities) {
/* 102 */           EntityItem entity = (EntityItem)obj;
/* 103 */           ItemStack foundItemstack = entity.func_92059_d();
/*     */           
/* 105 */           if ((SacrificeItem.isItemEqual(this.itemstack, foundItemstack)) && (Sacrifice.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) <= this.maxDistance)) {
/* 106 */             boolean skip = false;
/* 107 */             if ((Witchery.Items.GENERIC.itemWaystoneBound.isMatch(foundItemstack)) && 
/* 108 */               (!Witchery.Items.GENERIC.copyLocationBinding(world, foundItemstack, ritual))) {
/* 109 */               skip = true;
/*     */             }
/*     */             
/*     */ 
/* 113 */             if (!skip) {
/* 114 */               ItemStack sacrificedItemstack = ItemStack.func_77944_b(foundItemstack);
/* 115 */               sacrificedItemstack.field_77994_a = 1;
/* 116 */               ritual.sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(entity)));
/*     */               
/* 118 */               if ((foundItemstack.func_77985_e()) && (foundItemstack.field_77994_a > 1)) {
/* 119 */                 foundItemstack.field_77994_a -= 1;
/*     */               } else {
/* 121 */                 world.func_72900_e(entity);
/*     */               }
/*     */             }
/*     */             
/* 125 */             if (!skip) {
/* 126 */               ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, entity, 0.5D, 1.0D, 16);
/*     */             } else {
/* 128 */               ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, entity, 0.5D, 1.0D, 16);
/*     */             }
/*     */             
/* 131 */             return RitualStep.Result.COMPLETED;
/*     */           }
/*     */         }
/*     */         
/* 135 */         int radius = 5;
/* 136 */         for (int x = posX - 5; x <= posX + 5; x++) {
/* 137 */           for (int z = posZ - 5; z <= posZ + 5; z++) {
/* 138 */             Block blockID = world.func_147439_a(x, posY, z);
/* 139 */             if (blockID == Witchery.Blocks.GRASSPER) {
/* 140 */               net.minecraft.tileentity.TileEntity tile = world.func_147438_o(x, posY, z);
/* 141 */               if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
/* 142 */                 BlockGrassper.TileEntityGrassper grassper = (BlockGrassper.TileEntityGrassper)tile;
/*     */                 
/* 144 */                 ItemStack stack = grassper.func_70301_a(0);
/* 145 */                 if ((stack != null) && (SacrificeItem.isItemEqual(this.itemstack, stack))) {
/* 146 */                   boolean skip = false;
/* 147 */                   if ((Witchery.Items.GENERIC.itemWaystoneBound.isMatch(stack)) && 
/* 148 */                     (!Witchery.Items.GENERIC.copyLocationBinding(world, stack, ritual))) {
/* 149 */                     skip = true;
/*     */                   }
/*     */                   
/*     */ 
/* 153 */                   if (!skip) {
/* 154 */                     ItemStack sacrificedItemstack = ItemStack.func_77944_b(stack);
/* 155 */                     sacrificedItemstack.field_77994_a = 1;
/* 156 */                     ritual.sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(tile)));
/* 157 */                     if ((stack.func_77985_e()) && (stack.field_77994_a > 1)) {
/* 158 */                       stack.field_77994_a -= 1;
/*     */                     } else {
/* 160 */                       grassper.func_70299_a(0, null);
/*     */                     }
/*     */                   }
/* 163 */                   if (!skip) {
/* 164 */                     ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, world, 0.5D + x, 0.8D + posY, 0.5D + z, 0.5D, 1.0D, 16);
/*     */                   } else {
/* 166 */                     ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, world, 0.5D + x, 0.8D + posY, 0.5D + z, 0.5D, 1.0D, 16);
/*     */                   }
/*     */                   
/* 169 */                   return RitualStep.Result.COMPLETED;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 177 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 182 */       return String.format("StepSacrificeOptionalItem: %s, maxDistance: %d", new Object[] { this.itemstack.toString(), Integer.valueOf(this.maxDistance) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/SacrificeOptionalItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */