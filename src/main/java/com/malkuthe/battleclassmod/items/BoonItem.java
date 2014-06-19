package com.malkuthe.battleclassmod.items;

import java.util.Arrays;
import java.util.List;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.PlayerClass;
import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BoonItem extends Item {
	public static final int ACTIVE_BOON_META = 1;

	public static final String[] names = new String[] {
			"boon", "boonActive"
	};
	public static final String[] textures = new String[] {
			"bcmBoonItem", "bcmBoonItemActive"
	};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BoonItem(){
		// BoonItem is now a metadata item
		// This is because if you want to change the icon of it, it will change for ALL the instances of this item.
//		setCreativeTab(BattleClassMod.tabCustom);
		/* The problem with having it as a metadata item is that this would add them BOTH to this creative tab... The only way I can think of to fix this is to create to separate items...
		 * But who wants to do that? I am sure there is a way to do this... :/
		 */
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		GameRegistry.registerItem(this, "item_boon"); // One thing you forgot to do
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean j){
		if (itemstack.stackTagCompound == null)
		{
			itemstack.stackTagCompound = generateDefaultNBTTagCompound();
		}
		/*
		 * Okay, the problem you have with your boon item is this: Invalid UUID string: thiefspeed
		 * See how you are retrieving a UUID from the string thief? That string is invalid - you might want to fix that (However you do that)
		 */

		/*
		EntityPlayer player = (EntityPlayer) entity;
		PlayerClass props = PlayerClass.get(player);
		String playerClass = props.getPlayerClass();
		String thief = "thiefspeed";
		UUID thiefSpeed = UUID.fromString(thief);
		
		IAttributeInstance thiefspeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
		AttributeModifier speed;
		
		BCMInterfaceInventory inventoryCustom = props.inventory;
		ItemStack boonStack = inventoryCustom.getStackInSlot(0);
		speed = new AttributeModifier(thiefSpeed, "Thief's Haste", 3.0, 2);
		if (boonStack != null){
			NBTTagCompound itemProps = boonStack.stackTagCompound;
			String playerName = player.getDisplayName();
			String owner = itemProps.getString("Owner");
			String itemClass = itemProps.getString("Class");
			
			if(props.isClassHaste(playerClass) && props.isClassHaste(itemClass) && owner.equals(playerName)){
				if(thiefspeed.getModifier(thiefSpeed) == null){
					thiefspeed.applyModifier(speed);
				} 
			} else if(thiefspeed.getModifier(thiefSpeed) != null){
				thiefspeed.removeModifier(speed);
			}
		} else if(thiefspeed.getModifier(player.getPersistentID()) != null){
			thiefspeed.removeModifier(speed);
		}
		if (itemstack.stackTagCompound == null){
			itemstack.setTagCompound(new NBTTagCompound());
			itemstack.stackTagCompound.setString("Owner", "none");
			itemstack.stackTagCompound.setString("Class", Configs.defaultClass);
			itemstack.stackTagCompound.setInteger("Level", 1);
			itemstack.stackTagCompound.setInteger("Tributes", 0);
		}
		*/
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){
		if (!world.isRemote){
			String owner = itemstack.stackTagCompound.getString("Owner");
			String playerName = player.getDisplayName();
			if(player.isSneaking()){
				System.out.println("The Owner of this boon is: " + owner);
				if(!owner.equals(playerName) && owner.equals("none")){
					itemstack.stackTagCompound.setString("Owner", playerName);
					String newowner = itemstack.stackTagCompound.getString("Owner");
					System.out.println("Boon Owner set to:" + newowner);
					itemstack.setItemDamage(ACTIVE_BOON_META);
				} else if (!owner.equals(playerName) && !owner.equals("none")){
					
				} else if (owner.equals(playerName)){
					PlayerClass props = PlayerClass.get(player);
					if(itemstack.stackTagCompound.getString("Class") == null){
						props.ClassChange(Configs.defaultClass);
					} else {
						props.ClassChange(itemstack.stackTagCompound.getString("Class"));
					}
				}
			}
		}
		return itemstack;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, ItemInfo.songsItemUnlocalized.length - 1);
		return "item." + names[i];
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		int j = MathHelper.clamp_int(par1, 0, names.length - 1);
		return this.icons[j];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < names.length; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.icons = new IIcon[textures.length];

		for (int i = 0; i < textures.length; ++i)
		{
			this.icons[i] = par1IconRegister.registerIcon(BCMInfo.ID + ":" + textures[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	// This is deprecated - i.e. shouldn't be used but can
	public boolean hasEffect(ItemStack itemstack){
		if(itemstack.stackTagCompound != null){
			NBTTagCompound properties = itemstack.stackTagCompound;
			String bcmclass = properties.getString("Class");
			String owner = properties.getString("Owner");
			List<String> list = Arrays.asList(BCMClasses.tierTwoClasses);
		
			if (list.contains(bcmclass) && !owner.equals("none")){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool){
		if (itemstack.stackTagCompound != null){
			NBTTagCompound properties = itemstack.stackTagCompound;
			String owner = properties.getString("Owner");
			String playerName = player.getDisplayName();
			String bcmclass = properties.getString("Class");
			int level = properties.getInteger("Level");
			int tributes = properties.getInteger("Tributes");
			
			if( owner == playerName ){
				list.add("Owner: " + EnumChatFormatting.BLUE + owner); // There is a small bug here. If you log out of the world, and log back in, the name is formatted as red
			} else if ( owner == "none" ){
				list.add("Owner: " + EnumChatFormatting.YELLOW + owner);
			} else {
				list.add("Owner: " + EnumChatFormatting.RED + owner);  // Need to figure out how to fix this
			}
			
			if (bcmclass == Configs.defaultClass){
				list.add("Class: " + EnumChatFormatting.YELLOW + bcmclass);
			} else if(bcmclass != Configs.defaultClass) {
				list.add("Class: " + EnumChatFormatting.GREEN + bcmclass);
			}
			
			list.add("Level: " + level);
			list.add("Tributes: " + tributes);
		}
	}

	public NBTTagCompound generateDefaultNBTTagCompound()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("Owner", "none");
		nbt.setString("Class", Configs.defaultClass);
		nbt.setInteger("Level", 1);
		nbt.setInteger("Tributes", 0);
		return nbt;
	}
}
