/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityEnt;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.common.IFuelHandler;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWitchLog
/*     */   extends BlockBaseRotatedPillar
/*     */   implements IFuelHandler
/*     */ {
/*  34 */   public static final String[] WOOD_TYPES = { "rowan", "alder", "hawthorn" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] field_111052_c;
/*     */   
/*  38 */   public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  43 */       return BlockWitchLog.WOOD_TYPES;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] tree_top;
/*     */   
/*     */   public BlockWitchLog()
/*     */   {
/*  54 */     super(Material.field_151575_d, ClassItemBlock.class);
/*  55 */     func_149711_c(2.0F);
/*  56 */     func_149672_a(Block.field_149766_f);
/*     */     
/*  58 */     GameRegistry.registerFuelHandler(this);
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  63 */     super.func_149663_c(blockName);
/*  64 */     Blocks.field_150480_ab.setFireInfo(this, 5, 5);
/*  65 */     return this;
/*     */   }
/*     */   
/*     */   public void func_149690_a(World world, int x, int y, int z, int par5, float par6, int par7)
/*     */   {
/*  70 */     if (!world.field_72995_K) {
/*  71 */       double chance = 0.01D;
/*  72 */       chance += (world.func_147439_a(x, y + 1, z) == this ? 0.01D : 0.0D);
/*  73 */       chance += (world.func_147439_a(x, y - 1, z) == this ? 0.01D : 0.0D);
/*  74 */       chance += (world.func_147439_a(x + 1, y, z) == this ? 0.01D : 0.0D);
/*  75 */       chance += (world.func_147439_a(x - 1, y, z) == this ? 0.01D : 0.0D);
/*  76 */       chance += (world.func_147439_a(x, y, z + 1) == this ? 0.01D : 0.0D);
/*  77 */       chance += (world.func_147439_a(x, y, z - 1) == this ? 0.01D : 0.0D);
/*  78 */       chance = Math.min(chance, 0.05D);
/*     */       
/*  80 */       double roll = world.field_73012_v.nextDouble();
/*  81 */       Log.instance().debug("Ents: Chance: " + chance + ", roll: " + roll);
/*  82 */       if (roll < chance) {
/*  83 */         int MAX_DISTANCE = 16;
/*  84 */         int MIN_DISTANCE = 8;
/*     */         
/*  86 */         int activeRadius = 8;
/*  87 */         int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  88 */         if (ax > activeRadius) {
/*  89 */           ax += 16;
/*     */         }
/*  91 */         int nx = x - 16 + ax;
/*     */         
/*  93 */         int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  94 */         if (az > activeRadius) {
/*  95 */           az += 16;
/*     */         }
/*     */         
/*  98 */         int nz = z - 16 + az;
/*     */         
/* 100 */         int ny = y;
/* 101 */         while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/* 102 */           ny++;
/*     */         }
/*     */         
/*     */ 
/* 106 */         while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/* 107 */           ny--;
/*     */         }
/*     */         
/*     */ 
/* 111 */         int hy = 0;
/* 112 */         while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/* 113 */           hy++;
/*     */         }
/*     */         
/* 116 */         Log.instance().debug("Ents: hy: " + hy + " (" + nx + "," + ny + "," + nz + ")");
/*     */         
/* 118 */         if (hy >= 3) {
/* 119 */           EntityEnt ent = new EntityEnt(world);
/* 120 */           ent.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
/* 121 */           world.func_72838_d(ent);
/*     */           
/* 123 */           ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_HARP, world, x, y, z, 1.0D, 1.0D, 8);
/* 124 */           ParticleEffect.LARGE_SMOKE.send(SoundEffect.MOB_HORSE_SKELETON_DEATH, ent, 2.0D, 4.0D, 16);
/*     */         }
/*     */       }
/*     */     }
/* 128 */     super.func_149690_a(world, x, y, z, par5, par6, par7);
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 133 */     return 1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune)
/*     */   {
/* 138 */     return Item.func_150898_a(this);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block origBlock, int par6)
/*     */   {
/* 143 */     byte b0 = 4;
/* 144 */     int i1 = b0 + 1;
/*     */     
/* 146 */     if (world.func_72904_c(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
/* 147 */       for (int j1 = -b0; j1 <= b0; j1++) {
/* 148 */         for (int k1 = -b0; k1 <= b0; k1++) {
/* 149 */           for (int l1 = -b0; l1 <= b0; l1++) {
/* 150 */             Block block = world.func_147439_a(x + j1, y + k1, z + l1);
/* 151 */             if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
/* 152 */               block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon func_150163_b(int par1)
/*     */   {
/* 163 */     if ((par1 < 0) || (par1 >= WOOD_TYPES.length)) {
/* 164 */       par1 = 0;
/*     */     }
/* 166 */     return this.field_111052_c[par1];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon func_150161_d(int par1)
/*     */   {
/* 172 */     if ((par1 < 0) || (par1 >= WOOD_TYPES.length)) {
/* 173 */       par1 = 0;
/*     */     }
/* 175 */     return this.tree_top[par1];
/*     */   }
/*     */   
/*     */   public static int limitToValidMetadata(int par0) {
/* 179 */     return par0 & WOOD_TYPES.length - 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item block, CreativeTabs creativeTabs, List list)
/*     */   {
/* 185 */     for (int i = 0; i < WOOD_TYPES.length; i++) {
/* 186 */       list.add(new ItemStack(this, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 193 */     this.field_111052_c = new IIcon[WOOD_TYPES.length];
/* 194 */     this.tree_top = new IIcon[WOOD_TYPES.length];
/*     */     
/* 196 */     for (int i = 0; i < this.field_111052_c.length; i++) {
/* 197 */       this.field_111052_c[i] = iconRegister.func_94245_a(func_149641_N() + "_" + WOOD_TYPES[i]);
/* 198 */       this.tree_top[i] = iconRegister.func_94245_a(func_149641_N() + "_" + WOOD_TYPES[i] + "_top");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isWood(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 209 */     return true;
/*     */   }
/*     */   
/*     */   public int getBurnTime(ItemStack fuel)
/*     */   {
/* 214 */     if (Item.func_150898_a(this) == fuel.func_77973_b()) {
/* 215 */       return 300;
/*     */     }
/* 217 */     return 0;
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 222 */     if (world.func_72805_g(x, y, z) == 2) {
/* 223 */       return 1;
/*     */     }
/* 225 */     return super.getFlammability(world, x, y, z, face);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 231 */     if (world.func_72805_g(x, y, z) == 2) {
/* 232 */       return 1;
/*     */     }
/* 234 */     return super.getFireSpreadSpeed(world, x, y, z, face);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */