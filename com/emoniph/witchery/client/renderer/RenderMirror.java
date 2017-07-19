/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockMirror;
/*    */ import com.emoniph.witchery.blocks.BlockMirror.TileEntityMirror;
/*    */ import com.emoniph.witchery.client.model.ModelMirror;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderMirror
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelMirror model;
/*    */   
/*    */   public RenderMirror()
/*    */   {
/* 25 */     this.model = new ModelMirror();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 30 */     GL11.glPushMatrix();
/* 31 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 32 */     BlockMirror.TileEntityMirror tileEntityYour = (BlockMirror.TileEntityMirror)tileEntity;
/* 33 */     renderMirror(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*    */     
/* 35 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 38 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/mirror.png");
/*    */   
/*    */ 
/* 41 */   private static final ResourceLocation TEXTURE2_URL = new ResourceLocation("witchery", "textures/blocks/mirror2.png");
/*    */   
/*    */   public void renderMirror(BlockMirror.TileEntityMirror te, World world, int x, int y, int z)
/*    */   {
/* 45 */     GL11.glPushMatrix();
/* 46 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 47 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 49 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 51 */     if (world != null) {
/* 52 */       int meta = world.func_72805_g(x, y, z);
/* 53 */       int facing = BlockMirror.getDirection(meta);
/*    */       
/* 55 */       float rotation = 0.0F;
/* 56 */       switch (facing) {
/*    */       case 0: 
/* 58 */         rotation = 0.0F;
/* 59 */         break;
/*    */       case 1: 
/* 61 */         rotation = 180.0F;
/* 62 */         break;
/*    */       case 2: 
/* 64 */         rotation = 270.0F;
/* 65 */         break;
/*    */       case 3: 
/* 67 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 71 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */       
/* 73 */       if (!BlockMirror.isBlockTopOfMirror(meta)) {
/* 74 */         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 79 */       GL11.glTranslatef(0.0F, 0.1F, 0.42F);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 85 */     GL11.glTranslatef(0.0F, -0.1F, 0.02F);
/*    */     
/* 87 */     this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 89 */     if (te.men > 0) {
/* 90 */       func_147499_a(TEXTURE2_URL);
/* 91 */       this.model.backMiddle.func_78785_a(0.0625F);
/*    */     }
/*    */     
/* 94 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */