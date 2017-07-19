/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import cpw.mods.fml.common.registry.GameRegistry;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockChest;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ import net.minecraft.util.WeightedRandomChestContent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraftforge.common.ChestGenHooks;
/*    */ 
/*    */ public class BlockRefillingChest extends BlockChest
/*    */ {
/*    */   public BlockRefillingChest()
/*    */   {
/* 23 */     super(0);
/* 24 */     func_149752_b(9999.0F);
/* 25 */     func_149722_s();
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 30 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */     
/* 32 */     BlockUtil.registerBlock(this, blockName);
/* 33 */     GameRegistry.registerTileEntity(TileEntityRefillingChest.class, blockName);
/*    */     
/* 35 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   public TileEntity func_149915_a(World world, int metadata)
/*    */   {
/* 40 */     return new TileEntityRefillingChest();
/*    */   }
/*    */   
/*    */   public static class TileEntityRefillingChest extends TileEntityChest {
/* 44 */     protected long ticks = 0L;
/*    */     private static final int MAX_ITEMS_FOR_REFILL = 0;
/*    */     
/*    */     public void func_145845_h() {
/* 48 */       super.func_145845_h();
/*    */       
/* 50 */       if (this.ticks == 0L) {
/* 51 */         initiate();
/* 52 */       } else if (this.ticks >= Long.MAX_VALUE) {
/* 53 */         this.ticks = 1L;
/*    */       }
/*    */       
/* 56 */       this.ticks += 1L;
/*    */       
/* 58 */       doUpdate();
/*    */     }
/*    */     
/*    */     protected void initiate() {
/* 62 */       doUpdate();
/*    */     }
/*    */     
/*    */ 
/*    */     protected void doUpdate()
/*    */     {
/* 68 */       if ((!this.field_145850_b.field_72995_K) && (this.field_145850_b.field_73011_w.field_76574_g == Config.instance().dimensionTormentID) && (TimeUtil.secondsElapsed(3600, this.ticks)) && (com.emoniph.witchery.util.InvUtil.getItemStackCount(this) <= 0))
/*    */       {
/* 70 */         int numItems = 2 + this.field_145850_b.field_73012_v.nextInt(4);
/* 71 */         ChestGenHooks gen = ChestGenHooks.getInfo("dungeonChest");
/* 72 */         WeightedRandomChestContent.func_76293_a(this.field_145850_b.field_73012_v, gen.getItems(this.field_145850_b.field_73012_v), this, numItems);
/*    */       }
/*    */     }
/*    */     
/*    */     public void func_145841_b(NBTTagCompound nbtChest)
/*    */     {
/* 78 */       super.func_145841_b(nbtChest);
/* 79 */       nbtChest.func_74772_a("WITCLifeTicks", this.ticks);
/*    */     }
/*    */     
/*    */     public void func_145839_a(NBTTagCompound nbtChest)
/*    */     {
/* 84 */       super.func_145839_a(nbtChest);
/* 85 */       this.ticks = nbtChest.func_74763_f("WITCLifeTicks");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockRefillingChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */