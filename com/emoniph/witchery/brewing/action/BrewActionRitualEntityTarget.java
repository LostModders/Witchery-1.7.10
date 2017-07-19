/*    */ package com.emoniph.witchery.brewing.action;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*    */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*    */ import com.emoniph.witchery.brewing.RitualStatus;
/*    */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*    */ import com.emoniph.witchery.item.ItemTaglockKit;
/*    */ import com.emoniph.witchery.util.EntityPosition;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BrewActionRitualEntityTarget extends BrewActionRitual
/*    */ {
/*    */   public BrewActionRitualEntityTarget(BrewItemKey itemKey, AltarPower powerCost)
/*    */   {
/* 25 */     super(itemKey, powerCost, false);
/*    */   }
/*    */   
/*    */ 
/*    */   public RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*    */   {
/* 31 */     net.minecraft.nbt.NBTTagCompound tag = actionList.getTopItemStack().func_77978_p();
/* 32 */     if (tag == null) {
/* 33 */       return RitualStatus.FAILED;
/*    */     }
/*    */     
/* 36 */     EntityLivingBase targetEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, actionList.getTopItemStack(), Integer.valueOf(1));
/*    */     
/* 38 */     if (targetEntity != null) {
/* 39 */       if (!isDistanceAllowed(world, x, y, z, targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, targetEntity.field_71093_bK, modifiers.covenSize, modifiers.leonard))
/*    */       {
/* 41 */         return RitualStatus.FAILED_DISTANCE;
/*    */       }
/*    */       
/* 44 */       if (!actionList.isTargetLocationValid(server, world, x, y, z, modifiers.getTarget(), modifiers)) {
/* 45 */         return RitualStatus.FAILED_INVALID_CIRCLES;
/*    */       }
/*    */       
/* 48 */       ModifiersEffect modifiersEffect = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(targetEntity), true, modifiers.covenSize, com.emoniph.witchery.util.EntityUtil.playerOrFake(world, (EntityPlayer)null));
/*    */       
/* 50 */       modifiers.taglockUsed = true;
/*    */       
/* 52 */       boolean isImmune = com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(targetEntity);
/* 53 */       if (!isImmune) {
/* 54 */         isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(targetEntity, null);
/*    */       }
/*    */       
/* 57 */       if ((!isImmune) && 
/* 58 */         (!Witchery.Items.POPPET.voodooProtectionActivated(null, null, targetEntity, 1))) {
/* 59 */         WitcheryBrewRegistry.INSTANCE.applyRitualToEntity(targetEntity.field_70170_p, targetEntity, actionList.getTagCompound(), modifiers, modifiersEffect);
/*    */       }
/*    */       
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 66 */       return RitualStatus.FAILED;
/*    */     }
/* 68 */     return RitualStatus.COMPLETE;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionRitualEntityTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */