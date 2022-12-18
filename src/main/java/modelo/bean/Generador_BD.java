package modelo.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class Generador_BD {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.out.println("EJECUTANDO EL MAIN..... \n");
		BLFacade db = new BLFacadeImplementation();
		db.initializeBD();
		
	}
}
