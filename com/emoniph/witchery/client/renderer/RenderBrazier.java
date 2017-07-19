/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
/*    */ import com.emoniph.witchery.client.model.ModelBrazier;
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
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBrazier
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private final ModelBrazier model;
/*    */   
/*    */   public RenderBrazier()
/*    */   {
/* 26 */     this.model = new ModelBrazier();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 31 */     GL11.glPushMatrix();
/*    */     
/*    */ 
/*    */ 
/* 35 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*    */     
/* 37 */     BlockBrazier.TileEntityBrazier spinningWheel = (BlockBrazier.TileEntityBrazier)tileEntity;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 43 */     renderBrazier(spinningWheel, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.BRAZIER);
/*    */     
/* 45 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 48 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/brazier.png");
/*    */   
/*    */   public void renderBrazier(BlockBrazier.TileEntityBrazier te, World world, int x, int y, int z, Block par1BlockBrewingStand) {
/* 51 */     GL11.glPushMatrix();
/*    */     
/* 53 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/*    */ 
/*    */ 
/* 57 */     func_147499_a(TEXTURE_URL);
/*    */     
/*    */ 
/*    */ 
/* 61 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 70 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 72 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*    */     
/* 74 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBrazier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */