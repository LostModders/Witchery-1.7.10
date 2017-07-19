/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.entity.EntityVampire;
/*     */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockGarlicGarland extends BlockBaseContainer
/*     */ {
/*     */   public BlockGarlicGarland()
/*     */   {
/*  23 */     super(net.minecraft.block.material.Material.field_151594_q, TileEntityGarlicGarland.class);
/*  24 */     this.registerWithCreateTab = true;
/*  25 */     func_149711_c(0.2F);
/*     */   }
/*     */   
/*     */   public static class TileEntityGarlicGarland extends TileEntity
/*     */   {
/*     */     public boolean canUpdate()
/*     */     {
/*  32 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  38 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/*  57 */     return super.func_149718_j(world, x, y, z);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  68 */     return null;
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  73 */     int side = world.func_72805_g(x, y, z);
/*  74 */     float minY = 0.8F;
/*  75 */     float maxY = 1.0F;
/*  76 */     float minX = 0.0F;
/*  77 */     float maxX = 0.15F;
/*  78 */     float minZ = 0.1F;
/*  79 */     float maxZ = 0.9F;
/*     */     
/*  81 */     if (side == 2) {
/*  82 */       func_149676_a(0.1F, 0.8F, 1.0F, 0.9F, 1.0F, 0.85F);
/*  83 */     } else if (side == 3) {
/*  84 */       func_149676_a(0.100000024F, 0.8F, 0.0F, 0.9F, 1.0F, 0.15F);
/*  85 */     } else if (side == 4) {
/*  86 */       func_149676_a(1.0F, 0.8F, 0.1F, 0.85F, 1.0F, 0.9F);
/*  87 */     } else if (side == 5) {
/*  88 */       func_149676_a(0.0F, y + 0.8F, 0.1F, 0.15F, 1.0F, 0.9F);
/*     */     } else {
/*  90 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  96 */     if ((!world.field_72995_K) && ((entity instanceof EntityVampire))) {
/*  97 */       RiteProtectionCircleRepulsive.push(world, entity, 0.5D + x, 0.5D + y, 0.5D + z);
/*  98 */     } else if ((world.field_72995_K) && ((entity instanceof EntityPlayer))) {
/*  99 */       EntityPlayer player = (EntityPlayer)entity;
/* 100 */       if ((!player.field_71075_bZ.field_75098_d) && (ExtendedPlayer.get((EntityPlayer)entity).isVampire())) {
/* 101 */         RiteProtectionCircleRepulsive.push(world, entity, 0.5D + x, 0.5D + y, 0.5D + z, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
/*     */   {
/* 108 */     return super.func_149633_g(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/* 135 */     int facing = MathHelper.func_76128_c(entity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 136 */     if (facing == 0) {
/* 137 */       world.func_72921_c(x, y, z, 2, 2);
/* 138 */     } else if (facing == 1) {
/* 139 */       world.func_72921_c(x, y, z, 5, 2);
/* 140 */     } else if (facing == 2) {
/* 141 */       world.func_72921_c(x, y, z, 3, 2);
/* 142 */     } else if (facing == 3) {
/* 143 */       world.func_72921_c(x, y, z, 4, 2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockGarlicGarland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */