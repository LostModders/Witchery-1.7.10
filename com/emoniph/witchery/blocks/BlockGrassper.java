/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockGrassper extends BlockBaseContainer
/*     */ {
/*     */   public BlockGrassper()
/*     */   {
/*  28 */     super(Material.field_151585_k, TileEntityGrassper.class);
/*     */     
/*  30 */     func_149672_a(field_149779_h);
/*     */     
/*  32 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.51F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int posX, int posY, int posZ)
/*     */   {
/*  37 */     super.func_149726_b(world, posX, posY, posZ);
/*  38 */     BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/*  58 */     if (world.field_72995_K) {
/*  59 */       return true;
/*     */     }
/*  61 */     TileEntityGrassper tileEntity = (TileEntityGrassper)world.func_147438_o(posX, posY, posZ);
/*  62 */     if (tileEntity != null) {
/*  63 */       ItemStack stack = tileEntity.func_70301_a(0);
/*  64 */       if (stack != null) {
/*  65 */         tileEntity.func_70299_a(0, null);
/*  66 */         world.func_72838_d(new EntityItem(world, 0.5D + posX, 0.8D + posY, 0.5D + posZ, stack));
/*     */       } else {
/*  68 */         stack = player.func_70694_bm();
/*  69 */         if (stack != null) {
/*  70 */           tileEntity.func_70299_a(0, stack.func_77979_a(1));
/*  71 */           if (stack.field_77994_a == 0) {
/*  72 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  78 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World world, int posX, int posY, int posZ, Block par5)
/*     */   {
/*  84 */     boolean flag = (world.func_72864_z(posX, posY, posZ)) || (world.func_72864_z(posX, posY + 1, posZ));
/*  85 */     int i1 = world.func_72805_g(posX, posY, posZ);
/*  86 */     boolean flag1 = (i1 & 0x8) != 0;
/*     */     
/*  88 */     if ((flag) && (!flag1)) {
/*  89 */       world.func_147464_a(posX, posY, posZ, this, func_149738_a(world));
/*  90 */       world.func_72921_c(posX, posY, posZ, i1 | 0x8, 4);
/*  91 */     } else if ((!flag) && (flag1)) {
/*  92 */       world.func_72921_c(posX, posY, posZ, i1 & 0xFFFFFFF7, 4);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase entityLiving, ItemStack itemstack)
/*     */   {
/*  98 */     int l = BlockPistonBase.func_150071_a(world, posX, posY, posZ, entityLiving);
/*  99 */     world.func_72921_c(posX, posY, posZ, l, 2);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int posX, int posY, int posZ, Block par5, int par6)
/*     */   {
/* 104 */     TileEntityGrassper tileEntity = (TileEntityGrassper)world.func_147438_o(posX, posY, posZ);
/* 105 */     if (tileEntity != null) {
/* 106 */       for (int j1 = 0; j1 < tileEntity.func_70302_i_(); j1++) {
/* 107 */         ItemStack itemstack = tileEntity.func_70301_a(j1);
/*     */         
/* 109 */         if (itemstack != null) {
/* 110 */           float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 111 */           float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 112 */           float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 114 */           while (itemstack.field_77994_a > 0) {
/* 115 */             int k1 = world.field_73012_v.nextInt(21) + 10;
/*     */             
/* 117 */             if (k1 > itemstack.field_77994_a) {
/* 118 */               k1 = itemstack.field_77994_a;
/*     */             }
/*     */             
/* 121 */             itemstack.field_77994_a -= k1;
/* 122 */             EntityItem entityitem = new EntityItem(world, posX + f, posY + f1, posZ + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*     */             
/*     */ 
/* 125 */             if (itemstack.func_77942_o()) {
/* 126 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */             }
/*     */             
/* 129 */             float f3 = 0.05F;
/* 130 */             entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 131 */             entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
/* 132 */             entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 133 */             world.func_72838_d(entityitem);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 138 */       world.func_147453_f(posX, posY, posZ, par5);
/*     */     }
/*     */     
/* 141 */     super.func_149749_a(world, posX, posY, posZ, par5, par6);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 146 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int posX, int posY, int posZ, int par5)
/*     */   {
/* 151 */     return Container.func_94526_b((IInventory)world.func_147438_o(posX, posY, posZ));
/*     */   }
/*     */   
/*     */   public static class TileEntityGrassper extends TileEntityBase implements IInventory {
/* 155 */     private ItemStack[] contents = new ItemStack[1];
/*     */     protected String customName;
/*     */     
/*     */     public boolean canUpdate()
/*     */     {
/* 160 */       return false;
/*     */     }
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 165 */       return this.contents.length;
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int slot)
/*     */     {
/* 170 */       return this.contents[slot];
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int slot, int size)
/*     */     {
/* 175 */       if (this.contents[slot] != null)
/*     */       {
/*     */ 
/* 178 */         if (this.contents[slot].field_77994_a <= size) {
/* 179 */           ItemStack itemstack = this.contents[slot];
/* 180 */           this.contents[slot] = null;
/* 181 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 182 */           return itemstack;
/*     */         }
/* 184 */         ItemStack itemstack = this.contents[slot].func_77979_a(size);
/*     */         
/* 186 */         if (this.contents[slot].field_77994_a == 0) {
/* 187 */           this.contents[slot] = null;
/*     */         }
/*     */         
/* 190 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 191 */         return itemstack;
/*     */       }
/*     */       
/* 194 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int slot)
/*     */     {
/* 200 */       if (this.contents[slot] != null) {
/* 201 */         ItemStack itemstack = this.contents[slot];
/* 202 */         this.contents[slot] = null;
/* 203 */         return itemstack;
/*     */       }
/* 205 */       return null;
/*     */     }
/*     */     
/*     */     public int getRandomStackFromInventory()
/*     */     {
/* 210 */       int i = -1;
/* 211 */       int j = 1;
/*     */       
/* 213 */       for (int k = 0; k < this.contents.length; k++) {
/* 214 */         if ((this.contents[k] != null) && (this.field_145850_b.field_73012_v.nextInt(j++) == 0)) {
/* 215 */           i = k;
/*     */         }
/*     */       }
/*     */       
/* 219 */       return i;
/*     */     }
/*     */     
/*     */     public void func_70299_a(int slot, ItemStack itemstack)
/*     */     {
/* 224 */       this.contents[slot] = itemstack;
/*     */       
/* 226 */       if ((itemstack != null) && (itemstack.field_77994_a > func_70297_j_())) {
/* 227 */         itemstack.field_77994_a = func_70297_j_();
/*     */       }
/*     */       
/* 230 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public int addItem(ItemStack itemstack) {
/* 234 */       for (int i = 0; i < this.contents.length; i++) {
/* 235 */         if (this.contents[i] == null) {
/* 236 */           func_70299_a(i, itemstack);
/* 237 */           return i;
/*     */         }
/*     */       }
/*     */       
/* 241 */       return -1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public String func_145825_b()
/*     */     {
/* 252 */       return "tile.witcheryGrassper.name";
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 257 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 258 */       func_145841_b(nbtTag);
/* 259 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 264 */       super.onDataPacket(net, packet);
/* 265 */       func_145839_a(packet.func_148857_g());
/* 266 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 271 */       return false;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 276 */       super.func_145839_a(nbtTag);
/* 277 */       NBTTagList nbttaglist = nbtTag.func_150295_c("Items", 10);
/* 278 */       this.contents = new ItemStack[func_70302_i_()];
/*     */       
/* 280 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 281 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 282 */         int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
/*     */         
/* 284 */         if ((j >= 0) && (j < this.contents.length)) {
/* 285 */           this.contents[j] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 289 */       if (nbtTag.func_74764_b("CustomName")) {
/* 290 */         this.customName = nbtTag.func_74779_i("CustomName");
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 296 */       super.func_145841_b(nbtTag);
/* 297 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/* 299 */       for (int i = 0; i < this.contents.length; i++) {
/* 300 */         if (this.contents[i] != null) {
/* 301 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 302 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 303 */           this.contents[i].func_77955_b(nbttagcompound1);
/* 304 */           nbttaglist.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 308 */       nbtTag.func_74782_a("Items", nbttaglist);
/*     */       
/* 310 */       if (func_145818_k_()) {
/* 311 */         nbtTag.func_74778_a("CustomName", this.customName);
/*     */       }
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 317 */       return 1;
/*     */     }
/*     */     
/*     */     public boolean func_70300_a(EntityPlayer player)
/*     */     {
/* 322 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_70295_k_() {}
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_70305_f() {}
/*     */     
/*     */ 
/*     */     public boolean func_94041_b(int slot, ItemStack itemstack)
/*     */     {
/* 336 */       return (slot == 0) && (this.contents[0] == null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockGrassper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */