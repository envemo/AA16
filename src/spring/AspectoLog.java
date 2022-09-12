package spring;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.ParameterMetaData;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component


public class AspectoLog 
{
	@Before("execution(* leerArchivo(String)) && args(archivo)") //* cualquier generarTxt Antes
	public void log(String archivo)
	{
		
		try {
            Paths.get(archivo);
            System.out.println("Archivo válido: " + archivo);
        } catch (InvalidPathException ex) {
            ex.printStackTrace();
            System.out.println("Archivo inválido: " + archivo);
        }

	}
	
	//https://stackoverflow.com/questions/6315948/java-lang-illegalargumentexception-warning-no-match-for-this-type-name-userdao
	@Before("execution(* generartxt(java.util.List)))") //* cualquier generarTxt Antes
	public void log2()
	{
		System.out.println("generando TXT");
		
	}
	
	//https://stackoverflow.com/questions/6315948/java-lang-illegalargumentexception-warning-no-match-for-this-type-name-userdao
	@After("execution(* generartxt(java.util.List)))") //Despues
	public void log3()
	{
		System.out.println("TXT generado");
	}
	
}
/*
ASPECTOS: El concepto de aspecto es el ELEMENTO FUNDAMENTAL DEL PARADIGMA. SE EMPLEA PARA DEFINIR
UNA FUNCIONALIDAD TRANSVERSAL!!!!! AL SOFTWARE. 
PUNTO DE CORTE
CONSEJO O ADVICE 
*/