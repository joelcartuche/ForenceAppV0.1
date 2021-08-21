package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Persona;
import modelos.Proceso;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T23:06:40")
@StaticMetamodel(Perito.class)
public class Perito_ { 

    public static volatile SingularAttribute<Perito, Integer> idPerito;
    public static volatile CollectionAttribute<Perito, Proceso> procesoCollection;
    public static volatile SingularAttribute<Perito, String> numeroCasoInterno;
    public static volatile SingularAttribute<Perito, Persona> idPersona;

}