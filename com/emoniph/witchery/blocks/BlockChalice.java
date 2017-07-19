/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockChalice extends BlockBaseContainer
/*     */ {
/*     */   public BlockChalice()
/*     */   {
/*  27 */     super(Material.field_151574_g, TileEntityChalice.class);
/*  28 */     this.registerWithCreateTab = false;
/*     */     
/*  30 */     func_149711_c(3.0F);
/*  31 */     func_149672_a(field_149777_j);
/*     */     
/*  33 */     func_149676_a(0.3F, 0.0F, 0.37F, 0.63F, 0.46F, 0.695F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  38 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  48 */     return 1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/*  53 */     return Witchery.Items.GENERIC;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/*  58 */     if (metadata == 1) {
/*  59 */       return Witchery.Items.GENERIC.itemChaliceFull.damageValue;
/*     */     }
/*  61 */     return Witchery.Items.GENERIC.itemChaliceEmpty.damageValue;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/*  67 */     func_111046_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
/*  71 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/*  72 */       func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/*  73 */       par1World.func_147468_f(par2, par3, par4);
/*  74 */       return false;
/*     */     }
/*  76 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/*  82 */     Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
/*  83 */     return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  88 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/*  89 */     if ((tileEntity != null) && ((tileEntity instanceof TileEntityChalice)) && (((TileEntityChalice)tileEntity).isFilled())) {
/*  90 */       return Witchery.Items.GENERIC.itemChaliceFull.createStack();
/*     */     }
/*  92 */     return Witchery.Items.GENERIC.itemChaliceEmpty.createStack();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 105 */     int metadata = world.func_72805_g(x, y, z);
/* 106 */     if (metadata == 1) {
/* 107 */       double d0 = x + 0.45F;
/* 108 */       double d1 = y + 0.4F;
/* 109 */       double d2 = z + 0.5F;
/* 110 */       world.func_72869_a(ParticleEffect.REDDUST.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityChalice extends TileEntity {
/*     */     private boolean filled;
/*     */     private boolean checkState;
/*     */     private static final String FILLED_KEY = "WITCFilled";
/*     */     
/* 119 */     public boolean isFilled() { return this.filled; }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void func_145845_h()
/*     */     {
/* 126 */       if (!this.checkState) {
/* 127 */         this.checkState = true;
/* 128 */         if ((this.filled) && (!this.field_145850_b.field_72995_K) && (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.CHALICE)) {
/* 129 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
/*     */         }
/*     */       }
/* 132 */       super.func_145845_h();
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 137 */       super.func_145841_b(nbtTag);
/* 138 */       nbtTag.func_74757_a("WITCFilled", this.filled);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 143 */       super.func_145839_a(nbtTag);
/* 144 */       if (nbtTag.func_74764_b("WITCFilled")) {
/* 145 */         this.filled = nbtTag.func_74767_n("WITCFilled");
/*     */       }
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 151 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 152 */       func_145841_b(nbtTag);
/* 153 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 158 */       super.onDataPacket(net, packet);
/* 159 */       func_145839_a(packet.func_148857_g());
/* 160 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public void setFilled(boolean filled) {
/* 164 */       if (!this.field_145850_b.field_72995_K) {
/* 165 */         this.filled = filled;
/*     */         
/* 167 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         
/* 169 */         if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.CHALICE) {
/* 170 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, filled ? 1 : 0, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockChalice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */