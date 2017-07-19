/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
/*    */ import com.emoniph.witchery.client.model.ModelLeechChest;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderLeechChest
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 19 */   private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("witchery", "textures/blocks/leechchest.png");
/*    */   
/* 21 */   private ModelLeechChest model = new ModelLeechChest();
/*    */   
/*    */   public void renderLeechChest(BlockLeechChest.TileEntityLeechChest chestEntity, double par2, double par4, double par6, float par8) {
/* 24 */     int i = 0;
/*    */     
/* 26 */     if (chestEntity.func_145830_o()) {
/* 27 */       i = chestEntity.func_145832_p();
/*    */     }
/*    */     
/* 30 */     func_147499_a(RESOURCE_LOCATION);
/* 31 */     GL11.glPushMatrix();
/* 32 */     GL11.glEnable(32826);
/* 33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 34 */     GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
/* 35 */     GL11.glScalef(1.0F, -1.0F, -1.0F);
/* 36 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 37 */     short short1 = 0;
/*    */     
/* 39 */     if (i == 2) {
/* 40 */       short1 = 180;
/*    */     }
/*    */     
/* 43 */     if (i == 3) {
/* 44 */       short1 = 0;
/*    */     }
/*    */     
/* 47 */     if (i == 4) {
/* 48 */       short1 = 90;
/*    */     }
/*    */     
/* 51 */     if (i == 5) {
/* 52 */       short1 = -90;
/*    */     }
/*    */     
/* 55 */     GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
/* 56 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 57 */     float f1 = chestEntity.prevLidAngle + (chestEntity.lidAngle - chestEntity.prevLidAngle) * par8;
/* 58 */     f1 = 1.0F - f1;
/* 59 */     f1 = 1.0F - f1 * f1 * f1;
/* 60 */     this.model.chestLidBL.field_78795_f = (-(f1 * 3.1415927F / 2.0F) / 1.5F);
/* 61 */     this.model.chestLidBL.field_78796_g = (f1 * 3.1415927F / 2.0F / 3.0F);
/* 62 */     this.model.chestLidBL.field_78808_h = (f1 * 3.1415927F / 2.0F / 1.5F);
/*    */     
/* 64 */     this.model.chestLidBR.field_78795_f = (-(f1 * 3.1415927F / 2.0F) / 1.5F);
/* 65 */     this.model.chestLidBR.field_78796_g = (-(f1 * 3.1415927F / 2.0F) / 3.0F);
/* 66 */     this.model.chestLidBR.field_78808_h = (-(f1 * 3.1415927F / 2.0F) / 1.5F);
/*    */     
/* 68 */     this.model.chestLidFL.field_78795_f = (f1 * 3.1415927F / 2.0F / 1.5F);
/* 69 */     this.model.chestLidFL.field_78796_g = (-(f1 * 3.1415927F / 2.0F) / 3.0F);
/* 70 */     this.model.chestLidFL.field_78808_h = (f1 * 3.1415927F / 2.0F / 1.5F);
/*    */     
/* 72 */     this.model.chestLidFR.field_78795_f = (f1 * 3.1415927F / 2.0F / 1.5F);
/* 73 */     this.model.chestLidFR.field_78796_g = (f1 * 3.1415927F / 2.0F / 3.0F);
/* 74 */     this.model.chestLidFR.field_78808_h = (-(f1 * 3.1415927F / 2.0F) / 1.5F);
/* 75 */     this.model.renderAll(chestEntity.players.size());
/* 76 */     GL11.glDisable(32826);
/* 77 */     GL11.glPopMatrix();
/* 78 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 83 */     renderLeechChest((BlockLeechChest.TileEntityLeechChest)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderLeechChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */