/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import cpw.mods.fml.common.IFuelHandler;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ public class BlockWitchWood
/*     */   extends BlockBase
/*     */   implements IFuelHandler
/*     */ {
/*  26 */   private static final String[] WOOD_TYPES = { "rowan", "alder", "hawthorn" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] iconArray;
/*     */   
/*  30 */   public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  35 */       return BlockWitchWood.WOOD_TYPES;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BlockWitchWood()
/*     */   {
/*  43 */     super(Material.field_151575_d, ClassItemBlock.class);
/*  44 */     func_149711_c(2.0F);
/*  45 */     func_149672_a(Block.field_149766_f);
/*     */     
/*  47 */     GameRegistry.registerFuelHandler(this);
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  52 */     super.func_149663_c(blockName);
/*  53 */     Blocks.field_150480_ab.setFireInfo(this, 5, 20);
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  60 */     if ((par2 < 0) || (par2 >= this.iconArray.length)) {
/*  61 */       par2 = 0;
/*     */     }
/*     */     
/*  64 */     return this.iconArray[par2];
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  69 */     return par1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
/*     */   {
/*  75 */     for (int i = 0; i < WOOD_TYPES.length; i++) {
/*  76 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/*  83 */     this.iconArray = new IIcon[WOOD_TYPES.length];
/*  84 */     for (int i = 0; i < this.iconArray.length; i++) {
/*  85 */       this.iconArray[i] = par1IconRegister.func_94245_a(func_149641_N() + "_" + WOOD_TYPES[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int getBurnTime(ItemStack fuel)
/*     */   {
/*  92 */     if (Item.func_150898_a(this) == fuel.func_77973_b()) {
/*  93 */       return 300;
/*     */     }
/*  95 */     return 0;
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 100 */     if (world.func_72805_g(x, y, z) == 2) {
/* 101 */       return 1;
/*     */     }
/* 103 */     return super.getFlammability(world, x, y, z, face);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 109 */     if (world.func_72805_g(x, y, z) == 2) {
/* 110 */       return 1;
/*     */     }
/* 112 */     return super.getFireSpreadSpeed(world, x, y, z, face);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchWood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */