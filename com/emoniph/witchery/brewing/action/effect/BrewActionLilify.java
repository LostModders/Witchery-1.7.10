/*    */ package com.emoniph.witchery.brewing.action.effect;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.BrewNamePart;
/*    */ import com.emoniph.witchery.brewing.EffectLevel;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.Probability;
/*    */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*    */ import com.emoniph.witchery.util.BlockProtect;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrewActionLilify
/*    */   extends BrewActionEffect
/*    */ {
/*    */   public BrewActionLilify(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, EffectLevel effectLevel)
/*    */   {
/* 35 */     super(itemKey, namePart, powerCost, new Probability(1.0D), effectLevel);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*    */   {
/* 41 */     x += side.offsetX;
/* 42 */     y += side.offsetY;
/* 43 */     z += side.offsetZ;
/*    */     
/* 45 */     while (((world.func_147439_a(x, y, z).func_149688_o() != Material.field_151586_h) || (!world.func_147437_c(x, y + 1, z))) && (y < 255)) {
/* 46 */       y++;
/*    */     }
/*    */     
/* 49 */     if ((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) && (world.func_147437_c(x, y + 1, z)) && (BlockProtect.checkModsForBreakOK(world, x, y + 1, z, modifiers.caster)))
/*    */     {
/* 51 */       int meta = (modifiers.getStrength() & 0x3) << 2;
/* 52 */       world.func_147465_d(x, y + 1, z, Witchery.Blocks.LILY, meta, 3);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*    */   {
/* 59 */     Coord coord = new Coord(targetEntity);
/* 60 */     doApplyToBlock(world, coord.x, coord.y, coord.z, ForgeDirection.UP, 1, modifiers, actionStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionLilify.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */