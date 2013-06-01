package com.mathill.cc.game.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mathill.cc.game.block.BlockData;

public class ControllerRegister {

    private Map<Integer, BlockData> blockData = new LinkedHashMap<Integer, BlockData>();

    public BlockData getBlockData(int metadataId) {

        return blockData.get(metadataId);
    }

    public boolean addBlockData(int metadataId,
                                BlockData data) {

        if (blockData.containsKey(metadataId)) {

            blockData.put(metadataId, data);
            return true;
        } else {

            return false;
        }
    }

    public List<Integer> getValidMetaDataIds() {

        return new ArrayList<Integer>(blockData.keySet());
    }

    public BlockData removeBlockData(int metaDataId) {

        return blockData.remove(metaDataId);
    }
}
