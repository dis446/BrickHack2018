package model;


public class World {

	public static final double bigG = 6.67408e-11;
	public static final double lengthUnit = 1e7;
	public static final double timeUnit = 1;

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