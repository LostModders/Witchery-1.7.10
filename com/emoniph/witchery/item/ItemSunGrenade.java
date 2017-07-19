/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityGrenade;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemSunGrenade extends ItemBase
/*     */ {
/*     */   private final int mode;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon itemIconOverlay;
/*     */   
/*     */   public ItemSunGrenade(int mode)
/*     */   {
/*  23 */     this.mode = mode;
/*  24 */     func_77625_d(16);
/*  25 */     func_77656_e(0);
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
/*     */   {
/*  30 */     super.func_77624_a(stack, player, list, moreTips);
/*  31 */     if (this.mode == 1) {
/*  32 */       list.add(String.format(com.emoniph.witchery.Witchery.resource("item.witchery:dupgrenade.tip"), new Object[] { getOwnerName(stack) }));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack stack, int pass)
/*     */   {
/*  39 */     return pass == 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  45 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/*  51 */     if (pass == 0) {
/*  52 */       return this.itemIconOverlay;
/*     */     }
/*  54 */     return this.field_77791_bV;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/*  64 */     super.func_94581_a(iconRegister);
/*     */     
/*  66 */     this.itemIconOverlay = iconRegister.func_94245_a("witchery:ingredient.quartzSphere");
/*     */   }
/*     */   
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  71 */     return net.minecraft.item.EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack stack)
/*     */   {
/*  76 */     return EnumAction.bow;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  82 */     if (!player.field_71075_bZ.field_75098_d) {
/*  83 */       stack.field_77994_a -= 1;
/*     */     }
/*  85 */     world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*     */     
/*  87 */     if (!world.field_72995_K) {
/*  88 */       EntityGrenade grenade = new EntityGrenade(world, player, stack);
/*  89 */       grenade.setMode(this.mode);
/*  90 */       if (this.mode == 1) {
/*  91 */         grenade.setOwner(getOwnerName(stack));
/*     */       }
/*  93 */       world.func_72838_d(grenade);
/*     */     }
/*     */     
/*  96 */     return stack;
/*     */   }
/*     */   
/*     */   public static String getOwnerName(ItemStack stack) {
/* 100 */     if (stack.func_77942_o()) {
/* 101 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 102 */       return nbtRoot.func_74779_i("Owner");
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   public static void setOwnerName(ItemStack stack, String name) {
/* 108 */     if (!stack.func_77942_o()) {
/* 109 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/* 111 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/* 112 */     nbtRoot.func_74778_a("Owner", name);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemSunGrenade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */