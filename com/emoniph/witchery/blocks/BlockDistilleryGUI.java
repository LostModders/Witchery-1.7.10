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
/*    */ public class BlockDistilleryGUI
/*    */   extends GuiContainer
/*    */ {
/* 19 */   private static final ResourceLocation IMAGE_URL = new ResourceLocation("witchery", "textures/gui/distiller.png");
/*    */   private BlockDistillery.TileEntityDistillery furnaceInventory;
/*    */   
/*    */   public BlockDistilleryGUI(InventoryPlayer par1InventoryPlayer, BlockDistillery.TileEntityDistillery par2TileEntityFurnace) {
/* 23 */     super(new BlockDistillery.ContainerDistillery(par1InventoryPlayer, par2TileEntityFurnace));
/* 24 */     this.furnaceInventory = par2TileEntityFurnace;
/*    */   }
/*    */   
/*    */   protected void func_146979_b(int par1, int par2)
/*    */   {
/* 29 */     String s = this.furnaceInventory.func_145818_k_() ? this.furnaceInventory.func_145825_b() : I18n.func_135052_a(this.furnaceInventory.func_145825_b(), new Object[0]);
/* 30 */     this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
/* 31 */     this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
/*    */   }
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37 */     this.field_146297_k.func_110434_K().func_110577_a(IMAGE_URL);
/* 38 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 39 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 40 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/* 41 */     int brewTime = this.furnaceInventory.getCookProgressScaled(38);
/*    */     
/* 43 */     if (this.furnaceInventory.powerLevel <= 0) {
/* 44 */       func_73729_b(k + 35, l + 58, 197, 0, 9, 9);
/*    */     }
/*    */     
/* 47 */     if (brewTime > 0)
/*    */     {
/* 49 */       func_73729_b(k + 68, l + 14, 176, 29, brewTime, 35);
/*    */       
/* 51 */       int k1 = (800 - this.furnaceInventory.furnaceCookTime) / 2 % 7;
/*    */       
/* 53 */       switch (k1) {
/*    */       case 0: 
/* 55 */         brewTime = 29;
/* 56 */         break;
/*    */       case 1: 
/* 58 */         brewTime = 24;
/* 59 */         break;
/*    */       case 2: 
/* 61 */         brewTime = 20;
/* 62 */         break;
/*    */       case 3: 
/* 64 */         brewTime = 16;
/* 65 */         break;
/*    */       case 4: 
/* 67 */         brewTime = 11;
/* 68 */         break;
/*    */       case 5: 
/* 70 */         brewTime = 6;
/* 71 */         break;
/*    */       case 6: 
/* 73 */         brewTime = 0;
/*    */       }
/*    */       
/* 76 */       if (brewTime > 0) {
/* 77 */         func_73729_b(k + 33, l + 20 + 29 - brewTime, 185, 29 - brewTime, 12, brewTime);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockDistilleryGUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */