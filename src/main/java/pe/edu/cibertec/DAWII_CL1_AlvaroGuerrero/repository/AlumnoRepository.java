package pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DAWII_CL1_AlvaroGuerrero.model.bd.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {


}
