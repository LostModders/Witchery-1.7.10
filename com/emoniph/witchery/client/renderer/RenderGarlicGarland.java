/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockGarlicGarland.TileEntityGarlicGarland;
/*    */ import com.emoniph.witchery.client.model.ModelGarlicGarland;
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
/*    */ public class RenderGarlicGarland
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelGarlicGarland model;
/*    */   
/*    */   public RenderGarlicGarland()
/*    */   {
/* 23 */     this.model = new ModelGarlicGarland();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 28 */     GL11.glPushMatrix();
/* 29 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 30 */     BlockGarlicGarland.TileEntityGarlicGarland tileEntityYour = (BlockGarlicGarland.TileEntityGarlicGarland)tileEntity;
/* 31 */     renderGarlicGarland(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*    */     
/* 33 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 36 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/garlicgarland.png");
/*    */   
/*    */   public void renderGarlicGarland(BlockGarlicGarland.TileEntityGarlicGarland te, World world, int x, int y, int z)
/*    */   {
/* 40 */     GL11.glPushMatrix();
/* 41 */     GL11.glTranslatef(0.5F, 0.9F, 0.5F);
/* 42 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 44 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
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
/*    */     }
/*    */     
/* 70 */     GL11.glTranslatef(0.0F, -0.1F, 0.02F);
/*    */     
/* 72 */     this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 74 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGarlicGarland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */