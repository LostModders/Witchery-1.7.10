/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.util.Dye;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent.Pre;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class PotionColorful
/*    */   extends PotionBase
/*    */   implements IHandlePreRenderLiving, IHandleRenderLiving
/*    */ {
/*    */   public PotionColorful(int id, int color)
/*    */   {
/* 23 */     super(id, true, color);
/* 24 */     setIncurable();
/* 25 */     hideInventoryText();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Pre event, int amplifier)
/*    */   {
/* 31 */     GL11.glPushMatrix();
/* 32 */     Dye dye = Dye.DYES[Math.min(amplifier, Dye.DYES.length - 1)];
/*    */     
/* 34 */     float red = (dye.rgb >>> 16 & 0xFF) / 256.0F;
/* 35 */     float green = (dye.rgb >>> 8 & 0xFF) / 256.0F;
/* 36 */     float blue = (dye.rgb & 0xFF) / 256.0F;
/*    */     
/* 38 */     GL11.glColor3f(red, green, blue);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {}
/*    */   
/*    */ 
/*    */   public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
/*    */   {
/* 48 */     Dye dye = Dye.DYES[Math.min(effect.func_76458_c(), Dye.DYES.length - 1)];
/* 49 */     String label = Witchery.resource("witchery:color." + dye.unlocalizedName);
/* 50 */     mc.field_71466_p.func_78261_a(label, x + 10 + 18, y + 6, 16777215);
/* 51 */     String duration = Potion.func_76389_a(effect);
/* 52 */     mc.field_71466_p.func_78261_a(duration, x + 10 + 18, y + 6 + 10, 8355711);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionColorful.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */