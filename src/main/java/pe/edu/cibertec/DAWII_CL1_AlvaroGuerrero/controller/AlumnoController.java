package pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.model.bd.Alumno;
import pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.model.bd.Especialidad;
import pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.model.request.AlumnoRequest;
import pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.model.response.ResultadoResponse;
import pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.service.AlumnoService;

import java.util.List;

@Controller
@RequestMapping("/Alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/frmMantAlumno")
    public String frmMantAlumno(Model model){
        model.addAttribute("listaAlumnos", alumnoService.listarAlumnos());
        return "Alumno/frmMantAlumno";
    }

    @PostMapping("/registrarAlumno")
    public ResultadoResponse registrarAlumno(@RequestBody AlumnoRequest alumnoRequest){
        String mensaje = "Alumno registrado correctamente.";
        Boolean respuesta = true;
        try {
            Alumno objAlumno = new Alumno();
            objAlumno.setNomalumno(alumnoRequest.getNomalumno());
            objAlumno.setApealumno(alumnoRequest.getApealumno());
            objAlumno.setProce(alumnoRequest.getProce());

            Especialidad objEspecialidad = new Especialidad();
            objEspecialidad.setIdesp(alumnoRequest.getIdesp());

            objAlumno.setEspecialidad(objEspecialidad);
            alumnoService.registrarAlumno(objAlumno);

        }catch (Exception ex){
            mensaje = "Alumno no registrado.";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();

    }

    @DeleteMapping("/eliminarAlumno")
    @ResponseBody
    public ResultadoResponse eliminarAlumno(@RequestBody AlumnoRequest alumnoRequest){
        String mensaje = "Alumno eliminado correctamente.";
        Boolean respuesta = true;
        try {
            alumnoService.eliminarAlumno(alumnoRequest.getIdalumno());
        }catch (Exception ex){
            mensaje = "Alumno no eliminado.";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();

    }

    @GetMapping("/listarSalas")
    @ResponseBody
    public List<Alumno> listarAlumnos(){
        return alumnoService.listarAlumnos();
    }


}
