package com.gmail.nossr50.util.experience;

import com.gmail.nossr50.config.Unload;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.mcMMO;
import org.bukkit.Material;

import java.util.HashMap;

/**
 * This class handles the XP for block break related XP
 */
public class ExperienceMapManager implements Unload {
    private HashMap<PrimarySkillType, HashMap<Material, String>> skillMaterialXPMap;
    private HashMap<String, Integer> miningXpMap;
    private HashMap<String, Integer> herbalismXpMap;
    private HashMap<String, Integer> woodcuttingXpMap;
    private HashMap<String, Integer> excavationXpMap;

    private double globalXpMult;

    public ExperienceMapManager() {
        miningXpMap = new HashMap<>();
        herbalismXpMap = new HashMap<>();
        woodcuttingXpMap = new HashMap<>();
        excavationXpMap = new HashMap<>();

        //Register with unloader
        mcMMO.getConfigManager().registerUnloadable(this);
    }

    public void buildMaterialXPMap(HashMap<String, Integer> xpMap, PrimarySkillType primarySkillType)
    {

    }

    /**
     * Change the gloabl xp multiplier, this is temporary and will not be serialiized
     * @param newGlobalXpMult new global xp multiplier value
     */
    public void setGlobalXpMult(double newGlobalXpMult) {
        mcMMO.p.getLogger().info("Setting the global XP multiplier -> " + newGlobalXpMult);
        globalXpMult = newGlobalXpMult;
    }

    public void setMiningXpMap(HashMap<String, Integer> miningXpMap) {
        mcMMO.p.getLogger().info("Registering Mining XP Values...");
        this.miningXpMap = miningXpMap;
    }

    public void setHerbalismXpMap(HashMap<String, Integer> herbalismXpMap) {
        mcMMO.p.getLogger().info("Registering Herbalism XP Values...");
        this.herbalismXpMap = herbalismXpMap;
    }

    public void setWoodcuttingXpMap(HashMap<String, Integer> woodcuttingXpMap) {
        mcMMO.p.getLogger().info("Registering Woodcutting XP Values...");
        this.woodcuttingXpMap = woodcuttingXpMap;
    }

    public void setExcavationXpMap(HashMap<String, Integer> excavationXpMap) {
        mcMMO.p.getLogger().info("Registering Excavation XP Values...");
        this.excavationXpMap = excavationXpMap;
    }

    /**
     * Gets the current global xp multiplier value
     * This value can be changed by the xprate command
     * @return
     */
    public double getGlobalXpMult() {
        return globalXpMult;
    }

    /**
     * Gets the original value of the global XP multiplier
     * This is defined by the users config
     * This value can be different from the current working value (due to xprate etc)
     * @return the original global xp multiplier value from the user config file
     */
    public double getOriginalGlobalXpMult() {
        return mcMMO.getConfigManager().getConfigExperience().getGlobalXPMultiplier();
    }

    /**
     * Determines whether or not a block has Mining XP
     *
     * @param material target block material type
     * @return true if the block has valid xp registers
     */
    public boolean hasMiningXp(Material material) {
        return miningXpMap.get(material.getKey().getKey()) != null;
    }

    /**
     * Determines whether or not a block has Herbalism XP
     *
     * @param material target block material type
     * @return true if the block has valid xp registers
     */
    public boolean hasHerbalismXp(Material material) {
        return herbalismXpMap.get(material) != null;
    }

    /**
     * Determines whether or not a block has Woodcutting XP
     *
     * @param material target block material type
     * @return true if the block has valid xp registers
     */
    public boolean hasWoodcuttingXp(Material material) {
        return woodcuttingXpMap.get(material) != null;
    }

    /**
     * Determines whether or not a block has Excavation XP
     *
     * @param material target block material type
     * @return true if the block has valid xp registers
     */
    public boolean hasExcavationXp(Material material) {
        return excavationXpMap.get(material) != null;
    }

    /**
     * Gets the XP value for breaking this block from the xp map
     *
     * @param material the target block material
     * @return the raw XP value before any modifiers are applied
     */
    public int getMiningXp(Material material) {
        return miningXpMap.get(material);
    }

    /**
     * Gets the XP value for breaking this block from the xp map
     *
     * @param material the target block material
     * @return the raw XP value before any modifiers are applied
     */
    public int getHerbalismXp(Material material) {
        return herbalismXpMap.get(material);
    }

    /**
     * Gets the XP value for breaking this block from the xp map
     *
     * @param material the target block material
     * @return the raw XP value before any modifiers are applied
     */
    public int getWoodcuttingXp(Material material) {
        return woodcuttingXpMap.get(material);
    }

    /**
     * Gets the XP value for breaking this block from the xp map
     *
     * @param material the target block material
     * @return the raw XP value before any modifiers are applied
     */
    public int getExcavationXp(Material material) {
        return excavationXpMap.get(material);
    }

    @Override
    public void unload() {
        miningXpMap.clear();
        woodcuttingXpMap.clear();
        herbalismXpMap.clear();
        excavationXpMap.clear();
    }
}