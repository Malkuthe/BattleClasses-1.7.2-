package com.malkuthe.battleclassmod.items;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.PlayerClass;
import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.inventories.BCMInterfaceInventory;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BoonItem extends Item {
	
	public BoonItem(int id){
		super(id);
		setCreativeTab(null);
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.boonItemUnlocalized);
	}
	
	public void onItemCreated(ItemStack itemstack, World world, EntityPlayer player){
		itemstack.stackTagCompound = new NBTTagCompound();
	}

	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean j){
		EntityPlayer player = (EntityPlayer) entity;
		PlayerClass props = PlayerClass.get(player);
		String playerClass = props.getPlayerClass();
		
		AttributeInstance thiefspeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
		AttributeModifier speed;
		
		BCMInterfaceInventory inventoryCustom = props.inventory;
		ItemStack boonStack = inventoryCustom.getStackInSlot(0);
		speed = new AttributeModifier(player.getPersistentID(), "Thief's Haste", 3.0, 2);
		if (boonStack != null){
			NBTTagCompound itemProps = boonStack.stackTagCompound;
			String owner = itemProps.getString("Owner");
			String itemClass = itemProps.getString("Class");
			
			if(props.isClassHaste(playerClass) && props.isClassHaste(itemClass) && owner.equals(player.username)){
				if(thiefspeed.getModifier(player.getPersistentID()) == null){
					thiefspeed.applyModifier(speed);
				} 
			} else if(thiefspeed.getModifier(player.getPersistentID()) != null){
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
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){
		NBTTagCompound properties = itemstack.stackTagCompound;
		String owner = properties.getString("Owner");
		System.out.println(owner);
		if (!world.isRemote && itemstack != null){
			if(player.isSneaking()){
				if(!owner.equals(player.username) && owner.equals("none")){
					properties.setString("Owner", player.username);
					String newowner = properties.getString("Owner");
					System.out.println("Boon Owner set to:" + newowner);
				} else if (!owner.equals(player.username) && !owner.equals("none")){
					
				} else if (owner.equals(player.username)){
					PlayerClass props = PlayerClass.get(player);
					if(properties.getString("Class") == null){
						props.ClassChange(Configs.defaultClass);
					} else {
						props.ClassChange(properties.getString("Class"));
					}
				}
			} else {
				
			}
				
		}
		return itemstack;
	}
	
	private Icon[] iconIndex;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register){
		
		iconIndex = new Icon[2];
		
		//registering the two icons
		iconIndex[0] = register.registerIcon(BCMInfo.ID + ":" + ItemInfo.boonItemUnlocalized);
		System.out.println(iconIndex[0]);
		iconIndex[1] = register.registerIcon(BCMInfo.ID + ":" + ItemInfo.boonItemUnlocalized + "Active");
		System.out.println(iconIndex[1]);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(ItemStack itemstack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
		NBTTagCompound properties = itemstack.stackTagCompound;
		this.itemIcon = iconIndex[0];
		if (properties != null){
			if (properties.getString("Owner") != null && !properties.getString("Owner").equals("none")){
				return iconIndex[1];
			} 
			return iconIndex[0];
		}
		return this.itemIcon;
			
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconIndex(ItemStack itemstack){
		NBTTagCompound properties = itemstack.stackTagCompound;
		this.itemIcon = iconIndex[0];
		if (properties != null){
			if (properties.getString("Owner") != null && !properties.getString("Owner").equals("none")){
				return iconIndex[1];
			} 
			return iconIndex[0];
		}
		return this.itemIcon;
	}
	
	@SideOnly(Side.CLIENT)
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
			String bcmclass = properties.getString("Class");
			int level = properties.getInteger("Level");
			int tributes = properties.getInteger("Tributes");
			
			if( owner == player.username ){
				list.add("Owner: " + EnumChatFormatting.BLUE + owner);
			} else if ( owner == "none" ){
				list.add("Owner: " + EnumChatFormatting.YELLOW + owner);
			} else {
				list.add("Owner: " + EnumChatFormatting.RED + owner); 
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
	
}
