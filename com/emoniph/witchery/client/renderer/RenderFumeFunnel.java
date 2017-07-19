/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.blocks.BlockFumeFunnel.TileEntityFumeFunnel;
/*    */ import com.emoniph.witchery.client.model.ModelFumeFunnel;
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
/*    */ public class RenderFumeFunnel extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelFumeFunnel model;
/*    */   
/*    */   public RenderFumeFunnel(boolean filtered)
/*    */   {
/* 24 */     this.model = new ModelFumeFunnel(filtered);
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 29 */     GL11.glPushMatrix();
/*    */     
/*    */ 
/* 32 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 33 */     BlockFumeFunnel.TileEntityFumeFunnel tileEntityYour = (BlockFumeFunnel.TileEntityFumeFunnel)tileEntity;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 39 */     renderFumeFunnel(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.OVEN_FUMEFUNNEL);
/*    */     
/* 41 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 44 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/fumefunnel.png");
/*    */   
/*    */   public void renderFumeFunnel(BlockFumeFunnel.TileEntityFumeFunnel te, World world, int x, int y, int z, Block par1BlockBrewingStand)
/*    */   {
/* 48 */     GL11.glPushMatrix();
/*    */     
/* 50 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/*    */ 
/*    */ 
/* 54 */     func_147499_a(TEXTURE_URL);
/*    */     
/*    */ 
/*    */ 
/* 58 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 67 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 69 */     if (world != null) {
/* 70 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 72 */       float rotation = 0.0F;
/* 73 */       switch (meta) {
/*    */       case 2: 
/* 75 */         rotation = 0.0F;
/* 76 */         break;
/*    */       case 3: 
/* 78 */         rotation = 180.0F;
/* 79 */         break;
/*    */       case 4: 
/* 81 */         rotation = 270.0F;
/* 82 */         break;
/*    */       case 5: 
/* 84 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 88 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 91 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*    */     
/* 93 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderFumeFunnel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */