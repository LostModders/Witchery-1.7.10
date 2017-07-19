/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaMarker;
/*    */ import com.emoniph.witchery.client.model.ModelStatueWolf;
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
/*    */ public class RenderStatueWolf
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelStatueWolf model;
/*    */   
/*    */   public RenderStatueWolf()
/*    */   {
/* 22 */     this.model = new ModelStatueWolf();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 27 */     GL11.glPushMatrix();
/* 28 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 29 */     BlockAreaMarker.TileEntityAreaMarker tileEntityGoddess = (BlockAreaMarker.TileEntityAreaMarker)tileEntity;
/* 30 */     renderGoddess(tileEntityGoddess, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/* 31 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 34 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/statuewolf.png");
/*    */   
/*    */   public void renderGoddess(BlockAreaMarker.TileEntityAreaMarker tileEntity, World world, int x, int y, int z) {
/* 37 */     GL11.glPushMatrix();
/* 38 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 40 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 42 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 44 */     GL11.glTranslatef(0.0F, -0.26F, 0.0F);
/* 45 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*    */     
/*    */ 
/* 48 */     if (world != null) {
/* 49 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 51 */       float rotation = 0.0F;
/* 52 */       switch (meta) {
/*    */       case 2: 
/* 54 */         rotation = 0.0F;
/* 55 */         break;
/*    */       case 3: 
/* 57 */         rotation = 180.0F;
/* 58 */         break;
/*    */       case 4: 
/* 60 */         rotation = 270.0F;
/* 61 */         break;
/*    */       case 5: 
/* 63 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 67 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 69 */       GL11.glScalef(0.6F, 0.6F, 0.6F);
/* 70 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 71 */       GL11.glTranslatef(0.0F, 1.5F, 0.0F);
/*    */     }
/*    */     
/* 74 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 76 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderStatueWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */