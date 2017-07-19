/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.util.BlockProtect;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class PotionDiseased extends PotionBase implements IHandleRenderLiving
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static ResourceLocation TEXTURE;
/*    */   
/*    */   public PotionDiseased(int id, int color)
/*    */   {
/* 27 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void postContructInitialize()
/*    */   {
/* 32 */     setIncurable();
/* 33 */     func_111184_a(SharedMonsterAttributes.field_111264_e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
/*    */   }
/*    */   
/*    */   public double func_111183_a(int amplifier, AttributeModifier p_111183_2_)
/*    */   {
/* 38 */     return -0.5F * (Math.min(amplifier, 1) + 1);
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 43 */     return duration % 40 == 4;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 48 */     if (!entity.field_70170_p.field_72995_K)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 55 */       if (entity.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 56 */         Coord coord = new Coord(entity);
/* 57 */         if ((entity.field_70170_p.func_147437_c(coord.x, coord.y, coord.z)) && (entity.field_70170_p.func_147439_a(coord.x, coord.y - 1, coord.z).func_149688_o().func_76220_a()))
/*    */         {
/* 59 */           if (BlockProtect.checkModsForBreakOK(entity.field_70170_p, coord.x, coord.y, coord.z, entity)) {
/* 60 */             coord.setBlock(entity.field_70170_p, Witchery.Blocks.DISEASE);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
/*    */   {
/* 74 */     if (TEXTURE == null) {
/* 75 */       TEXTURE = new ResourceLocation("witchery", "textures/entities/disease_overlay.png");
/*    */     }
/* 77 */     GL11.glPushMatrix();
/* 78 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
/* 79 */     ModelOverlayRenderer.render(entity, event.x, event.y, event.z, event.renderer);
/* 80 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionDiseased.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */