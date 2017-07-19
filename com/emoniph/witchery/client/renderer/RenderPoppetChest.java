/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
/*     */ import com.emoniph.witchery.client.model.ModelPoppetChest;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderPoppetChest extends TileEntitySpecialRenderer
/*     */ {
/*     */   final ModelPoppetChest model;
/*     */   private RenderItem renderItems;
/*     */   
/*     */   public RenderPoppetChest()
/*     */   {
/*  33 */     this.model = new ModelPoppetChest();
/*     */     
/*  35 */     this.renderItems = new RenderItem() {
/*     */       public byte getMiniItemCountForItemStack(ItemStack stack) {
/*  37 */         return 1;
/*     */       }
/*     */       
/*     */       public byte getMiniBlockCountForItemStack(ItemStack stack) {
/*  41 */         return 1;
/*     */       }
/*     */       
/*     */       public boolean shouldBob()
/*     */       {
/*  46 */         return false;
/*     */       }
/*     */       
/*     */       public boolean shouldSpreadItems()
/*     */       {
/*  51 */         return false;
/*     */       }
/*  53 */     };
/*  54 */     this.renderItems.func_76976_a(RenderManager.field_78727_a);
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
/*     */   {
/*  59 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/*  62 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*  63 */     BlockPoppetShelf.TileEntityPoppetShelf tileEntityYour = (BlockPoppetShelf.TileEntityPoppetShelf)tileEntity;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */     renderPoppetChest(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.POPPET_SHELF);
/*     */     
/*  71 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderPoppetChest(BlockPoppetShelf.TileEntityPoppetShelf te, World world, int x, int y, int z, Block block)
/*     */   {
/*  95 */     GL11.glPushMatrix();
/*     */     
/*  97 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */     
/*     */ 
/*     */ 
/* 101 */     func_147499_a(TEXTURE_URL);
/*     */     
/*     */ 
/*     */ 
/* 105 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 114 */     GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
/*     */     
/* 116 */     this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*     */     
/* 118 */     GL11.glPopMatrix();
/*     */     
/* 120 */     if (world != null) {
/* 121 */       ItemStack newStack = null;
/* 122 */       float rotational = (float)Minecraft.func_71386_F() / 3000.0F * 300.0F;
/*     */       
/* 124 */       EntityItem ei = new EntityItem(world);
/* 125 */       ei.field_70290_d = 0.0F;
/* 126 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 127 */       GL11.glPushMatrix();
/* 128 */       GL11.glEnable(32826);
/* 129 */       GL11.glTranslatef(0.0F, 0.0F, 0.0F);
/* 130 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 170.0F, 170.0F);
/* 131 */       GL11.glTranslatef(0.0F, 0.6F, 0.0F);
/* 132 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 134 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */       float yShift;
/* 136 */       float xShift = yShift = zShift = 0.0F;
/* 137 */       float zShift = 0.5F;
/*     */       
/* 139 */       boolean fancy = Witchery.proxy.getGraphicsLevel();
/* 140 */       for (int i = 0; i < te.func_70302_i_(); i++) {
/* 141 */         if (i > 46) {
/*     */           break;
/*     */         }
/* 144 */         xShift += 0.5F;
/* 145 */         if ((i == 3) || (i == 6) || (i == 9)) {
/* 146 */           zShift += 0.5F;
/* 147 */           xShift = 0.5F;
/*     */         }
/*     */         
/* 150 */         if ((te.func_70301_a(i) != null) && (te.func_70301_a(i).func_77973_b() == Witchery.Items.POPPET))
/*     */         {
/* 152 */           newStack = te.func_70301_a(i).func_77946_l();
/* 153 */           newStack.field_77994_a = 1;
/* 154 */           ei.func_92058_a(newStack);
/* 155 */           GL11.glPushMatrix();
/* 156 */           GL11.glTranslatef(xShift, yShift, zShift);
/*     */           
/* 158 */           if (fancy) {
/* 159 */             GL11.glRotatef(rotational / 2.0F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */           
/* 162 */           GL11.glPushMatrix();
/* 163 */           this.renderItems.func_76986_a(ei, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 164 */           GL11.glPopMatrix();
/* 165 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/* 168 */       GL11.glDisable(32826);
/* 169 */       GL11.glPopMatrix();
/* 170 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/* 174 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/poppetShelf.png");
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderPoppetChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */