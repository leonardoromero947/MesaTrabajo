package com.example.demo.mesa;

import com.pi4j.io.gpio.PinState;

public class ControladorMotores {

  public void servicioAPP()throws InterruptedException{
			boolean	validarArmado = aperturaCubiertaA();
			if(validarArmado)
				validarArmado = aperturaCubiertaB();
			if(validarArmado)
				cubiertasA_B_Abiertas();
			if(validarArmado)
				validarArmado = elevandoCubiertaCentral();
	}

	public void movimientoMotoresApp(String valor)throws InterruptedException{
		boolean validarArmado = true;
		if(valor != "ABIERTA"){
				System.out.println("Estado inicial mesa :ABIERTA");
			if(validarArmado)
				validarArmado = aperturaCubiertaA();
			if(validarArmado)
				validarArmado = aperturaCubiertaB();
			if(validarArmado)
				cubiertasA_B_Abiertas();
			if(validarArmado)
				validarArmado = elevandoCubiertaCentral();
			if(validarArmado)
				cubiertaCentralArriba();
			if(validarArmado)
				validarArmado = cerrandoCubiertaA(1);
			if(validarArmado)
				validarArmado = cerrandoCubiertaB(1);
		}else{
			System.out.println("Estado Inicial Mesa :CERRADA");
			if(validarArmado)
				validarArmado = aperturaCubiertaA();
			if(validarArmado)
				validarArmado = aperturaCubiertaB();
			if(validarArmado)
				cubiertasA_B_Abiertas();
			if(validarArmado)
				validarArmado = bajandoCubiertaCentral();
			if(validarArmado)
				cubiertaCentralAbajo();
			if(validarArmado)
				validarArmado = cerrandoCubiertaA(2);
			if(validarArmado)
				validarArmado = cerrandoCubiertaB(2);

			System.out.println("Mesa Cerrada");
		}
	}
	
	public boolean movimientoMotores(String proceso) throws InterruptedException {
		boolean validarArmado = true;
		if(proceso == "ABIERTA"){
			System.out.println("Mesa Cerrada");
			if(validarArmado)
				validarArmado = aperturaCubiertaA();
			if(validarArmado)
				validarArmado = aperturaCubiertaB();
			if(validarArmado)
				cubiertasA_B_Abiertas();
			if(validarArmado)
				validarArmado = elevandoCubiertaCentral();
			if(validarArmado)
				cubiertaCentralArriba();
			if(validarArmado)
				validarArmado = cerrandoCubiertaA(1);
			if(validarArmado)
				validarArmado = cerrandoCubiertaB(1);
				System.out.println("Mesa Abierta");
		}else{
			System.out.println("Mesa Abierta");
			if(validarArmado)
				validarArmado = aperturaCubiertaA();
			if(validarArmado)
				validarArmado = aperturaCubiertaB();
			if(validarArmado)
				cubiertasA_B_Abiertas();
			if(validarArmado)
				validarArmado = bajandoCubiertaCentral();
			if(validarArmado)
				cubiertaCentralAbajo();
			if(validarArmado)
				validarArmado = cerrandoCubiertaA(2);
			if(validarArmado)
				validarArmado = cerrandoCubiertaB(2);
			
			System.out.println("Mesa Cerrada");
		}
		return validarArmado;

	}
	
	
	public boolean aperturaCubiertaA() throws InterruptedException{
		//Led uno apagado y Led Tres activado Abriendo cubierta B
		long createdMillis = System.currentTimeMillis();
		int contadorActSensor = 0;
		boolean respuesta = false;
		while(true){
			if(com.rest.mesa.demo.MesaApplication.controlSensores.entradaSensor()){
				if(Validacion.tiempoEspera(Timer.cronometro(createdMillis), VariablesTiempo.aperturaCubiertaA, contadorActSensor)){
					break;
				}
				com.rest.mesa.demo.MesaApplication.led1.setState(com.pi4j.io.gpio.PinState.HIGH);
				if (com.rest.mesa.demo.MesaApplication.boton_1A.getState().toString() == "HIGH"){
					Thread.sleep(VariablesTiempo.tiempoEspera);
					com.rest.mesa.demo.MesaApplication.led1.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
			}else{
				contadorActSensor += 1;
				com.rest.mesa.demo.MesaApplication.led1.setState(com.pi4j.io.gpio.PinState.LOW);
				Thread.sleep(com.rest.mesa.demo.MesaApplication.seguroDelay);
			}
		}
		return respuesta;
	}
	
	public boolean aperturaCubiertaB() throws InterruptedException{
		//Led uno apagado y Led Tres activado Abriendo cubierta B
		long createdMillis = System.currentTimeMillis();
		int contadorActSensor = 0;
		boolean respuesta = false;
		while(true){
			if(Validacion.tiempoEspera(Timer.cronometro(createdMillis), VariablesTiempo.aperturaCubiertaB, contadorActSensor)){
				break;
			}
			if(com.rest.mesa.demo.MesaApplication.controlSensores.entradaSensor()){
				com.rest.mesa.demo.MesaApplication.led3.setState(com.pi4j.io.gpio.PinState.HIGH);
				if (com.rest.mesa.demo.MesaApplication.boton_1B.getState().toString() == "HIGH"){
					Thread.sleep(VariablesTiempo.tiempoEspera);
					com.rest.mesa.demo.MesaApplication.led3.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
			}else{
				contadorActSensor += 1;
				com.rest.mesa.demo.MesaApplication.led3.setState(com.pi4j.io.gpio.PinState.LOW);
				Thread.sleep(com.rest.mesa.demo.MesaApplication.seguroDelay);
			}
		}
		return respuesta;
	}
	
	public void cubiertasA_B_Abiertas() throws InterruptedException{
		//Led Tres apagado Cuebiertas A y B  Abiertas
		while(true){
			if(com.rest.mesa.demo.MesaApplication.boton_1A.getState().toString() == "HIGH" && com.rest.mesa.demo.MesaApplication.boton_1B.getState().toString() == "HIGH"){
				Thread.sleep(VariablesTiempo.tiempoEspera);
				break;
			}
		}
	}
	
	public boolean elevandoCubiertaCentral() throws InterruptedException{
		//Led Tres apagado y Led Cinco  Elevando Cubierta Central"
		long createdMillis = System.currentTimeMillis();
		boolean respuesta = false;
		int contadorActSensor = 0;
		while(true){
			if(Validacion.tiempoEspera(Timer.cronometro(createdMillis), VariablesTiempo.elevandoCubiertaCentral, contadorActSensor)){
				break;
			}
			if(com.rest.mesa.demo.MesaApplication.controlSensores.entradaSensor()){
				com.rest.mesa.demo.MesaApplication.led5.setState(com.pi4j.io.gpio.PinState.HIGH);
				if (com.rest.mesa.demo.MesaApplication.boton_2A.getState().toString() == "HIGH"){
					Thread.sleep(VariablesTiempo.tiempoEspera2);
					com.rest.mesa.demo.MesaApplication.led5.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
			}else{
				contadorActSensor += 1;
				com.rest.mesa.demo.MesaApplication.led5.setState(com.pi4j.io.gpio.PinState.LOW);
				Thread.sleep(com.rest.mesa.demo.MesaApplication.seguroDelay);
			}
		}
		return respuesta;
	}
	
	public boolean bajandoCubiertaCentral() throws InterruptedException{
		//Led Seis  Bajando Cubierta Central
		long createdMillis = System.currentTimeMillis();
		int contadorActSensor = 0;
		boolean respuesta = false;
		while(true){
			if(Validacion.tiempoEspera(Timer.cronometro(createdMillis), VariablesTiempo.bajandoCubiertaCentral, contadorActSensor)){
				break;
			}
			if(com.rest.mesa.demo.MesaApplication.controlSensores.entradaSensor()){
				com.rest.mesa.demo.MesaApplication.led6.setState(com.pi4j.io.gpio.PinState.HIGH);
				if (com.rest.mesa.demo.MesaApplication.boton_2B.getState().toString() == "HIGH"){
					Thread.sleep(VariablesTiempo.tiempoEspera2);
					com.rest.mesa.demo.MesaApplication.led6.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
			}else{
				contadorActSensor += 1;
				com.rest.mesa.demo.MesaApplication.led6.setState(com.pi4j.io.gpio.PinState.LOW);
				Thread.sleep(com.rest.mesa.demo.MesaApplication.seguroDelay);
			}
		}
		return respuesta;
	}
	
	public void cubiertaCentralArriba() throws InterruptedException{
		//Led Cinco apagado Cubierta Central  Arriba
		while(true){
			if(com.rest.mesa.demo.MesaApplication.boton_2A.getState().toString() == "HIGH"){
				Thread.sleep(VariablesTiempo.tiempoEspera);
				break;
			}
		}
	}
	
	public void cubiertaCentralAbajo() throws InterruptedException{
		//Cubierta Central Abajo
		while(true){
			if(com.rest.mesa.demo.MesaApplication.boton_2B.getState().toString() == "HIGH"){
				Thread.sleep(VariablesTiempo.tiempoEspera);
				break;
			}
		}
	}
	
	public boolean cerrandoCubiertaA(int tipo) throws InterruptedException{
		//Activar led Dos cerrando Cubierta A
		long createdMillis = System.currentTimeMillis();
		int contadorActSensor = 0;
		int tiempo;
		boolean respuesta = false;
		if(tipo == 1){
			tiempo = VariablesTiempo.cerrandoCubiertaA1;
		}else{
			tiempo = VariablesTiempo.cerrandoCubiertaA2;
		}
		while(true){
			if(Validacion.tiempoEspera(Timer.cronometro(createdMillis), tiempo, contadorActSensor)){
				break;
			}
			if(com.rest.mesa.demo.MesaApplication.controlSensores.entradaSensor()){
				com.rest.mesa.demo.MesaApplication.led2.setState(com.pi4j.io.gpio.PinState.HIGH);
				if (com.rest.mesa.demo.MesaApplication.boton_4A.getState().toString() == "HIGH" && tipo == 1){
					Thread.sleep(VariablesTiempo.tiempoEspera);
					com.rest.mesa.demo.MesaApplication.led2.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
				if (com.rest.mesa.demo.MesaApplication.boton_3A.getState().toString() == "HIGH" && tipo == 2){
					Thread.sleep(VariablesTiempo.tiempoEspera);
					com.rest.mesa.demo.MesaApplication.led2.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
			}else{
				contadorActSensor += 1;
				com.rest.mesa.demo.MesaApplication.led2.setState(com.pi4j.io.gpio.PinState.LOW);
				Thread.sleep(com.rest.mesa.demo.MesaApplication.seguroDelay);
			}
		}
		return respuesta;
	}
	
	public boolean cerrandoCubiertaB(int tipo) throws InterruptedException{
		//Activar led  Cuatro  cerrando Cubierta B
		long createdMillis = System.currentTimeMillis();
		int contadorActSensor = 0;
		int tiempo;
		boolean respuesta = false;
		if(tipo == 1){
			tiempo = VariablesTiempo.cerrandoCubiertaB1;
		}else{
			tiempo = VariablesTiempo.cerrandoCubiertaB2;
		}
		while(true){
			if(Validacion.tiempoEspera(Timer.cronometro(createdMillis), tiempo, contadorActSensor)){
				break;
			}
			if(com.rest.mesa.demo.MesaApplication.controlSensores.entradaSensor()){
				com.rest.mesa.demo.MesaApplication.led4.setState(com.pi4j.io.gpio.PinState.HIGH);
				if (com.rest.mesa.demo.MesaApplication.boton_4B.getState().toString() == "HIGH" && tipo == 1){
					Thread.sleep(VariablesTiempo.tiempoEspera);
					com.rest.mesa.demo.MesaApplication.led4.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
				if (com.rest.mesa.demo.MesaApplication.boton_3B.getState().toString() == "HIGH" & tipo == 2){
					Thread.sleep(VariablesTiempo.tiempoEspera);
					com.rest.mesa.demo.MesaApplication.led4.setState(com.pi4j.io.gpio.PinState.LOW);
					Thread.sleep(VariablesTiempo.tiempoEspera);
					respuesta = true;
					break;
				}
			}else{
				contadorActSensor += 1;
				com.rest.mesa.demo.MesaApplication.led4.setState(com.pi4j.io.gpio.PinState.LOW);
				Thread.sleep(com.rest.mesa.demo.MesaApplication.seguroDelay);
			}
		}
		return respuesta;
	}
	
	public void seguro() throws InterruptedException{
		while(true){
			//apagado temporal
			if(com.rest.mesa.demo.MesaApplication.boton_2B.getState().toString() == "HIGH" && com.rest.mesa.demo.MesaApplication.boton_2A.getState().toString() == "HIGH"){
				break;
			}
			
			if(com.rest.mesa.demo.MesaApplication.lock.getState().toString() == "HIGH" && com.rest.mesa.demo.MesaApplication.botonInicio2.getState().toString() == "HIGH" ){
				break;
			}
			if(com.rest.mesa.demo.MesaApplication.lock.getState().toString() == "LOW"){
				com.rest.mesa.demo.MesaApplication.led7.setState(com.pi4j.io.gpio.PinState.HIGH);
				com.rest.mesa.demo.MesaApplication.led8.setState(com.pi4j.io.gpio.PinState.LOW);
			}
			if(com.rest.mesa.demo.MesaApplication.lock.getState().toString() == "HIGH"){
				com.rest.mesa.demo.MesaApplication.led7.setState(com.pi4j.io.gpio.PinState.LOW);
				com.rest.mesa.demo.MesaApplication.led8.setState(com.pi4j.io.gpio.PinState.HIGH);
			}
			if(com.rest.mesa.demo.MesaApplication.botonInicio1.getState().toString() == "HIGH"){
				com.rest.mesa.demo.MesaApplication.buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
				Thread.sleep(300);
				com.rest.mesa.demo.MesaApplication.buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
			}
			if(com.rest.mesa.demo.MesaApplication.botonInicio2.getState().toString() == "HIGH" && com.rest.mesa.demo.MesaApplication.lock.getState().toString() == "LOW"){
				com.rest.mesa.demo.MesaApplication.buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
				Thread.sleep(300);
				com.rest.mesa.demo.MesaApplication.buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
			}
		}
	}
 
 public void informe() throws InterruptedException{
   Validacion.informe();
 }
 
 
 	public void movimientoCauto(int orden) throws InterruptedException{
		Validacion.informe();
		if(orden == 1){
			cerrandoCubiertaA(1);
      	Validacion.informe();
		}
   if(orden == 2){
			cerrandoCubiertaA(2);
      	Validacion.informe();
		}
   if(orden == 3){
			cerrandoCubiertaB(1);
      	Validacion.informe();
		}
   if(orden == 4){
			cerrandoCubiertaB(2);
      	Validacion.informe();
		}
   if(orden == 5){
			bajandoCubiertaCentral();
      	Validacion.informe();
		}
   if(orden == 6){
			cubiertaCentralArriba();
      	Validacion.informe();
   }
	}
 
 
	
	public String validarPosicion() throws InterruptedException{
		String cadena = "CERRADA";
		if(Validacion.validaMesaCerrada()){
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionAbriendoCubiertaA()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaA(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_A_Abierta()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaA(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionAbriendoCubiertaB()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaA(2);
			if(cerrandoCubiertaA(2))
				cerrandoCubiertaB(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_B_Abierta()){
			System.out.println("Mesa Fail");
			if(cerrandoCubiertaA(2))
			cerrandoCubiertaB(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionAbriendoCubiertaCentral()){
			System.out.println("Mesa Fail");
			boolean validacion = true;
			if(validacion)
				validacion = bajandoCubiertaCentral();
			if(validacion)
				cubiertaCentralAbajo();
			if(validacion)
				validacion = cerrandoCubiertaA(2);
			if(validacion)
				validacion = cerrandoCubiertaB(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_Central_Abierta()){
			System.out.println("Mesa Fail");
			boolean validacion = true;
			if(validacion)
				validacion = bajandoCubiertaCentral();
			if(validacion)
				cubiertaCentralAbajo();
			if(validacion)
				validacion = cerrandoCubiertaA(2);
			if(validacion)
				validacion = cerrandoCubiertaB(2);
				System.out.println("Mesa OK");
		}
		if(Validacion.validacionCerrandoCubiertaA()){
			System.out.println("Mesa Fail");
			if(cerrandoCubiertaA(1))
			cerrandoCubiertaB(1);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_A_Cerrada()){
			System.out.println("Mesa Fail");
			if(cerrandoCubiertaA(1));
			cerrandoCubiertaB(1);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCerrandoCubiertaB()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaB(1);
			cadena = "ABIERTA";
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionMesaAbierta()){
			System.out.println("Mesa OK");
			cadena = "ABIERTA";
		}
		if(Validacion.validacionAbrirCubierta_A_MesaAbierta()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaA(1);
			cadena = "ABIERTA";
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_A_AbiertaMesaAbierta()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaA(1);
			cadena = "ABIERTA";
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_B_AbiertaMesaAbierta()){
			System.out.println("Mesa Fail");
			if(cerrandoCubiertaA(1))
			cerrandoCubiertaB(1);
			cadena = "ABIERTA";
			System.out.println("Mesa OK");
		}
		
		if(Validacion.validacionCerrandoCubierta_A_MesaAbierta()){
			System.out.println("Mesa Fail");
			if(cerrandoCubiertaA(2))
			cerrandoCubiertaB(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_B_CerradaMesaAbierta()){
			System.out.println("Mesa Fail");
			cerrandoCubiertaB(2);
			System.out.println("Mesa OK");
		}
		if(Validacion.validacionCubierta_A_CerradaMesaAbierta()){
			System.out.println("Mesa Fail");
			if(cerrandoCubiertaA(2))
			cerrandoCubiertaB(2);
			System.out.println("Mesa OK");
		}
		return cadena;
	}
 
 	public RespuestaAppDTO validarPosicionApp() throws InterruptedException{
		RespuestaAppDTO respuestaAppDTO = new RespuestaAppDTO();
		respuestaAppDTO.setCadena("NULL");
		respuestaAppDTO.setValidacion(false);
		if(Validacion.validaMesaCerrada()){
			respuestaAppDTO.setCadena("CERRADA");
      System.out.println("MESA CERRADA");
			respuestaAppDTO.setValidacion(true);
		}
		if(Validacion.validacionMesaAbierta()){
			respuestaAppDTO.setCadena("ABIERTA");
      System.out.println("MESA ABIERTA");
			respuestaAppDTO.setValidacion(true);
		}
		if(com.rest.mesa.demo.MesaApplication.lock.getState().toString() == "LOW"){
			respuestaAppDTO.setCadena("SEGURO");
      System.out.println("SEGURO");
			respuestaAppDTO.setValidacion(false);
		}
		return respuestaAppDTO;
	}
 
 
 	public String validarPosicionBoton() throws InterruptedException{
		String accionSigiente = "";
		if(Validacion.validaMesaCerrada()){
			accionSigiente = "ABIERTA";
		}
		if(Validacion.validacionMesaAbierta()){
			accionSigiente = "CERRADA";
		}
		return accionSigiente;
	}
	
	public static void eliminaMotores(){
		com.rest.mesa.demo.MesaApplication.led1.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led2.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led3.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led4.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led5.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led6.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led7.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led8.setState(com.pi4j.io.gpio.PinState.LOW);
		com.rest.mesa.demo.MesaApplication.led10.setState(com.pi4j.io.gpio.PinState.LOW);
	}
}