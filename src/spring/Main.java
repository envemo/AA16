package spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main 
{
	public static void main (String [] args)
	{
	
		try (
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurarSpring.class)) {
			Servicio servicio = ctx.getBean(Servicio.class);
			List<String> lineas = servicio.leerArchivo("Artistas 2020.txt");
			lineas.addAll(servicio.leerArchivo("Artistas 2021.txt"));
			List<Artista> artistas = new ArrayList<Artista>();
			for(String linea : lineas) {
				artistas.add(new Artista(linea));
			}
			lineas = servicio.leerArchivo("Canciones 2020.txt");
			lineas.addAll(servicio.leerArchivo("Canciones 2020.txt"));
			List<String> lineas_txt = new ArrayList<String>();
			lineas_txt.add("CANCIONES");
			lineas_txt.add("----------------------------");
			for(String linea : lineas) {
				if (linea != "") {
					lineas_txt.add(linea);
					//https://stackoverflow.com/questions/1635764/string-parsing-in-java-with-delimiter-tab-t-using-split
					for (Artista artista : artistas) {
						if (linea.contains(artista.nombre)) {
							String recaudacion = linea.split("\\t")[linea.split("\\t").length - 1];
							recaudacion = recaudacion.split(" ")[recaudacion.split(" ").length - 1];
							artista.calcularRecaudacion(recaudacion);
						}
					}
				}
			}
			lineas_txt.add("");
			lineas_txt.add("ARTISTAS");
			lineas_txt.add("----------------------------");
			for (Artista artista : artistas) {
				lineas_txt.add(artista.toString());
			}
			servicio.generartxt(lineas_txt);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}

class Persona
{
	String nombre;
}

class Artista extends Persona implements calcular
{
	int recaudacion;

	public Artista(String  nombre) {
		super();
		super.nombre = nombre;
	}

	@Override
	public void calcularRecaudacion(String recaudacion) {
		this.recaudacion = this.recaudacion + Integer.valueOf(recaudacion.replace(".", ""));
	}
	
	@Override
	public String toString() {
		return nombre + " " + recaudacion;
	}
}

interface calcular {
	void calcularRecaudacion(String recaudacion);
}