/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.entity.EntityBanshee;
/*     */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*     */ import com.emoniph.witchery.entity.EntitySpectre;
/*     */ import com.emoniph.witchery.entity.EntitySummonedUndead;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemSpectralStone extends ItemBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   IIcon item0;
/*     */   @SideOnly(Side.CLIENT)
/*     */   IIcon item1;
/*     */   @SideOnly(Side.CLIENT)
/*     */   IIcon item2;
/*     */   @SideOnly(Side.CLIENT)
/*     */   IIcon item3;
/*     */   private static final int SPECTRE = 1;
/*     */   private static final int BANSHEE = 2;
/*     */   private static final int POLTERGEIST = 3;
/*     */   private static final int TICKS_TO_ACTIVATE = 40;
/*     */   
/*     */   public ItemSpectralStone()
/*     */   {
/*  38 */     func_77625_d(16);
/*  39 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item item, net.minecraft.creativetab.CreativeTabs tab, List itemList)
/*     */   {
/*  45 */     itemList.add(new ItemStack(item, 1, 0));
/*  46 */     itemList.add(new ItemStack(item, 1, 17));
/*  47 */     itemList.add(new ItemStack(item, 1, 18));
/*  48 */     itemList.add(new ItemStack(item, 1, 19));
/*     */   }
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/*  63 */     super.func_94581_a(iconRegister);
/*  64 */     this.item0 = this.field_77791_bV;
/*  65 */     this.item1 = iconRegister.func_94245_a(func_111208_A() + ".spectre");
/*  66 */     this.item2 = iconRegister.func_94245_a(func_111208_A() + ".banshee");
/*  67 */     this.item3 = iconRegister.func_94245_a(func_111208_A() + ".poltergeist");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int damage)
/*     */   {
/*  75 */     switch (getBeingFromMeta(damage)) {
/*     */     case 0: 
/*     */     default: 
/*  78 */       return this.item0;
/*     */     case 1: 
/*  80 */       return this.item1;
/*     */     case 2: 
/*  82 */       return this.item2;
/*     */     }
/*  84 */     return this.item3;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean extraTip)
/*     */   {
/*  90 */     int creature = getBeingFromMeta(stack.func_77960_j());
/*  91 */     int quantity = Math.min(getQuantityFromMeta(stack.func_77960_j()), 4);
/*  92 */     switch (creature) {
/*     */     case 1: 
/*  94 */       list.add(String.format("%s: %d", new Object[] { Witchery.resource("entity.witchery.spectre.name"), Integer.valueOf(quantity) }));
/*  95 */       break;
/*     */     case 2: 
/*  97 */       list.add(String.format("%s: %d", new Object[] { Witchery.resource("entity.witchery.banshee.name"), Integer.valueOf(quantity) }));
/*  98 */       break;
/*     */     case 3: 
/* 100 */       list.add(String.format("%s: %d", new Object[] { Witchery.resource("entity.witchery.poltergeist.name"), Integer.valueOf(quantity) }));
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack stack)
/*     */   {
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public static int metaFromCreature(Class<? extends EntitySummonedUndead> creatureType, int quantity) {
/* 111 */     if (creatureType == EntitySpectre.class)
/* 112 */       return 0x1 | quantity << 4;
/* 113 */     if (creatureType == EntityBanshee.class)
/* 114 */       return 0x2 | quantity << 4;
/* 115 */     if (creatureType == EntityPoltergeist.class) {
/* 116 */       return 0x3 | quantity << 4;
/*     */     }
/* 118 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getBeingFromMeta(int meta)
/*     */   {
/* 128 */     int critter = meta & 0xF;
/* 129 */     if ((critter < 0) || (critter > 15)) {
/* 130 */       critter = 0;
/*     */     }
/* 132 */     return critter;
/*     */   }
/*     */   
/*     */   private int getQuantityFromMeta(int meta) {
/* 136 */     int quantity = meta >>> 4 & 0x7;
/* 137 */     if ((quantity < 0) || (quantity >= 8)) {
/* 138 */       quantity = 0;
/*     */     }
/* 140 */     return quantity;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/* 147 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */   public net.minecraft.item.EnumAction func_77661_b(ItemStack stack)
/*     */   {
/* 152 */     return net.minecraft.item.EnumAction.bow;
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack stack)
/*     */   {
/* 157 */     return com.emoniph.witchery.util.TimeUtil.secsToTicks(20);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*     */   {
/* 164 */     World world = player.field_70170_p;
/* 165 */     int elapsedTicks = func_77626_a(stack) - countdown;
/* 166 */     if (elapsedTicks == 40) {
/* 167 */       SoundEffect.NOTE_PLING.playOnlyTo(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 173 */     int elapsedTicks = func_77626_a(stack) - countdown;
/* 174 */     int creature = getBeingFromMeta(stack.func_77960_j());
/* 175 */     int quantity = Math.min(getQuantityFromMeta(stack.func_77960_j()), 3);
/* 176 */     if ((elapsedTicks >= 40) && (creature > 0) && (quantity > 0)) {
/* 177 */       net.minecraft.util.MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.doCustomRayTrace(world, player, true, 16.0D);
/* 178 */       int[] coords = com.emoniph.witchery.util.BlockUtil.getBlockCoords(world, mop, true);
/* 179 */       Class<? extends EntitySummonedUndead> theClass = null;
/* 180 */       if (coords != null) {
/* 181 */         switch (creature) {
/*     */         case 1: 
/* 183 */           theClass = EntitySpectre.class;
/* 184 */           break;
/*     */         case 2: 
/* 186 */           theClass = EntityBanshee.class;
/* 187 */           break;
/*     */         case 3: 
/* 189 */           theClass = EntityPoltergeist.class;
/* 190 */           break;
/*     */         default: 
/* 192 */           SoundEffect.NOTE_SNARE.playOnlyTo(player);
/* 193 */           return;
/*     */         }
/*     */         
/* 196 */         for (int i = 0; i < quantity; i++) {
/* 197 */           EntitySummonedUndead entity = (EntitySummonedUndead)com.emoniph.witchery.infusion.Infusion.spawnCreature(world, theClass, coords[0], coords[1], coords[2], null, 0, 1, com.emoniph.witchery.util.ParticleEffect.INSTANT_SPELL, null);
/* 198 */           if (entity != null) {
/* 199 */             com.emoniph.witchery.util.CreatureUtil.spawnWithEgg(entity, true);
/* 200 */             entity.setSummoner(player.func_70005_c_());
/* 201 */             com.emoniph.witchery.brewing.potions.PotionEnslaved.setEnslaverForMob(entity, player);
/*     */           }
/*     */         }
/* 204 */         if (!player.field_71075_bZ.field_75098_d) {
/* 205 */           if (stack.field_77994_a > 1) {
/* 206 */             ItemStack newStack = stack.func_77979_a(1);
/* 207 */             newStack.func_77964_b(0);
/* 208 */             if (!player.field_71071_by.func_70441_a(newStack)) {
/* 209 */               if (!world.field_72995_K) {
/* 210 */                 world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, newStack));
/*     */               }
/* 212 */             } else if ((player instanceof EntityPlayerMP)) {
/* 213 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           } else {
/* 216 */             stack.func_77964_b(0);
/*     */           }
/*     */         }
/*     */       } else {
/* 220 */         SoundEffect.NOTE_SNARE.playOnlyTo(player, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */     else {
/* 224 */       SoundEffect.NOTE_SNARE.playOnlyTo(player, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 230 */     player.func_71008_a(stack, func_77626_a(stack));
/* 231 */     return stack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemSpectralStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */