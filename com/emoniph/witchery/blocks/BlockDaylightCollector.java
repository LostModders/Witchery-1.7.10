/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockDaylightCollector extends BlockBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconGlobe;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconGlobeCharged;
/*     */   
/*     */   public BlockDaylightCollector()
/*     */   {
/*  26 */     super(net.minecraft.block.material.Material.field_151573_f);
/*  27 */     func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
/*  28 */     func_149711_c(3.5F);
/*  29 */     func_149672_a(field_149777_j);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/*  33 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/*  37 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  42 */     return 1;
/*     */   }
/*     */   
/*     */   public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
/*     */     int power;
/*  47 */     if ((y == tileY) && ((x == tileX) || (z == tileZ)) && 
/*  48 */       (world.func_147439_a(tileX, tileY, tileZ) == Blocks.field_150453_bW)) {
/*  49 */       ForgeDirection direction = ForgeDirection.UNKNOWN;
/*  50 */       if (x - tileX < 0) {
/*  51 */         direction = ForgeDirection.WEST;
/*  52 */       } else if (x - tileX > 0) {
/*  53 */         direction = ForgeDirection.EAST;
/*  54 */       } else if (z - tileZ < 0) {
/*  55 */         direction = ForgeDirection.NORTH;
/*  56 */       } else if (z - tileZ > 0) {
/*  57 */         direction = ForgeDirection.SOUTH;
/*     */       }
/*  59 */       power = Blocks.field_150453_bW.func_149748_c(world, tileX, tileY, tileZ, direction.ordinal());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/*  68 */     if (!world.field_72995_K) {
/*  69 */       ForgeDirection[] directions = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
/*     */       
/*  71 */       int meta = world.func_72805_g(x, y, z);
/*  72 */       if ((meta > 0) && (meta < 15)) {
/*  73 */         for (ForgeDirection direction : directions) {
/*  74 */           int otherX = x + direction.offsetX;
/*  75 */           int otherZ = z + direction.offsetZ;
/*  76 */           Block otherBlock = world.func_147439_a(otherX, y, otherZ);
/*  77 */           if (otherBlock == Blocks.field_150453_bW) {
/*  78 */             int power = Blocks.field_150453_bW.func_149709_b(world, otherX, y, otherZ, direction.ordinal());
/*     */             
/*  80 */             if (power != meta + 1) break;
/*  81 */             world.func_72921_c(x, y, z, meta + 1, 3); break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  93 */     if (!world.field_72995_K) {
/*  94 */       int meta = world.func_72805_g(x, y, z);
/*  95 */       if (meta == 0) {
/*  96 */         ItemStack stack = player.func_70694_bm();
/*  97 */         if (Witchery.Items.GENERIC.itemQuartzSphere.isMatch(stack)) {
/*  98 */           stack.field_77994_a -= 1;
/*  99 */           world.func_72921_c(x, y, z, 1, 3);
/*     */         }
/* 101 */       } else if (meta < 15) {
/* 102 */         world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, Witchery.Items.GENERIC.itemQuartzSphere.createStack()));
/*     */         
/* 104 */         world.func_72921_c(x, y, z, 0, 3);
/* 105 */       } else if (meta == 15) {
/* 106 */         world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, new ItemStack(Witchery.Items.SUN_GRENADE)));
/*     */         
/* 108 */         world.func_72921_c(x, y, z, 0, 3);
/*     */       }
/* 110 */       return true;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/* 125 */     switch (meta) {
/*     */     case 0: 
/* 127 */       return super.func_149691_a(side, meta);
/*     */     
/*     */     case 15: 
/* 130 */       return this.iconGlobeCharged;
/*     */     }
/* 132 */     return this.iconGlobe;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 139 */     super.func_149651_a(iconRegister);
/* 140 */     this.iconGlobe = iconRegister.func_94245_a(func_149641_N() + "1");
/* 141 */     this.iconGlobeCharged = iconRegister.func_94245_a(func_149641_N() + "2");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockDaylightCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */