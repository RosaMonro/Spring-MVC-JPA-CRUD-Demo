package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;

    private final  Logger LOG = Logger.getLogger("MainController");

    // CÓMO SE HACÍA ANTES:

    // @GetMapping("/all")
    // public ModelAndView dameEmpleados() {
    //     ModelAndView modelo = new ModelAndView("views/listadoEmpleados");

    //     List<Empleado> empleados = empleadoService.dameTodosLosEmpleados(); 

    //     modelo.addObject("empleados", empleados); // esto lo mandamos a la vista

    //     return modelo;
    // }

        @GetMapping("/all")
        public String dameEmpleados(Model model) { // model del org.springframework

            model.addAttribute("empleados", empleadoService.dameTodosLosEmpleados());

            return "views/listadoEmpleados";
        }


        @GetMapping("/detalles")
        public String detallesEmpleado(@RequestParam(name = "id") int idEmpleado, Model model) {

            LOG.info("ID Empleado Recibido: " + idEmpleado);

            return "views/empleadoDetalles";
        }

}
