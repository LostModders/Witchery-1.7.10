/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockPlacedItem.TileEntityPlacedItem;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class RenderPlacedItem
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private final RenderItem3d renderItems;
/*    */   
/*    */   public RenderPlacedItem()
/*    */   {
/* 20 */     this.renderItems = new RenderItem3d(true) {
/*    */       public byte getMiniItemCountForItemStack(ItemStack stack) {
/* 22 */         return 1;
/*    */       }
/*    */       
/*    */       public byte getMiniBlockCountForItemStack(ItemStack stack) {
/* 26 */         return 1;
/*    */       }
/*    */       
/*    */       public boolean shouldBob()
/*    */       {
/* 31 */         return false;
/*    */       }
/*    */       
/*    */       public boolean shouldSpreadItems()
/*    */       {
/* 36 */         return false;
/*    */       }
/* 38 */     };
/* 39 */     this.renderItems.func_76976_a(RenderManager.field_78727_a);
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileEntity, double d0, double d1, double d2, float f)
/*    */   {
/* 44 */     GL11.glPushMatrix();
/* 45 */     GL11.glTranslatef((float)d0, (float)d1, (float)d2);
/* 46 */     renderPlacedItem((BlockPlacedItem.TileEntityPlacedItem)tileEntity, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/* 47 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public void renderPlacedItem(BlockPlacedItem.TileEntityPlacedItem te, World world, int x, int y, int z) {
/* 51 */     if (world != null) {
/* 52 */       EntityItem ei = new EntityItem(world);
/* 53 */       ei.field_70290_d = 0.0F;
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 60 */       if ((te != null) && (te.getStack() != null)) {
/* 61 */         ei.func_92058_a(te.getStack().func_77946_l());
/*    */       } else {
/* 63 */         ei.func_92058_a(new ItemStack(Items.field_151103_aS));
/*    */       }
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 69 */       GL11.glTranslatef(0.5F, 0.05F, 0.5F);
/* 70 */       if (world != null) {
/* 71 */         int meta = world.func_72805_g(x, y, z);
/*    */         
/*    */ 
/* 74 */         float rotation = 0.0F;
/* 75 */         switch (meta) {
/*    */         case 2: 
/* 77 */           rotation = 0.0F;
/* 78 */           break;
/*    */         case 3: 
/* 80 */           rotation = 180.0F;
/* 81 */           break;
/*    */         case 4: 
/* 83 */           rotation = 90.0F;
/* 84 */           break;
/*    */         case 5: 
/* 86 */           rotation = 270.0F;
/*    */         }
/*    */         
/*    */         
/* 90 */         GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */       }
/*    */       
/* 93 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 94 */       GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*    */       
/*    */ 
/*    */ 
/* 98 */       this.renderItems.doRender(ei, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderPlacedItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */