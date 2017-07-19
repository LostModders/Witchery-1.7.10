/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockBarrier extends BlockBaseContainer
/*     */ {
/*     */   public static void setBlock(World world, int posX, int posY, int posZ, int ticksUntilExpiration, boolean blocksPlayers, EntityPlayer owner)
/*     */   {
/*  23 */     setBlock(world, posX, posY, posZ, ticksUntilExpiration, blocksPlayers, owner, false);
/*     */   }
/*     */   
/*     */   public static void setBlock(World world, int posX, int posY, int posZ, int ticksUntilExpiration, boolean blocksPlayers, EntityPlayer owner, boolean skipCreate) {
/*  27 */     if (!skipCreate) {
/*  28 */       world.func_147465_d(posX, posY, posZ, Witchery.Blocks.BARRIER, 0, 3);
/*     */     }
/*  30 */     TileEntity entity = world.func_147438_o(posX, posY, posZ);
/*  31 */     if ((entity != null) && ((entity instanceof TileEntityBarrier))) {
/*  32 */       TileEntityBarrier tileEntity = (TileEntityBarrier)entity;
/*  33 */       tileEntity.setTicksUntilExpiration(ticksUntilExpiration);
/*  34 */       tileEntity.setBlocksPlayers(blocksPlayers);
/*  35 */       tileEntity.setOwner(owner);
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockBarrier() {
/*  40 */     super(net.minecraft.block.material.Material.field_151592_s, TileEntityBarrier.class);
/*  41 */     this.registerWithCreateTab = false;
/*     */     
/*  43 */     func_149722_s();
/*  44 */     func_149752_b(1000.0F);
/*  45 */     func_149713_g(0);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int posX, int posY, int posZ)
/*     */   {
/*  50 */     TileEntity te = world.func_147438_o(posX, posY, posZ);
/*  51 */     TileEntityBarrier tileEntity; if ((te != null) && ((te instanceof TileEntityBarrier))) {
/*  52 */       tileEntity = (TileEntityBarrier)te;
/*  53 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX, posY, posZ, posX + 1, posY + 0.9D, posZ + 1);
/*  54 */       List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, bounds);
/*     */       
/*  56 */       for (EntityPlayer player : players) {
/*  57 */         if ((player != null) && ((!tileEntity.getBlocksPlayers()) || ((player.field_71075_bZ.field_75098_d) && (player.func_70093_af())) || (tileEntity.isOwner(player)))) {
/*  58 */           return null;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  63 */     float f = 0.0625F;
/*  64 */     return AxisAlignedBB.func_72330_a(posX + 0.0625F, posY + 0.0625F, posZ + 0.0625F, posX + 1 - 0.0625F, posY + 1 - 0.0625F, posZ + 1 - 0.0625F);
/*     */   }
/*     */   
/*     */   protected boolean func_149700_E()
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  74 */     return 0;
/*     */   }
/*     */   
/*     */   public int func_149701_w()
/*     */   {
/*  79 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess blockAccess, int posX, int posY, int posZ, int side)
/*     */   {
/*  84 */     Block i1 = blockAccess.func_147439_a(posX, posY, posZ);
/*  85 */     return i1 == this ? false : super.func_149646_a(blockAccess, posX, posY, posZ, side);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public static class TileEntityBarrier extends TileEntity {
/* 104 */     private int ticksUntilExpiration = 60;
/*     */     private boolean blocksPlayers;
/* 106 */     private String ownerName = "";
/*     */     private static final String KEY_REMAINING_TICKS = "remainingTicks";
/*     */     
/*     */     public void func_145845_h() {
/* 110 */       super.func_145845_h();
/*     */       
/* 112 */       if ((--this.ticksUntilExpiration <= 0) && 
/* 113 */         (!this.field_145850_b.field_72995_K)) {
/* 114 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a, 0, 3);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     private static final String KEY_BLOCKS_PLAYERS = "blocksPlayers";
/*     */     
/*     */     private static final String KEY_OWNER_NAME = "owner";
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 124 */       super.func_145841_b(nbtTag);
/* 125 */       nbtTag.func_74768_a("remainingTicks", this.ticksUntilExpiration);
/* 126 */       nbtTag.func_74757_a("blocksPlayers", this.blocksPlayers);
/* 127 */       nbtTag.func_74778_a("owner", this.ownerName);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 132 */       super.func_145839_a(nbtTag);
/* 133 */       this.ticksUntilExpiration = nbtTag.func_74762_e("remainingTicks");
/* 134 */       this.blocksPlayers = nbtTag.func_74767_n("blocksPlayers");
/* 135 */       this.ownerName = nbtTag.func_74779_i("owner");
/*     */     }
/*     */     
/*     */     public void setTicksUntilExpiration(int ticksUntilExpiration) {
/* 139 */       this.ticksUntilExpiration = ticksUntilExpiration;
/*     */     }
/*     */     
/*     */     public void setBlocksPlayers(boolean blocksPlayers) {
/* 143 */       this.blocksPlayers = blocksPlayers;
/*     */     }
/*     */     
/*     */     public boolean getBlocksPlayers() {
/* 147 */       return this.blocksPlayers;
/*     */     }
/*     */     
/*     */     public void setOwner(EntityPlayer owner) {
/* 151 */       this.ownerName = (owner != null ? owner.func_70005_c_() : "");
/*     */     }
/*     */     
/*     */     public boolean isOwner(EntityPlayer player) {
/* 155 */       return (player != null) && (player.func_70005_c_().equals(this.ownerName));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBarrier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */