/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockKettle;
/*     */ import com.emoniph.witchery.brewing.BlockCauldron;
/*     */ import com.emoniph.witchery.brewing.EntityBrew;
/*     */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*     */ import com.emoniph.witchery.entity.EntityGrenade;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*     */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*     */ import net.minecraft.dispenser.IBlockSource;
/*     */ import net.minecraft.dispenser.IPosition;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ public class DispenseBehaviourItemBrew
/*     */   implements IBehaviorDispenseItem
/*     */ {
/*     */   private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior;
/*     */   
/*  32 */   public DispenseBehaviourItemBrew() { this.defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem(); }
/*     */   
/*     */   public ItemStack func_82482_a(IBlockSource block, ItemStack stack) {
/*  35 */     if ((stack.func_77973_b() == Witchery.Items.BREW) && (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())))
/*  36 */       return new DispenserBehaviorBrew(this, stack).func_82482_a(block, stack);
/*  37 */     if (stack.func_77973_b() == Items.field_151069_bo) {
/*  38 */       EnumFacing facing = BlockDispenser.func_149937_b(block.func_82620_h());
/*  39 */       EnumFacing[] FACINGS = { EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST };
/*  40 */       for (EnumFacing cauldronFacing : FACINGS) {
/*  41 */         if (cauldronFacing != facing) {
/*  42 */           int x = block.func_82623_d() + cauldronFacing.func_82601_c();
/*  43 */           int y = block.func_82622_e() + cauldronFacing.func_96559_d();
/*  44 */           int z = block.func_82621_f() + cauldronFacing.func_82599_e();
/*  45 */           Block replaceBlock = block.func_82618_k().func_147439_a(x, y, z);
/*  46 */           if (replaceBlock == Witchery.Blocks.CAULDRON) {
/*  47 */             ItemStack brew = Witchery.Blocks.CAULDRON.fillBottleFromCauldron(block.func_82618_k(), x, y, z, 3000);
/*  48 */             if (brew != null) {
/*  49 */               IPosition position = BlockDispenser.func_149939_a(block);
/*  50 */               BehaviorDefaultDispenseItem.func_82486_a(block.func_82618_k(), brew, 6, facing, position);
/*  51 */               stack.func_77979_a(1);
/*  52 */               block.func_82618_k().func_72926_e(1000, block.func_82623_d(), block.func_82622_e(), block.func_82621_f(), 0);
/*     */             }
/*  54 */             return stack;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  59 */       return this.defaultDispenserItemBehavior.func_82482_a(block, stack); }
/*  60 */     if (stack.func_77973_b() == Witchery.Items.BREW_ENDLESS_WATER) {
/*  61 */       if (!block.func_82618_k().field_72995_K)
/*     */       {
/*  63 */         EnumFacing facing = BlockDispenser.func_149937_b(block.func_82620_h());
/*  64 */         int x = block.func_82623_d() + facing.func_82601_c();
/*  65 */         int y = block.func_82622_e() + facing.func_96559_d();
/*  66 */         int z = block.func_82621_f() + facing.func_82599_e();
/*     */         
/*  68 */         Block replaceBlock = block.func_82618_k().func_147439_a(x, y, z);
/*  69 */         net.minecraft.entity.player.EntityPlayer fakePlayer = net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((WorldServer)block.func_82618_k());
/*     */         
/*  71 */         if (stack.func_77960_j() <= stack.func_77958_k()) {
/*  72 */           if ((com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(block.func_82618_k(), x, y, z, fakePlayer)) && (replaceBlock.func_149688_o() != net.minecraft.block.material.Material.field_151586_h)) {
/*  73 */             stack.func_77972_a(1, fakePlayer);
/*  74 */             block.func_82618_k().func_147449_b(x, y, z, net.minecraft.init.Blocks.field_150358_i);
/*  75 */             block.func_82618_k().func_147471_g(x, y, z);
/*  76 */             SoundEffect.WATER_SPLASH.playAt(block.func_82618_k(), x, y, z);
/*  77 */           } else if (replaceBlock == Witchery.Blocks.CAULDRON) {
/*  78 */             if (Witchery.Blocks.CAULDRON.tryFillWith(block.func_82618_k(), x, y, z, new FluidStack(FluidRegistry.WATER, 3000))) {
/*  79 */               stack.func_77972_a(1, fakePlayer);
/*     */             }
/*  81 */           } else if ((replaceBlock == Witchery.Blocks.KETTLE) && 
/*  82 */             (Witchery.Blocks.KETTLE.tryFillWith(block.func_82618_k(), x, y, z, new FluidStack(FluidRegistry.WATER, 1000)))) {
/*  83 */             stack.func_77972_a(1, fakePlayer);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  88 */       return stack; }
/*  89 */     if (stack.func_77973_b() == Witchery.Items.SUN_GRENADE)
/*  90 */       return new DispenserGrenade(this, stack).func_82482_a(block, stack);
/*  91 */     if (stack.func_77973_b() == Witchery.Items.DUP_GRENADE) {
/*  92 */       return new DispenserGrenade(this, stack).func_82482_a(block, stack);
/*     */     }
/*  94 */     return this.defaultDispenserItemBehavior.func_82482_a(block, stack);
/*     */   }
/*     */   
/*     */   public static class DispenserBehaviorBrew extends BehaviorDefaultDispenseItem
/*     */   {
/*     */     final ItemStack potionItemStack;
/*     */     final DispenseBehaviourItemBrew dispenserPotionBehavior;
/*     */     
/*     */     DispenserBehaviorBrew(DispenseBehaviourItemBrew behavior, ItemStack brewStack) {
/* 103 */       this.dispenserPotionBehavior = behavior;
/* 104 */       this.potionItemStack = brewStack;
/*     */     }
/*     */     
/*     */     public ItemStack func_82487_b(IBlockSource dispenserBlock, ItemStack stack) {
/* 108 */       World world = dispenserBlock.func_82618_k();
/* 109 */       IPosition iposition = BlockDispenser.func_149939_a(dispenserBlock);
/* 110 */       EnumFacing enumfacing = BlockDispenser.func_149937_b(dispenserBlock.func_82620_h());
/* 111 */       EntityBrew iprojectile = getProjectileEntity(world, iposition);
/* 112 */       iprojectile.setThrowableHeading(enumfacing.func_82601_c(), enumfacing.func_96559_d() + 0.1F, enumfacing.func_82599_e(), func_82500_b(), func_82498_a());
/*     */       
/*     */ 
/* 115 */       world.func_72838_d(iprojectile);
/* 116 */       stack.func_77979_a(1);
/* 117 */       return stack;
/*     */     }
/*     */     
/*     */     protected void func_82485_a(IBlockSource dispenserBlock) {
/* 121 */       dispenserBlock.func_82618_k().func_72926_e(1002, dispenserBlock.func_82623_d(), dispenserBlock.func_82622_e(), dispenserBlock.func_82621_f(), 0);
/*     */     }
/*     */     
/*     */     protected EntityBrew getProjectileEntity(World world, IPosition position)
/*     */     {
/* 126 */       return new EntityBrew(world, position.func_82615_a(), position.func_82617_b(), position.func_82616_c(), this.potionItemStack, false);
/*     */     }
/*     */     
/*     */     protected float func_82498_a() {
/* 130 */       return 3.0F;
/*     */     }
/*     */     
/*     */     protected float func_82500_b() {
/* 134 */       return 1.375F;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DispenserGrenade extends BehaviorDefaultDispenseItem {
/*     */     final ItemStack potionItemStack;
/*     */     final DispenseBehaviourItemBrew dispenserPotionBehavior;
/*     */     
/*     */     DispenserGrenade(DispenseBehaviourItemBrew behavior, ItemStack brewStack) {
/* 143 */       this.dispenserPotionBehavior = behavior;
/* 144 */       this.potionItemStack = brewStack;
/*     */     }
/*     */     
/*     */     public ItemStack func_82487_b(IBlockSource dispenserBlock, ItemStack stack) {
/* 148 */       World world = dispenserBlock.func_82618_k();
/* 149 */       IPosition iposition = BlockDispenser.func_149939_a(dispenserBlock);
/* 150 */       EnumFacing enumfacing = BlockDispenser.func_149937_b(dispenserBlock.func_82620_h());
/* 151 */       EntityGrenade iprojectile = getProjectileEntity(world, iposition);
/* 152 */       iprojectile.setThrowableHeading(enumfacing.func_82601_c(), enumfacing.func_96559_d() + 0.1F, enumfacing.func_82599_e(), func_82500_b(), func_82498_a());
/*     */       
/*     */ 
/* 155 */       world.func_72838_d(iprojectile);
/* 156 */       stack.func_77979_a(1);
/* 157 */       return stack;
/*     */     }
/*     */     
/*     */     protected void func_82485_a(IBlockSource dispenserBlock) {
/* 161 */       dispenserBlock.func_82618_k().func_72926_e(1002, dispenserBlock.func_82623_d(), dispenserBlock.func_82622_e(), dispenserBlock.func_82621_f(), 0);
/*     */     }
/*     */     
/*     */     protected EntityGrenade getProjectileEntity(World world, IPosition position)
/*     */     {
/* 166 */       return new EntityGrenade(world, position.func_82615_a(), position.func_82617_b(), position.func_82616_c(), this.potionItemStack);
/*     */     }
/*     */     
/*     */     protected float func_82498_a() {
/* 170 */       return 3.0F;
/*     */     }
/*     */     
/*     */     protected float func_82500_b() {
/* 174 */       return 1.375F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/DispenseBehaviourItemBrew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */