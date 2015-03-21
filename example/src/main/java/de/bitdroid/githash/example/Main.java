package de.bitdroid.githash.example;


public class Main {

	public static void main(String[] args) {
		System.out.printf("%-25s\"%s\"\n", "Current version", GitConstants.VERSION);
		System.out.printf("%-25s\"%s\"", "Current commit hash", GitConstants.COMMIT_HASH);
	}

}
