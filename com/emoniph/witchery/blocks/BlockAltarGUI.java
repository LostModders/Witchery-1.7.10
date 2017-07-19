/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class BlockAltarGUI extends GuiScreen
/*    */ {
/* 16 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery:textures/gui/altar.png");
/*    */   private final BlockAltar.TileEntityAltar tileEntity;
/*    */   private static final int SIZE_OF_TEXTURE_X = 176;
/*    */   private static final int SIZE_OF_TEXTURE_Y = 88;
/*    */   
/* 21 */   public BlockAltarGUI(BlockAltar.TileEntityAltar tileEntity) { this.tileEntity = tileEntity; }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_73863_a(int x, int y, float f)
/*    */   {
/* 29 */     func_146276_q_();
/*    */     
/* 31 */     this.field_146297_k.func_110434_K().func_110577_a(TEXTURE_URL);
/*    */     
/* 33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 35 */     int posX = (this.field_146294_l - 176) / 2;
/* 36 */     int posY = (this.field_146295_m - 88) / 2;
/*    */     
/* 38 */     func_73729_b(posX, posY, 0, 0, 176, 88);
/*    */     
/* 40 */     func_73732_a(this.field_146289_q, "Altar", this.field_146294_l / 2, this.field_146295_m / 2 - 20, 16777215);
/*    */     
/* 42 */     String power = String.format("%.0f / %.0f (x%d)", new Object[] { Float.valueOf(this.tileEntity.getCorePower()), Float.valueOf(this.tileEntity.getCoreMaxPower()), Integer.valueOf(this.tileEntity.getCoreSpeed()) });
/*    */     
/* 44 */     func_73732_a(this.field_146289_q, power, this.field_146294_l / 2, this.field_146295_m / 2, 16777215);
/*    */   }
/*    */   
/*    */   public boolean func_73868_f()
/*    */   {
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   protected void func_73869_a(char par1, int par2) {
/* 53 */     if ((par2 == 1) || (par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i())) {
/* 54 */       this.field_146297_k.field_71439_g.func_71053_j();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockAltarGUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */