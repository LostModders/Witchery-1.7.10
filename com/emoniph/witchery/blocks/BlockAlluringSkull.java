/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockAlluringSkull extends BlockBaseContainer
/*     */ {
/*     */   private static final int UPDATE_FREQUENCY = 100;
/*     */   
/*     */   public BlockAlluringSkull()
/*     */   {
/*  43 */     super(Material.field_151594_q, TileEntityAlluringSkull.class, com.emoniph.witchery.item.ItemAlluringSkull.class);
/*  44 */     func_149715_a(0.5F);
/*  45 */     func_149672_a(field_149769_e);
/*  46 */     func_149722_s();
/*  47 */     func_149752_b(1000.0F);
/*  48 */     func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  53 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/*  68 */     if (!world.field_72995_K) {
/*  69 */       TileEntityAlluringSkull tileEntity = (TileEntityAlluringSkull)world.func_147438_o(posX, posY, posZ);
/*  70 */       int type = tileEntity.getSkullType();
/*  71 */       ItemStack itemstack = player.func_70694_bm();
/*  72 */       if ((itemstack != null) && (Witchery.Items.GENERIC.itemNecroStone.isMatch(itemstack))) {
/*  73 */         if (type == 0) {
/*  74 */           ParticleEffect.FLAME.send(SoundEffect.MOB_HORSE_SKELETON_DEATH, world, 0.5D + posX, 0.3D + posY, 0.5D + posZ, 0.5D, 0.5D, 16);
/*  75 */           tileEntity.setSkullType(type == 0 ? 1 : 0);
/*     */         } else {
/*  77 */           ParticleEffect.EXPLODE.send(SoundEffect.MOB_HORSE_SKELETON_HIT, world, 0.5D + posX, 0.3D + posY, 0.5D + posZ, 0.5D, 0.5D, 16);
/*  78 */           world.func_147468_f(posX, posY, posZ);
/*  79 */           world.func_72838_d(new EntityItem(world, 0.5D + posX, 0.8D + posY, 0.5D + posZ, new ItemStack(this)));
/*     */         }
/*     */         
/*  82 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  86 */     return super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/*  91 */     int l = par1IBlockAccess.func_72805_g(par2, par3, par4) & 0x7;
/*     */     
/*  93 */     switch (l) {
/*     */     case 1: 
/*     */     default: 
/*  96 */       func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
/*  97 */       break;
/*     */     case 2: 
/*  99 */       func_149676_a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
/* 100 */       break;
/*     */     case 3: 
/* 102 */       func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
/* 103 */       break;
/*     */     case 4: 
/* 105 */       func_149676_a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
/* 106 */       break;
/*     */     case 5: 
/* 108 */       func_149676_a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
/*     */     }
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 114 */     func_149719_a(par1World, par2, par3, par4);
/* 115 */     return super.func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/* 120 */     int l = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 2.5D) & 0x3;
/* 121 */     par1World.func_72921_c(par2, par3, par4, l, 2);
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 126 */     return new ItemStack(this);
/*     */   }
/*     */   
/*     */   public int func_149643_k(World par1World, int par2, int par3, int par4)
/*     */   {
/* 131 */     TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
/* 132 */     return (tileentity != null) && ((tileentity instanceof TileEntityAlluringSkull)) ? ((TileEntityAlluringSkull)tileentity).getSkullType() : super.func_149643_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 138 */     return par1;
/*     */   }
/*     */   
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 143 */     if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
/* 144 */       par5 |= 0x8;
/* 145 */       par1World.func_72921_c(par2, par3, par4, par5, 4);
/*     */     }
/*     */     
/* 148 */     func_149697_b(par1World, par2, par3, par4, par5, 0);
/*     */     
/* 150 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 155 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 160 */     ArrayList<ItemStack> drops = new ArrayList();
/* 161 */     if ((metadata & 0x8) == 0) {
/* 162 */       ItemStack itemstack = new ItemStack(this);
/* 163 */       TileEntityAlluringSkull tileentityskull = (TileEntityAlluringSkull)world.func_147438_o(x, y, z);
/*     */       
/* 165 */       if (tileentityskull == null) {
/* 166 */         return drops;
/*     */       }
/* 168 */       drops.add(itemstack);
/*     */     }
/* 170 */     return drops;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/* 175 */     return Item.func_150898_a(this);
/*     */   }
/*     */   
/*     */   private boolean func_82528_d(World par1World, int par2, int par3, int par4, int par5) {
/* 179 */     if (par1World.func_147439_a(par2, par3, par4) != this) {
/* 180 */       return false;
/*     */     }
/* 182 */     TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
/* 183 */     return ((TileEntityAlluringSkull)tileentity).getSkullType() == par5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {}
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 195 */     return net.minecraft.init.Blocks.field_150425_aM.func_149733_h(par1);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public String func_149702_O()
/*     */   {
/* 201 */     return func_149641_N();
/*     */   }
/*     */   
/*     */   public static void allure(World world, double posX, double posY, double posZ, int quad)
/*     */   {
/*     */     try
/*     */     {
/* 208 */       float r = 64.0F;
/* 209 */       float dy = 10.0F;
/* 210 */       AxisAlignedBB bounds = null;
/*     */       
/* 212 */       switch (quad) {
/*     */       case 0: 
/* 214 */         bounds = AxisAlignedBB.func_72330_a(posX, posY - 10.0D, posZ - 64.0D, posX + 64.0D, posY, posZ);
/* 215 */         break;
/*     */       case 1: 
/* 217 */         bounds = AxisAlignedBB.func_72330_a(posX - 64.0D, posY - 10.0D, posZ - 64.0D, posX, posY, posZ);
/* 218 */         break;
/*     */       case 2: 
/* 220 */         bounds = AxisAlignedBB.func_72330_a(posX, posY - 10.0D, posZ, posX + 64.0D, posY, posZ + 64.0D);
/* 221 */         break;
/*     */       case 3: 
/* 223 */         bounds = AxisAlignedBB.func_72330_a(posX - 64.0D, posY - 10.0D, posZ, posX, posY, posZ + 64.0D);
/* 224 */         break;
/*     */       case 4: 
/* 226 */         bounds = AxisAlignedBB.func_72330_a(posX - 64.0D, posY + 1.0D, posZ - 64.0D, posX, posY + 10.0D, posZ);
/* 227 */         break;
/*     */       case 5: 
/* 229 */         bounds = AxisAlignedBB.func_72330_a(posX, posY + 1.0D, posZ, posX + 64.0D, posY + 10.0D, posZ + 64.0D);
/* 230 */         break;
/*     */       case 6: 
/* 232 */         bounds = AxisAlignedBB.func_72330_a(posX - 64.0D, posY + 1.0D, posZ, posX, posY + 10.0D, posZ + 64.0D);
/* 233 */         break;
/*     */       case 7: 
/*     */       default: 
/* 236 */         bounds = AxisAlignedBB.func_72330_a(posX, posY + 1.0D, posZ - 64.0D, posX + 64.0D, posY + 10.0D, posZ);
/*     */       }
/*     */       
/*     */       
/* 240 */       for (Object obj : world.func_72872_a(EntityCreature.class, bounds)) {
/* 241 */         EntityCreature creature = (EntityCreature)obj;
/* 242 */         if ((creature.func_70668_bt() == net.minecraft.entity.EnumCreatureAttribute.UNDEAD) && 
/* 243 */           (!creature.func_70661_as().func_75492_a(posX, posY, posZ, 1.0D))) {
/* 244 */           int x = MathHelper.func_76128_c(posX);
/* 245 */           int y = MathHelper.func_76128_c(posY);
/* 246 */           int z = MathHelper.func_76128_c(posZ);
/* 247 */           PathEntity path = world.func_72844_a(creature, x, y, z, 10.0F, true, false, false, true);
/* 248 */           if (path != null) {
/* 249 */             creature.func_70778_a(path);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 255 */       Log.instance().debug(String.format("Exception occurred alluring with a skull! %s", new Object[] { e.toString() }));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityAlluringSkull extends TileEntityBase {
/*     */     private int skullType;
/*     */     private int skullRotation;
/* 262 */     private int quad = 0;
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 266 */       super.func_145845_h();
/*     */       
/* 268 */       if ((!this.field_145850_b.field_72995_K) && (this.skullType == 1) && (this.ticks % 100L == 0L)) {
/* 269 */         if (++this.quad >= 8) {
/* 270 */           this.quad = 0;
/*     */         }
/* 272 */         BlockAlluringSkull.allure(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.quad);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 278 */       super.func_145841_b(par1NBTTagCompound);
/* 279 */       par1NBTTagCompound.func_74774_a("SkullType", (byte)(this.skullType & 0xFF));
/* 280 */       par1NBTTagCompound.func_74774_a("Rot", (byte)(this.skullRotation & 0xFF));
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 285 */       super.func_145839_a(par1NBTTagCompound);
/* 286 */       this.skullType = par1NBTTagCompound.func_74771_c("SkullType");
/* 287 */       this.skullRotation = par1NBTTagCompound.func_74771_c("Rot");
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 292 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 293 */       func_145841_b(nbtTag);
/* 294 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 299 */       super.onDataPacket(net, packet);
/* 300 */       func_145839_a(packet.func_148857_g());
/* 301 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public void setSkullType(int par1) {
/* 305 */       this.skullType = par1;
/* 306 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public int getSkullType() {
/* 310 */       return this.skullType;
/*     */     }
/*     */     
/*     */     public void setSkullRotation(int par1) {
/* 314 */       this.skullRotation = par1;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public int func_82119_b() {
/* 319 */       return this.skullRotation;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockAlluringSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */