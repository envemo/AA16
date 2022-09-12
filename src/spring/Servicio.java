package spring;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;


@Service //Funciones y metodos a implementar USANDO AOP
public class Servicio 
{
	public void generartxt(List<String> lineas)
	{
		try
		{
			Path file = Paths.get("recaudaciones.txt");
			Files.write(file, lineas, StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			System.out.println("Ocurrió un error");
			e.printStackTrace();
		}
	}
	
	public List<String> leerArchivo(String nombreArchivo) {
		List<String> lineas = new ArrayList<String>();
		File documento = new File(nombreArchivo);
		try {
			Scanner sc = new Scanner(documento);
			while (sc.hasNextLine())
			{
				lineas.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineas;
	}
	
}