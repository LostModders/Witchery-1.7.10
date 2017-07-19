/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFence;
/*     */ import net.minecraft.block.BlockPressurePlate.Sensitivity;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPressurePlateBase
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   private BlockPressurePlate.Sensitivity sensitivity;
/*     */   private String textureName;
/*     */   private Block original;
/*     */   
/*     */   public BlockPressurePlateBase(Block original, String textureName, BlockPressurePlate.Sensitivity sensitivity)
/*     */   {
/*  40 */     super(original.func_149688_o(), TileEntityCursedBlock.class);
/*  41 */     this.textureName = textureName;
/*  42 */     this.sensitivity = sensitivity;
/*  43 */     this.original = original;
/*  44 */     func_149711_c(0.5F);
/*  45 */     func_149672_a(original.field_149762_H);
/*  46 */     func_149675_a(true);
/*  47 */     func_150063_b(func_150066_d(15));
/*  48 */     this.registerWithCreateTab = false;
/*     */   }
/*     */   
/*     */   public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, NBTTagCompound nbtBrew)
/*     */   {
/*  53 */     int meta = world.func_72805_g(x, y, z);
/*  54 */     world.func_147465_d(x, y, z, this, meta, 3);
/*  55 */     world.func_147464_a(x, y, z, this, func_149738_a(world));
/*  56 */     TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/*  57 */     if (tile != null) {
/*  58 */       tile.initalise(impactModifiers, nbtBrew);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  64 */     func_150063_b(world.func_72805_g(x, y, z));
/*     */   }
/*     */   
/*     */   protected void func_150063_b(int p_150063_1_) {
/*  68 */     boolean flag = func_150060_c(p_150063_1_) > 0;
/*  69 */     float f = 0.0625F;
/*     */     
/*  71 */     if (flag) {
/*  72 */       func_149676_a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
/*     */     } else {
/*  74 */       func_149676_a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149738_a(World world)
/*     */   {
/*  80 */     return 20;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z)
/*     */   {
/* 105 */     return (World.func_147466_a(world, x, y - 1, z)) || (BlockFence.func_149825_a(world.func_147439_a(x, y - 1, z)));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World world, int x, int y, int z, Block p_149695_5_)
/*     */   {
/* 111 */     boolean flag = false;
/*     */     
/* 113 */     if ((!World.func_147466_a(world, x, y - 1, z)) && (!BlockFence.func_149825_a(world.func_147439_a(x, y - 1, z))))
/*     */     {
/* 115 */       flag = true;
/*     */     }
/*     */     
/* 118 */     if (flag) {
/* 119 */       func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 120 */       world.func_147468_f(x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 126 */     if (!world.field_72995_K) {
/* 127 */       int l = func_150060_c(world.func_72805_g(x, y, z));
/*     */       
/* 129 */       if (l > 0) {
/* 130 */         func_150062_a(world, x, y, z, l);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 137 */     if (!world.field_72995_K) {
/* 138 */       int metadata = world.func_72805_g(x, y, z);
/* 139 */       int l = func_150060_c(metadata);
/*     */       
/* 141 */       if (l == 0)
/*     */       {
/* 143 */         int i1 = func_150065_e(world, x, y, z);
/* 144 */         boolean flag = l > 0;
/* 145 */         boolean flag1 = i1 > 0;
/*     */         
/* 147 */         if (l != i1) {
/* 148 */           int md = func_150066_d(i1);
/*     */           
/*     */ 
/* 151 */           if (!world.field_72995_K) {
/* 152 */             TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/*     */             
/* 154 */             if ((tile != null) && 
/* 155 */               (!tile.applyToEntityAndDestroy(entity))) {
/* 156 */               world.func_147449_b(x, y, z, this.original);
/* 157 */               world.func_72921_c(x, y, z, md, 2);
/* 158 */               func_150064_a_(world, x, y, z);
/* 159 */               world.func_147458_c(x, y, z, x, y, z);
/* 160 */               if ((!flag1) && (flag)) {
/* 161 */                 world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
/* 162 */               } else if ((flag1) && (!flag)) {
/* 163 */                 world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
/*     */               }
/*     */               
/* 166 */               if (flag1) {
/* 167 */                 world.func_147464_a(x, y, z, this.original, func_149738_a(world));
/*     */               }
/* 169 */               return;
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 174 */           world.func_72921_c(x, y, z, md, 2);
/* 175 */           func_150064_a_(world, x, y, z);
/* 176 */           world.func_147458_c(x, y, z, x, y, z);
/*     */         }
/*     */         
/* 179 */         if ((!flag1) && (flag)) {
/* 180 */           world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
/* 181 */         } else if ((flag1) && (!flag)) {
/* 182 */           world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
/*     */         }
/*     */         
/* 185 */         if (flag1) {
/* 186 */           world.func_147464_a(x, y, z, this, func_149738_a(world));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_150062_a(World world, int x, int y, int z, int p_150062_5_) {
/* 193 */     int i1 = func_150065_e(world, x, y, z);
/* 194 */     boolean flag = p_150062_5_ > 0;
/* 195 */     boolean flag1 = i1 > 0;
/*     */     
/* 197 */     if (p_150062_5_ != i1) {
/* 198 */       int metadata = func_150066_d(i1);
/* 199 */       world.func_72921_c(x, y, z, metadata, 2);
/* 200 */       func_150064_a_(world, x, y, z);
/* 201 */       world.func_147458_c(x, y, z, x, y, z);
/*     */     }
/*     */     
/* 204 */     if ((!flag1) && (flag)) {
/* 205 */       world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
/* 206 */     } else if ((flag1) && (!flag)) {
/* 207 */       world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
/*     */     }
/*     */     
/* 210 */     if (flag1) {
/* 211 */       world.func_147464_a(x, y, z, this, func_149738_a(world));
/*     */     }
/*     */   }
/*     */   
/*     */   protected AxisAlignedBB func_150061_a(int x, int y, int z) {
/* 216 */     float f = 0.125F;
/* 217 */     return AxisAlignedBB.func_72330_a(x + f, y, z + f, x + 1 - f, y + 0.25D, z + 1 - f);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int p_149749_6_)
/*     */   {
/* 222 */     if (func_150060_c(p_149749_6_) > 0) {
/* 223 */       func_150064_a_(world, x, y, z);
/*     */     }
/*     */     
/* 226 */     super.func_149749_a(world, x, y, z, block, p_149749_6_);
/*     */   }
/*     */   
/*     */   protected void func_150064_a_(World world, int x, int y, int z) {
/* 230 */     world.func_147459_d(x, y, z, this);
/* 231 */     world.func_147459_d(x, y - 1, z, this);
/*     */   }
/*     */   
/*     */   public int func_149709_b(IBlockAccess world, int x, int y, int z, int p_149709_5_)
/*     */   {
/* 236 */     return func_150060_c(world.func_72805_g(x, y, z));
/*     */   }
/*     */   
/*     */   public int func_149748_c(IBlockAccess world, int x, int y, int z, int p_149748_5_)
/*     */   {
/* 241 */     return p_149748_5_ == 1 ? func_150060_c(world.func_72805_g(x, y, z)) : 0;
/*     */   }
/*     */   
/*     */   public boolean func_149744_f()
/*     */   {
/* 246 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149683_g()
/*     */   {
/* 251 */     float f = 0.5F;
/* 252 */     float f1 = 0.125F;
/* 253 */     float f2 = 0.5F;
/* 254 */     func_149676_a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
/*     */   }
/*     */   
/*     */   public int func_149656_h()
/*     */   {
/* 259 */     return 1;
/*     */   }
/*     */   
/*     */   protected int func_150066_d(int p_150066_1_) {
/* 263 */     return p_150066_1_ > 0 ? 1 : 0;
/*     */   }
/*     */   
/*     */   protected int func_150060_c(int p_150060_1_) {
/* 267 */     return p_150060_1_ == 1 ? 15 : 0;
/*     */   }
/*     */   
/*     */   protected int func_150065_e(World world, int x, int y, int z) {
/* 271 */     List list = null;
/*     */     
/* 273 */     if (this.sensitivity == BlockPressurePlate.Sensitivity.everything) {
/* 274 */       list = world.func_72839_b((Entity)null, func_150061_a(x, y, z));
/*     */     }
/*     */     
/* 277 */     if (this.sensitivity == BlockPressurePlate.Sensitivity.mobs) {
/* 278 */       list = world.func_72872_a(EntityLivingBase.class, func_150061_a(x, y, z));
/*     */     }
/*     */     
/* 281 */     if (this.sensitivity == BlockPressurePlate.Sensitivity.players) {
/* 282 */       list = world.func_72872_a(EntityPlayer.class, func_150061_a(x, y, z));
/*     */     }
/*     */     
/* 285 */     if ((list != null) && (!list.isEmpty())) {
/* 286 */       Iterator iterator = list.iterator();
/*     */       
/* 288 */       while (iterator.hasNext()) {
/* 289 */         Entity entity = (Entity)iterator.next();
/*     */         
/* 291 */         if (!entity.func_145773_az()) {
/* 292 */           return 15;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 297 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 303 */     this.field_149761_L = iconRegister.func_94245_a(this.textureName);
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 308 */     return new ItemStack(this.original);
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/* 313 */     return Item.func_150898_a(this.original);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockPressurePlateBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */