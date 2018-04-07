//CS Java
//Period 2
//Donovan and Steven

package ClickingGame;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
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

	public static int updateCSV(String filename, int score)
	{
		List<String> scores = new ArrayList<String>();
		scores = CSVreader(filename);
		
		int n = scores.size();
		int pos = -1;
		
		try 
		{
			for (int i = 2; i < n; i+=2) 
			{
				if (score > Integer.parseInt(scores.get(i)))
				{
					for (int j = n - 1; j >= i + 2; j-=2)
					{
						scores.set(j, scores.get(j - 2));
					}
					
					scores.set(i, String.valueOf(score));
					pos = i/2;
					break;
				}
			}
			
			//object - append or add to file
			//allows me to write stuff
			FileWriter f = new FileWriter(filename, false);
			//extension of filewriter
			//makes it write more efficient?
			BufferedWriter b = new BufferedWriter(f);
			//prints the written stuff using filewriter and bufferedwriter
			//to the file
			PrintWriter p = new PrintWriter(b);
			
			String s = scores.get(0);
			for (int i = 1 ; i < n - 1; i+=2)
			{
				s+= "\n" + scores.get(i) + "," + scores.get(i+1);
			}
			
			p.print(s);
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
		
		return pos;
	}

}
