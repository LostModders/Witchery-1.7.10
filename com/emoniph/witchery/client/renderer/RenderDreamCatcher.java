/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
/*    */ import com.emoniph.witchery.client.model.ModelDreamCatcher;
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
/*    */ public class RenderDreamCatcher extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelDreamCatcher model;
/*    */   
/*    */   public RenderDreamCatcher()
/*    */   {
/* 23 */     this.model = new ModelDreamCatcher();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 28 */     GL11.glPushMatrix();
/*    */     
/* 30 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 31 */     BlockDreamCatcher.TileEntityDreamCatcher tileEntityYour = (BlockDreamCatcher.TileEntityDreamCatcher)tileEntity;
/*    */     
/* 33 */     renderDreamCatcher(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.DREAM_CATCHER);
/* 34 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 37 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/dreamCatcher.png");
/*    */   
/*    */   public void renderDreamCatcher(BlockDreamCatcher.TileEntityDreamCatcher tileEntity, World world, int x, int y, int z, Block block)
/*    */   {
/* 41 */     GL11.glPushMatrix();
/* 42 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 44 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 46 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 47 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 49 */     if (world != null) {
/* 50 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 52 */       float rotation = 0.0F;
/* 53 */       switch (meta) {
/*    */       case 2: 
/* 55 */         rotation = 0.0F;
/* 56 */         GL11.glTranslatef(0.0F, 0.0F, -0.01F);
/* 57 */         break;
/*    */       case 3: 
/* 59 */         rotation = 180.0F;
/* 60 */         GL11.glTranslatef(0.0F, 0.0F, 0.01F);
/* 61 */         break;
/*    */       case 4: 
/* 63 */         rotation = 270.0F;
/* 64 */         GL11.glTranslatef(0.01F, 0.0F, 0.0F);
/* 65 */         break;
/*    */       case 5: 
/* 67 */         rotation = 90.0F;
/* 68 */         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
/*    */       }
/*    */       
/*    */       
/* 72 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 75 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, tileEntity);
/*    */     
/* 77 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderDreamCatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */