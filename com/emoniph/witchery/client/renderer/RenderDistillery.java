/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.client.model.ModelDistillery;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderDistillery extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelDistillery model;
/*    */   
/*    */   public RenderDistillery()
/*    */   {
/* 22 */     this.model = new ModelDistillery();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 27 */     GL11.glPushMatrix();
/*    */     
/* 29 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*    */     
/* 31 */     renderDistiller(tileEntity, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.DISTILLERY_IDLE);
/* 32 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 35 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/distillery.png");
/*    */   
/*    */   public void renderDistiller(TileEntity te, World world, int x, int y, int z, Block par1BlockBrewingStand)
/*    */   {
/* 39 */     GL11.glPushMatrix();
/*    */     
/* 41 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/*    */ 
/* 44 */     func_147499_a(TEXTURE_URL);
/*    */     
/*    */ 
/* 47 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 54 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 56 */     if (world != null) {
/* 57 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 59 */       float rotation = 0.0F;
/* 60 */       switch (meta) {
/*    */       case 2: 
/* 62 */         rotation = 0.0F;
/* 63 */         break;
/*    */       case 3: 
/* 65 */         rotation = 180.0F;
/* 66 */         break;
/*    */       case 4: 
/* 68 */         rotation = 270.0F;
/* 69 */         break;
/*    */       case 5: 
/* 71 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 75 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/*    */ 
/* 79 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*    */     
/* 81 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderDistillery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */