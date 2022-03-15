package LogicaNegocio.modelo;

public class UsuarioAlumno extends Usuario {
    
    private String grupo;
    private String curso;
    
    public UsuarioAlumno(String nombre, String apellidos, String email, String contraseña, String grupo, String curso) {
        
        super(nombre, apellidos, email, contraseña);
        this.grupo = grupo;
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getCurso() {
        return curso;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return super.toString() +  ", grupo=" + grupo + ", curso=" + curso + '}';
    }
    
}
