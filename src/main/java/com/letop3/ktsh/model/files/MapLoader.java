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

    public MapLoader(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        Map<String, ChunkData[]> jsonData = mapper.readValue(file, new TypeReference<Map<String, ChunkData[]>>() {});
        ChunkData[] chunkDataList = jsonData.get("chunks");

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

    public Chunk[] getChunks() {
        return chunkMap.values().toArray(new Chunk[0]);
    }

    @Override
    public void close() {
        // Implémentation de la méthode de fermeture si nécessaire
    }
}