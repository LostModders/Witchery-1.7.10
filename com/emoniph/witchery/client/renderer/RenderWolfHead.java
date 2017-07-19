/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockWolfHead.TileEntityWolfHead;
/*    */ import com.emoniph.witchery.client.model.ModelWolfHead;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderWolfHead
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 28 */   private static final ResourceLocation WOLF_TEXTURE = new ResourceLocation("textures/entity/wolf/wolf.png");
/*    */   
/* 30 */   private static final ResourceLocation HELLHOUND_TEXTURE = new ResourceLocation("witchery", "textures/entities/hellhound.png");
/*    */   
/*    */   public static RenderWolfHead field_147536_b;
/*    */   
/* 34 */   private ModelWolfHead field_147533_g = new ModelWolfHead(0, 0, 64, 32);
/* 35 */   private ModelWolfHead field_147538_h = new ModelWolfHead(0, 0, 64, 64);
/*    */   
/*    */   public void renderTileEntityAt(BlockWolfHead.TileEntityWolfHead tile, double x, double y, double z, float partialTicks) {
/* 38 */     render((float)x, (float)y, (float)z, tile.func_145832_p() & 0x7, tile.getRotation() * 360 / 16.0F, tile.getSkullType());
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147497_a(TileEntityRendererDispatcher p_147497_1_)
/*    */   {
/* 44 */     super.func_147497_a(p_147497_1_);
/* 45 */     field_147536_b = this;
/*    */   }
/*    */   
/*    */   public void render(float x, float y, float z, int metadata, float rotation, int skullType) {
/* 49 */     ModelWolfHead modelskeletonhead = this.field_147533_g;
/*    */     
/* 51 */     switch (skullType) {
/*    */     case 0: 
/*    */     default: 
/* 54 */       func_147499_a(WOLF_TEXTURE);
/* 55 */       break;
/*    */     case 1: 
/* 57 */       func_147499_a(HELLHOUND_TEXTURE);
/*    */     }
/*    */     
/*    */     
/* 61 */     GL11.glPushMatrix();
/* 62 */     GL11.glDisable(2884);
/*    */     
/* 64 */     if (metadata != 1) {
/* 65 */       switch (metadata) {
/*    */       case 2: 
/* 67 */         GL11.glTranslatef(x + 0.5F, y + 0.25F, z + 0.74F);
/* 68 */         break;
/*    */       case 3: 
/* 70 */         GL11.glTranslatef(x + 0.5F, y + 0.25F, z + 0.26F);
/* 71 */         rotation = 180.0F;
/* 72 */         break;
/*    */       case 4: 
/* 74 */         GL11.glTranslatef(x + 0.74F, y + 0.25F, z + 0.5F);
/* 75 */         rotation = 270.0F;
/* 76 */         break;
/*    */       case 5: 
/*    */       default: 
/* 79 */         GL11.glTranslatef(x + 0.26F, y + 0.25F, z + 0.5F);
/* 80 */         rotation = 90.0F;break;
/*    */       }
/*    */     } else {
/* 83 */       GL11.glTranslatef(x + 0.5F, y, z + 0.5F);
/*    */     }
/*    */     
/* 86 */     float f4 = 0.0625F;
/* 87 */     GL11.glEnable(32826);
/* 88 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 89 */     GL11.glEnable(3008);
/* 90 */     modelskeletonhead.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, f4);
/* 91 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks)
/*    */   {
/* 96 */     renderTileEntityAt((BlockWolfHead.TileEntityWolfHead)tile, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWolfHead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */