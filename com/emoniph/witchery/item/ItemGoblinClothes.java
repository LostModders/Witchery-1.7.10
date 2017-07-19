/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.model.ModelGoblinClothes;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ServerTickEvents.ServerTickTask;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ 
/*     */ public class ItemGoblinClothes extends ItemArmor
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelGoblinClothes modelClothesChest;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelGoblinClothes modelClothesLegs;
/*     */   
/*     */   public ItemGoblinClothes(int armorSlot)
/*     */   {
/*  47 */     super(armorSlot == 0 ? ItemArmor.ArmorMaterial.IRON : ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
/*     */     
/*  49 */     func_77656_e(armorSlot == 0 ? ItemArmor.ArmorMaterial.DIAMOND.func_78046_a(armorSlot) : ItemArmor.ArmorMaterial.IRON.func_78046_a(armorSlot));
/*     */     
/*  51 */     func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public Item func_77655_b(String itemName)
/*     */   {
/*  56 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  57 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
/*     */   {
/*  63 */     if ((!world.field_72995_K) && (world.func_82737_E() % 100L == 0L)) {
/*  64 */       double R = 8.0D;
/*  65 */       AxisAlignedBB bb = AxisAlignedBB.func_72330_a(player.field_70165_t - 8.0D, player.field_70163_u - 8.0D, player.field_70161_v - 8.0D, player.field_70165_t + 8.0D, player.field_70163_u + 8.0D, player.field_70161_v + 8.0D);
/*  66 */       List players = world.func_72872_a(EntityPlayer.class, bb);
/*  67 */       for (Object obj : players) {
/*  68 */         EntityPlayer otherPlayer = (EntityPlayer)obj;
/*  69 */         if ((player != otherPlayer) && (
/*  70 */           ((isQuiverWorn(player)) && (isBeltWorn(otherPlayer))) || ((isQuiverWorn(otherPlayer)) && (isBeltWorn(player)))))
/*     */         {
/*  72 */           if (player.func_70644_a(Potion.field_76429_m)) {
/*  73 */             player.func_82170_o(Potion.field_76429_m.field_76415_H);
/*     */           }
/*     */           
/*  76 */           player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 200, 1));
/*  77 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  83 */     if ((!world.field_72995_K) && (isHelmWorn(player)) && (world.func_82737_E() % 5L == 1L)) {
/*  84 */       double R2 = 16.0D;
/*  85 */       AxisAlignedBB bb2 = AxisAlignedBB.func_72330_a(player.field_70165_t - 16.0D, player.field_70163_u - 16.0D, player.field_70161_v - 16.0D, player.field_70165_t + 16.0D, player.field_70163_u + 16.0D, player.field_70161_v + 16.0D);
/*     */       
/*  87 */       List entities = world.func_72872_a(EntityLivingBase.class, bb2);
/*  88 */       for (Object obj : entities) {
/*  89 */         EntityLivingBase otherPlayer = (EntityLivingBase)obj;
/*  90 */         if ((player != otherPlayer) && 
/*  91 */           (shouldAffectTarget(player, otherPlayer))) {
/*  92 */           if ((otherPlayer instanceof EntityPlayer)) {
/*  93 */             double yawRadians = Math.atan2(otherPlayer.field_70161_v - player.field_70161_v, otherPlayer.field_70165_t - player.field_70161_v);
/*  94 */             double yaw = Math.toDegrees(yawRadians) + 180.0D;
/*  95 */             float rev = ((float)yaw + 90.0F) % 360.0F;
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
/* 108 */             if ((otherPlayer instanceof net.minecraft.entity.player.EntityPlayerMP)) {
/* 109 */               S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(otherPlayer.field_70165_t, otherPlayer.field_70163_u, otherPlayer.field_70161_v, rev, otherPlayer.field_70125_A, false);
/* 110 */               Witchery.packetPipeline.sendTo(packet, (net.minecraft.entity.player.EntityPlayerMP)otherPlayer);
/*     */             }
/*     */             
/*     */ 
/* 114 */             if (!otherPlayer.func_70644_a(Potion.field_76438_s)) {
/* 115 */               otherPlayer.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, 100, 0));
/*     */             }
/*     */           }
/* 118 */           else if (!otherPlayer.func_70644_a(Potion.field_76437_t)) {
/* 119 */             otherPlayer.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 0));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean shouldAffectTarget(EntityLivingBase player, EntityLivingBase target)
/*     */   {
/* 129 */     ItemStack itemstack = target.func_71124_b(1);
/*     */     
/* 131 */     if ((itemstack != null) && (itemstack.func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150423_aK)))
/*     */     {
/* 133 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 137 */     Vec3 vec3 = target.func_70676_i(1.0F).func_72432_b();
/* 138 */     Vec3 vec31 = Vec3.func_72443_a(player.field_70165_t - target.field_70165_t, player.field_70121_D.field_72338_b + player.field_70131_O / 2.0F - (target.field_70163_u + target.func_70047_e()), player.field_70161_v - target.field_70161_v);
/* 139 */     double d0 = vec31.func_72433_c();
/* 140 */     vec31 = vec31.func_72432_b();
/* 141 */     double d1 = vec3.func_72430_b(vec31);
/* 142 */     return (d1 > 1.0D - 0.025D / d0) && (target.func_70685_l(player));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean hasEffect(ItemStack stack, int pass)
/*     */   {
/* 148 */     return (super.hasEffect(stack, pass)) || (stack.func_77973_b() != Witchery.Items.KOBOLDITE_HELM);
/*     */   }
/*     */   
/*     */   public boolean func_82816_b_(ItemStack stack)
/*     */   {
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 159 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   public int func_82814_b(ItemStack stack)
/*     */   {
/* 170 */     if (!func_82816_b_(stack)) {
/* 171 */       return 16777215;
/*     */     }
/*     */     
/* 174 */     return super.func_82814_b(stack);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 180 */     return this.field_77881_a != 0 ? EnumRarity.epic : EnumRarity.rare;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/* 185 */     if (stack != null) {
/* 186 */       String localText = Witchery.resource(func_77658_a() + ".tip");
/* 187 */       if (localText != null) {
/* 188 */         for (String s : localText.split("\n")) {
/* 189 */           if (!s.isEmpty()) {
/* 190 */             list.add(s);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/* 205 */     if (stack != null) {
/* 206 */       if (slot == 0)
/* 207 */         return "witchery:textures/entities/goblinclothes_head" + (type == null ? "" : "_overlay") + ".png";
/* 208 */       if (slot == 2) {
/* 209 */         return "witchery:textures/entities/goblinclothes_legs" + (type == null ? "" : "_overlay") + ".png";
/*     */       }
/* 211 */       return "witchery:textures/entities/goblinclothes" + (type == null ? "" : "_overlay") + ".png";
/*     */     }
/*     */     
/* 214 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
/*     */   {
/* 221 */     if (this.modelClothesChest == null) {
/* 222 */       this.modelClothesChest = new ModelGoblinClothes(0.61F);
/*     */     }
/*     */     
/* 225 */     if (this.modelClothesLegs == null) {
/* 226 */       this.modelClothesLegs = new ModelGoblinClothes(0.0F);
/*     */     }
/*     */     
/* 229 */     ModelBiped armorModel = null;
/* 230 */     if ((stack != null) && ((stack.func_77973_b() instanceof ItemArmor))) {
/* 231 */       int type = ((ItemArmor)stack.func_77973_b()).field_77881_a;
/*     */       
/* 233 */       if (type != 2) {
/* 234 */         armorModel = this.modelClothesChest;
/*     */       } else {
/* 236 */         armorModel = this.modelClothesLegs;
/*     */       }
/*     */       
/* 239 */       if (armorModel != null) {
/* 240 */         boolean isVisible = true;
/*     */         
/* 242 */         armorModel.field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 243 */         armorModel.field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 244 */         armorModel.field_78115_e.field_78806_j = ((isVisible) && ((armorSlot == 1) || (armorSlot == 2)));
/* 245 */         armorModel.field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 246 */         armorModel.field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 247 */         armorModel.field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/* 248 */         armorModel.field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/*     */         
/* 250 */         armorModel.field_78117_n = entityLiving.func_70093_af();
/* 251 */         armorModel.field_78093_q = entityLiving.func_70115_ae();
/* 252 */         armorModel.field_78091_s = entityLiving.func_70631_g_();
/*     */         
/* 254 */         ItemStack heldStack = entityLiving.func_71124_b(0);
/* 255 */         armorModel.field_78120_m = (heldStack != null ? 1 : 0);
/* 256 */         armorModel.field_78118_o = false;
/*     */         
/* 258 */         if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0)) {
/* 259 */           EnumAction enumaction = heldStack.func_77975_n();
/*     */           
/* 261 */           if (enumaction == EnumAction.block) {
/* 262 */             armorModel.field_78120_m = 3;
/*     */           }
/*     */           
/* 265 */           armorModel.field_78118_o = (enumaction == EnumAction.bow);
/*     */         }
/* 267 */         return armorModel;
/*     */       }
/*     */     }
/* 270 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean isQuiverWorn(EntityPlayer player) {
/* 274 */     ItemStack currentArmor = player.func_82169_q(2);
/* 275 */     return (currentArmor != null) && (currentArmor.func_77973_b() == Witchery.Items.MOGS_QUIVER);
/*     */   }
/*     */   
/*     */   private static boolean isHelmWorn(EntityPlayer player) {
/* 279 */     ItemStack currentArmor = player.func_82169_q(3);
/* 280 */     return (currentArmor != null) && (currentArmor.func_77973_b() == Witchery.Items.KOBOLDITE_HELM);
/*     */   }
/*     */   
/*     */   private static boolean isBeltWorn(EntityPlayer player) {
/* 284 */     ItemStack currentArmor = player.func_82169_q(1);
/* 285 */     return (currentArmor != null) && (currentArmor.func_77973_b() == Witchery.Items.GULGS_GURDLE);
/*     */   }
/*     */   
/*     */   public static class EventHooks {
/*     */     @SubscribeEvent
/*     */     public void onLivingHurt(LivingHurtEvent event) {
/* 291 */       if ((!event.entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled())) {
/* 292 */         if (event.source.func_76352_a()) {
/* 293 */           if (event.source.func_76364_f() != null) {
/* 294 */             boolean mogged = event.source.func_76364_f().getEntityData().func_74767_n("WITCMogged");
/* 295 */             if (mogged) {
/* 296 */               if (event.entityLiving.field_70160_al) {
/* 297 */                 event.ammount *= 3.0F;
/*     */               }
/* 299 */               if (event.entityLiving.func_70644_a(Potion.field_76437_t)) {
/* 300 */                 event.entityLiving.func_82170_o(Potion.field_76437_t.field_76415_H);
/*     */               }
/*     */               
/* 303 */               event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 200, 0));
/*     */             }
/*     */           }
/* 306 */         } else if (event.source.func_76355_l().equals("player")) {
/* 307 */           Entity source = event.source.func_76346_g();
/* 308 */           if ((source != null) && ((source instanceof EntityPlayer))) {
/* 309 */             EntityPlayer player = (EntityPlayer)source;
/* 310 */             if ((ItemGoblinClothes.isBeltWorn(player)) && (player.func_70694_bm() == null)) {
/* 311 */               event.ammount = 5.0F;
/* 312 */               final EntityLivingBase entity = event.entityLiving;
/* 313 */               if ((entity instanceof EntityPlayer)) {
/* 314 */                 Witchery.packetPipeline.sendTo(new com.emoniph.witchery.network.PacketPushTarget(entity.field_70159_w, 1.0D, entity.field_70179_y), (EntityPlayer)entity);
/*     */               } else {
/* 316 */                 com.emoniph.witchery.common.ServerTickEvents.TASKS.add(new ServerTickEvents.ServerTickTask(player.field_70170_p)
/*     */                 {
/*     */                   public boolean process() {
/* 319 */                     if ((entity != null) && (!entity.field_70128_L)) {
/* 320 */                       entity.field_70181_x = 1.0D;
/*     */                     }
/* 322 */                     return true;
/*     */                   }
/*     */                 });
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onArrowLoose(ArrowLooseEvent event) {
/* 334 */       if ((!event.isCanceled()) && (ItemGoblinClothes.isQuiverWorn(event.entityPlayer))) {
/* 335 */         float f = event.charge / 20.0F;
/* 336 */         f = (f * f + f * 2.0F) / 3.0F;
/*     */         
/* 338 */         if (f < 0.1D) {
/* 339 */           return;
/*     */         }
/*     */         
/* 342 */         if (f > 1.0F) {
/* 343 */           f = 1.0F;
/*     */         }
/*     */         
/* 346 */         EntityArrow entityarrow = new EntityArrow(event.entityPlayer.field_70170_p, event.entityPlayer, f * 3.0F);
/* 347 */         entityarrow.getEntityData().func_74757_a("WITCMogged", true);
/*     */         
/* 349 */         if (f == 1.0F) {
/* 350 */           entityarrow.func_70243_d(true);
/*     */         }
/*     */         
/* 353 */         int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, event.bow);
/*     */         
/* 355 */         if (k > 0) {
/* 356 */           entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
/*     */         }
/*     */         
/* 359 */         int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, event.bow);
/*     */         
/* 361 */         if (l > 0) {
/* 362 */           entityarrow.func_70240_a(l);
/*     */         }
/*     */         
/* 365 */         if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, event.bow) > 0) {
/* 366 */           entityarrow.func_70015_d(100);
/*     */         }
/*     */         
/* 369 */         event.bow.func_77972_a(1, event.entityPlayer);
/* 370 */         event.entityPlayer.field_70170_p.func_72956_a(event.entityPlayer, "random.bow", 1.0F, 1.0F / (ItemGoblinClothes.field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */         
/*     */ 
/* 373 */         entityarrow.field_70251_a = 2;
/*     */         
/* 375 */         if (!event.entityPlayer.field_70170_p.field_72995_K) {
/* 376 */           event.entityPlayer.field_70170_p.func_72838_d(entityarrow);
/*     */         }
/* 378 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onArrowNock(ArrowNockEvent event) {
/* 384 */       ExtendedPlayer playerEx = ExtendedPlayer.get(event.entityPlayer);
/* 385 */       if (playerEx.getCreatureType() != com.emoniph.witchery.util.TransformCreature.NONE) {
/* 386 */         event.setCanceled(true);
/* 387 */         return;
/*     */       }
/*     */       
/* 390 */       if ((!event.isCanceled()) && (ItemGoblinClothes.isQuiverWorn(event.entityPlayer))) {
/* 391 */         event.entityPlayer.func_71008_a(event.result, event.result.func_77973_b().func_77626_a(event.result));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemGoblinClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */