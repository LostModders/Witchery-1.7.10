/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ 
/*     */ public class PotionBase extends Potion
/*     */ {
/*     */   private boolean inventoryTextHidden;
/*     */   private boolean incurable;
/*     */   private boolean permenant;
/*     */   private static Field fieldPotionIsBadEffect;
/*     */   
/*     */   public PotionBase(int id, int color)
/*     */   {
/*  24 */     this(id, false, color);
/*     */   }
/*     */   
/*     */   protected PotionBase(int id, boolean debuff, int color) {
/*  28 */     super(id, debuff, color);
/*     */   }
/*     */   
/*     */ 
/*     */   public void postContructInitialize() {}
/*     */   
/*     */   public static boolean isDebuff(Potion potion)
/*     */   {
/*     */     try
/*     */     {
/*  38 */       if (fieldPotionIsBadEffect == null) {
/*  39 */         fieldPotionIsBadEffect = ReflectionHelper.findField(Potion.class, new String[] { "isBadEffect", "field_76418_K", "K" });
/*     */       }
/*  41 */       return ((Boolean)fieldPotionIsBadEffect.get(potion)).booleanValue();
/*     */     }
/*     */     catch (IllegalAccessException ex) {}
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isDebuff()
/*     */   {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public PotionBase getPotion() {
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public static boolean isCurable(Potion potion) {
/*  57 */     return (!(potion instanceof PotionBase)) || (((PotionBase)potion).isCurable());
/*     */   }
/*     */   
/*     */   public static boolean isPermenant(Potion potion) {
/*  61 */     return ((potion instanceof PotionBase)) && (((PotionBase)potion).isPermenant());
/*     */   }
/*     */   
/*     */   public boolean isCurable() {
/*  65 */     return !this.incurable;
/*     */   }
/*     */   
/*     */   public boolean isPermenant() {
/*  69 */     return this.permenant;
/*     */   }
/*     */   
/*     */   protected void setIncurable() {
/*  73 */     this.incurable = true;
/*     */   }
/*     */   
/*     */   protected void setPermenant() {
/*  77 */     this.permenant = true;
/*     */   }
/*     */   
/*     */   protected void hideInventoryText() {
/*  81 */     this.inventoryTextHidden = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_111185_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
/*     */   {
/*  87 */     super.func_111185_a(entity, attributes, amplifier);
/*  88 */     if ((this instanceof IHandleRenderLiving)) {
/*  89 */       PotionEffect effect = entity.func_70660_b(this);
/*  90 */       Witchery.packetPipeline.sendToAll(new S1DPacketEntityEffect(entity.func_145782_y(), effect));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_111187_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
/*     */   {
/*  97 */     super.func_111187_a(entity, attributes, amplifier);
/*  98 */     if ((this instanceof IHandleRenderLiving)) {
/*  99 */       Witchery.packetPipeline.sendToAll(new net.minecraft.network.play.server.S1EPacketRemoveEntityEffect(entity.func_145782_y(), new PotionEffect(this.field_76415_H, 1)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldRenderInvText(PotionEffect effect)
/*     */   {
/* 106 */     return !this.inventoryTextHidden;
/*     */   }
/*     */   
/*     */   public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
/*     */   {
/* 111 */     if (this.inventoryTextHidden) {
/* 112 */       mc.field_71466_p.func_78261_a(Witchery.resource("witchery:potion.unknown"), x + 10 + 18, y + 6, 16777215);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */