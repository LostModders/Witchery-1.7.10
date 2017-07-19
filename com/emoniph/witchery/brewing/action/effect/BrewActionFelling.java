/*    */ package com.emoniph.witchery.brewing.action.effect;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle;
/*    */ import com.emoniph.witchery.blocks.BlockCircleGlyph;
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
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class BrewActionFelling extends BrewActionEffect
/*    */ {
/*    */   private final int strengthReduction;
/*    */   
/*    */   public BrewActionFelling(Item axe, int strengthReduction, AltarPower powerCost, EffectLevel effectLevel)
/*    */   {
/* 28 */     super(new BrewItemKey(axe, 32767), new BrewNamePart("witchery:brew.felling"), powerCost, new Probability(1.0D), effectLevel);
/*    */     
/* 30 */     this.strengthReduction = strengthReduction;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*    */   {
/* 36 */     int strength = Math.max(modifiers.getStrength() - this.strengthReduction, 0);
/* 37 */     int BLOCK_RADIUS = Math.max(radius - (this.strengthReduction - 1) - 1, 1);
/* 38 */     int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
/* 39 */     int blockX = MathHelper.func_76128_c(posX);
/* 40 */     int blockY = MathHelper.func_76128_c(posY);
/* 41 */     int blockZ = MathHelper.func_76128_c(posZ);
/* 42 */     for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; y++) {
/* 43 */       for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; x++) {
/* 44 */         for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; z++) {
/* 45 */           if ((Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= BLOCK_RADIUS_SQ) && 
/* 46 */             (BlockProtect.checkModsForBreakOK(world, x, y, z, modifiers.caster))) {
/* 47 */             Block block = world.func_147439_a(x, y, z);
/* 48 */             Material material = block.func_149688_o();
/* 49 */             if ((material != null) && (material == Material.field_151575_d) && (block.canSustainLeaves(world, x, y, z)))
/*    */             {
/* 51 */               Block blockID = world.func_147439_a(x, y, z);
/* 52 */               if ((!(blockID instanceof BlockCircle)) && (!(blockID instanceof BlockCircleGlyph))) {
/* 53 */                 blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), strength);
/*    */                 
/* 55 */                 world.func_147468_f(x, y, z);
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionFelling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */