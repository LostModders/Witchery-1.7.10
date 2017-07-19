/*    */ package com.emoniph.witchery.brewing.action.effect;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.BrewNamePart;
/*    */ import com.emoniph.witchery.brewing.EffectLevel;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.Probability;
/*    */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*    */ import com.emoniph.witchery.util.BlockActionCircle;
/*    */ import com.emoniph.witchery.util.EntityUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.IPlantable;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ 
/*    */ public class BrewActionPlanting
/*    */   extends BrewActionEffect
/*    */ {
/*    */   public BrewActionPlanting(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, EffectLevel effectLevel)
/*    */   {
/* 31 */     super(itemKey, namePart, powerCost, new Probability(1.0D), effectLevel);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack stack)
/*    */   {
/* 46 */     int R = radius + modifiers.getStrength();
/* 47 */     double RADIUS_SQ = R * R;
/* 48 */     AxisAlignedBB areaOfEffect = AxisAlignedBB.func_72330_a(x - R, y - R, z - R, x + R, y + R, z + R);
/* 49 */     List<EntityItem> entities = world.func_72872_a(EntityItem.class, areaOfEffect);
/* 50 */     if ((entities != null) && (!entities.isEmpty())) {
/* 51 */       final ArrayList<ItemStack> seeds = new ArrayList();
/*    */       
/* 53 */       for (EntityItem itemEntity : entities) {
/* 54 */         ItemStack seedStack = itemEntity.func_92059_d();
/* 55 */         if ((itemEntity.func_70092_e(x, y, z) <= RADIUS_SQ) && (seedStack != null) && ((seedStack.func_77973_b() instanceof IPlantable)))
/*    */         {
/* 57 */           seeds.add(seedStack);
/*    */         }
/*    */       }
/*    */       
/* 61 */       int Y_RANGE = 2;
/* 62 */       new BlockActionCircle()
/*    */       {
/*    */         public void onBlock(World world, int x, int y, int z) {
/* 65 */           int index = seeds.size() - 1;
/* 66 */           if (index >= 0) {
/* 67 */             ItemStack seed = (ItemStack)seeds.get(index);
/*    */             
/* 69 */             for (int dy = y - 2; dy <= y + 2; dy++) {
/* 70 */               Block block = world.func_147439_a(x, dy, z);
/* 71 */               if ((block.func_149688_o().func_76220_a()) && (world.func_147437_c(x, dy + 1, z)) && 
/* 72 */                 (seed.func_77973_b().func_77648_a(seed, EntityUtil.playerOrFake(world, modifiers.caster), world, x, dy, z, 1, 0.0F, 0.0F, 0.0F))) {
/*    */                 break;
/*    */               }
/*    */             }
/*    */             
/*    */ 
/*    */ 
/* 79 */             if (seed.field_77994_a <= 0)
/* 80 */               seeds.remove(index); } } }.processFilledCircle(world, x, y, z, radius + modifiers.getStrength());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionPlanting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */