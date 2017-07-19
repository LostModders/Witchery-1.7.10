/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockAlluringSkull.TileEntityAlluringSkull;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemAlluringSkull extends ItemBlock
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] field_94586_c;
/*     */   
/*     */   public ItemAlluringSkull(Block par1)
/*     */   {
/*  23 */     super(par1);
/*  24 */     func_77656_e(0);
/*  25 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
/*     */   {
/*  31 */     if (par7 == 0)
/*  32 */       return false;
/*  33 */     if (!par3World.func_147439_a(par4, par5, par6).func_149688_o().func_76220_a()) {
/*  34 */       return false;
/*     */     }
/*  36 */     if (par7 == 1) {
/*  37 */       par5++;
/*     */     }
/*     */     
/*  40 */     if (par7 == 2) {
/*  41 */       par6--;
/*     */     }
/*     */     
/*  44 */     if (par7 == 3) {
/*  45 */       par6++;
/*     */     }
/*     */     
/*  48 */     if (par7 == 4) {
/*  49 */       par4--;
/*     */     }
/*     */     
/*  52 */     if (par7 == 5) {
/*  53 */       par4++;
/*     */     }
/*     */     
/*  56 */     if (!par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack))
/*  57 */       return false;
/*  58 */     if (!Witchery.Blocks.ALLURING_SKULL.func_149742_c(par3World, par4, par5, par6)) {
/*  59 */       return false;
/*     */     }
/*  61 */     if (!par3World.field_72995_K) {
/*  62 */       par3World.func_147465_d(par4, par5, par6, Witchery.Blocks.ALLURING_SKULL, par7, 2);
/*  63 */       int i1 = 0;
/*     */       
/*  65 */       if (par7 == 1) {
/*  66 */         i1 = net.minecraft.util.MathHelper.func_76128_c(par2EntityPlayer.field_70177_z * 16.0F / 360.0F + 0.5D) & 0xF;
/*     */       }
/*     */       
/*  69 */       net.minecraft.tileentity.TileEntity tileentity = par3World.func_147438_o(par4, par5, par6);
/*     */       
/*  71 */       if ((tileentity != null) && ((tileentity instanceof BlockAlluringSkull.TileEntityAlluringSkull))) {
/*  72 */         ((BlockAlluringSkull.TileEntityAlluringSkull)tileentity).setSkullType(par1ItemStack.func_77960_j());
/*  73 */         ((BlockAlluringSkull.TileEntityAlluringSkull)tileentity).setSkullRotation(i1);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  78 */     par1ItemStack.field_77994_a -= 1;
/*  79 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  87 */   public static final String[] field_94587_a = { "witchery:alluringSkull_off", "witchery:alluringSkull_on" };
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  92 */     this.field_94586_c = new IIcon[field_94587_a.length];
/*     */     
/*  94 */     for (int i = 0; i < field_94587_a.length; i++) {
/*  95 */       this.field_94586_c[i] = par1IconRegister.func_94245_a(field_94587_a[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/* 102 */     if ((par1 < 0) || (par1 >= field_94587_a.length)) {
/* 103 */       par1 = 0;
/*     */     }
/*     */     
/* 106 */     return this.field_94586_c[par1];
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/* 111 */     return super.func_77658_a();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemAlluringSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */