/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockPlacedItem;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemMysticBranch extends ItemBase
/*     */ {
/*     */   private static final float THRESHOLD_ORTHOGONAL = 7.0F;
/*     */   private static final int MAX_STROKES = 15;
/*     */   
/*     */   public ItemMysticBranch()
/*     */   {
/*  32 */     func_77625_d(1);
/*  33 */     func_77664_n();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  39 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d()
/*     */   {
/*  45 */     return true;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack stack)
/*     */   {
/*  50 */     return EnumAction.block;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
/*     */   {
/*  56 */     return super.onDroppedByPlayer(item, player);
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack stack)
/*     */   {
/*  61 */     return 36000;
/*     */   }
/*     */   
/*     */   public boolean hasEffect(ItemStack par1ItemStack, int pass)
/*     */   {
/*  66 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int invSlot, boolean isHeld) {}
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  76 */     NBTTagCompound nbtTag = player.getEntityData();
/*  77 */     if (!player.field_70170_p.field_72995_K) {
/*  78 */       nbtTag.func_82580_o("WITCSpellEffectID");
/*  79 */       nbtTag.func_82580_o("WITCSpellEffectEnhanced");
/*     */     }
/*     */     
/*  82 */     nbtTag.func_74773_a("Strokes", new byte[0]);
/*  83 */     nbtTag.func_74776_a("startPitch", player.field_70125_A);
/*  84 */     nbtTag.func_74776_a("startYaw", player.field_70759_as);
/*     */     
/*  86 */     player.func_71008_a(stack, func_77626_a(stack));
/*     */     
/*  88 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  94 */     if ((world.func_147439_a(x, y, z) == Witchery.Blocks.ALTAR) && (side == 1) && (world.func_147439_a(x, y + 1, z) == net.minecraft.init.Blocks.field_150350_a))
/*     */     {
/*  96 */       BlockPlacedItem.placeItemInWorld(stack, player, world, x, y + 1, z);
/*  97 */       player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*  98 */       return !world.field_72995_K;
/*     */     }
/* 100 */     return super.func_77648_a(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*     */   {
/* 108 */     if (player.field_70170_p.field_72995_K) {
/* 109 */       NBTTagCompound nbtTag = player.getEntityData();
/* 110 */       if (nbtTag == null) {
/* 111 */         return;
/*     */       }
/*     */       
/* 114 */       float yawDiff = nbtTag.func_74760_g("startYaw") - player.field_70759_as;
/* 115 */       float pitchDiff = nbtTag.func_74760_g("startPitch") - player.field_70125_A;
/*     */       
/* 117 */       byte[] strokes = nbtTag.func_74770_j("Strokes");
/*     */       
/* 119 */       int strokesStart = strokes.length;
/*     */       
/* 121 */       if ((!EffectRegistry.instance().contains(strokes)) && (strokesStart <= 15)) {
/* 122 */         if (pitchDiff >= 7.0F) {
/* 123 */           strokes = addNewStroke(nbtTag, strokes, (byte)0);
/* 124 */         } else if (pitchDiff <= -7.0F) {
/* 125 */           strokes = addNewStroke(nbtTag, strokes, (byte)1);
/* 126 */         } else if (yawDiff <= -7.0F) {
/* 127 */           strokes = addNewStroke(nbtTag, strokes, (byte)2);
/* 128 */         } else if (yawDiff >= 7.0F) {
/* 129 */           strokes = addNewStroke(nbtTag, strokes, (byte)3);
/*     */         }
/*     */         
/* 132 */         if (strokes.length > strokesStart) {
/* 133 */           nbtTag.func_74776_a("startPitch", player.field_70125_A);
/* 134 */           nbtTag.func_74776_a("startYaw", player.field_70759_as);
/*     */         }
/*     */         
/* 137 */         SymbolEffect effect = EffectRegistry.instance().getEffect(strokes);
/* 138 */         if (effect != null) {
/* 139 */           int level = EffectRegistry.instance().getLevel(strokes);
/* 140 */           Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketSpellPrepared(effect, level));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public byte[] addNewStroke(NBTTagCompound nbtTag, byte[] strokes, byte stroke)
/*     */   {
/* 148 */     byte[] newStrokes = new byte[strokes.length + 1];
/* 149 */     System.arraycopy(strokes, 0, newStrokes, 0, strokes.length);
/* 150 */     newStrokes[(newStrokes.length - 1)] = stroke;
/* 151 */     nbtTag.func_74773_a("Strokes", newStrokes);
/* 152 */     return newStrokes;
/*     */   }
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 157 */     NBTTagCompound nbtTag = player.getEntityData();
/* 158 */     if (nbtTag != null) {
/* 159 */       if (!world.field_72995_K) {
/* 160 */         int effectID = nbtTag.func_74762_e("WITCSpellEffectID");
/* 161 */         int level = 1;
/* 162 */         if (nbtTag.func_74764_b("WITCSpellEffectEnhanced")) {
/* 163 */           level = nbtTag.func_74762_e("WITCSpellEffectEnhanced");
/* 164 */           nbtTag.func_82580_o("WITCSpellEffectEnhanced");
/*     */         }
/* 166 */         nbtTag.func_82580_o("WITCSpellEffectID");
/*     */         
/* 168 */         SymbolEffect effect = EffectRegistry.instance().getEffect(effectID);
/* 169 */         NBTTagCompound nbtPerm = Infusion.getNBT(player);
/*     */         
/* 171 */         if (effect != null) {
/* 172 */           if ((player.field_71075_bZ.field_75098_d) || ((nbtPerm != null) && (nbtPerm.func_74764_b("witcheryInfusionID")) && (nbtPerm.func_74764_b("witcheryInfusionCharges"))))
/*     */           {
/* 174 */             if (effect.hasValidInfusion(player, nbtPerm.func_74762_e("witcheryInfusionID"))) {
/* 175 */               if (effect.hasValidKnowledge(player, nbtPerm)) {
/* 176 */                 long ticksRemaining = effect.cooldownRemaining(player, nbtPerm);
/* 177 */                 if ((ticksRemaining <= 0L) || (player.field_71075_bZ.field_75098_d)) {
/* 178 */                   if (level > 1) {
/* 179 */                     int newLevel = 1;
/* 180 */                     if (player.func_70644_a(Witchery.Potions.WORSHIP)) {
/* 181 */                       PotionEffect potion = player.func_70660_b(Witchery.Potions.WORSHIP);
/*     */                       
/* 183 */                       if (level <= potion.func_76458_c() + 2) {
/* 184 */                         newLevel = level;
/*     */                       }
/*     */                     }
/* 187 */                     level = newLevel;
/*     */                   }
/*     */                   
/* 190 */                   if ((player.field_71075_bZ.field_75098_d) || (nbtPerm.func_74762_e("witcheryInfusionCharges") >= effect.getChargeCost(world, player, level)))
/*     */                   {
/*     */ 
/* 193 */                     effect.perform(world, player, level);
/* 194 */                     if (!player.field_71075_bZ.field_75098_d) {
/* 195 */                       Infusion.setCurrentEnergy(player, nbtPerm.func_74762_e("witcheryInfusionCharges") - effect.getChargeCost(world, player, level));
/*     */                     }
/*     */                     
/*     */                   }
/*     */                   else
/*     */                   {
/* 201 */                     ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.branch.nocharges", new Object[0]);
/*     */                     
/* 203 */                     SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */                   }
/*     */                 } else {
/* 206 */                   ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.branch.effectoncooldown", new Object[] { Long.valueOf(com.emoniph.witchery.util.TimeUtil.ticksToSecs(ticksRemaining)).toString() });
/*     */                   
/*     */ 
/* 209 */                   SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */                 }
/*     */               } else {
/* 212 */                 ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.branch.unknowneffect", new Object[0]);
/*     */                 
/* 214 */                 SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */               }
/*     */             } else {
/* 217 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.branch.infernalrequired", new Object[0]);
/*     */               
/* 219 */               SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */             }
/*     */           } else {
/* 222 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.branch.infusionrequired", new Object[0]);
/*     */             
/* 224 */             SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */           }
/*     */         } else {
/* 227 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.branch.unknownsymbol", new Object[0]);
/* 228 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */         }
/*     */       } else {
/* 231 */         nbtTag.func_82580_o("Strokes");
/* 232 */         nbtTag.func_82580_o("startYaw");
/* 233 */         nbtTag.func_82580_o("startPitch");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemMysticBranch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */