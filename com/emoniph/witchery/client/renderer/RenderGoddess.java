/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockStatueGoddess.TileEntityStatueGoddess;
/*    */ import com.emoniph.witchery.client.model.ModelGoddess;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderGoddess
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelGoddess model;
/*    */   
/*    */   public RenderGoddess()
/*    */   {
/* 22 */     this.model = new ModelGoddess();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 27 */     GL11.glPushMatrix();
/* 28 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 29 */     BlockStatueGoddess.TileEntityStatueGoddess tileEntityGoddess = (BlockStatueGoddess.TileEntityStatueGoddess)tileEntity;
/* 30 */     renderGoddess(tileEntityGoddess, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/* 31 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 34 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/statuegoddess.png");
/*    */   
/*    */   public void renderGoddess(BlockStatueGoddess.TileEntityStatueGoddess tileEntity, World world, int x, int y, int z) {
/* 37 */     GL11.glPushMatrix();
/* 38 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 40 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 42 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 44 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 46 */     if (world != null) {
/* 47 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 49 */       float rotation = 0.0F;
/* 50 */       switch (meta) {
/*    */       case 2: 
/* 52 */         rotation = 0.0F;
/* 53 */         break;
/*    */       case 3: 
/* 55 */         rotation = 180.0F;
/* 56 */         break;
/*    */       case 4: 
/* 58 */         rotation = 270.0F;
/* 59 */         break;
/*    */       case 5: 
/* 61 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 65 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 67 */       GL11.glScalef(0.6F, 0.6F, 0.6F);
/* 68 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 69 */       GL11.glTranslatef(0.0F, 1.5F, 0.0F);
/*    */     }
/*    */     
/* 72 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 74 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGoddess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */