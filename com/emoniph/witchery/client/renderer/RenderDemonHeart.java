/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockDemonHeart.TileEntityDemonHeart;
/*    */ import com.emoniph.witchery.client.model.ModelDemonHeart;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderDemonHeart
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelDemonHeart model;
/*    */   
/*    */   public RenderDemonHeart()
/*    */   {
/* 23 */     this.model = new ModelDemonHeart();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 28 */     GL11.glPushMatrix();
/* 29 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 30 */     BlockDemonHeart.TileEntityDemonHeart tileEntityDemonHeart = (BlockDemonHeart.TileEntityDemonHeart)tileEntity;
/* 31 */     renderDemonHeart(tileEntityDemonHeart, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/* 32 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 35 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/demonHeart.png");
/*    */   
/*    */   public void renderDemonHeart(BlockDemonHeart.TileEntityDemonHeart tile, World world, int x, int y, int z) {
/* 38 */     GL11.glPushMatrix();
/* 39 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 41 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 43 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/* 46 */     double size = 0.7D;
/* 47 */     GL11.glScaled(size, size, size);
/*    */     
/* 49 */     if (world != null) {
/* 50 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 52 */       float rotation = 0.0F;
/* 53 */       switch (meta) {
/*    */       case 2: 
/* 55 */         rotation = 0.0F;
/* 56 */         break;
/*    */       case 3: 
/* 58 */         rotation = 180.0F;
/* 59 */         break;
/*    */       case 4: 
/* 61 */         rotation = 270.0F;
/* 62 */         break;
/*    */       case 5: 
/* 64 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 68 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 71 */     GL11.glTranslatef(0.1F, -0.9F, -0.15F);
/*    */     
/*    */ 
/*    */ 
/* 75 */     this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, tile.totalTicks());
/*    */     
/* 77 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderDemonHeart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */