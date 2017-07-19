/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
/*     */ import com.emoniph.witchery.client.model.ModelFetishScarecrow;
/*     */ import com.emoniph.witchery.client.model.ModelFetishTrent;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderFetish
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  26 */   private static final ResourceLocation TEXTURE_URL_SCARECROW = new ResourceLocation("witchery", "textures/blocks/scarecrow.png");
/*  27 */   private static final ResourceLocation TEXTURE_URL_TRENT = new ResourceLocation("witchery", "textures/blocks/trent.png");
/*     */   private final ModelFetishScarecrow modelScarecrow;
/*     */   private final ModelFetishTrent modelTrent;
/*     */   
/*     */   public RenderFetish()
/*     */   {
/*  33 */     this.modelScarecrow = new ModelFetishScarecrow();
/*  34 */     this.modelTrent = new ModelFetishTrent();
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity te, double d, double d1, double d2, float f)
/*     */   {
/*  39 */     GL11.glPushMatrix();
/*  40 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*  41 */     BlockFetish.TileEntityFetish fetish = (te instanceof BlockFetish.TileEntityFetish) ? (BlockFetish.TileEntityFetish)te : null;
/*     */     
/*  43 */     if ((fetish != null) && (fetish.isSpectral())) {
/*  44 */       GL11.glEnable(2977);
/*  45 */       GL11.glEnable(3042);
/*  46 */       GL11.glBlendFunc(770, 771);
/*     */       
/*  48 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
/*     */     }
/*     */     
/*  51 */     renderModel(fetish, te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
/*  52 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderModel(BlockFetish.TileEntityFetish te, World world, int x, int y, int z) {
/*  56 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */     
/*  58 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  59 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*     */     
/*  61 */     if (world != null) {
/*  62 */       int meta = world.func_72805_g(x, y, z);
/*     */       
/*  64 */       float rotation = 0.0F;
/*  65 */       switch (meta) {
/*     */       case 2: 
/*  67 */         rotation = 0.0F;
/*  68 */         break;
/*     */       case 3: 
/*  70 */         rotation = 180.0F;
/*  71 */         break;
/*     */       case 4: 
/*  73 */         rotation = 270.0F;
/*  74 */         break;
/*     */       case 5: 
/*  76 */         rotation = 90.0F;
/*     */       }
/*     */       
/*     */       
/*  80 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     
/*  83 */     Block block = world != null ? BlockUtil.getBlock(world, x, y, z) : null;
/*  84 */     if (block == null) {
/*  85 */       block = te.getExpectedBlock();
/*     */     }
/*  87 */     if (block == Witchery.Blocks.FETISH_TREANT_IDOL) {
/*  88 */       func_147499_a(TEXTURE_URL_TRENT);
/*  89 */       this.modelTrent.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*  90 */     } else if (block != Witchery.Blocks.FETISH_WITCHS_LADDER)
/*     */     {
/*  92 */       func_147499_a(TEXTURE_URL_SCARECROW);
/*  93 */       this.modelScarecrow.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RenderFetishBlockItem extends RenderBlockItem {
/*     */     private final Block block;
/*     */     private final BlockFetish.TileEntityFetish tileFetish;
/*     */     
/* 101 */     public RenderFetishBlockItem(Block block, TileEntitySpecialRenderer render, BlockFetish.TileEntityFetish dummy) { super(dummy);
/* 102 */       this.block = block;
/* 103 */       this.tileFetish = dummy;
/*     */     }
/*     */     
/*     */     public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */     {
/* 108 */       this.tileFetish.setExpectedBlock(this.block);
/* 109 */       NBTTagCompound nbtRoot = item.func_77978_p();
/* 110 */       if ((nbtRoot != null) && (nbtRoot.func_74764_b("BlockColor"))) {
/* 111 */         this.tileFetish.setColor(nbtRoot.func_74771_c("BlockColor"));
/*     */       }
/* 113 */       super.renderItem(type, item, data);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderFetish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */