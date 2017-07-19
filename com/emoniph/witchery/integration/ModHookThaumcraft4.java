/*      */ package com.emoniph.witchery.integration;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.item.ItemPoppet;
/*      */ import com.emoniph.witchery.item.ItemPoppet.PoppetType;
/*      */ import cpw.mods.fml.common.event.FMLInterModComms;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ 
/*      */ public class ModHookThaumcraft4 extends ModHook
/*      */ {
/*      */   public String getModID()
/*      */   {
/*   21 */     return "Thaumcraft";
/*      */   }
/*      */   
/*      */   protected void doInit()
/*      */   {
/*   26 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_ARTICHOKE, 1, Witchery.Blocks.CROP_ARTICHOKE.getNumGrowthStages()));
/*      */     
/*   28 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_BELLADONNA, 1, Witchery.Blocks.CROP_BELLADONNA.getNumGrowthStages()));
/*      */     
/*   30 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_MANDRAKE, 1, Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()));
/*      */     
/*   32 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_SNOWBELL, 1, Witchery.Blocks.CROP_SNOWBELL.getNumGrowthStages()));
/*      */     
/*   34 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_WORMWOOD, 1, Witchery.Blocks.CROP_WORMWOOD.getNumGrowthStages()));
/*      */     
/*   36 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_MINDRAKE, 1, Witchery.Blocks.CROP_MINDRAKE.getNumGrowthStages()));
/*      */     
/*      */ 
/*   39 */     FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(Witchery.Blocks.CROP_WOLFSBANE, 1, Witchery.Blocks.CROP_WOLFSBANE.getNumGrowthStages()));
/*      */     
/*      */ 
/*   42 */     FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", "" + com.emoniph.witchery.util.Config.instance().dimensionDreamID + ":0");
/*      */     
/*   44 */     FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", "" + com.emoniph.witchery.util.Config.instance().dimensionTormentID + ":0");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void doPostInit() {}
/*      */   
/*      */ 
/*      */ 
/*      */   protected void doReduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float factor)
/*      */   {
/*   55 */     IntegrateThaumcraft.reduceMagicPower(entity, factor);
/*      */   }
/*      */   
/*      */   private static class IntegrateThaumcraft {
/*      */     public static void reduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float percentage) {
/*   60 */       if ((entity instanceof net.minecraft.entity.player.EntityPlayer)) {
/*   61 */         net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer)entity;
/*   62 */         for (java.util.Iterator i$ = Aspect.aspects.values().iterator(); i$.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*   66 */             goto 83)
/*      */         {
/*   62 */           Aspect aspect = (Aspect)i$.next();
/*   63 */           AspectList list = new AspectList().add(aspect, 100);
/*   64 */           int countdown = percentage == 1.0F ? 1000 : (int)Math.max(150.0F * percentage, 1.0F);
/*   65 */           if ((thaumcraft.api.ThaumcraftApiHelper.consumeVisFromInventory(player, list)) && (countdown > 0)) {
/*   66 */             countdown--;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public static void registerAspects()
/*      */     {
/*   75 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.WICKER_BUNDLE), new int[] { 0, 4, 8 }, new AspectList().add(Aspect.TREE, 2));
/*      */       
/*   77 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.WICKER_BUNDLE), new int[] { 1, 5, 9 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.FLESH, 2).add(Aspect.DEATH, 3).add(Aspect.FIRE, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   83 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STOCKADE), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2));
/*      */       
/*   85 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STATUE_OF_WORSHIP), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 3).add(Aspect.MIND, 3).add(Aspect.SOUL, 2).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   91 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.LOG), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
/*      */       
/*   93 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PLANKS), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  102 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STAIRS_ALDER), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
/*      */       
/*  104 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STAIRS_HAWTHORN), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
/*      */       
/*  106 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STAIRS_ROWAN), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
/*      */       
/*      */ 
/*  109 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SAPLING), new int[] { 0 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1).add(Aspect.PLANT, 1));
/*      */       
/*  111 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SAPLING), new int[] { 1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1).add(Aspect.PLANT, 1));
/*      */       
/*  113 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SAPLING), new int[] { 2 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.ORDER, 1).add(Aspect.PLANT, 1));
/*      */       
/*  115 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.LEAVES), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/*  118 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.DOOR_ALDER), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1).add(Aspect.TRAP, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  123 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.DOOR_ROWAN), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1));
/*      */       
/*      */ 
/*  126 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemRowanBerries.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.HUNGER, 1));
/*      */       
/*      */ 
/*      */ 
/*  130 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.ALTAR), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 3).add(Aspect.EARTH, 4).add(Aspect.MECHANISM, 3).add(Aspect.ENERGY, 3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  136 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.DISTILLERY_IDLE), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.METAL, 8).add(Aspect.MECHANISM, 3).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.FIRE, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  142 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.DISTILLERY_BURNING), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.METAL, 8).add(Aspect.MECHANISM, 3).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.FIRE, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  149 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.OVEN_IDLE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 14).add(Aspect.MECHANISM, 3).add(Aspect.FIRE, 1).add(Aspect.AIR, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  154 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.OVEN_BURNING), new int[] { -1 }, new AspectList().add(Aspect.METAL, 14).add(Aspect.MECHANISM, 3).add(Aspect.FIRE, 3).add(Aspect.AIR, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  160 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SPINNING_WHEEL), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MECHANISM, 3).add(Aspect.CLOTH, 3));
/*      */       
/*      */ 
/*  163 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CROP_BELLADONNA), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.POISON, 4).add(Aspect.DEATH, 4).add(Aspect.CROP, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  168 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CROP_MANDRAKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.CROP, 1));
/*      */       
/*  170 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CROP_ARTICHOKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 2).add(Aspect.CROP, 1));
/*      */       
/*  172 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CROP_SNOWBELL), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.COLD, 2).add(Aspect.CROP, 1));
/*      */       
/*      */ 
/*  175 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSeedsTreefyd.damageValue }, new AspectList().add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*      */ 
/*  179 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEDS_BELLADONNA), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.POISON, 1));
/*      */       
/*  181 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEDS_MANDRAKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.EARTH, 1));
/*      */       
/*  183 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEDS_ARTICHOKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.WATER, 1));
/*      */       
/*  185 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEDS_SNOWBELL), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.COLD, 1));
/*      */       
/*      */ 
/*  188 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.KETTLE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.WATER, 4).add(Aspect.CRAFT, 8));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  194 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CAULDRON), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.WATER, 4).add(Aspect.CRAFT, 8));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  200 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.POPPET_SHELF), new int[] { -1 }, new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 4).add(Aspect.SOUL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  205 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SPANISH_MOSS), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.SOUL, 1));
/*      */       
/*  207 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.LEAPING_LILY), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 1).add(Aspect.MOTION, 3).add(Aspect.FLIGHT, 3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  212 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.EMBER_MOSS), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 2));
/*      */       
/*      */ 
/*  215 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLINT_WEED), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
/*      */       
/*  217 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLOW_GLOBE), new int[] { -1 }, new AspectList().add(Aspect.FIRE, 1));
/*      */       
/*      */ 
/*  220 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.ALLURING_SKULL), new int[] { -1 }, new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.UNDEAD, 6).add(Aspect.GREED, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  225 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.WITCH_HAND), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.FLESH, 1).add(Aspect.MAGIC, 2));
/*      */       
/*  227 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.ARTHANA), new int[] { -1 }, new AspectList().add(Aspect.GREED, 6).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 2).add(Aspect.WEAPON, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  232 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.HUNTSMANS_SPEAR), new int[] { -1 }, new AspectList().add(Aspect.TREE, 3).add(Aspect.MAGIC, 2).add(Aspect.WEAPON, 2));
/*      */       
/*      */ 
/*  235 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemMysticUnguent.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.ENTROPY, 10));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  240 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBranchEnt.damageValue }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  243 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.MYSTIC_BRANCH), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 2).add(Aspect.MAGIC, 3).add(Aspect.ENTROPY, 10));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  249 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CIRCLE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 1));
/*      */       
/*  251 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLYPH_RITUAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
/*      */       
/*  253 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLYPH_OTHERWHERE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  258 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLYPH_INFERNAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.FIRE, 2).add(Aspect.TRAVEL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  264 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DIVINER_WATER), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.SENSES, 2).add(Aspect.WATER, 4).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  269 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DIVINER_LAVA), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.SENSES, 2).add(Aspect.FIRE, 2).add(Aspect.EARTH, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  274 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.TAGLOCK_KIT), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.BEAST, 1).add(Aspect.MAN, 1).add(Aspect.LIFE, 1).add(Aspect.ORDER, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  279 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.unboundPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1));
/*      */       
/*      */ 
/*  282 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.earthPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.EARTH, 1).add(Aspect.FLIGHT, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  287 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.waterPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.WATER, 1).add(Aspect.AIR, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  292 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.firePoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  297 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.foodPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.HUNGER, 1).add(Aspect.LIFE, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  302 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.toolPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.TOOL, 1).add(Aspect.CRAFT, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  307 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.armorPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 2).add(Aspect.CRAFT, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  312 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.deathPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.DEATH, 1).add(Aspect.HEAL, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  317 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.antiVoodooPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 3).add(Aspect.ARMOR, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  322 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.voodooPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 4).add(Aspect.MOTION, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  327 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POPPET), new int[] { Witchery.Items.POPPET.vampiricPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.LIFE, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  332 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemCandelabra.damageValue }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.LIGHT, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  337 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CANDELABRA), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.LIGHT, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  342 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemChaliceEmpty.damageValue }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  347 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CHALICE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  352 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemChaliceFull.damageValue }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 4).add(Aspect.VOID, 2).add(Aspect.WATER, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  357 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDreamMove.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.MOTION, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  362 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDreamDig.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.ENERGY, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  367 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDreamEat.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.HUNGER, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  372 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDreamIntensity.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.MIND, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  377 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDreamNightmare.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 4).add(Aspect.EXCHANGE, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  382 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.DREAM_CATCHER), new int[] { -1 }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.ENERGY, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  387 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBoneNeedle.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.FLESH, 1).add(Aspect.DEATH, 1));
/*      */       
/*      */ 
/*  390 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBroom.damageValue }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 2));
/*      */       
/*      */ 
/*  393 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBroomEnchanted.damageValue }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 2).add(Aspect.MAGIC, 2).add(Aspect.FLIGHT, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  398 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemAttunedStone.damageValue }, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  403 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemAttunedStoneCharged.damageValue }, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/*      */ 
/*  407 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWaystone.damageValue }, new AspectList().add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*  410 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWaystoneBound.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/*  413 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWaystonePlayerBound.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/*  416 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemMutandis.damageValue }, new AspectList().add(Aspect.EXCHANGE, 4).add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/*  419 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemMutandisExtremis.damageValue }, new AspectList().add(Aspect.EXCHANGE, 8).add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  422 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemQuicklime.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.ENTROPY, 1));
/*      */       
/*      */ 
/*  425 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemGypsum.damageValue }, new AspectList().add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*  428 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemAshWood.damageValue }, new AspectList().add(Aspect.TREE, 1).add(Aspect.FIRE, 1));
/*      */       
/*      */ 
/*  431 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBelladonnaFlower.damageValue }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.POISON, 4).add(Aspect.DEATH, 4));
/*      */       
/*      */ 
/*  434 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemMandrakeRoot.damageValue }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*  437 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemIcyNeedle.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.COLD, 4));
/*      */       
/*      */ 
/*  440 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDemonHeart.damageValue }, new AspectList().add(Aspect.FIRE, 4).add(Aspect.VOID, 2).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  445 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemCreeperHeart.damageValue }, new AspectList().add(Aspect.BEAST, 4).add(Aspect.FIRE, 1).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  450 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemFrozenHeart.damageValue }, new AspectList().add(Aspect.BEAST, 4).add(Aspect.FIRE, 1).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4).add(Aspect.COLD, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  455 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBatWool.damageValue }, new AspectList().add(Aspect.FLESH, 1).add(Aspect.FLIGHT, 1));
/*      */       
/*      */ 
/*  458 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDogTongue.damageValue }, new AspectList().add(Aspect.FLESH, 1).add(Aspect.BEAST, 1));
/*      */       
/*      */ 
/*  461 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSoftClayJar.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.VOID, 1));
/*      */       
/*      */ 
/*  464 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemEmptyClayJar.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.VOID, 1));
/*      */       
/*      */ 
/*  467 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemFoulFume.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*  470 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDiamondVapour.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.CRYSTAL, 1));
/*      */       
/*      */ 
/*  473 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.FIRE, 1).add(Aspect.UNDEAD, 1));
/*      */       
/*      */ 
/*  476 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBreathOfTheGoddess.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ORDER, 1).add(Aspect.SOUL, 1));
/*      */       
/*      */ 
/*  479 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemHintOfRebirth.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.LIFE, 1).add(Aspect.EXCHANGE, 1));
/*      */       
/*      */ 
/*  482 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemReekOfMisfortune.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ENTROPY, 1));
/*      */       
/*      */ 
/*  485 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWhiffOfMagic.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  488 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemReekOfMisfortune.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ENTROPY, 1));
/*      */       
/*      */ 
/*  491 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemOdourOfPurity.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ORDER, 1));
/*      */       
/*      */ 
/*  494 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemEnderDew.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ELDRITCH, 2));
/*      */       
/*      */ 
/*  497 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemOilOfVitriol.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENTROPY, 4));
/*      */       
/*      */ 
/*  500 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemTearOfTheGoddess.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ORDER, 1).add(Aspect.SOUL, 2));
/*      */       
/*      */ 
/*  503 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemRefinedEvil.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MIND, 2).add(Aspect.ENTROPY, 2));
/*      */       
/*      */ 
/*  506 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDropOfLuck.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 1).add(Aspect.VOID, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  511 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemRedstoneSoup.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/*  514 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemFlyingOintment.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.FLIGHT, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  519 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemGhostOfTheLight.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 6).add(Aspect.LIGHT, 15).add(Aspect.ARMOR, 5));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  524 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSoulOfTheWorld.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.EARTH, 10).add(Aspect.METAL, 10));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  529 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSpiritOfOtherwhere.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.ELDRITCH, 20));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  534 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemInfernalAnimus.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.DEATH, 5).add(Aspect.UNDEAD, 5).add(Aspect.BEAST, 5).add(Aspect.HUNGER, 5));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  541 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookOven.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  544 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookDistilling.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  547 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookCircleMagic.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  550 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookWands.damageValue }, new AspectList().add(Aspect.MIND, 4).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  553 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookBiomes.damageValue }, new AspectList().add(Aspect.MIND, 4).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  556 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookInfusions.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  559 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookHerbology.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  562 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemOddPorkRaw.damageValue }, new AspectList().add(Aspect.MAN, 1).add(Aspect.HUNGER, 2));
/*      */       
/*      */ 
/*  565 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemOddPorkCooked.damageValue }, new AspectList().add(Aspect.MAN, 1).add(Aspect.HUNGER, 2).add(Aspect.FIRE, 1));
/*      */       
/*      */ 
/*  568 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemRock.damageValue }, new AspectList().add(Aspect.EARTH, 2));
/*      */       
/*      */ 
/*  571 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWeb.damageValue }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.TRAP, 4));
/*      */       
/*      */ 
/*  574 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfVines.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.PLANT, 2));
/*      */       
/*      */ 
/*  577 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfIce.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.COLD, 2));
/*      */       
/*      */ 
/*  580 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfLove.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.LIFE, 2).add(Aspect.SOUL, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  585 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfWebs.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  590 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.SOUL, 2));
/*      */       
/*      */ 
/*  593 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfWasting.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.HUNGER, 2));
/*      */       
/*      */ 
/*      */ 
/*  597 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfCursedLeaping.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.MOTION, 2));
/*      */       
/*      */ 
/*  600 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfFrogsTongue.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.MOTION, 2));
/*      */       
/*      */ 
/*  603 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfHitchcock.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.BEAST, 2));
/*      */       
/*      */ 
/*  606 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfInfection.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.POISON, 2));
/*      */       
/*      */ 
/*      */ 
/*  610 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemOwletsWing.damageValue }, new AspectList().add(Aspect.BEAST, 1).add(Aspect.AIR, 1));
/*      */       
/*      */ 
/*  613 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemToeOfFrog.damageValue }, new AspectList().add(Aspect.BEAST, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/*  616 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemInfernalBlood.damageValue }, new AspectList().add(Aspect.FIRE, 1).add(Aspect.FLESH, 1).add(Aspect.SOUL, 1));
/*      */       
/*      */ 
/*  619 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWormyApple.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.POISON, 1));
/*      */       
/*      */ 
/*  622 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSleepingApple.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.HUNGER, 1).add(Aspect.MIND, 2));
/*      */       
/*      */ 
/*  625 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSleeping.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.MIND, 2));
/*      */       
/*      */ 
/*      */ 
/*  629 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemTormentedTwine.damageValue }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DARKNESS, 1));
/*      */       
/*      */ 
/*  632 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemFancifulThread.damageValue }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.LIGHT, 1));
/*      */       
/*      */ 
/*  635 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemGoldenThread.damageValue }, new AspectList().add(Aspect.METAL, 1));
/*      */       
/*      */ 
/*  638 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDisturbedCotton.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1));
/*      */       
/*      */ 
/*  641 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemMellifluousHunger.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.HUNGER, 4));
/*      */       
/*      */ 
/*  644 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemCharmOfDisruptedDreams.damageValue }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.ENTROPY, 1));
/*      */       
/*      */ 
/*      */ 
/*  648 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemKobolditeDust.damageValue }, new AspectList().add(Aspect.METAL, 1));
/*      */       
/*      */ 
/*  651 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemKobolditeNugget.damageValue }, new AspectList().add(Aspect.METAL, 2));
/*      */       
/*      */ 
/*  654 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemKobolditeIngot.damageValue }, new AspectList().add(Aspect.METAL, 3));
/*      */       
/*      */ 
/*  657 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemKobolditePentacle.damageValue }, new AspectList().add(Aspect.METAL, 4).add(Aspect.MAGIC, 3));
/*      */       
/*      */ 
/*      */ 
/*  661 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.KOBOLDITE_PICKAXE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 3).add(Aspect.MAGIC, 1).add(Aspect.MINE, 1));
/*      */       
/*      */ 
/*  664 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.WISPY_COTTON), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.LIGHT, 1));
/*      */       
/*  666 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.FLOWING_SPIRIT), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.SOUL, 2));
/*      */       
/*      */ 
/*  669 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BUCKET_FLOWINGSPIRIT), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.SOUL, 2).add(Aspect.METAL, 1));
/*      */       
/*      */ 
/*  672 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.PARASYTIC_LOUSE), new int[] { -1 }, new AspectList().add(Aspect.EXCHANGE, 1).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1));
/*      */       
/*      */ 
/*  675 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 0 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  680 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 4 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  685 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 8 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  690 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfInk.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2));
/*      */       
/*      */ 
/*  693 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  698 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 5 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  703 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 9 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  708 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSprouting.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4));
/*      */       
/*      */ 
/*  711 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 3 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  716 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 7 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  721 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 11 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  726 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfErosion.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 5).add(Aspect.MINE, 4));
/*      */       
/*      */ 
/*  729 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfThorns.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  734 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 2 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  739 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 6 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  744 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.TRAPPED_PLANT), new int[] { 10 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  750 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSpectralDust.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.UNDEAD, 1));
/*      */       
/*      */ 
/*  753 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfRaising.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENTROPY, 1).add(Aspect.UNDEAD, 2));
/*      */       
/*      */ 
/*  756 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemNecroStone.damageValue }, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 5).add(Aspect.UNDEAD, 5));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  761 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemArtichoke.damageValue }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 2));
/*      */       
/*      */ 
/*  764 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewGrotesque.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.SENSES, 2).add(Aspect.EXCHANGE, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  769 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemImpregnatedLeather.damageValue }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  774 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.OVEN_FUMEFUNNEL), new int[] { -1 }, new AspectList().add(Aspect.VOID, 4).add(Aspect.METAL, 20).add(Aspect.SENSES, 3).add(Aspect.LIGHT, 5).add(Aspect.ORDER, 4).add(Aspect.FIRE, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  779 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemFumeFilter.damageValue }, new AspectList().add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 10).add(Aspect.GREED, 4).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 10).add(Aspect.ORDER, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  784 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED), new int[] { -1 }, new AspectList().add(Aspect.METAL, 24).add(Aspect.CRYSTAL, 10).add(Aspect.GREED, 4).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 10).add(Aspect.ORDER, 8).add(Aspect.VOID, 4).add(Aspect.SENSES, 3).add(Aspect.LIGHT, 5).add(Aspect.FIRE, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  790 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POLYNESIA_CHARM), new int[] { -1 }, new AspectList().add(Aspect.MIND, 2).add(Aspect.SENSES, 2).add(Aspect.BEAST, 2));
/*      */       
/*  792 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DEVILS_TONGUE_CHARM), new int[] { -1 }, new AspectList().add(Aspect.MIND, 4).add(Aspect.SENSES, 3).add(Aspect.FIRE, 6).add(Aspect.SOUL, 4).add(Aspect.FLESH, 5));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  797 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.WITCH_HAT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 6).add(Aspect.BEAST, 5).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 5));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  802 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BABAS_HAT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 5).add(Aspect.ELDRITCH, 3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  808 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.MOGS_QUIVER), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 3).add(Aspect.MECHANISM, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  814 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GULGS_GURDLE), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 3).add(Aspect.WEAPON, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  820 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.WITCH_ROBES), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 6).add(Aspect.BEAST, 4).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  825 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.NECROMANCERS_ROBES), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 6).add(Aspect.BEAST, 4).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 3).add(Aspect.UNDEAD, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  830 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.ICY_SLIPPERS), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2).add(Aspect.COLD, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  836 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.RUBY_SLIPPERS), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2).add(Aspect.ELDRITCH, 3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  841 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEPING_SHOES), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2).add(Aspect.POISON, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  846 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BARK_BELT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 6).add(Aspect.MAGIC, 2).add(Aspect.TREE, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  852 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemPurifiedMilk.damageValue }, new AspectList().add(Aspect.HEAL, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/*  855 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBatBall.damageValue }, new AspectList().add(Aspect.BEAST, 1).add(Aspect.MOTION, 1));
/*      */       
/*      */ 
/*  858 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfBats.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  864 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BITING_BELT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 4).add(Aspect.MAGIC, 1).add(Aspect.HUNGER, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  870 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BREW_BAG), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.VOID, 2).add(Aspect.MECHANISM, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  876 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.MUTATING_SPRIG), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 1).add(Aspect.TREE, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  882 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDoorKey.damageValue }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1));
/*      */       
/*      */ 
/*  885 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemDoorKeyring.damageValue }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1));
/*      */       
/*      */ 
/*      */ 
/*  889 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.BARRIER), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.ARMOR, 3));
/*      */       
/*      */ 
/*  892 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.VOID_BRAMBLE), new int[] { -1 }, new AspectList().add(Aspect.ORDER, 4).add(Aspect.ELDRITCH, 3).add(Aspect.ARMOR, 4).add(Aspect.MAGIC, 4).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  898 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CHALK_GOLDEN), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 1));
/*      */       
/*  900 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CHALK_RITUAL), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
/*      */       
/*  902 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CHALK_OTHERWHERE), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  907 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CHALK_INFERNAL), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 4).add(Aspect.BEAST, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  913 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CIRCLE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 1));
/*      */       
/*  915 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLYPH_RITUAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
/*      */       
/*  917 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLYPH_OTHERWHERE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  922 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GLYPH_INFERNAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 4).add(Aspect.BEAST, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  928 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CIRCLE_TALISMAN), new int[] { 0 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRYSTAL, 4).add(Aspect.METAL, 8).add(Aspect.MAGIC, 3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  934 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.LEECH_CHEST), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.VOID, 3).add(Aspect.HUNGER, 1));
/*      */       
/*      */ 
/*  937 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STATUE_GODDESS), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 10).add(Aspect.MAGIC, 10).add(Aspect.HEAL, 4));
/*      */       
/*      */ 
/*  940 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CRITTER_SNARE), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.PLANT, 1).add(Aspect.TRAP, 2));
/*      */       
/*  942 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.BRAMBLE), new int[] { 0 }, new AspectList().add(Aspect.ORDER, 2).add(Aspect.ELDRITCH, 3).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  947 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.BRAMBLE), new int[] { 1 }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
/*      */       
/*  949 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.GRASSPER), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.PLANT, 1).add(Aspect.VOID, 1));
/*      */       
/*      */ 
/*  952 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemQuartzSphere.damageValue }, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*  955 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemHappenstanceOil.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.SENSES, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  960 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSeerStone.damageValue }, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.EARTH, 1).add(Aspect.SENSES, 3));
/*      */       
/*      */ 
/*  963 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CRYSTAL_BALL), new int[] { -1 }, new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.MAGIC, 2).add(Aspect.SENSES, 4));
/*      */       
/*  965 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.BLOOD_ROSE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.HUNGER, 1).add(Aspect.MECHANISM, 1));
/*      */       
/*  967 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.DEMON_HEART), new int[] { -1 }, new AspectList().add(Aspect.FIRE, 4).add(Aspect.VOID, 2).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  973 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemGraveyardDust.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.UNDEAD, 1));
/*      */       
/*      */ 
/*      */ 
/*  977 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBookBurning.damageValue }, new AspectList().add(Aspect.MIND, 4).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/*  980 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.FETISH_WITCHS_LADDER), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 1).add(Aspect.AIR, 1).add(Aspect.MECHANISM, 2));
/*      */       
/*  982 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.FETISH_TREANT_IDOL), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 1).add(Aspect.TREE, 2).add(Aspect.MECHANISM, 2));
/*      */       
/*  984 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.FETISH_SCARECROW), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 1).add(Aspect.CLOTH, 2).add(Aspect.MECHANISM, 2));
/*      */       
/*      */ 
/*  987 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BOLINE), new int[] { -1 }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.METAL, 1).add(Aspect.CRYSTAL, 1));
/*      */       
/*  989 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SPECTRAL_STONE), new int[] { -1 }, new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.TRAP, 4));
/*      */       
/*  991 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.BRAZIER), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.FIRE, 2).add(Aspect.METAL, 2));
/*      */       
/*      */ 
/*  994 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemCongealedSpirit.damageValue }, new AspectList().add(Aspect.SOUL, 6));
/*      */       
/*      */ 
/*  997 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEDS_WORMWOOD), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
/*      */       
/*  999 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CROP_WORMWOOD), new int[] { -1 }, new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.PLANT, 1));
/*      */       
/* 1001 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemWormwood.damageValue }, new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/* 1004 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemSubduedSpirit.damageValue }, new AspectList().add(Aspect.SOUL, 2));
/*      */       
/*      */ 
/* 1007 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemFocusedWill.damageValue }, new AspectList().add(Aspect.SOUL, 2));
/*      */       
/*      */ 
/* 1010 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemCondensedFear.damageValue }, new AspectList().add(Aspect.DEATH, 2));
/*      */       
/*      */ 
/*      */ 
/* 1014 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSolidRock.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1019 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSolidDirt.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1024 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSolidSand.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1029 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSolidSandstone.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1034 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSolidErosion.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 3).add(Aspect.EXCHANGE, 1));
/*      */       
/*      */ 
/* 1037 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemInfusionBase.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ORDER, 3));
/*      */       
/*      */ 
/* 1040 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfSoaring.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.AIR, 3));
/*      */       
/*      */ 
/* 1043 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewGrave.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.UNDEAD, 3));
/*      */       
/*      */ 
/* 1046 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewRevealing.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.SENSES, 2));
/*      */       
/*      */ 
/* 1049 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewSubstitution.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 3));
/*      */       
/*      */ 
/*      */ 
/* 1053 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewOfHollowTears.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.VOID, 2));
/*      */       
/*      */ 
/* 1056 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.HOLLOW_TEARS), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.VOID, 2));
/*      */       
/* 1058 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BUCKET_HOLLOWTEARS), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.VOID, 2).add(Aspect.METAL, 1));
/*      */       
/*      */ 
/* 1061 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemNullCatalyst.damageValue }, new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.VOID, 2));
/*      */       
/*      */ 
/* 1064 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemNullifiedLeather.damageValue }, new AspectList().add(Aspect.VOID, 2).add(Aspect.CLOTH, 1));
/*      */       
/*      */ 
/*      */ 
/* 1068 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBoltStake.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.TREE, 1));
/*      */       
/*      */ 
/* 1071 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBoltSplitting.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.TREE, 1).add(Aspect.ENTROPY, 1));
/*      */       
/*      */ 
/* 1074 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBoltHoly.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.DEATH, 2));
/*      */       
/*      */ 
/* 1077 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBoltAntiMagic.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.DEATH, 1).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1083 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DEATH_HOOD), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DEATH, 4).add(Aspect.ARMOR, 2).add(Aspect.TRAP, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1088 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DEATH_ROBE), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DEATH, 4).add(Aspect.ARMOR, 2).add(Aspect.FIRE, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1093 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DEATH_FEET), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DEATH, 4).add(Aspect.ARMOR, 2).add(Aspect.WATER, 2));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1098 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.DEATH_HAND), new int[] { -1 }, new AspectList().add(Aspect.WEAPON, 3).add(Aspect.DEATH, 4).add(Aspect.SOUL, 3));
/*      */       
/* 1100 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBinkyHead.damageValue }, new AspectList().add(Aspect.DEATH, 3).add(Aspect.BEAST, 2).add(Aspect.EXCHANGE, 2));
/*      */       
/*      */ 
/*      */ 
/* 1104 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.HUNTER_HAT), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
/*      */       
/* 1106 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.HUNTER_BOOTS), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
/*      */       
/* 1108 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.HUNTER_LEGS), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
/*      */       
/* 1110 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.HUNTER_COAT), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
/*      */       
/* 1112 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CROSSBOW_PISTOL), new int[] { -1 }, new AspectList().add(Aspect.WEAPON, 2).add(Aspect.MOTION, 1).add(Aspect.MECHANISM, 2));
/*      */       
/* 1114 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SHELF_COMPASS), new int[] { -1 }, new AspectList().add(Aspect.SENSES, 2).add(Aspect.CRYSTAL, 3).add(Aspect.MAGIC, 2));
/*      */       
/* 1116 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.POTIONS), new int[] { Witchery.Items.POTIONS.potionAntidote.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.HEAL, 2));
/*      */       
/*      */ 
/*      */ 
/* 1120 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.SEEDS_MINDRAKE), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.EXCHANGE, 1));
/*      */       
/* 1122 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CROP_MINDRAKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.CROP, 1));
/*      */       
/*      */ 
/* 1125 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.PLAYER_COMPASS), new int[] { -1 }, new AspectList().add(Aspect.SENSES, 2).add(Aspect.MAN, 1).add(Aspect.MAGIC, 2));
/*      */       
/*      */ 
/* 1128 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.EARMUFFS), new int[] { -1 }, new AspectList().add(Aspect.SENSES, 1).add(Aspect.CLOTH, 1).add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1));
/*      */       
/*      */ 
/* 1131 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PIT_DIRT), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1134 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PIT_GRASS), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1137 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.VINE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/* 1140 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.LILY), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/* 1143 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.CACTUS), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.TRAP, 1));
/*      */       
/*      */ 
/* 1146 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.WEB), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 2));
/*      */       
/*      */ 
/* 1149 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SNOW_STAIRS), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1152 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SNOW_SLAB_SINGLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1155 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SNOW_SLAB_DOUBLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1158 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.SNOW_PRESSURE_PLATE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
/*      */       
/*      */ 
/* 1161 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1164 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_STAIRS), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1167 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1170 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1173 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_FENCE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1176 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
/*      */       
/*      */ 
/* 1179 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_DOOR), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
/*      */       
/*      */ 
/* 1182 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.STOCKADE_ICE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.ARMOR, 1));
/*      */       
/*      */ 
/* 1185 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_PRESSURE_PLATE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
/*      */       
/*      */ 
/* 1188 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Blocks.VINE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
/*      */       
/*      */ 
/* 1191 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BIOME_BOOK), new int[] { -1 }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1194 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BIOME_NOTE), new int[] { -1 }, new AspectList().add(Aspect.MIND, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1197 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.CAULDRON_BOOK), new int[] { -1 }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1200 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BREW_FUEL), new int[] { -1 }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1203 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BREW_ENDLESS_WATER), new int[] { -1 }, new AspectList().add(Aspect.WATER, 8).add(Aspect.MAGIC, 1));
/*      */       
/*      */ 
/* 1206 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemAnnointingPaste.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.EXCHANGE, 1));
/*      */       
/*      */ 
/* 1209 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.BREW), new int[] { -1 }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MAGIC, 3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1215 */       ThaumcraftApi.registerEntityTag("witchery.mindrake", new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.SENSES, 1).add(Aspect.MOTION, 1).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1218 */       ThaumcraftApi.registerEntityTag("witchery.demon", new AspectList().add(Aspect.DEATH, 5).add(Aspect.VOID, 2).add(Aspect.FIRE, 4).add(Aspect.MAGIC, 6).add(Aspect.MOTION, 2).add(Aspect.SENSES, 2).add(Aspect.GREED, 8).add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1223 */       ThaumcraftApi.registerEntityTag("witchery.familiar", new AspectList().add(Aspect.AIR, 5).add(Aspect.SOUL, 6).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1227 */       ThaumcraftApi.registerEntityTag("witchery.mandrake", new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.AIR, 2).add(Aspect.SENSES, 2).add(Aspect.MOTION, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1229 */       ThaumcraftApi.registerEntityTag("witchery.treefyd", new AspectList().add(Aspect.PLANT, 4).add(Aspect.MAGIC, 2).add(Aspect.MOTION, 2).add(Aspect.MIND, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1231 */       ThaumcraftApi.registerEntityTag("witchery.hornedHuntsman", new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAGIC, 4).add(Aspect.MOTION, 2).add(Aspect.MIND, 4).add(Aspect.BEAST, 8).add(Aspect.EARTH, 2).add(Aspect.SOUL, 4), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1235 */       ThaumcraftApi.registerEntityTag("witchery.ent", new AspectList().add(Aspect.PLANT, 2).add(Aspect.TREE, 8).add(Aspect.MAGIC, 2).add(Aspect.MOTION, 2).add(Aspect.MIND, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1240 */       ThaumcraftApi.registerEntityTag("witchery.babayaga", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 6).add(Aspect.MOTION, 2).add(Aspect.MIND, 4).add(Aspect.SOUL, 8), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1243 */       ThaumcraftApi.registerEntityTag("witchery.owl", new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.MOTION, 2).add(Aspect.MIND, 1).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1247 */       ThaumcraftApi.registerEntityTag("witchery.toad", new AspectList().add(Aspect.SLIME, 1).add(Aspect.WATER, 1).add(Aspect.MOTION, 2).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1251 */       ThaumcraftApi.registerEntityTag("witchery.cat", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1255 */       ThaumcraftApi.registerEntityTag("witchery.louse", new AspectList().add(Aspect.EXCHANGE, 1).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1258 */       ThaumcraftApi.registerEntityTag("witchery.illusionSpider", new AspectList().add(Aspect.AIR, 2).add(Aspect.SENSES, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1260 */       ThaumcraftApi.registerEntityTag("witchery.illusionCreeper", new AspectList().add(Aspect.AIR, 2).add(Aspect.SENSES, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1262 */       ThaumcraftApi.registerEntityTag("witchery.illusionZombie", new AspectList().add(Aspect.AIR, 2).add(Aspect.SENSES, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1265 */       ThaumcraftApi.registerEntityTag("witchery.covenwitch", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 3).add(Aspect.MIND, 2).add(Aspect.SOUL, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1267 */       ThaumcraftApi.registerEntityTag("witchery.witchhunter", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 2).add(Aspect.SOUL, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1270 */       ThaumcraftApi.registerEntityTag("witchery.corpse", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 2).add(Aspect.SOUL, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1272 */       ThaumcraftApi.registerEntityTag("witchery.nightmare", new AspectList().add(Aspect.VOID, 3).add(Aspect.MAGIC, 2).add(Aspect.SOUL, 2).add(Aspect.HUNGER, 4), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1275 */       ThaumcraftApi.registerEntityTag("witchery.spirit", new AspectList().add(Aspect.SOUL, 3).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1277 */       ThaumcraftApi.registerEntityTag("witchery.lostsoul", new AspectList().add(Aspect.SOUL, 3).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1279 */       ThaumcraftApi.registerEntityTag("witchery.spectre", new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1281 */       ThaumcraftApi.registerEntityTag("witchery.banshee", new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 1).add(Aspect.SENSES, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1283 */       ThaumcraftApi.registerEntityTag("witchery.poltergeist", new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/* 1285 */       ThaumcraftApi.registerEntityTag("witchery.death", new AspectList().add(Aspect.DEATH, 8).add(Aspect.SOUL, 8), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/* 1288 */       ThaumcraftApi.registerEntityTag("witchery.imp", new AspectList().add(Aspect.VOID, 2).add(Aspect.FIRE, 3).add(Aspect.MAGIC, 7).add(Aspect.MOTION, 2).add(Aspect.SENSES, 2).add(Aspect.GREED, 6).add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1294 */       ThaumcraftApi.registerEntityTag("witchery.lordoftorment", new AspectList().add(Aspect.DEATH, 2).add(Aspect.VOID, 2).add(Aspect.FIRE, 5).add(Aspect.MAGIC, 8).add(Aspect.MOTION, 3).add(Aspect.SENSES, 2).add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 2).add(Aspect.TRAP, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1300 */       ThaumcraftApi.registerEntityTag("witchery.goblin", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1304 */       ThaumcraftApi.registerEntityTag("witchery.goblingulg", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 2).add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/* 1308 */       ThaumcraftApi.registerEntityTag("witchery.goblinmog", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 2).add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1313 */       ThaumcraftApi.registerEntityTag("witchery.leonard", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 2).add(Aspect.MAGIC, 8), new ThaumcraftApi.EntityTagsNBT[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1318 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewSoulAnguish.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.MIND, 2));
/*      */       
/*      */ 
/* 1321 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewSoulFear.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.SENSES, 2));
/*      */       
/*      */ 
/* 1324 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewSoulHunger.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.HUNGER, 2));
/*      */       
/*      */ 
/* 1327 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemBrewSoulTorment.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.TRAP, 2));
/*      */       
/*      */ 
/*      */ 
/* 1331 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractOwnership.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.SOUL, 2));
/*      */       
/*      */ 
/* 1334 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractBlaze.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.BEAST, 1));
/*      */       
/*      */ 
/* 1337 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractEvaporate.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.WATER, 1));
/*      */       
/*      */ 
/* 1340 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractFieryTouch.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.MECHANISM, 1));
/*      */       
/*      */ 
/* 1343 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractResistFire.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.ARMOR, 1));
/*      */       
/*      */ 
/* 1346 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractSmelting.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.MINE, 1));
/*      */       
/*      */ 
/* 1349 */       ThaumcraftApi.registerObjectTag(new ItemStack(Witchery.Items.GENERIC), new int[] { Witchery.Items.GENERIC.itemContractTorment.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.TRAP, 3));
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookThaumcraft4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */