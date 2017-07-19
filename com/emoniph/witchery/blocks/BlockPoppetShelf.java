/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeChunkManager;
/*     */ import net.minecraftforge.common.ForgeChunkManager.Ticket;
/*     */ import net.minecraftforge.common.ForgeChunkManager.Type;
/*     */ 
/*     */ public class BlockPoppetShelf extends BlockBaseContainer
/*     */ {
/*     */   public BlockPoppetShelf()
/*     */   {
/*  33 */     super(Material.field_151576_e, TileEntityPoppetShelf.class);
/*  34 */     func_149711_c(2.5F);
/*  35 */     func_149672_a(field_149769_e);
/*  36 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.51F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int posX, int posY, int posZ)
/*     */   {
/*  41 */     super.func_149726_b(world, posX, posY, posZ);
/*  42 */     BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  47 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  52 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/*  57 */     if (world.field_72995_K) {
/*  58 */       return true;
/*     */     }
/*  60 */     TileEntityPoppetShelf tileEntity = (TileEntityPoppetShelf)world.func_147438_o(posX, posY, posZ);
/*  61 */     if (tileEntity != null) {
/*  62 */       player.func_71007_a(tileEntity);
/*     */     }
/*     */     
/*  65 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World world, int posX, int posY, int posZ, Block par5)
/*     */   {
/*  71 */     boolean flag = (world.func_72864_z(posX, posY, posZ)) || (world.func_72864_z(posX, posY + 1, posZ));
/*  72 */     int i1 = world.func_72805_g(posX, posY, posZ);
/*  73 */     boolean flag1 = (i1 & 0x8) != 0;
/*     */     
/*  75 */     if ((flag) && (!flag1)) {
/*  76 */       world.func_147464_a(posX, posY, posZ, this, func_149738_a(world));
/*  77 */       world.func_72921_c(posX, posY, posZ, i1 | 0x8, 4);
/*  78 */     } else if ((!flag) && (flag1)) {
/*  79 */       world.func_72921_c(posX, posY, posZ, i1 & 0xFFFFFFF7, 4);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase entityLiving, ItemStack itemstack)
/*     */   {
/*  85 */     int l = BlockPistonBase.func_150071_a(world, posX, posY, posZ, entityLiving);
/*  86 */     world.func_72921_c(posX, posY, posZ, l, 2);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int posX, int posY, int posZ, Block par5, int par6)
/*     */   {
/*  91 */     TileEntityPoppetShelf tileEntity = (TileEntityPoppetShelf)world.func_147438_o(posX, posY, posZ);
/*  92 */     if (tileEntity != null) {
/*  93 */       for (int j1 = 0; j1 < tileEntity.func_70302_i_(); j1++) {
/*  94 */         ItemStack itemstack = tileEntity.func_70301_a(j1);
/*     */         
/*  96 */         if (itemstack != null) {
/*  97 */           float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*  98 */           float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*  99 */           float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 101 */           while (itemstack.field_77994_a > 0) {
/* 102 */             int k1 = world.field_73012_v.nextInt(21) + 10;
/*     */             
/* 104 */             if (k1 > itemstack.field_77994_a) {
/* 105 */               k1 = itemstack.field_77994_a;
/*     */             }
/*     */             
/* 108 */             itemstack.field_77994_a -= k1;
/* 109 */             EntityItem entityitem = new EntityItem(world, posX + f, posY + f1, posZ + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*     */             
/*     */ 
/* 112 */             if (itemstack.func_77942_o()) {
/* 113 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */             }
/*     */             
/* 116 */             float f3 = 0.05F;
/* 117 */             entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 118 */             entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
/* 119 */             entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 120 */             world.func_72838_d(entityitem);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 125 */       world.func_147453_f(posX, posY, posZ, par5);
/*     */     }
/*     */     
/* 128 */     super.func_149749_a(world, posX, posY, posZ, par5, par6);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int posX, int posY, int posZ, int par5)
/*     */   {
/* 138 */     return Container.func_94526_b((IInventory)world.func_147438_o(posX, posY, posZ));
/*     */   }
/*     */   
/*     */   public static class TileEntityPoppetShelf extends TileEntityBase implements IInventory {
/* 142 */     private ItemStack[] contents = new ItemStack[9];
/*     */     protected String customName;
/*     */     private ForgeChunkManager.Ticket chunkTicket;
/*     */     
/*     */     protected void initiate()
/*     */     {
/* 148 */       super.initiate();
/* 149 */       if ((!this.field_145850_b.field_72995_K) && 
/* 150 */         (this.chunkTicket == null)) {
/* 151 */         this.chunkTicket = ForgeChunkManager.requestTicket(Witchery.instance, this.field_145850_b, ForgeChunkManager.Type.NORMAL);
/* 152 */         if (this.chunkTicket != null) {
/* 153 */           this.chunkTicket.getModData().func_74768_a("poppetX", this.field_145851_c);
/* 154 */           this.chunkTicket.getModData().func_74768_a("poppetY", this.field_145848_d);
/* 155 */           this.chunkTicket.getModData().func_74768_a("poppetZ", this.field_145849_e);
/* 156 */           forceChunkLoading(this.chunkTicket);
/*     */         } else {
/* 158 */           Log.instance().warning(String.format("The poppet shelf at %d, %d, %d failed to register a chunk loader.", new Object[] { Integer.valueOf(this.field_145851_c), Integer.valueOf(this.field_145848_d), Integer.valueOf(this.field_145849_e) }));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void forceChunkLoading(ForgeChunkManager.Ticket ticket)
/*     */     {
/* 165 */       if (this.chunkTicket == null) {
/* 166 */         this.chunkTicket = ticket;
/*     */       }
/*     */       try
/*     */       {
/* 170 */         ForgeChunkManager.forceChunk(this.chunkTicket, new ChunkCoordIntPair(this.field_145851_c >> 4, this.field_145849_e >> 4));
/*     */       } catch (Exception e) {
/* 172 */         Log.instance().warning(e, String.format("Unexpected exception occurred forcing chunk loading for popet shelf at %d, %d, %d.", new Object[] { Integer.valueOf(this.field_145851_c), Integer.valueOf(this.field_145848_d), Integer.valueOf(this.field_145849_e) }));
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145843_s()
/*     */     {
/* 178 */       if (!this.field_145850_b.field_72995_K) {
/* 179 */         if (this.chunkTicket != null) {
/* 180 */           ForgeChunkManager.releaseTicket(this.chunkTicket);
/*     */         } else {
/* 182 */           Log.instance().warning(String.format("Chunk loader ticket is null for poppet shelf at %d, %d, %d.", new Object[] { Integer.valueOf(this.field_145851_c), Integer.valueOf(this.field_145848_d), Integer.valueOf(this.field_145849_e) }));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 187 */       super.func_145843_s();
/*     */     }
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 192 */       return 9;
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int slot)
/*     */     {
/* 197 */       return this.contents[slot];
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int slot, int size)
/*     */     {
/* 202 */       if (this.contents[slot] != null)
/*     */       {
/*     */ 
/* 205 */         if (this.contents[slot].field_77994_a <= size) {
/* 206 */           ItemStack itemstack = this.contents[slot];
/* 207 */           this.contents[slot] = null;
/* 208 */           func_70296_d();
/* 209 */           return itemstack;
/*     */         }
/* 211 */         ItemStack itemstack = this.contents[slot].func_77979_a(size);
/*     */         
/* 213 */         if (this.contents[slot].field_77994_a == 0) {
/* 214 */           this.contents[slot] = null;
/*     */         }
/*     */         
/* 217 */         func_70296_d();
/* 218 */         return itemstack;
/*     */       }
/*     */       
/* 221 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int slot)
/*     */     {
/* 227 */       if (this.contents[slot] != null) {
/* 228 */         ItemStack itemstack = this.contents[slot];
/* 229 */         this.contents[slot] = null;
/* 230 */         return itemstack;
/*     */       }
/* 232 */       return null;
/*     */     }
/*     */     
/*     */     public int getRandomStackFromInventory()
/*     */     {
/* 237 */       int i = -1;
/* 238 */       int j = 1;
/*     */       
/* 240 */       for (int k = 0; k < this.contents.length; k++) {
/* 241 */         if ((this.contents[k] != null) && (this.field_145850_b.field_73012_v.nextInt(j++) == 0)) {
/* 242 */           i = k;
/*     */         }
/*     */       }
/*     */       
/* 246 */       return i;
/*     */     }
/*     */     
/*     */     public void func_70299_a(int slot, ItemStack itemstack)
/*     */     {
/* 251 */       this.contents[slot] = itemstack;
/*     */       
/* 253 */       if ((itemstack != null) && (itemstack.field_77994_a > func_70297_j_())) {
/* 254 */         itemstack.field_77994_a = func_70297_j_();
/*     */       }
/*     */       
/* 257 */       func_70296_d();
/*     */     }
/*     */     
/*     */     public int addItem(ItemStack itemstack) {
/* 261 */       for (int i = 0; i < this.contents.length; i++) {
/* 262 */         if (this.contents[i] == null) {
/* 263 */           func_70299_a(i, itemstack);
/* 264 */           return i;
/*     */         }
/*     */       }
/*     */       
/* 268 */       return -1;
/*     */     }
/*     */     
/*     */     public void func_70296_d()
/*     */     {
/* 273 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 274 */       super.func_70296_d();
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 279 */       return "container.witchery:poppetshelf";
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 284 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 285 */       func_145841_b(nbtTag);
/* 286 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 291 */       super.onDataPacket(net, packet);
/* 292 */       func_145839_a(packet.func_148857_g());
/* 293 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_145818_k_()
/*     */     {
/* 299 */       return false;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 304 */       super.func_145839_a(nbtTag);
/* 305 */       NBTTagList nbttaglist = nbtTag.func_150295_c("Items", 10);
/* 306 */       this.contents = new ItemStack[func_70302_i_()];
/*     */       
/* 308 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 309 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 310 */         int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
/*     */         
/* 312 */         if ((j >= 0) && (j < this.contents.length)) {
/* 313 */           this.contents[j] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 317 */       if (nbtTag.func_74764_b("CustomName")) {
/* 318 */         this.customName = nbtTag.func_74779_i("CustomName");
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 324 */       super.func_145841_b(nbtTag);
/* 325 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/* 327 */       for (int i = 0; i < this.contents.length; i++) {
/* 328 */         if (this.contents[i] != null) {
/* 329 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 330 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 331 */           this.contents[i].func_77955_b(nbttagcompound1);
/* 332 */           nbttaglist.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 336 */       nbtTag.func_74782_a("Items", nbttaglist);
/*     */       
/* 338 */       if (func_145818_k_()) {
/* 339 */         nbtTag.func_74778_a("CustomName", this.customName);
/*     */       }
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 345 */       return 64;
/*     */     }
/*     */     
/*     */     public boolean func_70300_a(EntityPlayer player)
/*     */     {
/* 350 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 364 */       return (itemstack != null) && (itemstack.func_77973_b() == Witchery.Items.POPPET);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPoppetShelf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */