/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.blocks.TileEntityBase;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ 
/*     */ public class BlockSlurp extends BlockBaseContainer
/*     */ {
/*     */   public BlockSlurp()
/*     */   {
/*  20 */     super(net.minecraft.block.material.Material.field_151592_s, TileEntitySlurp.class);
/*  21 */     this.registerWithCreateTab = false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  25 */     return -1;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/*  29 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/*  33 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149678_a(int p_149678_1_, boolean p_149678_2_) {
/*  37 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  42 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149690_a(World world, int x, int y, int z, int metadata, float chance, int fortune) {}
/*     */   
/*     */   public void replaceBlockAt(World world, int x, int y, int z, int timeoutTicks)
/*     */   {
/*  49 */     if (!world.field_72995_K) {
/*  50 */       Block block = world.func_147439_a(x, y, z);
/*  51 */       if ((BlockProtect.canBreak(block, world)) && (BlockProtect.checkModsForBreakOK(world, x, y, z, FakePlayerFactory.getMinecraft((WorldServer)world))))
/*     */       {
/*     */ 
/*  54 */         int meta = world.func_72805_g(x, y, z);
/*  55 */         world.func_147449_b(x, y, z, Witchery.Blocks.SLURP);
/*  56 */         TileEntitySlurp tile = (TileEntitySlurp)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntitySlurp.class);
/*  57 */         if (tile != null) {
/*  58 */           tile.saveBlock(timeoutTicks, block, meta);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntitySlurp extends TileEntityBase
/*     */   {
/*     */     private Block savedBlock;
/*     */     private int savedMeta;
/*     */     private int timeout;
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/*  72 */       super.func_145845_h();
/*  73 */       if ((!this.field_145850_b.field_72995_K) && (this.ticks >= this.timeout)) {
/*  74 */         if (this.savedBlock == null) {
/*  75 */           this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } else {
/*  77 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.savedBlock, Math.max(this.savedMeta, 0), 3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void saveBlock(int timeoutTicks, Block block) {
/*  83 */       saveBlock(timeoutTicks, block, 0);
/*     */     }
/*     */     
/*     */     public void saveBlock(int timeoutTicks, Block block, int meta) {
/*  87 */       this.savedBlock = block;
/*  88 */       this.savedMeta = meta;
/*  89 */       this.timeout = timeoutTicks;
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/*  94 */       super.func_145841_b(nbtRoot);
/*  95 */       nbtRoot.func_74768_a("Timeout", Math.max(this.timeout, 0));
/*  96 */       if (this.savedBlock != null) {
/*  97 */         nbtRoot.func_74778_a("blockName", Block.field_149771_c.func_148750_c(this.savedBlock));
/*  98 */         nbtRoot.func_74768_a("blockMeta", this.savedMeta);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 104 */       super.func_145839_a(nbtRoot);
/* 105 */       this.timeout = Math.max(nbtRoot.func_74762_e("Timeout"), 0);
/* 106 */       this.savedBlock = null;
/* 107 */       this.savedMeta = 0;
/* 108 */       if (nbtRoot.func_74764_b("blockName")) {
/* 109 */         String blockName = nbtRoot.func_74779_i("blockName");
/* 110 */         if ((blockName != null) && (!blockName.isEmpty())) {
/* 111 */           this.savedBlock = Block.func_149684_b(blockName);
/* 112 */           this.savedMeta = nbtRoot.func_74762_e("blockMeta");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockSlurp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */