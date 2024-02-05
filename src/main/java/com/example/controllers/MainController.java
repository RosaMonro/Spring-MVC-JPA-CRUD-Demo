package com.example.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

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

        // Cuando se recibe un parámetro conjuntamente con la request se procede dde esta manera:
        // Se utiliza también actualmente, pero menos que enviar una variable en la ruta
        // @GetMapping("/detalles")
        // public String detallesEmpleado(@RequestParam(name = "id") int idEmpleado, Model model) {

        //     LOG.info("ID Empleado Recibido: " + idEmpleado);

        //     return "views/empleadoDetalles";
        // }


        @GetMapping("/detalles/{id}")
        public String detallesEmpleado(@PathVariable(name = "id") int idEmpleado, Model model) {

            LOG.info("ID Empleado Recibido: " + idEmpleado);

            // con esto va el empleado para la vista (para que salga el nombre en el título)
            model.addAttribute("empleado", empleadoService.dameUnEmpleado(idEmpleado));

            return "views/empleadoDetalles";

        }

        @GetMapping("/frmAltaModificacion")
        public String formularioAltaModificacionEmpleado(Model model) {

            // Le paso un modelo vacío (eso se rellena en el formulario)
            Empleado empleado = new Empleado();

            model.addAttribute("empleado", empleado);

            // Necesito envíar tb los departamentos
            model.addAttribute("departamentos", departamentoService.dameDepartamentos());

            return "views/frmAltaModificacionEmpleado";
        }


        @PostMapping("/persistir")
        public String persistirEmpleado(@ModelAttribute(name = "empleado") Empleado empleado,
            @RequestParam(name = "numerosTel", required = false) String telefonosRecibidos,
            @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos) {

        // Procesar los telefonos

        if(telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";");
            List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);

            List<Telefono> telefonos = new ArrayList<>();

            numerosTelefonos.stream()
                .forEach(numeroTelefono -> {
                    telefonos.add(Telefono.builder()
                        .numero(numeroTelefono)
                        .empleado(empleado)
                        .build());
                });
            
            empleado.setTelefonos(telefonos);
        }


        // Procesar los correos

        if(correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> direccionesDeCorreo = Arrays.asList(arrayCorreos);

            List<Correo> correos = new ArrayList<>();

            direccionesDeCorreo.stream()
                .forEach(direccionDeCorreo -> {
                    correos.add(Correo.builder()
                        .correo(direccionDeCorreo)
                        .empleado(empleado)
                        .build());
                });

            empleado.setCorreos(correos);
        }

        empleadoService.persistirEmpleado(empleado);

            return "redirect:/all";
        }




        // Modificar un empleqado:

        @GetMapping("/actualizar/{id}")
        public String actualizarEmpleado(@PathVariable(name = "id", required = true) int idEmpleado, Model model) {

            // llamamos al método de buscar al empleado para recuperar al empleado cuyo id se recibe como parámetro
            Empleado empleado = empleadoService.dameUnEmpleado(idEmpleado);
            model.addAttribute("empleado", empleado);

                // También necesito los departamentos
                List<Departamento> departamentos = departamentoService.dameDepartamentos();
                model.addAttribute("departamentos", departamentos);

                // la lista de teléfonos y correos ya los tengo por el cascadeo. 
                // pero necesito construir el numero y la dirección unidos por ; a partir 
                // de los teléfonos y correos recibidos conjuntamente con el empleado
                if (empleado.getTelefonos() != null) {
                    String numerosTelefono = empleado.getTelefonos()
                                                    .stream()
                                                    .map(Telefono::getNumero)
                                                    .collect(Collectors.joining(";"));

                    model.addAttribute("numerosTelefono", numerosTelefono);
                }


            // Mandar al empleado al formulario de alta
            if (empleado.getCorreos() != null) {
                String direccionesDeCorreo = empleado.getCorreos()
                                                .stream()
                                                .map(Correo::getCorreo)
                                                .collect(Collectors.joining(";"));
                                                
                model.addAttribute("direccionesDeCorreo", direccionesDeCorreo);
            }

            return "views/frmAltaModificacionEmpleado";
        }


}
