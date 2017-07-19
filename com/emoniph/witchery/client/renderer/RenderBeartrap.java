/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockBeartrap.TileEntityBeartrap;
/*    */ import com.emoniph.witchery.client.model.ModelBeartrap;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBeartrap
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelBeartrap model;
/*    */   
/*    */   public RenderBeartrap()
/*    */   {
/* 23 */     this.model = new ModelBeartrap();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tile, double x, double y, double z, float t)
/*    */   {
/* 28 */     GL11.glPushMatrix();
/* 29 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 30 */     BlockBeartrap.TileEntityBeartrap mantrap = (BlockBeartrap.TileEntityBeartrap)tile;
/* 31 */     renderTileEntityAt(mantrap, tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/* 32 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 35 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/beartrap.png");
/*    */   
/*    */   public void renderTileEntityAt(BlockBeartrap.TileEntityBeartrap tile, World world, int x, int y, int z)
/*    */   {
/* 39 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 40 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 41 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 43 */     if (world != null) {
/* 44 */       int meta = world.func_72805_g(x, y, z);
/* 45 */       float rotation = 0.0F;
/* 46 */       switch (meta) {
/*    */       case 2: 
/* 48 */         rotation = 0.0F;
/* 49 */         break;
/*    */       case 3: 
/* 51 */         rotation = 180.0F;
/* 52 */         break;
/*    */       case 4: 
/* 54 */         rotation = 270.0F;
/* 55 */         break;
/*    */       case 5: 
/* 57 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 61 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 64 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 65 */     GL11.glEnable(3042);
/* 66 */     GL11.glBlendFunc(770, 771);
/* 67 */     GL11.glDisable(3008);
/* 68 */     func_147499_a(TEXTURE_URL);
/* 69 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, tile);
/* 70 */     GL11.glEnable(3008);
/* 71 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBeartrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */