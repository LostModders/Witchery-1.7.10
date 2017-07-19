/*     */ package com.emoniph.witchery.ritual;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
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
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class SacrificeItem extends Sacrifice
/*     */ {
/*     */   final ItemStack[] itemstacks;
/*     */   
/*     */   public SacrificeItem(ItemStack... itemstacks)
/*     */   {
/*  33 */     this.itemstacks = itemstacks;
/*     */   }
/*     */   
/*     */   public void addDescription(StringBuffer sb)
/*     */   {
/*  38 */     for (ItemStack itemstack : this.itemstacks) {
/*  39 */       sb.append("§8>§0 ");
/*  40 */       if (itemstack.func_77973_b() == Items.field_151068_bn) {
/*  41 */         List list = Items.field_151068_bn.func_77832_l(itemstack);
/*  42 */         if ((list != null) && (!list.isEmpty())) {
/*  43 */           PotionEffect effect = (PotionEffect)list.get(0);
/*  44 */           String s = itemstack.func_82833_r();
/*  45 */           if (effect.func_76458_c() > 0) {
/*  46 */             s = s + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */           }
/*     */           
/*  49 */           if (effect.func_76459_b() > 20) {
/*  50 */             s = s + " (" + net.minecraft.potion.Potion.func_76389_a(effect) + ")";
/*     */           }
/*  52 */           sb.append(s);
/*     */         } else {
/*  54 */           sb.append(itemstack.func_82833_r());
/*     */         }
/*     */       } else {
/*  57 */         sb.append(itemstack.func_82833_r());
/*     */       }
/*  59 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
/*     */   {
/*  65 */     ArrayList<Entity> itemsToRemove = new ArrayList();
/*  66 */     ArrayList<ItemStack> otherItemsToRemove = new ArrayList();
/*  67 */     for (int j = 0; j < this.itemstacks.length; j++) {
/*  68 */       boolean found = false;
/*  69 */       for (int i = 0; i < entities.size(); i++) {
/*  70 */         if ((entities.get(i) instanceof EntityItem)) {
/*  71 */           EntityItem entity = (EntityItem)entities.get(i);
/*  72 */           if ((isItemEqual(this.itemstacks[j], entity.func_92059_d())) && (!itemsToRemove.contains(entity)) && (distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) <= maxDistance))
/*     */           {
/*     */ 
/*  75 */             itemsToRemove.add(entity);
/*  76 */             found = true;
/*  77 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  82 */       for (int i = 0; i < grassperStacks.size(); i++)
/*     */       {
/*  84 */         if ((isItemEqual(this.itemstacks[j], (ItemStack)grassperStacks.get(i))) && (!otherItemsToRemove.contains(grassperStacks.get(i))))
/*     */         {
/*  86 */           otherItemsToRemove.add(grassperStacks.get(i));
/*  87 */           found = true;
/*  88 */           break;
/*     */         }
/*     */       }
/*     */       
/*  92 */       if (!found) {
/*  93 */         return false;
/*     */       }
/*     */     }
/*     */     
/*  97 */     for (Entity itemToRemove : itemsToRemove) {
/*  98 */       entities.remove(itemToRemove);
/*     */     }
/*     */     
/* 101 */     for (ItemStack itemToRemove : otherItemsToRemove) {
/* 102 */       grassperStacks.remove(itemToRemove);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isItemEqual(ItemStack itemstackToFind, ItemStack itemstackFound)
/*     */   {
/* 112 */     return ((itemstackFound.func_77973_b() != Witchery.Items.ARTHANA) && (itemstackFound.func_77973_b() != Witchery.Items.BOLINE)) || ((itemstackFound.func_77973_b() == itemstackToFind.func_77973_b()) || (itemstackFound.func_77969_a(itemstackToFind)));
/*     */   }
/*     */   
/*     */ 
/*     */   public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
/*     */   {
/* 118 */     for (ItemStack itemstack : this.itemstacks) {
/* 119 */       steps.add(new StepSacrificeItem(itemstack, bounds, maxDistance));
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class StepSacrificeItem extends RitualStep
/*     */   {
/*     */     protected final ItemStack itemstack;
/*     */     protected final AxisAlignedBB bounds;
/*     */     protected final int maxDistance;
/*     */     protected boolean showMessages;
/*     */     
/*     */     public StepSacrificeItem(ItemStack itemstack, AxisAlignedBB bounds, int maxDistance) {
/* 131 */       super();
/* 132 */       this.itemstack = itemstack;
/* 133 */       this.bounds = bounds;
/* 134 */       this.maxDistance = maxDistance;
/* 135 */       this.showMessages = false;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/* 140 */       if (ticks % 20L != 0L) {
/* 141 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/* 144 */       if (!world.field_72995_K) {
/* 145 */         List itemEntities = world.func_72872_a(EntityItem.class, this.bounds);
/*     */         
/* 147 */         if (Config.instance().traceRites()) {
/* 148 */           for (Object obj : itemEntities) {
/* 149 */             EntityItem entity = (EntityItem)obj;
/* 150 */             ItemStack foundItemstack = entity.func_92059_d();
/* 151 */             Log.instance().traceRite(String.format(" * found: %s, distance: %f", new Object[] { foundItemstack.toString(), Double.valueOf(Sacrifice.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ)) }));
/*     */           }
/*     */         }
/*     */         
/* 155 */         for (Object obj : itemEntities) {
/* 156 */           EntityItem entity = (EntityItem)obj;
/* 157 */           ItemStack foundItemstack = entity.func_92059_d();
/*     */           
/* 159 */           if ((SacrificeItem.isItemEqual(this.itemstack, foundItemstack)) && (Sacrifice.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) <= this.maxDistance))
/*     */           {
/* 161 */             ItemStack sacrificedItemstack = ItemStack.func_77944_b(foundItemstack);
/* 162 */             sacrificedItemstack.field_77994_a = 1;
/* 163 */             ritual.sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(entity)));
/*     */             
/* 165 */             if ((foundItemstack.func_77985_e()) && (foundItemstack.field_77994_a > 1)) {
/* 166 */               foundItemstack.field_77994_a -= 1;
/*     */             } else {
/* 168 */               world.func_72900_e(entity);
/*     */             }
/*     */             
/* 171 */             ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, entity, 0.5D, 1.0D, 16);
/*     */             
/* 173 */             return RitualStep.Result.COMPLETED;
/*     */           }
/*     */         }
/*     */         
/* 177 */         int radius = 5;
/* 178 */         for (int x = posX - 5; x <= posX + 5; x++) {
/* 179 */           for (int z = posZ - 5; z <= posZ + 5; z++) {
/* 180 */             Block blockID = world.func_147439_a(x, posY, z);
/* 181 */             if (blockID == Witchery.Blocks.GRASSPER) {
/* 182 */               TileEntity tile = world.func_147438_o(x, posY, z);
/* 183 */               if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
/* 184 */                 BlockGrassper.TileEntityGrassper grassper = (BlockGrassper.TileEntityGrassper)tile;
/*     */                 
/* 186 */                 ItemStack stack = grassper.func_70301_a(0);
/* 187 */                 if ((stack != null) && (SacrificeItem.isItemEqual(this.itemstack, stack))) {
/* 188 */                   ItemStack sacrificedItemstack = ItemStack.func_77944_b(stack);
/* 189 */                   sacrificedItemstack.field_77994_a = 1;
/* 190 */                   ritual.sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(tile)));
/* 191 */                   if ((stack.func_77985_e()) && (stack.field_77994_a > 1)) {
/* 192 */                     stack.field_77994_a -= 1;
/*     */                   } else {
/* 194 */                     grassper.func_70299_a(0, null);
/*     */                   }
/* 196 */                   ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, world, 0.5D + x, 0.8D + posY, 0.5D + z, 0.5D, 1.0D, 16);
/* 197 */                   return RitualStep.Result.COMPLETED;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 205 */       if (this.showMessages) {
/* 206 */         RiteRegistry.RiteError("witchery.rite.missingitem", ritual.getInitiatingPlayerName(), world);
/*     */       }
/*     */       
/* 209 */       return RitualStep.Result.ABORTED_REFUND;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 214 */       return String.format("StepSacrificeItem: %s, maxDistance: %d", new Object[] { this.itemstack.toString(), Integer.valueOf(this.maxDistance) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/SacrificeItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */