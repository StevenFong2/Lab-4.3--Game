//CS Java
//Period 2
//Donovan and Steven

package ClickingGame;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
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

	public static void updateCSV(String filename, String replace)
	{
		try
		{
			//object - append or add to file
			//allows me to write stuff
			FileWriter f = new FileWriter(filename, false);
			//extension of filewriter
			//makes it write more efficient?
			BufferedWriter b = new BufferedWriter(f);
			//prints the written stuff using filewriter and bufferedwriter
			//to the file
			PrintWriter p = new PrintWriter(b);

			p.print(replace);
			//flushes the stream...whatever that means
			//makes sure all data written to file?
			p.flush();
			//closes the filewriter stuff?
			p.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

}
