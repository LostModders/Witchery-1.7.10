/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.item.ItemBase;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBrew extends ItemBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon itemIconOverlay;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon itemIconSplash;
/*     */   
/*     */   public ItemBrew()
/*     */   {
/*  22 */     func_77625_d(8);
/*  23 */     func_77627_a(true);
/*  24 */     func_77656_e(0);
/*  25 */     this.registerWithCreativeTab = false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack stack, int pass)
/*     */   {
/*  31 */     return pass == 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  37 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/*  43 */     if (pass == 0) {
/*  44 */       return this.itemIconOverlay;
/*     */     }
/*  46 */     return (stack == null) || (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())) ? this.itemIconSplash : this.field_77791_bV;
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/*  60 */     super.func_94581_a(iconRegister);
/*     */     
/*  62 */     this.itemIconOverlay = iconRegister.func_94245_a("witchery:brew_overlay");
/*  63 */     this.itemIconSplash = iconRegister.func_94245_a("witchery:brew_splash");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/*  69 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/*  70 */     if (nbtRoot != null) {
/*  71 */       return nbtRoot.func_74779_i("BrewName");
/*     */     }
/*  73 */     return super.func_77653_i(stack);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int pass)
/*     */   {
/*  80 */     if (pass == 0) {
/*  81 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  82 */       return WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtRoot);
/*     */     }
/*  84 */     return super.func_82790_a(stack, pass);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, java.util.List list, boolean expanded)
/*     */   {
/*  91 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/*  92 */     if (nbtRoot != null) {
/*  93 */       String localText = nbtRoot.func_74779_i("BrewInfo");
/*  94 */       if (localText != null) {
/*  95 */         for (String s : localText.split("\n")) {
/*  96 */           if (!s.isEmpty()) {
/*  97 */             list.add(s);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/* 106 */     return net.minecraft.item.EnumRarity.common;
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack stack)
/*     */   {
/* 111 */     int DEFAULT_SPEED = 32;
/* 112 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/* 113 */     int drinkSpeed = nbtRoot != null ? nbtRoot.func_74762_e("BrewDrinkSpeed") : 32;
/* 114 */     return drinkSpeed > 0 ? drinkSpeed : 32;
/*     */   }
/*     */   
/*     */   public net.minecraft.item.EnumAction func_77661_b(ItemStack stack)
/*     */   {
/* 119 */     if (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())) {
/* 120 */       return net.minecraft.item.EnumAction.bow;
/*     */     }
/* 122 */     return net.minecraft.item.EnumAction.drink;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 128 */     if (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())) {
/* 129 */       if (!player.field_71075_bZ.field_75098_d) {
/* 130 */         stack.field_77994_a -= 1;
/*     */       }
/* 132 */       world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*     */       
/* 134 */       if (!world.field_72995_K) {
/* 135 */         world.func_72838_d(new EntityBrew(world, player, stack, false));
/*     */       }
/*     */     } else {
/* 138 */       player.func_71008_a(stack, func_77626_a(stack));
/*     */     }
/*     */     
/* 141 */     return stack;
/*     */   }
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 146 */     if (!player.field_71075_bZ.field_75098_d) {
/* 147 */       stack.field_77994_a -= 1;
/*     */     }
/*     */     
/* 150 */     if (!world.field_72995_K) {
/* 151 */       ModifiersEffect modifiers = new ModifiersEffect(1.0D, 1.0D, false, null, false, 0, player);
/* 152 */       WitcheryBrewRegistry.INSTANCE.applyToEntity(world, player, stack.func_77978_p(), modifiers);
/*     */     }
/*     */     
/* 155 */     if (!player.field_71075_bZ.field_75098_d) {
/* 156 */       if (stack.field_77994_a <= 0) {
/* 157 */         return new ItemStack(net.minecraft.init.Items.field_151069_bo);
/*     */       }
/*     */       
/* 160 */       player.field_71071_by.func_70441_a(new ItemStack(net.minecraft.init.Items.field_151069_bo));
/*     */     }
/*     */     
/* 163 */     return stack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ItemBrew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */