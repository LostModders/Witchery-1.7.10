/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*    */ import com.emoniph.witchery.util.ChatUtil;
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ 
/*    */ public class RiteTeleportEntity extends RiteTeleportation
/*    */ {
/*    */   public RiteTeleportEntity(int radius)
/*    */   {
/* 21 */     super(radius);
/*    */   }
/*    */   
/*    */   protected boolean teleport(World world, int posX, int posY, int posZ, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */   {
/* 26 */     if (!world.field_72995_K) {
/* 27 */       ItemStack taglockStack = null;
/* 28 */       for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
/* 29 */         if ((Witchery.Items.TAGLOCK_KIT == item.itemstack.func_77973_b()) && (item.itemstack.func_77960_j() == 1)) {
/* 30 */           taglockStack = item.itemstack;
/* 31 */           break;
/*    */         }
/*    */       }
/*    */       
/* 35 */       if (taglockStack != null) {
/* 36 */         EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, taglockStack, Integer.valueOf(1));
/* 37 */         if ((entity != null) && (entity.field_71093_bK != Config.instance().dimensionDreamID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID)) {
/* 38 */           net.minecraft.entity.player.EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 39 */           boolean isImmune = com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(entity);
/* 40 */           if (!isImmune) {
/* 41 */             isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, this);
/*    */           }
/* 43 */           if ((!isImmune) && (!Witchery.Items.POPPET.voodooProtectionActivated(player, null, entity, true, true)) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(entity, 3)))
/*    */           {
/* 45 */             ItemGeneral.teleportToLocation(world, posX, posY, posZ, world.field_73011_w.field_76574_g, entity, true);
/* 46 */             return true;
/*    */           }
/*    */           
/* 49 */           if (player != null) {
/* 50 */             if (isImmune) {
/* 51 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.blackmagicdampening", new Object[0]);
/*    */             } else {
/* 53 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.voodooprotectionactivated", new Object[0]);
/*    */             }
/*    */           }
/* 56 */           return false;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 62 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteTeleportEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */