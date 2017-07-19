/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
/*     */ import com.emoniph.witchery.client.model.ModelGrassper;
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
/*     */ public class RenderGrassper
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   final ModelGrassper model;
/*     */   private RenderItem renderItems;
/*     */   
/*     */   public RenderGrassper()
/*     */   {
/*  33 */     this.model = new ModelGrassper();
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
/*  63 */     BlockGrassper.TileEntityGrassper tileEntityYour = (BlockGrassper.TileEntityGrassper)tileEntity;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */     renderGrassper(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.GRASSPER);
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
/*     */   public void renderGrassper(BlockGrassper.TileEntityGrassper te, World world, int x, int y, int z, Block block)
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
/* 114 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*     */     
/* 116 */     if (world != null) {
/* 117 */       int meta = world.func_72805_g(x, y, z);
/*     */       
/* 119 */       float rotation = 0.0F;
/* 120 */       switch (meta) {
/*     */       case 2: 
/* 122 */         rotation = 0.0F;
/* 123 */         break;
/*     */       case 3: 
/* 125 */         rotation = 180.0F;
/* 126 */         break;
/*     */       case 4: 
/* 128 */         rotation = 270.0F;
/* 129 */         break;
/*     */       case 5: 
/* 131 */         rotation = 90.0F;
/*     */       }
/*     */       
/*     */       
/* 135 */       GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     
/* 138 */     this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*     */     
/* 140 */     GL11.glPopMatrix();
/*     */     
/* 142 */     if (world != null) {
/* 143 */       ItemStack newStack = null;
/* 144 */       float rotational = (float)Minecraft.func_71386_F() / 3000.0F * 300.0F;
/*     */       
/* 146 */       EntityItem ei = new EntityItem(world);
/* 147 */       ei.field_70290_d = 0.0F;
/* 148 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 149 */       GL11.glPushMatrix();
/* 150 */       GL11.glEnable(32826);
/* 151 */       GL11.glTranslatef(0.0F, 0.0F, 0.0F);
/* 152 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 170.0F, 170.0F);
/* 153 */       GL11.glTranslatef(0.0F, 0.6F, 0.0F);
/* 154 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 156 */       GL11.glScalef(0.75F, 0.75F, 0.75F);
/*     */       
/* 158 */       float yShift = -0.15F;
/* 159 */       float zShift = 0.65F;
/* 160 */       float xShift = 0.65F;
/*     */       
/* 162 */       boolean fancy = Witchery.proxy.getGraphicsLevel();
/* 163 */       for (int i = 0; i < te.func_70302_i_(); i++) {
/* 164 */         if (i > 46) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 173 */         if (te.func_70301_a(i) != null)
/*     */         {
/* 175 */           newStack = te.func_70301_a(i).func_77946_l();
/* 176 */           newStack.field_77994_a = 1;
/* 177 */           ei.func_92058_a(newStack);
/* 178 */           GL11.glPushMatrix();
/* 179 */           GL11.glTranslatef(xShift, yShift, zShift);
/*     */           
/* 181 */           if (fancy) {
/* 182 */             GL11.glRotatef(rotational / 2.0F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */           
/* 185 */           GL11.glPushMatrix();
/* 186 */           this.renderItems.func_76986_a(ei, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 187 */           GL11.glPopMatrix();
/* 188 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/* 191 */       GL11.glDisable(32826);
/* 192 */       GL11.glPopMatrix();
/* 193 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/* 197 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/grassper.png");
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGrassper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */