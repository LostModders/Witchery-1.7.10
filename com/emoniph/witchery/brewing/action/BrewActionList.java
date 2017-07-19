/*     */ package com.emoniph.witchery.brewing.action;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.util.BlockPosition;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BrewActionList
/*     */ {
/*     */   private final NBTTagCompound nbtBrew;
/*  22 */   public final ArrayList<BrewAction> actions = new ArrayList();
/*  23 */   public final ArrayList<ItemStack> items = new ArrayList();
/*     */   
/*     */   public BrewActionList(NBTTagCompound nbtBrew, Hashtable<BrewItemKey, BrewAction> actionRegistry) {
/*  26 */     this.nbtBrew = nbtBrew;
/*  27 */     if (nbtBrew != null) {
/*  28 */       NBTTagList nbtItems = nbtBrew.func_150295_c("Items", 10);
/*  29 */       int i = 0; for (int count = nbtItems.func_74745_c(); i < count; i++) {
/*  30 */         NBTTagCompound nbtItem = nbtItems.func_150305_b(i);
/*  31 */         ItemStack stack = ItemStack.func_77949_a(nbtItem);
/*  32 */         BrewAction brewAction = (BrewAction)actionRegistry.get(BrewItemKey.fromStack(stack));
/*  33 */         if (brewAction != null) {
/*  34 */           this.actions.add(brewAction);
/*  35 */           this.items.add(stack);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getTopItemStack() {
/*  42 */     return size() > 0 ? (ItemStack)this.items.get(size() - 1) : null;
/*     */   }
/*     */   
/*     */   public BrewAction getTopAction() {
/*  46 */     return size() > 0 ? (BrewAction)this.actions.get(size() - 1) : null;
/*     */   }
/*     */   
/*     */   public int size() {
/*  50 */     return this.actions.size();
/*     */   }
/*     */   
/*     */   public NBTTagCompound getTagCompound() {
/*  54 */     return this.nbtBrew;
/*     */   }
/*     */   
/*     */   public void nullifyItems(BrewAction brewAction, NBTTagList nbtItems) {
/*  58 */     brewAction.processNullifaction(this.actions, nbtItems);
/*     */   }
/*     */   
/*     */   public void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers) {
/*  62 */     for (int i = 0; i < this.actions.size(); i++) {
/*  63 */       BrewAction action = (BrewAction)this.actions.get(i);
/*  64 */       if (action.augmentEffectLevels(modifiers.effectLevel)) {
/*  65 */         action.augmentEffectModifiers(modifiers);
/*  66 */         action.applyToEntity(world, targetEntity, modifiers, (ItemStack)this.items.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers)
/*     */   {
/*  73 */     for (int i = 0; i < this.actions.size(); i++) {
/*  74 */       BrewAction action = (BrewAction)this.actions.get(i);
/*  75 */       if (action.augmentEffectLevels(modifiers.effectLevel)) {
/*  76 */         action.augmentEffectModifiers(modifiers);
/*  77 */         action.applyToBlock(world, x, y, z, side, radius, modifiers, (ItemStack)this.items.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers) {
/*  83 */     for (int i = 0; i < this.actions.size(); i++) {
/*  84 */       BrewAction action = (BrewAction)this.actions.get(i);
/*  85 */       action.prepareRitual(world, x, y, z, modifiers, (ItemStack)this.items.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers)
/*     */   {
/*  91 */     for (int i = 0; i < this.actions.size(); i++) {
/*  92 */       BrewAction action = (BrewAction)this.actions.get(i);
/*  93 */       if (action.augmentEffectLevels(modifiers.effectLevel)) {
/*  94 */         action.augmentEffectModifiers(modifiers);
/*  95 */         action.applyRitualToBlock(world, x, y, z, side, radius, ritualModifiers, modifiers, (ItemStack)this.items.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyRitualToEnitity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers)
/*     */   {
/* 102 */     for (int i = 0; i < this.actions.size(); i++) {
/* 103 */       BrewAction action = (BrewAction)this.actions.get(i);
/* 104 */       if (action.augmentEffectLevels(modifiers.effectLevel)) {
/* 105 */         action.augmentEffectModifiers(modifiers);
/* 106 */         action.applyRitualToEntity(world, targetEntity, ritualModifiers, modifiers, (ItemStack)this.items.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers) {
/* 112 */     for (int i = 0; i < this.actions.size(); i++) {
/* 113 */       BrewAction action = (BrewAction)this.actions.get(i);
/* 114 */       if (!action.isRitualTargetLocationValid(server, world, x, y, z, target, modifiers)) {
/* 115 */         return false;
/*     */       }
/*     */     }
/* 118 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */