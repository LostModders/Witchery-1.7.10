/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockWickerBundle
/*     */   extends BlockBaseRotatedPillar
/*     */ {
/*  30 */   private static final String[] bundleType = { "plain", "bloodied" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] block_side;
/*     */   
/*  34 */   public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  39 */       return BlockWickerBundle.bundleType;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] block_top;
/*     */   
/*     */   public BlockWickerBundle()
/*     */   {
/*  50 */     super(Material.field_151575_d, ClassItemBlock.class);
/*     */     
/*  52 */     func_149711_c(0.5F);
/*  53 */     func_149672_a(field_149779_h);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  60 */     super.func_149663_c(blockName);
/*  61 */     Blocks.field_150480_ab.setFireInfo(this, 20, 20);
/*  62 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
/*     */   {
/*  68 */     ItemStack heldItem = player.func_70694_bm();
/*  69 */     if ((heldItem != null) && (heldItem.func_77973_b() == Items.field_151033_d))
/*     */     {
/*  71 */       if (!tryIgniteMan(world, x, y, z, player.field_70177_z)) {
/*  72 */         return false;
/*     */       }
/*     */       
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean tryIgniteMan(World world, int x, int y, int z, float rotationYaw) {
/*  81 */     boolean xleft = world.func_147439_a(x - 1, y, z) == Witchery.Blocks.WICKER_BUNDLE;
/*  82 */     boolean xright = world.func_147439_a(x + 1, y, z) == Witchery.Blocks.WICKER_BUNDLE;
/*  83 */     boolean zleft = world.func_147439_a(x, y, z - 1) == Witchery.Blocks.WICKER_BUNDLE;
/*  84 */     boolean zright = world.func_147439_a(x, y, z + 1) == Witchery.Blocks.WICKER_BUNDLE;
/*  85 */     int dx = x;
/*  86 */     int dy = y;
/*  87 */     int dz = z;
/*  88 */     int fz = 0;
/*  89 */     int fx = 0;
/*  90 */     if (((!xleft) && (!xright)) || ((zleft) || (zright) || ((!xleft) && (!xright) && (!zleft) && (!zright))))
/*  91 */       return false;
/*  92 */     if ((xleft) || (xright))
/*     */     {
/*  94 */       if ((xleft) && (!xright)) {
/*  95 */         dx--;
/*  96 */       } else if (!xleft) {
/*  97 */         dx++;
/*     */       }
/*  99 */       while (world.func_147439_a(dx, dy - 1, dz) == Witchery.Blocks.WICKER_BUNDLE) {
/* 100 */         dy--;
/*     */       }
/*     */       
/* 103 */       while (world.func_147439_a(dx - 1, dy, dz) == Witchery.Blocks.WICKER_BUNDLE) {
/* 104 */         dx--;
/*     */       }
/* 106 */       fx = 1;
/*     */     } else {
/* 108 */       if ((zleft) && (!zright)) {
/* 109 */         dz--;
/* 110 */       } else if (!zleft) {
/* 111 */         dz++;
/*     */       }
/* 113 */       while (world.func_147439_a(dx, dy - 1, dz) == Witchery.Blocks.WICKER_BUNDLE) {
/* 114 */         dy--;
/*     */       }
/*     */       
/* 117 */       while (world.func_147439_a(dx, dy, dz - 1) == Witchery.Blocks.WICKER_BUNDLE) {
/* 118 */         dz--;
/*     */       }
/* 120 */       fz = 1;
/*     */     }
/*     */     
/* 123 */     World w = world;
/* 124 */     if ((!wicker(w, dx, dy + 7, dz)) && (!wicker(w, dx + 1 * fx, dy + 7, dz + 1 * fz)) && (!wicker(w, dx - 1 * fx, dy + 6, dz - 1 * fz)) && (wicker(w, dx, dy + 6, dz)) && (wicker(w, dx + 1 * fx, dy + 6, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy + 6, dz + 2 * fz)) && (!wicker(w, dx - 1 * fx, dy + 5, dz - 1 * fz)) && (wicker(w, dx, dy + 5, dz)) && (wicker(w, dx + 1 * fx, dy + 5, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy + 5, dz + 2 * fz)) && (!wicker(w, dx - 2 * fx, dy + 4, dz - 2 * fz)) && (wicker(w, dx - 1 * fx, dy + 4, dz - 1 * fz)) && (wicker(w, dx, dy + 4, dz)) && (wicker(w, dx + 1 * fx, dy + 4, dz + 1 * fz)) && (wicker(w, dx + 2 * fx, dy + 4, dz + 2 * fz)) && (!wicker(w, dx + 3 * fx, dy + 4, dz + 3 * fz)) && (!wicker(w, dx - 2 * fx, dy + 3, dz - 2 * fz)) && (wicker(w, dx - 1 * fx, dy + 3, dz - 1 * fz)) && (wicker(w, dx, dy + 3, dz)) && (wicker(w, dx + 1 * fx, dy + 3, dz + 1 * fz)) && (wicker(w, dx + 2 * fx, dy + 3, dz + 2 * fz)) && (!wicker(w, dx + 3 * fx, dy + 3, dz + 3 * fz)) && (!wicker(w, dx - 2 * fx, dy + 2, dz - 2 * fz)) && (wicker(w, dx - 1 * fx, dy + 2, dz - 1 * fz)) && (wicker(w, dx, dy + 2, dz)) && (wicker(w, dx + 1 * fx, dy + 2, dz + 1 * fz)) && (wicker(w, dx + 2 * fx, dy + 2, dz + 2 * fz)) && (!wicker(w, dx + 3 * fx, dy + 2, dz + 3 * fz)) && (!wicker(w, dx - 1 * fx, dy + 1, dz - 1 * fz)) && (wicker(w, dx, dy + 1, dz)) && (wicker(w, dx + 1 * fx, dy, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy + 1, dz + 2 * fz)) && (!wicker(w, dx - 1 * fx, dy, dz - 1 * fz)) && (wicker(w, dx, dy, dz)) && (wicker(w, dx + 1 * fx, dy, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy, dz + 2 * fz)))
/*     */     {
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
/* 141 */       world.func_147449_b(dx, dy + 6, dz, Blocks.field_150480_ab);
/* 142 */       world.func_147449_b(dx + 1 * fx, dy + 6, dz + 1 * fz, Blocks.field_150480_ab);
/*     */       
/* 144 */       world.func_147449_b(dx, dy + 3, dz, Blocks.field_150480_ab);
/* 145 */       world.func_147449_b(dx + 1 * fx, dy + 3, dz + 1 * fz, Blocks.field_150480_ab);
/*     */       
/* 147 */       world.func_147449_b(dx, dy + 2, dz, Blocks.field_150480_ab);
/* 148 */       world.func_147449_b(dx + 1 * fx, dy + 2, dz + 1 * fz, Blocks.field_150480_ab);
/*     */       
/* 150 */       world.func_147449_b(dx, dy + 1, dz, Blocks.field_150480_ab);
/* 151 */       world.func_147449_b(dx + 1 * fx, dy + 1, dz + 1 * fz, Blocks.field_150480_ab);
/*     */       
/* 153 */       world.func_147449_b(dx, dy + 0, dz, Blocks.field_150480_ab);
/* 154 */       world.func_147449_b(dx + 1 * fx, dy + 0, dz + 1 * fz, Blocks.field_150480_ab);
/*     */       
/* 156 */       world.func_147449_b(dx - 1 * fx, dy + 4, dz - 1 * fz, Blocks.field_150480_ab);
/* 157 */       world.func_147449_b(dx + 2 * fx, dy + 4, dz + 2 * fz, Blocks.field_150480_ab);
/*     */       
/* 159 */       EntityHornedHuntsman entity = new EntityHornedHuntsman(world);
/* 160 */       entity.func_70012_b(dx + 1.0D * fx + 0.5D * fz, dy + 0.1D, dz + 1.0D * fz + 0.5D * fx, 180.0F + rotationYaw, 0.0F);
/*     */       
/*     */ 
/* 163 */       entity.field_70759_as = entity.field_70177_z;
/* 164 */       entity.field_70761_aq = entity.field_70177_z;
/* 165 */       entity.func_110163_bv();
/* 166 */       entity.func_82206_m();
/* 167 */       entity.func_70642_aH();
/* 168 */       if (!world.field_72995_K) {
/* 169 */         world.func_72838_d(entity);
/*     */       }
/*     */       
/*     */ 
/* 173 */       for (int j1 = 0; j1 < 120; j1++) {
/* 174 */         world.func_72869_a("snowballpoof", dx + world.field_73012_v.nextDouble(), dy - 2 + world.field_73012_v.nextDouble() * 3.9D, dz + 1 + world.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */     
/* 178 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean wicker(World world, int x, int y, int z) {
/* 182 */     return (world.func_147439_a(x, y, z) == Witchery.Blocks.WICKER_BUNDLE) && (limitToValidMetadata(world.func_72805_g(x, y, z)) == 1);
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 187 */     return 31;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int meta)
/*     */   {
/* 192 */     return limitToValidMetadata(meta);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
/*     */   {
/* 198 */     for (int i = 0; i < bundleType.length; i++) {
/* 199 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 205 */     return 1;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 210 */     int metadata = world.func_72805_g(x, y, z);
/* 211 */     return new ItemStack(this, 1, metadata >= 0 ? limitToValidMetadata(metadata) : 0);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon func_150163_b(int meta)
/*     */   {
/* 217 */     return this.block_side[net.minecraft.util.MathHelper.func_76125_a(meta, 0, 1)];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon func_150161_d(int meta)
/*     */   {
/* 223 */     return this.block_top[net.minecraft.util.MathHelper.func_76125_a(meta, 0, 1)];
/*     */   }
/*     */   
/*     */   public static int limitToValidMetadata(int par0) {
/* 227 */     return par0 & bundleType.length - 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 233 */     this.block_side = new IIcon[bundleType.length];
/* 234 */     this.block_top = new IIcon[bundleType.length];
/*     */     
/* 236 */     for (int i = 0; i < bundleType.length; i++) {
/* 237 */       this.block_side[i] = iconRegister.func_94245_a(func_149641_N() + "_" + bundleType[i] + "_side");
/* 238 */       this.block_top[i] = iconRegister.func_94245_a(func_149641_N() + "_" + bundleType[i] + "_top");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 244 */     boolean flammable = super.isFlammable(world, x, y, z, face);
/* 245 */     return flammable;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWickerBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */