package com.mathill.cc.game.block;

import com.mathill.cc.lib.Locations;

public class ModBlocks {

    public final static BlockData PIPE_EXTRUDER;
    public final static BlockData PIPE_ROLLER;
    public final static BlockData CONTROLLER;
    public final static BlockData MULTI_FURNACE_DUMMY;

    static {

        PIPE_EXTRUDER = new BlockData();
        PIPE_EXTRUDER.blockName = "blockPipeExtruder";
        PIPE_EXTRUDER.blockId = 3000;
        PIPE_EXTRUDER.blockIdDefault = 3000;
        PIPE_EXTRUDER.block = null;
        PIPE_EXTRUDER.tileRenderId = 0;
        PIPE_EXTRUDER.tileName = "tilePipeExtruder";
        PIPE_EXTRUDER.tileTexture = Locations.MODEL_LOCATION + "alchemicalChest.png";
        PIPE_EXTRUDER.guiId = 0;
        PIPE_EXTRUDER.guiTexture = Locations.GUI_LOCATION + "PipeExtruder.png";

        PIPE_ROLLER = new BlockData();
        PIPE_ROLLER.blockId = 3001;
        PIPE_ROLLER.blockIdDefault = 3001;
        PIPE_ROLLER.tileRenderId = 0;
        PIPE_ROLLER.tileName = "tilePipeRoller";
        PIPE_ROLLER.tileTexture = "/mods/cconduits/textures/blocks/calcinator.png";
        PIPE_ROLLER.tileModel = "/mods/cconduits/models/roller.obj";
        PIPE_ROLLER.guiId = 0;
        PIPE_ROLLER.guiTexture = Locations.GUI_LOCATION + "roller.png";

        CONTROLLER = new BlockData();
        CONTROLLER.blockId = 3002;
        CONTROLLER.blockIdDefault = 3002;
        CONTROLLER.tileRenderId = 0;
        CONTROLLER.tileName = "tileController";
        CONTROLLER.tileTexture = "/mods/cconduits/textures/blocks/controller.png";
        CONTROLLER.tileModel = "";
        CONTROLLER.guiId = 0;
        CONTROLLER.guiTexture = Locations.GUI_LOCATION + "controller.png";

        MULTI_FURNACE_DUMMY = new BlockData();
        MULTI_FURNACE_DUMMY.blockId = 3003;
        MULTI_FURNACE_DUMMY.blockIdDefault = 3003;
        MULTI_FURNACE_DUMMY.tileRenderId = 0;
        MULTI_FURNACE_DUMMY.tileName = "tileMultiFurnaceDummy";
        MULTI_FURNACE_DUMMY.tileTexture = "/mods/cconduits/textures/blocks/MultiFurnaceDummy.png";
        MULTI_FURNACE_DUMMY.tileModel = "";
        MULTI_FURNACE_DUMMY.guiId = 0;
        MULTI_FURNACE_DUMMY.guiTexture = Locations.GUI_LOCATION + "MultiFurnaceDummy.png";
    }

    private static void initControllerData() {

        BlockData data;
        data = new BlockData();
        data.blockId = 3002;
        data.blockIdDefault = 3002;
        data.blockName = "StoneController";
        data.tileRenderId = 0;
        data.tileName = "tileController";
        data.tileTexture = "/mods/cconduits/textures/blocks/controller.png";
        data.tileModel = "";
        data.guiId = 0;
        data.guiTexture = Locations.GUI_LOCATION + "controller.png";
    }
}
