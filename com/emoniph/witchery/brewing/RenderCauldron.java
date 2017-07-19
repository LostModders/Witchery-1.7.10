/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderCauldron
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   final ModelCauldron model;
/*    */   
/*    */   public RenderCauldron()
/*    */   {
/* 25 */     this.model = new ModelCauldron();
/*    */   }
/*    */   
/* 28 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/cauldron.png");
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*    */   {
/* 32 */     TileEntityCauldron cauldron = (TileEntityCauldron)tileEntity;
/*    */     
/* 34 */     GL11.glPushMatrix();
/* 35 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 36 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 37 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 38 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 40 */     func_147499_a(TEXTURE_URL);
/*    */     
/* 42 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 44 */     if (cauldron.isFilled()) {
/* 45 */       func_147499_a(TextureMap.field_110575_b);
/*    */       
/* 47 */       GL11.glPushMatrix();
/* 48 */       GL11.glEnable(3042);
/* 49 */       GL11.glBlendFunc(770, 771);
/* 50 */       GL11.glDisable(3008);
/*    */       
/* 52 */       int color = cauldron.getColor();
/* 53 */       if (color == -1) {
/* 54 */         color = 3432410;
/*    */       }
/*    */       
/* 57 */       float red = (color >>> 16 & 0xFF) / 256.0F;
/* 58 */       float green = (color >>> 8 & 0xFF) / 256.0F;
/* 59 */       float blue = (color & 0xFF) / 256.0F;
/* 60 */       GL11.glColor4f(red, green, blue, 1.0F);
/*    */       
/*    */ 
/* 63 */       float w = -0.375F;
/* 64 */       float depth = 1.3F - (float)(cauldron.getPercentFilled() * 0.5D);
/* 65 */       GL11.glTranslatef(w, depth, -w);
/* 66 */       GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
/*    */       
/* 68 */       float s = 0.046875F;
/* 69 */       GL11.glScalef(0.046875F, 0.046875F, 0.046875F);
/*    */       
/* 71 */       IIcon icon = Witchery.Blocks.BREW.func_149691_a(0, 0);
/* 72 */       int x = 0;int y = 0;
/* 73 */       int u = 16;int v = 16;
/*    */       
/* 75 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 76 */       tessellator.func_78382_b();
/* 77 */       tessellator.func_78380_c(200);
/* 78 */       tessellator.func_78374_a(0.0D, 16.0D, 0.0D, icon.func_94209_e(), icon.func_94210_h());
/* 79 */       tessellator.func_78374_a(16.0D, 16.0D, 0.0D, icon.func_94212_f(), icon.func_94210_h());
/* 80 */       tessellator.func_78374_a(16.0D, 0.0D, 0.0D, icon.func_94212_f(), icon.func_94206_g());
/* 81 */       tessellator.func_78374_a(0.0D, 0.0D, 0.0D, icon.func_94209_e(), icon.func_94206_g());
/* 82 */       tessellator.func_78381_a();
/*    */       
/* 84 */       GL11.glEnable(3008);
/* 85 */       GL11.glDisable(3042);
/* 86 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 89 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/RenderCauldron.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */