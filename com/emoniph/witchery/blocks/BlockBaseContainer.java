/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.common.registry.GameRegistry;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockContainer;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class BlockBaseContainer
/*    */   extends BlockContainer
/*    */ {
/* 16 */   protected boolean registerBlockName = true;
/* 17 */   protected boolean registerTileEntity = true;
/* 18 */   protected boolean registerWithCreateTab = true;
/*    */   protected final Class<? extends TileEntity> clazzTile;
/*    */   protected final Class<? extends ItemBlock> clazzItem;
/*    */   
/*    */   public BlockBaseContainer(Material material, Class<? extends TileEntity> clazzTile)
/*    */   {
/* 24 */     this(material, clazzTile, null);
/*    */   }
/*    */   
/*    */   public BlockBaseContainer(Material material, Class<? extends TileEntity> clazzTile, Class<? extends ItemBlock> clazzItem) {
/* 28 */     super(material);
/*    */     
/* 30 */     this.clazzTile = clazzTile;
/* 31 */     this.clazzItem = clazzItem;
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 36 */     if (this.registerWithCreateTab) {
/* 37 */       func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */     }
/*    */     
/* 40 */     if (this.registerBlockName) {
/* 41 */       if (this.clazzItem == null) {
/* 42 */         BlockUtil.registerBlock(this, blockName);
/*    */       } else {
/* 44 */         BlockUtil.registerBlock(this, this.clazzItem, blockName);
/*    */       }
/*    */     }
/*    */     
/* 48 */     if (this.registerTileEntity) {
/* 49 */       GameRegistry.registerTileEntity(this.clazzTile, blockName);
/*    */     }
/*    */     
/* 52 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   public TileEntity func_149915_a(World world, int metadata)
/*    */   {
/*    */     try {
/* 58 */       return (TileEntity)this.clazzTile.newInstance();
/*    */     } catch (Throwable e) {}
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBaseContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */