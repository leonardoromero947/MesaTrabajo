package com.rest.mesa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.mesa.RespuestaAppDTO;

@SpringBootApplication
@org.springframework.boot.autoconfigure.EnableAutoConfiguration
@org.springframework.context.annotation.Configuration
@org.springframework.context.annotation.ComponentScan({ "com.example.demo.controller, com.example.demo.service" })
public class MesaApplication {

    public final static com.pi4j.io.gpio.GpioController gpioController = com.pi4j.io.gpio.GpioFactory.getInstance();

    //COntroladores
    public final static com.example.demo.mesa.ControlSensores controlSensores = new com.example.demo.mesa.ControlSensores();
    public final static com.example.demo.mesa.ControladorMotores controladorMotores = new com.example.demo.mesa.ControladorMotores();

    //Activacion sensores
    public final static com.pi4j.io.gpio.GpioPinDigitalInput sensor1 = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_28, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput sensor2 = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_29, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);

    //Activacion buzzer
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput buzzer = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_05, "Buzzer", com.pi4j.io.gpio.PinState.LOW);

    //Activacion Leds
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led1 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_16, "Led1", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led2 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_01, "Led2", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led3 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_04, "Led3", com.pi4j.io.gpio.PinState.LOW);
    public  final static com.pi4j.io.gpio.GpioPinDigitalOutput led4 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_06, "Led4", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led5 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_26, "Led5", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led6 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_27, "Led6", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led7 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_10, "Led7", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led8 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_11, "Led8", com.pi4j.io.gpio.PinState.LOW);
    public final static com.pi4j.io.gpio.GpioPinDigitalOutput led10 = gpioController.provisionDigitalOutputPin(com.pi4j.io.gpio.RaspiPin.GPIO_07, "Led10", com.pi4j.io.gpio.PinState.LOW);


    //Activacion botones de inicio
    public final static com.pi4j.io.gpio.GpioPinDigitalInput botonInicio1 = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_24, "Switch1", com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput botonInicio2 = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_25, "Switch2", com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);

    //Activacion botones de desplasamiento
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_1B = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_03, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_1A = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_02, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_2A = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_12, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_2B = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_13, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_4A = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_22, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_4B = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_23, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_3A = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_14, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);
    public final static com.pi4j.io.gpio.GpioPinDigitalInput boton_3B = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_21
            , com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);

    //Activacion de seguro
    public final static com.pi4j.io.gpio.GpioPinDigitalInput lock = gpioController.provisionDigitalInputPin(com.pi4j.io.gpio.RaspiPin.GPIO_00, com.pi4j.io.gpio.PinPullResistance.PULL_DOWN);


    public final static int seguroDelay = 2000;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MesaApplication.class, args);
        buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
        Thread.sleep(100);
        buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
        buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
        Thread.sleep(100);
        buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
        boolean seguroActivo = false;
        com.example.demo.mesa.ControladorMotores.eliminaMotores();
        boolean procesoCompletado = true;
        //Valida si la mesa esta en estado de cerrada o abierta y si no lo pondra en alguna posicion de estas
        String valor = controladorMotores.validarPosicion();
        while(true){
            valor = controladorMotores.validarPosicionBoton();
            if(procesoCompletado){
                if(lock.getState().toString() == "HIGH"){
                    //Seguro desactivado
                    led8.setState(com.pi4j.io.gpio.PinState.HIGH);
                    led7.setState(com.pi4j.io.gpio.PinState.LOW);
                    seguroActivo = false;
                }
                if(lock.getState().toString() == "LOW" ){
                    //Seguro activado
                    led8.setState(com.pi4j.io.gpio.PinState.LOW);
                    led7.setState(com.pi4j.io.gpio.PinState.HIGH);
                    seguroActivo = true;
                }
                if(seguroActivo == false && botonInicio1.getState().toString() == "HIGH" && valor == "ABIERTA"){
                    System.out.println("La mesa ya esta abierta");
                    buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
                    Thread.sleep(300);
                    buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
                }

                if(seguroActivo == false && botonInicio2.getState().toString() == "HIGH" && valor == "CERRADA"){
                    System.out.println("La mesa ya esta cerrada");
                    buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
                    Thread.sleep(300);
                    buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
                }

                if(seguroActivo == false && botonInicio1.getState().toString() == "HIGH" && valor == "CERRADA"){
                    procesoCompletado = controladorMotores.movimientoMotores(valor);
                }

                if(seguroActivo == false && botonInicio2.getState().toString() == "HIGH" && valor == "ABIERTA"){
                    procesoCompletado = controladorMotores.movimientoMotores(valor);
                }
                
                if(seguroActivo == true && botonInicio1.getState().toString() == "HIGH"){
                    System.out.println("No puedo ejecutar mientras el seguro esta activo");
                    buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
                    Thread.sleep(300);
                    buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
                }
                if(seguroActivo == true && botonInicio2.getState().toString() == "HIGH"){
                    System.out.println("No puedo ejecutar mientras el seguro esta activo");
                    buzzer.setState(com.pi4j.io.gpio.PinState.HIGH);
                    Thread.sleep(300);
                    buzzer.setState(com.pi4j.io.gpio.PinState.LOW);
                }
            }

        }
    }
}
