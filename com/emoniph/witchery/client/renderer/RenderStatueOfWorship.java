/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockStatueOfWorship.TileEntityStatueOfWorship;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderStatueOfWorship
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 20 */   private final ModelBiped model = new ModelBiped(0.0F);
/*    */   
/*    */ 
/* 23 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/statueofworship.png");
/*    */   
/*    */   public void func_147500_a(TileEntity tile, double x, double y, double z, float var8)
/*    */   {
/* 27 */     GL11.glPushMatrix();
/* 28 */     GL11.glTranslated(x, y, z);
/*    */     
/* 30 */     this.model.field_78091_s = true;
/* 31 */     this.model.field_78119_l = 1;
/* 32 */     this.model.field_78120_m = 1;
/*    */     
/* 34 */     BlockStatueOfWorship.TileEntityStatueOfWorship statue = tile != null ? (BlockStatueOfWorship.TileEntityStatueOfWorship)tile : null;
/* 35 */     World world = statue != null ? statue.func_145831_w() : null;
/*    */     
/* 37 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 38 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 39 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*    */     
/* 41 */     if ((world != null) && (statue != null)) {
/* 42 */       int meta = world.func_72805_g(statue.field_145851_c, statue.field_145848_d, statue.field_145849_e);
/* 43 */       float rotation = 0.0F;
/* 44 */       switch (meta) {
/*    */       case 2: 
/* 46 */         rotation = 0.0F;
/* 47 */         break;
/*    */       case 3: 
/* 49 */         rotation = 180.0F;
/* 50 */         break;
/*    */       case 4: 
/* 52 */         rotation = 270.0F;
/* 53 */         break;
/*    */       case 5: 
/* 55 */         rotation = 90.0F;
/*    */       }
/*    */       
/* 58 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 61 */     func_147499_a(statue.getLocationSkin());
/* 62 */     GL11.glColor3f(0.7F, 0.7F, 0.7F);
/* 63 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/*    */ 
/*    */ 
/* 67 */     GL11.glPushAttrib(16448);
/* 68 */     GL11.glShadeModel(7424);
/* 69 */     GL11.glDisable(3008);
/* 70 */     GL11.glEnable(3042);
/* 71 */     GL11.glBlendFunc(770, 771);
/*    */     
/*    */ 
/* 74 */     func_147499_a(TEXTURE_URL);
/* 75 */     GL11.glColor3f(0.8F, 0.8F, 0.8F);
/* 76 */     this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 87 */     GL11.glPopAttrib();
/*    */     
/* 89 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderStatueOfWorship.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */