package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Perito;
import modelos.Solicitante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T23:06:40")
@StaticMetamodel(Proceso.class)
public class Proceso_ { 

    public static volatile SingularAttribute<Proceso, Double> valorPericia;
    public static volatile SingularAttribute<Proceso, Perito> idPerito;
    public static volatile SingularAttribute<Proceso, Date> fechaLimiteEntrega;
    public static volatile SingularAttribute<Proceso, Date> fechaSolicitud;
    public static volatile SingularAttribute<Proceso, Solicitante> idSolicitante;
    public static volatile SingularAttribute<Proceso, Integer> idProceso;

}