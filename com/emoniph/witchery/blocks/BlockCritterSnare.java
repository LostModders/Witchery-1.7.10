/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.item.ItemPolynesiaCharm;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMagmaCube;
/*     */ import net.minecraft.entity.monster.EntitySilverfish;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockCritterSnare
/*     */   extends BlockBaseBush
/*     */ {
/*  30 */   private static final String[] CAUGHT_TYPES = { "empty", "bat", "silverfish", "slime", "magmacube" };
/*  31 */   private static final String[] CAUGHT_TYPES_SOUNDS = { "", "mob.bat.idle", "mob.silverfish.say", "mob.slime.small", "mob.magmacube.small" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] critterIcons;
/*     */   
/*     */   public static class ClassItemBlock extends MultiItemBlock {
/*  36 */     public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  41 */       return BlockCritterSnare.CAUGHT_TYPES;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public IIcon func_77617_a(int par1)
/*     */     {
/*  47 */       return this.field_150939_a.func_149691_a(0, par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockCritterSnare() {
/*  52 */     super(Material.field_151585_k, ClassItemBlock.class);
/*     */     
/*  54 */     func_149672_a(field_149779_h);
/*     */     
/*  56 */     float f = 0.45F;
/*  57 */     func_149676_a(0.050000012F, 0.0F, 0.050000012F, 0.95F, 1.0F, 0.95F);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/*  62 */     int meta = world.func_72805_g(posX, posY, posZ);
/*     */     
/*  64 */     if ((meta == 0) && (!world.field_72995_K) && (entity != null) && (entity.func_70089_S())) {
/*  65 */       if ((entity instanceof EntityBat)) {
/*  66 */         boolean hasStock = ItemPolynesiaCharm.hasStockInventory((EntityBat)entity);
/*  67 */         world.func_72921_c(posX, posY, posZ, hasStock ? 9 : 1, 3);
/*  68 */         entity.func_70106_y();
/*  69 */       } else if ((entity instanceof EntitySilverfish)) {
/*  70 */         world.func_72921_c(posX, posY, posZ, 2, 3);
/*  71 */         entity.func_70106_y();
/*  72 */       } else if (((entity instanceof EntityMagmaCube)) && (((EntityMagmaCube)entity).func_70809_q() == 1)) {
/*  73 */         world.func_72921_c(posX, posY, posZ, 4, 3);
/*  74 */         entity.func_70106_y();
/*  75 */       } else if (((entity instanceof EntitySlime)) && (((EntitySlime)entity).func_70809_q() == 1)) {
/*  76 */         world.func_72921_c(posX, posY, posZ, 3, 3);
/*  77 */         entity.func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
/*     */   {
/*  85 */     int meta = world.func_72805_g(x, y, z);
/*  86 */     if ((!world.field_72995_K) && (meta > 0) && (player.func_70093_af())) {
/*  87 */       world.func_72921_c(x, y, z, 0, 3);
/*  88 */       int tries = 0;
/*  89 */       switch (getCritterFromMeta(meta)) {
/*     */       case 1: 
/*  91 */         EntityBat bat = new EntityBat(world);
/*  92 */         bat.func_70012_b(0.5D + x, 1.5D + y, 0.5D + z, 0.0F, 0.0F);
/*  93 */         if ((meta & 0x8) == 8) {
/*  94 */           ItemPolynesiaCharm.setEmptyStockInventory(world, bat);
/*     */         }
/*  96 */         world.func_72838_d(bat);
/*  97 */         break;
/*     */       case 2: 
/*  99 */         EntitySilverfish silverfish = new EntitySilverfish(world);
/* 100 */         silverfish.func_70012_b(player.field_70165_t < x ? x - 0.5D : x + 1.5D, player.field_70163_u + 0.5D, player.field_70161_v < z ? z - 0.5D : z + 1.5D, 0.0F, 0.0F);
/*     */         
/* 102 */         world.func_72838_d(silverfish);
/* 103 */         break;
/*     */       case 3: 
/* 105 */         EntitySlime slime = null;
/* 106 */         tries = 20;
/*     */         do {
/* 108 */           slime = new EntitySlime(world);
/* 109 */           if (slime.func_70809_q() == 1) break; tries--; } while (tries > 0);
/*     */         
/* 111 */         if (tries > 0) {
/* 112 */           slime.func_70012_b(player.field_70165_t < x ? x - 0.5D : x + 1.5D, player.field_70163_u + 0.5D, player.field_70161_v < z ? z - 0.5D : z + 1.5D, 0.0F, 0.0F);
/*     */           
/* 114 */           world.func_72838_d(slime);
/*     */         } else {
/* 116 */           world.func_72838_d(new EntityItem(world, 0.5D + x, 1.5D + y, 0.5D + z, new ItemStack(Items.field_151123_aH)));
/*     */         }
/* 118 */         break;
/*     */       case 4: 
/* 120 */         EntityMagmaCube cube = null;
/* 121 */         tries = 20;
/*     */         do {
/* 123 */           cube = new EntityMagmaCube(world);
/* 124 */           if (cube.func_70809_q() == 1) break; tries--; } while (tries > 0);
/*     */         
/* 126 */         if (tries > 0) {
/* 127 */           cube.func_70012_b(player.field_70165_t < x ? x - 0.5D : x + 1.5D, player.field_70163_u + 0.5D, player.field_70161_v < z ? z - 0.5D : z + 1.5D, 0.0F, 0.0F);
/*     */           
/* 129 */           world.func_72838_d(cube);
/*     */         } else {
/* 131 */           world.func_72838_d(new EntityItem(world, 0.5D + x, 1.5D + y, 0.5D + z, new ItemStack(Items.field_151064_bs)));
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/* 136 */     return true;
/*     */   }
/*     */   
/*     */   private int getCritterFromMeta(int meta) {
/* 140 */     int critter = meta & 0x7;
/* 141 */     if ((critter < 0) || (critter >= CAUGHT_TYPES.length)) {
/* 142 */       critter = 0;
/*     */     }
/* 144 */     return critter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/* 153 */     int critterType = getCritterFromMeta(meta);
/* 154 */     return this.critterIcons[critterType];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 160 */     this.critterIcons = new IIcon[CAUGHT_TYPES.length];
/* 161 */     for (int i = 0; i < CAUGHT_TYPES.length; i++) {
/* 162 */       this.critterIcons[i] = iconRegister.func_94245_a(func_149641_N() + "_" + CAUGHT_TYPES[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 169 */     if (rand.nextInt(24) == 0) {
/* 170 */       int meta = world.func_72805_g(x, y, z);
/* 171 */       int critterType = getCritterFromMeta(meta);
/* 172 */       if ((critterType > 0) && (critterType < CAUGHT_TYPES_SOUNDS.length)) {
/* 173 */         String sound = CAUGHT_TYPES_SOUNDS[critterType];
/* 174 */         world.func_72980_b(x, y, z, sound, 0.5F, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F), false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random rand, int fortune)
/*     */   {
/* 181 */     return Item.func_150898_a(this);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 186 */     return metadata;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
/*     */   {
/* 192 */     list.add(new ItemStack(item, 1, 0));
/*     */   }
/*     */   
/*     */   protected ItemStack func_149644_j(int par1)
/*     */   {
/* 197 */     return new ItemStack(this, 1, par1);
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 202 */     return (super.func_149742_c(par1World, par2, par3, par4)) && (func_149718_j(par1World, par2, par3, par4));
/*     */   }
/*     */   
/*     */   protected boolean func_149854_a(Block block)
/*     */   {
/* 207 */     return (block != null) && (block.func_149662_c());
/*     */   }
/*     */   
/*     */   public boolean func_149718_j(World world, int posX, int posY, int posZ)
/*     */   {
/* 212 */     Material material = world.func_147439_a(posX, posY - 1, posZ).func_149688_o();
/* 213 */     return (material != null) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   public void func_149636_a(World par3World, EntityPlayer player, int par4, int par5, int par6, int damageValue)
/*     */   {
/* 218 */     super.func_149636_a(par3World, player, par4, par5, par6, damageValue);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCritterSnare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */