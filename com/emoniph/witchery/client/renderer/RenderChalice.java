/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
/*    */ import com.emoniph.witchery.client.model.ModelChalice;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderChalice
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelChalice model;
/*    */   
/*    */   public RenderChalice()
/*    */   {
/* 21 */     this.model = new ModelChalice();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 26 */     GL11.glPushMatrix();
/* 27 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 28 */     BlockChalice.TileEntityChalice tileEntityChalice = (BlockChalice.TileEntityChalice)tileEntity;
/* 29 */     renderChalice(tileEntityChalice, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/* 30 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 33 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/chalice.png");
/*    */   
/*    */   public void renderChalice(BlockChalice.TileEntityChalice tileEntityChalice, World world, int x, int y, int z) {
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 39 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 41 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 43 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 45 */     this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, tileEntityChalice);
/*    */     
/* 47 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderChalice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */