/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockBloodCrucible.TileEntityBloodCrucible;
/*    */ import com.emoniph.witchery.client.model.ModelBloodCrucible;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBloodCrucible
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelBloodCrucible model;
/*    */   
/*    */   public RenderBloodCrucible()
/*    */   {
/* 26 */     this.model = new ModelBloodCrucible();
/*    */   }
/*    */   
/* 29 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/bloodcrucible.png");
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 34 */     BlockBloodCrucible.TileEntityBloodCrucible crucible = (BlockBloodCrucible.TileEntityBloodCrucible)tileEntity;
/*    */     
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 38 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 39 */     GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/* 40 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/* 43 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 45 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 47 */     float percentFilled = crucible.getPercentFilled();
/*    */     
/* 49 */     if (percentFilled > 0.0F) {
/* 50 */       func_147499_a(TextureMap.field_110575_b);
/*    */       
/* 52 */       GL11.glPushMatrix();
/* 53 */       GL11.glEnable(3042);
/* 54 */       GL11.glBlendFunc(770, 771);
/* 55 */       GL11.glDisable(3008);
/*    */       
/* 57 */       int color = 16711680;
/*    */       
/* 59 */       float red = (color >>> 16 & 0xFF) / 256.0F;
/* 60 */       float green = (color >>> 8 & 0xFF) / 256.0F;
/* 61 */       float blue = (color & 0xFF) / 256.0F;
/* 62 */       GL11.glColor4f(red, green, blue, 0.9F);
/*    */       
/*    */ 
/* 65 */       float w = -0.1875F;
/* 66 */       float depth = -0.1F + -0.2F * percentFilled;
/* 67 */       GL11.glTranslatef(w, depth, -w);
/* 68 */       GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
/*    */       
/* 70 */       float s = 0.0234375F;
/* 71 */       GL11.glScalef(0.0234375F, 0.0234375F, 0.0234375F);
/*    */       
/* 73 */       IIcon icon = Blocks.field_150353_l.func_149691_a(0, 0);
/* 74 */       int x = 0;int y = 0;
/* 75 */       int u = 16;int v = 16;
/*    */       
/* 77 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 78 */       tessellator.func_78382_b();
/* 79 */       tessellator.func_78380_c(200);
/* 80 */       tessellator.func_78374_a(0.0D, 16.0D, 0.0D, icon.func_94209_e(), icon.func_94210_h());
/* 81 */       tessellator.func_78374_a(16.0D, 16.0D, 0.0D, icon.func_94212_f(), icon.func_94210_h());
/* 82 */       tessellator.func_78374_a(16.0D, 0.0D, 0.0D, icon.func_94212_f(), icon.func_94206_g());
/* 83 */       tessellator.func_78374_a(0.0D, 0.0D, 0.0D, icon.func_94209_e(), icon.func_94206_g());
/* 84 */       tessellator.func_78381_a();
/*    */       
/* 86 */       GL11.glEnable(3008);
/* 87 */       GL11.glDisable(3042);
/* 88 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 91 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBloodCrucible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */