package model;


public class World {

	Chunk[][] chunks;

	public World(int length, int width){
		this.chunks = new Chunk[length][width];
		for (int i = 0; i < length; i++){
			for (int j = 0; j < width; j++){
				Chunk chunk = new Chunk(i, j);
				chunks[i][j] = chunk;
			}
		}
	}

	public Chunk getChunk(int row, int col){
		return chunks[row][col];
	}

}
