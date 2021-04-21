package meu.vinil

import grails.gorm.services.Service

@Service(Genero)
interface GeneroService {

    Genero get(Serializable id)

    List<Genero> list(Map args)

    Long count()

    Genero delete(Serializable id)

    Genero save(Genero genero)

}
