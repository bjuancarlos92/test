package com.inventario.vacunacion.utility;

import java.util.Random;

import com.inventario.vacunacion.entity.EmpleadoEntity;

import org.apache.commons.lang3.RandomStringUtils;

public class Metodo {
    
    public String generateRandomPassword(int len) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random(len, chars);
	}

    public String generateUsername(EmpleadoEntity empleado) {

        Random generator = new Random();
        String usuario = empleado.getNombre().replaceAll("\\s+", "").toLowerCase().substring(0, 2)
                + empleado.getApellido().replaceAll("\\s+", "").toLowerCase().substring(0, 6)
                + String.valueOf(generator.nextInt(89) + 10);

        return usuario;
    }
}
