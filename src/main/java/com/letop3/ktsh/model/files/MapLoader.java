package com.letop3.ktsh.model.files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letop3.ktsh.model.ground.Chunk;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapLoader implements Closeable {
    private final Map<Integer, Chunk> chunkMap = new HashMap<>();
    private static int chunkWidth;
    private static int chunkHeight;

    public MapLoader(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        Map<String, Object> jsonData = mapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        ChunkData[] chunkDataList = mapper.convertValue(jsonData.get("chunks"), ChunkData[].class);
        chunkWidth = (Integer) jsonData.get("chunk_width");
        chunkHeight = (Integer) jsonData.get("chunk_height");

        // Créer les chunks sans voisins
        for (ChunkData chunkData : chunkDataList) {
            Chunk chunk = new Chunk(chunkData.id, chunkData.tiles);
            chunkMap.put(chunk.getId(), chunk);
        }

        // Ajouter les voisins après que tous les chunks aient été créés
        for (ChunkData chunkData : chunkDataList) {
            Chunk chunk = chunkMap.get(chunkData.id);
            for (int neighborId : chunkData.neighbors) {
                Chunk neighbor = chunkMap.get(neighborId);
                if (neighbor != null) {
                    chunk.addNeighbor(neighbor);
                }
            }
        }
    }

    public Chunk getChunkById(int chunkId) {
        return chunkMap.get(chunkId);
    }

    @Override
    public void close() {
        // Implémentation de la méthode de fermeture si nécessaire
    }
}