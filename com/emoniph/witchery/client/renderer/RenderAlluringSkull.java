/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockAlluringSkull.TileEntityAlluringSkull;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelSkeletonHead;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderAlluringSkull
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 21 */   private static final ResourceLocation field_110642_c = new ResourceLocation("witchery", "textures/blocks/alluringSkull.png");
/* 22 */   private static final ResourceLocation field_110640_d = new ResourceLocation("witchery", "textures/blocks/alluringSkull2.png");
/*    */   
/* 24 */   private ModelSkeletonHead field_82396_c = new ModelSkeletonHead(0, 0, 64, 32);
/*    */   
/*    */   public void renderTileEntityAlluringSkullAt(BlockAlluringSkull.TileEntityAlluringSkull par1TileEntitySkull, double par2, double par4, double par6, float par8) {
/* 27 */     func_82393_a((float)par2, (float)par4, (float)par6, par1TileEntitySkull.func_145832_p() & 0x7, par1TileEntitySkull.func_82119_b() * 360 / 16.0F, par1TileEntitySkull.getSkullType());
/*    */   }
/*    */   
/*    */   public void func_82393_a(float par1, float par2, float par3, int par4, float par5, int par6)
/*    */   {
/* 32 */     ModelSkeletonHead modelskeletonhead = this.field_82396_c;
/*    */     
/* 34 */     switch (par6) {
/*    */     case 0: 
/*    */     default: 
/* 37 */       func_147499_a(field_110642_c);
/* 38 */       break;
/*    */     case 1: 
/* 40 */       func_147499_a(field_110640_d);
/*    */     }
/*    */     
/*    */     
/* 44 */     GL11.glPushMatrix();
/* 45 */     GL11.glDisable(2884);
/*    */     
/* 47 */     if (par4 != 1) {
/* 48 */       switch (par4) {
/*    */       case 2: 
/* 50 */         GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.74F);
/* 51 */         break;
/*    */       case 3: 
/* 53 */         GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.26F);
/* 54 */         par5 = 180.0F;
/* 55 */         break;
/*    */       case 4: 
/* 57 */         GL11.glTranslatef(par1 + 0.74F, par2 + 0.25F, par3 + 0.5F);
/* 58 */         par5 = 270.0F;
/* 59 */         break;
/*    */       case 5: 
/*    */       default: 
/* 62 */         GL11.glTranslatef(par1 + 0.26F, par2 + 0.25F, par3 + 0.5F);
/* 63 */         par5 = 90.0F;break;
/*    */       }
/*    */     } else {
/* 66 */       GL11.glTranslatef(par1 + 0.5F, par2, par3 + 0.5F);
/*    */     }
/*    */     
/* 69 */     float f4 = 0.0625F;
/* 70 */     GL11.glEnable(32826);
/* 71 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 72 */     GL11.glEnable(3008);
/* 73 */     modelskeletonhead.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, f4);
/* 74 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 78 */     renderTileEntityAlluringSkullAt((BlockAlluringSkull.TileEntityAlluringSkull)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderAlluringSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */