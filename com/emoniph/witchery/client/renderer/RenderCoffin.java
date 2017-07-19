/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCoffin;
/*     */ import com.emoniph.witchery.blocks.BlockCoffin.TileEntityCoffin;
/*     */ import com.emoniph.witchery.client.model.ModelCoffin;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCoffin
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   final ModelCoffin model;
/*     */   
/*     */   public RenderCoffin()
/*     */   {
/*  23 */     this.model = new ModelCoffin();
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*     */   {
/*  28 */     GL11.glPushMatrix();
/*  29 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*  30 */     BlockCoffin.TileEntityCoffin tileEntityGoddess = (BlockCoffin.TileEntityCoffin)tileEntity;
/*  31 */     renderGoddess(tileEntityGoddess, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, f);
/*     */     
/*  33 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*  36 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/coffin.png");
/*     */   
/*     */   public void renderGoddess(BlockCoffin.TileEntityCoffin tile, World world, int x, int y, int z, float f)
/*     */   {
/*  40 */     GL11.glPushMatrix();
/*  41 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */     
/*  43 */     func_147499_a(TEXTURE_URL);
/*     */     
/*  45 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */     
/*  47 */     GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*     */     
/*     */ 
/*  50 */     if (world != null) {
/*  51 */       int meta = world.func_72805_g(x, y, z);
/*     */       
/*  53 */       int direction = BlockCoffin.getDirection(meta);
/*     */       
/*  55 */       float rotation = 0.0F;
/*  56 */       switch (direction) {
/*     */       case 0: 
/*  58 */         rotation = 0.0F;
/*  59 */         break;
/*     */       case 1: 
/*  61 */         rotation = 90.0F;
/*  62 */         break;
/*     */       case 2: 
/*  64 */         rotation = 180.0F;
/*  65 */         break;
/*     */       case 3: 
/*  67 */         rotation = 270.0F;
/*     */       }
/*     */       
/*     */       
/*  71 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*     */       
/*  73 */       if (!BlockCoffin.isBlockHeadOfBed(meta)) {
/*  74 */         this.model.sideLeft.field_78798_e = 1.0F;
/*  75 */         this.model.sideRight.field_78798_e = 1.0F;
/*  76 */         this.model.base.field_78798_e = 1.0F;
/*  77 */         this.model.sideEnd.field_78796_g = 3.1415927F;
/*     */         
/*  79 */         this.model.lidTop.field_78798_e = 2.0F;
/*  80 */         this.model.lidMid.field_78798_e = 1.0F;
/*  81 */         this.model.lid.field_78796_g = 0.0F;
/*     */       } else {
/*  83 */         this.model.sideLeft.field_78798_e = 0.0F;
/*  84 */         this.model.sideRight.field_78798_e = 0.0F;
/*  85 */         this.model.sideEnd.field_78798_e = 0.0F;
/*  86 */         this.model.sideEnd.field_78796_g = 0.0F;
/*  87 */         this.model.base.field_78798_e = 0.0F;
/*  88 */         this.model.lidTop.field_78798_e = 0.0F;
/*  89 */         this.model.lidMid.field_78798_e = 0.0F;
/*     */       }
/*     */       
/*  92 */       float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * f;
/*     */       
/*  94 */       f1 = 1.0F - f1;
/*  95 */       f1 = 1.0F - f1 * f1 * f1;
/*  96 */       this.model.lid.field_78808_h = (-(f1 * 3.1415927F / 2.0F));
/*     */     }
/*     */     
/*  99 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*     */     
/* 101 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderCoffin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */