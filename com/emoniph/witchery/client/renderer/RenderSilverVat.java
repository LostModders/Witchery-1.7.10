/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockSilverVat.TileEntitySilverVat;
/*     */ import com.emoniph.witchery.client.model.ModelSilverVat;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderSilverVat extends TileEntitySpecialRenderer
/*     */ {
/*     */   final ModelSilverVat model;
/*     */   
/*     */   public RenderSilverVat()
/*     */   {
/*  26 */     this.model = new ModelSilverVat();
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*     */   {
/*  31 */     GL11.glPushMatrix();
/*     */     
/*  33 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*  34 */     BlockSilverVat.TileEntitySilverVat tileEntityYour = (BlockSilverVat.TileEntitySilverVat)tileEntity;
/*     */     
/*  36 */     renderSilverVat(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*     */     
/*  38 */     func_147499_a(TextureMap.field_110575_b);
/*     */     
/*  40 */     GL11.glPushMatrix();
/*  41 */     GL11.glEnable(3042);
/*  42 */     GL11.glBlendFunc(770, 771);
/*  43 */     GL11.glDisable(3008);
/*     */     
/*  45 */     int color = 3432410;
/*     */     
/*  47 */     float red = (color >>> 16 & 0xFF) / 256.0F;
/*  48 */     float green = (color >>> 8 & 0xFF) / 256.0F;
/*  49 */     float blue = (color & 0xFF) / 256.0F;
/*  50 */     GL11.glColor4f(red, green, blue, 1.0F);
/*     */     
/*     */ 
/*  53 */     float w = -0.375F;
/*  54 */     float depth = 1.1999999F;
/*  55 */     GL11.glTranslatef(w, depth, -w);
/*  56 */     GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/*  58 */     float s = 0.046875F;
/*  59 */     GL11.glScalef(0.046875F, 0.046875F, 0.046875F);
/*     */     
/*  61 */     IIcon icon = Witchery.Blocks.BREW.func_149691_a(0, 0);
/*  62 */     int x = 0;int y = 0;
/*  63 */     int u = 16;int v = 16;
/*     */     
/*  65 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  66 */     tessellator.func_78382_b();
/*  67 */     tessellator.func_78380_c(200);
/*  68 */     tessellator.func_78374_a(0.0D, 16.0D, 0.0D, icon.func_94209_e(), icon.func_94210_h());
/*  69 */     tessellator.func_78374_a(16.0D, 16.0D, 0.0D, icon.func_94212_f(), icon.func_94210_h());
/*  70 */     tessellator.func_78374_a(16.0D, 0.0D, 0.0D, icon.func_94212_f(), icon.func_94206_g());
/*  71 */     tessellator.func_78374_a(0.0D, 0.0D, 0.0D, icon.func_94209_e(), icon.func_94206_g());
/*  72 */     tessellator.func_78381_a();
/*     */     
/*  74 */     GL11.glEnable(3008);
/*  75 */     GL11.glDisable(3042);
/*  76 */     GL11.glPopMatrix();
/*     */     
/*  78 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*  81 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/silvervat.png");
/*     */   
/*     */   public void renderSilverVat(BlockSilverVat.TileEntitySilverVat te, World world, int x, int y, int z)
/*     */   {
/*  85 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */     
/*  87 */     func_147499_a(TEXTURE_URL);
/*  88 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */     
/*     */ 
/*  91 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 115 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderSilverVat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */