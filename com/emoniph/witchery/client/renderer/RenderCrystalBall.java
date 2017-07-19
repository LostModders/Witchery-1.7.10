/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCrystalBall.TileEntityCrystalBall;
/*    */ import com.emoniph.witchery.client.model.ModelCrystalBall;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderCrystalBall
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelCrystalBall model;
/*    */   
/*    */   public RenderCrystalBall()
/*    */   {
/* 21 */     this.model = new ModelCrystalBall();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 26 */     GL11.glPushMatrix();
/* 27 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 28 */     BlockCrystalBall.TileEntityCrystalBall ball = (BlockCrystalBall.TileEntityCrystalBall)tileEntity;
/* 29 */     renderCrystalBall(ball, ball.func_145831_w(), ball.field_145851_c, ball.field_145848_d, ball.field_145849_e);
/* 30 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 33 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/crystalball.png");
/*    */   
/*    */   public void renderCrystalBall(BlockCrystalBall.TileEntityCrystalBall ball, World world, int x, int y, int z) {
/* 36 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 38 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 40 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 42 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 44 */     this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, ball);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderCrystalBall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */