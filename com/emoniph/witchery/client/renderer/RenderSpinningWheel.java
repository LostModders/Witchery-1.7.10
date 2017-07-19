/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
/*    */ import com.emoniph.witchery.client.model.ModelSpinningWheel;
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
/*    */ public class RenderSpinningWheel extends TileEntitySpecialRenderer
/*    */ {
/*    */   private final ModelSpinningWheel model;
/*    */   
/*    */   public RenderSpinningWheel()
/*    */   {
/* 24 */     this.model = new ModelSpinningWheel();
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 29 */     GL11.glPushMatrix();
/*    */     
/*    */ 
/*    */ 
/* 33 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*    */     
/* 35 */     BlockSpinningWheel.TileEntitySpinningWheel spinningWheel = (BlockSpinningWheel.TileEntitySpinningWheel)tileEntity;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 41 */     renderSpinningWheel(spinningWheel, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.SPINNING_WHEEL);
/*    */     
/*    */ 
/* 44 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/* 47 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/spinningwheel.png");
/*    */   
/*    */   public void renderSpinningWheel(BlockSpinningWheel.TileEntitySpinningWheel te, World world, int x, int y, int z, Block par1BlockBrewingStand) {
/* 50 */     GL11.glPushMatrix();
/*    */     
/* 52 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/*    */ 
/*    */ 
/* 56 */     func_147499_a(TEXTURE_URL);
/*    */     
/*    */ 
/*    */ 
/* 60 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 69 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 71 */     if (world != null) {
/* 72 */       int meta = world.func_72805_g(x, y, z);
/*    */       
/* 74 */       float rotation = 0.0F;
/* 75 */       switch (meta) {
/*    */       case 2: 
/* 77 */         rotation = 0.0F;
/* 78 */         break;
/*    */       case 3: 
/* 80 */         rotation = 180.0F;
/* 81 */         break;
/*    */       case 4: 
/* 83 */         rotation = 270.0F;
/* 84 */         break;
/*    */       case 5: 
/* 86 */         rotation = 90.0F;
/*    */       }
/*    */       
/*    */       
/* 90 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 93 */     this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
/*    */     
/* 95 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderSpinningWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */