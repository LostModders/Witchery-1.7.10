/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPlacedItem extends BlockBaseContainer
/*     */ {
/*     */   public static void placeItemInWorld(ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/*  30 */     int meta = 0;
/*     */     
/*  32 */     if (player != null) {
/*  33 */       int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */       
/*  35 */       if (l == 0) {
/*  36 */         meta = 2;
/*     */       }
/*     */       
/*  39 */       if (l == 1) {
/*  40 */         meta = 5;
/*     */       }
/*     */       
/*  43 */       if (l == 2) {
/*  44 */         meta = 3;
/*     */       }
/*     */       
/*  47 */       if (l == 3) {
/*  48 */         meta = 4;
/*     */       }
/*     */     }
/*     */     
/*  52 */     world.func_147465_d(x, y, z, Witchery.Blocks.PLACED_ITEMSTACK, meta, 3);
/*  53 */     TileEntity tile = world.func_147438_o(x, y, z);
/*  54 */     if ((tile != null) && ((tile instanceof TileEntityPlacedItem))) {
/*  55 */       ((TileEntityPlacedItem)tile).setStack(stack);
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockPlacedItem() {
/*  60 */     super(Material.field_151578_c, TileEntityPlacedItem.class);
/*  61 */     this.registerWithCreateTab = false;
/*     */     
/*  63 */     func_149711_c(0.0F);
/*  64 */     func_149672_a(field_149777_j);
/*     */     
/*  66 */     func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.05F, 0.8F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149651_a(IIconRegister p_149651_1_) {}
/*     */   
/*     */ 
/*     */   protected String func_149641_N()
/*     */   {
/*  75 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/*  90 */     if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
/*  91 */       par5 |= 0x8;
/*  92 */       par1World.func_72921_c(par2, par3, par4, par5, 4);
/*     */     }
/*     */     
/*  95 */     func_149697_b(par1World, par2, par3, par4, par5, 0);
/*     */     
/*  97 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 102 */     ArrayList<ItemStack> drops = new ArrayList();
/* 103 */     if ((metadata & 0x8) == 0) {
/* 104 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 105 */       if ((tile != null) && ((tile instanceof TileEntityPlacedItem)) && (((TileEntityPlacedItem)tile).getStack() != null)) {
/* 106 */         drops.add(((TileEntityPlacedItem)tile).getStack());
/*     */       }
/*     */     }
/* 109 */     return drops;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 114 */     func_111046_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
/* 118 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 119 */       if (!par1World.field_72995_K) {
/* 120 */         func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/* 121 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/* 123 */       return false;
/*     */     }
/* 125 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 131 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 132 */     if ((tile != null) && ((tile instanceof TileEntityPlacedItem)) && (((TileEntityPlacedItem)tile).getStack() != null)) {
/* 133 */       return ((TileEntityPlacedItem)tile).getStack().func_77946_l();
/*     */     }
/* 135 */     return new ItemStack(Witchery.Items.ARTHANA);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/* 141 */     Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
/* 142 */     return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 148 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public String func_149702_O()
/*     */   {
/* 159 */     return func_149641_N();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 165 */     return net.minecraft.init.Blocks.field_150339_S.func_149733_h(0);
/*     */   }
/*     */   
/*     */   public static class TileEntityPlacedItem extends TileEntity
/*     */   {
/*     */     private static final String ITEM_KEY = "WITCPlacedItem";
/*     */     private ItemStack stack;
/*     */     
/*     */     public boolean canUpdate() {
/* 174 */       return false;
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 179 */       super.func_145841_b(nbtRoot);
/* 180 */       if (this.stack != null) {
/* 181 */         NBTTagCompound nbtItem = new NBTTagCompound();
/* 182 */         this.stack.func_77955_b(nbtItem);
/* 183 */         nbtRoot.func_74782_a("WITCPlacedItem", nbtItem);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 189 */       super.func_145839_a(nbtRoot);
/* 190 */       if (nbtRoot.func_74764_b("WITCPlacedItem")) {
/* 191 */         NBTTagCompound nbtItem = nbtRoot.func_74775_l("WITCPlacedItem");
/* 192 */         ItemStack stack = ItemStack.func_77949_a(nbtItem);
/* 193 */         this.stack = stack;
/*     */       }
/*     */     }
/*     */     
/*     */     public void setStack(ItemStack stack) {
/* 198 */       this.stack = stack;
/* 199 */       if (!this.field_145850_b.field_72995_K) {
/* 200 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public ItemStack getStack() {
/* 205 */       return this.stack;
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 210 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 211 */       func_145841_b(nbtTag);
/* 212 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 217 */       super.onDataPacket(net, packet);
/* 218 */       func_145839_a(packet.func_148857_g());
/* 219 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPlacedItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */