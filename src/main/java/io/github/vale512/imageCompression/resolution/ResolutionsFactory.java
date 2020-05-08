package io.github.vale512.imageCompression.resolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ResolutionsFactory {

	private static final String VALUES_SEPARATOR = ","; //We are reading from a csv file
	private static Set<Resolution> RESOLUTIONS = null;

	/**
	 * Create a list of resolutions, according to a given configuration file.
	 * 
	 * @return a list of {@link Resolution}.
	 * @param resolutionPath
	 */
	public static Set<Resolution> getOrCreate(String resolutionPath) {
		if(RESOLUTIONS == null)
			RESOLUTIONS = parseResolutionsFile(resolutionPath);
		return RESOLUTIONS;
	}

	public static Set<Resolution> parseResolutionsFile(String resolutionPath) {
		Path path = Paths.get(resolutionPath);
		BufferedReader reader;
		Set<String> resolutions = new HashSet<>();
		try {
			reader = Files.newBufferedReader(path);
			String line = reader.readLine();
			while(line != null) {
				Collections.addAll(resolutions, line.split(VALUES_SEPARATOR));
				line = reader.readLine();
			}
			reader = Files.newBufferedReader(path);
		} catch (IOException e) {
			System.err.println("Error while parsing resolutions file " + resolutionPath);
			e.printStackTrace();
		}
		return toResolutionsSet(resolutions);
	}

	private static Set<Resolution> toResolutionsSet(Set<String> resolutions) {
		Set<Resolution> res = new HashSet<>();
		for(String resolution : resolutions) {
			StringTokenizer st = new StringTokenizer(resolution, Resolution.RESOLUTIONS_DELIMITER);
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			res.add(new Resolution(width, height));
		}
		return res;
	}
}
