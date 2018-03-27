package ClickingGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Backend 
{
	public static List<String> CSVreader(String filename)
	{
		List<String> scores = new ArrayList<String>();
		Path pathToFile = Paths.get(filename);

		try (BufferedReader br = Files.newBufferedReader(pathToFile,  StandardCharsets.US_ASCII))
		{
			String line = br.readLine();

			while (line != null)
			{
				String[] elements = line.split(",");

				for (String s: elements)
				{
					scores.add(s);
				}

				line = br.readLine();
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return scores;
	}
}
