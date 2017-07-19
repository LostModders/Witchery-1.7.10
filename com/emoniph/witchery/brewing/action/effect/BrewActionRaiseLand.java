/*    */ package com.emoniph.witchery.brewing.action.effect;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.BrewNamePart;
/*    */ import com.emoniph.witchery.brewing.EffectLevel;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*    */ import com.emoniph.witchery.brewing.Probability;
/*    */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*    */ import com.emoniph.witchery.util.BlockProtect;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
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
/*    */ public class BrewActionRaiseLand
/*    */   extends BrewActionEffect
/*    */ {
/*    */   public BrewActionRaiseLand(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, EffectLevel effectLevel)
/*    */   {
/* 36 */     super(itemKey, namePart, powerCost, new Probability(1.0D), effectLevel);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*    */   {
/* 42 */     int r = (modifiers.getStrength() + 1) * 2;
/* 43 */     int rsq = r * r;
/* 44 */     for (int dx = -r; dx <= r; dx++) {
/* 45 */       for (int dz = -r; dz <= r; dz++) {
/* 46 */         if (dx * dx + dz * dz < rsq) {
/* 47 */           int nx = x + dx;
/* 48 */           int nz = z + dz;
/* 49 */           doApplyToBlock(world, nx, y, nz, ForgeDirection.UP, 1, modifiers, stack);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*    */   {
/* 58 */     Coord coord = new Coord(targetEntity);
/* 59 */     doApplyToBlock(world, coord.x, coord.y - 1, coord.z, ForgeDirection.UP, 1, modifiers, stack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*    */   {
/* 68 */     while ((!world.func_147437_c(x, y + 1, z)) && (y < 255))
/* 69 */       y++;
/*    */     int height;
/* 71 */     if ((BlockProtect.canBreak(x, y, z, world)) && (BlockProtect.checkModsForBreakOK(world, x, y, z, modifiers.caster)))
/*    */     {
/* 73 */       height = (modifiers.getStrength() + 1) * (modifiers.ritualised ? 2 : 3);
/* 74 */       if (!world.field_72995_K) {
/* 75 */         for (int i = 0; i < height; i++) {
/* 76 */           int dy = y - i;
/* 77 */           Block block = world.func_147439_a(x, dy, z);
/* 78 */           int meta = world.func_72805_g(x, dy, z);
/* 79 */           world.func_147468_f(x, dy, z);
/* 80 */           world.func_147465_d(x, dy + height, z, block, meta, 3);
/*    */         }
/*    */       }
/*    */       
/* 84 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x, y, z, x + 1, y + 2, z + 1);
/* 85 */       List<EntityLivingBase> list = world.func_72872_a(EntityLivingBase.class, bounds);
/* 86 */       for (EntityLivingBase entity : list) {
/* 87 */         if ((entity instanceof EntityPlayer)) {
/* 88 */           entity.func_70634_a(0.5D + x, y + height + 1, 0.5D + z);
/*    */         } else {
/* 90 */           entity.func_70634_a(0.5D + x, y + height + 1, 0.5D + z);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionRaiseLand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */