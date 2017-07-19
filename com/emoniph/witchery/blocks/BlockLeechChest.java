/*      */ package com.emoniph.witchery.blocks;
/*      */ 
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.renderer.texture.IIconRegister;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.passive.EntityOcelot;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.ContainerChest;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.NetworkManager;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BlockLeechChest
/*      */   extends BlockBaseContainer
/*      */ {
/*   41 */   private final Random random = new Random();
/*      */   public final int chestType;
/*      */   
/*      */   public BlockLeechChest()
/*      */   {
/*   46 */     super(Material.field_151576_e, TileEntityLeechChest.class);
/*   47 */     this.chestType = 1;
/*   48 */     func_149711_c(25.0F);
/*   49 */     func_149752_b(1000.0F);
/*   50 */     func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*      */   }
/*      */   
/*      */   public boolean func_149662_c()
/*      */   {
/*   55 */     return false;
/*      */   }
/*      */   
/*      */   public boolean func_149686_d()
/*      */   {
/*   60 */     return false;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*      */   {
/*   66 */     return false;
/*      */   }
/*      */   
/*      */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*      */   {
/*   71 */     if (par1IBlockAccess.func_147439_a(par2, par3, par4 - 1) == this) {
/*   72 */       func_149676_a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
/*   73 */     } else if (par1IBlockAccess.func_147439_a(par2, par3, par4 + 1) == this) {
/*   74 */       func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
/*   75 */     } else if (par1IBlockAccess.func_147439_a(par2 - 1, par3, par4) == this) {
/*   76 */       func_149676_a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*   77 */     } else if (par1IBlockAccess.func_147439_a(par2 + 1, par3, par4) == this) {
/*   78 */       func_149676_a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
/*      */     } else {
/*   80 */       func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*      */     }
/*      */   }
/*      */   
/*      */   public void func_149726_b(World par1World, int par2, int par3, int par4)
/*      */   {
/*   86 */     super.func_149726_b(par1World, par2, par3, par4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*      */   {
/*  113 */     Block l = par1World.func_147439_a(par2, par3, par4 - 1);
/*  114 */     Block i1 = par1World.func_147439_a(par2, par3, par4 + 1);
/*  115 */     Block j1 = par1World.func_147439_a(par2 - 1, par3, par4);
/*  116 */     Block k1 = par1World.func_147439_a(par2 + 1, par3, par4);
/*  117 */     byte b0 = 0;
/*  118 */     int l1 = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*      */     
/*  120 */     if (l1 == 0) {
/*  121 */       b0 = 2;
/*      */     }
/*      */     
/*  124 */     if (l1 == 1) {
/*  125 */       b0 = 5;
/*      */     }
/*      */     
/*  128 */     if (l1 == 2) {
/*  129 */       b0 = 3;
/*      */     }
/*      */     
/*  132 */     if (l1 == 3) {
/*  133 */       b0 = 4;
/*      */     }
/*      */     
/*  136 */     if ((l != this) && (i1 != this) && (j1 != this) && (k1 != this)) {
/*  137 */       par1World.func_72921_c(par2, par3, par4, b0, 3);
/*      */     } else {
/*  139 */       if (((l == this) || (i1 == this)) && ((b0 == 4) || (b0 == 5))) {
/*  140 */         if (l == this) {
/*  141 */           par1World.func_72921_c(par2, par3, par4 - 1, b0, 3);
/*      */         } else {
/*  143 */           par1World.func_72921_c(par2, par3, par4 + 1, b0, 3);
/*      */         }
/*      */         
/*  146 */         par1World.func_72921_c(par2, par3, par4, b0, 3);
/*      */       }
/*      */       
/*  149 */       if (((j1 == this) || (k1 == this)) && ((b0 == 2) || (b0 == 3))) {
/*  150 */         if (j1 == this) {
/*  151 */           par1World.func_72921_c(par2 - 1, par3, par4, b0, 3);
/*      */         } else {
/*  153 */           par1World.func_72921_c(par2 + 1, par3, par4, b0, 3);
/*      */         }
/*      */         
/*  156 */         par1World.func_72921_c(par2, par3, par4, b0, 3);
/*      */       }
/*      */     }
/*      */     
/*  160 */     TileEntity tile = par1World.func_147438_o(par2, par3, par4);
/*  161 */     if ((tile != null) && ((tile instanceof TileEntityLeechChest))) {
/*  162 */       TileEntityLeechChest chest = (TileEntityLeechChest)tile;
/*      */       
/*  164 */       if (par6ItemStack.func_82837_s()) {
/*  165 */         chest.setChestGuiName(par6ItemStack.func_82833_r());
/*      */       }
/*      */       
/*  168 */       if ((!par1World.field_72995_K) && (par6ItemStack.func_77942_o()) && (par6ItemStack.func_77978_p().func_74764_b("WITCPlayers")))
/*      */       {
/*  170 */         NBTTagList nbtPlayersList = par6ItemStack.func_77978_p().func_150295_c("WITCPlayers", 10);
/*      */         
/*  172 */         chest.players.clear();
/*  173 */         for (int i = 0; i < nbtPlayersList.func_74745_c(); i++) {
/*  174 */           NBTTagCompound nbtPlayer = nbtPlayersList.func_150305_b(i);
/*  175 */           String s = nbtPlayer.func_74779_i("Player");
/*  176 */           if ((s != null) && (!s.isEmpty())) {
/*  177 */             chest.players.add(s);
/*      */           }
/*      */         }
/*      */         
/*  181 */         chest.sync();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*      */   {
/*  307 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*      */   {
/*  323 */     super.func_149695_a(par1World, par2, par3, par4, par5);
/*  324 */     TileEntity tile = par1World.func_147438_o(par2, par3, par4);
/*  325 */     if ((tile != null) && ((tile instanceof TileEntityLeechChest))) {
/*  326 */       TileEntityLeechChest tileentitychest = (TileEntityLeechChest)tile;
/*  327 */       tileentitychest.func_145836_u();
/*      */     }
/*      */   }
/*      */   
/*      */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*      */   {
/*  333 */     TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
/*  334 */     if ((tileentity != null) && ((tileentity instanceof TileEntityLeechChest))) {
/*  335 */       TileEntityLeechChest tileentitychest = (TileEntityLeechChest)tileentity;
/*  336 */       for (int j1 = 0; j1 < tileentitychest.func_70302_i_(); j1++) {
/*  337 */         ItemStack itemstack = tileentitychest.func_70301_a(j1);
/*      */         
/*  339 */         if (itemstack != null) {
/*  340 */           float f = this.random.nextFloat() * 0.8F + 0.1F;
/*  341 */           float f1 = this.random.nextFloat() * 0.8F + 0.1F;
/*      */           
/*      */           EntityItem entityitem;
/*  344 */           for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; par1World.func_72838_d(entityitem))
/*      */           {
/*  346 */             int k1 = this.random.nextInt(21) + 10;
/*      */             
/*  348 */             if (k1 > itemstack.field_77994_a) {
/*  349 */               k1 = itemstack.field_77994_a;
/*      */             }
/*      */             
/*  352 */             itemstack.field_77994_a -= k1;
/*  353 */             entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*      */             
/*  355 */             float f3 = 0.05F;
/*  356 */             entityitem.field_70159_w = ((float)this.random.nextGaussian() * f3);
/*  357 */             entityitem.field_70181_x = ((float)this.random.nextGaussian() * f3 + 0.2F);
/*  358 */             entityitem.field_70179_y = ((float)this.random.nextGaussian() * f3);
/*      */             
/*  360 */             if (itemstack.func_77942_o()) {
/*  361 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  367 */       par1World.func_147453_f(par2, par3, par4, par5);
/*      */     }
/*      */     
/*  370 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*      */   }
/*      */   
/*      */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*      */   {
/*  375 */     ArrayList<ItemStack> drops = new ArrayList();
/*  376 */     return drops;
/*      */   }
/*      */   
/*      */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
/*  380 */     if (!par1World.field_72995_K) {
/*  381 */       ItemStack itemstack = new ItemStack(this);
/*  382 */       TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
/*  383 */       if ((tileentity != null) && ((tileentity instanceof TileEntityLeechChest))) {
/*  384 */         TileEntityLeechChest chest = (TileEntityLeechChest)tileentity;
/*  385 */         if (chest.players.size() > 0) {
/*  386 */           itemstack.func_77982_d(new NBTTagCompound());
/*  387 */           NBTTagList nbtPlayers = new NBTTagList();
/*      */           
/*  389 */           for (int i = 0; i < chest.players.size(); i++) {
/*  390 */             NBTTagCompound nbtPlayer = new NBTTagCompound();
/*  391 */             nbtPlayer.func_74778_a("Player", (String)chest.players.get(i));
/*  392 */             nbtPlayers.func_74742_a(nbtPlayer);
/*      */           }
/*      */           
/*  395 */           itemstack.func_77978_p().func_74782_a("WITCPlayers", nbtPlayers);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  400 */       func_149642_a(par1World, par2, par3, par4, itemstack);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
/*      */   {
/*  407 */     if (par1World.field_72995_K) {
/*  408 */       return true;
/*      */     }
/*  410 */     IInventory iinventory = getInventory(par1World, par2, par3, par4);
/*      */     
/*  412 */     if (iinventory != null) {
/*  413 */       TileEntity tileEntity = par1World.func_147438_o(par2, par3, par4);
/*  414 */       if ((tileEntity != null) && ((tileEntity instanceof TileEntityLeechChest))) {
/*  415 */         TileEntityLeechChest chest = (TileEntityLeechChest)tileEntity;
/*  416 */         chest.storePlayer(player);
/*      */       }
/*      */       
/*  419 */       player.func_71007_a(iinventory);
/*      */     }
/*      */     
/*  422 */     return true;
/*      */   }
/*      */   
/*      */   public IInventory getInventory(World par1World, int par2, int par3, int par4)
/*      */   {
/*  427 */     Object object = par1World.func_147438_o(par2, par3, par4);
/*      */     
/*  429 */     if (object == null)
/*  430 */       return null;
/*  431 */     if (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN))
/*  432 */       return null;
/*  433 */     if (isOcelotBlockingChest(par1World, par2, par3, par4))
/*  434 */       return null;
/*  435 */     if ((par1World.func_147439_a(par2 - 1, par3, par4) == this) && ((par1World.isSideSolid(par2 - 1, par3 + 1, par4, ForgeDirection.DOWN)) || (isOcelotBlockingChest(par1World, par2 - 1, par3, par4))))
/*      */     {
/*      */ 
/*  438 */       return null; }
/*  439 */     if ((par1World.func_147439_a(par2 + 1, par3, par4) == this) && ((par1World.isSideSolid(par2 + 1, par3 + 1, par4, ForgeDirection.DOWN)) || (isOcelotBlockingChest(par1World, par2 + 1, par3, par4))))
/*      */     {
/*      */ 
/*  442 */       return null; }
/*  443 */     if ((par1World.func_147439_a(par2, par3, par4 - 1) == this) && ((par1World.isSideSolid(par2, par3 + 1, par4 - 1, ForgeDirection.DOWN)) || (isOcelotBlockingChest(par1World, par2, par3, par4 - 1))))
/*      */     {
/*      */ 
/*  446 */       return null; }
/*  447 */     if ((par1World.func_147439_a(par2, par3, par4 + 1) == this) && ((par1World.isSideSolid(par2, par3 + 1, par4 + 1, ForgeDirection.DOWN)) || (isOcelotBlockingChest(par1World, par2, par3, par4 + 1))))
/*      */     {
/*      */ 
/*  450 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  480 */     return (IInventory)object;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean func_149744_f()
/*      */   {
/*  486 */     return this.chestType == 1;
/*      */   }
/*      */   
/*      */   public int func_149709_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*      */   {
/*  491 */     if (!func_149744_f()) {
/*  492 */       return 0;
/*      */     }
/*  494 */     TileEntity tile = par1IBlockAccess.func_147438_o(par2, par3, par4);
/*  495 */     if ((tile != null) && ((tile instanceof TileEntityLeechChest))) {
/*  496 */       int i1 = ((TileEntityLeechChest)tile).numUsingPlayers;
/*  497 */       return MathHelper.func_76125_a(i1, 0, 15);
/*      */     }
/*  499 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int func_149748_c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*      */   {
/*  506 */     return par5 == 1 ? func_149709_b(par1IBlockAccess, par2, par3, par4, par5) : 0;
/*      */   }
/*      */   
/*      */   public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {
/*  510 */     Iterator iterator = par0World.func_72872_a(EntityOcelot.class, AxisAlignedBB.func_72330_a(par1, par2 + 1, par3, par1 + 1, par2 + 2, par3 + 1)).iterator();
/*      */     
/*      */     EntityOcelot entityocelot;
/*      */     do
/*      */     {
/*  515 */       if (!iterator.hasNext()) {
/*  516 */         return false;
/*      */       }
/*      */       
/*  519 */       EntityOcelot entityocelot1 = (EntityOcelot)iterator.next();
/*  520 */       entityocelot = entityocelot1;
/*  521 */     } while (!entityocelot.func_70906_o());
/*      */     
/*  523 */     return true;
/*      */   }
/*      */   
/*      */   public boolean func_149740_M()
/*      */   {
/*  528 */     return true;
/*      */   }
/*      */   
/*      */   public int func_149736_g(World par1World, int par2, int par3, int par4, int par5)
/*      */   {
/*  533 */     return Container.func_94526_b(getInventory(par1World, par2, par3, par4));
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public void func_149651_a(IIconRegister par1IconRegister)
/*      */   {
/*  539 */     this.field_149761_L = par1IconRegister.func_94245_a("planks_oak");
/*      */   }
/*      */   
/*      */   public static class TileEntityLeechChest extends TileEntity implements IInventory {
/*  543 */     private ItemStack[] chestContents = new ItemStack[36];
/*      */     
/*      */ 
/*      */     public boolean adjacentChestChecked;
/*      */     
/*      */ 
/*      */     public TileEntity adjacentChestZNeg;
/*      */     
/*      */ 
/*      */     public TileEntity adjacentChestXPos;
/*      */     
/*      */ 
/*      */     public TileEntity adjacentChestXNeg;
/*      */     
/*      */ 
/*      */     public TileEntity adjacentChestZPosition;
/*      */     
/*      */ 
/*      */     public float lidAngle;
/*      */     
/*      */     public float prevLidAngle;
/*      */     
/*      */     public int numUsingPlayers;
/*      */     
/*      */     private int ticksSinceSync;
/*      */     
/*      */     private int cachedChestType;
/*      */     
/*      */     private String customName;
/*      */     
/*  573 */     public ArrayList<String> players = new ArrayList();
/*      */     
/*      */     public TileEntityLeechChest() {
/*  576 */       this.cachedChestType = -1;
/*      */     }
/*      */     
/*      */     public void sync() {
/*  580 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */     }
/*      */     
/*      */     public void storePlayer(EntityPlayer player) {
/*  584 */       if ((!this.field_145850_b.field_72995_K) && 
/*  585 */         (player != null) && 
/*  586 */         (!this.players.contains(player.func_70005_c_()))) {
/*  587 */         this.players.add(player.func_70005_c_());
/*  588 */         while (this.players.size() > 3) {
/*  589 */           this.players.remove(0);
/*      */         }
/*  591 */         sync();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public String popUserExcept(EntityPlayer usingPlayer)
/*      */     {
/*  598 */       String missingPlayers = "";
/*  599 */       for (int i = this.players.size() - 1; i >= 0; i--) {
/*  600 */         String foundPlayerName = (String)this.players.get(i);
/*  601 */         if (!foundPlayerName.equals(usingPlayer.func_70005_c_())) {
/*  602 */           if (usingPlayer.field_70170_p.func_72924_a(foundPlayerName) != null) {
/*  603 */             this.players.remove(i);
/*  604 */             sync();
/*  605 */             return foundPlayerName;
/*      */           }
/*  607 */           missingPlayers = missingPlayers + foundPlayerName + " ";
/*      */         }
/*  609 */         else if (this.players.size() == 1) {
/*  610 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, usingPlayer, "tile.witcheryLeechChest.onlyowntaglock", new Object[0]);
/*  611 */           return null;
/*      */         }
/*      */       }
/*      */       
/*  615 */       if (!missingPlayers.isEmpty()) {
/*  616 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, usingPlayer, "tile.witcheryLeechChest.playernotloggedin", new Object[] { missingPlayers });
/*      */       }
/*      */       
/*  619 */       return null;
/*      */     }
/*      */     
/*      */     public Packet func_145844_m()
/*      */     {
/*  624 */       NBTTagCompound nbtTag = new NBTTagCompound();
/*  625 */       func_145841_b(nbtTag);
/*  626 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*      */     }
/*      */     
/*      */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*      */     {
/*  631 */       super.onDataPacket(net, packet);
/*  632 */       func_145839_a(packet.func_148857_g());
/*  633 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */     }
/*      */     
/*      */     @SideOnly(Side.CLIENT)
/*      */     public TileEntityLeechChest(int par1) {
/*  638 */       this.cachedChestType = par1;
/*      */     }
/*      */     
/*      */     public int func_70302_i_()
/*      */     {
/*  643 */       return 27;
/*      */     }
/*      */     
/*      */     public ItemStack func_70301_a(int par1)
/*      */     {
/*  648 */       return this.chestContents[par1];
/*      */     }
/*      */     
/*      */     public ItemStack func_70298_a(int par1, int par2)
/*      */     {
/*  653 */       if (this.chestContents[par1] != null)
/*      */       {
/*      */ 
/*  656 */         if (this.chestContents[par1].field_77994_a <= par2) {
/*  657 */           ItemStack itemstack = this.chestContents[par1];
/*  658 */           this.chestContents[par1] = null;
/*  659 */           func_70296_d();
/*  660 */           return itemstack;
/*      */         }
/*  662 */         ItemStack itemstack = this.chestContents[par1].func_77979_a(par2);
/*      */         
/*  664 */         if (this.chestContents[par1].field_77994_a == 0) {
/*  665 */           this.chestContents[par1] = null;
/*      */         }
/*      */         
/*  668 */         func_70296_d();
/*  669 */         return itemstack;
/*      */       }
/*      */       
/*  672 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */     public ItemStack func_70304_b(int par1)
/*      */     {
/*  678 */       if (this.chestContents[par1] != null) {
/*  679 */         ItemStack itemstack = this.chestContents[par1];
/*  680 */         this.chestContents[par1] = null;
/*  681 */         return itemstack;
/*      */       }
/*  683 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */     public void func_70299_a(int par1, ItemStack par2ItemStack)
/*      */     {
/*  689 */       this.chestContents[par1] = par2ItemStack;
/*      */       
/*  691 */       if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_())) {
/*  692 */         par2ItemStack.field_77994_a = func_70297_j_();
/*      */       }
/*      */       
/*  695 */       func_70296_d();
/*      */     }
/*      */     
/*      */     public String func_145825_b()
/*      */     {
/*  700 */       return func_145818_k_() ? this.customName : "container.chest";
/*      */     }
/*      */     
/*      */     public boolean func_145818_k_()
/*      */     {
/*  705 */       return (this.customName != null) && (this.customName.length() > 0);
/*      */     }
/*      */     
/*      */     public void setChestGuiName(String par1Str) {
/*  709 */       this.customName = par1Str;
/*      */     }
/*      */     
/*      */     public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*      */     {
/*  714 */       super.func_145839_a(par1NBTTagCompound);
/*      */       
/*  716 */       NBTTagList nbtItemsList = par1NBTTagCompound.func_150295_c("Items", 10);
/*  717 */       this.chestContents = new ItemStack[func_70302_i_()];
/*      */       
/*  719 */       if (par1NBTTagCompound.func_74764_b("CustomName")) {
/*  720 */         this.customName = par1NBTTagCompound.func_74779_i("CustomName");
/*      */       }
/*      */       
/*  723 */       for (int i = 0; i < nbtItemsList.func_74745_c(); i++) {
/*  724 */         NBTTagCompound nbtItem = nbtItemsList.func_150305_b(i);
/*  725 */         int j = nbtItem.func_74771_c("Slot") & 0xFF;
/*  726 */         if ((j >= 0) && (j < this.chestContents.length)) {
/*  727 */           this.chestContents[j] = ItemStack.func_77949_a(nbtItem);
/*      */         }
/*      */       }
/*      */       
/*  731 */       this.players.clear();
/*  732 */       NBTTagList nbtPlayersList = par1NBTTagCompound.func_150295_c("WITCPlayers", 10);
/*  733 */       for (int i = 0; i < nbtPlayersList.func_74745_c(); i++) {
/*  734 */         NBTTagCompound nbtPlayer = nbtPlayersList.func_150305_b(i);
/*  735 */         String s = nbtPlayer.func_74779_i("Player");
/*  736 */         if ((s != null) && (!s.isEmpty())) {
/*  737 */           this.players.add(s);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     public void func_145841_b(NBTTagCompound nbtTag)
/*      */     {
/*  744 */       super.func_145841_b(nbtTag);
/*  745 */       NBTTagList nbtItemsList = new NBTTagList();
/*      */       
/*  747 */       for (int i = 0; i < this.chestContents.length; i++) {
/*  748 */         if (this.chestContents[i] != null) {
/*  749 */           NBTTagCompound nbtItem = new NBTTagCompound();
/*  750 */           nbtItem.func_74774_a("Slot", (byte)i);
/*  751 */           this.chestContents[i].func_77955_b(nbtItem);
/*  752 */           nbtItemsList.func_74742_a(nbtItem);
/*      */         }
/*      */       }
/*      */       
/*  756 */       nbtTag.func_74782_a("Items", nbtItemsList);
/*      */       
/*  758 */       if (func_145818_k_()) {
/*  759 */         nbtTag.func_74778_a("CustomName", this.customName);
/*      */       }
/*      */       
/*  762 */       NBTTagList nbtPlayers = new NBTTagList();
/*      */       
/*  764 */       for (int i = 0; i < this.players.size(); i++) {
/*  765 */         NBTTagCompound nbtPlayer = new NBTTagCompound();
/*  766 */         nbtPlayer.func_74778_a("Player", (String)this.players.get(i));
/*  767 */         nbtPlayers.func_74742_a(nbtPlayer);
/*      */       }
/*      */       
/*  770 */       nbtTag.func_74782_a("WITCPlayers", nbtPlayers);
/*      */     }
/*      */     
/*      */     public int func_70297_j_()
/*      */     {
/*  775 */       return 64;
/*      */     }
/*      */     
/*      */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*      */     {
/*  780 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*      */     }
/*      */     
/*      */ 
/*      */     public void func_145836_u()
/*      */     {
/*  786 */       super.func_145836_u();
/*  787 */       this.adjacentChestChecked = false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void func_145845_h()
/*      */     {
/*  871 */       super.func_145845_h();
/*      */       
/*  873 */       this.ticksSinceSync += 1;
/*      */       
/*      */ 
/*  876 */       if ((!this.field_145850_b.field_72995_K) && (this.numUsingPlayers != 0) && ((this.ticksSinceSync + this.field_145851_c + this.field_145848_d + this.field_145849_e) % 200 == 0))
/*      */       {
/*  878 */         this.numUsingPlayers = 0;
/*  879 */         float f = 5.0F;
/*  880 */         List list = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_145851_c - f, this.field_145848_d - f, this.field_145849_e - f, this.field_145851_c + 1 + f, this.field_145848_d + 1 + f, this.field_145849_e + 1 + f));
/*      */         
/*      */ 
/*      */ 
/*  884 */         Iterator iterator = list.iterator();
/*      */         
/*  886 */         while (iterator.hasNext()) {
/*  887 */           EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*      */           
/*  889 */           if ((entityplayer.field_71070_bA instanceof ContainerChest)) {
/*  890 */             IInventory iinventory = ((ContainerChest)entityplayer.field_71070_bA).func_85151_d();
/*      */             
/*  892 */             if (iinventory == this) {
/*  893 */               this.numUsingPlayers += 1;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  899 */       this.prevLidAngle = this.lidAngle;
/*  900 */       float f = 0.1F;
/*      */       
/*      */ 
/*  903 */       if ((this.numUsingPlayers > 0) && (this.lidAngle == 0.0F))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  911 */         double d1 = this.field_145851_c + 0.5D;
/*  912 */         double d0 = this.field_145849_e + 0.5D;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  922 */         this.field_145850_b.func_72908_a(d1, this.field_145848_d + 0.5D, d0, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */       }
/*      */       
/*      */ 
/*  926 */       if (((this.numUsingPlayers == 0) && (this.lidAngle > 0.0F)) || ((this.numUsingPlayers > 0) && (this.lidAngle < 1.0F))) {
/*  927 */         float f1 = this.lidAngle;
/*      */         
/*  929 */         if (this.numUsingPlayers > 0) {
/*  930 */           this.lidAngle += f;
/*      */         } else {
/*  932 */           this.lidAngle -= f;
/*      */         }
/*      */         
/*  935 */         if (this.lidAngle > 1.0F) {
/*  936 */           this.lidAngle = 1.0F;
/*      */         }
/*      */         
/*  939 */         float f2 = 0.5F;
/*      */         
/*  941 */         if ((this.lidAngle < f2) && (f1 >= f2))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  947 */           double d0 = this.field_145851_c + 0.5D;
/*  948 */           double d2 = this.field_145849_e + 0.5D;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  958 */           this.field_145850_b.func_72908_a(d0, this.field_145848_d + 0.5D, d2, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */         }
/*      */         
/*      */ 
/*  962 */         if (this.lidAngle < 0.0F) {
/*  963 */           this.lidAngle = 0.0F;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean func_145842_c(int par1, int par2)
/*      */     {
/*  970 */       if (par1 == 1) {
/*  971 */         this.numUsingPlayers = par2;
/*  972 */         return true;
/*      */       }
/*  974 */       return super.func_145842_c(par1, par2);
/*      */     }
/*      */     
/*      */ 
/*      */     public void func_70295_k_()
/*      */     {
/*  980 */       if (this.numUsingPlayers < 0) {
/*  981 */         this.numUsingPlayers = 0;
/*      */       }
/*      */       
/*  984 */       this.numUsingPlayers += 1;
/*  985 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 1, this.numUsingPlayers);
/*  986 */       this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*  987 */       this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, func_145838_q());
/*      */     }
/*      */     
/*      */     public void func_70305_f()
/*      */     {
/*  992 */       if ((func_145838_q() != null) && ((func_145838_q() instanceof BlockLeechChest))) {
/*  993 */         this.numUsingPlayers -= 1;
/*  994 */         this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 1, this.numUsingPlayers);
/*  995 */         this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*  996 */         this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, func_145838_q());
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*      */     {
/* 1002 */       return true;
/*      */     }
/*      */     
/*      */     public void func_145843_s()
/*      */     {
/* 1007 */       super.func_145843_s();
/* 1008 */       func_145836_u();
/*      */     }
/*      */     
/*      */     public int getChestType()
/*      */     {
/* 1013 */       if (this.cachedChestType == -1) {
/* 1014 */         if ((this.field_145850_b == null) || (!(func_145838_q() instanceof BlockLeechChest))) {
/* 1015 */           return 0;
/*      */         }
/*      */         
/* 1018 */         this.cachedChestType = ((BlockLeechChest)func_145838_q()).chestType;
/*      */       }
/*      */       
/* 1021 */       return this.cachedChestType;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockLeechChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */