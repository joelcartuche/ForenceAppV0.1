package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Perito;
import modelos.Solicitante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T23:06:40")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> telefonoCelular;
    public static volatile CollectionAttribute<Persona, Solicitante> solicitanteCollection;
    public static volatile SingularAttribute<Persona, String> nombreApellido;
    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile CollectionAttribute<Persona, Perito> peritoCollection;
    public static volatile SingularAttribute<Persona, String> correo;
    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile SingularAttribute<Persona, Integer> idPersona;
    public static volatile SingularAttribute<Persona, String> telefonoFijo;

}