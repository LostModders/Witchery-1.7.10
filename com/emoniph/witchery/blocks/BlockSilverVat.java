/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockSilverVat extends BlockBaseContainer
/*     */ {
/*     */   public BlockSilverVat()
/*     */   {
/*  26 */     super(net.minecraft.block.material.Material.field_151573_f, TileEntitySilverVat.class);
/*     */     
/*  28 */     func_149711_c(8.0F);
/*  29 */     func_149672_a(field_149777_j);
/*  30 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.64F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  35 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/*  50 */     super.func_149726_b(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int x, int y, int z, int side)
/*     */   {
/*  60 */     TileEntity tile = world.func_147438_o(x, y, z);
/*  61 */     if ((tile != null) && ((tile instanceof IInventory))) {
/*  62 */       return Container.func_94526_b((IInventory)tile);
/*     */     }
/*  64 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  71 */     if (!world.field_72995_K) {
/*  72 */       TileEntitySilverVat tile = (TileEntitySilverVat)BlockUtil.getTileEntity(world, x, y, z, TileEntitySilverVat.class);
/*  73 */       if (tile != null) {
/*  74 */         ItemStack stack = tile.func_70301_a(0);
/*  75 */         if (stack != null) {
/*  76 */           EntityItem entity = new EntityItem(world, player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, stack);
/*  77 */           entity.field_70159_w = (entity.field_70181_x = entity.field_70179_y = 0.0D);
/*  78 */           world.func_72838_d(entity);
/*  79 */           tile.func_70299_a(0, null);
/*  80 */           tile.markBlockForUpdate(true);
/*     */         }
/*     */       }
/*  83 */       return true;
/*     */     }
/*  85 */     return false;
/*     */   }
/*     */   
/*     */ 
/*  89 */   public static final ItemStack GOLD_INGOT = new ItemStack(Items.field_151043_k);
/*     */   
/*     */   public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
/*     */   {
/*  93 */     if ((y == tileY) && ((x == tileX) || (z == tileZ))) {
/*  94 */       TileEntitySilverVat vat = (TileEntitySilverVat)BlockUtil.getTileEntity(world, x, y, z, TileEntitySilverVat.class);
/*  95 */       if ((vat != null) && (!vat.reenterLock)) {
/*  96 */         vat.reenterLock = true;
/*     */         try {
/*  98 */           TileEntity tile = world.func_147438_o(tileX, tileY, tileZ);
/*  99 */           if ((tile != null) && ((tile instanceof ISidedInventory))) {
/* 100 */             ISidedInventory inv = (ISidedInventory)tile;
/* 101 */             int offsetX = x - tileX;
/* 102 */             int offsetZ = z - tileZ;
/* 103 */             int side = offsetX > 0 ? 5 : offsetX == 0 ? 2 : offsetZ > 0 ? 3 : 4;
/*     */             
/* 105 */             for (int slot = 0; slot < inv.func_70302_i_(); slot++) {
/* 106 */               if ((inv.func_102008_b(slot, GOLD_INGOT, side)) && (!inv.func_102007_a(slot, GOLD_INGOT, side)))
/*     */               {
/* 108 */                 ItemStack stack = inv.func_70301_a(slot);
/* 109 */                 if ((stack != null) && (stack.func_77973_b() == GOLD_INGOT.func_77973_b())) {
/* 110 */                   if ((stack.field_77994_a > vat.getLastStackSizeForSide(side)) && 
/* 111 */                     (vat.func_145831_w().field_73012_v.nextInt(5) == 0)) {
/* 112 */                     ItemStack silver = vat.func_70301_a(0);
/* 113 */                     if (silver == null) {
/* 114 */                       silver = com.emoniph.witchery.Witchery.Items.GENERIC.itemSilverDust.createStack();
/* 115 */                       vat.func_70299_a(0, silver);
/* 116 */                       vat.markBlockForUpdate(true);
/* 117 */                     } else if (silver.field_77994_a < silver.func_77976_d()) {
/* 118 */                       silver.field_77994_a += 1;
/* 119 */                       vat.markBlockForUpdate(true);
/*     */                     }
/*     */                   }
/*     */                   
/* 123 */                   vat.setLastStackSizeForSide(side, stack.field_77994_a);
/* 124 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         } finally {
/* 130 */           vat.reenterLock = false;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntitySilverVat extends TileEntity implements IInventory {
/* 137 */     private ItemStack[] slots = new ItemStack[1];
/* 138 */     private final int[] sides = new int[6];
/*     */     private boolean reenterLock;
/*     */     
/*     */     public boolean canUpdate()
/*     */     {
/* 143 */       return false;
/*     */     }
/*     */     
/*     */     public void markBlockForUpdate(boolean notifyNeighbours) {
/* 147 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 148 */       if ((notifyNeighbours) && (this.field_145850_b != null)) {
/* 149 */         this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*     */       }
/*     */     }
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 155 */       return this.slots.length;
/*     */     }
/*     */     
/*     */     public void setLastStackSizeForSide(int side, int stackSize) {
/* 159 */       this.sides[side] = stackSize;
/*     */     }
/*     */     
/*     */     public int getLastStackSizeForSide(int side) {
/* 163 */       return this.sides[side];
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int slot)
/*     */     {
/* 168 */       return this.slots[slot];
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int slot, int quantity)
/*     */     {
/* 173 */       if (this.slots[slot] != null)
/*     */       {
/*     */ 
/* 176 */         if (this.slots[slot].field_77994_a <= quantity) {
/* 177 */           ItemStack itemstack = this.slots[slot];
/* 178 */           this.slots[slot] = null;
/* 179 */           return itemstack;
/*     */         }
/* 181 */         ItemStack itemstack = this.slots[slot].func_77979_a(quantity);
/*     */         
/* 183 */         if (this.slots[slot].field_77994_a == 0) {
/* 184 */           this.slots[slot] = null;
/*     */         }
/*     */         
/* 187 */         return itemstack;
/*     */       }
/*     */       
/* 190 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int slot)
/*     */     {
/* 196 */       if (this.slots[slot] != null) {
/* 197 */         ItemStack itemstack = this.slots[slot];
/* 198 */         this.slots[slot] = null;
/* 199 */         return itemstack;
/*     */       }
/* 201 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_70299_a(int slot, ItemStack stack)
/*     */     {
/* 207 */       this.slots[slot] = stack;
/*     */       
/* 209 */       if ((stack != null) && (stack.field_77994_a > func_70297_j_())) {
/* 210 */         stack.field_77994_a = func_70297_j_();
/*     */       }
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 216 */       return func_145838_q().func_149732_F();
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 221 */       return true;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 226 */       super.func_145839_a(nbtRoot);
/* 227 */       NBTTagList nbtSlotList = nbtRoot.func_150295_c("Items", 10);
/* 228 */       this.slots = new ItemStack[func_70302_i_()];
/*     */       
/* 230 */       for (int i = 0; i < nbtSlotList.func_74745_c(); i++) {
/* 231 */         NBTTagCompound nbtSlot = nbtSlotList.func_150305_b(i);
/* 232 */         byte b0 = nbtSlot.func_74771_c("Slot");
/*     */         
/* 234 */         if ((b0 >= 0) && (b0 < this.slots.length)) {
/* 235 */           this.slots[b0] = ItemStack.func_77949_a(nbtSlot);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 242 */       super.func_145841_b(nbtRoot);
/*     */       
/* 244 */       NBTTagList nbtSlotList = new NBTTagList();
/*     */       
/* 246 */       for (int i = 0; i < this.slots.length; i++) {
/* 247 */         if (this.slots[i] != null) {
/* 248 */           NBTTagCompound nbtSlot = new NBTTagCompound();
/* 249 */           nbtSlot.func_74774_a("Slot", (byte)i);
/* 250 */           this.slots[i].func_77955_b(nbtSlot);
/* 251 */           nbtSlotList.func_74742_a(nbtSlot);
/*     */         }
/*     */       }
/*     */       
/* 255 */       nbtRoot.func_74782_a("Items", nbtSlotList);
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 260 */       return 64;
/*     */     }
/*     */     
/*     */     public void func_70296_d()
/*     */     {
/* 265 */       super.func_70296_d();
/* 266 */       if (!this.field_145850_b.field_72995_K) {
/* 267 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 273 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 274 */       func_145841_b(nbtTag);
/* 275 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 280 */       super.onDataPacket(net, packet);
/* 281 */       func_145839_a(packet.func_148857_g());
/* 282 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */     {
/* 287 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 301 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockSilverVat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */