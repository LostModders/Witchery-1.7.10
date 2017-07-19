/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer.QuestState;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockStatueWerewolf extends BlockBaseContainer
/*     */ {
/*     */   public BlockStatueWerewolf()
/*     */   {
/*  36 */     super(Material.field_151576_e, TileEntityStatueWerewolf.class);
/*  37 */     func_149752_b(1000.0F);
/*  38 */     func_149711_c(2.5F);
/*  39 */     func_149672_a(field_149769_e);
/*  40 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/*  45 */     switch (MathHelper.func_76128_c(entity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3) {
/*     */     case 0: 
/*  47 */       world.func_72921_c(x, y, z, 2, 2);
/*  48 */       break;
/*     */     case 1: 
/*  50 */       world.func_72921_c(x, y, z, 5, 2);
/*  51 */       break;
/*     */     case 2: 
/*  53 */       world.func_72921_c(x, y, z, 3, 2);
/*  54 */       break;
/*     */     case 3: 
/*  56 */       world.func_72921_c(x, y, z, 4, 2);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  64 */     if (!world.field_72995_K) {
/*  65 */       TileEntityStatueWerewolf statue = (TileEntityStatueWerewolf)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueWerewolf.class);
/*     */       
/*  67 */       if (statue != null) {
/*  68 */         int meta = world.func_72805_g(x, y, z);
/*  69 */         ForgeDirection direction = ForgeDirection.getOrientation(meta);
/*  70 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  71 */         ItemStack heldStack = player.func_70694_bm();
/*  72 */         SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playOnlyTo(player, 1.0F, 1.0F);
/*  73 */         int level = playerEx.getWerewolfLevel();
/*  74 */         int GOLD_REQUIRED = 3;
/*  75 */         if ((level >= 2) && (heldStack != null) && (heldStack.func_77973_b() == Items.field_151043_k) && (heldStack.field_77994_a >= 3))
/*     */         {
/*  77 */           ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.mooncharmcrafted", new Object[0]);
/*     */           
/*  79 */           heldStack.func_77979_a(3);
/*  80 */           EntityItem itemEntity = new EntityItem(world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, new ItemStack(Witchery.Items.MOON_CHARM));
/*     */           
/*     */ 
/*  83 */           itemEntity.field_70159_w = (itemEntity.field_70181_x = itemEntity.field_70179_y = 0.0D);
/*  84 */           world.func_72838_d(itemEntity);
/*  85 */           ParticleEffect.REDDUST.send(SoundEffect.RANDOM_ORB, itemEntity, 0.2D, 0.2D, 16);
/*     */         } else {
/*  87 */           switch (level) {
/*     */           case 0: 
/*  89 */             ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, player, 1.0D, 1.0D, 16);
/*  90 */             player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, TimeUtil.secsToTicks(60), 0));
/*  91 */             ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.notworthy", new Object[0]);
/*  92 */             break;
/*     */           
/*     */           case 1: 
/*  95 */             if ((heldStack != null) && (heldStack.func_77973_b() == Items.field_151043_k)) {
/*  96 */               if (heldStack.field_77994_a >= 3) {
/*  97 */                 ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level2complete", new Object[0]);
/*     */                 
/*  99 */                 heldStack.func_77979_a(3);
/* 100 */                 EntityItem itemEntity = new EntityItem(world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, new ItemStack(Witchery.Items.MOON_CHARM));
/*     */                 
/*     */ 
/* 103 */                 itemEntity.field_70159_w = (itemEntity.field_70181_x = itemEntity.field_70179_y = 0.0D);
/* 104 */                 world.func_72838_d(itemEntity);
/* 105 */                 ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, itemEntity, 0.2D, 0.2D, 16);
/* 106 */                 playerEx.increaseWerewolfLevel();
/*     */               } else {
/* 108 */                 ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level2progress", new Object[] { Integer.valueOf(3).toString(), Integer.valueOf(3 - heldStack.field_77994_a).toString() });
/*     */               }
/*     */               
/*     */ 
/*     */             }
/*     */             else {
/* 114 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level2begin", new Object[] { Integer.valueOf(3).toString() });
/*     */             }
/*     */             
/* 117 */             break;
/*     */           case 2: 
/* 119 */             int MUTTON_REQUIRED = 30;
/* 120 */             if ((heldStack != null) && (Witchery.Items.GENERIC.itemMuttonRaw.isMatch(heldStack))) {
/* 121 */               if (heldStack.field_77994_a >= 30) {
/* 122 */                 ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level3complete", new Object[0]);
/*     */                 
/* 124 */                 heldStack.func_77979_a(30);
/* 125 */                 ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */                 
/* 127 */                 playerEx.increaseWerewolfLevel();
/*     */               } else {
/* 129 */                 ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level3progress", new Object[] { Integer.valueOf(30).toString(), Integer.valueOf(30 - heldStack.field_77994_a).toString() });
/*     */               }
/*     */               
/*     */ 
/*     */             }
/*     */             else {
/* 135 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level3begin", new Object[] { Integer.valueOf(30).toString() });
/*     */             }
/*     */             
/* 138 */             break;
/*     */           case 3: 
/* 140 */             int TONGUES_REQUIRED = 10;
/* 141 */             if ((heldStack != null) && (Witchery.Items.GENERIC.itemDogTongue.isMatch(heldStack))) {
/* 142 */               if (heldStack.field_77994_a >= 10) {
/* 143 */                 ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level4complete", new Object[0]);
/*     */                 
/* 145 */                 heldStack.func_77979_a(10);
/* 146 */                 ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */                 
/* 148 */                 playerEx.increaseWerewolfLevel();
/*     */               } else {
/* 150 */                 ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level4progress", new Object[] { Integer.valueOf(10).toString(), Integer.valueOf(10 - heldStack.field_77994_a).toString() });
/*     */               }
/*     */               
/*     */ 
/*     */             }
/*     */             else {
/* 156 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level4begin", new Object[] { Integer.valueOf(10).toString() });
/*     */             }
/*     */             
/* 159 */             break;
/*     */           case 4: 
/* 161 */             switch (playerEx.getWolfmanQuestState()) {
/*     */             case NOT_STATED: 
/* 163 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level5begin", new Object[0]);
/*     */               
/* 165 */               EntityItem itemEntity = new EntityItem(world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, new ItemStack(Witchery.Items.HORN_OF_THE_HUNT));
/*     */               
/*     */ 
/* 168 */               itemEntity.field_70159_w = (itemEntity.field_70181_x = itemEntity.field_70179_y = 0.0D);
/* 169 */               world.func_72838_d(itemEntity);
/* 170 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, itemEntity, 0.2D, 0.2D, 16);
/* 171 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
/* 172 */               break;
/*     */             case STARTED: 
/* 174 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level5progress", new Object[0]);
/*     */               
/* 176 */               break;
/*     */             case COMPLETE: 
/* 178 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level5complete", new Object[0]);
/*     */               
/* 180 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */               
/* 182 */               playerEx.increaseWerewolfLevel();
/*     */             }
/*     */             
/* 185 */             break;
/*     */           case 5: 
/* 187 */             int KILLS_REQUIRED = 10;
/* 188 */             if (playerEx.getWolfmanQuestCounter() >= 10) {
/* 189 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
/*     */             }
/* 191 */             switch (playerEx.getWolfmanQuestState()) {
/*     */             case NOT_STATED: 
/* 193 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level6begin", new Object[] { Integer.valueOf(10).toString() });
/*     */               
/* 195 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
/* 196 */               break;
/*     */             case STARTED: 
/* 198 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level6progress", new Object[] { Integer.valueOf(10).toString(), Integer.valueOf(10 - playerEx.getWolfmanQuestCounter()).toString() });
/*     */               
/*     */ 
/*     */ 
/* 202 */               break;
/*     */             case COMPLETE: 
/* 204 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level6complete", new Object[0]);
/*     */               
/* 206 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */               
/* 208 */               playerEx.increaseWerewolfLevel();
/*     */             }
/*     */             
/* 211 */             break;
/*     */           case 6: 
/* 213 */             int PLACES_HOWLED_AT = 16;
/* 214 */             if (playerEx.getWolfmanQuestCounter() >= 16) {
/* 215 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
/*     */             }
/* 217 */             switch (playerEx.getWolfmanQuestState()) {
/*     */             case NOT_STATED: 
/* 219 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level7begin", new Object[] { Integer.valueOf(16).toString() });
/*     */               
/* 221 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
/* 222 */               break;
/*     */             case STARTED: 
/* 224 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level7progress", new Object[] { Integer.valueOf(16).toString(), Integer.valueOf(16 - playerEx.getWolfmanQuestCounter()).toString() });
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 229 */               break;
/*     */             case COMPLETE: 
/* 231 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level7complete", new Object[0]);
/*     */               
/* 233 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */               
/* 235 */               playerEx.increaseWerewolfLevel();
/*     */             }
/*     */             
/* 238 */             break;
/*     */           case 7: 
/* 240 */             int WOLVES_TAMED = 6;
/* 241 */             if (playerEx.getWolfmanQuestCounter() >= 6) {
/* 242 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
/*     */             }
/* 244 */             switch (playerEx.getWolfmanQuestState()) {
/*     */             case NOT_STATED: 
/* 246 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level8begin", new Object[] { Integer.valueOf(6).toString() });
/*     */               
/* 248 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
/* 249 */               break;
/*     */             case STARTED: 
/* 251 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level8progress", new Object[] { Integer.valueOf(6).toString(), Integer.valueOf(6 - playerEx.getWolfmanQuestCounter()).toString() });
/*     */               
/*     */ 
/* 254 */               break;
/*     */             case COMPLETE: 
/* 256 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level8complete", new Object[0]);
/*     */               
/* 258 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */               
/* 260 */               playerEx.increaseWerewolfLevel();
/*     */             }
/*     */             
/* 263 */             break;
/*     */           case 8: 
/* 265 */             int PIGMEN_KILLED = 30;
/* 266 */             if (playerEx.getWolfmanQuestCounter() >= 30) {
/* 267 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
/*     */             }
/* 269 */             switch (playerEx.getWolfmanQuestState()) {
/*     */             case NOT_STATED: 
/* 271 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level9begin", new Object[] { Integer.valueOf(30).toString() });
/*     */               
/* 273 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
/* 274 */               break;
/*     */             case STARTED: 
/* 276 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level9progress", new Object[] { Integer.valueOf(30).toString(), Integer.valueOf(30 - playerEx.getWolfmanQuestCounter()).toString() });
/*     */               
/*     */ 
/* 279 */               break;
/*     */             case COMPLETE: 
/* 281 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level9complete", new Object[0]);
/*     */               
/* 283 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */               
/* 285 */               playerEx.increaseWerewolfLevel();
/*     */             }
/*     */             
/* 288 */             break;
/*     */           case 9: 
/* 290 */             int PEOPLE_KILLED = 1;
/* 291 */             if (playerEx.getWolfmanQuestCounter() >= 1) {
/* 292 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
/*     */             }
/* 294 */             switch (playerEx.getWolfmanQuestState()) {
/*     */             case NOT_STATED: 
/* 296 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10begin", new Object[] { Integer.valueOf(1).toString() });
/*     */               
/* 298 */               playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
/* 299 */               break;
/*     */             case STARTED: 
/* 301 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10progress", new Object[] { Integer.valueOf(1).toString(), Integer.valueOf(1 - playerEx.getWolfmanQuestCounter()).toString() });
/*     */               
/*     */ 
/*     */ 
/* 305 */               break;
/*     */             case COMPLETE: 
/* 307 */               ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10complete", new Object[0]);
/*     */               
/* 309 */               ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, direction.offsetX + 0.5D + x, 1.1D + y, 0.5D + z + direction.offsetZ, 0.2D, 0.2D, 16);
/*     */               
/* 311 */               playerEx.increaseWerewolfLevel();
/*     */             }
/*     */             
/* 314 */             break;
/*     */           case 10: 
/* 316 */             SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playOnlyTo(player, 1.0F, 1.0F);
/* 317 */             ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "witchery.werewolf.level10complete", new Object[0]);
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 324 */       return true;
/*     */     }
/* 326 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 331 */     return 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 337 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 342 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 347 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */   public static class TileEntityStatueWerewolf
/*     */     extends TileEntity
/*     */   {
/*     */     public boolean canUpdate()
/*     */     {
/* 358 */       return false;
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 363 */       super.func_145841_b(nbtRoot);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 368 */       super.func_145839_a(nbtRoot);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockStatueWerewolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */