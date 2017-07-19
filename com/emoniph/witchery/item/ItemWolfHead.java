/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockWolfHead.TileEntityWolfHead;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemWolfHead extends MultiItemBlock
/*     */ {
/*  24 */   private static final String[] skullTypes = { "wolf", "hellhound" };
/*  25 */   public static final String[] field_94587_a = { "wolf", "hellhound" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] field_94586_c;
/*     */   
/*     */   public ItemWolfHead(Block par1) {
/*  30 */     super(par1);
/*  31 */     func_77656_e(0);
/*  32 */     func_77625_d(64);
/*  33 */     func_77627_a(true);
/*  34 */     func_111206_d("witchery:wolfhead");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  40 */     if (side == 0)
/*  41 */       return false;
/*  42 */     if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
/*  43 */       return false;
/*     */     }
/*  45 */     if (side == 1) {
/*  46 */       y++;
/*     */     }
/*     */     
/*  49 */     if (side == 2) {
/*  50 */       z--;
/*     */     }
/*     */     
/*  53 */     if (side == 3) {
/*  54 */       z++;
/*     */     }
/*     */     
/*  57 */     if (side == 4) {
/*  58 */       x--;
/*     */     }
/*     */     
/*  61 */     if (side == 5) {
/*  62 */       x++;
/*     */     }
/*     */     
/*  65 */     if (!player.func_82247_a(x, y, z, side, stack))
/*  66 */       return false;
/*  67 */     if (!Witchery.Blocks.WOLFHEAD.func_149742_c(world, x, y, z)) {
/*  68 */       return false;
/*     */     }
/*  70 */     if (!world.field_72995_K) {
/*  71 */       world.func_147465_d(x, y, z, Witchery.Blocks.WOLFHEAD, side, 3);
/*  72 */       int i1 = 0;
/*     */       
/*  74 */       if (side == 1) {
/*  75 */         i1 = MathHelper.func_76128_c(player.field_70177_z * 16.0F / 360.0F + 0.5D) & 0xF;
/*     */       }
/*     */       
/*  78 */       BlockWolfHead.TileEntityWolfHead tile = (BlockWolfHead.TileEntityWolfHead)BlockUtil.getTileEntity(world, x, y, z, BlockWolfHead.TileEntityWolfHead.class);
/*  79 */       if (tile != null) {
/*  80 */         tile.setSkullType(stack.func_77960_j());
/*  81 */         tile.setRotation(i1);
/*     */       }
/*     */     }
/*     */     
/*  85 */     stack.field_77994_a -= 1;
/*  86 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
/*     */   {
/*  93 */     for (int i = 0; i < skullTypes.length; i++) {
/*  94 */       p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_77647_b(int p_77647_1_) {
/*  99 */     return p_77647_1_;
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack p_77667_1_) {
/* 103 */     int i = p_77667_1_.func_77960_j();
/*     */     
/* 105 */     if ((i < 0) || (i >= skullTypes.length)) {
/* 106 */       i = 0;
/*     */     }
/*     */     
/* 109 */     return super.func_77658_a() + "." + skullTypes[i];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int p_77617_1_) {
/* 114 */     if ((p_77617_1_ < 0) || (p_77617_1_ >= skullTypes.length)) {
/* 115 */       p_77617_1_ = 0;
/*     */     }
/*     */     
/* 118 */     return this.field_94586_c[p_77617_1_];
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack p_77653_1_) {
/* 122 */     return super.func_77653_i(p_77653_1_);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister p_94581_1_) {
/* 127 */     this.field_94586_c = new IIcon[field_94587_a.length];
/*     */     
/* 129 */     for (int i = 0; i < field_94587_a.length; i++) {
/* 130 */       this.field_94586_c[i] = p_94581_1_.func_94245_a(func_111208_A() + "_" + field_94587_a[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   protected String[] getNames()
/*     */   {
/* 136 */     return skullTypes;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemWolfHead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */