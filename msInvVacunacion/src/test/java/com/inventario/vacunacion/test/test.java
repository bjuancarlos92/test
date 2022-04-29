package com.inventario.vacunacion.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inventario.vacunacion.entity.EmpleadoEntity;
import com.inventario.vacunacion.entity.TokenEntity;
import com.inventario.vacunacion.service.EmpleadoController;
import com.inventario.vacunacion.service.TokenController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigurationTest.class })
public class test {
	
	@Autowired
	private EmpleadoController empleado;
	
	@Autowired
	private TokenController token;
	
	@Test
	public void test() {
	    
	    ResponseEntity<TokenEntity> tokenRecibido = token.generarToken("Juan Carlos","jguevara");
	    ResponseEntity<List<EmpleadoEntity>> empleados = empleado.consultaEmpleados();
	    ResponseEntity<EmpleadoEntity> emplead = empleado.getEmpleado(1);
	
	}

}
