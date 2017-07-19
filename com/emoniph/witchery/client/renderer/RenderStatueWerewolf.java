/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockStatueWerewolf.TileEntityStatueWerewolf;
/*    */ import com.emoniph.witchery.client.model.ModelWolfAltar;
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
/*    */ public class RenderStatueWerewolf
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelWolfAltar model;
/*    */   
/*    */   public RenderStatueWerewolf()
/*    */   {
/* 23 */     this.model = new ModelWolfAltar();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 28 */     GL11.glPushMatrix();
/* 29 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 30 */     BlockStatueWerewolf.TileEntityStatueWerewolf tileEntityGoddess = (BlockStatueWerewolf.TileEntityStatueWerewolf)tileEntity;
/* 31 */     renderStatue(tileEntityGoddess, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/* 32 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 35 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/wolfaltar.png");
/*    */   
/*    */   public void renderStatue(BlockStatueWerewolf.TileEntityStatueWerewolf tileEntity, World world, int x, int y, int z) {
/* 38 */     GL11.glPushMatrix();
/* 39 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 41 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 43 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 45 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 47 */     if (world != null) {
/* 48 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 50 */       float rotation = 0.0F;
/* 51 */       switch (meta) {
/*    */       case 2: 
/* 53 */         rotation = 0.0F;
/* 54 */         break;
/*    */       case 3: 
/* 56 */         rotation = 180.0F;
/* 57 */         break;
/*    */       case 4: 
/* 59 */         rotation = 270.0F;
/* 60 */         break;
/*    */       case 5: 
/* 62 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 66 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 68 */       GL11.glScalef(0.6F, 0.6F, 0.6F);
/* 69 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 70 */       GL11.glTranslatef(0.0F, 1.5F, 0.0F);
/*    */     }
/*    */     
/* 73 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 75 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderStatueWerewolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */