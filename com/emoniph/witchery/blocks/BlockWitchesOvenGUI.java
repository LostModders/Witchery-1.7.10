/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class BlockWitchesOvenGUI
/*    */   extends GuiContainer
/*    */ {
/* 19 */   private static final ResourceLocation field_110410_t = new ResourceLocation("witchery", "textures/gui/witchesOven.png");
/*    */   private BlockWitchesOven.TileEntityWitchesOven furnaceInventory;
/*    */   
/*    */   public BlockWitchesOvenGUI(InventoryPlayer par1InventoryPlayer, BlockWitchesOven.TileEntityWitchesOven par2TileEntityFurnace) {
/* 23 */     super(new BlockWitchesOven.ContainerWitchesOven(par1InventoryPlayer, par2TileEntityFurnace));
/* 24 */     this.furnaceInventory = par2TileEntityFurnace;
/*    */   }
/*    */   
/*    */   protected void func_146979_b(int par1, int par2)
/*    */   {
/* 29 */     String s = this.furnaceInventory != null ? I18n.func_135052_a(this.furnaceInventory.func_145825_b(), new Object[0]) : this.furnaceInventory.func_145818_k_() ? this.furnaceInventory.func_145825_b() : "";
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 34 */     this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
/* 35 */     this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
/*    */   }
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 40 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     this.field_146297_k.func_110434_K().func_110577_a(field_110410_t);
/* 42 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 43 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 44 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/*    */ 
/* 47 */     if (this.furnaceInventory != null) {
/* 48 */       if (this.furnaceInventory.isBurning()) {
/* 49 */         int i1 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
/* 50 */         func_73729_b(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
/*    */       }
/*    */       
/* 53 */       int i1 = this.furnaceInventory.getCookProgressScaled(24);
/* 54 */       func_73729_b(k + 79, l + 20, 176, 14, i1 + 1, 16);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchesOvenGUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */