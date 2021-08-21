package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Persona;
import modelos.Proceso;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T23:06:40")
@StaticMetamodel(Solicitante.class)
public class Solicitante_ { 

    public static volatile SingularAttribute<Solicitante, String> unidadPertenece;
    public static volatile CollectionAttribute<Solicitante, Proceso> procesoCollection;
    public static volatile SingularAttribute<Solicitante, String> numeroProceso;
    public static volatile SingularAttribute<Solicitante, Integer> idSolicitante;
    public static volatile SingularAttribute<Solicitante, String> urlPericia;
    public static volatile SingularAttribute<Solicitante, Persona> idPersona;

}